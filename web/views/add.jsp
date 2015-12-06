<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="models.User"%>
<%@page import="models.Friend"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Κάνε Φίλους</title>
        <link rel="stylesheet" href="css/bootstrap.css">
    </head>
    <body>
        <jsp:useBean id="user" scope="request" class="models.User"></jsp:useBean>
        <jsp:useBean id="friend" scope="request" class="models.Friend"></jsp:useBean>
        
        <%@ include file="menu.html" %>
        <div class="container-fluid">
            <div class="col-md-4 col-md-offset-2">
                <div class = "table-responsive">
                    <h3>Διαθέσιμοι χρήστες για προσθήκη</h3>
                    <table class = "table table-condensed noBorders">
                        <tbody>
                            <c:forEach var="user" items="${users}">
                                <tr>
                                    <td>
                                        <li class="list-group-item" style="padding:20px;"> 
                                            <span class="h3">${user.lastName} ${user.firstName}</span>
                                            <form action="add" method="post" style="display:inline" class="pull-right">
                                                <jsp:setProperty name="friend" property="friendId" value="${user.uid}"/>
                                                <input type="hidden" name="friendId" value="<jsp:getProperty name="friend" property="friendId"/>">
                                                <input class="btn btn-primary" type="submit" value="Προσθήκη">
                                            </form>
                                        </li>
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
