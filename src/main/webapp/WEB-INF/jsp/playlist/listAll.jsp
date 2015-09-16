<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<section class="playlist">
	<h2 class="playlist-sectionTitle">Playlists</h2>
	<ol>
		<c:forEach var="playlist" items="${playlists}">
			<li class="playlistItem">
				<figure>
					<a href="${linkTo[PlaylistController].open(playlist)}">
						<img class="playlistItem-cover" src="<c:url value='/assets/playlist-art/${playlist.cover}'/>" alt="Playlist cover"/>
					</a>
					<figcaption class="playlistItem-description">
						<a href="${linkTo[PlaylistController].open(playlist)}">
							${playlist.name}
						</a>
					</figcaption>
				</figure>
			</li>
		</c:forEach>
	</ol>
	<a class="playlist-newButton" href="${linkTo[PlaylistController].newForm()}">Create new</a>
</section>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
