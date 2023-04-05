const modal = document.querySelector('.modal')
const modalBtn = document.querySelector('.modal-btn')
let isOpen = false;
modalBtn.addEventListener("click", () => {
    isOpen = !isOpen;
    isOpen ?  modal.style.display = 'block' :  modal.style.display = 'none';
})