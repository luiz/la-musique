define('player', ['progressBar', 'audioSupport', 'domLoaded'], function(ProgressBar, audioSupport, domLoaded) {
	'use strict';

	function togglePlayPauseClasses(audioTag, buttonsContainer) {
		return () => {
			if (audioTag.paused) {
				buttonsContainer.classList.remove('player-controls--playing');
				buttonsContainer.classList.add('player-controls--paused');
			} else {
				buttonsContainer.classList.add('player-controls--playing');
				buttonsContainer.classList.remove('player-controls--paused');
			}
		};
	}
	function addPlayPauseButton(buttonsContainer, audioTag) {
		const playButton = document.createElement('a');
		const pauseButton = document.createElement('a');
		playButton.textContent = 'Play';
		pauseButton.textContent = 'Pause';
		playButton.href = '#';
		pauseButton.href = '#';
		playButton.className = 'player-control player-control--play';
		pauseButton.className = 'player-control player-control--pause';
		playButton.addEventListener('click', function(event) {
			audioTag.play();
			event.preventDefault();
		});
		pauseButton.addEventListener('click', function(event) {
			audioTag.pause();
			event.preventDefault();
		});
		buttonsContainer.classList.add('player-controls--playing');
		audioTag.addEventListener('ended', togglePlayPauseClasses(audioTag, buttonsContainer));
		audioTag.addEventListener('playing', togglePlayPauseClasses(audioTag, buttonsContainer));
		audioTag.addEventListener('pause', togglePlayPauseClasses(audioTag, buttonsContainer));
		const nextButton = buttonsContainer.querySelector('.player-control--next');
		buttonsContainer.insertBefore(playButton, nextButton);
		buttonsContainer.insertBefore(document.createTextNode(' '), nextButton);
		buttonsContainer.insertBefore(pauseButton, nextButton);
		buttonsContainer.insertBefore(document.createTextNode(' '), nextButton);
	}
	function formatTime(mins, secs) {
		const pad = (n) => (n < 10 ? '0' : '') + n;
		return `${pad(mins)}:${pad(secs)}`;
	}
	function addProgressBar(playerContainer, audioTag) {
		const progressBar = ProgressBar.create();
		audioTag.addEventListener('timeupdate', function(event) {
			const percentage = audioTag.currentTime / audioTag.duration * 100;
			const curMins = Math.floor(audioTag.currentTime / 60);
			const curSecs = Math.floor(audioTag.currentTime - curMins * 60);
			const durMins = Math.floor(audioTag.duration / 60);
			const durSecs = Math.floor(audioTag.duration - durMins * 60);
			const curTime = formatTime(curMins, curSecs);
			const durTime = formatTime(durMins, durSecs);
			progressBar.update(percentage, `${curTime} / ${durTime}`);
		});
		progressBar.on('input', function(event) {
			const newTime = audioTag.duration * (event.newValue / 100);
			audioTag.currentTime = newTime;
		});
		progressBar.appendInto(playerContainer);
	}
	function configurePlayer(playerContainer) {
		const buttons = playerContainer.querySelector('.player-controls');
		const audioTag = playerContainer.querySelector('.player-nativeControls');
		addPlayPauseButton(buttons, audioTag);
		addProgressBar(playerContainer, audioTag);
		audioTag.removeAttribute('controls');
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