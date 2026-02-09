import { Link } from "react-router-dom";

function SellerDashboard() {
  // In a real app, these would come from an API
  const metrics = {
    products: 18,
    orders: 42,
  };

  return (
    <div className="container py-4 page-fade">
      <div className="row gy-3">
        <div className="col-md-3">
          <div className="sidebar p-3">
            <h5 className="mb-3">Seller Menu</h5>
            <ul className="nav flex-column gap-1">
              <li className="nav-item">
                <span className="nav-link text-white-50">Overview</span>
              </li>
              <li className="nav-item">
                <Link to="/seller/add" className="nav-link text-white">
                  Add Product
                </Link>
              </li>
              <li className="nav-item">
                <Link to="/seller/my-products" className="nav-link text-white">
                  My Products
                </Link>
              </li>
            </ul>
          </div>
        </div>
        <div className="col-md-9">
          <h2 className="mb-3">Seller Dashboard</h2>
          <div className="row gy-3 mb-3">
            <div className="col-sm-6">
              <div className="metric-card p-3">
                <div className="text-muted-soft small mb-1">Products</div>
                <div className="metric-value">{metrics.products}</div>
              </div>
            </div>
            <div className="col-sm-6">
              <div className="metric-card p-3">
                <div className="text-muted-soft small mb-1">Orders</div>
                <div className="metric-value">{metrics.orders}</div>
              </div>
            </div>
          </div>
          <div className="card-elevated p-3">
            <div className="d-flex justify-content-between align-items-center mb-2">
              <h5 className="mb-0">Quick Actions</h5>
              <Link
                to="/seller/add"
                className="btn btn-primary btn-sm btn-animate"
              >
                Add New Product
              </Link>
            </div>
            <p className="text-muted-soft mb-0">
              Use the sidebar to manage your catalog and view your latest
              orders.
            </p>
          </div>
        </div>
      </div>
    </div>
  );
}
export default SellerDashboard;
