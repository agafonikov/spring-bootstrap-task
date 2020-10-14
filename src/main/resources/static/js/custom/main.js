

window.onload = async function (){
    drawTable();
    let addButton = document.getElementById("add-button");
}

function changeToEdit(){
    let btn = document.getElementsByClassName("submit")[0];
    btn.classList.remove("btn-danger");
    btn.classList.add("btn-primary");
    btn.value = "Edit";

    let form = document.getElementsByClassName("edit-form")[0];
    form.action = "/admin/update";

    form.getElementsByTagName("select")[0].removeAttribute("readonly");
    let els = Array.from(form.children[0].getElementsByTagName("input"));
    els.forEach(el => el.removeAttribute("readonly"));
    document.getElementById("id").setAttribute("readonly", "");
}

function changeToDelete(){
    let btn = document.getElementsByClassName("submit")[0];
    btn.classList.remove("btn-primary");
    btn.classList.add("btn-danger");
    btn.value = "Delete";

    let form = document.getElementsByClassName("edit-form")[0];
    form.action = "/admin/delete";

    form.getElementsByTagName("select")[0].setAttribute("readonly", "")
    let els = Array.from(form.children[0].getElementsByTagName("input"));
    els.forEach(el => el.setAttribute("readonly", ""));
    document.getElementById("id").setAttribute("readonly", "");
}

function fillForm(e){
    let target = e.target;
    e.preventDefault();
    let form = document.getElementsByClassName("edit-form")[0];
    let els = Array.from(form.children[0].getElementsByTagName("input"));
    let tt = Array.from(target.parentElement.parentElement.children);
    let roles = Array.from(tt[4].getElementsByClassName("role"));
    let info = {
        id:         tt[0].innerHTML,
        username:   tt[1].innerHTML,
        name:       tt[2].innerHTML,
        lastName:   tt[3].innerHTML,
        roles:      roles.map((value, index) => value.innerHtml)
    };
    els[0].value = info.id;
    els[1].value = info.username;
    els[3].value = info.name;
    els[4].value = info.lastName;

    let options = Array.from(form.getElementsByTagName("option"));
    options.forEach((value, index) => {
        if (info.roles.includes(value.innerHTML)) value.setAttribute("selected", "");
    });
}

function getFormData(form){
    let formData = new FormData(form);
    formData.append("id", form.elements["id"].value);
    return formData;
}

function closeModal() {

    // get modal
    const modal = document.getElementById("editModal");

    // change state like in hidden modal
    modal.classList.remove('show');
    modal.setAttribute('aria-hidden', 'true');
    modal.setAttribute('style', 'display: none');

    // get modal backdrop
    const modalBackdrops = document.getElementsByClassName('modal-backdrop');

    // remove opened modal backdrop
    document.body.removeChild(modalBackdrops[0]);
}

function drawTable() {
    let delButton = document.getElementsByClassName("delete-btn");
    let editButton = document.getElementsByClassName("edit-btn");
    Array.from(delButton).forEach(el=> el.addEventListener("click", function (e){
        fillForm(e);
        changeToDelete();
    }, false));
    Array.from(editButton).forEach(el=> el.addEventListener("click", function (e){
        fillForm(e);
        changeToEdit();
    }, false));
}

