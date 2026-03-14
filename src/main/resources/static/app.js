const { useState, useEffect } = React;

const FlashSaleApp = () => {
    const [products, setProducts] = useState([]);
    const [message, setMessage] = useState({ text: '', type: '' });
    const [loading, setLoading] = useState(false);
    
    const API_BASE = "/api";
    const USER_ID = 1;

    const fetchProducts = async () => {
        try {
            const response = await fetch(`${API_BASE}/flash-sale/products`);
            const result = await response.json();
            if (result.httpStatus === 200) setProducts(result.data);
        } catch (err) { console.error(err); }
    };

    useEffect(() => {
        fetchProducts();
        const interval = setInterval(fetchProducts, 5000);
        return () => clearInterval(interval);
    }, []);

    const handleBuy = async (productId) => {
        setLoading(true);
        setMessage({ text: '', type: '' });
        try {
            const response = await fetch(`${API_BASE}/flash-sale/order`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ productId, quantity: 1, userId: USER_ID })
            });
            const result = await response.json();
            if (response.ok) {
                setMessage({ text: "✅ " + result.message, type: 'success' });
                fetchProducts();
            } else {
                setMessage({ text: "❌ " + (result.message || "Lỗi"), type: 'error' });
            }
        } catch (err) { setMessage({ text: "❌ Lỗi kết nối!", type: 'error' }); }
        finally { setLoading(false); }
    };

    return (
        <div className="container">
            <h1>⚡ SAPO FLASH SALE</h1>
            {message.text && <div className={`message ${message.type}`}>{message.text}</div>}
            <div className="product-grid">
                {products.map(p => (
                    <div key={p.productId} className="product-card">
                        <h3>{p.name}</h3>
                        <div>
                            <span className="price-original">{p.originalPrice ? p.originalPrice.toLocaleString() + 'đ' : ''}</span>
                            <span className="price-sale">{p.salePrice ? p.salePrice.toLocaleString() + 'đ' : ''}</span>
                        </div>
                        <p className="stock">Còn lại: <strong>{p.inStock}</strong></p>
                        <button className={`btn-buy ${p.inStock > 0 ? 'btn-active' : ''}`}
                            disabled={loading || p.inStock <= 0} onClick={() => handleBuy(p.productId)}>
                            {loading ? '...' : (p.inStock > 0 ? 'MUA NGAY' : 'HẾT HÀNG')}
                        </button>
                    </div>
                ))}
            </div>
        </div>
    );
};

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(<FlashSaleApp />);
