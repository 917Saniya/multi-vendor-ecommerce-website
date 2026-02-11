import { useContext } from "react";
import { Navigate, useLocation } from "react-router-dom";
import { AuthContext } from "../context/AuthContext";

/**
 * Simple, reusable protected route component.
 *
 * Usage:
 *   <ProtectedRoute allowedRoles={['USER']}>
 *     <Cart />
 *   </ProtectedRoute>
 *
 * - Redirects unauthenticated users to `/login`
 * - Redirects authenticated users without the required role back to `/`
 */
function ProtectedRoute({ allowedRoles, children }) {
  const location = useLocation();
  const { isAuthenticated, role } = useContext(AuthContext) || {};

  if (!isAuthenticated) {
    return <Navigate to="/login" replace state={{ from: location }} />;
  }

  if (allowedRoles && allowedRoles.length > 0 && !allowedRoles.includes(role)) {
    return <Navigate to="/" replace />;
  }

  return children;
}

export default ProtectedRoute;
