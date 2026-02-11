import { useEffect, useState } from "react";
import api from "../../api/api";
import ProductCard from "../../components/ProductCard";

function ProductList() {
  const [products, setProducts] = useState([]);
  const [categories, setCategories] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState("ALL");
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const fetchCategories = async () => {
    try {
      const res = await api.get("/categories");
      setCategories(res.data || []);
    } catch {
      // Keep UI simple if categories endpoint is not wired yet
      setCategories([]);
    }
  };

  const fetchProducts = async (category) => {
    try {
      setLoading(true);
      setError("");
      const params =
        category && category !== "ALL" ? { category: category } : undefined;
      const res = await api.get("/products", { params });
      setProducts(res.data || []);
    } catch (err) {
      setError("Failed to load products. Please try again.");
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchCategories();
    fetchProducts();
  }, []);

  const handleAddToCart = (product) => {
    const existing = JSON.parse(localStorage.getItem("cart") || "[]");
    existing.push({ ...product, quantity: 1 });
    localStorage.setItem("cart", JSON.stringify(existing));
    // Simple feedback; you can replace with a toast later
    alert("Added to cart");
  };

  const handleCategoryChange = (e) => {
    const value = e.target.value;
    setSelectedCategory(value);
    fetchProducts(value);
  };

  return (
    <div className="container py-3 page-fade">
      <div className="d-flex flex-column flex-md-row justify-content-between align-items-md-center gap-3 mb-3">
        <h2 className="mb-0">Products</h2>
        <div className="d-flex align-items-center gap-2">
          <label htmlFor="category" className="form-label mb-0 me-2">
            Category:
          </label>
          <select
            id="category"
            className="form-select form-select-sm"
            value={selectedCategory}
            onChange={handleCategoryChange}
          >
            <option value="ALL">All</option>
            {categories.map((cat) => (
              <option key={cat.id || cat} value={cat.name || cat}>
                {cat.name || cat}
              </option>
            ))}
          </select>
        </div>
      </div>

      {loading && (
        <div className="row g-3">
          {Array.from({ length: 8 }).map((_, idx) => (
            <div
              key={idx}
              className="col-12 col-sm-6 col-md-4 col-lg-3"
            >
              <div className="skeleton" style={{ height: 260 }} />
            </div>
          ))}
        </div>
      )}
      {error && <p className="text-danger">{error}</p>}

      <div className="row g-3">
        {!loading && products.length === 0 && (
          <p className="text-muted">No products found.</p>
        )}
        {products.map((p) => (
          <div key={p.id} className="col-12 col-sm-6 col-md-4 col-lg-3">
            <ProductCard product={p} onAddToCart={handleAddToCart} />
          </div>
        ))}
      </div>
    </div>
  );
}

export default ProductList;