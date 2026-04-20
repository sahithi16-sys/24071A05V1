const products = [
  { id: 1, name: "Wireless Bluetooth Headphones", description: "High-quality wireless headphones with noise isolation and long battery life.", price: 1499, quantity: 5 },
  { id: 2, name: "Smart Fitness Band", description: "Track your steps, heart rate, and sleep.", price: 2199, quantity: 8 },
  { id: 3, name: "Portable Bluetooth Speaker", description: "Compact speaker with deep bass.", price: 999, quantity: 10 },
  { id: 4, name: "USB-C Fast Charger", description: "Fast charging adapter.", price: 699, quantity: 12 },
  { id: 5, name: "Gaming Mouse RGB", description: "Ergonomic mouse with RGB.", price: 1299, quantity: 6 },
  { id: 6, name: "Mechanical Keyboard", description: "Durable keyboard with backlight.", price: 3499, quantity: 4 }
];

const $ = id => document.getElementById(id);
const getLS = key => JSON.parse(localStorage.getItem(key));
const setLS = (key, val) => localStorage.setItem(key, JSON.stringify(val));

document.addEventListener("DOMContentLoaded", () => {
  setupForm("regForm", registerUser);
  setupForm("loginForm", loginUser);
  loadProducts();
  renderCart();
});

// ---------- CART ----------
const getCart = () =>
  (getLS("cart") || []).map(item => {
    const p = products.find(x => x.id === item.id) || {};
    return { ...p, ...item, quantity: item.quantity || p.quantity || 1, cartQuantity: item.cartQuantity || 1 };
  });

const saveCart = cart => setLS("cart", cart);

// ---------- FORMS ----------
function setupForm(id, handler) {
  const form = $(id);
  if (!form) return;
  form.onsubmit = e => {
    e.preventDefault();
    handler();
  };
}

function registerUser() {
  const name = $("rName").value.trim();
  const user = $("rUser").value.trim();
  const pass = $("rPass").value.trim();
  const err = $("err-div");

  err.textContent = "";

  if (!name || !user || !pass)
    return (err.textContent = "All fields are required!");

  localStorage.setItem("name", name);
  localStorage.setItem("username", user);
  localStorage.setItem("password", pass);

  alert("Registration successful");
  location.href = "login.html";
}

function loginUser() {
  const user = $("lUser").value.trim();
  const pass = $("lPass").value.trim();
  const err = $("lErr");

  err.textContent = "";

  if (!user || !pass)
    return (err.textContent = "All fields are required!");

  if (user !== localStorage.getItem("username") || pass !== localStorage.getItem("password"))
    return (err.textContent = "Invalid Credentials!");

  alert("Logged in Successfully!");
  location.href = "catalog.html";
}

function loadProducts() {
  const div = $("products");
  if (!div) return;

  const cart = getCart();
  div.innerHTML = products.map(p => {
    const added = cart.some(c => c.id === p.id);
    return `
      <div class="col-md-4">
        <div class="card p-4 h-100 shadow-sm">
          <h3>${p.name}</h3>
          <p>${p.description}</p>
          <p class="fw-bold">Rs. ${p.price}</p>
          <p>Available: ${p.quantity}</p>
          <button class="btn ${added ? "btn-success" : "btn-primary"}"
            data-id="${p.id}" ${added ? "disabled" : ""}>
            ${added ? "Added to Cart" : "Add To Cart"}
          </button>
        </div>
      </div>`;
  }).join("");

  if (!div.dataset.listener) {
    div.onclick = e => {
      const btn = e.target.closest("[data-id]");
      if (btn) addToCart(+btn.dataset.id);
    };
    div.dataset.listener = true;
  }
}

function addToCart(id) {
  const cart = getCart();
  if (cart.some(i => i.id === id)) return;

  const product = products.find(p => p.id === id);
  cart.push({ ...product, cartQuantity: 1 });

  saveCart(cart);
  loadProducts();
}

function renderCart() {
  const div = $("cartDiv");
  if (!div) return;

  const cart = getCart();
  if (!cart.length)
    return (div.innerHTML = "<h3 class='text-center'>No items added!</h3>");

  div.innerHTML = cart.map((p, i) => `
    <div class="col-12">
      <div class="card shadow-sm border-0">
        <div class="card-body">
          <div class="row align-items-center g-3">
            <div class="col-md-5">
              <h4 class="card-title mb-2">${p.name}</h4>
              <span class="badge text-bg-primary rounded-pill">Rs. ${p.price}</span>
              <span class="badge text-bg-light border rounded-pill ms-2">Stock: ${p.quantity}</span>
            </div>

            <div class="col-md-3">
              <label class="form-label text-secondary small mb-1">Quantity</label>
              <div class="input-group">
                <button
                  class="btn btn-outline-secondary"
                  type="button"
                  onclick="changeCartQuantity(${i}, -1)"
                  ${p.cartQuantity === 1 ? "disabled" : ""}
                >
                  -
                </button>
                <span class="form-control text-center fw-bold">${p.cartQuantity}</span>
                <button
                  class="btn btn-outline-secondary"
                  type="button"
                  onclick="changeCartQuantity(${i}, 1)"
                  ${p.cartQuantity === p.quantity ? "disabled" : ""}
                >
                  +
                </button>
              </div>
            </div>

            <div class="col-md-2">
              <p class="text-secondary small mb-1">Total</p>
              <p class="fs-5 fw-bold text-success mb-0">Rs. ${p.price * p.cartQuantity}</p>
            </div>

            <div class="col-md-2">
              <div class="d-grid gap-2">
                <button class="btn btn-success rounded-pill" onclick="checkoutItem(${i})">
                  Checkout
                </button>
                <button class="btn btn-outline-danger rounded-pill" onclick="removeFromCart(${i})">
                  Remove
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  `).join("");
}

function removeFromCart(i) {
  const cart = getCart();
  cart.splice(i, 1);
  saveCart(cart);
  renderCart();
}

function changeCartQuantity(i, change) {
  const cart = getCart();
  const p = cart[i];
  if (!p) return;

  p.cartQuantity = Math.max(1, Math.min(p.quantity, p.cartQuantity + change));
  saveCart(cart);
  renderCart();
}

function checkoutItem(i) {
  const cart = getCart();
  const p = cart[i];
  if (!p) return;

  alert(`Checkout successful for ${p.cartQuantity} x ${p.name}`);
  removeFromCart(i);
}
