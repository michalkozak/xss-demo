<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<jsp:include page="header.jsp"/>
<body>
  <jsp:include page="top-bar.jsp"/>

  <div class="container">
    <div class="app-demo-object-list">
      <h2>Demo objects:</h2>
      <c:forEach items="${demoObjects.demos()}" var="demoObject">
        <div>
          <p>${demoObject.id()}.
            <a title="${demoObject.name()} details" href="/demos/${demoObject.id()}">${demoObject.name()}</a>
          </p>
        </div>
      </c:forEach>
    </div>
  </div>

</body>
</html>