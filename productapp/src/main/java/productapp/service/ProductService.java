package productapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import productapp.dao.ProductDao;
import productapp.entity.Product;

@Service
public class ProductService {

	@Autowired
	private ProductDao productDao;
	
	public Long createProduct(Product p) {
		return this.productDao.createProduct(p);
	}
	
	public List<Product> getAllProducts(){
		return this.productDao.getAllProducts();
	}
	
	public Product getProduct(Long id) {
		return this.productDao.getProduct(id);
	}
	
	public void deleteProduct(Long id) {
		this.productDao.deleteProduct(id);
	}
	
	public void updateProduct(Product product) {
		this.productDao.updateProduct(product);
	}
}
