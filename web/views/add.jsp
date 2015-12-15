<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="models.ChatUser"%>
<%@page import="models.Friend"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Κάνε Φίλους</title>
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <jsp:useBean id="user" scope="request" class="models.ChatUser"></jsp:useBean>
        <jsp:useBean id="friend" scope="request" class="models.Friend"></jsp:useBean>
        
        <%@ include file="menu.html" %>
        <div class="container-fluid">
            <div class="col-md-10">
                <div class = "table-responsive">
                    <h3 class="center-block">Διαθέσιμοι χρήστες για προσθήκη</h3>
                            <c:forEach var="user" items="${users}">
                                <div class="col-md-3 addUser">
                                    <h3>${user.lastName} ${user.firstName}</h3>
                                    <hr>
                                    <br>
                                    <form action="add" method="post" style="display:inline">
                                        <jsp:setProperty name="friend" property="friendId" value="${user.uid}"/>
                                        <input type="hidden" name="friendId" value="<jsp:getProperty name="friend" property="friendId"/>">
                                        <input class="btn btn-primary" type="submit" value="Προσθήκη">
                                    </form>
                                </div>    
                            </c:forEach>
                </div>  	
            </div>
        </div>
    </body>
</html>
