const email = document.getElementById("email")
const username = document.getElementById("username")
const password = document.getElementById("password")
const password2 = document.getElementById("password2")
const btn = document.getElementById("btn")
email.addEventListener("input", (event) => {
    const regex = /([a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\.[a-zA-Z0-9_-]+)/
    if (regex.test(email.value) && email.value.length <= 100) {
        email.classList.remove("danger");
        btn.removeAttribute('disabled')
    } else {
        email.classList.add("danger");
        btn.setAttribute('disabled', "disabled")
    }
})


password.addEventListener("input", (event) => {
    const regex = /^(?=.*[a-zA-Z])(?=.*\d).{5,}$/
    if (regex.test(password.value) && password.value.length <= 100) {
        password.classList.remove("danger");
        btn.removeAttribute('disabled')
    } else {
        password.classList.add("danger");
        btn.setAttribute('disabled', "disabled")
    }
})

username.addEventListener("input", (event) => {
    if ( username.value.length <= 50) {
        username.classList.remove("danger");
        btn.removeAttribute('disabled')
    } else {
        username.classList.add("danger");
        btn.setAttribute('disabled', "disabled")
    }
})

password2.addEventListener("input", (event) => {
    if (password2.value !== password.value) {
        password2.classList.add("danger");
        btn.setAttribute('disabled', "disabled")
    } else {
        password2.classList.remove("danger");
        btn.removeAttribute('disabled')
    }
})

