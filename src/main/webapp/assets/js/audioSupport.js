define('audioSupport', [], function() {
	'use strict';

	function audioSupported() {
		return ('canPlayType' in document.createElement('audio'));
	}

	return audioSupported();
});