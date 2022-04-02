const userForm = document.querySelector("#login-username-form");

const username = document.querySelector("#login-user-name").value;
const email = document.querySelector("#login-email").value;
const url = `https://6e43teedbd.execute-api.us-west-2.amazonaws.com/Teststage/user/${username}`

const userObj = {
  "email": email
};


userForm.onsubmit = async function(evt) {
  evt.preventDefault();
  console.log("Submitting User Data...");

  axios.get(url, userObj)
    .then((res) => {
    console.log(res.data);
    //persist the data to a session like variable
    persistUserdata(res.data.user);
    window.location.replace("/user/user-home.html");

  }).catch(function (error) {
    // handle error
    console.log(error);
    alert(error + "\nSomething went wrong!");
  })
  sessionStorage.setItem('username', username);
  sessionStorage.setItem('email', email);
  window.location.replace("/user/user-home.html");
}

window.onload = async function(evt) {

}

function persistUserdata(userData) {

  sessionStorage.setItem('username', userData.username);
  sessionStorage.setItem('email', userData.email);
  
}

