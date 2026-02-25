document.addEventListener('DOMContentLoaded', () => {
    const track = document.getElementById('carousel-track');
    const nextButton = document.getElementById('nextBtn');
    const prevButton = document.getElementById('prevBtn');
    let slides = [];
    let currentSlideIndex = 0;

    // 1. API-tik datuak ekarri (Lehenengo 5ak bakarrik)
    fetch('https://fakestoreapi.com/products?limit=5')
        .then(res => res.json())
        .then(products => {
            crearSlides(products);
        });

    function crearSlides(products) {
        products.forEach((product, index) => {
            const slide = document.createElement('li');
            slide.classList.add('carousel-slide');
            if (index === 0) slide.classList.add('current-slide'); // Lehenengoa aktibo

            slide.innerHTML = `
                <div class="slide-content">
                    <img src="${product.image}" alt="${product.title}">
                    <h3>${product.title}</h3>
                    <p>${product.price}€</p>
                    <button class="add-to-cart-btn">Saskira gehitu</button>
                </div>
            `;
            
            // Botoiaren funtzionalitatea
            slide.querySelector('button').addEventListener('click', () => {
                agregarAlCarrito(product); // cartService.js-tik
                alert("Produktua saskira gehitu da!");
            });

            track.appendChild(slide);
            slides.push(slide);
        });
    }

    // 2. Mugimendu logika
    nextButton.addEventListener('click', () => {
        const currentSlide = track.querySelector('.current-slide');
        let nextSlide = currentSlide.nextElementSibling;
        
        // Amaierara iristean, hasierara itzuli
        if (!nextSlide) {
            nextSlide = track.firstElementChild;
        }

        moveToSlide(currentSlide, nextSlide);
    });

    prevButton.addEventListener('click', () => {
        const currentSlide = track.querySelector('.current-slide');
        let prevSlide = currentSlide.previousElementSibling;

        // Hasieran bagaude, amaierara joan
        if (!prevSlide) {
            prevSlide = track.lastElementChild;
        }

        moveToSlide(currentSlide, prevSlide);
    });

    function moveToSlide(currentSlide, targetSlide) {
        currentSlide.classList.remove('current-slide');
        targetSlide.classList.add('current-slide');
    }

    actualizarNumeroCarrito();
});