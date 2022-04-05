const planetTable = document.querySelector("#planet-table");
const urlParams = new URLSearchParams(window.location.search);
//const username = urlParams.get('username');



window.onload = async function (evt) {
    evt.preventDefault();
    console.log("Getting All Solar Systems...");
    // const username = document.querySelector("#user-name").value;
    // const email = document.querySelector("#password").value;
    const username = 'Dixon';
    const email = 'email';

    const userObj = {
        "email": email
    }

    const url = `https://6e43teedbd.execute-api.us-west-2.amazonaws.com/Teststage/user/${username}`;
    axios.get(url, {
        params: {
            email: email
        }
    })
        .then((res) => {
            console.log("response", res.data.user);
            let currentUserName = res.data.user.name;
            let currentUserEmail = res.data.user.email;
            let userSolarSystemIdsList = res.data.user.solarSystemIds;
            let userCelestialBodyIdsList = res.data.user.celestialBodyIds;
            const systemUrl = `https://6e43teedbd.execute-api.us-west-2.amazonaws.com/Teststage/user/${username}`;

            if (userSolarSystemIdsList.length > 0) {
                axios.get(systemUrl, { params: { getAll: true } })

                populateCelestialBodies(userCelestialBodyIdsList);
            } else {
                console.log('This user has no solar systems');
            }


        })

    //get solar system






    function populateCelestialBodies(solarSystemData) {
        let thead = planetTable.createTHead();
        let tbody = planetTable.createTBody();
        let row = thead.insertRow();


        //  for (let key in solarSystemData) {
        let th = document.createElement("th");
        let text = document.createTextNode('User Celestial Body Ids');
        th.appendChild(text);
        row.appendChild(th);
        // }

        for (let solarSystem of solarSystemData) {
            let row = tbody.insertRow();
            //for (key in solarSystem) 
            let cell = row.insertCell();
            let text = document.createTextNode(solarSystem);
            cell.appendChild(text);

        }
    }
}

