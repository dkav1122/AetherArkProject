const userForm = document.querySelector("#fff");
const solarSystemList = document.querySelector("#user-solar-list");
const celestialBodyList = document.querySelector("#user-body-list");

userForm.onsubmit = async function(evt) {
}

window.onload = async function(evt) {
  evt.preventDefault();
  
  username = localStorage.getItem('username');
  email = localStorage.getItem('email');
  const url = `https://6e43teedbd.execute-api.us-west-2.amazonaws.com/Teststage/user/`
  const userObj = {
    "email": email
  }
  console.log("Getting User Data...");
  axios.get(`${url}${username}`, userObj

  ).then((res) => {
    console.log(res.data);
    populateSolarSystems(res.data.user.solarSystemIds);
    populateCelestialBodies(res.data)
  }).catch(function (error) {
    // handle error
    console.log(error);
    // alert(error + "\nSomething went wrong!");
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