const userForm = document.querySelector("#login-username-form");
<<<<<<< HEAD

const username = document.querySelector("#login-user-name").value;
const email = document.querySelector("#login-email").value;
const url = `https://6e43teedbd.execute-api.us-west-2.amazonaws.com/Teststage/user/${username}`

const userObj = {
  "email": email
};
=======
const username = document.querySelector("#login-user-name").value;
// exports.username = username;
const email = document.querySelector("#login-email").value;
const userObj = {
  "email": email
}
>>>>>>> main


userForm.onsubmit = async function(evt) {
  evt.preventDefault();
  console.log("Submitting User Data...");
<<<<<<< HEAD

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
=======
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

>>>>>>> main
}

window.onload = async function(evt) {

}

function persistUserdata(userData) {

<<<<<<< HEAD
  sessionStorage.setItem('username', userData.username);
  sessionStorage.setItem('email', userData.email);
  
}

=======
    exports.name =userData.username;
  
}


>>>>>>> main
