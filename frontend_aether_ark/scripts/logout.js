window.onload = () =>{
    const logout = document.getElementById("");
    logout.addEventListener('click', );
}

const logout = async (evt) => {
    evt.preventDefault();
    console.log("Logging out...");
    sessionStorage.clear("username");
    sessionStorage.clear("email");
    console.log(sessionStorage);
}