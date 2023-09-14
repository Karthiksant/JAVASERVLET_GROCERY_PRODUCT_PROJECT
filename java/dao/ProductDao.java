package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import dto.ProductDto;
public class ProductDao 
{
	EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("karthik");
	EntityManager entityManager=entityManagerFactory.createEntityManager();
	EntityTransaction entityTransaction=entityManager.getTransaction();

	public void save(ProductDto productDto)
	{
		entityTransaction.begin();
		entityManager.persist(productDto);
		entityTransaction.commit();
	}

}
