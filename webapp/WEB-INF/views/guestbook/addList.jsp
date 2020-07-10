<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">

</head>

<body>
    <div id="wrap">

        <c:import url="/WEB-INF/views/include/header.jsp"></c:import>

        <c:import url="/WEB-INF/views/include/userAside.jsp"></c:import>

        <div id="content">

            <div id="content-head">
                <h3>일반방명록</h3>
                <div id="location">
                    <ul>
                        <li>홈</li>
                        <li>방명록</li>
                        <li class="last">일반방명록</li>
                    </ul>
                </div>
                <div class="clear"></div>
            </div>
            <!-- //content-head -->

            <div id="guestbook">
                <form action="${pageContext.request.contextPath }/guestbook/write" method="get">
                    <table id="guestAdd">
                        <colgroup>
                            <col style="width: 70px;">
                            <col>
                            <col style="width: 70px;">
                            <col>
                        </colgroup>
                        <tbody>
                            <tr>
                                <th><label class="form-text" for="input-uname">이름</label></td>
                                <td><input id="input-uname" type="text" name="name"></td>
                                <th><label class="form-text" for="input-pass">패스워드</label></td>
                                <td><input id="input-pass"type="password" name="password"></td>
                            </tr>
                            <tr>
                                <td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
                            </tr>
                            <tr class="button-area">
                                <td colspan="4"><button type="submit">등록</button></td>
                            </tr>
                        </tbody>

                    </table>
                    <!-- //guestWrite -->
                    <input type="hidden" name="action" value="add">

                </form>
                <c:forEach items="${gbList}" var="guetbookVo">
                    <table class="guestRead">
                        <colgroup>
                            <col style="width: 10%;">
                            <col style="width: 40%;">
                            <col style="width: 40%;">
                            <col style="width: 10%;">
                        </colgroup>
                        <tr>
                            <td>${guetbookVo.no}</td>
                            <td>${guetbookVo.name}</td>
                            <td>${guetbookVo.regDate}</td>
                            <td><a href="${pageContext.request.contextPath }/guestbook/deleteForm?no=${guetbookVo.no}">[삭제]</a></td>
                        </tr>
                        <tr>
                            <td colspan=4 class="text-left">${guetbookVo.content}</td>
                        </tr>
                    </table>
                </c:forEach>
                <!-- //guestRead -->
            </div>
            <!-- //guestbook -->
        </div>
        <!-- //content  -->
        <c:import url="/WEB-INF/views/include/footer.jsp"></c:import>

    </div>
    <!-- //wrap -->

</body>

</html>