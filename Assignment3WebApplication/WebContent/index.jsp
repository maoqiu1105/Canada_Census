<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>

    <head>
        <title>Home</title>
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

            <h2 class="text-center">Assignment3 - CanadaCensusDB</h2>
            
            <div style="float:left;width:30%;margin:5px">
             <h3>Add Geographic Area</h3>
            <form action="CreationServlet?action=addGeo" method="POST">
            	<p>Code</p><input type="number" name="code"><br>
            	<p>Level</p><input type="number" name="level"><br>
            	<p>Name</p><input type="text" name="name"><br>
            	<p>AlternativeCode</p><input type="number" name="alternative" placeholder="same as code if level 1; 5 digits if level 2 or 3"><br>
            	<input type="submit" value=submit>
            </form>
            </div>
            
             <div style="float:left;width:30%;margin:5px">
            <h3>Add Age</h3>
            <form action="CreationServlet?action=addAge" method="POST">
            	<p>age Group</p><input type="number" name="ageGroup" placeholder="1-127"><br>
            	<p>Census Year</p><input type="number" name="censusYear" placeholder="1 for 2016; 2 for 2011"><br>
            	<p>Geographic Area</p><input type="number" name="geographicArea"><br>
            	<p>Male</p><input type="number" name="male"><br>
            	<p>Female</p><input type="number" name="female"><br>
            	<input type="submit" value=submit>
            </form>
            </div>
            
             <div style="float:left;width:30%;margin:5px">
             	<h3>Add Household</h3>
           		<form action="CreationServlet?action=addHousehold" method="POST">
            		<p>geographicArea</p><input type="number" name="geographicArea" placeholder="1-127"><br>
            		<p>householdType</p><input type="number" name="householdType" placeholder="1-11"><br>
            		<p>householdSize</p><input type="number" name="householdSize" placeholder="1-7"><br>
            		<p>householdsByAgeRange</p><input type="number" name="householdsByAgeRange" placeholder="1-18"><br>
            		<p>householdEarners</p><input type="number" name="householdEarners" placeholder="1-6"><br>
            		<p>totalIncome</p><input type="number" name="totalIncome" placeholder="1-22"><br>
            		<p>censusYear</p><input type="number" name="censusYear" placeholder="1-2"><br>
            		<p>numberReported</p><input type="number" name="numberReported"><br>
            		<input type="submit" value=submit>
            	</form>
            </div>
           
            
        </div>

    </body>

</html>



