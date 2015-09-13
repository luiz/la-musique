package com.github.luiz.lamusique.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.inject.Inject;

import com.github.luiz.lamusique.models.Song;

public class SongDao
{

   @Inject
   private EntityManager manager;

   public List<Song> all()
   {
      return manager.createQuery("select s from Song s", Song.class).getResultList();
   }

   public void save(Song song)
   {
      manager.persist(song);
   }

   public Song findById(Integer id)
   {
      return manager.find(Song.class, id);
   }

   public void remove(Song song)
   {
      manager.remove(song);
   }

   public void update(Song song)
   {
      manager.merge(song);
   }

}
