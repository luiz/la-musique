<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../header.jsp" %>
  <div>
    <div class ="container min-container">      
    <h2 class="basic-title">Add</h2>
      <form role="form" class="well" action="<c:url value='/artists/${artist.id}'/>" method="POST">
		<%@include file="form-inputs.jsp" %>
        <button type="submit" class="btn btn-primary">Submit</button>
      </form>	
    </div>
  </div>
<%@include file="../footer.jsp" %>
