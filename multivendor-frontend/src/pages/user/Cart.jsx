import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

function Cart() {
  const [items, setItems] = useState([]);

  useEffect(() => {
    const stored = JSON.parse(localStorage.getItem("cart") || "[]");
    setItems(stored);
  }, []);

  const updateStorage = (next) => {
    setItems(next);
    localStorage.setItem("cart", JSON.stringify(next));
  };

  const handleRemove = (index) => {
    const next = items.filter((_, i) => i !== index);
    updateStorage(next);
  };

  const total = items.reduce(
    (sum, item) => sum + Number(item.price || 0) * (item.quantity || 1),
    0
  );

  return (
    <div className="container py-3">
      <h3 className="mb-3">Cart</h3>
      {items.length === 0 ? (
        <p>
          Your cart is empty.{" "}
          <Link to="/products" className="link-primary">
            Browse products
          </Link>
        </p>
      ) : (
        <>
          <div className="list-group mb-3">
            {items.map((item, index) => (
              <div
                key={index}
                className="list-group-item d-flex justify-content-between align-items-center"
              >
                <div>
                  <div className="fw-semibold">{item.name}</div>
                  <small className="text-muted">
                    ₹{Number(item.price || 0).toFixed(2)} ×{" "}
                    {item.quantity || 1}
                  </small>
                </div>
                <button
                  type="button"
                  className="btn btn-sm btn-outline-danger"
                  onClick={() => handleRemove(index)}
                >
                  Remove
                </button>
              </div>
            ))}
          </div>
          <div className="d-flex justify-content-between align-items-center mb-3">
            <h5 className="mb-0">Total: ₹{total.toFixed(2)}</h5>
            <Link to="/checkout" className="btn btn-success">
              Proceed to Checkout
            </Link>
          </div>
        </>
      )}
    </div>
  );
}

export default Cart;
