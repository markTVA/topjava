<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- connect jstl -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meal List</title>
</head>
<body>
<h2 align="center">Meal List</h2>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of users</h2></caption>
        <tr>
            <th>DateTime</th>
            <th>Description</th>
            <th>Calories</th>
        </tr>
        <c:forEach var="meal" items="${mealList}">
            <c:choose>
                <c:when test="${meal.exceed == true}">
                    <tr bgcolor="red">
                        <td>${meal.getDateTime().toLocalDate()}  ${meal.getDateTime().toLocalTime()}</td>
                        <td><c:out value="${meal.description}" /></td>
                        <td><c:out value="${meal.calories}" /></td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <tr bgcolor="green">
                        <td>${meal.getDateTime().toLocalDate()}  ${meal.getDateTime().toLocalTime()}</td>
                        <td><c:out value="${meal.description}" /></td>
                        <td><c:out value="${meal.calories}" /></td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </table>
</div>

</body>
</html>
