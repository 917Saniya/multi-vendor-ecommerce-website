import { Link } from "react-router-dom";

function Home() {
  return (
    <div className="container py-4 page-fade">
      <div className="row align-items-center gy-4 mb-4">
        <div className="col-md-6 hero-text-animate">
          <h1 className="display-5 fw-bold mb-3">
            Your One-Stop Multi-Vendor Store
          </h1>
          <p className="lead text-muted mb-4">
            Browse products from multiple sellers, manage your own catalog, or
            control the entire marketplace as an admin — all from a single,
            clean dashboard.
          </p>
          <div className="d-flex flex-wrap gap-2">
            <Link
              to="/products"
              className="btn btn-primary btn-lg btn-animate"
            >
              Start Shopping
            </Link>
            <Link
              to="/seller"
              className="btn btn-outline-primary btn-animate"
            >
              Become a Seller
            </Link>
          </div>
        </div>
        <div className="col-md-6 text-center">
          <div className="hero-illustration mx-auto">
            <div className="hero-circle hero-circle-lg" />
            <div className="hero-circle hero-circle-md" />
            <div className="hero-circle hero-circle-sm" />
          </div>
        </div>
      </div>

      <div className="row gy-3">
        <div className="col-md-4 feature-card-stagger">
          <div className="card-elevated p-3 h-100">
            <h5 className="mb-2">Smart Catalog</h5>
            <p className="text-muted-soft mb-0">
              Category-wise product browsing with modern, responsive cards.
            </p>
          </div>
        </div>
        <div className="col-md-4 feature-card-stagger">
          <div className="card-elevated p-3 h-100">
            <h5 className="mb-2">Seller Tools</h5>
            <p className="text-muted-soft mb-0">
              Manage your products, pricing, and orders from a clean dashboard.
            </p>
          </div>
        </div>
        <div className="col-md-4 feature-card-stagger">
          <div className="card-elevated p-3 h-100">
            <h5 className="mb-2">Admin Controls</h5>
            <p className="text-muted-soft mb-0">
              Role-based access for monitoring users, sellers, and products.
            </p>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Home;
