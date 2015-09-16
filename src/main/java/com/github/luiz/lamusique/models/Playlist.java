package com.github.luiz.lamusique.models;

import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "playlist")
@NamedQueries({
	@NamedQuery(name = "playlist.all", query = "select p from Playlist p"),
	@NamedQuery(name = "playlist.byId", query =
			"select p from Playlist p" +
			" join fetch p.associations assocs" +
			" join fetch assocs.song s" +
			" join fetch s.artist" +
			" where p.id = :id")
})
public class Playlist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "playlist_id")
	private Integer id;

	@Column(name = "name", nullable = false)
	@NotNull
	private String name;

	@Column(name = "cover", nullable = false)
	@NotNull
	private String cover;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "playlist")
	@OrderBy("songOrder")
	private Set<PlaylistAssociation> associations = new TreeSet<>();

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getCover() {
		return this.cover;
	}

	public void setCover(final String cover) {
		this.cover = cover;
	}

	public Iterator<Song> getSongs() {
		return this.associations.stream().map(PlaylistAssociation::getSong).iterator();
	}

	public Optional<Song> getSong(final int index) {
		return this.associations.stream().map(PlaylistAssociation::getSong).skip(index - 1).findFirst();
	}

	public boolean hasPrevious(final int index) {
		return index > 1;
	}

	public boolean hasNext(final int index) {
		return index < this.associations.size();
	}
}
