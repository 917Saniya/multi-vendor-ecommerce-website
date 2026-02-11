import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import api from "../../api/api";

function Register() {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [role, setRole] = useState("USER");
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      setLoading(true);
      setError("");
      setSuccess("");

      /**
       * Adjust endpoint and payload to match your backend.
       * A typical Spring Boot controller might accept:
       * { name, email, password, role }
       */
      await api.post("/register", { name, email, password, role });
      setSuccess("Registration successful. You can now log in.");

      setTimeout(() => navigate("/login"), 1200);
    } catch (err) {
      console.error(err);
      setError("Registration failed. Please try again.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="container py-4 page-fade" style={{ maxWidth: 520 }}>
      <h3 className="mb-3">Register</h3>
      <form onSubmit={handleSubmit} className="card p-3 shadow-sm border-0">
        <div className="mb-3">
          <label htmlFor="name" className="form-label">
            Name
          </label>
          <input
            id="name"
            type="text"
            className="form-control"
            value={name}
            onChange={(e) => setName(e.target.value)}
            required
          />
        </div>
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
        <div className="mb-3">
          <label htmlFor="role" className="form-label">
            Register As
          </label>
          <select
            id="role"
            className="form-select"
            value={role}
            onChange={(e) => setRole(e.target.value)}
          >
            <option value="USER">User</option>
            <option value="SELLER">Seller</option>
          </select>
        </div>
        {error && <p className="text-danger small">{error}</p>}
        {success && <p className="text-success small">{success}</p>}
        <button
          type="submit"
          className="btn btn-primary w-100 btn-animate"
          disabled={loading}
        >
          {loading ? "Registering..." : "Register"}
        </button>
        <p className="mt-3 mb-0 text-center small">
          Already have an account?{" "}
          <Link to="/login" className="link-primary">
            Login
          </Link>
        </p>
      </form>
    </div>
  );
}

export default Register;
