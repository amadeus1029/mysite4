<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="category" value="${param.category ne null ? '&category='.concat(param.category) : ''}"/>
<c:set var="keyword" value="${param.keyword ne null ? '&keyword='.concat(param.keyword) : ''}"/>
<c:set var="currPage" value="${page}"/>
<fmt:parseNumber var="_currPage" integerOnly="true" value="${(currPage-1)/10}"/>
<c:set var="beginPage" value="${_currPage*10+1}"/>
<c:set var="endPage" value="${_currPage*10+10}"/>
<fmt:parseNumber var="totalPage" integerOnly="true" value="${(totalCount-1)/5 + 1}"/>

<div id="paging">
    <ul>
        <li>
            <a class="${currPage eq 1 ? 'off':''}" href="${pageContext.request.contextPath }/board/list?page=1${category}${keyword}">처음으로</a>
        </li>
        <li>
            <a class="${currPage le 10 ? 'off':''}" href="${pageContext.request.contextPath }/board/list?page=${beginPage-10}${category}${keyword}">◀</a>
        </li>
        <c:forEach begin="${beginPage}" end="${totalPage gt endPage ? endPage:totalPage }" var="pageNum">
            <li class="${currPage eq pageNum ? 'active':''}">
                <a href="${pageContext.request.contextPath }/board/list?page=${pageNum}${category}${keyword}">${pageNum}</a>
            </li>
        </c:forEach>
        <li>
            <a class="${totalPage - beginPage lt 10 ? 'off':'' }" href="${pageContext.request.contextPath }/board/list?page=${endPage+1}${category}${keyword}">▶</a>
        </li>
        <li>
            <a class="${currPage eq totalPage ? 'off':'' }" href="${pageContext.request.contextPath }/board/list?page=${totalPage}${category}${keyword}">마지막으로</a>
        </li>
    </ul>


    <div class="clear"></div>
</div>
