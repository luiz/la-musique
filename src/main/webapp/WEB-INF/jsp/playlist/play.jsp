<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<a href="${linkTo[PlaylistController].listAll()}">Home</a>
<h2>${playlist.name}</h2>
<img src="<c:url value='/assets/playlist-art/${playlist.cover}'/>" alt="Playlist cover" />
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
		<c:if test="${playlist.hasPrevious(index)}">
			<a href="${linkTo[PlaylistController].play(playlist, index - 1)}">Previous</a>
		</c:if>
		<c:if test="${playlist.hasNext(index)}">
			<a href="${linkTo[PlaylistController].play(playlist, index + 1)}">Next</a>
		</c:if>
	</nav>
	<dl>
		<dt>${song.artist.name} - ${song.name}</dt>
		<dd>
			<audio autoplay controls>
				Sorry, your browser does not support audio playback
				<source src="<c:url value='/assets/songs/${song.file}.mp3'/>" type="audio/mpeg">
				<source src="<c:url value='/assets/songs/${song.file}.ogg'/>" type="audio/ogg">
			</audio>
		</dd>
	</dl>
</section>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>