const userForm = document.querySelector("#login-card");


userForm.onsubmit = async function(evt) {
  evt.preventDefault();
  console.log("Submitting User Data...");

  const username = document.getElementById("#login-username").value;
  const email = document.getElementById("#login-email").value;
  const userObj = {
    "username": username,
    "email": email
  };
  const url = `https://6e43teedbd.execute-api.us-west-2.amazonaws.com/Teststage/login/`
  
  axios.post(url, userObj)
    .then((res) => {
    console.log("response", res.data);
    //persist the data to a session like variable
    persistUserdata(res.data);
    // window.location.replace("/user/user-home.html");
  }).catch(function (error) {
    // handle error
    console.log("error",error);
  })
  // localStorage.setItem('username', username);
  // localStorage.setItem('email', email);
  // window.location.replace("/user/user-home.html");
}

window.onload = async function(evt) {
  // alert(error + "\nSomething went wrong!");
}

function persistUserdata(userData) {

  localStorage.setItem('username', userData.username);
  localStorage.setItem('email', userData.email);
  
}

