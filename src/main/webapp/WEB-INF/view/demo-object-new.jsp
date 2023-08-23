<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<jsp:include page="header.jsp"/>
<body>
<jsp:include page="top-bar.jsp"/>

<div class="container">
    <div class="app-demo-object-new">
        <h4>Details of a new demo object:</h4>

        <form action="/demos/new" method="POST" enctype="multipart/form-data">
            <p><strong>name:</strong></p>
            <input type="text" class="form-control" id="name" required maxlength="256" name="name">
            <p><strong>description:</strong></p>
            <input type="text" class="form-control" id="description" maxlength="1024" name="description">
            <div><button type="submit" class="btn btn-success">Submit</button> Content-Type: <strong>multipart/form-data</strong> </div>
        </form>

        <hr/>

        <form action="/demos/new" method="POST" enctype="application/x-www-form-urlencoded">
            <p><strong>name:</strong></p>
            <input type="text" class="form-control" id="name" required maxlength="256" name="name">
            <p><strong>description:</strong></p>
            <input type="text" class="form-control" id="description" maxlength="1024" name="description">
            <div><button type="submit" class="btn btn-success">Submit</button> Content-Type: <strong>application/x-www-form-urlencoded</strong> </div>
        </form>

        <hr/>
        <a class="upload-button" [title]="add by uploading file" href="/demos/upload">&#x21E7; add by uploading a file &#x1F4CE;</a>
        <hr/>

        <a class="return" title="return to list" href="/demos">&#x2190; return to list &#x1D306;</a>
    </div>
</div>




</body>
</html>