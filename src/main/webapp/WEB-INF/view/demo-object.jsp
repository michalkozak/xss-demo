<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<jsp:include page="header.jsp"/>
<body>
  <jsp:include page="top-bar.jsp"/>

  <div class="container">
    <div class="app-demo-object-details">
      <h4>Details of demo object <span>${demoObject.id()}</span></h4>
      <c:if test="${demoObject != null}">
        <div class="demo-details">
          <p><strong>name:</strong> ${demoObject.name()}</p>
          <p><strong>description:</strong> ${demoObject.description()}</p>
        </div>
      </c:if>
      <hr/>
      <a class="return" title="return to list" href="/demos">&#x2190; return to list &#x1D306;</a>
    </div>
  </div>

</body>
</html>