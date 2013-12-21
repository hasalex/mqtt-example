$(document).ready(function() {
    function doSubscribe() {

    }

    $('#mainform').submit(function(){
        var messageinput = $('#message');
        message = new Messaging.Message(messageinput.val());
        message.destinationName = 'sewatech';
        messageinput.val('');
        messageinput.focus();
        client.send(message);
        return false;
    });


    function doDisconnect() {
        client.disconnect();
    }

    // Web Messaging API callbacks
    var onSuccess = function(value) {
        $('#status').toggleClass('connected',true);
        $('#status').text('Success');
    }

    var onConnect = function(frame) {
        $('#status').toggleClass('connected',true);
        $('#status').text('Connected');
        client.subscribe("sewatech");
    }
    var onFailure = function(error) {
        $('#status').toggleClass('connected',false);
        $('#status').text("Failure");
    }

    function onConnectionLost(responseObject) {
        alert(client.clientId+"\n"+responseObject.errorCode);
    }

    function onMessageArrived(message) {
        $('#messagelist').prepend('<li>'+message.payloadString+'</li>');
    }

    var client;
    var r = Math.round(Math.random()*Math.pow(10,5));
    var d = new Date().getTime();
    var cid = r.toString() + "-" + d.toString()

    client = new Messaging.Client("localhost", 61614, cid );
    client.onConnect = onConnect;
    client.onMessageArrived = onMessageArrived;
    client.onConnectionLost = onConnectionLost;
    client.connect({onSuccess: onConnect, onFailure: onFailure});

});
