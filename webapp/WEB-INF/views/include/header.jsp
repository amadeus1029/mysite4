<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="header">
    <h1>
        <a href="${pageContext.request.contextPath }/main">MySite</a>
    </h1>
    <ul>
        <c:choose>
            <c:when test="${authUser ne null}">
                <li>${authUser.name} 님 안녕하세요^^</li>
                <li><a href="${pageContext.request.contextPath }/user/logout">로그아웃</a></li>
                <li><a href="${pageContext.request.contextPath }/user/modifyForm">회원정보수정</a></li>
            </c:when>
            <c:otherwise>
                <li><a href="${pageContext.request.contextPath }/user/loginForm">로그인</a></li>
                <li><a href="${pageContext.request.contextPath }/user/joinForm">회원가입</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
</div>
<!-- //header -->

<div id="nav">
    <ul>
        <li><a href="${pageContext.request.contextPath }/guestbook/list">방명록</a></li>
        <li><a href="">갤러리</a></li>
        <li><a href="">게시판</a></li>
        <li><a href="">입사지원서</a></li>
    </ul>
    <div class="clear"></div>
</div>
<!-- //nav -->
