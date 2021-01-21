package com.kh.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestPostServlet
 */
@WebServlet("/test2.do")
public class RequestPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestPostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//System.out.println("doGet 실행");
		
		// 요청시 전달값은 request의 parameter 영역 안에 담겨 있음.
		
		// POST 방식같은 경우 request 담겨잇는 값을 뽑기 전 에 인코딩을 UTF-8로 작업한 후 그리고 나서 뽑아야한다.
		
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");					// 사용자가 입력한 이름 "홍길동"	/ "" 아무것도 입력안하면 비어있는 문자열
		String gender = request.getParameter("gender");				// "M", "F"					/ 아무것도 입력안하면 null
		int age = Integer.parseInt(request.getParameter("age"));	// "20" ->20				/ 비어있게되면 NumberFormatException 오류발생
		String city = request.getParameter("city");					// "서울"						/ 아무것도 입력안하면 "" 문자열로옴
		double height = Double.parseDouble(request.getParameter("height"));//"170" -> 170.0 으로변경
		
		String[] foods = request.getParameterValues("food");		//["한식", "분식", "양식"]		/ 아무것도 체크 안하면 null 반환
		
		System.out.println("name : " + name);
		System.out.println("gender : " + gender);
		System.out.println("age : " + age);
		System.out.println("city : " + city);
		System.out.println("height : " + height);
		
		if(foods != null) {
			for(int i = 0; i<foods.length; i++) {
				System.out.println("foods[" + i + "] : " + foods[i]);
			}
		}
		
		// > Service > Dao > sql 문 실행
		// 요청처리 다 했다는 가정하에 응답페이지 만들기
		
		//1 ) 앞으로 내가 출력할 내용은 문서형태의 html 이고, 문자셋은 UTF-8 이다.
		response.setContentType("text/html; charset=UTF-8");
		
		//2 ) 요청했던 사용자와의 스트림 생성
		PrintWriter out = response.getWriter();
		
		//3 ) 스트림으로 html문서를 한줄씩 출력
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
		
		// Servlet : JAVA 코드 내에 html 을 기술
		// JSP(Java Server Page) : html 내에 JAVA코드 기술 가능
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//System.out.println("doPost 실행");
		
		doGet(request, response);
	}

}
