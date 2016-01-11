<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.*"%>
<%@page import="controllers.ChatSettings;" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%  
    HttpSession s=request.getSession(false);  
    String username=(String)s.getAttribute("username");
    ChatUser u = new ChatUser();
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
        <script src="js/ajax.js"></script>
		
	<link rel="stylesheet" href="css/bootstrap.css" />
	<link rel="stylesheet" href="css/style.css" />
        <script charset="UTF-8">
            maxChatWindows=5;
            refreshRate=1000;
            refreshRateForContacts=5;
            currentChatWindows=0;
            refresh=0;
            mute=0;

            var nsstyle='display:""'
            if (document.layers){
                var scrolldoc=document.scroll1.document.scroll2
            }
            //******
            function up(){
                if (!document.layers) return
                if (scrolldoc.top<0){
                    scrolldoc.top+=10
                }
                temp2=setTimeout("up()",50)
            }
            //******
            function down(){
                if (!document.layers) return
                if (scrolldoc.top-150>=scrolldoc.document.height*-1)
                    scrolldoc.top-=10
                temp=setTimeout("down()",50)
            }
            //******
            function clearup(){
                if (window.temp2)
                    clearInterval(temp2)
            }
            //******
            function cleardown(){
                if (window.temp)
                    clearInterval(temp)
            }
            //********
            function scroll_down(user_no){
                var objDiv = document.getElementById('scroll3_'+user_no);
                objDiv.scrollTop = objDiv.scrollHeight;
            }
            //*******
            function clear_text(user_no){
                var objDiv = document.getElementById('scroll3_'+user_no);
                objDiv.innerHTML='';
            }
            
            
            
            function close_chat(user_no){
                var objDiv = document.getElementById('global_div');
                var oldDiv = document.getElementById(user_no);
                objDiv.removeChild(oldDiv);
                currentChatWindows--;
            }
            
            function base64_url_encode(str) {
                return str.replace(/\+/g, '-').replace(/\//g, '_').replace(/\=+$/, '');
            }

            function base64_url_decode(str) {
                str = (str + '===').slice(0, str.length + (str.length % 4));
                return str.replace(/-/g, '+').replace(/_/g, '/');
            }
            
            function add_message(user_no){
                var img = $('#base64Form').val();
                var textValue=document.getElementById('input_'+user_no).value;
                if(textValue=='' && img=='') return;
                sendMessage(<%= u.getUid()%>,'<%= u.getUsername()%>',user_no,textValue,img);
                var objDiv = document.getElementById('scroll3_'+user_no);
                var nameValue='<%= u.getUsername()%>';
                objDiv.innerHTML=objDiv.innerHTML+'<div class="col-md-12 chat-message1"><br><font color=black>'+nameValue+' : '+textValue+'</font>'+'<br><img src="data:image/jpg; base64,'+img+' " /></div>';
                document.getElementById('input_'+user_no).value='';
                scroll_down(user_no);
                //document.getElementById('state_'+user_no).src='/WebChatApp/images/empty.png';
                //document.getElementById('state_'+user_no).width=1;
                //document.getElementById('state_'+user_no).hight=1;
            }
            function receive_message(user_no,name,message,time,img){
                var objDiv = document.getElementById('scroll3_'+user_no);
                if(!document.getElementById('scroll3_'+user_no)) {
                    openNewChat(user_no,name);
                    objDiv = document.getElementById('scroll3_'+user_no);
                }
                objDiv.innerHTML=objDiv.innerHTML+'<div class="col-md-12 chat-message2"><br><font color=white>'+name+' : '+message+'</font>'+'<br><img src="data:image/png; base64,'+base64_url_decode(img)+' " /></div>';
                scroll_down(user_no);
                //document.getElementById('state_'+user_no).src='/WebChatApp/images/report.gif';
                //document.getElementById('state_'+user_no).width=15;
                //document.getElementById('state_'+user_no).hight=15;
                //document.getElementById('display_'+user_no).value=' Τελευταίο μήνυμα στις '+time;
            }
            function enableEnterKey(e,user_no){
                var key;
                if(window.event)
                    key = window.event.keyCode;
                else
                    key = e.which;
                if(key == 13){
                    return add_message(user_no);
                } else{
                    return true;
                }
            }
            function openNewChat(user_no,name){
                var oldDiv = document.getElementById(user_no);
                if(oldDiv!=null) return;
                if(currentChatWindows>=maxChatWindows){
                    //alert('Sorry, You have exceed the max number of open chat dialogs per user');
                    return;
                }
                currentChatWindows++;
                var objDiv = document.getElementById('global_div');
                var mytext='\
<div id='+user_no+' class="container-fluid chat-wrapper">\n\
    <div id="chat_'+user_no+'" class="col-md-12">\n\
        <div class="chat-header">\n\
            <span class="h3"><font color=white><b>'+name+'</b></font></span>\n\
            <span class="pull-right"><button onclick="close_chat('+user_no+');" class="btn btn-default btn-sm">Close Chat</button></span>\n\
            <span class="pull-right"><button onclick="add_message('+user_no+')" class="btn btn-info btn-sm">Send</button></span>\n\
        </div><hr>\n\
        <div>\n\
<table style="display:none" width=400><tr><td width=360><font color=white><b> Chat to '+name+'</b></font></td><td align=right width=20><img id=state_'+user_no+' src=/WebChatApp/images/empty.png border=0 width=1 hight=1></td><td align=right width=20><img src=/WebChatApp/images/close.jpg border=0 title="Close Chat" onclick="close_chat('+user_no+');" style="cursor:pointer"></a></td></tr></table>\n\
            <div id=scroll3_'+user_no+' class="chat-body col-md-12"></div>\n\
            <input id=input_'+user_no+' name=input_'+user_no+' onkeydown="return enableEnterKey(event,'+user_no+')" class="col-md-12 col-xs-12 col-sm-12 chat-input" >\n\
        </div>\n\
    </div>\n\
</div>';
                objDiv.innerHTML=objDiv.innerHTML+mytext;
            }
            function update(){
                getMessages(<%= u.getUid()%>);
                refresh++;
                //each 4 = 1 minute (15 seconds * 4 =60)
                if(refresh>=refreshRateForContacts) {
                    refresh=0;
                    refreshContactList();
                }
                setTimeout("update()",refreshRate);
            }
            function updateUserStatus(user_no,user_name,newStatus){
                if(newStatus==<%=ChatUser.ONLINE%>){
                    document.getElementById('user_'+user_no).onclick=function(){ openNewChat(user_no,user_name)}
                    $('#user_'+user_no).removeClass('btn-danger').addClass('btn-success').html('online');
                }else{
                    document.getElementById('user_'+user_no).onclick="";
                    $('#user_'+user_no).addClass('btn-danger').html('offline');
                }
            }
        </script> 
    <script>
        function del()
        {
           var Nobel = document.getElementById("image").style.display ='None';
        }
    </script>
    </head>
    <body  onload='setTimeout("update()",refreshRate);'>
        <nav role="navigation" class="navbar navbar-inverse">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand">Photo Chat</a>
        </div>
        <!-- Collection of nav links, forms, and other content for toggling -->
        <div id="navbarCollapse" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="home">Home</a></li>
                <li><a href="friends">Add</a></li>
                <li><a href="/photo_chat/ChatServer?selection=4&uid=<%= u.getUid()%>"">Logout</a></li>
            </ul>
        </div>
    </nav>
      <div class="container-fluid">
          
          <div class="col-md-12">
              
              
              <div class="col-md-3">
                  <div id="webcam"></div>
                  <button class="btn btn-info" id="btn2" onclick="base64_tofield_and_image()">Take a Picture!</button>
                  <button class="btn btn-info" id="btn2" onclick="del()">Delete photo!</button>
                  <textarea style="display: none" id="base64Form"></textarea>
              </div>
              
              
              <div class="col-md-6">
                  <img id="image" class="center-block"/>
                  <div id="global_div" class="col-md-12">
                        
                  </div>
              </div>
              
              
                <div class="col-md-3 bg-warning">
                    <h3>Friends List</h3>
                    <hr/>
                    <table class="table table-condensed">
                        <tbody>
                        <c:forEach var="friend" items="${myFriends}">
                            <tr>
                                <td>
                                    <span><button id="user_${friend.uid}" class="btn btn-success btn-sm" onclick="openNewChat(${friend.uid},'${friend.username}')">online</button>  </span>
                                    <span class="h3">${friend.username} - (${friend.firstName} ${friend.lastName})</span>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
              </div>
          </div>
    </body>
</html>

