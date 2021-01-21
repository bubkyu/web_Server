<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// request객체의 attribute 영역에 담겨있는 데이터 빼주는 거임
	// request.getAttribute("키값") : 그에 해당하는 value 반환(단, 무조건 Object타입으로)
	String name = (String)request.getAttribute("name"); // 강제 형변환 -> 무조건 object 형태이기 때문에 문자열로 바꿔주는거임. 
	String gender = (String)request.getAttribute("gender"); 
	int age = (int)request.getAttribute("age");
	String city = (String)request.getAttribute("city");
	double height = (double)request.getAttribute("height");
	String[] foods = (String[])request.getAttribute("foods");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>개인정보결과 화면</h2>
	
	<span id="name"><%=name %></span>님은
	<span id="age"><%=age %></span>살이며, 
	<span id="city"><%=city %></span>에 사는
	키는<span id="height"><%=height %></span>cm이고 
	성별은 
		<% if(gender != null){ %>
			<%if(gender.equals("M")) { %>
			<span id="gender">남자</span>입니다. <br>
			<%}else { %>
			<span id="gender">여자</span>입니다. <br>
			<%} %>
		<%}else { %>
		선택하지 않았습니다.<br>
		<%} %>
		
	좋아하는 음식은
	
	<% if(foods != null){ %>
		<ul>
			<% for(int i=0; i<foods.length;i++){ %>
				<li><%= foods[i] %></li>
			<%} %>
		</ul>
	<%}else { %>
		없습니다. 
	<%} %>
</body>
</html>