const ln = document.getElementById("len")
const comment = document.getElementById("comment")
const btn = document.getElementById("btn")

comment.addEventListener("input", (event) => {
    if (comment.value.length >= 50) {
        ln.classList.add("len__danger");
        comment.classList.add("danger")
        btn.setAttribute('disabled', "disabled")
    } else {
        ln.classList.remove("len__danger");
        comment.classList.remove("danger")
        btn.removeAttribute('disabled')
    }
    ln.textContent = String(50 - Number(comment.value.length));
})