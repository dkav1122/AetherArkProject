const userForm = document.querySelector("#create-username-form");

userForm.onsubmit = async function(evt) {
  evt.preventDefault();
  const username = document.querySelector("#create-username").value;
  const email = document.querySelector("#create-email").value;
  const userObj = {
    "email": email
  }
  axios.post(`https://6e43teedbd.execute-api.us-west-2.amazonaws.com/Teststage/user/${username}`, userObj ).then((res) => {
    console.log(res);
    window.location.reload();
  })
}

window.onload = async function(evt) {
  // evt.preventDefault();
  // console.log("Getting Playlist Data...");
  // axios.get("https://svebsuap66.execute-api.us-west-2.amazonaws.com/prod/playlists", {
  //   authorization: {
  //     'x-api-key': 'K7CHRL6aqt1C6eGJ9EHyFaZCn86G0fyI2sTZKSkW'
  //   }
  // }).then((res) => {
  //   console.log(res.data);
  //   populatePlaylists(res.data.playlists);
  // })
}

// function populatePlaylists(playlistData) {

//   for (let playlist of playlistData) {
//     let li = document.createElement("li");
//     let a = document.createElement("a");
//     let text = document.createTextNode(playlist.name);
//     //we want to have a link populate our form instead
//     a.setAttribute('href', `./playlist.html?id=${playlist.id}`);

//     a.appendChild(text);
//     li.appendChild(a);
//     playlistsList.appendChild(li);
//   }
// }

// function populateForm() {
//   //
// }