package com.shop.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.dao.Basket;

public class BasketAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BasketAddServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("BasketAddServlet doGet");

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("\nBasketAddServlet doPost");
		System.out.println("request.getParameter('productQuantity'): "
				+ request.getParameter("productQuantity"));

		System.out.println("paginationCurPage: '"
				+ request.getParameter("paginationCurPage") + "'");
		String paginationCurPage = "";
		if (!request.getParameter("paginationCurPage").equals("")) {
			paginationCurPage = "/?page="
					+ request.getParameter("paginationCurPage");
		}

		// if choosed some quantity of products
		if (Integer.valueOf(request.getParameter("productQuantity")) > 0) {
			Basket basketProduct = new Basket();
			basketProduct.setProductId(request.getParameter("productId"));
			basketProduct.setProductName(request.getParameter("productName"));
			basketProduct.setProductQuantity(request
					.getParameter("productQuantity"));

			List<Basket> basket = null;
			// if basket already created
			if (request.getSession().getAttribute("basket") != null) {
				// retrieve existed basket
				basket = (List<Basket>) request.getSession().getAttribute(
						"basket");

				// add product to busket, and if product already exist sum
				// products
				boolean addedProductToBusket = true;
				ListIterator<Basket> litr = basket.listIterator();
				while (litr.hasNext()) {
					Basket curBasket = litr.next();
					System.out.println("iter.next(): " + curBasket);

					if (curBasket.getProductId().equals(
							request.getParameter("productId"))) {
						int newQuantity = Integer.valueOf(request
								.getParameter("productQuantity"))
								+ Integer.valueOf(curBasket
										.getProductQuantity());
						basketProduct.setProductQuantity(String
								.valueOf(newQuantity));
						System.out.println("basketProduct: " + basketProduct);
						litr.remove();
						System.out.println("after removing");
						litr.add(basketProduct);
						addedProductToBusket = false;
					}
				}
				if (addedProductToBusket) {
					basket.add(basketProduct);
				}
				request.getSession().removeAttribute("basket");
				request.getSession().setAttribute("basket", basket);
			} else { // if basket already created
				basket = new ArrayList<Basket>();
				basket.add(basketProduct);
				request.getSession().setAttribute("basket", basket);
			}
			response.sendRedirect("/shop" + paginationCurPage);
		} else { // if choosed some quantity of products
			response.sendRedirect("/shop" + paginationCurPage);
		}
	}
}