package com.shop.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SetLangServlet
 */
public class SetLangServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetLangServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("SetLangServlet doGet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("SetLangServlet doPost");
		String newLang = request.getParameter("selectedLang");
		System.out.println(newLang);
		System.out.println(request.getParameter("curUrl"));
		System.out.println(request.getParameter("curUrlParam"));
		
		request.getSession().setAttribute("curShopLang", newLang);
		
		System.out.println("seted curShopLang: " + request.getSession().getAttribute("curShopLang"));
		//redirect to last url (save also url)
		response.sendRedirect("/shop" + request.getParameter("curUrl"));
	}

}
