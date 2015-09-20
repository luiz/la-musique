define('progressBar', [], function() {
	'use strict';

	const progressBarCSS = document.createElement('link');
	progressBarCSS.rel = 'stylesheet';
	progressBarCSS.href = contextPath + 'assets/css/progressBar.css';
	document.head.appendChild(progressBarCSS);

	function create() {
		var seeking = false;
		const progressBar = document.createElement('div');
		const progress = document.createElement('span');
		const progressCaption = document.createElement('span');
		const update = function(value, text) {
			progress.style.width = value + '%';
			progressCaption.textContent = text;
		};
		const seek = function(event) {
			if (seeking) {
				const maxWidth = progressBar.offsetWidth;
				const newValue = (event.clientX - progressBar.offsetLeft) * 100 / maxWidth;
				const updateValueEvent = new Event('input');
				updateValueEvent.newValue = newValue;
				progressBar.dispatchEvent(updateValueEvent);
			}
		};
		progress.className = 'progressBar-indicator';
		progressCaption.className = 'progressBar-caption';
		progressBar.className = 'progressBar';
		progressBar.appendChild(progress);
		progressBar.appendChild(progressCaption);
		progressBar.addEventListener("mousedown", (event) => { seeking=true; seek(event) });
		progressBar.addEventListener("mousemove", (event) => seek(event));
		progressBar.addEventListener("mouseup", () => seeking=false);
		return {
			appendInto(element) {
				element.appendChild(progressBar);
			},
			on(event, callback) {
				progressBar.addEventListener(event, callback);
			},
			update
		};
	}

	return {
		create
	};
});