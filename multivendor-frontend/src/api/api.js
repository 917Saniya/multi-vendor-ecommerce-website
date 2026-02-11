import axios from "axios";

/**
 * Re-usable Axios instance.
 *
 * - Configured with a base URL pointing to the Spring Boot backend.
 * - Automatically attaches the JWT token (if present) to every request.
 *
 * NOTE: Adjust `baseURL` if your backend runs on a different host/port or path.
 */
const api = axios.create({
  baseURL: "http://localhost:8080/api",
  headers: {
    "Content-Type": "application/json",
  },
});

// Attach JWT token from localStorage to every request
api.interceptors.request.use((config) => {
  try {
    const storedAuth = localStorage.getItem("auth");
    if (storedAuth) {
      const { token } = JSON.parse(storedAuth);
      if (token) {
        // Standard Bearer token header
        config.headers.Authorization = `Bearer ${token}`;
      }
    }
  } catch {
    // If parsing fails, silently ignore and send the request without auth header
  }
  return config;
});

export default api;
