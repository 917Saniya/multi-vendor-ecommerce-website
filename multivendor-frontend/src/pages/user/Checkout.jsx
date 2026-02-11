import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../../api/api";

function Checkout() {
  const navigate = useNavigate();
  const [submitting, setSubmitting] = useState(false);
  const [error, setError] = useState("");
  const [successMessage, setSuccessMessage] = useState("");

  const handlePlaceOrder = async () => {
    const cart = JSON.parse(localStorage.getItem("cart") || "[]");
    if (cart.length === 0) {
      setError("Your cart is empty.");
      return;
    }

    try {
      setSubmitting(true);
      setError("");

      // Minimal example payload. Adjust to match your backend contract.
      const payload = {
        items: cart.map((item) => ({
          productId: item.id,
          quantity: item.quantity || 1,
        })),
        paymentMode: "COD",
      };

      await api.post("/orders", payload);
      localStorage.removeItem("cart");
      setSuccessMessage("Order placed successfully with Cash on Delivery.");

      // Redirect after a short delay so the user can read the message
      setTimeout(() => {
        navigate("/");
      }, 1500);
    } catch (err) {
      console.error(err);
      setError("Failed to place order. Please try again.");
    } finally {
      setSubmitting(false);
    }
  };

  return (
    <div className="container py-3">
      <h3 className="mb-3">Checkout</h3>
      <p className="text-muted">
        This demo uses a dummy payment method:{" "}
        <strong>Cash on Delivery (COD)</strong>.
      </p>

      {error && <p className="text-danger">{error}</p>}
      {successMessage && <p className="text-success">{successMessage}</p>}

      <button
        className="btn btn-success"
        disabled={submitting}
        onClick={handlePlaceOrder}
      >
        {submitting ? "Placing order..." : "Place Order (Cash on Delivery)"}
      </button>
    </div>
  );
}

export default Checkout;
