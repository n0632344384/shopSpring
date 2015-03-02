package com.shop.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.dao.Category;
import com.shop.dao.CategoryDaoImpl;
import com.shop.dao.Product;
import com.shop.dao.ProductDaoImpl;

/**
 * Servlet implementation class Mandator
 */
public class MandatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MandatorServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MandatorServlet doGet");

		int curRole = 0;
		if (request.getSession().getAttribute("role") != null) {
			curRole = (int) request.getSession().getAttribute("role");
		}
		System.out.println("curRole: '" + curRole + "'");

		// if mandator tries to load page
		if (curRole < 2) {
			response.sendRedirect("/shop");
		} else {
			// if mandator done some operation (pushed link in mandator menu)
			if (request.getParameter("mandator") != null) {
				String curMandatorOperation = request.getParameter("mandator");
				System.out.println("curMandatorOperation: '"
						+ curMandatorOperation + "'");
				request.setAttribute("mandator", curMandatorOperation);

				CategoryDaoImpl categoryDao = new CategoryDaoImpl();
				List<Category> categories = categoryDao.getAllCategories();
				for (Category category : categories) {
					System.out.println(category);
				}
				request.setAttribute("categories", categories);

				if (curMandatorOperation.equals("updateProduct")) {
					ProductDaoImpl productDao = new ProductDaoImpl();
					List<Product> products = productDao.getAllProducts();
					for (Product product : products) {
						System.out.println(product);
					}
					request.setAttribute("products", products);
				}
			}
			request.getRequestDispatcher("mandator.jsp").forward(request,
					response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MandatorServlet doPost");

		// if mandator logged out
		if (request.getParameter("logout") != null) {
			System.out.println("Pressed logout button");
			request.getSession().removeAttribute("login");
			request.getSession().removeAttribute("pass");
			request.getSession().removeAttribute("firstname");
			request.getSession().removeAttribute("role");
			response.sendRedirect("/shop");
		} else {
			// if mandator pressed delete product button
			if (request.getParameter("delete") != null) {
				System.out.println("Pressed delete button");
				System.out.println("ProductId to delete: '"
						+ request.getParameter("productId") + "'");
				ProductDaoImpl productDao = new ProductDaoImpl();
				productDao.deleteProduct(productDao.getProductById(Integer
						.valueOf(request.getParameter("productId"))));
				request.getSession().setAttribute(
						"delProductDone",
						"Product with id = '"
								+ request.getParameter("productId")
								+ "' removed.");
				response.sendRedirect("/shop/mandator?mandator=updateProduct");
			} else {
				// if mandator pressed update product button
				if (request.getParameter("update") != null) {
					System.out.println("Pressed update button");
					System.out.println("ProductId to update: '"
							+ request.getParameter("productId") + "'");
					ProductDaoImpl productDao = new ProductDaoImpl();
					System.out.println(request.getParameter("productName"));

					// check if updated product fields not empty

					Product updatedProduct = new Product();
					updatedProduct.setId(Integer.valueOf(request
							.getParameter("productId")));
					updatedProduct.setName(request.getParameter("productName"));
					updatedProduct.setDescription(request
							.getParameter("productDescription"));
					updatedProduct.setPrice(Integer.valueOf(request
							.getParameter("productPrice")));
					updatedProduct.setImage(request
							.getParameter("productImage"));
					updatedProduct.setCategory_id(Integer.valueOf(request
							.getParameter("productCategory")));
					System.out.println("updatedProduct: " + updatedProduct);

					productDao.updateProduct(updatedProduct);
					request.getSession().setAttribute(
							"updateProductDone",
							"Product with id = '"
									+ request.getParameter("productId")
									+ "' updated.");
					response.sendRedirect("/shop/mandator?mandator=updateProduct");
				} else { // not logged out, not delete, not update
					String newName = request.getParameter("name");
					String newDescription = request.getParameter("description");

					int newPrice = 0;
					if (!request.getParameter("price").equals("")) {
						newPrice = Integer.valueOf(request
								.getParameter("price"));
					}

					String newImage = request.getParameter("image");
					int newCategory = Integer.valueOf(request
							.getParameter("category"));

					// check if all fields are filled in and add product
					if (newName.equals("") | newDescription.equals("")
							| newPrice == 0 | newImage.equals("")) {
						System.out
								.println("Some fields are empty. Fill in all fields please.");
						request.getSession()
								.setAttribute("error",
										"Some fields are empty. Fill in all fields please.");
						response.sendRedirect("/shop/mandator?mandator=addProduct");
					} else {
						Product newProduct = new Product();
						newProduct.setName(newName);
						newProduct.setDescription(newDescription);
						newProduct.setPrice(newPrice);
						newProduct.setImage(newImage);
						newProduct.setCategory_id(newCategory);
						System.out.println(newProduct);

						ProductDaoImpl productDao = new ProductDaoImpl();
						productDao.createProduct(newProduct);

						request.getSession().setAttribute("addProductDone",
								"Product added. You can add more.");
						response.sendRedirect("/shop/mandator?mandator=addProduct");
					}
				}
			}
		}
	}
}