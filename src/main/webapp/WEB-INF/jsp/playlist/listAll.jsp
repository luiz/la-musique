<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<section class="playlistList">
	<h2 class="playlistList-sectionTitle">Playlists</h2>
	<ol>
		<c:forEach var="playlist" items="${playlists}">
			<li class="playlistListItem">
				<figure>
					<a href="${linkTo[PlaylistController].open(playlist)}">
						<img class="playlistListItem-cover" src="<c:url value='/assets/playlist-art/${playlist.cover}'/>" alt="Playlist cover"/>
					</a>
					<figcaption class="playlistListItem-description">
						<a href="${linkTo[PlaylistController].open(playlist)}">
							${playlist.name}
						</a>
					</figcaption>
				</figure>
			</li>
		</c:forEach>
	</ol>
	<a class="playlistList-newButton" href="${linkTo[PlaylistController].newForm()}">Create new</a>
</section>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
