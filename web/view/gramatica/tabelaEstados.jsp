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
                        <th class="col-lg-1 h4"></th>
                        <th class="col-lg-7 h4"></th>

                    </tr>
                </thead>
                <tbody>

                    <tr>
                        <td>
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th class="col-lg-1 h4">Tabelas</th>
                                        <th class="col-lg-1 h4"> dos </th>
                                        <th class="col-lg-2 h4">Estados</th>
                                    </tr>
                                </thead>
                                <tbody>

                                    <c:forEach var="u" items="${gram.estados}"> 
                                        <tr class="bg-info">
                                            <th>Estado            </th>
                                            <th>${u.indice}                 </th>
                                            <th>                  </th>
                                        </tr>    
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
                                        <tr><th></th><th></th><th></th></tr>
                                            </c:forEach>

                                </tbody>
                            </table>
                        </td>
                        <td>
                        </td>
                        <td>
                            <table class="table table-striped table-bordered">
                                <thead>
                                    <tr><h3>Tabela LR0</h3></tr>
                    <tr  class="table-dark">
                        <th class="col-lg-1 h4 text-center">Estado</th>
                        <th class="col-lg-6 h4 text-center" colspan="${gram.lr0.maxColuna}">Ação</th>
                    </tr>
                    <tr>
                        <th></th>

                        <c:forEach var="termo" items="${gram.lr0.indice}">
                            <th>${termo}</th>
                            </c:forEach>

                    </tr>
                    </thead>
                <tbody>
                    <c:forEach var="est" items="${gram.estados}">
                        <tr>
                            <th>${est.indice}</th>

                            <c:forEach var="unidade" items="${gram.lr0.tabela[est.indice]}">
                                <th>
                                    <span class="h4">${unidade}<span>
                                            </th> 
                                        </c:forEach>

                                        </tr>    
                                    </c:forEach>
                                    </tbody>
                                    </table>
                                    </td>

                                    </tr>


                                    </tbody>
                                    </table>
                                    <c:choose>
                                        <c:when test="${gram.lr0.aceita == 1}">
                                            
                                            <div class="center-block">                    
                                                <form class="form-inline" action="${pageContext.servletContext.contextPath}/gramatica/trace" method="POST">                        
                                                        
                                                    <div class="input-group mb-4 mr-sm-4 mb-sm-0">
                                                        <input class="form-control" type="text" name="cadeia" placeholder="cadeia $" required autofocus>

                                                    </div>
                                                    <button class="btn btn-primary" type="submit">Submeter</button>
                                                    <a class="btn btn-default" href="${pageContext.servletContext.contextPath}/">Voltar</a>
                                                </form>                        
                                            </div>
                                        </c:when>    
                                        <c:otherwise>
                                            <div class="text-center">
                                                <h2 class="text-center"> A GRAMATICA NÃO É LR0</h2>
                                                
                                                <br>
                                                <br>
                                                <a class="btn btn-default" href="${pageContext.servletContext.contextPath}/">Voltar</a>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>



                                    </div>

                                    </body>
                                    </html>
