const solarSystemTable = document.querySelector("#solar-system-table");
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
            const firstSolarSystemId = userSolarSystemIdsList[0];
            let userCelestialBodyIdsList = res.data.user.celestialBodyIds;
            const systemUrl = `https://6e43teedbd.execute-api.us-west-2.amazonaws.com/Teststage/user/${username}/solarSystem/${firstSolarSystemId}`;

            if (userSolarSystemIdsList.length > 0) {
                axios.get(systemUrl, { params: { getAll: true } })
                    .then((res) => {
                        console.log("response", res.data);
                        populateSolarSystems(res.data);

                    })


            } else {
                console.log('This user has no solar systems');
            }

            // populateSolarSystems(userSolarSystemIdsList);


        })

    //get solar system






    function populateSolarSystems(solarSystemData) {


        let thead = solarSystemTable.createTHead();
        let tbody = solarSystemTable.createTBody();
        let row = thead.insertRow();

        // for (let header in solarSystemData) {
        //     let th = document.createElement("th");
        //     let text = document.createTextNode(solarSystemData.solarSystemModels[0].systemId);
        //     th.appendChild(text);
        //     row.appendChild(th);
        // }


        let th = document.createElement("th");
        let text = document.createTextNode('Solar System Ids');
        th.appendChild(text);
        row.appendChild(th);

        let th1 = document.createElement("th");
        let text1 = document.createTextNode('System Name');
        th1.appendChild(text1);
        row.appendChild(th1);

        let th2 = document.createElement("th");
        let text2 = document.createTextNode('Celestial Bodies');
        th2.appendChild(text2);
        row.appendChild(th2);

        let th3 = document.createElement("th");
        let text3 = document.createTextNode('Distance From Center');
        th3.appendChild(text3);
        row.appendChild(th3);

        let th4 = document.createElement("th");
        let text4 = document.createTextNode('Username');
        th4.appendChild(text4);
        row.appendChild(th4);



        // for (let key in solarSystemData) {
        //     let th = document.createElement("th");
        //     let text = document.createTextNode('User Solar System Ids');
        //     th.appendChild(text);
        //     row.appendChild(th);
        // }

        // for (let solarSystem of solarSystemData) {
        //     let row = tbody.insertRow();
        //     //for (key in solarSystem) 
        //     let cell = row.insertCell();
        //     let text = document.createTextNode(solarSystem);
        //     cell.appendChild(text);

        // }
    }
}

