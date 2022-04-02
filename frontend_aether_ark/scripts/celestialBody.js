const solarSystemTable = document.querySelector("#solar-system-table");
const urlParams = new URLSearchParams(window.location.search);
//const username = urlParams.get('username');
const username = 'Dixon';
const email = 'email';

window.onload = async function (evt) {
    // evt.preventDefault();
    // console.log("Getting All Solar Systems...");
    // // const username = document.querySelector("#user-name").value;
    // // const email = document.querySelector("#password").value;
    // const userObj = {
    //     "username": username,
    //     "email": email
    // }
    // axios.get("https://6e43teedbd.execute-api.us-west-2.amazonaws.com/Teststage/user/" + username + "", {
    //     authorization: {
    //         'x-api-key': '8l6cwka4wc'
    //     }
    // }).then((res) => {
    //     console.log(res.data);
    //     populateSolarSystems();
    // })
    populateSolarSystems();





}

function populateSolarSystems() {
    let thead = solarSystemTable.createTHead();
    let tbody = solarSystemTable.createTBody();
    let row = thead.insertRow();

    let th = document.createElement("th");
    let text = document.createTextNode("Solar System Id")
    th.appendChild(text);
    row.appendChild(th);


    let th1 = document.createElement("th");
    let text1 = document.createTextNode("Celestial Bodies")
    th1.appendChild(text1);
    row.appendChild(th1);

    let th2 = document.createElement("th");
    let text2 = document.createTextNode("Distances From Center")
    th2.appendChild(text2);
    row.appendChild(th2);

    let th3 = document.createElement("th");
    let text3 = document.createTextNode("System Name")
    th3.appendChild(text3);
    row.appendChild(th3);

    for (i = 0; i < 4; i++) {
        let row = tbody.insertRow();
        for (k = 0; k < 4; k++) {
            let cell = row.insertCell();
            let text = document.createTextNode('');
            cell.appendChild(text);

        }
    }



    // for (let key in solarSystemData[0]) {
    //     let th = document.createElement("th");
    //     let text = document.createTextNode(key);
    //     th.appendChild(text);
    //     row.appendChild(th);
    // }

    // for (let solarSystem of solarSystemData) {
    //     let row = tbody.insertRow();
    //     for (key in solarSystem) {
    //         let cell = row.insertCell();
    //         let text = document.createTextNode(solarSystem[key]);
    //         cell.appendChild(text);
    //     }
    // }
}

