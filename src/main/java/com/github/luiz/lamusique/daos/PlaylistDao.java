package com.github.luiz.lamusique.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.inject.Inject;

import com.github.luiz.lamusique.models.Playlist;

public class PlaylistDao {

	@PersistenceContext
	private EntityManager manager;

	public List<Playlist> all() {
		return manager.createQuery("select p from Playlist p", Playlist.class).getResultList();
	}

	public void save(Playlist playlist) {
		manager.persist(playlist);
	}

	public Playlist findById(Integer id) {
		return manager.find(Playlist.class, id);
	}

	public void remove(Playlist playlist) {
		manager.remove(playlist);
	}

	public void update(Playlist playlist) {
		manager.merge(playlist);
	}

}
