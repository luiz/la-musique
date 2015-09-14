package com.github.luiz.lamusique.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.github.luiz.lamusique.models.Artist;

public class ArtistDao {

	@PersistenceContext
	private EntityManager manager;

	public List<Artist> all() {
		return this.manager.createQuery("select a from Artist a", Artist.class).getResultList();
	}

	public void save(final Artist artist) {
		this.manager.persist(artist);
	}

	public Artist findById(final Integer id) {
		return this.manager.find(Artist.class, id);
	}

	public void remove(final Artist artist) {
		this.manager.remove(artist);
	}

	public void update(final Artist artist) {
		this.manager.merge(artist);
	}

}
