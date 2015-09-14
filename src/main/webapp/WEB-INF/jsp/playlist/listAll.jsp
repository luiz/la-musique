<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<h2>Playlists</h2>
<ol>
	<c:forEach var="playlist" items="${playlists}">
		<li>
			<figure>
				<a href="${linkTo[PlaylistController].open(playlist)}">
					<img src="${playlist.cover}" alt="Playlist cover"/>
					<figcaption>${playlist.name}</figcaption>
				</a>
			</figure>
		</li>
	</c:forEach>
</ol>
<a href="${linkTo[PlaylistController].newForm()}">Create new</a>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
