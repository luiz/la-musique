define('reactiveWaveform', ['improvedNoise', 'loopVisualizer', 'requestAnimationFrame'], function(ImprovedNoise, LoopVisualizer, requestAnimationFrame) {
	'use strict';
	/**
	*
	* Loop Waveform Visualizer by Felix Turner
	* @felixturner / www.airtight.cc
	*
	* Audio Reactive Waveform via Web Audio API.
	*
	*/


	var mouseX = 0, mouseY = 0, windowHalfX = window.innerWidth / 2, windowHalfY = window.innerHeight / 2, camera, scene, renderer, material, container, toReplace, dimensions;
	var source;
	var analyser;
	var buffer;
	var audioTag;
	var dropArea;
	var audioContext;
	var source;
	var analyser;
	var xhr;
	var started = false;

	var noisePos = Math.random()*100;

	if(!hasWebGL()){
		console.log("WebGL not supported");
		return;
	}

	try {
		window.AudioContext = window.AudioContext || window.webkitAudioContext;
		audioContext = new window.AudioContext();
	} catch(e) {
		console.log("Web Audio API not supported");
		return;
	}

	audioTag = document.querySelector('.player-nativeControls');

	//init audio
	analyser = audioContext.createAnalyser();
	analyser.smoothingTimeConstant = 0.1;
	analyser.fftSize = 1024;

	//init 3D scene
	container = document.createElement('div');
	toReplace = document.querySelector('.playlist-cover');
	toReplace.parentNode.insertBefore(container, toReplace);
	container.className = 'playlist-cover';
	dimensions = getDimensionsForView();
	camera = new THREE.PerspectiveCamera(60, dimensions.width / dimensions.height, 1, 1000000);
	camera.position.z = 2000;
	scene = new THREE.Scene();
	scene.add(camera);
	renderer = new THREE.WebGLRenderer({
		antialias : false,
		sortObjects : false
	});
	renderer.setSize(dimensions.width, dimensions.height);

	container.appendChild(renderer.domElement);

	// stop the user getting a text cursor
	container.addEventListener('selectStart', function(e) {
		e.preventDefault();
	});

	//init listeners
	document.addEventListener('mousemove', onDocumentMouseMove);

	container.addEventListener('touchstart', onDocumentTouchStart, false );
	container.addEventListener('touchmove', onDocumentTouchMove, false );

	window.addEventListener('resize', onWindowResize);

	onWindowResize(null);

	LoopVisualizer.init(analyser, scene);

	// Connect audio processing graph
	source = audioContext.createMediaElementSource(audioTag);	
	source.connect(audioContext.destination);
	source.connect(analyser);

	startViz();

	function getDimensionsForView() {
		toReplace.style.display = '';
		toReplace.style.visibility = '';
		var style = {
			width: toReplace.offsetWidth,
			height: toReplace.offsetHeight
		};
		toReplace.style.display = 'none';
		toReplace.style.visibility = 'hidden';
		return style;
	}

	function onDocumentMouseMove(event) {
		mouseX = (event.clientX - windowHalfX);
		mouseY = (event.clientY - windowHalfY);
	}

	function onDocumentTouchStart( event ) {
		if ( event.touches.length == 1 ) {
			event.preventDefault();
			mouseX = event.touches[ 0 ].pageX - windowHalfX;
			mouseY = event.touches[ 0 ].pageY - windowHalfY;
		}
	}

	function onDocumentTouchMove( event ) {
		if ( event.touches.length == 1 ) {
			event.preventDefault();
			mouseX = event.touches[ 0 ].pageX - windowHalfX;
			mouseY = event.touches[ 0 ].pageY - windowHalfY;
		}
	}

	function onWindowResize(event) {
		dimensions = getDimensionsForView();
		windowHalfX = window.innerWidth / 2;
		windowHalfY = window.innerHeight / 2;
		camera.aspect = dimensions.width / dimensions.height;
		camera.updateProjectionMatrix();
		renderer.setSize(dimensions.width, dimensions.height);
	}

	function animate() {
		requestAnimationFrame(animate);
		render();
	}

	function render() {

		LoopVisualizer.update(noisePos);

		noisePos += 0.005;

		if (LoopVisualizer.vizParams.autoTilt){
			var rotRng = Math.PI /2;
			LoopVisualizer.loopHolder.rotation.x = ImprovedNoise.noise(noisePos,0,0) * rotRng;
			LoopVisualizer.loopHolder.rotation.y = ImprovedNoise.noise(noisePos ,100,0) * rotRng;

		}else{
			//mouse
			var xrot = mouseX/window.innerWidth * Math.PI*2 + Math.PI;
			var yrot = mouseY/window.innerHeight* Math.PI*2 + Math.PI;
			LoopVisualizer.loopHolder.rotation.x += (-yrot - LoopVisualizer.loopHolder.rotation.x) * 0.3;
			LoopVisualizer.loopHolder.rotation.y += (xrot - LoopVisualizer.loopHolder.rotation.y) * 0.3;
		}

		renderer.render(scene, camera);
	}

	function startViz(){
		if (!started){
			started = true;
			animate();
		}
	}

	function hasWebGL() { 
		try { 
			return !! window.WebGLRenderingContext && !! document.createElement( 'canvas' ).getContext( 'experimental-webgl' ); 
		} catch( e ) { 
			return false; 
		}
	}
});