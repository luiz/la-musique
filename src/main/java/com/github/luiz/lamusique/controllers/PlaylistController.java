package com.github.luiz.lamusique.controllers;

import javax.inject.Inject;
import javax.validation.Valid;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import javax.transaction.Transactional;

import com.github.luiz.lamusique.daos.PlaylistDao;
import com.github.luiz.lamusique.models.Playlist;

@Controller
@Path("/playlists")
public class PlaylistController {

	@Inject
	private PlaylistDao playlistDao;
	@Inject
	private Validator validator;
	@Inject
	private Result result;

	@Get("/form")
	public void formAdd(Playlist playlist) {
		result.include("playlist", playlist);
	}

	@Post("")
	@Transactional
	public void save(@Valid Playlist playlist) {
		validator.onErrorForwardTo(PlaylistController.class).formAdd(playlist);
		playlistDao.save(playlist);
		result.redirectTo(PlaylistController.class).list();
	}

	@Get("/{playlist.id}")
	public void formUpdate(Playlist playlist) {
		result.include("playlist", playlistDao.findById(playlist.getId()));
	}

	@Get("")
	public void list() {
		result.include("list", playlistDao.all());
	}

	// just because get is easier here. Be my guest if you want to change.
	@Get("/remove/{id}")
	@Transactional
	public void remove(Integer id) {
		Playlist playlist = playlistDao.findById(id);
		playlistDao.remove(playlist);
		result.redirectTo(PlaylistController.class).list();
	}

	@Post("/{id}")
	@Transactional
	public void update(Integer id, @Valid Playlist playlist) {
		playlist.setId(id);
		validator.onErrorForwardTo(PlaylistController.class).formUpdate(playlist);

		playlistDao.update(playlist);
		result.redirectTo(PlaylistController.class).list();
	}
}
