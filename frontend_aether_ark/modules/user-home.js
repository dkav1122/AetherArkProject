import {persistUserdata, InvalidAttributeValueException, UserNotFoundException }
 from "/modules/utils.js";
const solarSystemList = document.querySelector("#user-solar-list");
const celestialBodyList = document.querySelector("#user-body-list");

var c = document.getElementById("myCanvas");
var ctx = c.getContext("2d");
// var img = "/images/png-clipart-meteor.png";
// ctx.drawImage(img, 10, 10);
ctx.strokeRect(10,10, 180, 180);


window.onload = (evt) => {
  console.log(sessionStorage);
  // const username = sessionStorage.getItem("username");
  // const email = sessionStorage.getItem("email");
  const username = "Dixon";
  const email = "email";
  
  evt.preventDefault();
  console.log("Getting User Data...");

const userUrl = `https://6e43teedbd.execute-api.us-west-2.amazonaws.com/Teststage/user/${username}`
axios.get(userUrl, {params:{
    email : email
}})
  .then((res) => {
  console.log("response", res.data);
  populateCelestialBodies(res.data.user.celestialBodyIds);
  populateSolarSystems(res.data.user.solarSystemIds);
  sessionStorage.setItem("userData", res.data);

}).catch(function (error) {
  // handle error
  console.log("error",error);
})
}

 function getAllSolars(){
//////////////get solars//////////////////
console.log("Getting all Solar Systems...");
const userdata = sessionStorage.getItem("userData");
const solarSystemId = userdata.solarSystemIds[0];
const solarUrl = `https://6e43teedbd.execute-api.us-west-2.amazonaws.com/Teststage/user/${username}/solarsystems/${solarSystemId}`

axios.get(solarUrl)
  .then((res) => {
  console.log("response", res.data);
  sessionStorage.setItem("solarSystemData", res.data);
  // populateSolarSystems(solarSystemsData.systemName);

}).catch(function (error) {
  // handle error
  console.log("error",error);
})
}


function populateSolarSystems(solarSystemData) {
  
  for (let solarSystem of solarSystemData) {
    let li = document.createElement("li");
    let a = document.createElement("a");
    let text = document.createTextNode(solarSystem);
    //we want to have a link populate our form instead
    a.setAttribute('href', `#`);

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
    a.setAttribute('href', `#`);

    a.appendChild(text);
    li.appendChild(a);
    celestialBodyList.appendChild(li);
  }
}

