window.onload = () => {
    const loginBtn = document.getElementById("login");
    loginBtn.addEventListener('click', login);
    const getBtn = document.getElementById("GET");
    getBtn.addEventListener('click', get );
    const postBtn = document.getElementById("POST");
    postBtn.addEventListener('click', post );
    const putBtn = document.getElementById("PUT");
    postBtn.addEventListener('click', put);
}
//login
const login = async (evt) => {
  evt.preventDefault();
  console.log("Submitting User Data...");

  const username = document.getElementById("login-username").value;
  const email = document.getElementById("login-email").value;
  const userObj = {
    "username": username,
    "email": email
  };
  const url = `https://6e43teedbd.execute-api.us-west-2.amazonaws.com/Teststage/login/`
  populateRequest(username);
  populateRequest(email);
  
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
}

//get user
const get = async (evt) => {
    evt.preventDefault();
  console.log("Submitting User Data...");

  const username = document.getElementById("login-username").value;
  const email = document.getElementById("login-email").value;
  const userObj = {
    "email": email
  };
  const url = `https://6e43teedbd.execute-api.us-west-2.amazonaws.com/Teststage/user/${username}`
  populateRequest(username);
  populateRequest(email);

  axios.get(url, userObj)
    .then((res) => {
    console.log("response", res.data);
    //persist the data to a session like variable
    persistUserdata(res.data);
    // window.location.replace("/user/user-home.html");
  }).catch(function (error) {
    // handle error
    console.log("error",error);
  })
}

//new user
const post = async (evt) => {
    evt.preventDefault();
  console.log("Submitting User Data...");

  const username = document.getElementById("login-username").value;
  const email = document.getElementById("login-email").value;
  const userObj = {
    "username": username,
    "email": email
  };
  const url = `https://6e43teedbd.execute-api.us-west-2.amazonaws.com/Teststage/user`
  populateRequest(username);
  populateRequest(email);

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
}

const put = async (evt) => {
    evt.preventDefault();
    console.log("Submitting User Data...");
  
    const username = document.getElementById("login-username").value;
    const email = document.getElementById("login-email").value;
    const updatedEmail = document.getElementById("login-updateEmail").value;
    const userObj = {
      "email": email,
      "updatedEmail" : updatedEmail
    };
    const url = `https://6e43teedbd.execute-api.us-west-2.amazonaws.com/Teststage/user/${username}`
    populateRequest(username);
    populateRequest(email);
  
    axios.get(url, userObj)
      .then((res) => {
      console.log("response", res.data);
      //persist the data to a session like variable
      persistUserdata(res.data);
      // window.location.replace("/user/user-home.html");
    }).catch(function (error) {
      // handle error
      console.log("error",error);
    })
}

const destroy = async (evt) => {
    evt.preventDefault();
    console.log("Submitting User Data...");
  
    const username = document.getElementById("login-username").value;
    const email = document.getElementById("login-email").value;
    const userObj = {
      "email": email
    };
    const url = `https://6e43teedbd.execute-api.us-west-2.amazonaws.com/Teststage/user/${username}`
    populateRequest(username);
    populateRequest(email);
  
    axios.delete(url, userObj)
      .then((res) => {
      console.log("response", res.data);
      //persist the data to a session like variable
      persistUserdata(res.data);
      // window.location.replace("/user/user-home.html");
    }).catch(function (error) {
      // handle error
      console.log("error",error);
    })
}

function persistUserdata(userData) {

  localStorage.setItem('username', userData.username);
  localStorage.setItem('email', userData.email);
  
}

function populateRequest(data) {
    
    let ul = document.getElementById("request-data");    
      let li = document.createElement("li");
      let p = document.createElement("p");
      let text = document.createTextNode(data);

      p.appendChild(text);
      li.appendChild(p);
      ul.appendChild(li);
    
  }

  
function populateResponse(data) {
    
    let ul = document.getElementById("response-data");    
      let li = document.createElement("li");
      let p = document.createElement("p");
      let text = document.createTextNode(data);

      p.appendChild(text);
      li.appendChild(p);
      ul.appendChild(li);
    
  }
