<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 메뉴</title>
</head>
<body>
	<%-- <img src="${pageContext.request.contextPath }/images/unnamed.jpg">--%>
	<img src="/site1/images/unnamed.jpg">
	<!-- 메뉴row 시작 -->
	<div class="row">
		<div class="col-lg-6 col-md-12">
		    <h1>Beverages</h1>
		    <p>Cappucino $3.25</p>
		    <p>Latte $3.35</p>
		    <p>Espresso $2.00</p>
		    <p>Mocha $3.50</p>
    	</div>
    	
    	<div class="col-lg-6 col-md-12">
		    <h1>돼지고기</h1>
		    <p>목살</p>
		    <p>갈비</p>
		    <p>갈빗살</p>
    	</div>
	</div><!-- 메뉴row 끝 -->
	<div class="row">
		<div class="col">
			<table class="table table-pink">
				<thead>
					<tr><th>이름</th><th>전화번호</th></tr>
				</thead>
				<tbody>
					<tr><td>바보</td><td>111</td></tr>
					<tr><td>바보2</td><td>222</td></tr>
					<tr><td>바보3</td><td>3</td></tr>
				</tbody>
			</table>
			<span class="btn btn-warning">span</span>
			<a class="btn btn-info href="#" role="button">Link</a>
			<button class="btn btn-outline-danger type="submit">Button<span class="badge badge-light">4</span></button>
			<input class="btn btn-outline-dark type="button" value="Input">
			
		</div>
	</div>
</body>
</html>