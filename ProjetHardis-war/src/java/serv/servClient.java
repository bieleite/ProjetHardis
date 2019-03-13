/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serv;

import Entites.Client;
import Entites.Entreprise;
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
    
   Client clientT = null;

      String jspClient = null;
       HttpSession sess = null;
      
        
    
 protected Client connexion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        String email = request.getParameter("email");
        String mdp = request.getParameter("mdp");

       Client c = null;
        String message = "";
        String messageErreur = "";
        
        if ( mdp.isEmpty() || email.isEmpty()) {
            messageErreur = "Erreur, vous n'avez pas rempli tous les champs";
        } else {

             c = clientSession.authentificationClient(email, mdp);
          if (c==null){
              messageErreur = "Erreur, identifiants erronnés";
              jspClient = "/Internaute/FormLog.jsp";
          }
          else  {
            jspClient = "/Client/tabBord.jsp";
            
          
          }
        }
        request.setAttribute("message", message);
        request.setAttribute("messageErreur", messageErreur);
return c;
    }
 
 protected void creation(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        String email = request.getParameter("email");
        String mdp = request.getParameter("mdp");
        String mdpC = request.getParameter("mdpC");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String cp = request.getParameter("cp");
         String questS = request.getParameter("qs");
         String repS = request.getParameter("rs");
        



        String message = "";
        String messageErreur = "";
        
        if ( mdp.isEmpty() || email.isEmpty() || mdpC.isEmpty() || nom.isEmpty() || prenom.isEmpty() || cp.isEmpty() || questS.isEmpty() || repS.isEmpty()) {
            messageErreur = "Erreur, vous n'avez pas rempli tous les champs";
        } else {

            Client c = clientSession.rechercheCliParLogin(email);
          if (c==null){
              if (mdp.equals(mdpC))
              {
                  Client cli = clientSession.creerClient(nom, prenom, email, mdp, questS, repS, cp);
                  jspClient = "/Internaute/entreprise.jsp";
                  clientT = cli;
              }
              else {
                  messageErreur = "Erreur, les mots de passe sont différents";
                   jspClient = "/Internaute/signup.jsp";
              }
          }
          else  { messageErreur = "Erreur, email existant";
          jspClient = "/Internaute/signup.jsp";
          }
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
      
        RequestDispatcher Rd;

        String act = request.getParameter("action");
        
        if (act.equals("connexion")) {
          
            Client c = connexion(request, response);
            if (c!=null)
            sess.setAttribute("client", c);
}
         if (act.equals("deconnexion")) {
             Client c = (Client)sess.getAttribute("client");
            clientSession.deconnexion(c.getId());
            sess.setAttribute("client", null);
              jspClient = "/PageAccueil.jsp";
}
        if (act.equals("creation")) {
           
            creation(request, response);
}
         if (act.equals("lierE")) {
           
            String codeE = request.getParameter("codeE");
            String siret = request.getParameter("siret");
            
            Entreprise e = clientSession.rechercheEntrepriseParSiretEtMdp(siret, codeE);
            
            if (e==null)
            {
                
            }
            else {
                clientSession.majEntreprise(clientT.getId(), e.getId());
                jspClient = "/tabBord.jsp";
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
