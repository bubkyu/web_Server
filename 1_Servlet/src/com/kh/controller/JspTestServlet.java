package com.kh.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JspTestServlet
 */
@WebServlet("/test3.do")
public class JspTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JspTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// post 방식은 값 뽑기 전에 UTF-8으로 인코딩
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		int age = Integer.parseInt(request.getParameter("age"));
		String city = request.getParameter("city");
		double height = Double.parseDouble(request.getParameter("height"));
		
		String[] foods = request.getParameterValues("food");
		
		// >Service > Dao > sql 실행
		
		// 요청 처리 다 했다는 가정하에 응답페이지를 만들어줄꺼임.
		
		// 응답페이지를 만들어서 사용자에게 출력했던 Servlet이 하는 일을 JSP 떠넘길것(위임한다.)
		
		// 위임시 필요한 객체 : RequestDispatcher 클래스
		
		// 응답페이지(JSP)에 필요한 데이터를 담아서 보내줘야한다.
		// 담는 공간은 : request의 attribute영역(key, value 세트로 담아내야한다.)
		// request.setAttributer("키값", 벨류값);
		request.setAttribute("name", name);
		request.setAttribute("gender", gender);
		request.setAttribute("age", age);
		request.setAttribute("city", city);
		request.setAttribute("height", height);
		request.setAttribute("foods", foods);
		
		
		// 응답할 뷰 jsp 선택
		RequestDispatcher view = request.getRequestDispatcher("views/responsePage.jsp");
		
		// 포워딩
		view.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
