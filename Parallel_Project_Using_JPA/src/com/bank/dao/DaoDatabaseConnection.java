package com.bank.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class DaoDatabaseConnection {

	
	public EntityManager getConnection() {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("kum");
		EntityManager em=emf.createEntityManager();
		return em;
	}
}
