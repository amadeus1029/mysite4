<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<div id="paging">
    <ul>
        <li>
            <a class="${pageVo.currPage eq 1 ? 'off':''}" href="${pageContext.request.contextPath }/board/list?page=1&category=${param.category}&keyword=${param.keyword}">처음으로</a>
        </li>
        <li>
            <a class="${pageVo.currPage le 10 ? 'off':''}" href="${pageContext.request.contextPath }/board/list?page=${pageVo.beginPage-10}&category=${param.category}&keyword=${param.keyword}">◀</a>
        </li>
        <c:forEach begin="${pageVo.beginPage}" end="${pageVo.endPage}" var="pageNum">
            <li class="${pageVo.currPage eq pageNum ? 'active':''}">
                <a href="${pageContext.request.contextPath }/board/list?page=${pageNum}&category=${param.category}&keyword=${param.keyword}">${pageNum}</a>
            </li>
        </c:forEach>
        <li>
            <a class="${pageVo.totalPage - pageVo.beginPage lt 10 ? 'off':'' }" href="${pageContext.request.contextPath }/board/list?page=${pageVo.endPage+1}&category=${param.category}&keyword=${param.keyword}">▶</a>
        </li>
        <li>
            <a class="${pageVo.currPage eq pageVo.totalPage ? 'off':'' }" href="${pageContext.request.contextPath }/board/list?page=${pageVo.totalPage}&category=${param.category}&keyword=${param.keyword}">마지막으로</a>
        </li>
    </ul>


    <div class="clear"></div>
</div>
