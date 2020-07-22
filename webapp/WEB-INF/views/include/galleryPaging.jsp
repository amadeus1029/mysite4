<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<div id="paging">
    <ul>
        <li>
            <a class="${gallery.currPage eq 1 ? 'off':''}" href="${pageContext.request.contextPath }/gallery/list?page=1&category=${param.category}&keyword=${param.keyword}">처음으로</a>
        </li>
        <li>
            <a class="${gallery.currPage le gallery.pageNum ? 'off':''}" href="${pageContext.request.contextPath }/gallery/list?page=${gallery.beginPage-10}&category=${param.category}&keyword=${param.keyword}">◀</a>
        </li>
        <c:forEach begin="${gallery.beginPage}" end="${gallery.endPage}" var="pageNum">
            <li class="${gallery.currPage eq pageNum ? 'active':''}">
                <a href="${pageContext.request.contextPath }/gallery/list?page=${pageNum}&category=${param.category}&keyword=${param.keyword}">${pageNum}</a>
            </li>
        </c:forEach>
        <li>
            <a class="${gallery.totalPage - gallery.beginPage lt gallery.pageNum ? 'off':'' }" href="${pageContext.request.contextPath }/gallery/list?page=${gallery.endPage+1}&category=${param.category}&keyword=${param.keyword}">▶</a>
        </li>
        <li>
            <a class="${gallery.currPage eq gallery.totalPage ? 'off':'' }" href="${pageContext.request.contextPath }/gallery/list?page=${gallery.totalPage}&category=${param.category}&keyword=${param.keyword}">마지막으로</a>
        </li>
    </ul>


    <div class="clear"></div>
</div>

