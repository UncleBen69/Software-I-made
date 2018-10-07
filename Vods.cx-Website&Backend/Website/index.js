window.addEventListener('load', function () {
    // Init's all materialize stuff.
    M.AutoInit();
    //Gets list of all the streamers on the Cx network from their api
    $.get("https://api-production.iceposeidon.com/streamers/full", function (shit) {
        var data = shit
        console.log(data.streamers.length + " Streamers")
        var i;
        //This for loop runs this code once for each streamer in the network
        for(i = 0; i < data.streamers.length; i++){
            temp1 = data.streamers[i].id;
            id = temp1.toString();
            //Removes Non ascii chars like emoji
            name = data.streamers[i].name.replace(/[^\x00-\x7F]/g, "");
            image = data.streamers[i].images.avatars.high.url
            // Favorite Section
            //Checks if the streamer is favorited.
            if(localStorage.getItem(id+"_fav") == "true"){
                inthere = 1;
                //Sets the live text
                if(data.streamers[i].liveData.live == true){
                    live = ` class="green-text">Live now`
                }
                else{
                    live = ` class="red-text">Offline now`
                }
                //Appends the card to the favs div in index.html
                $(".favs").append(`
                <div class="col s12 m4" id="${id}">
                <div class="card sticky-action darker">
                    <a href="vodlist.html?name=${id}&page=1"><div class="card-image waves-effect waves-block waves-light">
                        <img src="${image}">
                    </div></a>
                    <div class="card-content">
                        
                        <span class="card-title white-text">${name}
                        <a href="javascript:;" id="${id}" class="secondary-content favtoggle"><i class="material-icons">star</i></a>
                        </span>
                        <p${live}</p>
                    </div>
                    <div class="card-action">
                        <a href="vodlist.html?name=${id}&page=1">Vods</a>
                        <a href="http://youtube.com/channel/${data.streamers[i].socials.yt}">Youtube</a>
                    </div>
                </div>
                </div>
                `);
            }

            //Adds the streamer to the sidebar
            $(".sidebarshit").append(`<li><a href="vodlist.html?name=${id}&page=1">${name}</a></li>`);

            //Sets the live text
            if(data.streamers[i].liveData.live == true){
                live = ` class="green-text">Live now`
            }
            else{
                live = ` class="red-text">Offline now`
            }
            //Sets the star to correct state
            if(localStorage.getItem(id+"_fav") == "true"){
                checked = `<a href="javascript:;" id="${id}" class="secondary-content favtoggle"><i class="material-icons">star</i></a>`
            }
            else{
                checked = `<a href="javascript:;" id="${id}" class="secondary-content favtoggle"><i class="material-icons">star_border</i></a>`
            } 
            //Appends to the streamer list
            $("#List").append(`
                <a href="vodlist.html?name=${id}&page=1"><li class="collection-item avatar grey darken-4" id="${id}">
                    <img src="${image}" alt="${name}" class="circle">
                    <span class="title">${name}</span>
                    <p${live}</p>

                    <div class="${id}_star">
                    ${checked}
                    <div>
                </li></a>
                `);
            
        }
        /* if(inthere == 0){
            $(".favs").append(`
            <h6>No favorites added, toggle the switch next to the streamer to favorite them.</h6>
            <h6>Refresh page to apply changes.</h6>
                `);
        } */
    })
})
//This is just a ping to my backend saying that someone visited the website.
$.get("http://92.236.229.24:8080/ping", function (data) {
    //nothing
});

//Load Count
// This is local variable nothing is stored outside of the pc.
if(!localStorage.getItem("visits")){localStorage.setItem("visits", "1")}
oldnumber = parseInt(localStorage.visits)
newnumber = oldnumber + 1
localStorage.setItem("visits", newnumber)
console.log(newnumber+" Visits")
if(newnumber == 10){
    // I might add a popup that would tell people about other Cx Network tools and thanking them.
    console.log("Would popup")
}

//Button Listeners

//Favorite toggles
function toggleFunction(event){
    console.log("clicked")
    id = this.id
    enabled = localStorage.getItem(id+"_fav");
    if(enabled == "true"){
        console.log("Already enabled and is now disabled")
        localStorage.setItem(id+"_fav", false)
        remove(id)
        if ($(this).find('i').text() == 'star'){
            $(this).find('i').text('star_border');
        }
    }
    else{
        console.log("was disabled and now enabled")
        localStorage.setItem(id+"_fav", true)
        add(id)
        if ($(this).find('i').text() == 'star_border'){
            $(this).find('i').text('star');
        }
    }
}
  
$(document).on('click', '.favtoggle', toggleFunction);


function remove(id){
    $(`#${id}`).remove()
}
//Adds the card to favorites
function add(id1){
    //Another request to api.
    $.get("https://api-production.iceposeidon.com/streamers/full", function (shit) {
        var data = shit
        var i;
        for(i = 0; i < data.streamers.length; i++){
            if(data.streamers[i].id == id1){
                temp1 = data.streamers[i].id;
                id = temp1.toString();
                //Removes Non ascii chars like emoji
                name = data.streamers[i].name.replace(/[^\x00-\x7F]/g, "");
                image = data.streamers[i].images.avatars.high.url
                if(data.streamers[i].liveData.live == true){
                    live = ` class="green-text">Live now`
                }
                else{
                    live = ` class="red-text">Offline now`
                }
                $(".favs").append(`
                    <div class="col s12 m4" id="${id}">
                    <div class="card sticky-action darker">
                        <a href="vodlist.html?name=${id}&page=1"><div class="card-image waves-effect waves-block waves-light">
                            <img src="${image}">
                        </div></a>
                        <div class="card-content">
                            
                            <span class="card-title white-text">${name}
                            <a href="#!" id="${id}" class="secondary-content favtoggle"><i class="material-icons">star</i></a>
                            </span>
                            <p${live}</p>
                        </div>
                        <div class="card-action">
                            <a href="vodlist.html?name=${id}&page=1">Vods</a>
                            <a href="http://youtube.com/channel/${data.streamers[i].socials.yt}">Youtube</a>
                        </div>
                    </div>
                    </div>
                `);
            }
        }
    })
}