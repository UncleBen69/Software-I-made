//Requirements
var http = require("http")
var url = require('url');
var mysql = require('mysql');
var timestamp = require('time-stamp');
var geoip = require('geoip-lite');
const publicIp = require('public-ip');

if (typeof localStorage === "undefined" || localStorage === null) {
    var LocalStorage = require('node-localstorage').LocalStorage;
    localStorage = new LocalStorage('./cache');
}
//Info removed for security reasons
var db_config = {
    host     : '',
    user     : '',
    password : '',
    database : ''
  };

//var con = mysql.createConnection(db_config);
console.log("Running")


var con;
//This connection handler was "borrowed" from somewhere on the internet to reconnect the mysql server on disconnect.
function handleDisconnect() {
  con = mysql.createConnection(db_config);  

  con.connect(function(err) {
    if(err) {
      setTimeout(handleDisconnect, 2000);
    }
  });

  con.on('error', function(err) {
    //console.log('db error', err);
    if(err.code === 'PROTOCOL_CONNECTION_LOST') { 
      handleDisconnect();
    } else {
        console.log(err);
    }
  });
}

handleDisconnect();

  

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
        //Check if in database
        con.query(`CREATE TABLE IF NOT EXISTS ${id} (videoId TEXT, title TEXT, date TEXT, time TEXT, id MEDIUMINT NOT NULL AUTO_INCREMENT KEY)`)
    }
}
function checkLive(data){
    if(data.success == true){
        var i;
        for(i = 0; i < data.streamers.length; i++){
            //In the for function
            temp1 = data.streamers[i].id;
            id = temp1.toString();

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

                    writeToDatabase(id, title, videoId);
                    
                    localStorage.setItem(id+"_live", true);
                }
            }
        }
    }
}

function writeToDatabase(id, title, videoId){
    console.log(id+" Went online")
    console.log("Wrote to database")

    let now = new Date();

    date = timestamp(`DD/MM/YYYY`);
    time = timestamp(`HH:mm`);
    console.log(date+" "+time)
    //Write to database
    con.query(`INSERT INTO ${id} (videoId, title, date, time) VALUES (?, ?, ?, ?)`, [videoId, title, date, time]);
}

//Repeat every so so seconds
const INTERVAL = 1000 * 60
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
            var data = JSON.parse(body);
            //Shit that runs
            checkShit(data);
            checkLive(data);
        });
    }).on('error', function(e){
    console.log("Got an error: ", e);
});
}







//Web Server
http.createServer(function (req, res) {
    res.setHeader('Access-Control-Allow-Origin', '*');
    // Request methods you wish to allow
    res.setHeader('Access-Control-Allow-Methods', 'GET, POST, OPTIONS, PUT, PATCH, DELETE');
    // Request headers you wish to allow
    res.setHeader('Access-Control-Allow-Headers', 'X-Requested-With,content-type');

    rawip = req.connection.remoteAddress.split(":")
    ip = rawip[3]
    var geo = geoip.lookup(ip);

    res.writeHead(200, {"Content-Type": "application/json"});
    console.log(req.url)
    if(req.url == "/"){res.end("Hello this is the vods.cx api");
        console.log(`${ip} in ${geo.city} of ${geo.country} ${geo.region} visited root`);
    return;}
    if(req.url == "/favicon.ico"){console.log(`${ip} in ${geo.city} of ${geo.country} ${geo.region} requesting favicon.ico`);return;}
    if(req.url == "/systemInfo"){console.log(`${ip} in ${geo.city} of ${geo.country} ${geo.region} requesting systemInfo`);return}
    if(req.url == "/ip"){
        console.log(`${ip} in ${geo.city} of ${geo.country} ${geo.region} requesting ip address`)
        publicIp.v4().then(ip => {
            res.end(ip)
        });
        return;
    }
    //This url is pinged everytime someone visits the vods.cx site.
    if(req.url == "/ping"){
        console.log(`${ip} in ${geo.city} of ${geo.country} ${geo.region} Visited vods.cx`)
        res.end("thanks");
        return;
    }
    //This section is for the retrieval of vods
    var q = url.parse(req.url, true).query;
    var path = url.parse(req.url)
    console.log(path.pathname)
    pathname = path.pathname
    if(pathname == "/vods/"){
        id = q.id;
        page = q.page;
        if(page > 1){offset = (page - 1) * 12}else{offset = 0}

        console.log(`${ip} in ${geo.city} of ${geo.country} ${geo.region} checked page ${page} of ${id}`)
        //Querys the database
        con.query(`SELECT videoId, title, date, time FROM ${id} LIMIT 12 OFFSET ${offset}`,(err, data) =>{
            if(err) console.log(err);
            var json = JSON.stringify({
                vods: data
            })
            //console.log(json)
            res.end(json)
        });
    }
    /* else if(pathname == "/test/"){
        res.end("Working")
    } */
    else{
        res.end("Invalid Request")
    }
    //Running on port 8080
}).listen(8080)