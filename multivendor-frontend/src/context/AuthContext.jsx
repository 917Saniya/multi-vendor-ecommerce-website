import { createContext, useContext, useEffect, useMemo, useState } from "react";

/**
 * AuthContext manages:
 * - JWT token
 * - Logged-in user basic info
 * - Current role (USER, SELLER, ADMIN)
 * - Helper methods: login, logout, hasRole
 *
 * The underlying data is persisted in localStorage under the `auth` key so that
 * the user stays logged in across page refreshes.
 */

// Roles expected from backend: "USER", "SELLER", "ADMIN"
export const AuthContext = createContext(null);

export const AuthProvider = ({ children }) => {
  const [authState, setAuthState] = useState(() => {
    // Initialize from localStorage if present
    try {
      const stored = localStorage.getItem("auth");
      return stored
        ? JSON.parse(stored)
        : { user: null, token: null, role: null };
    } catch {
      return { user: null, token: null, role: null };
    }
  });

  useEffect(() => {
    // Keep localStorage in sync with the current auth state
    if (authState?.token) {
      localStorage.setItem("auth", JSON.stringify(authState));
    } else {
      localStorage.removeItem("auth");
    }
  }, [authState]);

  const login = (payload) => {
    /**
     * `payload` should come from the login API response, and is expected to contain:
     * - token: JWT string
     * - role: "USER" | "SELLER" | "ADMIN"
     * - user: optional user object (name, email, etc.)
     *
     * This keeps the React code straightforward to explain in a viva/demo.
     */
    const next = {
      user: payload.user || null,
      token: payload.token,
      role: payload.role,
    };
    setAuthState(next);
  };

  const logout = () => {
    setAuthState({ user: null, token: null, role: null });
    localStorage.removeItem("auth");
    // Optional: clear role-only storage or cart
    localStorage.removeItem("cart");
  };

  const value = useMemo(() => {
    const isAuthenticated = !!authState.token;
    const hasRole = (allowedRoles = []) =>
      authState.role && allowedRoles.includes(authState.role);

    return {
      user: authState.user,
      token: authState.token,
      role: authState.role,
      isAuthenticated,
      hasRole,
      login,
      logout,
    };
  }, [authState]);

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
};

// Small helper hook for consuming the context
export const useAuth = () => useContext(AuthContext);
