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
                    <textarea class="form-control" rows="10" cols="30" name="gramatica" placeholder=" S -> A b &#x0a A -> b &#x0a A -> c" ></textarea>
                </td>
                <br>
                <br>
                <div class="text-center">
                    <button class="btn btn-lg btn-primary" type="submit">Submeter</button>
                </div>
            </form>
        </div>  
                <div class="text-center">
                <h3 class="form-signin-heading">Authors</h3>
                <h3 class="form-signin-heading">Gustavo Mariotto de Oliveira</h3>
                <h3 class="form-signin-heading">Igor da Costa Quitério</h3>
                Get the 
                 <a href="https://github.com/igorquiterio/ParserLR-0-">Source Code</a> here, feel free to share :)
                </div>
        <script src="${pageContext.servletContext.contextPath}/assets/vendor/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/assets/vendor/js/bootstrap.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/assets/vendor/js/multiline-placeholder.js"></script>
    </body>
</html>
