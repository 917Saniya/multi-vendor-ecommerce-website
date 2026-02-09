import { useContext, useState } from "react";
import { Link, NavLink, useNavigate } from "react-router-dom";
import { AuthContext } from "../context/AuthContext";

function Navbar() {
  const navigate = useNavigate();
  const auth = useContext(AuthContext);
   const [searchTerm, setSearchTerm] = useState("");

  const handleLogout = () => {
    auth?.logout?.();
    navigate("/");
  };

  const handleSearchSubmit = (e) => {
    e.preventDefault();
    if (!searchTerm.trim()) return;
    // For now, navigate to products and rely on backend search/filter.
    navigate(`/products?query=${encodeURIComponent(searchTerm.trim())}`);
  };

  return (
    <nav className="navbar navbar-expand-lg navbar-dark shadow-sm sticky-top" style={{ background: "linear-gradient(90deg, #0f172a, #111827)" }}>
      <div className="container">
        <Link className="navbar-brand fw-bold" to="/">
          <span className="me-1">MarketSphere</span>
        </Link>
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#mainNavbar"
          aria-controls="mainNavbar"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon" />
        </button>

        <div className="collapse navbar-collapse" id="mainNavbar">
          <ul className="navbar-nav me-3 mb-2 mb-lg-0">
            <li className="nav-item">
              <NavLink
                end
                to="/"
                className={({ isActive }) =>
                  `nav-link nav-link-underline ${isActive ? "active" : ""}`
                }
              >
                Home
              </NavLink>
            </li>
            <li className="nav-item">
              <NavLink
                to="/products"
                className={({ isActive }) =>
                  `nav-link nav-link-underline ${isActive ? "active" : ""}`
                }
              >
                Products
              </NavLink>
            </li>

            {/* Seller menu */}
            {auth?.role === "SELLER" && (
              <>
                <li className="nav-item">
                  <NavLink to="/seller" className="nav-link">
                    Seller Dashboard
                  </NavLink>
                </li>
                <li className="nav-item">
                  <NavLink to="/seller/my-products" className="nav-link">
                    My Products
                  </NavLink>
                </li>
              </>
            )}

            {/* Admin menu */}
            {auth?.role === "ADMIN" && (
              <li className="nav-item dropdown">
                <button
                  className="nav-link dropdown-toggle btn btn-link"
                  id="adminDropdown"
                  data-bs-toggle="dropdown"
                  aria-expanded="false"
                >
                  Admin
                </button>
                <ul
                  className="dropdown-menu"
                  aria-labelledby="adminDropdown"
                >
                  <li>
                    <NavLink to="/admin" className="dropdown-item">
                      Dashboard
                    </NavLink>
                  </li>
                  <li>
                    <NavLink to="/admin/users" className="dropdown-item">
                      Manage Users
                    </NavLink>
                  </li>
                  <li>
                    <NavLink to="/admin/products" className="dropdown-item">
                      Manage Products
                    </NavLink>
                  </li>
                </ul>
              </li>
            )}
          </ul>

          {/* Center search bar on desktop */}
          <form
            className="d-flex flex-grow-1 mb-2 mb-lg-0 me-lg-3"
            onSubmit={handleSearchSubmit}
          >
            <input
              className="form-control form-control-sm me-2"
              type="search"
              placeholder="Search products..."
              aria-label="Search products"
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
            />
            <button
              className="btn btn-outline-light btn-sm btn-animate"
              type="submit"
            >
              Search
            </button>
          </form>

          <ul className="navbar-nav ms-auto mb-2 mb-lg-0 align-items-lg-center">
            {/* Cart is visible for users */}
            {auth?.role === "USER" && (
              <li className="nav-item me-lg-2">
                <NavLink to="/cart" className="btn btn-outline-light btn-sm">
                  Cart
                </NavLink>
              </li>
            )}

            {!auth?.isAuthenticated && (
              <>
                <li className="nav-item me-lg-2">
                  <NavLink to="/login" className="btn btn-outline-light btn-sm">
                    Login
                  </NavLink>
                </li>
                <li className="nav-item">
                  <NavLink to="/register" className="btn btn-light btn-sm">
                    Register
                  </NavLink>
                </li>
              </>
            )}

            {auth?.isAuthenticated && (
              <>
                <li className="nav-item me-lg-2 text-white-50 small">
                  {auth.user?.name
                    ? `Hi, ${auth.user.name}`
                    : auth.role || "Logged in"}
                </li>
                <li className="nav-item">
                  <button
                    type="button"
                    className="btn btn-danger btn-sm"
                    onClick={handleLogout}
                  >
                    Logout
                  </button>
                </li>
              </>
            )}
          </ul>
        </div>
      </div>
    </nav>
  );
}

export default Navbar;
