<%@page import="models.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%  
    HttpSession s=request.getSession(false);  
    String username=(String)s.getAttribute("username");
    User u = new User();
    u.setUsername(username);
    u.getUser();            
%>
<!DOCTYPE html>
<html>
    <head>
        <title><% out.print(s.getAttribute("username")); %></title>
        <script src="js/jquery.js"></script>
        <script src="js/swf.js"></script>
        <script src="js/script.js"></script>
        <script src="views/scriptcam.js"></script>
		
	<link rel="stylesheet" href="css/bootstrap.css" />
	<link rel="stylesheet" href="css/style.css" />
        <script>
	var wsocket;
	var serviceLocation = "ws://localhost:8080/photo_chat/chat/";
	var $nickName;
	var $message;
	var $chatWindow;
	var room = '';
 
	function onMessageReceived(evt) {
		var msg = JSON.parse(evt.data); // native API
		var $messageLine = $('<div class="messageSent"><span class="text-info">' + msg.received
				+ '</span><span class="text-success">'+ 'Από ' + msg.sender
				+ '<hr/></span><p>' + msg.message
				+ '</p></div>');
		$chatWindow.append($messageLine);
                $("#response").animate({ scrollTop: $(document).height() }, "slow");return false;
	}
	function sendMessage() {
		var msg = '{"message":"' + $message.val() + '", "sender":"'
				+ $nickName.text() + '", "received":""}';
		wsocket.send(msg);
		$message.val('').focus();
	}
 
	function connectToChatserver() {
		room = 'global';
		wsocket = new WebSocket(serviceLocation + room);
		wsocket.onmessage = onMessageReceived;
	}
 
	function leaveRoom() {
		wsocket.close();
		$chatWindow.empty();
		$('.chat-wrapper').hide();
                $('.startChat').prop('disabled', false);
		//$('.chat-signin').show();
		//$nickName.focus();
	}
 
	$(document).ready(function() {
		$nickName = $('head title');
		$message = $('#message');
		$chatWindow = $('#response');
		$('.chat-wrapper').hide();
		//$nickName.focus();
 
		$('.startChat').click(function(evt) {
			evt.preventDefault();
			connectToChatserver();
			//$('.chat-wrapper h2').text('Chat # '+$nickName.text() + "@" + room);
			//$('.chat-signin').hide();
			$('.chat-wrapper').show();
			$message.focus();
                        $(this).prop('disabled', true);
		});
		$('#do-chat').submit(function(evt) {
			evt.preventDefault();
			sendMessage()
		});
 
		$('#leave-room').click(function(){
			leaveRoom();
		});
	});
</script>
<script>
    function del()
    {
        var Nobel = document.getElementById("image").style.display ='None';
    }
</script>
    </head>
    <body>
        <%@ include file="/views/menu.html" %>
      <div class="container-fluid">
          
          <div class="col-md-12">
              <div class="col-md-3">
                  <div id="webcam"></div>
                  <h1>Κάνε Snap</h1>
                  <button class="btn btn-danger" id="btn2" onclick="base64_toimage()">Send</button>
                  <button class="btn btn-danger" id="btn2" onclick="del()">Delete</button>
                  <img id="image"/>
                  
              </div>
              <div class="col-md-6 col-md-offset-2 bg-info">
                  
                  <div class="col-md-12" id="chatArea">
                      <span class="col-md-12"><h1 class="col-md-8">Chat Area</h1><h1  class="col-md-4"><button class="startChat">Chat</button></h1></span>
                      <div class="chat-wrapper">
                        <form id="do-chat">
                            <h2 class="alert alert-success"></h2>
                            <div id="response" class="col-md-7" style=""></div>
                            <fieldset class="col-md-6">
                                <div class="controls">
                                    <input type="text" class="input-block-level" placeholder="Your message..." id="message" style="height:60px"/>
                                    <input type="submit" class="btn btn-large btn-success" value="Αποστολή" />
                                    <button class="btn btn-large bg-danger" type="button" id="leave-room">Έξοδος</button>
                                </div>
                            </fieldset>
                        </form>
                    </div>
                  </div>
              </div>
                  
              </div>
          </div>
      </div>
    </body>
</html>

