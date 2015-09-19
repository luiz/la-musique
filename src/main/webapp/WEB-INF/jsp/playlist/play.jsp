<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<header class="playlist-header">
	<a href="${linkTo[PlaylistController].listAll()}" class="playlist-backButton">Home</a>
	<h2>${playlist.name}</h2>
</header>
<section class="playlist-main">
	<img src="<c:url value='/assets/playlist-art/${playlist.cover}'/>" alt="Playlist cover" class="playlist-cover"/>
	<ol class="playlistSongs">
		<c:forEach var="song" items="${playlist.songs}" varStatus="status">
			<li class="playlistSongs-item ${status.count == index ? 'playlistSongs-item--current' : ''}">
				<a href="${linkTo[PlaylistController].play(playlist, status.count)}">
					${song.artist.name} - ${song.name}
				</a>
			</li>
		</c:forEach>
	</ol>
</section>
<section class="player" data-player>
	<nav class="player-controls">
		<c:if test="${playlist.hasPrevious(index)}">
			<a href="${linkTo[PlaylistController].play(playlist, index - 1)}" class="player-control player-control--prev">Previous</a>
		</c:if>
		<c:if test="${not playlist.hasPrevious(index)}">
			<span class="player-control player-control--prev player-control--disabled">Previous</span>
		</c:if>
		<c:if test="${playlist.hasNext(index)}">
			<a href="${linkTo[PlaylistController].play(playlist, index + 1)}" class="player-control player-control--next">Next</a>
		</c:if>
		<c:if test="${not playlist.hasNext(index)}">
			<span class="player-control player-control--next player-control--disabled">Next</span>
		</c:if>
	</nav>
	<span class="player-songInfo">
		${song.artist.name} - ${song.name}
	</span>
	<audio autoplay controls class="player-nativeControls">
		Sorry, your browser does not support audio playback
		<source src="<c:url value='/assets/songs/${song.file}.mp3'/>" type="audio/mpeg">
		<source src="<c:url value='/assets/songs/${song.file}.ogg'/>" type="audio/ogg">
	</audio>
</section>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>