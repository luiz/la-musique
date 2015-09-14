package com.github.luiz.lamusique.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.github.luiz.lamusique.models.Playlist;

public class PlaylistDao {

	@PersistenceContext
	private EntityManager manager;

	public List<Playlist> all() {
		return this.manager.createQuery("select p from Playlist p", Playlist.class).getResultList();
	}

	public void save(final Playlist playlist) {
		this.manager.persist(playlist);
	}

	public Playlist findById(final Integer id) {
		return this.manager.find(Playlist.class, id);
	}

	public void remove(final Playlist playlist) {
		this.manager.remove(playlist);
	}

	public void update(final Playlist playlist) {
		this.manager.merge(playlist);
	}

	public Playlist load(final Playlist playlist) {
		return findById(playlist.getId());
	}

}
