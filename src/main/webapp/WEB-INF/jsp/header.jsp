<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html >
<head>
  <meta charset="UTF-8">
  <title>Listing</title>

  <!-- bootstrap -->
  <link rel="stylesheet" href="<c:url value='/assets/css/bootstrap.min.css'/>">
  <link rel="stylesheet" href="<c:url value='/assets/css/bootstrap-theme.min.css'/>">

  <!-- style -->
   <link rel="stylesheet" href="<c:url value='/assets/css/index.css'/>">
  <link rel="stylesheet" href="<c:url value='/assets/css/forms.css'/>">
</head>

<body>
  
  <!-- INICIO NAV (alterar pra include)-->

  <nav class="navbar navbar-default">
    <div class="container-fluid">
      <div class="navbar-header">

        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#menu" aria-expanded="false">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="index.html">Project Name</a>
      </div>

      <div class="collapse navbar-collapse" id="menu">
        <ul class="nav navbar-nav">
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Admin <span class="caret"></span></a>
            <ul class="dropdown-menu">
	            	              <li><a href="<c:url value='/songs/form'/>"><span class="glyphicon glyphicon-plus-sign"></span> Add songs</a></li>	              
	              <li><a href="<c:url value='/songs'/>"><span class="glyphicon glyphicon-menu-hamburger"></span> List songs</a></li>
				  <li role="separator" class="divider"></li>
	              <li><a href="<c:url value='/artists/form'/>"><span class="glyphicon glyphicon-plus-sign"></span> Add artists</a></li>	              
	              <li><a href="<c:url value='/artists'/>"><span class="glyphicon glyphicon-menu-hamburger"></span> List artists</a></li>
				  <li role="separator" class="divider"></li>
	              <li><a href="<c:url value='/playlists/form'/>"><span class="glyphicon glyphicon-plus-sign"></span> Add playlists</a></li>	              
	              <li><a href="<c:url value='/playlists'/>"><span class="glyphicon glyphicon-menu-hamburger"></span> List playlists</a></li>
				  <li role="separator" class="divider"></li>
	              <li><a href="<c:url value='/playlistAssociations/form'/>"><span class="glyphicon glyphicon-plus-sign"></span> Add playlistAssociations</a></li>	              
	              <li><a href="<c:url value='/playlistAssociations'/>"><span class="glyphicon glyphicon-menu-hamburger"></span> List playlistAssociations</a></li>
				  <li role="separator" class="divider"></li>
            </ul>
          </li>
        </ul>
        
      </div>
    </div>
  </nav>

  <!-- FINAL NAV -->