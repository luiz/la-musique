define('autoPlayer', ['xr', 'audioSupport', 'domLoaded'], function(xr, audioSupport, domLoaded) {
	'use strict';

	function notifyOffline(reason) {
		alert("Sorry, couldn't connect to the server to get it");
		console.log('Offline:', reason);
	}
	function updateButton(button, href, enabled) {
		if (enabled) {
			button.href = href;
			button.classList.remove('player-control--disabled');
		} else {
			button.removeAttribute('href');
			button.classList.add('player-control--disabled');
		}
	}
	function updateAudioSource(player, files) {
		for (var file of files) {
			const source = player.audio.querySelector(`[type="${file.type}"]`);
			if (source) {
				source.src = file.src;
			}
		}
		player.audio.load();
		player.audio.play();
	}
	function captionFor(song) {
		return `${song.artist} - ${song.name}`;
	}
	function updateCaption(player, song) {
		player.caption.textContent = captionFor(song);
	}
	function updateURL(player, song) {
		history.pushState({}, 'La musique', song.index);
	}
	function updatePlaylist(player, song) {
		const items = player.playlist.querySelectorAll('.playlistSongs-item');
		for (var item of items) {
			item.classList.remove('playlistSongs-item--current');
			if (item.textContent.trim() === captionFor(song)) {
				item.classList.add('playlistSongs-item--current');
			}
		}
	}
	function changeSong(player) {
		return (json) => {
			updateButton(player.prevButton, json.playlist.previousUrl, json.playlist.hasPrevious);
			updateButton(player.nextButton, json.playlist.nextUrl, json.playlist.hasNext);
			updateAudioSource(player, json.song.files);
			updateCaption(player, json.song);
			updatePlaylist(player, json.song);
			updateURL(player, json.song);
		};
	}
	function ajaxify(player, button) {
		button.addEventListener('click', function(event) {
			event.preventDefault();
			if (button.className.contains('disabled')) {
				return;
			}
			xr.get(button.href).then(changeSong(player)).catch(notifyOffline);
		});
	}
	function playIfPaused(audio) {
		audio.play();
	}
	function advanceWhenFinished(player) {
		player.audio.addEventListener('ended', function() {
			player.nextButton.click();
		});
	}
	function ajaxifyPlaylist(player) {
		const links = player.playlist.querySelectorAll('[href]');
		Array.prototype.forEach.call(links, function(link) {
			link.addEventListener('click', function(event) {
				event.preventDefault();
				xr.get(link.href).then(changeSong(player)).catch(notifyOffline);
			});
		});
	}
	function configurePlayer(playerElement) {
		const prevButton = playerElement.querySelector('.player-control--prev');
		const nextButton = playerElement.querySelector('.player-control--next');
		const caption = playerElement.querySelector('.player-songInfo');
		const audio =  playerElement.querySelector('.player-nativeControls');
		const playlist = document.querySelector('.playlistSongs');
		const player = {
			element: playerElement,
			prevButton,
			nextButton,
			caption,
			playlist,
			audio
		};
		ajaxify(player, prevButton);
		ajaxify(player, nextButton);
		ajaxifyPlaylist(player);
		playIfPaused(audio);
		advanceWhenFinished(player);
	}
	function configurePlayers() {
		const players = document.querySelectorAll('[data-player]');
		for (var player of players) {
			configurePlayer(player);
		}
	}

	if (!audioSupport) {
		console.log('Native audio playback not supported');
		return;
	}

	domLoaded(configurePlayers);
});