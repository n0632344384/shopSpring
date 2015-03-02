package com.shop.dao;

import java.util.List;

public interface ProductDao {

	// creating new product
	Product createProduct(Product product);

	// get all existing products
	List<Product> getAllProducts();

	// returns products for pagination
	List<Product> getAllProducts(int offset, int numOfRecords);

	// get product by id
	Product getProductById(int id);

	// update product by product and returns updated product id
	int updateProduct(Product product);

	// delete product by product and returns deleted product id
	int deleteProduct(Product product);

	// create new product and return created id
	int createProductGetIdCreated(Product product);
}