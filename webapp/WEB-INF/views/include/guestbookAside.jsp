<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="aside">
    <h2>방명록</h2>
    <ul>
        <li>
            <a href="${pageContext.request.contextPath }/guestbook/list">일반방명록</a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath }/guestbook/ajaxList">ajax방명록</a>
        </li>
    </ul>
</div>
