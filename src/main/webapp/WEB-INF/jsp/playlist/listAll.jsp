<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<link rel="icon" href="<c:url value='/assets/images/logo.ico'/>">
	<meta name="viewport" content="width=device-width">
	<title>La musique</title>
</head>
<body>
	<h1>La musique</h1>
	<main>
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
	</main>
	<footer>&copy; 2015 Luiz Corte Real</footer>
</body>
</html>
