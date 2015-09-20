<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
{
	"playlist": {
		"hasPrevious": ${playlist.hasPrevious(index)},
		"hasNext": ${playlist.hasNext(index)},
		"previousUrl": "${linkTo[PlaylistController].play(playlist, index - 1)}",
		"nextUrl": "${linkTo[PlaylistController].play(playlist, index + 1)}"
	},
	"song": {
		"index": ${index},
		"artist": "${song.artist.name}",
		"name": "${song.name}",
		"files": [
			{
				"src": "<c:url value='/assets/songs/${song.file}.mp3'/>",
				"type": "audio/mpeg"
			},
			{
				"src": "<c:url value='/assets/songs/${song.file}.ogg'/>",
				"type": "audio/ogg"
			}
		]
	}
}