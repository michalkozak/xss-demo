<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<jsp:include page="header.jsp"/>
<body>
<jsp:include page="top-bar.jsp"/>

<div class="container">
    <div class="app-demo-object-upload">
        <h4>Upload a file with details of a demo object:</h4>

        <form action="/demos/upload" method="POST" enctype="multipart/form-data">
            <input type="file" class="form-control" id="file" name="file">
            <div><button type="submit" class="btn btn-success">Submit</button> Content-Type: <strong>multipart/form-data</strong> </div>
        </form>

        <hr/>
        <a class="return" title="return to list" href="/demos">&#x2190; return to list &#x1D306;</a>
    </div>
</div>

</body>
</html>