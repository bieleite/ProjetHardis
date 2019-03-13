/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serv;

import Entites.Client;
import Session.ClientSessionLocal;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 6170361
 */
@WebServlet(name = "servClient", urlPatterns = {"/servClient"})
public class servClient extends HttpServlet {

    @EJB
    private ClientSessionLocal clientSession;

    
    
 protected void connexion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        String email = request.getParameter("email");
        String mdp = request.getParameter("mdp");


        String message = "";
        String messageErreur = "";
        
        if ( mdp.isEmpty() || email.isEmpty()) {
            messageErreur = "Erreur, vous n'avez pas rempli tous les champs";
        } else {

            Client c = clientSession.authentificationClient(email, mdp);
          if (c==null){
              messageErreur = "Erreur, identifiants erronnés";
          }
          else  request.setAttribute("client", c);
        }
        request.setAttribute("message", message);
        request.setAttribute("messageErreur", messageErreur);

    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         HttpSession sess = request.getSession(true);

        String message = "";

        response.setContentType("text/html;charset=UTF-8");
        String jspClient = null;
        RequestDispatcher Rd;

        String act = request.getParameter("action");
        
        if (act.equals("connexion")) {
            jspClient = "/PageAccueil.jsp";
            connexion(request, response);
}
        if (act.equals("creation")) {
            jspClient = "/PageAccueil.jsp";
            connexion(request, response);
}
        
        
        Rd = getServletContext().getRequestDispatcher(jspClient);
Rd.forward(request, response);
       
    }

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
        processRequest(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
