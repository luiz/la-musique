package com.github.luiz.lamusique.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.github.luiz.lamusique.models.Song;

public class SongDao {

	@PersistenceContext
	private EntityManager manager;

	public List<Song> all() {
		return this.manager.createQuery("select s from Song s", Song.class).getResultList();
	}

	public void save(final Song song) {
		this.manager.persist(song);
	}

	public Song findById(final Integer id) {
		return this.manager.find(Song.class, id);
	}

	public void remove(final Song song) {
		this.manager.remove(song);
	}

	public void update(final Song song) {
		this.manager.merge(song);
	}

	public Song load(final Song song) {
		return findById(song.getId());
	}

}
