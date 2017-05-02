package org.casadocodigo.store.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.casadocodigo.store.models.Product;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ProductDAO {

	@PersistenceContext
	private EntityManager manager;

	public void save(Product product){
		manager.persist(product);
		
	}

	public List<Product> listing() {
		return manager.createQuery("select p from  Product p", Product.class)
				.getResultList();
	}

	public Product findById(Integer id) {
		return manager.createQuery("select distinct(p) from Product p "
				+ "join fetch p.prices price where p.id = :id",Product.class)
				.setParameter("id", id)
				.getSingleResult();
	}
}
