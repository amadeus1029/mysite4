<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">

</head>


<body>
    <div id="wrap">

        <c:import url="/WEB-INF/views/include/header.jsp"></c:import>

        <c:import url="/WEB-INF/views/include/boardAside.jsp"></c:import>

        <div id="content">

            <div id="content-head">
                <h3>게시판</h3>
                <div id="location">
                    <ul>
                        <li>홈</li>
                        <li>게시판</li>
                        <li class="last">일반게시판</li>
                    </ul>
                </div>
                <div class="clear"></div>
            </div>
            <!-- //content-head -->

            <div id="board">
                <div id="list">
                    <form action="${pageContext.request.contextPath }/board/list" method="get">
                        <div class="form-group text-right">
                            <select id="searchCategory" name="category">
                                <option ${param.category eq 'title' || searched eq null ? 'selected':'' } value="title">제목</option>
                                <option ${param.category eq 'content'  ? 'selected':'' } value="content">내용</option>
                                <option ${param.category eq 'titleContent'  ? 'selected':'' } value="titleContent">제목 + 내용</option>
                                <option ${param.category eq 'writer' ? 'selected':'' } value="writer">작성자</option>
                            </select>
                            <input type="text" name="keyword" value="${param.keyword}">
                            <button type="submit" id=btn_search>검색</button>
                        </div>
                    </form>
                    <table >
                        <thead>
                            <tr>
                                <th>번호</th>
                                <th>제목</th>
                                <th>글쓴이</th>
                                <th>조회수</th>
                                <th>작성일</th>
                                <th>관리</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${boardList}" var="boardVo">
                                <tr>
                                    <td>${boardVo.boardNo}</td>
                                    <td class="text-left"><a href="${pageContext.request.contextPath }/board/read?boardNo=${boardVo.boardNo}">${boardVo.title}</a></td>
                                    <td>${boardVo.writer}</td>
                                    <td>${boardVo.views}</td>
                                    <td>${boardVo.regDate}</td>
                                    <td>
                                        <c:if test="${authUser.no eq boardVo.writerNo}">
                                            <a href="${pageContext.request.contextPath }/board/delete?boardNo=${boardVo.boardNo}&writerNo=${boardVo.writerNo}">[삭제]</a>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <c:import url="/WEB-INF/views/include/boardPaging.jsp"></c:import>

                    <c:choose>
                        <c:when test="${authUser ne null}">
                            <a id="btn_write" href="${pageContext.request.contextPath }/board/writeForm">글쓰기</a>
                        </c:when>
                        <c:otherwise>
                            <a id="btn_write" href="${pageContext.request.contextPath }/user/loginForm">글쓰기</a>
                        </c:otherwise>
                    </c:choose>
                </div>
                <!-- //list -->
            </div>
            <!-- //board -->
        </div>
        <!-- //content  -->

        <c:import url="/WEB-INF/views/include/footer.jsp"></c:import>

    </div>
    <!-- //wrap -->

</body>

</html>
