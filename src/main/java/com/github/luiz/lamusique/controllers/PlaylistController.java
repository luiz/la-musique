package com.github.luiz.lamusique.controllers;

import java.io.FileWriter;
import java.io.IOException;

import javax.inject.Inject;

import com.github.luiz.lamusique.daos.PlaylistDao;
import com.github.luiz.lamusique.models.Playlist;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;

@Controller
public class PlaylistController {

	@Inject
	private PlaylistDao dao;

	@Inject
	private Result result;

	@Get("/")
	public void listAll() {
		result.include("playlists", dao.all());
	}

	@Get("/playlists/new")
	public void newForm() {
		// TODO
	}

	@Post("/playlists/new")
	public void saveNew() {
		// TODO
	}

	@Get("/playlists/{playlist.id}")
	public void open(Playlist playlist) {
		// TODO
	}
}
