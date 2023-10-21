const title = document.getElementById("title")
const btn = document.getElementById("btn")

title.addEventListener("input", (event) => {
    if ( title.value.length <= 50) {
        title.classList.remove("danger");
        btn.removeAttribute('disabled')
    } else {
        title.classList.add("danger");
        btn.setAttribute('disabled', "disabled")
    }
})