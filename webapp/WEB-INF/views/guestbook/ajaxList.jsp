<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.css" rel="stylesheet"
          type="text/css">
    <script src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.min.js"></script>
    <script src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.js"></script>
</head>

<body>
    <div id="wrap">

        <c:import url="/WEB-INF/views/include/header.jsp"></c:import>

        <c:import url="/WEB-INF/views/include/guestbookAside.jsp"></c:import>

        <div id="content">

            <div id="content-head">
                <h3>AJAX방명록</h3>
                <div id="location">
                    <ul>
                        <li>홈</li>
                        <li>방명록</li>
                        <li class="last">AJAX방명록</li>
                    </ul>
                </div>
                <div class="clear"></div>
            </div>
            <!-- //content-head -->

            <div id="guestbook">
                <form action="" method="">
                    <table id="guestAdd">
                        <colgroup>
                            <col style="width: 70px;">
                            <col>
                            <col style="width: 70px;">
                            <col>
                        </colgroup>
                        <tbody>
                            <tr>
                                <th><label class="form-text" for="input-uname">이름</label>
                                </td>
                                <td><input id="input-uname" type="text" name="name"></td>
                                <th><label class="form-text" for="input-pass">패스워드</label>
                                </td>
                                <td><input id="input-pass" type="password" name="password"></td>
                            </tr>
                            <tr>
                                <td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
                            </tr>
                            <tr class="button-area">
                                <td colspan="4">
                                    <button id="addGuestBookSubmit" type="button" onclick="addGuestBook($(this));">등록
                                    </button>
                                </td>
                            </tr>
                        </tbody>

                    </table>
                    <!-- //guestWrite -->
                    <input type="hidden" name="action" value="add">

                </form>
                <div id="guestBookListArea">

                </div>
                <!-- //guestRead -->
            </div>
            <!-- //guestbook -->
        </div>
        <!-- //content  -->
        <c:import url="/WEB-INF/views/include/footer.jsp"></c:import>

    </div>
    <!-- //wrap -->
    <!-- 삭제팝업(모달)창 -->
    <div class="modal fade" id="deleteModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">방명록삭제</h4>
                </div>
                <div class="modal-body">
                    <label>비밀번호</label>
                    <input type="password" name="modalPassword" id="modalPassword"><br>
                    <input type="text" name="modalNo" value="" id="modalNo"> <br>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
                    <button type="button" class="btn btn-danger" id="btnDel" onclick="deleteGuestbook()">삭제</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

    <script type="text/javascript">
        $(document).ready(function () {
            fetchList();
        }); //ready 종료
        function render(obj) {
            var html = "";
            html += "<table class='guestRead' data-id='"+obj.no+"'>";
            html += "<colgroup>";
            html += "<col style='width: 10%;'>";
            html += "<col style='width: 40%;'>";
            html += "<col style='width: 40%;'>";
            html += "<col style='width: 10%;'>";
            html += "</colgroup>";
            html += "<tr>";
            html += "<td>";
            html += obj.no;
            html += "</td>";
            html += "<td>";
            html += obj.name;
            html += "</td>";
            html += "<td>";
            html += obj.regDate;
            html += "</td>";
            html += "<td>";
            html += "<a href='' onclick='showDeleteGuestbookModal(" + obj.no + ")'>[삭제]<a>";
            html += "</td>";
            html += "</tr>";
            html += "<tr>";
            html += "<td colspan=4 class='text-left'>";
            html += obj.content;
            html += "</td>";
            html += "</tr>";
            html += "</table>";
            $("#guestBookListArea").prepend(html);
        }

        function fetchList() {
            $.ajax({
                url: "${pageContext.request.contextPath}/api/guestbook/list",
                type: "post",
                /*contentType: "application/json",
                data: {id: inputIdVal},*/
                dataType: "json",
                success: function (gbList) {
                    for (i = 0; i < gbList.length; i++) {
                        render(gbList[i]);
                    }
                },
                error: function (XHR, status, error) {
                    console.error(status + ":" + error);
                }
            });
        }

        function addGuestBook(target) {
            var form = target.parents("form");
            var name = form.find("input[name='name']").val();
            var password = form.find("input[name='password']").val();
            var content = form.find("textarea[name='content']").val();
            var guestbookVo = {
                name: name,
                password: password,
                content: content
            };
            $.ajax({
                url: "${pageContext.request.contextPath}/api/guestbook/write",
                type: "post",
                contentType: "application/json",
                data: JSON.stringify(guestbookVo),
                dataType: "json",
                success: function (result) {
                    render(result);
                    form[0].reset();
                    form.find("textarea").val("");
                },
                error: function (XHR, status, error) {
                    console.error(status + ":" + error);
                }
            });
        }

        function showDeleteGuestbookModal(no) {
            event.preventDefault();
            var deleteModal = $("#deleteModal");
            deleteModal.find("input[name='modalNo']").val(no);
            deleteModal.find("input[name='modalPassword']").val("");
            deleteModal.modal();
        }

        function deleteGuestbook() {
            var modal = $("#deleteModal");
            var no = modal.find("input[name='modalNo']").val();
            var password = modal.find("input[name='modalPassword']").val();

            $.ajax({
                url: "${pageContext.request.contextPath}/api/guestbook/delete",
                type: "post",
                /*contentType: "application/json",*/
                data: {
                    no: no,
                    password: password
                },
                dataType: "json",
                success: function (count) {
                    //모달 숨기기
                    modal.modal("hide");
                    if (count == 1) {
                        //리스트에서 지우기
                        $("table[data-id='"+no+"']").remove();
                        alert("삭제되었습니다!");
                    } else {
                        //경고창 띄우기
                        alert("비밀번호가 틀렸습니다!");
                    }
                },
                error: function (XHR, status, error) {
                    console.error(status + ":" + error);
                }
            });
        }
    </script>
</body>

</html>

