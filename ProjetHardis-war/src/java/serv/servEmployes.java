/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serv;

import Entites.UtilisateurHardis;
import Session.VisualisateurHardisSessionLocal;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 6170361
 */
@WebServlet(name = "servEmployes", urlPatterns = {"/servEmployes"})
public class servEmployes extends HttpServlet {

    @EJB
    private VisualisateurHardisSessionLocal visualisateurHardisSession;

    
    
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
      
        String message = "";

        response.setContentType("text/html;charset=UTF-8");
        String jspClient = null;
        RequestDispatcher Rd;

        String act = request.getParameter("action");
        
        if (act.equals("majMDP")) {
        
            jspClient = "/Employe/majCompte.jsp";
         
}
        
        else if (act.equals("majProfil"))
        {
     
            
            String mail = (String) request.getParameter("mail");
            
          
            UtilisateurHardis u =  visualisateurHardisSession.rechercheUParEmailHache(mail);
            
            if (u!=null)
            {

         String mdp = request.getParameter("mdp");
        String mdpC = request.getParameter("mdpC");

         String questS = request.getParameter("qs");
         String repS = request.getParameter("rs");
         if (mdp.equals(mdpC))
             visualisateurHardisSession.majInfosProfil(u.getId(), mdp, questS, repS);
          jspClient = "/Admin/Login.jsp";
            }
            
        
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
