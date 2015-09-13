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
import com.github.luiz.lamusique.daos.PlaylistDao;
import com.github.luiz.lamusique.models.Playlist;
import com.github.luiz.lamusique.daos.PlaylistAssociationDao;
import com.github.luiz.lamusique.daos.SongDao;
import com.github.luiz.lamusique.models.PlaylistAssociation;

@Controller
@Path("/playlistAssociations")
public class PlaylistAssociationController
{

   @Inject
   private PlaylistAssociationDao playlistAssociationDao;
   @Inject
   private SongDao songDao;
   @Inject
   private PlaylistDao playlistDao;
   @Inject
   private Validator validator;
   @Inject
   private Result result;

   @Get("/form")
   public void formAdd(PlaylistAssociation playlistAssociation)
   {
      result.include("playlistAssociation", playlistAssociation);
      loadFormDependencies();
   }

   private void loadFormDependencies()
   {
      result.include("songs", songDao.all());
      result.include("playlists", playlistDao.all());
   }

   @Post("")
   @Transactional
   public void save(@Valid PlaylistAssociation playlistAssociation)
   {
      validator.onErrorForwardTo(PlaylistAssociationController.class).formAdd(playlistAssociation);
      playlistAssociationDao.save(playlistAssociation);
      result.redirectTo(PlaylistAssociationController.class).list();
   }

   @Get("/{playlistAssociation.id}")
   public void formUpdate(PlaylistAssociation playlistAssociation)
   {
      result.include("playlistAssociation", playlistAssociationDao.findById(playlistAssociation.getId()));
      loadFormDependencies();
   }

   @Get("")
   public void list()
   {
      result.include("list", playlistAssociationDao.all());
   }

   //just because get is easier here. Be my guest if you want to change.
   @Get("/remove/{id}")
   @Transactional
   public void remove(Integer id)
   {
      PlaylistAssociation playlistAssociation = playlistAssociationDao.findById(id);
      playlistAssociationDao.remove(playlistAssociation);
      result.redirectTo(PlaylistAssociationController.class).list();
   }

   @Post("/{id}")
   @Transactional
   public void update(Integer id, @Valid PlaylistAssociation playlistAssociation)
   {
      playlistAssociation.setId(id);
      validator.onErrorForwardTo(PlaylistAssociationController.class).formUpdate(playlistAssociation);

      playlistAssociationDao.update(playlistAssociation);
      result.redirectTo(PlaylistAssociationController.class).list();
   }
}
