<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="lfn" uri="http://lamusique.com/fn" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<a href="${linkTo[PlaylistController].listAll()}">Home</a>
<h2>${playlist.name}</h2>
<img src="${lfn:playlistArt(playlist)}" alt="Playlist cover"/>
<ol>
	<c:forEach var="song" items="${playlist.songs}" varStatus="status">
		<li>
			<a href="${linkTo[PlaylistController].play(playlist, status.count)}">
				${song.artist.name} - ${song.name}
			</a>
		</li>
	</c:forEach>
</ol>
<section>
	<nav>
		<a href="${playlist.hasPrevious(index) ? linkTo[PlaylistController].play(playlist, index - 1) : 'javascript:void(0)'}">Previous</a>
		<a href="${playlist.hasNext(index) ? linkTo[PlaylistController].play(playlist, index + 1) : 'javascript:void(0)'}">Next</a>
	</nav>
	<dl>
		<dt>${song.artist.name} - ${song.name}</dt>
		<dd>
			<audio autoplay controls>
				Sorry, your browser does not support audio playback
				<source src="${lfn:songFile(song)}">
			</audio>
		</dd>
	</dl>
</section>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>