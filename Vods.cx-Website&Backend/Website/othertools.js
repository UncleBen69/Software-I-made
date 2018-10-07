window.addEventListener('load', function () {
    M.AutoInit();
})
$.get("https://api-production.iceposeidon.com/streamers/full", function (data) {
    var i;
    for(i = 0; i < data.streamers.length; i++){
        temp1 = data.streamers[i].id;
        id = temp1.toString();
        //Removes Non ascii chars like emoji
        name = data.streamers[i].name.replace(/[^\x00-\x7F]/g, "");
        $(".sidebarshit").append(`<li><a href="vodlist.html?name=${id}&page=1">${name}</a></li>`);
    }
});