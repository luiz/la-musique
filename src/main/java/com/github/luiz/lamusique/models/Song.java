package com.github.luiz.lamusique.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.github.luiz.lamusique.models.Artist;

@Entity
public class Song
{

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   private String name;
   private String file;
   @ManyToOne
   private Artist artist;

   public Integer getId()
   {
      return this.id;
   }

   public void setId(Integer id)
   {
      this.id = id;
   }

   public String getName()
   {
      return this.name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public String getFile()
   {
      return this.file;
   }

   public void setFile(String file)
   {
      this.file = file;
   }

   public Artist getArtist()
   {
      return this.artist;
   }

   public void setArtist(Artist artist)
   {
      this.artist = artist;
   }
}
