package com.github.luiz.lamusique;

import java.io.File;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.luiz.lamusique.models.Playlist;
import com.github.luiz.lamusique.models.Song;

import br.com.caelum.vraptor.environment.Environment;

@ApplicationScoped
public class Functions {

	private static final Logger LOGGER = LoggerFactory.getLogger(Functions.class);

	@Inject
	private Environment environment;

	private static File songsFolder;
	private static File playlistArtFolder;

	public void processEnvironment(@Observes final ServletContext context) {
		playlistArtFolder = new File(this.environment.get("playlistArt.folder"));
		songsFolder = new File(this.environment.get("songs.folder"));
		LOGGER.info("Initialized folders");
	}

	public static String playlistArt(final Playlist playlist) {
		return new File(playlistArtFolder, playlist.getCover()).getAbsolutePath();
	}

	public static String songFile(final Song song) {
		return new File(songsFolder, song.getFile()).getAbsolutePath();
	}
}
