/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Gramatica;
import model.TabelaTrace;

/**
 *
 * @author quiterio
 */
@WebServlet(name = "gramatica", urlPatterns = {"/gramatica", "/gramatica/estados", "/gramatica/trace" , "/gramatica/produz_cadeia"})
public class GramaticaController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Gramatica gramatica;
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher;
        switch (request.getServletPath()) {
           
            case "/gramatica/estados":
                
                dispatcher = request.getRequestDispatcher("/view/gramatica/tabelaEstados.jsp");
                dispatcher.forward(request, response);
                
                break;
                
            case "/gramatica/produz_cadeia":
                gramatica = (Gramatica) session.getAttribute("gram");
//                gramatica.getTt().mostrarTabela();
//                System.out.println(gramatica.getTt()[1][2]);
//                if(gramatica.getTt().getAceito() == 1){
                    dispatcher = request.getRequestDispatcher("/view/gramatica/tabelaTrace.jsp");
                    dispatcher.forward(request, response);
//                }
//                else{
//                    dispatcher = request.getRequestDispatcher("/view/gramatica/naoLR0.jsp");
//                    dispatcher.forward(request, response);
//                }
                break;
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    @SuppressWarnings("empty-statement")
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Gramatica gramatica;
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher;
        
        switch (request.getServletPath()) {
           
            case "/gramatica":
                String txtInput = request.getParameter("gramatica");
                
                gramatica = new Gramatica(txtInput);
                gramatica.inicializarGramatica();
                
//                TabelaTrace tt = new TabelaTrace(gramatica, "c c c c a b $");
                
                session.setAttribute("gram", gramatica);
                response.sendRedirect(request.getContextPath() + "/gramatica/estados");
                break;
                
            case "/gramatica/trace":
                
                  gramatica = (Gramatica) session.getAttribute("gram");
                  String cadeia = request.getParameter("cadeia");
                  System.out.println(cadeia);
                  gramatica.iniciaTrace(cadeia);
                  session.setAttribute("gram", gramatica);
                  response.sendRedirect(request.getContextPath() + "/gramatica/produz_cadeia");
                
                break;
        }
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
