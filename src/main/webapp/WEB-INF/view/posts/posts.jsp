<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
	.userClick {
		cursor: pointer;
	}
</style>
<script>
	$(document).ready(function(){
		console.log("document.ready");
		
		//tr에 select (class="userClick")
		$("#postsList").on("click", ".postsClick", function(){
			var posts_use = $(this).children()[4].innerText;
			var posts_no = $(this).children()[0].innerText;
			console.log(posts_no);
			/* $("#posts_no").val(posts_no);
			$("#frm").submit(); */
			
			if(posts_use == "N"){
				alert("삭제된 게시글입니다.");
				history.go(0);
			}else if(posts_use == "Y"){
				location.href = '/posts/postsDetail?posts_no='+posts_no;
			}
		});
		
		getPostsListHtml(1);
		getPostsPaginationHtml(1);
	});
	
	function getPostsListHtml(page){
		var board_id = '${boardvo.board_id}';
		var pageSize = 10;
		
		$.ajax({
			url : "/posts/postsPageListAjaxHtml",
			type : "GET",
			data : "page="+page+"&pageSize="+pageSize+"&board_id="+board_id,
			success : function(data){
				console.log(data);
				$("#postsList").html(data);
			}
		});
	}
	
	function getPostsPaginationHtml(page){
		var board_id = '${boardvo.board_id}';
		var pageSize = 10;
		
		$.ajax({
			url: "/posts/postsPaginationAjaxHtml",
			type: "GET",
			data: "page="+page+"&pageSize="+pageSize+"&board_id="+board_id,
			success: function(data){
				
				$(".pagination").html(data);
			}
		});
	}
</script>
<form id="frm" action="/posts/postsDetail" method="get">
	<input type="hidden" id="posts_no" name="posts_no"/>
</form>
<div class="row">
	<div class="col-sm-8 blog-main">
		<h2 class="sub-header">${boardvo.board_name}</h2>
		<div class="table-responsive">
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>게시글번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일시</th>
						<th style="display:none;">사용여부</th>
					</tr>
				</thead>
				<tbody id="postsList">
					<!-- postsPageListHtml.jsp -->
				</tbody>
			</table>
		</div>
		<a class="btn btn-default pull-right"
				href="/posts/addPostsForm?board_id=${boardvo.board_id}">게시글 등록</a>
		
		<div class="text-center">
			<ul class="pagination">
				<!-- postsPaginationHtml.jsp -->
			</ul>
		</div>
		<form class="navbar-form navbar-right" action="javascript:getPostsListHtml()">
			<select name="searchOption">
				<option value="posts_title">제목</option>
				<option value="posts_cnt">내용</option>
				<option value="userid">작성자</option>
			</select>
			<input type="text" class="form-control" name="postsSearch"/>
			<input type="submit" class="btn btn-default pull-right" value="검색"/>
		</form>
	</div>	
</div>
