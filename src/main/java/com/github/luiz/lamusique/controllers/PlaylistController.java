package com.github.luiz.lamusique.controllers;

import java.util.Optional;

import javax.inject.Inject;

import com.github.luiz.lamusique.daos.PlaylistDao;
import com.github.luiz.lamusique.models.Playlist;
import com.github.luiz.lamusique.models.Song;

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
		this.result.include("playlists", this.dao.all());
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
	public void open(final Playlist playlist) {
		this.result.forwardTo(this).play(playlist, 1);
	}

	@Get("/playlists/{playlist.id}/songs/{index}")
	public void play(final Playlist playlist, final int index) {
		final Playlist loadedPlaylist = this.dao.load(playlist);
		final Optional<Song> song = loadedPlaylist.getSong(index);
		if (!song.isPresent()) {
			this.result.notFound();
		} else {
			this.result.include("playlist", loadedPlaylist);
			this.result.include("song", song.get());
			this.result.include("index", index);
		}
	}
}
