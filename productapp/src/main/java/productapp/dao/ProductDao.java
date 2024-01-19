package productapp.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import productapp.entity.Product;

@Repository
public class ProductDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Transactional
	public Long createProduct(Product product) {
		Long id = (Long) this.hibernateTemplate.save(product);
		System.out.println(id);
		return id;
	}

	public List<Product> getAllProducts(){
		List<Product> products = this.hibernateTemplate.loadAll(Product.class);
		return products;
	}
	
	@Transactional
	public void deleteProduct(Long id) {
		this.hibernateTemplate.delete(this.hibernateTemplate.get(Product.class, id));
	}
	
	public Product getProduct(Long id) {
		Product p = (Product) this.hibernateTemplate.get(Product.class, id);
		return p;
	}

	@Transactional
	public void updateProduct(Product product) {
		this.hibernateTemplate.update(product);
	}
	
}
