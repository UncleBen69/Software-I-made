window.addEventListener('load', function () {
    //Inits shit
    M.AutoInit();

    /* $('.backbtn').click(function () {
        console.log("Exit Test")
    }); */

    var url = new URL(window.location.href);
    var id = url.searchParams.get("name");
    var page = url.searchParams.get("page");
    console.log(id);
    if(page == 0){
        page = 1;
    }
    page = page++
    console.log(page)
    //Requests the json from my backend server
    $.get(`http://92.236.229.24:8080/vods/?id=${id}&page=${page}`, function (data) {
        console.log(data.vods.length)
        // If their is no data for that streamer
        if(data.vods.length === 0){
            if(page == 1){
                $(".shit").prepend(`
                    <div class="col s12 m4">
                        <div class="card hoverable darker">
                        <div class="card-image">
                            <img src="desert.jpeg">
                        </div>
                        <div class="card-content">
                            <h6 class="bold white-text">Streamer has not streamed since 21/08/18</h6>
                            <p class="white-text">Nothing here yet</p>
                        </div>
                        </div>
                    </div>
                `);
            }else{
                $(".shit").prepend(`
                    <div class="col s12 m4">
                        <div class="card hoverable darker">
                        <div class="card-image">
                            <img src="desert.jpeg">
                        </div>
                        <div class="card-content">
                            <h6 class="bold white-text">Nothing here yet</h6>
                            <p class="white-text">Nothing here yet</p>
                        </div>
                        </div>
                    </div>
                `);
            }
        }
        var i;
        for(i = 0; i < data.vods.length; i++){
            console.log("ran")
            title = data.vods[i].title
            videoId = data.vods[i].videoId
            date = data.vods[i].date
            time = data.vods[i].time
            //console.log(`title is ${title}. videoId is ${videoId}`)
            console.log(id)
            //This was for the experimental events page
            if(id == "ice_poseidon_events"){
                $(".shit").append(`
                <div class="col s12 m4">
                    <div class="card large hoverable darker">
                        <a href="https://youtube.com/watch?v=${videoId}"><div class="card-image waves-effect waves-block waves-light">
                            <img src="http://img.youtube.com/vi/${videoId}/0.jpg">
                        </div></a>
                        <div class="card-content">
                            <h6 class="truncate white-text">${title}</h6>
                            <p class="white-text">${time} ${date}</p>
                        </div>
                        <div class="card-action">
                            <a href="https://youtube.com/watch?v=${videoId}" target="_blank">Youtube Video</a>
                        </div>
                    </div>
                </div>
                `)
            }else{
                $(".shit").prepend(`
                <div class="col s12 m4">
                    <div class="card large hoverable darker">
                        <a href="https://youtube.com/watch?v=${videoId}"><div class="card-image waves-effect waves-block waves-light">
                            <img src="http://img.youtube.com/vi/${videoId}/0.jpg">
                        </div></a>
                        <div class="card-content">
                            <h6 class="truncate white-text">${title}</h6>
                            <p class="white-text">${time} ${date}</p>
                        </div>
                        <div class="card-action">
                            <a href="https://youtube.com/watch?v=${videoId}" target="_blank">Youtube Video</a>
                        </div>
                    </div>
                </div>
                `)}
        }
    })
    //This is for the sidebar
    $.get("https://api-production.iceposeidon.com/streamers/full", function (data) {
        var i;
        for(i = 0; i < data.streamers.length; i++){
            listtemp1 = data.streamers[i].id;
            listid = listtemp1.toString();
            listname = data.streamers[i].name.replace(/[^\x00-\x7F]/g, "");
            console.log("in here")
            if(listid == id){
                $(".sidebarshit").append(`<li><a class="sidenav-close">${listname}</a></li>`);
                $(".breadcrumbShit").append(`<a class="breadcrumb">${listname}</a>`);
            }else{
                $(".sidebarshit").append(`<li><a href="vodlist.html?name=${listid}&page=1">${listname}</a></li>`);
            }
        }
    })
    //Pagenation shit at the bottom
    var i
    for(i = 1; i<=9; i++){
        if(i == page){
            $(".pageshit").append(`<li class="active cxpurple"><a>${page}</a></li>`);
        }else{
            $(".pageshit").append(`<li class="waves-effect"><a href="vodlist.html?name=${id}&page=${i}">${i}</a></li>`);
        }
    }
    //Go back
    $(".pageshit").prepend(`<li class="waves-effect"><a href="vodlist.html?name=${id}&page=${page - 1}"><i class="material-icons">chevron_left</i></a></li>`);
    //Go forward
    $(".pageshit").append(`<li class="waves-effect"><a href="vodlist.html?name=${id}&page=${page + 1}"><i class="material-icons">chevron_right</i></a></li>`);
});