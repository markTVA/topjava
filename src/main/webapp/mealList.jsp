<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- connect jstl -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Список еды</title>
    <link rel="stylesheet" type="text/css" href="style.css"/>
</head>
<body>
<table>
    <caption>Список еды</caption>
    <tr><th>Описание</th><th>Калории</th><th>Дата и Время</th><th>Удалить</th><th>Изменить</th></tr>
    <c:forEach items="${mealList}" var="mealWithExceed">
        <c:choose>
            <c:when test="${mealWithExceed.exceed}">
                <tr class="exceed">
            </c:when>
            <c:otherwise>
                <tr class="notExceed">
            </c:otherwise>
        </c:choose>
        <td><c:out value="${mealWithExceed.description}" /></td>
        <td><c:out value="${mealWithExceed.calories}" /></td>
        <td>${mealWithExceed.getDateTime().toLocalDate()}  ${mealWithExceed.getDateTime().toLocalTime()}</td>
        <td>
            <form method="post" action="<c:url value="/meals"/> ">
                <input type="hidden" name="action" value="delete"/>
                <input type="hidden" name="id" value="${mealWithExceed.id}"/>
                <input type="submit" value="Удалить"/>
            </form>
        </td>
        <td>
            <form method="post" action="<c:url value="/meals"/> ">
                <input type="hidden" name="action" value="showFormForChange"/>
                <input type="hidden" name="id" value="${mealWithExceed.id}"/>
                <input type="submit" value="Изменить"/>
            </form>
        </td>
        </tr>
    </c:forEach>
</table>
<br/>
<br/>
<h2>Добавить новую запись</h2>
<form method="post" action="<c:url value="/meals"/> ">
    <input type="hidden" name="action" value="add"/>
    <input type="text" name="description" placeholder="Описание" required/>
    <input type="date" name="date" required/>
    <input type="time" name="time" required/>
    <input type="number" name="calories" required/>
    <input type="submit" value="Добавить"/>
</form>
<br/>
<br/>
<c:if test="${isNeedShowFormForChange}">
    <h2>Изменить запись</h2>
    <form method="post" action="<c:url value="/meals"/> ">
        <input type="hidden" name="action" value="change"/>
        <input type="hidden" name="id" value="${mealForChanging.id}"/>
        <input type="text" name="description" value="${mealForChanging.description}" required/>
        <input type="date" name="date" value="${mealForChanging.dateTime.toLocalDate()}" required/>
        <input type="time" name="time" value="${mealForChanging.dateTime.toLocalTime()}" required/>
        <input type="number" name="calories" value="${mealForChanging.calories}" required/>
        <input type="submit" value="Изменить"/>
    </form>
</c:if>
</body>
</html>
