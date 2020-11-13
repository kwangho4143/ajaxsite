<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberClient.jsp</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
	$(function(){
		$.ajax("/site1/ajax/memberList.do",{
			dataType :"json",
		}).done(function(datas){ //성공시에 done실행, 성공한 실행결과가 functino의 첫번째 인수로 들어옴
			for(i=0; i<datas.length;i++){
				$("<div>").append($("<span>").text(datas[i].id))
						  .append($("<span>").text(datas[i].name))
						  .append($("<span>").text(datas[i].tel))
						  .appendTo("#memlist"); //div태그를 #memList에 붙인다
				
			}
		});
		//등록버튼
		$("#btnSave").on("click",function(){
			$.ajax("/site1/ajax/memberInsert.do",{
				dataType:"json",
				data : $("#frm").serialize() //frm값들 하나하나읽어오겠다.
			}).done(function(datas){
				$("<div>").append($("<span>").text(datas.id))
				  .append($("<span>").text(datas.name))
				  .append($("<span>").text(datas.tel))
				  .appendTo("#memlist"); //div태그를 #memList에 붙인다
			})
		});
		
		
	}); //end function
</script>
</head>
<body>
	<h3>회원관리</h3>
	<div class="row">
		<div class="col">
			<div id="memlist"></div>
		</div>

		<div class="col">
			<form id="frm" name="frm" method="post">
				<table border="1">
					<tr>
						<th width="150">아이디</th>
						<td width="500"><input type="text" id="id" name="id"></td>
					</tr>
					<tr>
						<th width="150">이 름</th>
						<td width="500"><input type="text" id="name" name="name"></td>
					</tr>
					<tr>
						<th width="150">패스워드</th>
						<td width="500"><input type="password" id="pw" name="pw"></td>
					</tr>
					<tr>
						<th width="150">사진</th>
						<td width="500"><input type="file" id="img" name="img"></td>
					</tr>
					<tr>
						<th width="150">주소</th>
						<td width="500"><input type="text" id="address"
							name="address"></td>
					</tr>
					<tr>
						<th width="150">전화</th>
						<td width="500"><input type="text" id="tel" name="tel"></td>
					</tr>
					<tr>
						<th width="150">가입일자</th>
						<td width="500"><input type="date" id="enterdate"
							name="enterdate"></td>
					</tr>
					<tr>
						<td colspan="2" align="center">
						<input type="button" id="btnSave" value="가입하기">&nbsp;&nbsp; 
						<input type="reset" value="취 소"></td>
					</tr>
				</table>
			</form>
		</div>
		
	</div>

</body>
</html>