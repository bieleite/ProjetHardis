package serv;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entites.Offre;
import Entites.Service;
import Entites.ServiceStandard;
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
        String mess = request.getParameter("besoin");
        String societe = request.getParameter("entreprise");

        String message = "";
        String messageErreur = "";
        
        if (nom.trim().isEmpty() || prenom.isEmpty() || email.isEmpty() || tel.isEmpty() || mess.isEmpty()) {
            messageErreur = "Erreur, vous n'avez pas rempli tous les champs";
        } else {

            gestionInternaute.contacterHardis(mess, email, nom, prenom, tel, "", societe);
            message = "Message envoyé avec succès !";
        }
        
        request.setAttribute("message", message);
        request.setAttribute("messageErreur", messageErreur);
       

    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
      //  HttpSession sess = request.getSession(true);
    HttpSession sess = request.getSession(true);
        String message = "";

        response.setContentType("text/html;charset=UTF-8");
        String jspClient = null;
        RequestDispatcher Rd;

        String act = request.getParameter("action");
        
        if (act == null) {
            jspClient = "/PageAccueil.jsp";
              List<Offre> listeO = gestionInternaute.afficheOffres();
             if (listeO==null)
                 listeO=new ArrayList();
             sess.setAttribute("listeO", listeO);
             sess.setAttribute("listeSS", new ArrayList<>());
                sess.setAttribute("listeSN", new ArrayList<>());
            request.setAttribute("message", "");
}
        else if (act.equals("afficheOffres"))
        {
             jspClient = "/PageAccueil.jsp";
           
             request.setAttribute("valeur", "offres");
             

        }
         else if (act.equals("afficheFormContact"))
        {
             jspClient = "/PageAccueil.jsp";
             request.setAttribute("valeur", "contacter");
         ;
          

        }
            else if (act.equals("contacter"))
        {
          
             jspClient = "/PageAccueil.jsp";
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
         else if (act.equals("afficheService"))
        {
          
             jspClient = "/PageAccueil.jsp";
  
             String idO = request.getParameter("idO");
             
             if (idO!=null && idO!="")
             {
                 List<Service> listeServ = gestionInternaute.recupServiceNSOffre(Long.valueOf(idO));
                 List<ServiceStandard> listeServS = gestionInternaute.recupServicesSOffre(Long.valueOf(idO)); 
                 sess.setAttribute("listeSS", listeServS);
                 sess.setAttribute("listeSN", listeServ);
             }
             
             request.setAttribute("valeur", "services");
            

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
