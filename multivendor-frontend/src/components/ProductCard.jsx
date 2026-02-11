function ProductCard({ product, onAddToCart }) {
  return (
    <div className="card product-card h-100 shadow-sm border-0">
      <div className="product-card-image bg-light">
        {/* Fallback gradient if backend does not provide an image URL yet */}
        {product.imageUrl ? (
          <img
            src={product.imageUrl}
            alt={product.name}
            className="img-fluid"
          />
        ) : (
          <div
            style={{
              height: 160,
              background:
                "linear-gradient(135deg, rgba(37,99,235,0.08), rgba(15,23,42,0.08))",
            }}
          />
        )}
      </div>
      <div className="card-body d-flex flex-column">
        <div className="mb-2 d-flex justify-content-between align-items-start">
          <div>
            <h5 className="card-title text-truncate mb-1">{product.name}</h5>
            {product.category && (
              <span className="badge bg-light text-dark small">
                {product.category}
              </span>
            )}
          </div>
          <span className="badge bg-primary-subtle text-primary-emphasis product-price-badge">
            ₹{Number(product.price || 0).toFixed(2)}
          </span>
        </div>
        <p className="card-text text-muted small flex-grow-1">
          {product.description || "No description available."}
        </p>
        <div className="d-flex justify-content-end mt-2">
          {onAddToCart && (
            <button
              className="btn btn-sm btn-primary btn-animate"
              onClick={() => onAddToCart(product)}
            >
              Add to Cart
            </button>
          )}
        </div>
      </div>
    </div>
  );
}

export default ProductCard;
