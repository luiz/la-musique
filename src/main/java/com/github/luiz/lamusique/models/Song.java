package com.github.luiz.lamusique.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "song", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"name", "artist_id"})
})
public class Song {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "song_id")
	private Integer id;

	@Column(name = "name", nullable = false)
	@NotNull
	private String name;

	@Column(name = "file", nullable = false)
	@NotNull
	private String file;

	@ManyToOne(optional = false)
	@JoinColumn(name = "artist_id", nullable = false)
	@NotNull
	private Artist artist;

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

	public String getFile() {
		return this.file;
	}

	public void setFile(final String file) {
		this.file = file;
	}

	public Artist getArtist() {
		return this.artist;
	}

	public void setArtist(final Artist artist) {
		this.artist = artist;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.name, this.artist);
	}

	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof Song)) {
			return false;
		}
		final Song other = (Song) obj;
		return Objects.equals(this.name, other.name)
			&& Objects.equals(this.artist, other.artist);
	}
}
