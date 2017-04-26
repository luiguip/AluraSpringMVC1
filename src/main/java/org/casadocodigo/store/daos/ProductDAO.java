package org.casadocodigo.store.daos;

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
}
