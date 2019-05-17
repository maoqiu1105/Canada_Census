<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

    <head>
        <title>Age Range</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </head>

    <body>

        <nav class="navbar navbar-expand-sm navbar-dark bg-dark fixed-top">

            <ul class="navbar-nav"> 
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/index.jsp">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/DisplayLocationServlet">Largest Total Income Group</a>
                </li>
                 <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/DisplayAgeRangeServlet">Population Growth by Age Range</a>
                </li>
            </ul>

        </nav>

        <div class="container" style="margin-top:80px">

            <h2 class="text-center">Population Growth by Age Range</h2>
            
            <div style="float:left;width:30%">
            	<c:forEach var="item" items="${AgeRange }">
            		<a href="AgeServlet?id=${item.ageGroupID }">${item.description }</a>
            		<br>
            	</c:forEach>
            </div>
            <div style="float:left;width:70%">
            <h2>Statement</h2>
            	<p>${GrowthStatement }</p>
            </div>

        </div>

    </body>

</html>