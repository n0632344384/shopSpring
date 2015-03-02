package com.shop.servlets;

import java.io.IOException;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.dao.Basket;

public class BasketDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BasketDelServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\nBasketDelServlet doGet");
		List<Basket> basket = (List<Basket>) request.getSession().getAttribute(
				"basket");
		// delete from basket product by id
		ListIterator<Basket> litr = basket.listIterator();
		while (litr.hasNext()) {
			Basket curBasket = litr.next();
			if (curBasket.getProductId().equals(
					request.getParameter("productId"))) {
				litr.remove();
			}
		}
		response.sendRedirect("/shop");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\nBasketDelServlet doPost");
	}
}