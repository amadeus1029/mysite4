<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>

    <link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath }/assets/css/gallery.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.css" rel="stylesheet"
          type="text/css">
    <script src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.min.js"></script>
    <script src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.js"></script>

</head>


<body>
    <div id="wrap">

        <c:import url="/WEB-INF/views/include/header.jsp"></c:import>
        <!-- //header -->
        <!-- //nav -->

        <c:import url="/WEB-INF/views/include/galleryAside.jsp"></c:import>
        <!-- //aside -->


        <div id="content">

            <div id="content-head">
                <h3>갤러리</h3>
                <div id="location">
                    <ul>
                        <li>홈</li>
                        <li>갤러리</li>
                        <li class="last">갤러리</li>
                    </ul>
                </div>
                <div class="clear"></div>
            </div>
            <!-- //content-head -->


            <div id="gallery">
                <div id="list">

                    <c:if test="${authUser ne null}">
                        <button id="btnImgUpload" onclick="showAddGalleryModal();">이미지올리기</button>
                    </c:if>
                    <div class="clear"></div>


                    <ul id="viewArea">
                        <c:forEach items="${galleryList}" var="galleryVo">
                            <li>
                                <div class="view">
                                    <img class="imgItem" src="${pageContext.request.contextPath }/upload/${galleryVo.saveName}" data-no="${galleryVo.no}" onclick="showViewGalleryModal($(this));">
                                    <div class="imgWriter">작성자: <strong>${galleryVo.userName}</strong></div>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <!-- //list -->
            </div>
            <!-- //board -->
        </div>
        <!-- //content  -->
        <div class="clear"></div>

        <c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
        <!-- //footer -->

    </div>
    <!-- //wrap -->


    <!-- 이미지등록 팝업(모달)창 -->
    <div class="modal fade" id="addModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">이미지등록</h4>
                </div>

                <form method="post" action="${pageContext.request.contextPath }/gallery/add" enctype="multipart/form-data">
                    <div class="modal-body">
                        <div class="form-group">
                            <label class="form-text">글작성</label>
                            <input id="addModalContent" type="text" name="content" value="">
                        </div>
                        <div class="form-group">
                            <label class="form-text">이미지선택</label>
                            <input id="file" type="file" name="file" value="">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn" id="btnUpload">등록</button>
                    </div>
                </form>


            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->


    <!-- 이미지보기 팝업(모달)창 -->
    <div class="modal fade" id="viewModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">이미지보기</h4>
                </div>
                <div class="modal-body">

                    <div class="formgroup">
                        <img id="viewModalImg" src=""> <!-- ajax로 처리 : 이미지출력 위치-->
                    </div>

                    <div class="formgroup">
                        <p id="viewModalContent"></p>
                    </div>

                </div>
                <form method="" action="">
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
                        <button type="button" class="btn btn-danger" id="btnDel" onclick="deleteGallery();">삭제</button>
                    </div>


                </form>

            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->


</body>

<script type="text/javascript">

    function showAddGalleryModal() {
        $("#addModal").modal();
    }

    function showViewGalleryModal(target) {
        var no = target.attr("data-no");
        var modal = $("#viewModal");
        var img = modal.find("#viewModalImg");
        var content = modal.find("#viewModalContent");
        var deleteBtn = modal.find("#btnDel");

        //일단 모달 내부 내용물 비우기
        img.attr("src","");
        img.attr("data-no","");
        content.text("");

        //ajax로 요청

        $.ajax({
            url: "${pageContext.request.contextPath}/gallery/view",
            type: "post",
            contentType: "application/json",
            data: JSON.stringify({no: no}),
            dataType: "json",
            success: function (galleryVo) {
                //모달내부 내용물 채우기
                img.attr("src","${pageContext.request.contextPath }/upload/"+galleryVo.saveName);
                img.attr("data-no",galleryVo.no);
                content.text(galleryVo.content);

                //삭제버튼 감춤 유무
                if(galleryVo.userNo == "${authUser.no}") {
                    deleteBtn.show();
                } else {
                    deleteBtn.hide();
                }

                //모달 보여주기
                modal.modal();

            },
            error: function (XHR, status, error) {
                console.error(status + ":" + error);
            }
        })
    }

    function deleteGallery() {
        var modal = $("#viewModal");
        var img = modal.find("#viewModalImg");
        var galleryNo = img.attr("data-no");

        //ajax로 요청

        $.ajax({
            url: "${pageContext.request.contextPath}/gallery/delete",
            type: "post",
            contentType: "application/json",
            data: JSON.stringify({no: galleryNo}),
            dataType: "json",
            success: function (result) {
                //삭제에 성공했다면 리스트에서 remove
                if(result) {
                    var target = $("ul#viewArea img.imgItem[data-no='"+galleryNo+"']").parents("li");
                    target.remove();
                }

                //모달 닫기
                modal.modal("hide");

            },
            error: function (XHR, status, error) {
                console.error(status + ":" + error);
            }
        })
    }

</script>


</html>

