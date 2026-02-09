import { useState } from "react";
import api from "../../api/api";

function AddProduct() {
  const [name, setName] = useState("");
  const [price, setPrice] = useState("");

  const addProduct = async () => {
    await api.post("/seller/products", { name, price });
    alert("Product Added");
  };

  return (
    <div className="container mt-4">
      <input className="form-control" placeholder="Product Name"
        onChange={(e) => setName(e.target.value)} />
      <input className="form-control mt-2" placeholder="Price"
        onChange={(e) => setPrice(e.target.value)} />
      <button className="btn btn-success mt-2" onClick={addProduct}>
        Add Product
      </button>
    </div>
  );
}
export default AddProduct;
