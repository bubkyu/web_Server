package com.kh.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestGetServlet
 */
@WebServlet("/test1.do")
public class RequestGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestGetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * Get방식으로 요청했으면 내부적으로 자동으로 이 doGet() 메소드가 호출 될 것이다.
		 * 
		 * 첫번쨰 매개변수인 HttpServletRequest request 에는 사용자가 요청한 애용들을 받아주는 용도
		 * 두번쨰 매개변수인 HttpServletResponse response 에는 요청 처리후 나중에 사용자에게 응답을 해줄 때  사용할 용도
		 * 
		 * request : 요청시 전달된 내용(사용자가 입력한 값들, 요청 전송방식, 요청한 사용자의 ip 등등)
		 * response : 요청 처리 후 응답을 할 떄 사용하게 되는 객체
		 * 
		 */
		
		/*
		 * 요청 처리하기 위해서 전달된 값들 뽑아야됨(request에 parameter 공간에 담겨있음)
		 * > request 안의 파라미터 영역안에 데이터가 (키=벨류) 형태로 담겨있음(name 속성값 = value값)
		 * 
		 * 따라서 request 로 담겨있는 전달값 뽑는 메소드
		 * > request.getParameter("키값") : 그에 해당하는 value 값 반환 (단, 무조건 String 으로 반환) "20" -파싱작업-> 20
		 * > reqeust.getParameterValues("키값") : 그에 해당하는 value 값들이 배열(String[])에 담겨 반환
		 */
		
		String name = request.getParameter("name");							// "홍길동" / 입력 x -> ""
		String gender = request.getParameter("gender");						// "M" or "F" / 둘다 체크 x -> null 
		int age = Integer.parseInt(request.getParameter("age"));			// "20" --> 20 / 입력 안할경우 "" -> 오류 // 문자열로 뽑혀서 데이터의 자료형을 변환해주어야한다.
		String city = request.getParameter("city");							// "서울" "전라도" 등등 
		double height = Double.parseDouble(request.getParameter("height")); // "170" -파싱->170.0
		
		String [] foods = request.getParameterValues("food");				//["한식", "양식", "분식"] / 아무것도 체크x -> null
		
		System.out.println("name : " + name);
		System.out.println("gender : " + gender);
		System.out.println("age : " + age);
		System.out.println("city : " + city);
		System.out.println("height : " + height);
		
		if(foods != null) {
			for(int i = 0; i<foods.length; i++) {
				System.out.println("foods[" + i + "] : " + foods[i]);
			}
		}else {
			System.out.println("좋아하는 음식 없음");
		}
		
		// *** 자바코드 수정했으면 무조건 서버 리스타드(재실행) 해야함.
		
		// 요청 처리 : > Service > Dao > sql실행
		//int result = new MemberService().inserMember(name, gender, age, city, height, foods);
		
		//if(result > 0) {	// 성공
			
		//}else {	// 실패
			
		//}
		// 요청 처리 다 했다는 가정 하에 응답페이지 만들기.
		
		// Servlet이 하는 역할은 요청 처리 다 하고 나서 그에 해당하는 응답페이지를 "만들어서" 사용자에게 출력하는 역할 함.
		// 즉, 여기 java 코드 내에 사용자가 보게 될 응답 html 만드는 구문을 작성해야됨.
		
		// 장점 : Java 코드 내에 작성하기 때문에 반복문이나, 조건문, 유용한 메소드 같은걸 활용할 수 있다.
		// 단점 : 복잡하다. 혹시나 html 을 수정 하고자 했을 때, 수정을 JAVA 코드내에서 자바코드를 수정하는 거기 때문에 
		//				다시 반영시키고자 한다면 서버 재실행(리스타드) 해야한다.
		
		
		// * response 객체를 통해 사용자에게 html (응답화면) 전달
		
		// 1) 이제부터 내가 전달한 내용은 문서형태의 html이고 문자셋은 UTF-8 이라는걸 지정
		response.setContentType("text/html; charset=UTF-8");
		
		// 2 ) 응답하고자 하는 사용자(요청했던 사용자)와의 스트림(클라이언트와의 길) 생성
		PrintWriter out = response.getWriter();
		
		// 3 ) 그 스트림(out)을 통해 html문서를 한줄씩 출력
		out.println("<html>");
		out.println("<head>");
		out.println("<title>개인정보출력화면</title>");
		out.println("<style>");
		out.println("h2{color:red;}");
		out.println("#name{color:orange; font-weight:bold;}");
		out.println("#age{color:yellow;}");
		out.println("#city{color:blue;}");
		out.println("#height{color:green;}");
		out.println("#gender{color:purple;}");
		out.println("</style>");
		out.println("</head>");
		out.println("<body>");
		
		out.println("<h2>개인정보결과화면(GET) 화면</h2>");
		
		//out.println("<span id='name'>" + name + "</span>");
		out.printf("<span id='name'>%s</span>님은", name);
		out.printf("<span id='age'>%d</span>살이며, ", age);
		out.printf("<span id='city'>%s</span>에 사는, ", city);
		out.printf("키는 <span id='height'>%.1f</spang>cm이고", height);
		
		out.print("성별은");
		//자바코드내에 html문을 만들고 있기 때문에 조건문 및 메소드 활용 가능
		if(gender != null) {
			
			if(gender.equals("M")) {
				out.print("<span id='gender'>남자</span>입니다.<br>");
			}else{
				out.print("<span id='gender'>여자</span>입니다.<br>");
			}
		}else {
			out.print("선택을 안했습니다.<br>");
		}
		
		
		out.print("좋아하는 음식은 ");
		if(foods != null) {
			
			out.print("<ul>");
			for(int i=0; i<foods.length; i++) {
				out.printf("<li>%s</li>", foods[i]);
			}
			out.print("</ul>");		
			
		}else {
			out.print("좋아하는 음식 없습니다.");
		}
		
		out.println("</body>");
		out.println("</html>");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}






