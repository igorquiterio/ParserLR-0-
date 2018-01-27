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
                        <th class="h4 text-center">  </th>

                        
                    </tr>
                    <tr>
                        <th class="col-lg-4 h4"></th>
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
                                        <th class="col-lg-1 h4">Tabelas</th>
                                        <th class="col-lg-1 h4"> dos </th>
                                        <th class="col-lg-2 h4">Estados</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    
                                    <c:forEach var="u" items="${gram.estados}">
                                    <th>Estado            </th>
                                    <th>${u.indice}                 </th>
                                    <th>                  </th>
                                        
                                        <c:forEach var="p" items="${u.producao}">
                                        <tr>
                                            <td>
                                                <span class="h4">${p.naoTerminal}</span>
                                            </td>
                                            <td>
                                                <span class="h4"> -> </span>
                                            </td>
                                            <td>
                                                <span class="h4"> ${p.ProducaoInteira()}</span>
                                            </td>

                                        </tr>
                                        </c:forEach>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </td>
                        <td>
                            
                        </td>
                        <td>
                            
                        </td>

                    </tr>


                </tbody>
            </table>
        </div>
    </body>
</html>
