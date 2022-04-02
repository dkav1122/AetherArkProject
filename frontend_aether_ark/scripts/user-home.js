// const userForm = document.querySelector("#login-username-form");
const solarSystemList = document.querySelector("#user-solar-list");
const celestialBodyList = document.querySelector("#user-body-list");
const username = sessionStorage.getItem('username');
const email = sessionStorage.getItem('email');
const url = `https://6e43teedbd.execute-api.us-west-2.amazonaws.com/Teststage/user/${username}`

const userObj = {
  "email": email
}

userForm.onsubmit = async function(evt) {

}

window.onload = async function(evt) {
  
  evt.preventDefault();
  
  console.log("Getting User Data...");
  axios.get(`https://6e43teedbd.execute-api.us-west-2.amazonaws.com/Teststage/user/${username}`, userObj


  ).then((res) => {
    console.log(res.data);
    populateSolarSystems(res.data.user.solarSystemIds);
    populateCelestialBodies(res.data)
  }).catch(function (error) {
    // handle error
    console.log(error);
    alert(error + "\nSomething went wrong!");
  })
}

function populateSolarSystems(solarSystemData) {

  for (let solarSystem of solarSystemData) {
    let li = document.createElement("li");
    let a = document.createElement("a");
    let text = document.createTextNode(solarSystem);
    //we want to have a link populate our form instead
    a.setAttribute('href', ``);

    a.appendChild(text);
    li.appendChild(a);
    solarSystemList.appendChild(li);
  }
}

function populateCelestialBodies(celestialBodyData) {

  for (let celestialBody of celestialBodyData) {
    let li = document.createElement("li");
    let a = document.createElement("a");
    let text = document.createTextNode(celestialBody);
    //we want to have a link populate our form instead
    a.setAttribute('href', ``);

    a.appendChild(text);
    li.appendChild(a);
    celestialBodyList.appendChild(li);
  }
}