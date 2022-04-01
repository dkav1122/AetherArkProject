const userForm = document.querySelector("#login-username-form");
const username = document.querySelector("#login-user-name").value;
// exports.username = username;
const email = document.querySelector("#login-email").value;
const userObj = {
  "email": email
}


userForm.onsubmit = async function(evt) {
  evt.preventDefault();
  console.log("Submitting User Data...");
  axios.get(`https://6e43teedbd.execute-api.us-west-2.amazonaws.com/Teststage/user/${username}`, userObj
  , 
  {
    authorization: {
      'x-api-key': 'iSw09XZBjq3H6JowPSXa08qph5Kwdw7F87Ry5iwy'
    }
  }
  )
    .then((res) => {

    console.log(res.data);
    persistUserdata(res.data.user)
    window.location.replace("/user/user-home.html");
  }).catch(function (error) {
    // handle error
    console.log(error);
    alert(error + "\nSomething went wrong!")
  })
  window.location.replace("/user/user-home.html");

}

window.onload = async function(evt) {

}

function persistUserdata(userData) {

    exports.name =userData.username;
  
}

