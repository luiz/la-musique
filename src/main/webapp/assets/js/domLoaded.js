define('domLoaded', [], function() {
	'use strict';

	return function(callback) {
		if (document.readyState === 'interactive' || document.readyState === 'complete') {
			callback();
		} else {
			document.addEventListener('DOMContentLoaded', callback);
		}
	}
});