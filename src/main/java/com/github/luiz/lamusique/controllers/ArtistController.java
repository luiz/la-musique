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

import com.github.luiz.lamusique.daos.ArtistDao;
import com.github.luiz.lamusique.models.Artist;

@Controller
@Path("/artists")
public class ArtistController {

	@Inject
	private ArtistDao artistDao;
	@Inject
	private Validator validator;
	@Inject
	private Result result;

	@Get("/form")
	public void formAdd(Artist artist) {
		result.include("artist", artist);
	}

	@Post("")
	@Transactional
	public void save(@Valid Artist artist) {
		validator.onErrorForwardTo(ArtistController.class).formAdd(artist);
		artistDao.save(artist);
		result.redirectTo(ArtistController.class).list();
	}

	@Get("/{artist.id}")
	public void formUpdate(Artist artist) {
		result.include("artist", artistDao.findById(artist.getId()));
	}

	@Get("")
	public void list() {
		result.include("list", artistDao.all());
	}

	// just because get is easier here. Be my guest if you want to change.
	@Get("/remove/{id}")
	@Transactional
	public void remove(Integer id) {
		Artist artist = artistDao.findById(id);
		artistDao.remove(artist);
		result.redirectTo(ArtistController.class).list();
	}

	@Post("/{id}")
	@Transactional
	public void update(Integer id, @Valid Artist artist) {
		artist.setId(id);
		validator.onErrorForwardTo(ArtistController.class).formUpdate(artist);

		artistDao.update(artist);
		result.redirectTo(ArtistController.class).list();
	}
}
