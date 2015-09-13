<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  	        <div class="form-group">
          <label for="name">name</label>
          <div class="input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-unchecked"></i></span><input type='text' name='song.name' id='song.name' value='${song.name}'/>
          </div>
        </div>
        <div class="form-group">
          <label for="file">file</label>
          <div class="input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-unchecked"></i></span><input type='text' name='song.file' id='song.file' value='${song.file}'/>
          </div>
        </div>
        <div class="form-group">
          <label for="artist">artist</label>
          <div class="input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-unchecked"></i></span><input type='text' name='song.artist' id='song.artist' value='${song.artist}'/>
          </div>
        </div>
