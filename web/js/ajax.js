
var sendXmlHttp = null ;
var getXmlHttp = null ;
var contactXmlHttp = null;
function sendMessage(from,name,to,message,img){;
    createSendXmlHttpRequest();
    sendXmlHttp.open("POST",'/photo_chat/ChatServer?selection=1&from='+from+'&fromName='+name+'&to='+to+'&message='+message,true);
    sendXmlHttp.setRequestHeader("Content-Type", "text/plain;charset=UTF-8");
    sendXmlHttp.send(img);
}

function getMessages(uid){
    createGetXmlHttpRequest();
    getXmlHttp.onreadystatechange = handleRequestMessages;
    getXmlHttp.open("POST",'/photo_chat/ChatServer?selection=2&uid='+uid,true);
    getXmlHttp.setRequestHeader("Content-Type", "text/plain;charset=UTF-8");
    getXmlHttp.send("<?XML version=\"1.0\" encoding=\"UTF-8\"?>");
}

function refreshContactList(){
    createContactXmlHttpRequest();
    contactXmlHttp.onreadystatechange = handleRequestContacts;
    contactXmlHttp.open("POST",'/photo_chat/ChatServer?selection=3',true);
    contactXmlHttp.setRequestHeader("Content-Type", "text/plain;charset=UTF-8");
    contactXmlHttp.send("<?XML version=\"1.0\" encoding=\"UTF-8\"?>");
}
function createSendXmlHttpRequest(){
    if(window.ActiveXObject){
        sendXmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    else if(window.XMLHttpRequest){
        sendXmlHttp = new XMLHttpRequest();
    }
}
function createGetXmlHttpRequest(){
    if(window.ActiveXObject){
        getXmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    else if(window.XMLHttpRequest){
        getXmlHttp = new XMLHttpRequest();
    }
}
function createContactXmlHttpRequest(){
    if(window.ActiveXObject){
        contactXmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    else if(window.XMLHttpRequest){
        contactXmlHttp = new XMLHttpRequest();
    }
}
function handleRequestMessages(){
    if(getXmlHttp.readyState == 4){
        no_of_messages=getXmlHttp.getResponseHeader("message_count");
        xmlvalue=getXmlHttp.responseText;
        if(xmlvalue == null){
            xmlvalue = getXmlHttp.responseXML;
        }
        if(no_of_messages>0 && xmlvalue!=null && xmlvalue!=''){
            tokens=xmlvalue.split('ø');
            var d = new Date();
            var time =d.getHours()+':'+d.getMinutes()+':'+d.getSeconds();
            for(i=0;i<no_of_messages;i++){
                n=i*4;
                var from = tokens[n];
                var fromName = tokens[n+1];
                var message = tokens[n+2];
                var img = tokens[n+3];
                receive_message(from,fromName,message,time,img);
            }
        }
    }
}

function handleRequestContacts(){
    if(contactXmlHttp.readyState == 4){
        no_of_contacts=contactXmlHttp.getResponseHeader("contact_count");
        xmlvalue=contactXmlHttp.responseText;
        if(xmlvalue == null){
            xmlvalue = contactXmlHttp.responseXML;
        }
        if(no_of_contacts>0 && xmlvalue!=null && xmlvalue!=''){
            tokens=xmlvalue.split('ø');
            for(i=0;i<no_of_contacts;i++){
                n=i*3;
                var user = tokens[n];
                var username = tokens[n+1];
                var newStatus = tokens[n+2];
                updateUserStatus(user,username,newStatus);
            }
        }
    }
}