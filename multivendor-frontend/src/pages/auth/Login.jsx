import { useContext, useState } from "react";
import { Link, useLocation, useNavigate } from "react-router-dom";
import api from "../../api/api";
import { AuthContext } from "../../context/AuthContext";

function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const auth = useContext(AuthContext);
  const navigate = useNavigate();
  const location = useLocation();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      setLoading(true);
      setError("");

      /**
       * Adjust the login endpoint and response mapping to match your backend.
       * This example assumes the backend returns:
       * { token: string, role: "USER" | "SELLER" | "ADMIN", user: { name, email, ... } }
       */
      const res = await api.post("/login", { email, password });

      auth?.login?.({
        token: res.data.token,
        role: res.data.role,
        user: res.data.user,
      });

      const from = location.state?.from?.pathname;
      if (from) {
        navigate(from, { replace: true });
      } else if (res.data.role === "SELLER") {
        navigate("/seller", { replace: true });
      } else if (res.data.role === "ADMIN") {
        navigate("/admin", { replace: true });
      } else {
        navigate("/", { replace: true });
      }
    } catch (err) {
      console.error(err);
      setError("Invalid email or password.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="container py-4 page-fade" style={{ maxWidth: 480 }}>
      <h3 className="mb-3">Login</h3>
      <form onSubmit={handleSubmit} className="card p-3 shadow-sm border-0">
        <div className="mb-3">
          <label htmlFor="email" className="form-label">
            Email
          </label>
          <input
            id="email"
            type="email"
            className="form-control"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="password" className="form-label">
            Password
          </label>
          <input
            id="password"
            type="password"
            className="form-control"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        {error && <p className="text-danger small">{error}</p>}
        <button
          type="submit"
          className="btn btn-primary w-100 btn-animate"
          disabled={loading}
        >
          {loading ? "Logging in..." : "Login"}
        </button>
        <p className="mt-3 mb-0 text-center small">
          Don&apos;t have an account?{" "}
          <Link to="/register" className="link-primary">
            Register
          </Link>
        </p>
      </form>
    </div>
  );
}

export default Login;
