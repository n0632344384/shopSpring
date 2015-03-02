package com.shop.dao;

import java.util.List;

public interface CategoryDao {

	// create new category
	Category createCategory(Category category);

	// get all existed categories
	List<Category> getAllCategories();

	// get category by id
	Category getCategoryById(int id);

	// update category by category and return id updated
	int updateCategory(Category category);

	// delete category by category and return id deleted
	int deleteCategory(Category category);

	// create new category and return created id
	int createCategoryGetIdCreated(Category category);
}
