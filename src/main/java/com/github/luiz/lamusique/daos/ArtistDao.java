package com.github.luiz.lamusique.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.inject.Inject;

import com.github.luiz.lamusique.models.Artist;

public class ArtistDao {

	@PersistenceContext
	private EntityManager manager;

	public List<Artist> all() {
		return manager.createQuery("select a from Artist a", Artist.class).getResultList();
	}

	public void save(Artist artist) {
		manager.persist(artist);
	}

	public Artist findById(Integer id) {
		return manager.find(Artist.class, id);
	}

	public void remove(Artist artist) {
		manager.remove(artist);
	}

	public void update(Artist artist) {
		manager.merge(artist);
	}

}
