package com.shop.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.dao.Address;
import com.shop.dao.AddressDaoImpl;
import com.shop.dao.User;
import com.shop.dao.UserDaoImpl;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("RegisterServlet doGet");
		System.out.println("Current login: '"
				+ request.getSession().getAttribute("login") + "'");

		// deny registered user to register
		if (request.getSession().getAttribute("login") != null) {
			response.sendRedirect("/shop");
		} else {
			request.getRequestDispatcher("register.jsp").forward(request,
					response);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("RegisterServlet doPost");
		System.out.println("register: " + request.getParameter("register"));

		// if user pushed button register
		if (request.getParameter("register") != null) {
			String curLogin = request.getParameter("login");
			UserDaoImpl userDao = new UserDaoImpl();
			System.out.println("userDao.checkLogin(curLogin)"
					+ userDao.checkLogin(curLogin));

			// check if login exists
			if (userDao.checkLogin(curLogin)) {
				System.out
						.println("Login already exists. Please invent another.");
				request.getSession().setAttribute("error",
						"Login already exists. Please invent another.");
				response.sendRedirect("/shop/register");
			} else {
				String curPass1 = request.getParameter("pass1");
				String curPass2 = request.getParameter("pass2");
				System.out.println("curPass1: " + curPass1 + " curPass2: "
						+ curPass2);

				// if login and pass entered
				if (!curLogin.equals("") & !curPass1.equals("")
						& !curPass2.equals("")) {
					// if entered passwords not the same
					if (curPass1.equals(curPass2)) {
						String curZip = request.getParameter("zip");
						String curState = request.getParameter("state");
						String curCity = request.getParameter("city");
						String curStreet = request.getParameter("street");
						String curPhone = request.getParameter("phone");

						String curFname = request.getParameter("fname");
						String curLname = request.getParameter("lname");
						String curEmail = request.getParameter("email");
						String curAccount = request.getParameter("account");
						String curAge = request.getParameter("age");
						String curGender = request.getParameter("gender");

						System.out.println("curZip: '" + curZip
								+ "' curState: '" + curState + "' curCity: '"
								+ curCity + "' curStreet: '" + curStreet
								+ "' curPhone: '" + curPhone + "'");
						System.out.println("curFname: '" + curFname
								+ "' curLname: '" + curLname + "' curEmail: '"
								+ curEmail + "' curAccount: '" + curAccount
								+ "' curAge: '" + curAge + "' curGender: '"
								+ curGender + "'");

						// if all fields filled in
						if (curFname.equals("") | curLname.equals("")
								| curEmail.equals("") | curAccount.equals("")
								| curAge.equals("") | curGender.equals("")
								| curZip.equals("") | curState.equals("")
								| curCity.equals("") | curStreet.equals("")
								| curPhone.equals("")) {
							System.out
									.println("Some fields are empty. Fill in all fields please.");
							request.getSession()
									.setAttribute("error",
											"Some fields are empty. Fill in all fields please.");
							response.sendRedirect("/shop/register");
						} else { // if all fields filled in
							Address newAdr = new Address();
							newAdr.setZip(curZip);
							newAdr.setState(curState.toString());
							newAdr.setCity(curCity.toString());
							newAdr.setStreet(curStreet.toString());
							newAdr.setPhone(curPhone.toString());
							System.out.println(newAdr);

							AddressDaoImpl adrDao = new AddressDaoImpl();

							User newUser = new User();
							newUser.setLogin(curLogin.toString());
							newUser.setPassword(curPass1.toString());
							newUser.setFirstName(curFname.toString());
							newUser.setLastName(curLname.toString());
							newUser.setEmail(curEmail.toString());
							newUser.setAccount(Integer.valueOf(curAccount
									.toString()));
							newUser.setAge(curAge);
							newUser.setGender(curGender.equals("true") ? true
									: false);
							newUser.setRole_id(1);

							int newId = adrDao
									.createAddressGetIdCreated(newAdr);
							System.out
									.println("adrDao.createAddressGetIdCreated(newAdr): '"
											+ newId + "'");

							newUser.setAddress_id(newId);
							System.out.println(newUser);

							UserDaoImpl userDao2 = new UserDaoImpl();
							userDao2.createUser(newUser);

							request.getSession()
									.setAttribute("registrationDone",
											"Registration complited. Now you can log in.");
							response.sendRedirect("/shop/register");
						}
					} else { // if entered passwords not the same
						System.out
								.println("Passwords not the same. Try again.");
						// request.setAttribute("error",
						// "Passwords not the same. Try again");
						request.getSession().setAttribute("error",
								"Passwords not the same. Try again.");
						response.sendRedirect("/shop/register");
					}
				} else { // if login and pass entered
					System.out.println("Login or pass is empty. Try again.");
					// request.setAttribute("error",
					// "Login or pass is empty. Try again");
					request.getSession().setAttribute("error",
							"Login or pass is empty. Try again.");
					response.sendRedirect("/shop/register");
				}
			}
		} else { // (request.getParameter("register") != null)
			request.getRequestDispatcher("register.jsp").forward(request,
					response);
		}
	}
}