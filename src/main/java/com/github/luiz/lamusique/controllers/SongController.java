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

import com.github.luiz.lamusique.models.Song;
import com.github.luiz.lamusique.daos.ArtistDao;
import com.github.luiz.lamusique.daos.SongDao;
import com.github.luiz.lamusique.models.Artist;

@Controller
@Path("/songs")
public class SongController
{

   @Inject
   private SongDao songDao;
   @Inject
   private ArtistDao artistDao;
   @Inject
   private Validator validator;
   @Inject
   private Result result;

   @Get("/form")
   public void formAdd(Song song)
   {
      result.include("song", song);
      loadFormDependencies();
   }

   private void loadFormDependencies()
   {
      result.include("artists", artistDao.all());
   }

   @Post("")
   @Transactional
   public void save(@Valid Song song)
   {
      validator.onErrorForwardTo(SongController.class).formAdd(song);
      songDao.save(song);
      result.redirectTo(SongController.class).list();
   }

   @Get("/{song.id}")
   public void formUpdate(Song song)
   {
      result.include("song", songDao.findById(song.getId()));
      loadFormDependencies();
   }

   @Get("")
   public void list()
   {
      result.include("list", songDao.all());
   }

   //just because get is easier here. Be my guest if you want to change.
   @Get("/remove/{id}")
   @Transactional
   public void remove(Integer id)
   {
      Song song = songDao.findById(id);
      songDao.remove(song);
      result.redirectTo(SongController.class).list();
   }

   @Post("/{id}")
   @Transactional
   public void update(Integer id, @Valid Song song)
   {
      song.setId(id);
      validator.onErrorForwardTo(SongController.class).formUpdate(song);

      songDao.update(song);
      result.redirectTo(SongController.class).list();
   }
}
