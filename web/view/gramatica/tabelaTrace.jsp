<%-- 
    Document   : tabelaTrace
    Created on : Jan 30, 2018, 4:57:56 PM
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

                                    <table class="table table-striped table-bordered">
                                        <thead>
                                            <tr>
                                                <th class="col-lg-1 h4">Indice</th>
                                                <th class="col-lg-5 h4">Pilha</th>
                                                <th class="col-lg-5 h4">cadeia</th>
                                                <th class="col-lg-1 h4 text-center">Ação</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="i" items="${gram.tt.tabelaTrace}">
                                                <tr>
                                                    <td>
                                                        <span class="h4"><c:out value="${i[0]}"/></span>
                                                    </td>
                                                    <td>
                                                        <span class="h4"><c:out value="${i[1]}"/></span>
                                                    </td>
                                                    <td>
                                                        <span class="h4"><c:out value="${i[2]}"/></span>
                                                    </td>
                                                    <td>
                                                        <span class="h4"><c:out value="${i[3]}"/></span>
                                                    </td>

                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>

                                    <c:choose>
                                        <c:when test="${gram.tt.aceito == 1}">
                                            <div class="text-center">
                                                <h2 class="text-center"> CADEIA ACEITA</h2>

                                                <br>
                                                <br>
                                            </div>
                                        </c:when>    
                                        <c:otherwise>
                                            <div class="text-center">
                                                <h2 class="text-center"> CADEIA NÃO ACEITA</h2>

                                                <br>
                                                <br>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>

                                    <div class="text-center"> 
                                        <a class="btn btn-default" href="${pageContext.servletContext.contextPath}/">Pagina Inicial</a>  
                                        <a class="btn btn-default" href="${pageContext.servletContext.contextPath}/gramatica/estados">Voltar</a>  
                                    </div>
                                    </div>

                                    </body>
                                    </html>
