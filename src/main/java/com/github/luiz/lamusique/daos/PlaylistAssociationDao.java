package com.github.luiz.lamusique.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.inject.Inject;

import com.github.luiz.lamusique.models.PlaylistAssociation;

public class PlaylistAssociationDao {

	@PersistenceContext
	private EntityManager manager;

	public List<PlaylistAssociation> all() {
		return manager.createQuery("select p from PlaylistAssociation p", PlaylistAssociation.class).getResultList();
	}

	public void save(PlaylistAssociation playlistAssociation) {
		manager.persist(playlistAssociation);
	}

	public PlaylistAssociation findById(Integer id) {
		return manager.find(PlaylistAssociation.class, id);
	}

	public void remove(PlaylistAssociation playlistAssociation) {
		manager.remove(playlistAssociation);
	}

	public void update(PlaylistAssociation playlistAssociation) {
		manager.merge(playlistAssociation);
	}

}
