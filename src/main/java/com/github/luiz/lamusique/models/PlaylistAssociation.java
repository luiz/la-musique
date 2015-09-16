package com.github.luiz.lamusique.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "playlist_association", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"playlist_id", "song_id"}),
		@UniqueConstraint(columnNames = {"playlist_id", "song_order"})
})
public class PlaylistAssociation implements Comparable<PlaylistAssociation> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "playlist_association_id")
	private Integer id;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "song_id", nullable = false)
	@NotNull
	private Song song;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "playlist_id", nullable = false)
	@NotNull
	private Playlist playlist;

	@Column(name = "song_order", nullable = false)
	private int songOrder;

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public Song getSong() {
		return this.song;
	}

	public void setSong(final Song song) {
		this.song = song;
	}

	public Playlist getPlaylist() {
		return this.playlist;
	}

	public void setPlaylist(final Playlist playlist) {
		this.playlist = playlist;
	}

	public int getOrder() {
		return this.songOrder;
	}

	public void setOrder(final int order) {
		this.songOrder = order;
	}

	@Override
	public int compareTo(final PlaylistAssociation other) {
		return Integer.compare(this.songOrder, other.songOrder);
	}
}
