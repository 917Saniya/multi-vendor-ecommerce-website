function Footer() {
  return (
    <footer className="bg-dark text-light py-3 mt-auto">
      <div className="container d-flex flex-column flex-md-row justify-content-between align-items-center gap-2">
        <span className="small">
          © {new Date().getFullYear()} MultiVendor Store. All rights reserved.
        </span>
        <span className="small text-secondary">
          Built with React, Vite, Bootstrap, and Spring Boot APIs.
        </span>
      </div>
    </footer>
  );
}

export default Footer;