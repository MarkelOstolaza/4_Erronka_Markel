let allProducts = []; // Hemen gordeko ditugu produktu guztiak

document.addEventListener('DOMContentLoaded', () => {
    // 1. Datuak API-tik ekarri
    fetch('https://fakestoreapi.com/products')
        .then(res => res.json())
        .then(data => {
            allProducts = data; // Datuak memorian gorde
            displayProducts(allProducts); // Hasieran denak erakutsi
            setupFilters(); // Botoiak prestatu
        })
        .catch(error => console.error('Errorea datuak kargatzean:', error));

    actualizarNumeroCarrito(); // Saskiaren zenbakia eguneratu (cartService.js-tik)
});

// Produktuak pantailan marrazteko funtzioa
function displayProducts(products) {
    const container = document.getElementById('products-container');
    container.innerHTML = ''; // Edukia garbitu

    if (products.length === 0) {
        container.innerHTML = '<p>Ez da produkturik aurkitu kategoria honetan.</p>';
        return;
    }

    products.forEach(product => {
        const card = document.createElement('div');
        card.classList.add('product-card');

        card.innerHTML = `
            <img src="${product.image}" alt="${product.title}">
            <h3>${product.title}</h3>
            <p class="product-price">${product.price.toFixed(2)}€</p>
            <button class="add-btn">Saskira gehitu</button>
        `;

        // "Saskira gehitu" botoiaren funtzionalitatea
        const btn = card.querySelector('.add-btn');
        btn.addEventListener('click', () => {
            agregarAlCarrito(product); // cartService.js funtzioa deitu
            alert("Produktua saskira gehitu da!");
        });

        container.appendChild(card);
    });
}

// Iragazki botoiak kudeatzeko funtzioa
function setupFilters() {
    const buttons = document.querySelectorAll('.filter-btn');

    buttons.forEach(btn => {
        btn.addEventListener('click', (e) => {
            // Kendu 'active' klasea botoi guztiei
            buttons.forEach(b => b.classList.remove('active'));
            // Gehitu 'active' klasea sakatu den botoiari
            e.target.classList.add('active');

            const category = e.target.dataset.category;

            if (category === 'all') {
                displayProducts(allProducts);
            } else {
                // Iragazi kategoriaren arabera
                const filtered = allProducts.filter(p => p.category === category);
                displayProducts(filtered);
            }
        });
    });
}