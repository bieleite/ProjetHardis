package serv;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entites.Offre;
import Session.InternauteSessionLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(urlPatterns = {"/servInternaute"})
public class servInternaute extends HttpServlet {

    
    @EJB
    private InternauteSessionLocal gestionInternaute;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * 
     * 
     */
    
    protected void envoiMessage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String tel = request.getParameter("tel");
        String mess = request.getParameter("message");

        String message = "";
        String messageErreur = "";
        
        if (nom.trim().isEmpty() || prenom.isEmpty() || email.isEmpty() || tel.isEmpty() || mess.isEmpty()) {
            messageErreur = "Erreur, vous n'avez pas rempli tous les champs";
        } else {

            gestionInternaute.contacterHardis(mess, email, nom, prenom, tel, "");
            message = "Message envoyé avec succès !";
        }
        request.setAttribute("message", message);
        request.setAttribute("messageErreur", messageErreur);

    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
      //  HttpSession sess = request.getSession(true);

        String message = "";

        response.setContentType("text/html;charset=UTF-8");
        String jspClient = null;
        RequestDispatcher Rd;

        String act = request.getParameter("action");
        
        if (act == null) {
            jspClient = "/PageAccueil.jsp";
            request.setAttribute("message", "");
}
        else if (act.equals("afficheOffres"))
        {
             jspClient = "/Internaute/AfficheOffres.jsp";
             List<Offre> listeO = gestionInternaute.afficheOffres();
             if (listeO==null)
                 listeO=new ArrayList();
             request.setAttribute("listeO", listeO);
             

        }
         else if (act.equals("afficheFormContact"))
        {
             jspClient = "/Internaute/FormContact.jsp";
          

        }
                 else if (act.equals("contacter"))
        {
          
             jspClient = "/Internaute/FormContact.jsp";
             envoiMessage(request, response);
             
        

        }
         else if (act.equals("connexion"))
        {
          
             jspClient = "/Internaute/login.jsp";
  
             
        

        }
          else if (act.equals("register"))
        {
          
             jspClient = "/Internaute/signup.jsp";
  
             
        

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
