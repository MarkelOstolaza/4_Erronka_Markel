document.addEventListener('DOMContentLoaded', () => {
    
    // 1. MOCK DATA (Datu simulatuak)
    // Hau benetako datu base batetik etorriko litzateke etorkizunean
    const statsData = {
        totalEarnings: 25450.95,
        monthlyEarnings: [
            { month: "Urtarrila 2026", amount: 4500 },
            { month: "Otsaila 2026", amount: 3200 },
            { month: "Martxoa 2026", amount: 5100 }
        ],
        lowStock: [ // Stock gutxi dutenak (< 5 unitate)
            { name: "Fjallraven Backpack", stock: 2 },
            { name: "Mens Cotton Jacket", stock: 1 },
            { name: "Gold Ring", stock: 0 }
        ],
        bestSellers: [
            { name: "WD 2TB Hard Drive", units: 150 },
            { name: "SanDisk SSD", units: 120 },
            { name: "Silicon Power SSD", units: 95 }
        ],
        topCustomers: [
            { name: "Jon Ander", orders: 12 },
            { name: "Ane Miren", orders: 9 },
            { name: "Mikel O.", orders: 8 }
        ],
        unsoldProducts: [
            "Acer SB220Q Monitor",
            "Samsung 49-Inch Curved",
            "Women's Rain Jacket"
        ],
        highRevenueProducts: [ // 500€ baino gehiago fakturatu dutenak
            { name: "Dan V. Gold Chain", total: 1200 },
            { name: "John Hardy Bracelet", total: 950 },
            { name: "Solid Gold Petite Micropave", total: 600 }
        ]
    };

    // 2. FUNTZIOAK DATUAK BISTARATZEKO

    // Irabazi guztizkoak
    const earningsElement = document.getElementById('total-earnings-display');
    if(earningsElement) {
        earningsElement.innerText = statsData.totalEarnings.toFixed(2) + "€";
    }

    // Zerrendak betetzeko funtzio orokorra
    function fillList(listId, dataArray, formatFn) {
        const list = document.getElementById(listId);
        if (list) {
            list.innerHTML = "";
            dataArray.forEach(item => {
                const li = document.createElement('li');
                li.innerHTML = formatFn(item);
                list.appendChild(li);
            });
        }
    }

    // Hileko irabaziak
    fillList('monthly-earnings-list', statsData.monthlyEarnings, 
        (item) => `<strong>${item.month}:</strong> ${item.amount}€`);

    // Stock baxua
    fillList('low-stock-list', statsData.lowStock, 
        (item) => `<span style="color:red">${item.name}</span> (Aleak: ${item.stock})`);

    // Gehien salduak
    fillList('top-products-list', statsData.bestSellers, 
        (item) => `${item.name} - <b>${item.units} salmenta</b>`);

    // Bezero onenak
    fillList('top-customers-list', statsData.topCustomers, 
        (item) => `${item.name} (${item.orders} eskaera)`);

    // Inoiz saldu gabeak
    fillList('unsold-products-list', statsData.unsoldProducts, 
        (item) => item);

    // +500€ irabaziak
    fillList('high-revenue-list', statsData.highRevenueProducts, 
        (item) => `${item.name}: <span style="color:green">+${item.total}€</span>`);

    actualizarNumeroCarrito();
});