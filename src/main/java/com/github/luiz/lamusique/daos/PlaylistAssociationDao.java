package com.github.luiz.lamusique.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.github.luiz.lamusique.models.PlaylistAssociation;

public class PlaylistAssociationDao {

	@PersistenceContext
	private EntityManager manager;

	public List<PlaylistAssociation> all() {
		return this.manager.createQuery("select p from PlaylistAssociation p", PlaylistAssociation.class).getResultList();
	}

	public void save(final PlaylistAssociation playlistAssociation) {
		this.manager.persist(playlistAssociation);
	}

	public PlaylistAssociation findById(final Integer id) {
		return this.manager.find(PlaylistAssociation.class, id);
	}

	public void remove(final PlaylistAssociation playlistAssociation) {
		this.manager.remove(playlistAssociation);
	}

	public void update(final PlaylistAssociation playlistAssociation) {
		this.manager.merge(playlistAssociation);
	}

}
