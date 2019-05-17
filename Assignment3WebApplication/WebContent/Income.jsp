<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

    <head>
        <title>Income</title>
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
            <h2 class="text-center">The Largest Income Group</h2>
          	
         <P>${IncomeReport }</P>
          
            <div style="float:left;width:10%">
            <h3>Country</h3>
            	<c:forEach var="item" items="${nameCountry }">
            	<p>${item.name }<p>
            	<table>
            		<tr>
            			<td><a href="IncomeServlet?id=${item.geographicAreaID }&amp;ceneusYear=2">2011</a></td>
            			<td><a href="IncomeServlet?id=${item.geographicAreaID }&amp;ceneusYear=1">2016</a></td>
            		</tr>
            	</table>
            	
            	
            	</c:forEach>
            </div>
            
            <div style="float:left;width:25%">
            <h3>Province</h3>
            	<c:forEach var="item" items="${nameProvince }">
            	${item.name }
            	<table>
            		<tr>
            			<td><a href="IncomeServlet?id=${item.geographicAreaID }&amp;ceneusYear=2">2011</a></td>
            			<td><a href="IncomeServlet?id=${item.geographicAreaID }&amp;ceneusYear=1">2016</a></td>
            		</tr>
            	</table>
            	<br>
            	</c:forEach>
            </div>
            
            <div style="float:left;width:40%">
            <h3>City</h3>
            	<c:forEach var="item" items="${nameCity }">
            	${item.name }
            	<table>
            		<tr>
            			<td><a href="IncomeServlet?id=${item.geographicAreaID }&amp;ceneusYear=2">2011</a></td>
            			<td><a href="IncomeServlet?id=${item.geographicAreaID }&amp;ceneusYear=1">2016</a></td>
            		</tr>
            	</table>
            	<br>
            	</c:forEach>
            </div>
            
            <div style="float:left;width:25%">
            <h3>Agglomeration</h3>
            	<c:forEach var="item" items="${nameArea }">
            	${item.name }
            	<table>
            		<tr>
            			<td><a href="IncomeServlet?id=${item.geographicAreaID }&amp;ceneusYear=2">2011</a></td>
            			<td><a href="IncomeServlet?id=${item.geographicAreaID }&amp;ceneusYear=1">2016</a></td>
            		</tr>
            	</table>
            	<br>
            	</c:forEach>
            </div>
            

        </div>

    </body>

</html>