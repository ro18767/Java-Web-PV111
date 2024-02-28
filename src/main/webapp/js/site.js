document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('.modal');
    M.Modal.init(elems, {
        onCloseEnd: onAuthModalClosed
    });

    const authButton = document.getElementById("auth-button");
    if(authButton) authButton.addEventListener('click', authButtonClick);
});
function getAuthElements() {
    const authEmailInput = document.getElementById("auth-email");
    if( ! authEmailInput) throw "Element '#auth-email' not found" ;
    const authPasswordInput = document.getElementById("auth-password");
    if( ! authPasswordInput) throw "Element '#auth-password' not found" ;
    const authMessageDiv = document.querySelector(".auth-message");
    if( ! authMessageDiv) throw "Element '.auth-message' not found" ;
    return [authEmailInput, authPasswordInput, authMessageDiv];
}
function authButtonClick() {
    const [authEmailInput, authPasswordInput, authMessageDiv] = getAuthElements();
    const email = authEmailInput.value;
    if(email === "") {
        authMessageDiv.innerText = "Необхідно заповнити поле 'Email'";
        return;
    }
    const password = authPasswordInput.value;
    if(password === "") {
        authMessageDiv.innerText = "Необхідно заповнити поле 'Password'";
        return;
    }
    const appContext = window.location.pathname.split('/')[1] ;
    fetch(`/${appContext}/auth?email=${email}&password=${password}`)
        .then(r => r.json())
        .then(j => {
            if( j.status !== "success" ) {
                authMessageDiv.innerText = "Автентифікацію відхилено";
            }
            else {
                // переходимо в авторизований режим

                // перезавантажуємо сторінку
                window.location.reload();
            }
        }) ;
}

function onAuthModalClosed() {
    const [authEmailInput, authPasswordInput, authMessageDiv] = getAuthElements();
    authEmailInput.value = "";
    authPasswordInput.value = "";
    authMessageDiv.innerText = "";
}