package br.com.fiap.apmd.filmes.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManager manager;
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("oracle");

	public static EntityManager getManager() {
		if (manager == null) manager = factory.createEntityManager();

		return manager;
	}

}
