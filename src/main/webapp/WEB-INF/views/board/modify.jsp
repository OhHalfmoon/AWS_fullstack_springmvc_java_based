<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<jsp:include page="../includes/header.jsp" />
                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">Board Register</h1>
                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">Board List Page</h6>
                        </div>
                        <div class="card-body">
                          <form method="post" id="modifyform">
                          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
						    <div class="form-group">
						      <label for="bno">bno</label>
						      <input type="text" class="form-control" id="bno" placeholder="Enter bno" name="bno" readonly value="${board.bno}">
						    </div>                          
						    <div class="form-group">
						      <label for="title">title</label>
						      <input type="text" class="form-control" id="title" placeholder="Enter title" name="title" value="${board.title}">
						    </div>
						    <div class="form-group">
						      <label for="comment">content</label>
						      <textarea rows="10" class="form-control" id="comment" name="content" >${board.content}</textarea>						      
						    </div>				    
						    <div class="form-group">
						      <label for="writer">writer</label>
						      <input type="text" class="form-control" id="writer" placeholder="Enter writer" name="writer" readonly value="${board.writer}">
						    </div>						
						    		
						 	<div class="form-group">
						      <label for="file">file <br> <span class="btn btn-primary">파일첨부</span></label>
						      <input type="file" class="form-control d-none" id="file" name="file" multiple>
						      <div class="uploadResult mt-3">		      
							      	<ul class="list-group filenames my-3">							      	
										<c:forEach items="${board.attachs}" var="attach">
										<c:if test="${not empty board.attachs[0].uuid}">
											<li class="list-group-item" data-uuid= "${attach.uuid}" data-name="${attach.name}" data-path="${attach.uuid}" data-image="${attach.uuid}">
												<a href="/download${attach.url}&amp;thumb=off">
													<i class="far fa-file"></i>${attach.name}
												</a>
												<i class="far fa-times-circle btn-remove float-right" data-uuid= "${attach.uuid}" data-file= "${attach.url}&thumb=off"></i>
											</li>
										</c:if>										
										</c:forEach>										
									</ul>
										<ul class="nav thumbs">
											<c:forEach items="${board.attachs}" var="attach">
												<c:if test="${attach.image}">
													<li class="nav-item mx-2" data-uuid="${attach.uuid}">
														<a class="img-thumb" href="">
															<img class="img-thumbnail" src="/display${attach.url}&thumb=on" data-src="${attach.url}&thumb=off">
														</a>
													</li>																	
												</c:if>
											</c:forEach>
										</ul>
						      </div>
						    </div>
       						<sec:authorize access="isAuthernticated() and principal.username eq #board.writer">
						    <button class="btn btn-warning" formaction="modify">modify</button>
						    <button class="btn btn-danger" formaction="remove">remove</button>
						    </sec:authorize>
						    <a href="list${criteria.fullQueryString}" class="btn btn-secondary">list</a>
						    <input type="hidden" name="pageNum" value="${cri.pageNum}">					    
							<input type="hidden" name="amount" value="${cri.amount}">
							<input type="hidden" name="type" value="${cri.type}">					    
							<input type="hidden" name="keyword" value="${cri.keyword}">
						  </form>
						  </div>
                    </div>
                </div>
<style>
.ck-editor__editable[role="textbox"] {
	min-height:300px;

}
</style>                
<script>

$(function () {
	var csrfHeader = '${_csrf.headerName}';
	var csrfToken = '${_csrf.token}';	
	
	$("form button[formaction='modify']").click(function() {
		event.preventDefault();
		//title, content, writer, attachs[0].uuid
		var str = '';
		$(".filenames li").each(function(i) {
			console.log(i, this);
			var uuid = $(this).data('uuid');
			str += `
		    <input type="hidden" name="attachs[\${i}].uuid" value="\${uuid}">
		    <input type="hidden" name="attachs[\${i}].name" value="\${$(this).data('name')}">
		    <input type="hidden" name="attachs[\${i}].path" value="\${$(this).data('path')}">
		    <input type="hidden" name="attachs[\${i}].image" value="\${$(this).data('image')}">
		    `;
		})
		console.log(str);
		$("#modifyform").attr("action", "modify");
		$("#modifyform").append(str);
		$("#modifyform").submit();
	})

    function checkExtension(files) {
        const MAX_SIZE = 5 * 1024 * 1024;
        const EXCLUDE_EXT = new RegExp("(.*?)\.(js|jsp|asp|php)");

        for (let i in files) {
            if (files[i].size >= MAX_SIZE || EXCLUDE_EXT.test(files[i].name)) {
                return false;
            }
        }
        return true;
    }

    $(":file").change(function () {
        event.preventDefault();
        let files = $(":file").get(0).files;
        console.log(files);

        if (!checkExtension(files)) {
            alert("조건 불일치")
            return false;
        }

        let formData = new FormData();

        for (let i in files) {
            formData.append("files", files[i])
        }

        $.ajax({
            url : "/uploadAjax",
            processData: false,
            contentType : false,
            data : formData,
            beforeSend : function(xhr) {
				xhr.setRequestHeader(csrfHeader, csrfToken);
			},            
            method : "post",
            success : function (data) {
                console.log(data)
                $("form").get(0).reset();
                showUploadedFile(data)
            	}        	        
        	})
        })
        
		function showUploadedFile(uploadResultArr) {	
			var str = "";
			var imgStr = "";
			for(var i in uploadResultArr) {
				let obj = uploadResultArr[i];
				obj.thumb = "on";
				var params = new URLSearchParams(obj).toString();
				str += `<li class="list-group-item" data-uuid ="\${obj.uuid}" data-name ="\${obj.name}" data-path ="\${obj.path}" data-image ="\${obj.image}">
					<a href="/download?\${obj.url}"><i class="far fa-file"></i>`;
				str +=  obj.name + `</a><i class="far fa-times-circle btn-remove float-right" data-file="\${obj.url}"></i></li>`;				
				if (obj.image) {
					obj.thumb = "off";
					var params2 = new URLSearchParams(obj).toString();
					imgStr += `<li class="nav-item mx-2" data-uuid="\${obj.uuid}"><a class="img-thumb" href="">
					<img class="img-thumbnail" src="/display\${obj.url}&thumb=on" data-src="\${obj.url}"></a></li>`;										
					}	
				}
				console.log(str);

			//내부적으로 스트림 사용
			$(".uploadResult .filenames").append(str);
			$(".uploadResult .thumbs").append(imgStr);
		}        
    
		$(".uploadResult ul").on("click", ".btn-remove", function() {
			var uuid = $(this).data("uuid");	
			console.log(uuid);
					$('[data-uuid="' + uuid + '"]').remove()	
		});
		
		
		$(".uploadResult ul").on("click", ".img-thumb", function() {
			event.preventDefault();
			var param = $(this).find("img").data("src")
			$("#showImageModal").find("img").attr("src", "/display" + param).end().modal("show");
			console.log(param)
		});
    })
</script>

<script>
	ClassicEditor.create($('#comment').get(0), {
		ckfinder : {
			uploadUrl : '/ckImage.json'
		}
	});
</script>
 
 <jsp:include page="../includes/footer.jsp" />