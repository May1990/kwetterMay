
var page = "";
function userNameExist(){
    var elementUserExist = document.getElementById('detailForm:userNameExist');
    elementUserExist.style.display = 'block';

    disabledBtnAdjust(elementUserExist);
}

function next() {
    if(page == ''){
        document.getElementById('detailForm:detail').style.display = 'none';
        document.getElementById('detailForm:detailAdjust').style.display = 'block';
        document.getElementById('detailForm:usernameAdjustPnl').style.display = 'none';
        document.getElementById('btnCancel').style.display = 'block';
        document.getElementById('btnCancel').value = 'Cancel';
        document.getElementById('btnAdjust').value = 'Aanpassingen verwerken';

        page = "details";
    }else if(page == 'details'){
        document.getElementById('detailForm:detailAdjust').style.display = 'none';
        document.getElementById('detailForm:usernameAdjustPnl').style.display = 'block';
        document.getElementById('btnAdjust').value = 'Username aanpassen';
        document.getElementById('btnAdjust').disabled = true;
        document.getElementById('btnCancel').value = 'Username zo laten';

        page = 'username';
    }else if(page == 'username'){
        var elementUserExist = document.getElementById('detailForm:userNameExist');
        disabledBtnAdjust(elementUserExist);

        back();

        page = "";
    }
}

function back(){
    document.getElementById('detailForm:detailAdjust').style.display = 'none';
    document.getElementById('detailForm:detail').style.display = 'block';
    document.getElementById('detailForm:usernameAdjustPnl').style.display = 'none';

    document.getElementById('btnAdjust').value = 'Bewerken';
    document.getElementById('btnAdjust').style.display = 'block';
    document.getElementById('btnCancel').style.display = 'none';

    page = "";
}

function disabledBtnAdjust(element){
    if(element.innerHTML == 'Username bestaat al!'){
        var btn = document.getElementById('btnAdjust');
        if(btn != 'undefined'){
            btn.disabled = true;
        }
    }else{
        resetDisableBtnAdjust();
    }
}

function resetDisableBtnAdjust(){
    var btn = document.getElementById('btnAdjust');
    if(btn != 'undefined') {
        btn.disabled = false;
    }
}

function blockOutAdjustBtns() {
    document.getElementById('btnAdjust').style.display = 'none';
    document.getElementById('btnCancel').style.display = 'none';
}

var host = "ws://localhost:8080/kwetterMay/kwetterWeb/";
var wSocket;
var browserSupport;
var elementHidden;
var username;
var sendMessage;

function setUsername(){
    debugger;
    elementHidden = document.getElementById('happeningForm:hiddenInput');
    // elementHidden = document.getElementById('logInForm:userNameInput');
    username = elementHidden.value;
    console.log(username);
    elementHidden = null;
}

// called  body onLoad()
function loadWebsocket()//username
{
    debugger;
    wSocket = new WebSocket(host+username);
    browserSupport = ("WebSocket" in window) ? true : false;

    if (browserSupport)
    {
        wSocket.onopen = function()
        {
            console.log("websocket has opened!");
        };

        // called when a message is received
        wSocket.onmessage = function(event)
        {
            debugger;
            var received_msg = event.data;
            var message = JSON.parse(received_msg);
            console.log(username+ " ontvangt " + message.data + " van " + message.from );
        };

        // called when socket closes
        wSocket.onclose = function()
        {
            // websocket is closed.
            console.log("Connection is closed...");
        };
    }
    else
    {
        // The browser doesn't support WebSocket
        alert("WebSocket is NOT supported by your Browser!");
    }

    sendMessage = function () {
        wSocket.send(JSON.stringify({
            from: username,
            data: document.getElementById("happeningForm:newTweet").value
        }));
    }

    // document.getElementById("happeningForm:newTweet").
}




