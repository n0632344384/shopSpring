package com.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

	private Connection conn;
	private int numOfRecords;
	private static final String PID = "id";
	private static final String PNAME = "name";
	private static final String PDESCRIPTION = "description";
	private static final String PPRICE = "price";
	private static final String PIMAGE = "image";
	private static final String PCATEGORY_ID = "category_id";
	private static final String PCATEGORY = "category_name";
	private static final String PCATEGORY_PARENT = "parent";
	private static final String INSERT_PRODUCT = "INSERT INTO shop.products (name, description, price, image, category_id) VALUES (?,?,?,?,?);";
	private static final String SELECT_PRODUCT = "SELECT products.*, categories.name as category_name, categories.parent "
			+ "from shop.products "
			+ "inner join categories on products.category_id=categories.id;";
	private static final String SELECT_PRODUCT_ID = "SELECT * FROM shop.products WHERE id = ?;";
	private static final String SELECT_PRODUCT_PAGINATION = "SELECT products.*, categories.name as category_name, categories.parent from shop.products inner join categories on products.category_id=categories.id limit ?, ?;";
	// private static final String SELECT_PRODUCT_LAZY =
	// "SELECT * FROM shop.products";
	private static final String SELECT_FOUND_ROWS = "SELECT FOUND_ROWS()";
	private static final String UPDATE_PRODUCT = "UPDATE shop.products SET name=?, description=?, price=?, image=?, category_id=?  WHERE id=?;";
	private static final String DELETE_PRODUCT = "DELETE FROM shop.products WHERE id = ?;";

	public ProductDaoImpl() {
		this.conn = CurConn.getConnection();
	}

	@Override
	public Product createProduct(Product product) {
		try (PreparedStatement ps = conn.prepareStatement(INSERT_PRODUCT)) {
			ps.setString(1, product.getName());
			ps.setString(2, product.getDescription());
			ps.setInt(3, product.getPrice());
			ps.setString(4, product.getImage());
			ps.setInt(5, product.getCategory_id());
			ps.executeUpdate();
		} catch (SQLException e) {
			MvcException.print(e);
		}
		return product;
	}

	@Override
	public List<Product> getAllProducts() {
		Statement statement = null;
		ResultSet rs = null;
		List<Product> products = null;
		try {
			statement = conn.createStatement();
			rs = statement.executeQuery(SELECT_PRODUCT);
			products = new ArrayList<Product>();
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt(PID));
				product.setName(rs.getString(PNAME));
				product.setDescription(rs.getString(PDESCRIPTION));
				product.setPrice(rs.getInt(PPRICE));
				product.setImage(rs.getString(PIMAGE));
				product.setCategory_id(rs.getInt(PCATEGORY_ID));
				product.setCategory_name(rs.getString(PCATEGORY));
				product.setCategory_parent(rs.getInt(PCATEGORY_PARENT));
				products.add(product);
			}
		} catch (SQLException e) {
			MvcException.print(e);
		}
		return products;
	}

	@Override
	public List<Product> getAllProducts(int offset, int numOfRecords) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Product> products = null;
		try {
			ps = conn.prepareStatement(SELECT_PRODUCT_PAGINATION);
			ps.setInt(1, offset);
			ps.setInt(2, numOfRecords);
			rs = ps.executeQuery();
			products = new ArrayList<Product>();
			while (rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt(PID));
				product.setName(rs.getString(PNAME));
				product.setDescription(rs.getString(PDESCRIPTION));
				product.setPrice(rs.getInt(PPRICE));
				product.setImage(rs.getString(PIMAGE));
				product.setCategory_id(rs.getInt(PCATEGORY_ID));
				product.setCategory_name(rs.getString(PCATEGORY));
				product.setCategory_parent(rs.getInt(PCATEGORY_PARENT));
				products.add(product);
			}
			rs.close();
			ps.executeQuery(SELECT_PRODUCT);
			rs = ps.executeQuery(SELECT_FOUND_ROWS);
			if (rs.next())
				this.numOfRecords = rs.getInt(1);

		} catch (SQLException e) {
			MvcException.print(e);
		}
		return products;
	}

	public int getNumOfRecords() {
		return numOfRecords;
	}

	@Override
	public Product getProductById(int id) {
		Product product = new Product();
		try (PreparedStatement ps = conn.prepareStatement(SELECT_PRODUCT_ID)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			product.setId(rs.getInt(PID));
			product.setName(rs.getString(PNAME));
			product.setDescription(rs.getString(PDESCRIPTION));
			product.setPrice(rs.getInt(PPRICE));
			product.setImage(rs.getString(PIMAGE));
			product.setCategory_id(rs.getInt(PCATEGORY_ID));
		} catch (SQLException e) {
			MvcException.print(e);
		}
		return product;
	}

	@Override
	public int updateProduct(Product product) {
		int result = 0;
		try (PreparedStatement ps = conn.prepareStatement(UPDATE_PRODUCT)) {
			ps.setString(1, product.getName());
			ps.setString(2, product.getDescription());
			ps.setInt(3, product.getPrice());
			ps.setString(4, product.getImage());
			ps.setInt(5, product.getCategory_id());
			ps.setInt(6, product.getId());
			ps.executeUpdate();
			result = product.getId();
		} catch (SQLException e) {
			MvcException.print(e);
		}
		return result;
	}

	@Override
	public int deleteProduct(Product product) {
		int result = 0;
		try (PreparedStatement ps = conn.prepareStatement(DELETE_PRODUCT)) {
			ps.setInt(1, product.getId());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			MvcException.print(e);
		}
		return result;
	}

	@Override
	public int createProductGetIdCreated(Product product) {
		// TODO Auto-generated method stub
		return 0;
	}

}
