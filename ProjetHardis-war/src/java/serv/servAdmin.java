/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serv;

import Entites.Adresse;
import Entites.Agence;
import Entites.Atelier;
import Entites.Communication;
import Entites.Devis;
import Entites.Disponibilite;
import Entites.Document;
import Entites.EchangeTel;
import Entites.HistoriqueDevis;
import Entites.Offre;
import Entites.UtilisateurHardis;
import Session.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;;

/**
 *
 * @author 6171217
 */
@WebServlet(name = "servAdmin", urlPatterns = {"/servAdmin"})
public class servAdmin extends HttpServlet {
    HttpSession sess =null;
    @EJB
    private AdministrateurHardisSessionLocal administrateurHardisSession;

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

            //gestionInternaute.contacterHardis(mess, email, nom, prenom, tel, "");
            message = "Message envoyé avec succès !";
        }
        request.setAttribute("message", message);
        request.setAttribute("messageErreur", messageErreur);

    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        sess= request.getSession(true);

        String message = "";

        response.setContentType("text/html;charset=UTF-8");
        String jspClient = null;
        RequestDispatcher Rd;

        String act = request.getParameter("action");
        
        if((act==null)||(act.equals("vide")))
            {   
                request.setAttribute("message","Svp remplir Login et password");
                jspClient="/Admin/Login.jsp";
            }
            else if(act.equals("LoginAdmin")){
                String login = request.getParameter("login");
                String pass = request.getParameter("pass");
                if(!(login.trim().isEmpty())||!(pass.trim().isEmpty())){
                UtilisateurHardis utilisateur=administrateurHardisSession.authentificationUtilisateurHardis(login, pass);
                    if(utilisateur!=null){
                        request.setAttribute("message","Bienvenue: "+ utilisateur.getNom());
                        sess.setAttribute("utilisateur", utilisateur);
                        jspClient="/dashboardAdmin.jsp";    
                                }   
                    else{
                        request.setAttribute("message","Entraineur non trouvé");
                        jspClient="/Admin/Login.jsp";
                    }
                }
                else{
                    request.setAttribute("message","Login ou/et password non rempli");
                    jspClient="/Admin/Login.jsp";
                }
            }
            else if(act.equals("Menu"))
            {
                jspClient="/dashboardAdmin.jsp";
                request.setAttribute("message","pas d'information");
            }
            else if(act.equals("CreerAgence"))
            {
               UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
               doActionCreerAgence(request,response);
            }
//            else{
//                jspClient="/ChoixE.jsp";
//                request.setAttribute("message","Entraineur non attribué dans une equipe");
//            }
//            }
//            else if(act.equals("insereComposition"))
//            {
//                jspClient="/ChoixE.jsp";
//                doActionInsererComposition(request,response);
//            }
//            else if(act.equals("CreerContratJouer"))
//            {
//                List<Jouer> lists= sessionEntraineur.afficherJouer();
//                request.setAttribute("listeJouer",lists);
//                jspClient="/Entraineur/AffecterJouer.jsp";
//            }
//            else if(act.equals("insereContratJouer"))
//            {
//                jspClient="/ChoixE.jsp";
//                doActionInsererContratJouer(request,response);
//            }

       
        Rd = getServletContext().getRequestDispatcher(jspClient);
        Rd.forward(request, response);
      
      
    }
    protected void doActionCreerAgence(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String nomAgence = request.getParameter("nomAgence");
        String message = null;
        if(nomAgence.trim().isEmpty()){
            message = "Erreur - Vous n'avez pas rempli tous les champs obligatoires." + "<br/><a href=\"CreerContratEntraineur.jsp\">Clique ici </a>pour accéder au formulaire de creation.";
        }
        else {
            UtilisateurHardis ut = (UtilisateurHardis) sess.getAttribute("utilisateur");
            if(ut!=null){
                administrateurHardisSession.creerAgence(nomAgence, ut);
                Agence agence = administrateurHardisSession.rechercherAgence(0, nomAgence, ut);
                String nomagence = agence.getNomAgence();
                String classe = agence.getClass().toString();
                message= " "+classe+":"+ nomagence+" créé avec succès !";
            }
            else{
                message= "Erreur information non inserée dans la base de données";
            }
            
        }
        request.setAttribute("message", message);
    }
    protected void doActionCreerAdresse(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String NumRue = request.getParameter("numRue");
        String NomRue = request.getParameter("nomRue");
        String Ville = request.getParameter("ville");
        String CodePostal= request.getParameter("cp");
        String message = null;
        if(NumRue.trim().isEmpty()||NomRue.trim().isEmpty()||Ville.trim().isEmpty()||CodePostal.trim().isEmpty()){
            message = "Erreur - Vous n'avez pas rempli tous les champs obligatoires." + "<br/><a href=\"CreerContratEntraineur.jsp\">Clique ici </a>pour accéder au formulaire de creation.";
        }
        else {
            UtilisateurHardis ut = (UtilisateurHardis) sess.getAttribute("utilisateur");
            if(ut!=null){
                int numRue = Integer.valueOf(NumRue);
                Adresse adresse = administrateurHardisSession.creerAdresse(numRue, NomRue, Ville, Ville, ut);
                String nomentite = adresse.getNomRue() ;
                String classe = adresse.getClass().toString();
                message= " "+classe+":"+ nomentite+" créé avec succès !";
            }
            else{
                message= "Erreur information non inserée dans la base de données";
            }
        }
        request.setAttribute("message", message);
    }
    protected void doActionCreerAtelier(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String nomAtelier = request.getParameter("nomAtelier");
        String message = null;
        if(nomAtelier.trim().isEmpty()){
            message = "Erreur - Vous n'avez pas rempli tous les champs obligatoires." + "<br/><a href=\"CreerContratEntraineur.jsp\">Clique ici </a>pour accéder au formulaire de creation.";
        }
        else {
            UtilisateurHardis ut = (UtilisateurHardis) sess.getAttribute("utilisateur");
            if(ut!=null){
                administrateurHardisSession.creerAtelier(nomAtelier, ut);
                Atelier atelier = administrateurHardisSession.rechercherAtelier(0, nomAtelier, ut);
                String nomentite = atelier.getNomAtelier();
                String classe = atelier.getClass().toString();
                message= " "+classe+":"+ nomentite+" créé avec succès !";
            }
            else{
                message= "Erreur information non inserée dans la base de données";
            }
        }
        request.setAttribute("message", message);
    }
    protected void doActionCreerCommunication(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String messagecom = request.getParameter("messageCommunication");
        String devis = request.getParameter("devisCommunication");
        String message = null;
        if(messagecom.trim().isEmpty()||devis.trim().isEmpty()){
            message = "Erreur - Vous n'avez pas rempli tous les champs obligatoires." + "<br/><a href=\"CreerContratEntraineur.jsp\">Clique ici </a>pour accéder au formulaire de creation.";
        }
        else {
            UtilisateurHardis ut = (UtilisateurHardis) sess.getAttribute("utilisateur");
            if(ut!=null){
                Long iddevis = Long.valueOf(devis);
                Communication communication = administrateurHardisSession.creerCommunicationHardis(messagecom, iddevis, ut);
                String classe = communication.getClass().toString();
                message= " "+classe+": envoiée avec succès !";
            }
            else{
                message= "Erreur information non inserée dans la base de données";
            }
        }
        request.setAttribute("message", message);
    }
    protected void doActionCreerDisponibilite(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String dateDebut = request.getParameter("dtdebutDisponibilite");
        String dateFin = request.getParameter("dtfinDisponibilite"); 
        String libelle = request.getParameter("libelleDisponibilite");
        String message = null;
        if(dateDebut.trim().isEmpty()||dateFin.trim().isEmpty()||libelle.trim().isEmpty()){
            message = "Erreur - Vous n'avez pas rempli tous les champs obligatoires." + "<br/><a href=\"CreerContratEntraineur.jsp\">Clique ici </a>pour accéder au formulaire de creation.";
        }
        else {
            UtilisateurHardis ut = (UtilisateurHardis) sess.getAttribute("utilisateur");
            if(ut!=null){
                Date dtdebut = Date.valueOf(dateDebut);
                Date dtfin = Date.valueOf(dateFin);
                Disponibilite disponibilite = administrateurHardisSession.creerDisponibilite(dtdebut, dtfin, libelle, ut);
                String nomentite = disponibilite.getLibelleActivite();
                String classe = disponibilite.getClass().toString();
                message= " "+classe+":"+ nomentite+" créé avec succès !";
            }
            else{
                message= "Erreur information non inserée dans la base de données";
            }
        }
        request.setAttribute("message", message);
    }
    protected void doActionCreerDocument(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String descriptif = request.getParameter("descripitfDocument"); 
        String liendoc = request.getParameter("lienDocument"); 
        String historiquedevis = request.getParameter("historiquedevisDocument");
        String message = null;
        if(descriptif.trim().isEmpty()||liendoc.trim().isEmpty()||historiquedevis.trim().isEmpty()){
            message = "Erreur - Vous n'avez pas rempli tous les champs obligatoires." + "<br/><a href=\"CreerContratEntraineur.jsp\">Clique ici </a>pour accéder au formulaire de creation.";
        }
        else {
            UtilisateurHardis ut = (UtilisateurHardis) sess.getAttribute("utilisateur");
            if(ut!=null){
                Long idhistorique = Long.valueOf(historiquedevis);
                Document document = administrateurHardisSession.creerDocument(descriptif, liendoc, idhistorique, ut);
                String nomentite = document.getDescriptif();
                String classe = document.getClass().toString();
                message= " "+classe+":"+ nomentite+" créé avec succès !";
            }
            else{
                message= "Erreur information non inserée dans la base de données";
            }
        }
        request.setAttribute("message", message);
    }
    protected void doActionCreerEchangeTel(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String text = request.getParameter("dtdebutDisponibilite");
        String devis = request.getParameter("dtdebutDisponibilite");
        String message = null;
        if(text.trim().isEmpty()||devis.trim().isEmpty()){
            message = "Erreur - Vous n'avez pas rempli tous les champs obligatoires." + "<br/><a href=\"CreerContratEntraineur.jsp\">Clique ici </a>pour accéder au formulaire de creation.";
        }
        else {
            UtilisateurHardis ut = (UtilisateurHardis) sess.getAttribute("utilisateur");
            if(ut!=null){
                Long iddevis = Long.valueOf(devis);
                EchangeTel echangetel = administrateurHardisSession.creerEchangeTel(text, iddevis, ut);
                String nomentite = echangetel.getInterlocuteur().getNom();
                String classe = echangetel.getClass().toString();
                message= " "+classe+":"+ nomentite+" créé avec succès !";
            }
            else{
                message= "Erreur information non inserée dans la base de données";
            }
        }
        request.setAttribute("message", message);
    }
    protected void doActionCreerEchangeTele(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String text = request.getParameter("dtdebutDisponibilite");
        String devis = request.getParameter("dtdebutDisponibilite");
        String message = null;
        if(text.trim().isEmpty()||devis.trim().isEmpty()){
            message = "Erreur - Vous n'avez pas rempli tous les champs obligatoires." + "<br/><a href=\"CreerContratEntraineur.jsp\">Clique ici </a>pour accéder au formulaire de creation.";
        }
        else {
            UtilisateurHardis ut = (UtilisateurHardis) sess.getAttribute("utilisateur");
            if(ut!=null){
                Long iddevis = Long.valueOf(devis);
                EchangeTel echangetel = administrateurHardisSession.creerEchangeTel(text, iddevis, ut);
                String nomentite = echangetel.getInterlocuteur().getNom();
                String classe = echangetel.getClass().toString();
                message= " "+classe+":"+ nomentite+" créé avec succès !";
            }
            else{
                message= "Erreur information non inserée dans la base de données";
            }
        }
        request.setAttribute("message", message);
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
