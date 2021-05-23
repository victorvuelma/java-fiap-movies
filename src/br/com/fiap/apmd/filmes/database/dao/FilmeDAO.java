package br.com.fiap.apmd.filmes.database.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.apmd.filmes.model.Filme;
import br.com.fiap.apmd.filmes.util.JPAUtil;

public class FilmeDAO {
	EntityManager manager = JPAUtil.getManager();

	public void create(Filme filme) {
		manager.getTransaction().begin();
		manager.persist(filme);
		manager.getTransaction().commit();
	}
	
	public List<Filme> buscarTodos() {
		TypedQuery<Filme> query = 
				manager.createQuery("SELECT f FROM Filme f", Filme.class);
		return query.getResultList();
	}
	
	public void apagar(Filme filme) {
		manager.getTransaction().begin();
		manager.remove(filme);
		manager.getTransaction().commit();
	}

	public void apagar(Long id) {
		apagar(buscarPorId(id));
	}

	public Filme buscarPorId(Long id) {
		return manager.find(Filme.class, id);
	}
	
	public void alterar(Filme filme) {
		manager.getTransaction().begin();
		manager.merge(filme);
		manager.getTransaction().commit();
	}

}
