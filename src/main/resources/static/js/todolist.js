const modal = document.querySelector('.modal')
const modalBtn = document.querySelector('.modal-btn')
let isOpen = false;
modalBtn.addEventListener("click", () => {
    isOpen = !isOpen;
    isOpen ?  modal.style.display = 'block' :  modal.style.display = 'none';
})


function modifyContext(cell) {
    const curText = cell.innerText;
    const tr = cell.parentNode;
    const input = document.createElement('input');
    const hiddenId = tr.querySelector('.hidden-id')
    const todoId = hiddenId.getAttribute('value');

    input.value = curText;
    input.className = 'hidden';
    cell.innerHTML = '';
    cell.appendChild(input);
    input.focus();

    // input 내용 갱신시 업데이트
    input.addEventListener('blur', () => {
        const newText = input.value;
        console.log(newText)
        if (curText != newText) {
            console.log("갱신",todoId);
            updateContext(newText, `/todolist/${todoId}/update`);
        }
        cell.removeChild(input);
        cell.innerText = newText;
    });
}

function updateContext(text, action){
    let form = document.createElement('form');
    let input = document.createElement('input');
    form.setAttribute('method', 'post');
    form.setAttribute('action', action);
    input.setAttribute('type','hidden');
    input.setAttribute('id', "updateContext")
    input.setAttribute('name', "updateContext")
    input.setAttribute('value', text)
    input.setAttribute('class','contextInput')
    form.appendChild(input);

    document.body.appendChild(form);
    form.submit()
}

function changeStatus(cell) {
    const tr = cell.parentNode;
    const hiddenId = tr.querySelector('.hidden-id')
    const todoId = hiddenId.getAttribute('value');
    console.log(todoId)
    let statusValue = cell.querySelector('.hidden-status').getAttribute('value');
    let targetValue = (parseInt(statusValue) + 1 > 2) ? 0 : parseInt(statusValue) + 1;
    let form = document.createElement('form');
    const action = `/todolist/${todoId}/statuschange/${targetValue}`
    form.setAttribute('method', 'post');
    form.setAttribute('action', action);

    document.body.appendChild(form);
    form.submit()
}
function updateIcon() {
    const statusArr = document.querySelectorAll('#statusIcon')
    for (let i = 0; i < statusArr.length; i++)
    {
        let statusIcon = statusArr.item(i)
        let td = statusIcon.parentNode;
        let statusValue = td.querySelector('.hidden-status').getAttribute('value');
        let statusText = td.querySelector('#statusText');
        if(statusValue == '0')
        {
            statusIcon.style = "color: gray; font-size:40px;"
            statusIcon.textContent = "not_started";
            statusText.textContent = "Not started";
        }
        else if(statusValue == '1')
        {
            statusIcon.style = "color: orange; font-size:40px;"
            statusIcon.textContent = "update";
            statusText.textContent = "In progress";
        }
        else if(statusValue == '2')
        {
            statusIcon.style = "color: green; font-size:40px;"
            statusIcon.textContent = "check_circle";
            statusText.textContent = "Done";
        }


    }



}
