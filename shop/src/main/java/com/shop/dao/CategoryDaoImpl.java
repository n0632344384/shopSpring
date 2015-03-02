package com.shop.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

	private Connection conn;
	private static final String CID = "id";
	private static final String CNAME = "name";
	private static final String CPARENT = "parent";
//	private static final String INSERT_CATEGORY = "INSERT INTO shop.categories (name, parent) VALUES (?,?);";
	private static final String SELECT_CATEGORY = "SELECT * FROM shop.categories";
//	private static final String SELECT_CATEGORY_ID = "SELECT * FROM shop.categories WHERE id = ?;";
//	private static final String UPDATE_CATEGORY = "UPDATE shop.categories SET name=?, parent=?  WHERE id=?;";
//	private static final String DELETE_CATEGORY = "DELETE FROM shop.categories WHERE id = ?;";

	public CategoryDaoImpl() {
		this.conn = CurConn.getConnection();
	}
	
	@Override
	public Category createCategory(Category category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> getAllCategories() {
		Statement statement = null;
		ResultSet rs = null;
		List<Category> categories = null;
		try {
			statement = conn.createStatement();
			rs = statement.executeQuery(SELECT_CATEGORY);
			categories = new ArrayList<Category>();
			while (rs.next()) {
				Category category = new Category();
				category.setId(rs.getInt(CID));
				category.setName(rs.getString(CNAME));
				category.setParent(rs.getInt(CPARENT));
				categories.add(category);
			}
		} catch (SQLException e) {
			MvcException.print(e);
		}
		return categories;
	}

	@Override
	public Category getCategoryById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateCategory(Category category) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteCategory(Category category) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int createCategoryGetIdCreated(Category category) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
