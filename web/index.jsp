<%-- 
    Document   : index
    Created on : Jan 22, 2018, 3:00:46 PM
    Author     : quiterio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.servletContext.contextPath}/assets/vendor/css/bootstrap.min.css" rel="stylesheet">
        <title>Parser LR(0)</title>
    </head>
    <body>
        
        <div class="container">
            <h2 class="form-signin-heading">Parser LR0</h2>
            <form class="form-group" action="${pageContext.servletContext.contextPath}/gramatica" method="POST">
                <td>
                    <textarea class="form-control" rows="10" cols="30" name="gramatica" placeholder=" S -> A b &#x0a A -> c &#x0a A -> d" ></textarea>
                </td>
                <br>
                <br>
                <div class="text-center">
                    <button class="btn btn-lg btn-primary" type="submit">Submeter</button>
                </div>
            </form>
        </div>
        <script src="${pageContext.servletContext.contextPath}/assets/vendor/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/assets/vendor/js/bootstrap.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/assets/vendor/js/multiline-placeholder.js"></script>
    </body>
</html>
