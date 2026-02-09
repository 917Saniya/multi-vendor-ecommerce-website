import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

/**
 * Slide-in cart drawer component.
 * - Reads items from localStorage ("cart")
 * - Allows removal and shows total
 * - Designed to feel like a modern SaaS e‑commerce cart
 */
function CartDrawer({ isOpen, onClose }) {
  const [items, setItems] = useState([]);

  useEffect(() => {
    if (!isOpen) return;
    const stored = JSON.parse(localStorage.getItem("cart") || "[]");
    setItems(stored);
  }, [isOpen]);

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
    <div className={`cart-drawer-backdrop ${isOpen ? "open" : ""}`}>
      <aside
        className={`cart-drawer card-elevated ${
          isOpen ? "open" : ""
        } bg-white`}
      >
        <div className="d-flex justify-content-between align-items-center mb-2">
          <h5 className="mb-0">Cart</h5>
          <button
            type="button"
            className="btn btn-sm btn-outline-secondary"
            onClick={onClose}
          >
            Close
          </button>
        </div>

        {items.length === 0 ? (
          <p className="text-muted-soft mb-0">
            Your cart is empty. Browse products to add items.
          </p>
        ) : (
          <>
            <div className="cart-drawer-list mb-3">
              {items.map((item, index) => (
                <div
                  key={index}
                  className="d-flex justify-content-between align-items-center py-2 border-bottom"
                >
                  <div>
                    <div className="fw-semibold small">{item.name}</div>
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
            <div className="mt-auto">
              <div className="d-flex justify-content-between align-items-center mb-2">
                <span className="fw-semibold">Total</span>
                <span className="fw-bold">₹{total.toFixed(2)}</span>
              </div>
              <Link
                to="/cart"
                className="btn btn-outline-primary w-100 btn-animate mb-2"
                onClick={onClose}
              >
                View full cart
              </Link>
              <Link
                to="/checkout"
                className="btn btn-success w-100 btn-animate"
                onClick={onClose}
              >
                Checkout (COD)
              </Link>
            </div>
          </>
        )}
      </aside>
    </div>
  );
}

export default CartDrawer;

