<%-- 
    Document   : tabelaEstados
    Created on : Jan 23, 2018, 10:53:37 AM
    Author     : quiterio
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.servletContext.contextPath}/assets/vendor/css/bootstrap.min.css" rel="stylesheet">
        <title>Estados</title>
    </head>
    <body>

        <div class="container">
            <table>
                <thead>
                    <tr>
                        <th class="h4 text-center"> Tabelas </th>

                        
                    </tr>
                    <tr>
                        <th class="col-lg-4 h4">Tabelas dos Estados</th>
                        <th class="col-lg-2 h4"></th>
                        <th class="col-lg-6 h4"></th>

                    </tr>
                </thead>
                <tbody>

                    <tr>
                        <td>
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th class="col-lg-1 h4">Nao Terminal</th>
                                        <th class="col-lg-1 h4"> -> </th>
                                        <th class="col-lg-2 h4">Produção</th>
                                    </tr>
                                </thead>
                                <tbody>
                                        
                                    <th>estado 0</th>
                                        
                                    <%--<c:forEach var="u" items="${clienteList}">--%>
                                        <tr>
                                            <td>
                                                <span class="h4">S</span>
                                            </td>
                                            <td>
                                                <span class="h4"> -> </span>
                                            </td>
                                            <td>
                                                <span class="h4">A b</span>
                                            </td>

                                        </tr>
                                        
                                        <tr>
                                            <td>
                                                <span class="h4">A</span>
                                            </td>
                                            <td>
                                                <span class="h4"> -> </span>
                                            </td>
                                            <td>
                                                <span class="h4">c </span>
                                            </td>

                                        </tr>
                                        
                                        <tr>
                                            <td>
                                                <span class="h4">A</span>
                                            </td>
                                            <td>
                                                <span class="h4"> -> </span>
                                            </td>
                                            <td>
                                                <span class="h4">d </span>
                                            </td>

                                        </tr>
                                    <%--</c:forEach>--%>
                                </tbody>
                            </table>
                        </td>
                        <td>
                            ###
                        </td>
                        <td>
                            ####
                        </td>

                    </tr>


                </tbody>
            </table>
        </div>
    </body>
</html>
