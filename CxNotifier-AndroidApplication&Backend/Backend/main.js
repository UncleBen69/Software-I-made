//Requirements
if (typeof localStorage === "undefined" || localStorage === null) {
    var LocalStorage = require('node-localstorage').LocalStorage;
    localStorage = new LocalStorage('./cache');
}
const Sentry = require('@sentry/node');
var http = require("http");
var admin = require("firebase-admin");
//File removed for security reasons.
var serviceAccount = "cxnotifiermobile-firebase-adminsdk-0vl3a-ea5c7309b6.json";

//Inits sentry
//Token removed
Sentry.init({ dsn: '' });

admin.initializeApp({
    credential: admin.credential.cert(serviceAccount),
    databaseURL: ""
  });
console.log("Running")



//Dynamic Variables
function checkShit(data){
    var i;
    //console.log(data.streamers.length)
    for(i = 0; i < data.streamers.length; i++){
        temp1 = data.streamers[i].id;
        id = temp1.toString();
        //console.log("In "+id)
        // Checks/ Adds live LocalStorage item
        if (!localStorage.getItem(id+"_live")){
            console.log("added new person")
            localStorage.setItem(id+"_live", false);
        }
    }
}
function checkLive(data){
    if(data.success == true){
        var i;
        for(i = 0; i < data.streamers.length; i++){
            //In the for function
            temp1 = data.streamers[i].id;
            id = temp1.toString();
            name = data.streamers[i].name;
            if(data.streamers[i].liveData.live === false){
                //Sets the variable
                if(localStorage.getItem(id+"_live") === "true"){
                    localStorage.setItem(id+"_live", false);
                }
            }
            if(data.streamers[i].liveData.live === true){
                if(localStorage.getItem(id+"_live") === "false"){
                    //In execute function
                    title = data.streamers[i].liveData.title;
                    videoId = data.streamers[i].liveData.videoId;

                    notificationSend(name, id, title, videoId);
                    
                    localStorage.setItem(id+"_live", true);
                }
            }
        }
    }
}

function notificationSend(name, id, title, videoId){
    console.log(id+" Went online")
    
    var message = {
        android: {
          //ttl: 3600 * 1000, // 1 hour in milliseconds
          ttl: 60 * 60 *24, // 24hours
          priority: "high",
          notification: {
            title: `${name} is live`,
            body: `Title: ${title}`,
            //icon: "",
            color: "#7F00FF",
            sound: "online.mp3"
          }
        },
        topic: id
    };
    
    admin.messaging().send(message)
    .then(function(response) {
        console.log("Successfully sent message:", response);
    })
    .catch(function(error) {
        console.log("Error sending message:", error);
    });
}

//Repeat every so so seconds
const INTERVAL = 1000 * 5
intimer()
setInterval(function () {
    intimer();
}, INTERVAL);

function intimer(){
    var url = "http://api-production.iceposeidon.com/streamers/full";
    http.get(url, function(res){
        var body = '';

        res.on('data', function(chunk){
            body += chunk;
        });

        res.on('end', function(){
            if(body.charAt(0) === "<"){
                console.log("invalid webpage probably throttling")
                return;
            }
            var data = JSON.parse(body);
            //Shit that runs
            checkShit(data);
            checkLive(data);
        });
    }).on('error', function(e){
    console.log("Got an error: ", e); return;
});
}
