package com.shop.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.dao.Product;
import com.shop.dao.ProductDaoImpl;
import com.shop.dao.UserDaoImpl;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LoginServlet doGet");
		System.out.println(String.valueOf(request.getSession().getAttribute(
				"error")));
		printProducts(request, response);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LoginServlet doPost");
		String paginationCurPage = "";

		// if now opened pagination page save also parameter
		if (!request.getParameter("paginationCurPage").equals("")) {
			paginationCurPage = "?page="
					+ request.getParameter("paginationCurPage");
		}

		// if at login page pressed enter button
		if (request.getParameter("entrance") != null) {
			String curLogin = request.getParameter("login");
			String curPass = request.getParameter("pass");

			// if login and pass entered
			if (!curLogin.equals("") & !curPass.equals("")) {
				request.getSession().setAttribute("login", curLogin);
				request.getSession().setAttribute("pass", curPass);
				System.out.println("login: '" + curLogin + "'" + "pass: '"
						+ curPass + "'");
				// check if user with this login and password exists
				UserDaoImpl userDao = new UserDaoImpl();
				if (userDao.checkLoginPass(curLogin, curPass)) {
					request.getSession().setAttribute("firstname",
							userDao.getFirstNameByLogin(curLogin));

					// check the role of user
					int role = userDao.getRoleByLogin(curLogin);
					System.out.println("role: " + role);
					request.getSession().setAttribute("role", role);

					switch (role) {
					case 1:
						System.out.println("User logged in.");
						response.sendRedirect("/shop" + paginationCurPage);
						break;
					case 2:
						System.out.println("Mandator logged in.");
						response.sendRedirect("/shop/mandator");
						break;
					case 3:
						System.out.println("Admin logged in.");
						break;
					}
				} else {
					System.out.println("User does not exist");
					request.getSession().setAttribute("error",
							"User does not exist. Try again");
					response.sendRedirect("/shop" + paginationCurPage);
				}
			} else { // if all fields filled in
				System.out.println("Login or pass is empty. Try again");
				request.getSession().setAttribute("error",
						"Login or pass is empty. Try again");
				response.sendRedirect("/shop" + paginationCurPage);
			}
		}
		if (request.getParameter("logout") != null) {
			request.getSession().removeAttribute("login");
			request.getSession().removeAttribute("pass");
			request.getSession().removeAttribute("firstname");
			request.getSession().removeAttribute("role");
			System.out.println("/shop" + paginationCurPage);
			response.sendRedirect("/shop" + paginationCurPage);
		}
	}

	// print products with pagiantion
	protected void printProducts(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ProductDaoImpl productDao = new ProductDaoImpl();
		int page = 1;
		int recordsPerPage = 1;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		List<Product> products = productDao.getAllProducts((page - 1)
				* recordsPerPage, recordsPerPage);
		int numOfRecords = productDao.getNumOfRecords();

		int numOfPages = (int) Math.ceil(numOfRecords * 1.0 / recordsPerPage);
		for (Product product : products) {
			System.out.println(product);
		}

		request.setAttribute("products", products);
		request.setAttribute("numOfPages", numOfPages);
		request.setAttribute("currentPage", page);
		request.setAttribute("numOfRecords", numOfRecords);
	}
}