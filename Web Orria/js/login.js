document.addEventListener('DOMContentLoaded', () => {
    const loginForm = document.getElementById('login-form');
    const messageDiv = document.getElementById('login-message');

    if (loginForm) {
        loginForm.addEventListener('submit', (e) => {
            e.preventDefault(); // Orria ez birkargatzeko

            const username = document.getElementById('username').value;
            const password = document.getElementById('password').value;

            // Balidazio sinplea (Mock Login)
            if (username === 'admin' && password === '1234') {
                // Login ZUZENA
                messageDiv.textContent = "Logina ondo burutu da. Ongi etorri!";
                messageDiv.className = "message success"; // Kolore berdea jartzeko
                
                // 2 segundora orrialde nagusira bidali (aukerakoa)
                setTimeout(() => {
                    window.location.href = "index.html";
                }, 2000);

            } else {
                // Login OKERRA
                messageDiv.textContent = "Erabiltzailea edo pasahitza okerrak dira.";
                messageDiv.className = "message error"; // Kolore gorria jartzeko
            }
        });
    }

    // Saskiaren zenbakia eguneratu
    actualizarNumeroCarrito();
});