/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serv;

import Entites.Adresse;
import Entites.Agence;
import Entites.Atelier;
import Entites.Client;
import Entites.Communication;
import Entites.ContactMail;
import Entites.Devis;
import Entites.Disponibilite;
import Entites.Document;
import Entites.EchangeTel;
import Entites.Entreprise;
import Entites.Expertise;
import Entites.FacturationFrais;
import Entites.Facture;
import Entites.Helpers;
import Entites.HistoriqueDevis;
import Entites.HistoriqueEtats;
import Entites.HistoriqueTraitement;
import Entites.LieuIntervention;
import Entites.Livrable;
import Entites.NiveauHabilitation;
import Entites.Notification;
import Entites.Offre;
import Entites.Offre_Profil_Util_CV;
import Entites.ProfilMetier;
import Entites.ProfilTechnique;
import Entites.SendMail;
import Entites.Service;
import Entites.ServiceStandard;
import Entites.Statut;
import Entites.TypeDoc;
import Entites.TypeService;
import Entites.TypeUtilisateur;
import Entites.UtilisateurHardis;
import Facades.ClientFacade;
import Session.GestionnaireHardisSessionLocal;
import Session.VisualisateurHardisSessionLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "servEmployes", urlPatterns = {"/servEmployes"})
public class servEmployes extends HttpServlet {

    @EJB
    private GestionnaireHardisSessionLocal gestionnaireHardisSession;
HttpSession sess =null;
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
      
        String message = "";

        response.setContentType("text/html;charset=UTF-8");
        String jspClient = null;
        
sess= request.getSession(true);

      

        response.setContentType("text/html;charset=UTF-8");
        
        

        
        String act = request.getParameter("action");
        if((act==null)||(act.equals("vide")))
            {   
                request.setAttribute("message","");
                jspClient="/Employe/login.jsp";
            }
     
        else if (act.equals("deconnexion"))
        {
              UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("utilisateur");
              gestionnaireHardisSession.signout(utilisateur.getId());
          sess.invalidate();
               jspClient = "/Admin/Login.jsp";
           
        }
            else if(act.equals("LoginAdmin")){
                String login = request.getParameter("login");
                String pass = request.getParameter("pass");
                if(!(login.trim().isEmpty())||!(pass.trim().isEmpty())){
                UtilisateurHardis utilisateur = gestionnaireHardisSession.authentificationUtilisateurHardis(login, pass);
                    if(utilisateur!=null){
                        request.setAttribute("message","Bienvenue: "+ utilisateur.getNom());
                        sess.setAttribute("utilisateur", utilisateur);
                        List<Communication> listeCommunication= new ArrayList<>();
                        listeCommunication= gestionnaireHardisSession.rechercherCommunication(0, utilisateur.getId(), utilisateur);
                        List<Notification> listeNotif =new ArrayList<>();
                        listeNotif = gestionnaireHardisSession.getNotifsAdmin(utilisateur);
                        List<Devis> listeDevis = new ArrayList<>();
                        List<HistoriqueTraitement> ht = gestionnaireHardisSession.rechercherHistoriqueTraitementParConsultant(utilisateur.getId(), utilisateur);
                        if (!ht.isEmpty()){
                            for (HistoriqueTraitement lesht: ht){
                                Devis dev = lesht.getDevis();
                                listeDevis.add(dev);
                            }
                        }
                        List<Client> listeClient = new ArrayList<>();
                        
                        if (!listeDevis.isEmpty()){
                            for (Devis dev: listeDevis){
                                Client client = dev.getClient();
                                listeClient.add(client);
                            }
                        }
                        List<Entreprise> listeEntreprise = new ArrayList<>();
                        listeEntreprise = gestionnaireHardisSession.listEntreprise();
                        List<UtilisateurHardis> listeUtilisateurHardis = new ArrayList<>();
                        listeUtilisateurHardis = gestionnaireHardisSession.listUtilisateurHardis();
                        List<ContactMail> listeContactMail = new ArrayList<>();
                        listeContactMail = gestionnaireHardisSession.listCommunicationNonReponduParUtilisateur(utilisateur.getId());
                        List<HistoriqueTraitement> listeHistoriqueTraitement = new ArrayList<>();
                        listeHistoriqueTraitement = gestionnaireHardisSession.listHistoriqueTraitementSansConsultant();
                        List<UtilisateurHardis> listeUtilisateurHardisReponseContactMail = new ArrayList<>();
                        String acao = null;
                        sess.setAttribute("listeUtilisateurHardisReponseContactMail",listeUtilisateurHardisReponseContactMail);                                               
                        sess.setAttribute("listeCommunication",listeCommunication);
                        sess.setAttribute("listeNotif",listeNotif);
                        sess.setAttribute("listeDevis",listeDevis);
                        sess.setAttribute("listeClient",listeClient);
                        sess.setAttribute("listeEntreprise",listeEntreprise);
                        sess.setAttribute("listeUtilisateurHardis",listeUtilisateurHardis);
                        sess.setAttribute("listeContactMail",listeContactMail);
                        sess.setAttribute("listeHistoriqueTraitement",listeHistoriqueTraitement);
                        jspClient="/Employe/dashboardAdmin.jsp";    
                                }   
                    else{
                        request.setAttribute("message","Utilisateur non trouvé");
                        jspClient="/Employe/Login.jsp";
                    }
                }
                else{
                    request.setAttribute("message","Login ou/et password non rempli");
                    jspClient="/Employe/Login.jsp";
                }
                
            }
            else if (act.equals("majMDP")) {
        
            jspClient = "/Employe/majCompte.jsp";
         
        }
        
        else if (act.equals("majProfil"))
        {
     
            
            String mail = (String) request.getParameter("mail");
            
          
            UtilisateurHardis u =  visualisateurHardisSession.rechercheUParEmailHache(mail);
            
            if (u!=null)
            {

         String mdp = request.getParameter("mdp");
         System.out.println(mdp);
        String mdpC = request.getParameter("mdpC");
        System.out.println(mdpC);
         String questS = request.getParameter("qs");
         System.out.println(questS);
         String repS = request.getParameter("rs");
         System.out.println(repS);
         if (mdp.equals(mdpC))
             visualisateurHardisSession.majInfosProfil(u.getId(), mdp, questS, repS);
          jspClient = "/Employe/Login.jsp";
            }
            
        
        }
         
      
    


    
        
        
        
            else if(act.equals("MotDePassOublie"))
            {

                jspClient="/Employe/QSRS.jsp";
            }
            else if(act.equals("DSRSAdmin")){
                String Login = request.getParameter("Login");
                String QS = request.getParameter("QS");
                String RS = request.getParameter("RS");
                UtilisateurHardis ut = gestionnaireHardisSession.rechercherUtilisateurHardisParLogin(Login);
                if(ut!=null){
                    if(!(QS.trim().isEmpty())||!(RS.trim().isEmpty())){
                        UtilisateurHardis  utilisateur = gestionnaireHardisSession.recupererUtilisateurHardisQSRS(QS, RS);
                        if(utilisateur != null){
                            if(utilisateur.equals(ut)){
                                sess.setAttribute("utilisateur", utilisateur);
                                jspClient="/Employe/newpass.jsp";    
                            }   
                            else{
                                request.setAttribute("message","Question Secret ou/et Response secret ne correspond pas au Login");
                                jspClient="/Employe/Login.jsp";
                            }
                        }
                        else{
                            request.setAttribute("message","Question Secret ou/et Response secret ne correspond pas au Login");
                            jspClient="/Employe/Login.jsp";
                        }
                    }
                    else{
                        request.setAttribute("message","Question Secret ou/et Response secret non rempli");
                        jspClient="/Employe/Login.jsp";
                    }
                }
                else{
                request.setAttribute("message","Utilisateur non trouvé");
                jspClient="/Employe/Login.jsp";}
            }
            else if(act.equals("ModifierPass"))
            {
               UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("utilisateur");
               doActionModifierUtilisateur(request,response);
               jspClient="/Employe/Login.jsp";
            }
            
            else if(act.equals("Menu"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("utilisateur");
                List<UtilisateurHardis> listeUtilisateurHardisReponseContactMail = new ArrayList<>(); 
                String test = request.getParameter("acao");
                String idContactMail = request.getParameter("idContactMail");
                if(test!=null&&test.equals("affecter")){
                    listeUtilisateurHardisReponseContactMail = gestionnaireHardisSession.listUtilisateurHardis();
                    if (listeUtilisateurHardisReponseContactMail==null) listeUtilisateurHardisReponseContactMail=new ArrayList<>();
                    
                }
                else if(test!=null&&test.equals("repondre")){
                    request.setAttribute("acao",test);
                }
                
                sess.setAttribute("listeUtilisateurHardisReponseContactMail",listeUtilisateurHardisReponseContactMail);
                jspClient="/Employe/dashboardAdmin.jsp";
                request.setAttribute("idContactMail",idContactMail);
               // request.setAttribute("message","");
            }
            else if(act.equals("CreerAgence"))
            {
                jspClient="/Employe/creerAgence.jsp";
            }
            else if(act.equals("InsererAgence"))
            {
               UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("utilisateur");
               doActionCreerAgence(request,response);
               jspClient="/Employe/dashboardAdmin.jsp";
            }
            else if(act.equals("AfficherAgence"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("utilisateur");
                List<Agence> listeAgence = gestionnaireHardisSession.listAgence();
                if (listeAgence==null) listeAgence=new ArrayList<>();
                request.setAttribute("listeAgence",listeAgence);
                jspClient="/Employe/afficherAgence.jsp";
            }
            else if(act.equals("AfficherEntreprise"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("utilisateur");
                List<Entreprise> listeEntreprise = gestionnaireHardisSession.listEntreprise();
                if (listeEntreprise==null) listeEntreprise=new ArrayList<>();
                 List<Agence> listeAgence = new ArrayList<>(); 
                String test = request.getParameter("test");
                if(test!=null && !test.isEmpty()){
                    listeAgence = gestionnaireHardisSession.listAgence();
                    if (listeAgence==null) listeAgence=new ArrayList<>();
                    
                }
                request.setAttribute("listeAgence",listeAgence);
                request.setAttribute("listeEntreprise",listeEntreprise);
                jspClient="/Employe/afficherEntreprise.jsp";
            }
            else if(act.equals("AffecterAgenceEntreprise"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("utilisateur");
                List<Entreprise> listeEntreprise = gestionnaireHardisSession.listEntreprise();
                if (listeEntreprise==null) listeEntreprise=new ArrayList<>();
                 List<Agence> listeAgence = new ArrayList<>(); 
                String test = request.getParameter("test");
                if(test!=null && !test.isEmpty()){
                    listeAgence = gestionnaireHardisSession.listAgence();
                    if (listeAgence==null) listeAgence=new ArrayList<>();
                    
                }
                request.setAttribute("listeAgence",listeAgence);
                request.setAttribute("listeEntreprise",listeEntreprise);
                doActionModifierAgenceEntreprise(request,response);
                jspClient="/Employe/afficherEntreprise.jsp";
            }
            else if(act.equals("RechercherAgence"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                List<Agence> listeAgence= new ArrayList<>();
                String champ = request.getParameter("champ");
                Agence a = gestionnaireHardisSession.rechercherAgence(0, champ, utilisateur);
                if(a!=null){
                    listeAgence.add(a);
                }else{
                listeAgence=new ArrayList<>();}
                request.setAttribute("listeAgence",listeAgence);
                jspClient="/Employe/afficherAgence.jsp";
            }
            else if(act.equals("RechercherEntreprise"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                List<Entreprise> listeEntreprise= new ArrayList<>();
                String champ = request.getParameter("champ");
                Entreprise a = gestionnaireHardisSession.rechercherEntreprise(0, "", champ, utilisateur);
                if(a!=null){
                    listeEntreprise.add(a);
                }else{
                listeEntreprise=new ArrayList<>();}
                request.setAttribute("listeEntreprise",listeEntreprise);
                jspClient="/Employe/afficherEntreprise.jsp";
            }
            else if(act.equals("formAgence"))
            {
                UtilisateurHardis utilisateur  = (UtilisateurHardis) sess.getAttribute("utilisateur");
                String champ = request.getParameter("idAgence");
                Long idagence = Long.valueOf(champ);
                Agence a = gestionnaireHardisSession.rechercherAgenceParId(idagence);
                request.setAttribute("agence",a);
                jspClient="/Employe/modifierAgence.jsp";
            }
             else if(act.equals("ModifierAgence"))
            {
               UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
               doActionModifierAgence(request,response);
               jspClient="/Employe/dashboardAdmin.jsp";
            }
             else if(act.equals("ContactMail"))
            {
               UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
               doActionRepondreContactMail(request,response);
               jspClient="/Employe/dashboardAdmin.jsp";
            }
             else if(act.equals("AffecterContactMail"))
            {
               UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
               doActionAffecterContactMail(request,response);
               jspClient="/Employe/dashboardAdmin.jsp";
            }
            else if(act.equals("CreerAdresse"))
            {
                UtilisateurHardis utilisateur  = (UtilisateurHardis) sess.getAttribute("utilisateur");
                List<Communication> listeCommunication= gestionnaireHardisSession.rechercherCommunication(0, utilisateur.getId(), utilisateur);
                List<Notification> listeNotif = gestionnaireHardisSession.getNotifsAdmin(utilisateur);
                List<Devis> listeDevis = new ArrayList<>();
                        List<HistoriqueTraitement> ht = gestionnaireHardisSession.rechercherHistoriqueTraitementParConsultant(utilisateur.getId(), utilisateur);
                        if (!ht.isEmpty()){
                            for (HistoriqueTraitement lesht: ht){
                                Devis dev = lesht.getDevis();
                                listeDevis.add(dev);
                            }
                        }
                List<Client> listeClient = new ArrayList<>();
                        
                        if (!listeDevis.isEmpty()){
                            for (Devis dev: listeDevis){
                                Client client = dev.getClient();
                                listeClient.add(client);
                            }
                        }
                if (listeCommunication==null) listeCommunication=new ArrayList<>();
                if (listeNotif==null) listeNotif=new ArrayList<>();
                if (listeDevis==null) listeDevis=new ArrayList<>();
                if (listeClient==null) listeClient=new ArrayList<>();
                request.setAttribute("listeCommunication",listeCommunication);
                request.setAttribute("listeNotif",listeNotif);
                request.setAttribute("listeDevis",listeDevis);
                request.setAttribute("listeClient",listeClient);
               jspClient="/Employe/creerAdresse.jsp";
            }
            else if(act.equals("InsererAdresse"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
               doActionCreerAdresse(request,response);
               jspClient="/Employe/dashboardAdmin.jsp";
               
            }
            else if(act.equals("AfficherAdresse"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                List<Adresse> listeAdresse = gestionnaireHardisSession.listAdresse();
                if (listeAdresse==null) listeAdresse=new ArrayList<>();
                request.setAttribute("listeAdresse",listeAdresse);
                jspClient="/Employe/afficherAdresse.jsp";
            }
            else if(act.equals("CreerAtelier"))
            {
               jspClient="/Employe/creerAtelier.jsp";
            }
             else if(act.equals("InsererAtelier"))
            {
               UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
               doActionCreerAtelier(request,response);
               jspClient="/Employe/dashboardAdmin.jsp";
            }
             else if(act.equals("AfficherAtelier"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                List<Atelier> listeAtelier = gestionnaireHardisSession.listAtelier();
                if (listeAtelier==null) listeAtelier=new ArrayList<>();
                request.setAttribute("listeAtelier",listeAtelier);
                jspClient="/Employe/afficherAtelier.jsp";
            }
             else if(act.equals("listeDevisComunnication"))
            {
                List<Devis> listdevis= gestionnaireHardisSession.listDevis();
                if (listdevis==null) listdevis=new ArrayList<>();
                request.setAttribute("listeDevis",listdevis);
                jspClient="/Employe/CreerCommunication.jsp";
            }
            else if(act.equals("InsererCommunication"))
            {
                
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                doActionCreerCommunication(request,response);
            }
            else if(act.equals("CreerDisponibilite"))
            {
               jspClient="/Employe/creerDisponibilite.jsp";
            }
            else if(act.equals("InsererDisponibilite"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                doActionCreerDisponibilite(request,response);
                jspClient="/Employe/dashboardAdmin.jsp";
            }
            else if(act.equals("listeHistoriquesDevisDoc"))
            {
                List<HistoriqueDevis> listhistdevis= gestionnaireHardisSession.listHistoriqueDevis();
                if (listhistdevis==null) listhistdevis=new ArrayList<>();
                request.setAttribute("listHistDevis",listhistdevis);
                jspClient="/Employe/CreerCommunication.jsp";
            }
            else if(act.equals("InsererDocument"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                doActionCreerDocument(request,response);
                jspClient="/Employe/dashboardAdmin.jsp";
            }
             else if(act.equals("listeDevisEchangeTel"))
            {
                List<Devis> listdevis= gestionnaireHardisSession.listDevis();
                if (listdevis==null) listdevis=new ArrayList<>();
                request.setAttribute("listeDevis",listdevis);
                jspClient="/Employe/CreerEchangeTel.jsp";
            }
            else if(act.equals("InsererEchangeTel"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("utilisateur");
                doActionCreerEchangeTel(request,response);
                jspClient="/Employe/dashboardAdmin.jsp";
            }
            else if(act.equals("listesPourModifierDevis"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("utilisateur");
                List<Agence> listagence= gestionnaireHardisSession.listAgence();
                if (listagence==null) listagence=new ArrayList<>();
                request.setAttribute("listeAgence",listagence);
                 List<Devis> listeDevis = new ArrayList<>();
                        List<HistoriqueTraitement> ht = gestionnaireHardisSession.rechercherHistoriqueTraitementParConsultant(utilisateur.getId(), utilisateur);
                        if (!ht.isEmpty()){
                            for (HistoriqueTraitement lesht: ht){
                                Devis dev = lesht.getDevis();
                                listeDevis.add(dev);
                            }
                        }
                request.setAttribute("listeDevis",listeDevis);
                List<Client> listeClient = new ArrayList<>();
                        
                        if (!listeDevis.isEmpty()){
                            for (Devis dev: listeDevis){
                                Client client = dev.getClient();
                                listeClient.add(client);
                            }
                        }
                request.setAttribute("listeClient",listeClient);
                jspClient="/Employe/ModifierDevis.jsp";
            }
            else if(act.equals("modifierDevis"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                doActionModifierDevis(request,response);
                jspClient="/Employe/dashboardAdmin.jsp";
            }
            else if(act.equals("listesCreerLivrable"))
            {
                
                jspClient="/Employe/creerLivrable.jsp";
            }
            else if(act.equals("InsererLivrable"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                doActionCreerLivrable(request,response);
                jspClient="/Employe/dashboardAdmin.jsp";
            }
            else if(act.equals("AfficherLivrable"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                List<Livrable> listeLivrable = gestionnaireHardisSession.listLivrable();
                if (listeLivrable==null) listeLivrable=new ArrayList<>();
                request.setAttribute("listeLivrable",listeLivrable);
                jspClient="/Employe/afficherLivrable.jsp";
            }
            else if(act.equals("CreerOffre"))
            {
               jspClient="/Employe/creerOffre.jsp";
            }
            else if(act.equals("InsererOffre"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                doActionCreerOffre(request,response);
                jspClient="/Employe/dashboardAdmin.jsp";
            }
            else if(act.equals("AfficherOffre"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                List<Offre> listeOffre = gestionnaireHardisSession.listOffre();
                if (listeOffre==null) listeOffre=new ArrayList<>();
                request.setAttribute("listeOffre",listeOffre);
                jspClient="/Employe/afficherOffre.jsp";
            }
            else if(act.equals("listesCreerService"))
            {
                List<Offre> listoffre= gestionnaireHardisSession.listOffre();
                if (listoffre==null) listoffre=new ArrayList<>();
                request.setAttribute("listoffre",listoffre);
                jspClient="/Employe/creerService.jsp";
            }
            else if(act.equals("InsererService"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                doActionCreerService(request,response);
                jspClient="/Employe/dashboardAdmin.jsp";
            }
            else if(act.equals("AfficherService"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("utilisateur");
                List<Service> listeService = gestionnaireHardisSession.listServiceNonStandard();
                if (listeService==null) listeService=new ArrayList<>();
                request.setAttribute("listeService",listeService);
                jspClient="/Employe/afficherService.jsp";
            }
            else if(act.equals("listesCreerServiceStandard"))
            {
                List<Offre> listoffre= gestionnaireHardisSession.listOffre();
                List<Atelier> listatelier= gestionnaireHardisSession.listAtelier();
                List<Livrable> listlivrable = gestionnaireHardisSession.listLivrable();
                if (listoffre==null) listoffre=new ArrayList<>();
                if (listatelier==null) listatelier=new ArrayList<>();
                if (listlivrable==null) listlivrable=new ArrayList<>();
                request.setAttribute("listoffre",listoffre);
                request.setAttribute("listatelier",listatelier);
                request.setAttribute("listlivrable",listlivrable);
                jspClient="/Employe/creerServiceStandard.jsp";
            }
            else if(act.equals("InsererServiceStandard"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                doActionCreerServiceStandard(request,response);
                jspClient="/Employe/dashboardAdmin.jsp";
            }
            else if(act.equals("AfficherServiceStandard"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("utilisateur");
                List<ServiceStandard> listeServiceStandard = gestionnaireHardisSession.listServiceStandard();
                if (listeServiceStandard==null) listeServiceStandard=new ArrayList<>();
                request.setAttribute("listeServiceStandard",listeServiceStandard);
                jspClient="/Employe/afficherServiceStandard.jsp";
            }
            else if(act.equals("listesCreerUtiliateurHardis"))
            {
                List<Agence> listagence= gestionnaireHardisSession.listAgence();
                List<Offre> listOffres = gestionnaireHardisSession.listOffre();
                if (listagence==null) listagence=new ArrayList<>();
                request.setAttribute("listagence",listagence);
                request.setAttribute("listOffres",listOffres);
                jspClient="/Employe/creerUtilisateur.jsp";
            }
            else if(act.equals("InsererUtilisateurHardis"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                doActionCreerUtilisateur(request,response);
                jspClient="/Employe/dashboardAdmin.jsp";
            }
             else if(act.equals("AfficherUtilisateur"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("utilisateur");
                List<UtilisateurHardis> listeUtilisateurHardis = gestionnaireHardisSession.listUtilisateurHardis();
                if (listeUtilisateurHardis==null) listeUtilisateurHardis=new ArrayList<>();
                request.setAttribute("listeUtilisateurHardis",listeUtilisateurHardis);
                jspClient="/Employe/afficherUtilisateur.jsp";
            }
             else if(act.equals("RechercherUtilisateur"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("utilisateur");
                List<UtilisateurHardis> listeUtilisateurHardis= new ArrayList<>();
                String textid = request.getParameter("champ");
                UtilisateurHardis utili = gestionnaireHardisSession.rechercherUtilisateurHardisParLogin(textid);
                if(utili!=null){
                    listeUtilisateurHardis.add(utili);
                }else{
                listeUtilisateurHardis=new ArrayList<>();}
                
                if (listeUtilisateurHardis==null) listeUtilisateurHardis=new ArrayList<>();
                request.setAttribute("listeUtilisateurHardis",listeUtilisateurHardis);
                jspClient="/Employe/afficherUtilisateur.jsp";
            }
             else if(act.equals("listesCertifierClient"))
            {
                List<Client> listClient= gestionnaireHardisSession.listClientNonCertifies();
                List<Agence> listeAg = gestionnaireHardisSession.listAgence();
                if (listClient==null) listClient=new ArrayList<>();
                request.setAttribute("listClient",listClient);
                 request.setAttribute("listAgence",listeAg);
                jspClient="/Employe/certifierClient.jsp";
            }
             else if(act.equals("RechercherClient"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                List<Client> listClient= new ArrayList<>();
                String mailClient = request.getParameter("mail");
                Client client = gestionnaireHardisSession.rechercherClient(0, jspClient, mailClient, utilisateur);
                if(client!=null){
                    listClient.add(client);
                }else{
                listClient=new ArrayList<>();}
                request.setAttribute("listClient",listClient);
                jspClient="/Employe/certifierClient.jsp";
            }
            else if(act.equals("CertifierClient"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                doActionCertifierClient(request,response);
                jspClient="/Employe/dashboardAdmin.jsp";
            }
            else if(act.equals("RechercherAtelier"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                List<Client> listClient= new ArrayList<>();
                String mailClient = request.getParameter("mail");
                Client client = gestionnaireHardisSession.rechercherClient(0, jspClient, mailClient, utilisateur);
                if(client!=null){
                    listClient.add(client);
                }else{
                listClient=new ArrayList<>();}
                request.setAttribute("listClient",listClient);
                jspClient="/Employe/certifierClient.jsp";
            }
         else if(act.equals("listesAfficherClient"))
            {
                List<Client> listClient= gestionnaireHardisSession.listClientNonCertifies();
                if (listClient==null) listClient=new ArrayList<>();
                request.setAttribute("listClient",listClient);
                jspClient="/Employe/afficherClient.jsp";
            }
             else if(act.equals("RechercherClient1"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                List<Client> listClient= new ArrayList<>();
                String mailClient = request.getParameter("champ");
                Client client = gestionnaireHardisSession.rechercherClient(0, jspClient, mailClient, utilisateur);
                if(client!=null){
                    listClient.add(client);
                }else{
                listClient=new ArrayList<>();}
                request.setAttribute("listClient",listClient);
                jspClient="/Employe/afficherClient.jsp";
            }
            else if(act.equals("formClient"))
            {
                UtilisateurHardis utilisateur  = (UtilisateurHardis) sess.getAttribute("utilisateur");
                String champ = request.getParameter("idClient");
                Long idagence = Long.valueOf(champ);
                Client a = gestionnaireHardisSession.rechercherClient(idagence, "", "", utilisateur);
                request.setAttribute("client",a);
                jspClient="/Employe/detailClient.jsp";
            }
             else if(act.equals("listesDevis"))
            {   
                 UtilisateurHardis utilisateur  = (UtilisateurHardis) sess.getAttribute("utilisateur");
                 List<Devis> listeDevis2 = new ArrayList<>();
                        List<HistoriqueTraitement> ht = gestionnaireHardisSession.rechercherHistoriqueTraitementParConsultant(utilisateur.getId(), utilisateur);
                        if (!ht.isEmpty()){
                            for (HistoriqueTraitement lesht: ht){
                                Devis dev = lesht.getDevis();
                                listeDevis2.add(dev);
                            }
                        }
                if (listeDevis2==null) listeDevis2=new ArrayList<>();                  
                request.setAttribute("listeDevis2",listeDevis2);
                
                jspClient="/Employe/afficherDevis.jsp";
            }
            else if(act.equals("RechercherDevis"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("utilisateur");
                List<Devis> listDevis= new ArrayList<>();
                String textidDevis = request.getParameter("champ");
                String idDevis = textidDevis.substring(3);
                Long id = Long.valueOf(idDevis);
                Devis devis = gestionnaireHardisSession.rechercherDevis(id, utilisateur);
                if(devis!=null){
                    listDevis.add(devis);
                }else{
                listDevis=new ArrayList<>();}
                request.setAttribute("listeDevis2",listDevis);
                jspClient="/Employe/afficherDevis.jsp";
            }
            else if(act.equals("formDevis"))
            {
                UtilisateurHardis utilisateur  = (UtilisateurHardis) sess.getAttribute("utilisateur");
                List<HistoriqueTraitement> listeHTVide =new ArrayList<>();
                String champ = request.getParameter("idDevis");
                String faire = request.getParameter("faire");
                Long iddevis = Long.valueOf(champ);
                Devis a = gestionnaireHardisSession.rechercherDevis(iddevis, utilisateur);
                List<UtilisateurHardis> listeConsultantOffre = new ArrayList<>();
                Long of = a.getService().getOffre().getId();                
                List<Offre_Profil_Util_CV> o = gestionnaireHardisSession.listHistoriqueOffre_Profil_Util_CV(utilisateur);
             
               for (Offre_Profil_Util_CV compteur : o)
               {
                   if ( compteur.getOffre().equals(a.getService().getOffre()))
                       listeConsultantOffre.add(compteur.getUtilisateur());
               }
                if(faire!=null&&faire.equals("valider")){
                faire = "valider";
                request.setAttribute("faire",faire);}
                if(faire!=null&&faire.equals("document")){
                faire = "document";
                request.setAttribute("faire",faire);}
                if(faire!=null&&faire.equals("facture")){
                faire = "facture";
                request.setAttribute("faire",faire);}
                if(faire!=null&&faire.equals("affecter")){
                    faire = "affecter";
                request.setAttribute("faire",faire);
                }
                if(faire!=null&&faire.equals("envoyer")){
                    faire = "envoyer";
                request.setAttribute("faire",faire);
                }
                if(faire!=null&&faire.equals("modifier")){
                    faire = "modifier";
                request.setAttribute("faire",faire);
                }
                HistoriqueDevis hd = gestionnaireHardisSession.rechercherUnHistoriqueDevisParUtilisateur(iddevis);
                List<Document> listeDocument = gestionnaireHardisSession.rechercherDocumentParHistoriqueDevis(hd.getId(), utilisateur);
                if (listeDocument==null) listeDocument=new ArrayList<>();    
                List<Communication> listeCommunicationDevis = gestionnaireHardisSession.rechercherCommunication(iddevis, 0, utilisateur);
                if (listeCommunicationDevis==null) listeCommunicationDevis=new ArrayList<>();                  
                request.setAttribute("listeCommunicationDevis",listeCommunicationDevis);
                request.setAttribute("listeHTVide",listeHTVide);
                request.setAttribute("listeDocument",listeDocument);
                request.setAttribute("listeConsultantOffre",listeConsultantOffre);
                sess.setAttribute("devistraitement",a);
                jspClient="/Employe/traitementDevis.jsp";
                
            }
             else if(act.equals("affecterConsultantDevis"))
            {
                UtilisateurHardis utilisateur  = (UtilisateurHardis) sess.getAttribute("utilisateur");
                List<HistoriqueTraitement> listeHTVide =new ArrayList<>();
                String champ = request.getParameter("idDevis");             
                String nombreJour = request.getParameter("nombreJour");
                    Float numJour = Float.valueOf(nombreJour);
                   
                Long iddevis = Long.valueOf(champ);
                 gestionnaireHardisSession.majNBJ(iddevis,numJour);
                Devis a = gestionnaireHardisSession.rechercherDevis(iddevis, utilisateur);
                List<UtilisateurHardis> listeConsultantOffre = new ArrayList<>();
                    
                Long of = a.getService().getOffre().getId();         
                 List<UtilisateurHardis> listeCCDispo = gestionnaireHardisSession.rechercheCDisponibles("Confirme", a.getDateIntervSouhaitee(), a.getService().getId(), "Non_Standard", a.getClient().getId(), numJour);
                List<UtilisateurHardis> listeCSDispo = gestionnaireHardisSession.rechercheCDisponibles("Senior", a.getDateIntervSouhaitee(), a.getService().getId(), "Non_Standard", a.getClient().getId(), numJour);
                List<UtilisateurHardis> listeCJDispo = gestionnaireHardisSession.rechercheCDisponibles("Junior", a.getDateIntervSouhaitee(), a.getService().getId(), "Non_Standard", a.getClient().getId(), numJour);
                List<Offre_Profil_Util_CV> o = gestionnaireHardisSession.listHistoriqueOffre_Profil_Util_CV(utilisateur);             
               
                       listeConsultantOffre.addAll(listeCCDispo);
                        listeConsultantOffre.addAll(listeCSDispo);
                        listeConsultantOffre.addAll(listeCJDispo);
               
               List<Float> PrixU =  new ArrayList<Float>();
                  List<String> listeLibC =  new ArrayList<String>();
                
                for (UtilisateurHardis u : listeConsultantOffre)
                {
                    float prixUni = gestionnaireHardisSession.recherchePrixOffreC(u, a.getService().getOffre());
                    PrixU.add(prixUni);
                    String lib = gestionnaireHardisSession.rechercheLibConsultOffre(u, a.getService().getOffre());
                    listeLibC.add(lib);
                 
                }
                   request.setAttribute("listeLibC", listeLibC);
                    request.setAttribute("PrixU", PrixU);
                List<Communication> listeCommunicationDevis = gestionnaireHardisSession.rechercherCommunication(iddevis, 0, utilisateur);
                if (listeCommunicationDevis==null) listeCommunicationDevis=new ArrayList<>();                  
                request.setAttribute("listeCommunicationDevis",listeCommunicationDevis);
                request.setAttribute("listeHTVide",listeHTVide);
                request.setAttribute("listeConsultantOffre",listeConsultantOffre);
                request.setAttribute("nombreJour",numJour);
                sess.setAttribute("devistraitement",a);
                jspClient="/Employe/affecterConsultant.jsp";
                
            }
            else if(act.equals("formUtilisateur"))
            {
                UtilisateurHardis utilisateur  = (UtilisateurHardis) sess.getAttribute("utilisateur");
                List<HistoriqueTraitement> listeHTVide =new ArrayList<>();
                String champ = request.getParameter("idUtili");
                String test = request.getParameter("acao");
                Long idutili = Long.valueOf(champ);
                UtilisateurHardis utili = gestionnaireHardisSession.rechercherUtilisateurHardisParId(idutili, utilisateur);
                List<Offre> listeOffress = gestionnaireHardisSession.listOffre();
                if (listeOffress==null) listeOffress=new ArrayList<>();   
                List<UtilisateurHardis> listeUtilisateurHardis = gestionnaireHardisSession.listUtilisateurHardis();
                if (listeUtilisateurHardis==null) listeUtilisateurHardis=new ArrayList<>();
                request.setAttribute("listeUtilisateurHardis",listeUtilisateurHardis);
                request.setAttribute("listeOffress",listeOffress);
                request.setAttribute("utili",utili);
                request.setAttribute("acao",test);
                jspClient="/Employe/traitementUtilisateur.jsp";
            }
            else if(act.equals("affecterDevis"))
            {
                UtilisateurHardis utilisateur  = (UtilisateurHardis) sess.getAttribute("utilisateur");
                List<HistoriqueTraitement> listeHTVide =new ArrayList<>();
                String champ = request.getParameter("idDevis");
                Long iddevis = Long.valueOf(champ);
                Devis a = gestionnaireHardisSession.rechercherDevis(iddevis, utilisateur);
                Long of = a.getService().getOffre().getId();
                List<UtilisateurHardis> listeConsultantOffre = new ArrayList<>();
                List<Offre_Profil_Util_CV> o = gestionnaireHardisSession.listHistoriqueOffre_Profil_Util_CV(utilisateur);
             
               for (Offre_Profil_Util_CV compteur : o)
               {
                   if ( compteur.getOffre().equals(a.getService().getOffre()))
                       listeConsultantOffre.add(compteur.getUtilisateur());
               }
                List<Communication> listeCommunicationDevis = gestionnaireHardisSession.rechercherCommunication(iddevis, 0, utilisateur);
                if (listeCommunicationDevis==null) listeCommunicationDevis=new ArrayList<>();                  
                request.setAttribute("listeCommunicationDevis",listeCommunicationDevis);
                request.setAttribute("listeHTVide",listeHTVide);
                request.setAttribute("listeConsultantOffre",listeConsultantOffre);
                sess.setAttribute("devistraitement",a);
                jspClient="/Employe/affecterDevis.jsp";
            }
            
            else if(act.equals("messageDevis"))
            {
                UtilisateurHardis utilisateur  = (UtilisateurHardis) sess.getAttribute("utilisateur");
                List<HistoriqueTraitement> listeHTVide =new ArrayList<>();
                Devis devis = (Devis) sess.getAttribute("devistraitement");
                String champ = request.getParameter("idDevis");
                Long iddevis = devis.getId();
                doActionMessageDevis(request,response);
                List<Communication> listeCommunicationDevis = gestionnaireHardisSession.rechercherCommunication(iddevis, 0, utilisateur);
                List<UtilisateurHardis> listeConsultantOffre = new ArrayList<>();
                if (listeCommunicationDevis==null) listeCommunicationDevis=new ArrayList<>();                  
                request.setAttribute("listeCommunicationDevis",listeCommunicationDevis);
                request.setAttribute("listeConsultantOffre",listeConsultantOffre);
                request.setAttribute("listeHTVide",listeHTVide);
                jspClient="/Employe/traitementDevis.jsp";
            }
            else if(act.equals("ValiderDevis"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("utilisateur");
                doActionValiderDevisNonStandard(request,response);
                jspClient="/Employe/dashboardAdmin.jsp";
            }
            else if(act.equals("Creer1ereFactureDevis"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("utilisateur");
                doActionCreer1ereFacture(request,response);
                jspClient="/Employe/dashboardAdmin.jsp";
            }
            else if(act.equals("EnvoyerDevis"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("utilisateur");
                String docenv = request.getParameter("docenvoye");
                String idclient = request.getParameter("idclient");
                String iddev = request.getParameter("iddev");
                Long idClient = Long.valueOf(idclient);
                Long idDevis = Long.valueOf(iddev);
                Long idDoc = Long.valueOf(docenv);
                Document doc = gestionnaireHardisSession.rechercherDocument(idDoc, utilisateur);
                String docenvoye = doc.getDescriptif();
                java.util.Date nowDate = new java.util.Date();
                java.util.Date datevali =  new java.util.Date();
                datevali.setHours(nowDate.getHours()+48);
                SimpleDateFormat dformat = new SimpleDateFormat("dd/MM/yyyy");
                String date = dformat.format(datevali) ; 
                String textmail = "Ci-joint votre proposition liée au devis DEV:<%=devistraitement.getId().toString() %> valable jusqu\'au : <%=date%> <br>Cordialement<br> <%=utilisateur.getNom() %> ";
                Devis devistraitement  = gestionnaireHardisSession.rechercherDevis(idDevis, utilisateur);
                Client client = gestionnaireHardisSession.rechercherClient(idClient, "", "", utilisateur);
                HistoriqueDevis hd = gestionnaireHardisSession.rechercherUnHistoriqueDevisParUtilisateur(idDevis);
                List<Document> listeDocument = gestionnaireHardisSession.rechercherDocumentParHistoriqueDevis(hd.getId(), utilisateur);
                if (listeDocument==null) listeDocument=new ArrayList<>();
                request.setAttribute("listeDocument",listeDocument);
                request.setAttribute("devistraitement",devistraitement);
                request.setAttribute("client",client);
                request.setAttribute("docenvoye",docenvoye);
                request.setAttribute("date",date);
                request.setAttribute("textmail",textmail);
                jspClient="/Employe/envoyerDevisClient.jsp";
            }
            else if(act.equals("EnvoyerLeDevis"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("utilisateur");
                doActionEnvoyerDevis(request,response);
                jspClient="/Employe/envoyerDevisClient.jsp";
            }
            else if(act.equals("AjouterDocumentAUnDevis"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("utilisateur");
                doActionAjouterDocumentAUnDevis(request,response);
                jspClient="/Employe/dashboardAdmin.jsp";
            }
            else if(act.equals("RelancerDevis"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("utilisateur");
                doActionRelancerDevisNonStandard(request,response);
                jspClient="/Employe/dashboardAdmin.jsp";
            }
            else if(act.equals("PrestationtermineeDevis"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("utilisateur");
                doActionPrestationtermineeDevis(request,response);
                jspClient="/Employe/dashboardAdmin.jsp";
            }
            else if(act.equals("ModifierDevis"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("utilisateur");
                doActionModifierDevis(request,response);
                jspClient="/Employe/dashboardAdmin.jsp";
            }
            else if(act.equals("AffecterDevis"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("utilisateur");
                doActionAffecterDevis(request,response);
                jspClient="/Employe/dashboardAdmin.jsp";
            }
            
            
            else if(act.equals("EnvoyerDevisMail"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("utilisateur");
                doActionEnvoyerMail(request,response);
                jspClient="/Employe/dashboardAdmin.jsp";
            }
            else if(act.equals("ModifierProfilMetier"))
            {
               UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("utilisateur");
               doActionModifierProfilMetierUtilisateur(request,response);
               jspClient="/Employe/dashboardAdmin.jsp";
            }
            else if(act.equals("AffecterConsultantAUnDevis"))
            {
               UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("utilisateur");
               doActionAffecterConsultantAUnDevis(request,response);
               jspClient="/Employe/dashboardAdmin.jsp";
            }
            
            else if (act.equals("calendar"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("utilisateur");
               
                jspClient="/Employe/calendar.jsp";
            
            
              
                 String lib = request.getParameter("lib");
                 String dateD = request.getParameter("dd");
                 String dateF = request.getParameter("df");
                 String hd = request.getParameter("hd");
                 
                
                 String hf = request.getParameter("hf");
                 
                 
           
                 
                 if (lib!=null && dateD!=null )
                 {
                     if (lib!="" && dateD!="" )
                     {
                         
                         String dte1 = dateD.replace("/", "-");
                         String dte2 = dateF.replace("/", "-");
                         
                          String heureD = hd.split(":")[0];
                          String minutesD = hd.split(":")[1].substring(0,2);
                          
                          String heureF = hf.split(":")[0];
                          String minutesF = hf.split(":")[1].substring(0,2);
                         
                         
                           java.util.Date parsed = null;
                           java.util.Date parsed1 = null;
                           DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
                          try{
                             parsed = df.parse(dte1);
                             if (hd.contains("PM")) {
                             parsed.setHours(Integer.valueOf(heureD)+12);
                             parsed.setMinutes(Integer.valueOf(minutesD));
                             }
                             else {
                                  parsed.setHours(Integer.valueOf(heureD));
                             parsed.setMinutes(Integer.valueOf(minutesD));
                             }
                             
                              parsed1 = df.parse(dte2);
                             if (hf.contains("PM")) {
                             parsed1.setHours(Integer.valueOf(heureF)+12);
                             parsed1.setMinutes(Integer.valueOf(minutesF));                        
                             
                             }    
                             else {
                                  parsed1.setHours(Integer.valueOf(heureF));
                             parsed1.setMinutes(Integer.valueOf(minutesF));  
                          }
                          }
                          catch(Exception ex)
                          {
                              ex.getMessage();
                          }
                  
                       gestionnaireHardisSession.creerDisponibilite(parsed, parsed1, lib, utilisateur);
                     }
                 }
                  List<Disponibilite> listeD =  gestionnaireHardisSession.getDispoU(utilisateur);
                request.setAttribute("listeD", listeD);
            }

        RequestDispatcher Rd;
         try{
        Rd = getServletContext().getRequestDispatcher(jspClient);
        Rd.forward(request, response);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
               Rd = getServletContext().getRequestDispatcher("/404.jsp");
        Rd.forward(request, response);     
        }
      
    }
    protected void doActionCreerAgence(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String nomAgence = request.getParameter("nomAgence");
        String adrAgence = request.getParameter("adrAgence");
        String message = null;
        if(nomAgence.trim().isEmpty()){
            message = "Erreur - Vous n'avez pas rempli tous les champs obligatoires." + "<br/><a href=\"CreerContratEntraineur.jsp\">Clique ici </a>pour accéder au formulaire de creation.";
        }
        else {
            UtilisateurHardis ut = (UtilisateurHardis) sess.getAttribute("utilisateur");
            if(ut!=null){
                Agence agence =  gestionnaireHardisSession.rechercherAgence(0, nomAgence, ut);
                if(agence!=null){
                    message= "Erreur agence: "+nomAgence +" déjà dans la base de données";
                }
                else{
                    gestionnaireHardisSession.creerAgence(nomAgence, ut,adrAgence);
                    agence = gestionnaireHardisSession.rechercherAgence(0, nomAgence, ut);
                    String nomagence = agence.getNomAgence();
                    String classe = agence.getClass().toString();
                    message= " "+classe+":"+ nomagence+" créé avec succès !";
                }
            }
            else{
                message= "Erreur information non inserée dans la base de données";
            }
            
        }
        request.setAttribute("message", message);
    }
    protected void doActionModifierAgence(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String idAgence = request.getParameter("idAgence");
        String nomAgence = request.getParameter("nomAgence");
        String message = null;
        if(nomAgence.trim().isEmpty()||idAgence.trim().isEmpty()){
            message = "Erreur - Vous n'avez pas rempli tous les champs obligatoires." + "<br/><a href=\"CreerContratEntraineur.jsp\">Clique ici </a>pour accéder au formulaire de creation.";
        }
        else {
            UtilisateurHardis ut = (UtilisateurHardis) sess.getAttribute("utilisateur");
            if(ut!=null){
                Agence agence =  gestionnaireHardisSession.rechercherAgence(0, nomAgence, ut);
                if(agence!=null){
                    message= "Erreur agence: "+nomAgence +" déjà dans la base de données";
                }
                else{
                    Long id = Long.valueOf(idAgence);
                    gestionnaireHardisSession.modifierAgence(id, nomAgence, ut);
                    agence = gestionnaireHardisSession.rechercherAgence(0, nomAgence, ut);
                    String nomagence = agence.getNomAgence();
                    String classe = agence.getClass().toString();
                    message= " "+classe+":"+ nomagence+" modifiée avec succès !";
                }
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
                Adresse adresse = gestionnaireHardisSession.creerAdresse(numRue, NomRue, Ville, CodePostal, ut);
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
    
    protected void doActionCertifierClient(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String idClient = request.getParameter("idClient");
        String agence = request.getParameter("agence");
        String message = null;
        if(idClient.trim().isEmpty()){
            message = "Erreur - Vous n'avez pas rempli tous les champs obligatoires.";
        }
        else {
            UtilisateurHardis ut = (UtilisateurHardis) sess.getAttribute("utilisateur");
            if(ut!=null){
                Long id = Long.valueOf(idClient);
                Agence ag = gestionnaireHardisSession.rechercherAgenceParId(Long.valueOf(agence));
                gestionnaireHardisSession.majAgenceClient(ag.getId(), id);
              
                gestionnaireHardisSession.certifieClient(id, ut);
                 
          
                Client cli = gestionnaireHardisSession.rechercherClient(id, "", "", ut);
                  gestionnaireHardisSession.majAgenceEnt(ag.getId(), cli.getEntreprise().getId());
                
                 SendMail send = new SendMail();
                 String messa = "<p>Bonjour, <br> Votre entreprise a été certifié. Veuillez accéder a votre <a href=\"http://localhost:8080/ProjetHardis-war/servInternaute?action=connexion\">compte Hardis</a> et spécifier les informations suivantes dans la partie Profil <br>"
                 + "Code contrat :"+ cli.getEntreprise().getCodeContrat()+"<br>Mot de passe : "+cli.getEntreprise().getMdpEntreprise()+" Cordialement, Hardis</p> ";
                 send.sendMail(cli.getLogin(),"Validation compte Hardis", messa);
       
                String nomentite = cli.getNom() ;
                String classe = cli.getClass().toString();
                message= " "+classe+":"+ nomentite+" certfiée avec succès !";
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
                Atelier atelier = gestionnaireHardisSession.rechercherAtelier(0, nomAtelier, ut);
                if(atelier!=null){
                    message= "Erreur atelier: "+nomAtelier +" déjà dans la base de données";
                }else{
                gestionnaireHardisSession.creerAtelier(nomAtelier, ut);
                atelier = gestionnaireHardisSession.rechercherAtelier(0, nomAtelier, ut);
                String nomentite = atelier.getNomAtelier();
                String classe = atelier.getClass().toString();
                message= " "+classe+":"+ nomentite+" créé avec succès !";
                }
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
                Communication communication = gestionnaireHardisSession.creerCommunicationHardis(messagecom, iddevis, ut);
                String classe = communication.getClass().toString();
                message= " "+classe+": envoiée avec succès !";
                List<Communication> listeCommunicationDevis = gestionnaireHardisSession.rechercherCommunication(iddevis, 0, ut);
                                 
                request.setAttribute("listeCommunicationDevis",listeCommunicationDevis);
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
                Disponibilite disponibilite = gestionnaireHardisSession.creerDisponibilite(dtdebut, dtfin, libelle, ut);
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
         String typeDoc = request.getParameter("typeDoc"); 
        String historiquedevis = request.getParameter("historiquedevisDocument");
        String message = null;
        if(descriptif.trim().isEmpty()||liendoc.trim().isEmpty()||historiquedevis.trim().isEmpty()){
            message = "Erreur - Vous n'avez pas rempli tous les champs obligatoires." + "<br/><a href=\"CreerContratEntraineur.jsp\">Clique ici </a>pour accéder au formulaire de creation.";
        }
        else {
            UtilisateurHardis ut = (UtilisateurHardis) sess.getAttribute("utilisateur");
            if(ut!=null){
                Long idhistorique = Long.valueOf(historiquedevis);
                Document document = gestionnaireHardisSession.creerDocument(descriptif, liendoc, idhistorique, ut, typeDoc);
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
                EchangeTel echangetel = gestionnaireHardisSession.creerEchangeTel(text, iddevis, ut);
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
    protected void doActionModifierDevis(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
//        String facturation = request.getParameter("factDevis"); 
        String montantdevis= request.getParameter("montDevis"); 
        String motifrefus = request.getParameter("refusDevis");
        String saisielibre= request.getParameter("slDevis"); 
        String statut = request.getParameter("statutDevis");
        String agence = request.getParameter("idage");
        String devis = request.getParameter("iddev");
        String client = request.getParameter("idcli");
        String message = null;
            UtilisateurHardis ut = (UtilisateurHardis) sess.getAttribute("utilisateur");
            if(ut!=null){
                Long idagence = Long.valueOf(agence);
                Long iddevis = Long.valueOf(devis);
                Long idclient = Long.valueOf(client);
                Devis o = gestionnaireHardisSession.rechercherDevis(iddevis, ut);
                if(motifrefus==null||motifrefus.equals("")){
                    motifrefus = o.getMotifRefus();
                }
                if(saisielibre.equals("")){
                    motifrefus = o.getSaisieLibre();
                }
//                Facturation fact = null;
//                if(facturation.equals("Auto")){
//                    fact = Facturation.Auto;
//                }
//                else if (facturation.equals("Manuel")){
//                    fact = Facturation.Manuel;
//                }
//                else if (facturation==null){
//                    fact = o.getIndicateurFact();
//                }
                Statut statuts = null;
                if(statut.equals("Incomplet")){
                    statuts = Statut.Incomplet;
                }
                else if (statut.equals("Rep_en_Cours")){
                    statuts = Statut.Rep_en_Cours;
                }
                else if (statut.equals("Envoye")){
                    statuts = Statut.Envoye;
                }
                else if (statut.equals("Valide")){
                    statuts = Statut.Valide;
                }
                else if (statut.equals("Refuse")){
                    statuts = Statut.Refuse;
                }
                else if (statut.equals("En_nego")){
                    statuts = Statut.En_nego;
                }
                else if (statut.equals("Acompte_regle")){
                    statuts = Statut.Acompte_regle;
                }
                else if (statut.equals("Presta_terminee")){
                    statuts = Statut.Presta_terminee;
                }
                else if (statut.equals("Transmettre_au_client")){
                    statuts = Statut.Transmettre_au_client;
                }
                else if (statut.isEmpty()){
                    statuts = o.getStatut();
                }
                Statut sta = gestionnaireHardisSession.rechercherStatutParDevis(iddevis, ut);
                Float montantadevis = null;
                if(montantdevis.equals("")){
                    montantadevis = o.getMontantDevis();
                    }
                else{
                    montantadevis = Float.valueOf(montantdevis);
                }
                gestionnaireHardisSession.modifieDevis(iddevis, null, null, null, montantadevis, motifrefus, saisielibre, statuts, idclient, idagence, ut);
                if(sta!=statuts){
                    List<HistoriqueEtats> liste = gestionnaireHardisSession.rechercherHistoriqueEtatsParDevis(iddevis, ut);
                    HistoriqueEtats he = gestionnaireHardisSession.creerHistoriqueEtats(statuts, iddevis, ut);
                    liste.add(he);
                }
                
                String nomentite = o.getId().toString();
                String classe = o.getClass().toString();
                message= " "+classe+":"+ nomentite+" modifiée avec succès !";
            }
            else{
                message= "Erreur information non inserée dans la base de données";
            }
        
        request.setAttribute("message", message);
    }
    protected void doActionCreerLivrable(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String nom = request.getParameter("nomLivrable");
        String message = null;
        if(nom.trim().isEmpty()){
            message = "Erreur - Vous n'avez pas rempli tous les champs obligatoires." + "<br/><a href=\"CreerContratEntraineur.jsp\">Clique ici </a>pour accéder au formulaire de creation.";
        }
        else {
            UtilisateurHardis ut = (UtilisateurHardis) sess.getAttribute("utilisateur");
            if(ut!=null){
                
                Livrable o = gestionnaireHardisSession.creerLivrable(nom,  ut);
                
                o = gestionnaireHardisSession.creerLivrable(nom, ut);
                String nomentite = o.getNomLivrable();
                String classe = o.getClass().toString();
                message= " "+classe+":"+ nomentite+" créé avec succès !";}
            
            else{
                message= "Erreur information non inserée dans la base de données";
            }
        }
        request.setAttribute("message", message);
    }
    
    
    protected void doActionCreerOffre(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String libelle = request.getParameter("libelleOffre");
        String message = null;
        if(libelle.trim().isEmpty()){
            message = "Erreur - Vous n'avez pas rempli tous les champs obligatoires." + "<br/><a href=\"CreerContratEntraineur.jsp\">Clique ici </a>pour accéder au formulaire de creation.";
        }
        else {
            UtilisateurHardis ut = (UtilisateurHardis) sess.getAttribute("utilisateur");
            if(ut!=null){
                Offre o =gestionnaireHardisSession.rechercherOffreLibelle(libelle, ut);
                if(o==null){
                o = gestionnaireHardisSession.creerOffre(libelle, ut);
                String nomentite = o.getLibelle();
                String classe = o.getClass().toString();
                message= " "+classe+":"+ nomentite+" créé avec succès !";
                }
                else{
                    message= "Erreur offre"+o.getLibelle() + "deja dans la base de données";
                }
            }
            else{
                message= "Erreur information non inserée dans la base de données";
            }
        }
        request.setAttribute("message", message);
    }
    
     protected void doActionCreerService(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
              
        String nomService = request.getParameter("nomService");
        String descriptionService= request.getParameter("descriptionService");
        String lieuInterv= request.getParameter("lieuInterv");
        String offre= request.getParameter("offre");
        String cout= request.getParameter("cout");
        String facturation= request.getParameter("facturation");
        String listeCond= request.getParameter("listeCond");
        String delai = request.getParameter("delai");
//        String typeS= request.getParameter("typeS");
        String message = null;
        if(nomService.trim().isEmpty()||descriptionService.trim().isEmpty()||lieuInterv.trim().isEmpty()||offre.trim().isEmpty()||cout.trim().isEmpty()||facturation.trim().isEmpty()||listeCond.trim().isEmpty()||delai.trim().isEmpty()){
            message = "Erreur - Vous n'avez pas rempli tous les champs obligatoires." + "<br/><a href=\"CreerContratEntraineur.jsp\">Clique ici </a>pour accéder au formulaire de creation.";
        }
        else {
            UtilisateurHardis ut = (UtilisateurHardis) sess.getAttribute("utilisateur");
            if(ut!=null){

                LieuIntervention lieuIntervs= null;
                if(lieuInterv.equals("Agence_Hardis")){
                    lieuIntervs = LieuIntervention.Agence_Hardis;
                }
                else if (lieuInterv.equals("Mixte")){
                    lieuIntervs = LieuIntervention.Mixte;
                }
                else if (lieuInterv.equals("Site_Client")){
                    lieuIntervs = LieuIntervention.Site_Client;
                }
                Long idoffre= Long.valueOf(offre);
                Float coutService= Float.valueOf(cout);
                FacturationFrais facturations= null;
                if(facturation.equals("Oui")){
                    facturations = FacturationFrais.Oui;
                }
                else if (facturation.equals("Non")){
                    facturations = FacturationFrais.Non;
                }
                int delaiService = Integer.valueOf(delai);
                TypeService typeSs= TypeService.Non_Standard;
//                if(typeS.equals("Standard")){
//                    typeSs = TypeService.Standard;
//                }
//                else if (facturation.equals("Non_Standard")){
//                    typeSs = TypeService.Non_Standard;
//                }
                Service o = gestionnaireHardisSession.creerService(nomService, descriptionService, lieuIntervs, idoffre, coutService, facturations, listeCond, delaiService, typeSs, ut);
                String nomentite = o.getNomService();
                String classe = o.getClass().toString();
                message= " "+classe+":"+ nomentite+" créé avec succès !";
 
            }
            else{
                message= "Erreur information non inserée dans la base de données";
            }
        }
        request.setAttribute("message", message);
    }
      protected void doActionCreerServiceStandard(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String descPresta = request.getParameter("descPrestaService");
        String snbJS= request.getParameter("nbJSService");
        String snbJC = request.getParameter("nbJCService");
        String snbJJ= request.getParameter("nbJJService");
        String snbHA= request.getParameter("nbHAService");
        String snbHS= request.getParameter("nbHSService");
        String[] listidlivrable = request.getParameterValues("listlivrable");
        String[] listeidAtelier= request.getParameterValues("listatelier");
        String nomService = request.getParameter("nomService");
        String descriptionService= request.getParameter("descriptionService");
        String lieuInterv= request.getParameter("lieuInterv");
        String offre= request.getParameter("offre");
        String cout= request.getParameter("cout");
        String facturation= request.getParameter("facturation");
        String listeCond= request.getParameter("listeCond");
        String delai = request.getParameter("delai");
//        String typeS= request.getParameter("typeS");
        String message = null;
        if(nomService.trim().isEmpty()||descriptionService.trim().isEmpty()||lieuInterv.trim().isEmpty()||offre.trim().isEmpty()||cout.trim().isEmpty()||facturation.trim().isEmpty()||listeCond.trim().isEmpty()||delai.trim().isEmpty()){
            message = "Erreur - Vous n'avez pas rempli tous les champs obligatoires." + "<br/><a href=\"CreerContratEntraineur.jsp\">Clique ici </a>pour accéder au formulaire de creation.";
        }
        else {
            UtilisateurHardis ut = (UtilisateurHardis) sess.getAttribute("utilisateur");
            if(ut!=null){
                Float nbJS= Float.valueOf(snbJS);
                Float nbJC= Float.valueOf(snbJC);
                Float nbJJ= Float.valueOf(snbJJ);
                Float nbHA= Float.valueOf(snbHA);
                Float nbHS= Float.valueOf(snbHS);
                LieuIntervention lieuIntervs= null;
                if(lieuInterv.equals("Agence_Hardis")){
                    lieuIntervs = LieuIntervention.Agence_Hardis;
                }
                else if (lieuInterv.equals("Mixte")){
                    lieuIntervs = LieuIntervention.Mixte;
                }
                else if (lieuInterv.equals("Site_Client")){
                    lieuIntervs = LieuIntervention.Site_Client;
                }
                Long idoffre= Long.valueOf(offre);
                Float coutService= Float.valueOf(cout);
                FacturationFrais facturations= null;
                if(facturation.equals("Oui")){
                    facturations = FacturationFrais.Oui;
                }
                else if (facturation.equals("Non")){
                    facturations = FacturationFrais.Non;
                }
                int delaiService = Integer.valueOf(delai);
                TypeService typeSs= TypeService.Standard;
//                if(typeS.equals("Standard")){
//                    typeSs = TypeService.Standard;
//                }
//                else if (facturation.equals("Non_Standard")){
//                    typeSs = TypeService.Non_Standard;
//                }
                ServiceStandard o = gestionnaireHardisSession.creerServiceStandard(nomService, descriptionService, lieuIntervs, idoffre, coutService, facturations, listeCond, delaiService, typeSs, descPresta, nbJS, nbJC, nbJJ, nbHA, listidlivrable, listeidAtelier, nbHS, ut);
                String nomentite = o.getNomService();
                String classe = o.getClass().toString();
                message= " "+classe+":"+ nomentite+" créé avec succès !";
 
            }
            else{
                message= "Erreur information non inserée dans la base de données";
            }
        }
        request.setAttribute("message", message);
    }
      
      protected void doActionCreerUtilisateur(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
          String mailHache="";
          
        
                
        String nom = request.getParameter("nomUtilisateur");
        String prix = request.getParameter("prix");
        String prenom= request.getParameter("prenomUtilisateur");
        String login= request.getParameter("loginUtilisateur");
        String mdp= request.getParameter("mdpUtilisateur");
        String profil = request.getParameter("PFUtilisateur");
        String agence= request.getParameter("agence");
        String offre= request.getParameter("offre");
        String niveau= request.getParameter("niveau");
         String expertise= request.getParameter("expertise");
         String plafond= request.getParameter("plafond");
        
        String message = null;
        if(niveau.trim().isEmpty()||offre.trim().isEmpty()||nom.trim().isEmpty()||prenom.trim().isEmpty()||login.trim().isEmpty()||mdp.trim().isEmpty()||profil.trim().isEmpty()||agence.trim().isEmpty()){
            message = "Erreur - Vous n'avez pas rempli tous les champs obligatoires." + "<br/><a href=\"CreerContratEntraineur.jsp\">Clique ici </a>pour accéder au formulaire de creation.";
        }
        else {
            UtilisateurHardis ut = (UtilisateurHardis) sess.getAttribute("utilisateur");
            if(ut!=null){
                UtilisateurHardis o =gestionnaireHardisSession.rechercherUtilisateurHardisParLogin(login);
                if(o==null){
                ProfilTechnique profilt= null;
                NiveauHabilitation niveauH = null;
                Expertise exp = null;
                if(profil.equals("Admin")){
                    profilt = ProfilTechnique.Admin;
                }
                else if (profil.equals("Gestion")){
                    profilt = ProfilTechnique.Gestion;
                }
                else if (profil.equals("Visiteur")){
                    profilt = ProfilTechnique.Visiteur;
                }
                
                
                  if(niveau.equals("Consultant")){
                    niveauH = NiveauHabilitation.Consultant;
                }
                else if (niveau.equals("Referent")){
                        niveauH = NiveauHabilitation.Referent;
                }
                else if (niveau.equals("Porteur")){
                    niveauH = NiveauHabilitation.Porteur;
                }
                  
                     if(expertise.equals("Confirme")){
                    exp = Expertise.Confirme;
                }  else if(expertise.equals("Junior")){
                    exp = Expertise.Junior;
                }
                else    if(expertise.equals("Senior")){
                    exp = Expertise.Senior;
                }
                  
                      Long idagence = Long.valueOf(agence);
                
                o = gestionnaireHardisSession.creerUtilisateurHardis(nom, prenom, login, mdp, profilt, idagence, ut);
                
                Offre off = gestionnaireHardisSession.rechercheOffreParId(Long.valueOf(offre));
                
                ProfilMetier pm = gestionnaireHardisSession.creerProfilMetier(niveauH, exp, Float.valueOf(plafond), new String[1], ut);
                
                Offre_Profil_Util_CV offP = gestionnaireHardisSession.creerOffre_Profil_Util_CV(off.getId(), pm.getId(), o.getId(), "", ut, Float.valueOf(prix));
                
        try {
           mailHache = Helpers.sha1(login);
        } 
       catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ClientFacade.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
         SendMail send = new SendMail();
         String messa = "<p>Bonjour, <br> Veuillez cliquer <a href=\"http://localhost:8080/ProjetHardis-war/servEmployes?action=majMDP&email="+mailHache+"\">ici</a> pour créer votre mot de passe <br> Cordialement, Hardis</p> ";
         send.sendMail(login,"Création compte Hardis", messa);
       
               
               
                String nomentite = o.getNom();
                String classe = o.getClass().toString();
                message= " "+classe+":"+ nomentite+" créé avec succès !";
                }
                else{
                    message= "Erreur login "+o.getLogin() + "deja dans la base de données";
                }
            }
            else{
                message= "Erreur information non inserée dans la base de données";
            }
        }
        request.setAttribute("message", message);
    }
      protected void doActionModifierUtilisateur(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String pass = request.getParameter("pass");
        String message = null;
        if(pass.trim().isEmpty()){
            message = "Erreur - Vous n'avez pas rempli tous les champs obligatoires." + "<br/><a href=\"CreerContratEntraineur.jsp\">Clique ici </a>pour accéder au formulaire de creation.";
        }
        else {
            UtilisateurHardis ut = (UtilisateurHardis) sess.getAttribute("utilisateur");
            if(ut!=null){
                
               gestionnaireHardisSession.modifieUtilisateurHardisMDP(ut, pass);
            message= "Mot de passe modifié";
                

                
            }
            else{
                message= "Erreur information non inserée dans la base de données";
            }
        }
        request.setAttribute("message", message);
    }
      protected void doActionModifierProfilMetierUtilisateur(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String idutili = request.getParameter("idutili");
        String[] offreProf = request.getParameterValues("offreProf");
        String[] NivEx = request.getParameterValues("NivEx");
        String[] NivHab = request.getParameterValues("NivHab");
        String[] plafondPFCV = request.getParameterValues("plafondPFCV");
        String[] prixPFCV = request.getParameterValues("prixPFCV");
        String[] cvPFCV = request.getParameterValues("cvPFCV");
        String message = null;
        if(idutili.trim().isEmpty()||offreProf.length==0||NivEx.length==0 ||NivHab.length==0||plafondPFCV.length==0||prixPFCV.length==0||cvPFCV.length==0){
            message = "Erreur - Vous n'avez pas rempli tous les champs obligatoires.";
        } 
        else {
            UtilisateurHardis ut = (UtilisateurHardis) sess.getAttribute("utilisateur");
            if(ut!=null){
                Long iduili = Long.valueOf(idutili);
                UtilisateurHardis utili = gestionnaireHardisSession.rechercherUtilisateurHardisParId(iduili, ut);
                List<Long> idopcv = new ArrayList<>();
                List<Long> liidPM = new ArrayList<>();
                Long idPM = null;
                NiveauHabilitation niveauH =null;
                Expertise exp = null;
                for (String offre : offreProf ){
                    if(!offre.equalsIgnoreCase("vide")){
                    Long idoffre = Long.valueOf(offre); 
                    Offre offree = gestionnaireHardisSession.rechercherOffre(idoffre, utili);
                    idPM = Long.valueOf(0);
                    String liencvPFCV = "";
                    Float prixFCV = Float.valueOf(0);
                    Offre_Profil_Util_CV opcv = gestionnaireHardisSession.rechercheOPUCParUtilisateurEtOffre(utili, offree);;
                    if(opcv==null){
                    
                    opcv = gestionnaireHardisSession.creerOffre_Profil_Util_CV(idoffre, idPM, iduili, liencvPFCV, ut, prixFCV);}
                    else{
//                        opcv = gestionnaireHardisSession.rechercheOPUCParUtilisateurEtOffre(utili, offree);
                        Long idididi = opcv.getId();
                        gestionnaireHardisSession.modifierOffre_Profil_Util_CV( idididi ,idoffre, idPM, iduili, liencvPFCV, utili);
                    }
                    idopcv.add(opcv.getId());}
                    
                }             
                for (String prixpfcv : prixPFCV ){
                    if(!prixpfcv.equalsIgnoreCase("")){
                    for (Long iddopcv : idopcv ){
                        
                        Offre_Profil_Util_CV opcv = gestionnaireHardisSession.rechercherOffre_Profil_Util_CV(iddopcv, utili);
                        String liencvPFCV = "";
                        idPM = Long.valueOf(0);
                        Float prixFCV = Float.valueOf(prixpfcv);                                
                        Long idoffre = opcv.getOffre().getId();
                        gestionnaireHardisSession.modifierOffre_Profil_Util_CV(opcv.getId(), idoffre, idPM, iduili, liencvPFCV, utili);
                        }}
                }
                for (String cvpfcv : cvPFCV ){
                    if(!cvpfcv.equalsIgnoreCase("")){
                    for (Long iddopcv : idopcv ){
                        
                        Offre_Profil_Util_CV opcv = gestionnaireHardisSession.rechercherOffre_Profil_Util_CV(iddopcv, utili);
                        String liencvPFCV = cvpfcv;
                        idPM = Long.valueOf(0);
                        Float prixFCV = Float.valueOf(0);
                        Long idoffre = opcv.getOffre().getId();
                        gestionnaireHardisSession.modifierOffre_Profil_Util_CV(opcv.getId(), idoffre, idPM, iduili, liencvPFCV, utili);
                    }  }                 
                }
                for (String nivhab : NivHab ){
                    if(!nivhab.equalsIgnoreCase("vide")){
                    if(nivhab.equals("Consultant")){
                        niveauH = NiveauHabilitation.Consultant;
                    }
                    else if (nivhab.equals("Referent")){
                        niveauH = NiveauHabilitation.Referent;
                    }
                    else if (nivhab.equals("Porteur")){
                        niveauH = NiveauHabilitation.Porteur;
                    }
                    else if (nivhab.equals("vide")){
                        niveauH = NiveauHabilitation.vide;
                    }
                    exp = Expertise.Junior;
                    Float plafond = Float.valueOf(0);
                    ProfilMetier pm = gestionnaireHardisSession.creerProfilMetier(niveauH, exp, plafond, null, utili);
                    liidPM.add(pm.getId());
                }}
                for (String nivex : NivEx ){
                    if(!nivex.equalsIgnoreCase("vide")){
                    for (Long ididPM : liidPM ){
                    ProfilMetier pmm = gestionnaireHardisSession.rechercherProfilMetier(ididPM, utili);
                    if(nivex.equals("Confirme")){
                        exp = Expertise.Confirme;
                    }  
                    else if(nivex.equals("Junior")){
                        exp = Expertise.Junior;
                    }
                    else if(nivex.equals("Senior")){
                        exp = Expertise.Senior;
                    }
                    else if(nivex.equals("vide")){
                        exp = Expertise.vide;
                    }
                    niveauH = pmm.getNiveauHabilitation();
                    Float plafond = Float.valueOf(0);
                    gestionnaireHardisSession.modifierProfilMetier(pmm.getId(), niveauH, exp, plafond, null, utili);}
                }}
                for (String plafondpfcv : plafondPFCV ){
                    if(!plafondpfcv.equalsIgnoreCase("")){
                    for (Long ididPM : liidPM ){
                    ProfilMetier pmm = gestionnaireHardisSession.rechercherProfilMetier(ididPM, utili);                    
                    niveauH = pmm.getNiveauHabilitation();
                    exp = pmm.getNiveauExpertise();
                    Float plafond  = null;
                    if(plafondpfcv.equalsIgnoreCase("")){
                    plafond = Float.valueOf(0);}
                    else{plafond = Float.valueOf(plafondpfcv);}
                    gestionnaireHardisSession.modifierProfilMetier(pmm.getId(), niveauH, exp, plafond, null, utili);
                    }  }                  
                }   
                for (Long iddopcv : idopcv ){
                    
                    for (Long ididPM : liidPM ){
                        Offre_Profil_Util_CV opcv = gestionnaireHardisSession.rechercherOffre_Profil_Util_CV(iddopcv, utili);
                        String liencvPFCV = "";
                        idPM = ididPM ;
                        Float prixFCV = opcv.getPrixUnit();
                        Long idoffre = opcv.getOffre().getId();
                        gestionnaireHardisSession.modifierOffre_Profil_Util_CV(opcv.getId(), idoffre, idPM, iduili, liencvPFCV, utili);
                    }}
                request.setAttribute("utili",utili);
            message= "Mot de passe modifié";
                

            }
            else{
                message= "Erreur information non inserée dans la base de données";
            }
        }
        request.setAttribute("message", message);
    }
      
            
      protected void doActionMessageDevis(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String textmessage = request.getParameter("message");
        String iddevis = request.getParameter("idDev");
        String message = null;
        if(textmessage.trim().isEmpty()||iddevis.trim().isEmpty()){
            message = "Erreur - Vous n'avez pas rempli tous les champs obligatoires.";
        }
        else {
            UtilisateurHardis ut = (UtilisateurHardis) sess.getAttribute("utilisateur");
            if(ut!=null){
                Long id = Long.valueOf(iddevis);
                gestionnaireHardisSession.creerCommunicationHardis(textmessage, id, ut);
                message= "Message envoyé";
                

                
            }
            else{
                message= "Erreur information non inserée dans la base de données";
            }
        }
        request.setAttribute("message", message);
    }
      
      protected void doActionEnvoyerMail(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String emailto = request.getParameter("emailto");
        String subject = request.getParameter("subject");
        String messagemail = "";
        messagemail = request.getParameter("textmail");
        if(messagemail.equals("")){
        messagemail = request.getParameter("textemail");
        }
        String message = null;
        if(emailto.trim().isEmpty()||messagemail.trim().isEmpty()){
            message = "Erreur - Vous n'avez pas rempli tous les champs obligatoires.";
        }
        else {
            UtilisateurHardis ut = (UtilisateurHardis) sess.getAttribute("utilisateur");
            if(ut!=null){
                
               ContactMail cm=  gestionnaireHardisSession.creerContactMail(ut.getNom(), ut.getPrenom(), emailto, "", subject, messagemail, ut);
                gestionnaireHardisSession.modifReponduContactMail(cm.getId());
                message= "Message envoyé";
                SendMail send = new SendMail();                
                send.sendMail(emailto,subject, messagemail); 

                
            }
            else{
                message= "Erreur information non inserée dans la base de données";
            }
        }
        request.setAttribute("message", message);
    }
      
      protected void doActionAffecterDevis(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String ConsultantAffecte = request.getParameter("ConsultantAffecte");
        String idDevis = request.getParameter("iddev");
        String message = null;
            UtilisateurHardis ut = (UtilisateurHardis) sess.getAttribute("utilisateur");
            if(ut!=null){
                Long id = Long.valueOf(ConsultantAffecte);
                Long idref = ut.getId();
                Long iddevis = Long.valueOf(idDevis);
                Devis devis = gestionnaireHardisSession.rechercherDevis(iddevis, ut);
                java.util.Date d = new java.util.Date();
                gestionnaireHardisSession.creerHistoriqueTraitement(d, null, TypeUtilisateur.r, iddevis, id, idref, 0, ut);
                sess.setAttribute("devistraitement",devis);
                
            }
            else{
                message= "Erreur information non inserée dans la base de données";
            
        }
        request.setAttribute("message", message);
    }
      protected void doActionModifierAgenceEntreprise(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String agenceEntreprise = request.getParameter("AgenceEntreprise");
        String idEntreprise = request.getParameter("identreprise");
        String message = null;
            UtilisateurHardis ut = (UtilisateurHardis) sess.getAttribute("utilisateur");
            if(ut!=null){
                Long identre = Long.valueOf(idEntreprise);
                Long idagence = Long.valueOf(agenceEntreprise);
                gestionnaireHardisSession.modifieEntreprise(identre, idagence, "", null, "", "", 0, "", "", ut);
//                long identreprise, long idagence,  String nom, String[] listidinterlocuteurs , String codeContrat, String mdpEntreprise, long idadresse, String lienJustif, String numeroEnt, UtilisateurHardis hardis
                List<Entreprise> listeEntreprise = gestionnaireHardisSession.listEntreprise();
                if (listeEntreprise==null) listeEntreprise=new ArrayList<>();
                 List<Agence> listeAgence = new ArrayList<>(); 
                String test = request.getParameter("test");
                if(test!=null && !test.isEmpty()){
                    listeAgence = gestionnaireHardisSession.listAgence();
                    if (listeAgence==null) listeAgence=new ArrayList<>();
                    
                }
                request.setAttribute("listeAgence",listeAgence);
                request.setAttribute("listeEntreprise",listeEntreprise);
                
            }
            else{
                message= "Erreur information non inserée dans la base de données";
            
        }
        request.setAttribute("message", message);
    }
       protected void doActionRepondreContactMail(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String idContactMail = request.getParameter("idContactMail");
        String textContactMail = request.getParameter("textContactMail");
        String message = null;
            UtilisateurHardis ut = (UtilisateurHardis) sess.getAttribute("utilisateur");
            if(ut!=null){
                Long idutilisateur = ut.getId();
                Long contactMail = Long.valueOf(idContactMail);
                ContactMail cm=gestionnaireHardisSession.rechercheCommunication(contactMail);                
                String subjetcontactmail = "Reponse:"+cm.getSujet();                
               ContactMail cmreponse=  gestionnaireHardisSession.creerContactMail(ut.getNom(), ut.getPrenom(), ut.getLogin(),"00-00-00-00",subjetcontactmail, textContactMail,  ut);
                 gestionnaireHardisSession.modifReponduContactMail(contactMail);
                 gestionnaireHardisSession.modifReponduContactMail(cmreponse.getId());
                 SendMail send = new SendMail();                
                send.sendMail(cm.getEmail(),subjetcontactmail, textContactMail); 
                List<ContactMail> listeContactMail = gestionnaireHardisSession.listCommunicationNonReponduParUtilisateur(idutilisateur);
                sess.setAttribute("listeContactMail",listeContactMail);
                message= "Message envoyé avec sucess";
            } 
            else{
                message= "Erreur information non inserée dans la base de données";
            
        }
        request.setAttribute("message", message);
    }
    protected void doActionAffecterContactMail(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String idContactMail = request.getParameter("idContactMail");
        String AgentAffecterContactMail = request.getParameter("AgentAffecterContactMail");
        String message = null;
            UtilisateurHardis ut = (UtilisateurHardis) sess.getAttribute("utilisateur");
            if(ut!=null){
                Long identre = Long.valueOf(idContactMail);
                Long idagence = Long.valueOf(AgentAffecterContactMail);
                UtilisateurHardis utcontactmail = gestionnaireHardisSession.rechercherUtilisateurHardisParId(idagence, ut);
                gestionnaireHardisSession.majUtilisateurH(identre, utcontactmail);
//                long identreprise, long idagence,  String nom, String[] listidinterlocuteurs , String codeContrat, String mdpEntreprise, long idadresse, String lienJustif, String numeroEnt, UtilisateurHardis hardis
                List<ContactMail> listeContactMail = gestionnaireHardisSession.listContactMailNonRepondu();
                sess.setAttribute("listeContactMail",listeContactMail);
                
            }
            else{
                message= "Erreur information non inserée dans la base de données";
            
        }
        request.setAttribute("message", message);
    }
    protected void doActionValiderDevisNonStandard(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String idDevis = request.getParameter("idDevis");
        String message = null;
            UtilisateurHardis ut = (UtilisateurHardis) sess.getAttribute("utilisateur");
            if(ut!=null){
                Long idUtili = ut.getId();
                Long iddevis = Long.valueOf(idDevis);
                Devis devis = gestionnaireHardisSession.rechercherDevis(iddevis,  ut);
                Offre of = devis.getService().getOffre();   
                Offre_Profil_Util_CV unopcv = gestionnaireHardisSession.rechercheOPUCParUtilisateurEtOffre(ut, of);
                if (unopcv.getProfil().getPlafond()>=devis.getMontantDevis()){
                        gestionnaireHardisSession.modifieDevis(devis.getId(), devis.getDateDevis(), devis.getDateIntervSouhaitee(), devis.getIndicateurFact(), devis.getMontantDevis(), devis.getMotifRefus(), devis.getSaisieLibre(), Statut.Envoye, devis.getClient().getId(), devis.getAgence().getId(), ut);   
                        gestionnaireHardisSession.creerHistoriqueEtats(Statut.Envoye, devis.getId(), ut);
                        gestionnaireHardisSession.creerHistoriqueTraitement(null, null, TypeUtilisateur.v, iddevis, 0, 0, ut.getId(), ut);
                        java.util.Date nowDate = new java.util.Date();
                    /*    String server = "cpanel.freehosting.com";
                        String user = "lucialei";
                        String pass = "rj3fTOw378";
                        String remoteFile = "/public_html/FACT"+devis.getId()+".pdf";
                        String lien ="ftp://"+user+":"+pass+"@"+server+remoteFile;*/
                        gestionnaireHardisSession.creerFacture(nowDate, devis.getId(), devis.getMontantDevis(), 0, devis.getSaisieLibre(), ut, "");
                }
                else{
                    gestionnaireHardisSession.modifieDevis(devis.getId(), devis.getDateDevis(), devis.getDateIntervSouhaitee(), devis.getIndicateurFact(), devis.getMontantDevis(), devis.getMotifRefus(), devis.getSaisieLibre(), Statut.Transmettre_au_client, devis.getClient().getId(), devis.getAgence().getId(), ut);
                    gestionnaireHardisSession.creerHistoriqueEtats(Statut.Transmettre_au_client, devis.getId(), ut);
                    gestionnaireHardisSession.creerHistoriqueTraitement(null, null, TypeUtilisateur.v, iddevis, 0, 0, ut.getId(), ut);
                }                 
                List<Devis> listeDevis = new ArrayList<>();
                        List<HistoriqueTraitement> ht = gestionnaireHardisSession.rechercherHistoriqueTraitementParConsultant(ut.getId(), ut);
                        if (!ht.isEmpty()){
                            for (HistoriqueTraitement lesht: ht){
                                Devis dev = lesht.getDevis();
                                listeDevis.add(dev);
                            }
                        }    
                if (listeDevis==null) listeDevis=new ArrayList<>();
                sess.setAttribute("listeDevis",listeDevis);
                sess.setAttribute("devistraitement",devis);
            }
            else{
                message= "Erreur information non inserée dans la base de données";
            
        }
        request.setAttribute("message", message);
    }
    
    protected void doActionAjouterDocumentAUnDevis(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String idDevis = request.getParameter("idDevis");
        String descriptDocu = request.getParameter("DescriptionDocument");
        String lienDocument = request.getParameter("DocumentDevis");
        String message = null;
            UtilisateurHardis ut = (UtilisateurHardis) sess.getAttribute("utilisateur");
            if(ut!=null){
                Long idUtili = ut.getId();
                Long iddevis = Long.valueOf(idDevis);
                Devis devis = gestionnaireHardisSession.rechercherDevis(iddevis,  ut);
                HistoriqueDevis hd = gestionnaireHardisSession.rechercherUnHistoriqueDevisParUtilisateur(iddevis);
                gestionnaireHardisSession.creerDocument(descriptDocu, lienDocument, hd.getId(), ut, TypeDoc.p.toString());
                List<Devis> listeDevis = new ArrayList<>();
                        List<HistoriqueTraitement> ht = gestionnaireHardisSession.rechercherHistoriqueTraitementParConsultant(ut.getId(), ut);
                        if (!ht.isEmpty()){
                            for (HistoriqueTraitement lesht: ht){
                                Devis dev = lesht.getDevis();
                                listeDevis.add(dev);
                            }
                        }    
                          if (listeDevis==null) listeDevis=new ArrayList<>();
                          sess.setAttribute("listeDevis",listeDevis);
                          sess.setAttribute("devistraitement",devis);
                          
            }
            else{
                message= "Erreur information non inserée dans la base de données";
            
        }
        request.setAttribute("message", message);
    }
    
    
    protected void doActionRelancerDevisNonStandard(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String idDevis = request.getParameter("idDevis");
        String message = null;
            UtilisateurHardis ut = (UtilisateurHardis) sess.getAttribute("utilisateur");
            if(ut!=null){
                Long idUtili = ut.getId();
                Long iddevis = Long.valueOf(idDevis);
                Devis devis = gestionnaireHardisSession.rechercherDevis(iddevis,  ut);
                SendMail send = new SendMail();
                String messa = "<p>Bonjour, <br> Vous avez envoyée un devis avec nous, mais il parait qu'il manque quelque informations afin que nous puissions traiter vos devis cliquer <a href=\"\">ici</a> pour finaliser votre devis. <br> Si vous avez des questions, n'hesitez pas à nous contacter<br> Cordialement, Hardis</p> ";
                send.sendMail(devis.getClient().getLogin(),"Devis Hardis: DEV"+devis.getId(), messa);                
                List<Devis> listeDevis = new ArrayList<>();
                        List<HistoriqueTraitement> ht = gestionnaireHardisSession.rechercherHistoriqueTraitementParConsultant(ut.getId(), ut);
                        if (!ht.isEmpty()){
                            for (HistoriqueTraitement lesht: ht){
                                Devis dev = lesht.getDevis();
                                listeDevis.add(dev);
                            }
                        }    
                          if (listeDevis==null) listeDevis=new ArrayList<>();
                          sess.setAttribute("listeDevis",listeDevis);
                          sess.setAttribute("devistraitement",devis);
                          
            }
            else{
                message= "Erreur information non inserée dans la base de données";
            
        }
        request.setAttribute("message", message);
    }
    
    protected void doActionCreer1ereFacture(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String idDevis = request.getParameter("idDevis");
        String montDevis = request.getParameter("MontantDevis");
        String message = null;
            UtilisateurHardis ut = (UtilisateurHardis) sess.getAttribute("utilisateur");
            if(ut!=null){
                Long idUtili = ut.getId();
                Long iddevis = Long.valueOf(idDevis);
                float montdevis = 0;
                Devis devis = gestionnaireHardisSession.rechercherDevis(iddevis,  ut);
                java.util.Date nowDate = new java.util.Date();
                if(montDevis!=null&&!montDevis.equals("")){
                montdevis = Float.valueOf(montDevis);
                }
                else{
                    montdevis = devis.getMontantDevis();
                }
                Facture f =  gestionnaireHardisSession.creerFacture(nowDate, iddevis, montdevis, 0, "", ut, "");
             
/*
                gestionnaireHardisSession.payerFacture(f.getId());
                gestionnaireHardisSession.changerStatut(devis, "Acompte_regle");
                gestionnaireHardisSession.majDateDPresta(devis.getId());*/
                List<Devis> listeDevis = new ArrayList<>();
                        List<HistoriqueTraitement> ht = gestionnaireHardisSession.rechercherHistoriqueTraitementParConsultant(ut.getId(), ut);
                        if (!ht.isEmpty()){
                            for (HistoriqueTraitement lesht: ht){
                                Devis dev = lesht.getDevis();
                                listeDevis.add(dev);
                            }
                        }    
                          if (listeDevis==null) listeDevis=new ArrayList<>();
                          sess.setAttribute("listeDevis",listeDevis);
                          sess.setAttribute("devistraitement",devis);
                          message= "Facture crée ";
                          
            }
            else{
                message= "Erreur information non inserée dans la base de données";
            
        }
        request.setAttribute("message", message);
    }
    protected void doActionPrestationtermineeDevis(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String idDevis = request.getParameter("idDevis");
        String message = null;
            UtilisateurHardis ut = (UtilisateurHardis) sess.getAttribute("utilisateur");
            if(ut!=null){
                Long idUtili = ut.getId();
                Long iddevis = Long.valueOf(idDevis);
                Devis devis = gestionnaireHardisSession.rechercherDevis(iddevis,  ut);
                Offre of = devis.getService().getOffre();
                java.util.Date nowDate = new java.util.Date();
                gestionnaireHardisSession.modifieDevis(devis.getId(), devis.getDateDevis(), devis.getDateIntervSouhaitee(), devis.getIndicateurFact(), devis.getMontantDevis(), devis.getMotifRefus(), devis.getSaisieLibre(), Statut.Presta_terminee, devis.getClient().getId(), devis.getAgence().getId(), ut);
                gestionnaireHardisSession.modifDateFinDevis(devis, nowDate);
                gestionnaireHardisSession.creerHistoriqueEtats(Statut.Presta_terminee, devis.getId(), ut); 
                /*String server = "cpanel.freehosting.com";
                String user = "lucialei";
                String pass = "rj3fTOw378";
                String remoteFile = "/public_html/FACT"+devis.getId()+".pdf";
                String lien ="ftp://"+user+":"+pass+"@"+server+remoteFile;*/
                gestionnaireHardisSession.creerFacture(nowDate, devis.getId(), devis.getMontantDevis(), 0, devis.getSaisieLibre(), ut, "");
                List<Devis> listeDevis = new ArrayList<>();
                        List<HistoriqueTraitement> ht = gestionnaireHardisSession.rechercherHistoriqueTraitementParConsultant(ut.getId(), ut);
                        if (!ht.isEmpty()){
                            for (HistoriqueTraitement lesht: ht){
                                Devis dev = lesht.getDevis();
                                listeDevis.add(dev);
                            }
                        }    
                if (listeDevis==null) listeDevis=new ArrayList<>();
                sess.setAttribute("listeDevis",listeDevis);
                sess.setAttribute("devistraitement",devis);
                
            }
            else{
                message= "Erreur information non inserée dans la base de données";
            
        }
        request.setAttribute("message", message);
    }
    protected void doActionAffecterConsultantAUnDevis(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String iddevis = request.getParameter("iddev");
        String numJour = request.getParameter("numJour");        
        String[] idConsultant = request.getParameterValues("idConsultant");
        String idage = request.getParameter("idage");
        String idcli = request.getParameter("idcli");
        String message = null;
            UtilisateurHardis ut = (UtilisateurHardis) sess.getAttribute("utilisateur");
            if(ut!=null){
                Long idDevis = Long.valueOf(iddevis);
                Long idAgence = Long.valueOf(idage);
                Long idClient = Long.valueOf(idcli);
                Devis devis = gestionnaireHardisSession.rechercherDevis(idDevis, ut);
                Agence agence = gestionnaireHardisSession.rechercherAgenceParId(idAgence);
                Client client = gestionnaireHardisSession.rechercherClient(idClient, null, null, ut);
                float somm = 0;
                
                for (String consult : idConsultant){
                Long idconsultant = Long.valueOf(consult);
                UtilisateurHardis utili = gestionnaireHardisSession.rechercherUtilisateurHardisParId(idconsultant, ut);
                float prixUni = gestionnaireHardisSession.recherchePrixOffreC(utili, devis.getService().getOffre());
                somm +=prixUni;
                gestionnaireHardisSession.creerHistoriqueTraitement(devis.getDateIntervSouhaitee() , null, TypeUtilisateur.p, idDevis, idconsultant, 0, ut.getId(), ut);
                }
                Long idUtili = ut.getId();
                float jours = Float.valueOf(numJour);
                somm*=jours;
                Offre of = devis.getService().getOffre();   
                Offre_Profil_Util_CV unopcv = gestionnaireHardisSession.rechercheOPUCParUtilisateurEtOffre(ut, of);
                if (unopcv!=null){
                    
                if (unopcv.getProfil().getPlafond()>=devis.getMontantDevis()){
                        gestionnaireHardisSession.modifieDevis(devis.getId(), devis.getDateDevis(), devis.getDateIntervSouhaitee(), devis.getIndicateurFact(), somm, devis.getMotifRefus(), devis.getSaisieLibre(), Statut.Envoye, devis.getClient().getId(), devis.getAgence().getId(), ut);   
                        gestionnaireHardisSession.creerHistoriqueEtats(Statut.Envoye, devis.getId(), ut);
                        gestionnaireHardisSession.creerFacture(new java.util.Date(), devis.getId(), devis.getMontantDevis(), 0, devis.getSaisieLibre(), ut, "");

                        java.util.Date nowDate = new java.util.Date();                        
                        message= "Devis: DEV"+iddevis+" validé!";
                }
                else{
                    gestionnaireHardisSession.modifieDevis(devis.getId(), devis.getDateDevis(), devis.getDateIntervSouhaitee(), devis.getIndicateurFact(),somm, devis.getMotifRefus(), devis.getSaisieLibre(), Statut.Transmettre_au_client, devis.getClient().getId(), devis.getAgence().getId(), ut);
                    gestionnaireHardisSession.creerHistoriqueEtats(Statut.Transmettre_au_client, devis.getId(), ut);
                    message= "Vous n'avez pas de plafond pour valider le devis DEV:"+iddevis;
                }}                 
                List<Devis> listeDevis = new ArrayList<>();
                        List<HistoriqueTraitement> ht = gestionnaireHardisSession.rechercherHistoriqueTraitementParConsultant(ut.getId(), ut);
                        if (!ht.isEmpty()){
                            for (HistoriqueTraitement lesht: ht){
                                Devis dev = lesht.getDevis();
                                listeDevis.add(dev);
                            }
                        }    
                if (listeDevis==null) listeDevis=new ArrayList<>();
                Devis devisapres = gestionnaireHardisSession.rechercherDevis(idDevis, ut);
                sess.setAttribute("listeDevis",listeDevis);
                sess.setAttribute("devistraitement",devisapres);
            }
            else{
                message= "Erreur information non inserée dans la base de données";
            
        }
        request.setAttribute("message", message);
    }
    
    protected void doActionEnvoyerDevis(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String idDevis = request.getParameter("idDevis");
        String doc = request.getParameter("docenvoye");
        String client = request.getParameter("idclient");
        String message = null;
            UtilisateurHardis ut = (UtilisateurHardis) sess.getAttribute("utilisateur");
            if(ut!=null){
                Long idUtili = ut.getId();
                Long iddevis = Long.valueOf(idDevis);
                Devis devis = gestionnaireHardisSession.rechercherDevis(iddevis,  ut);
                Long iddoc = Long.valueOf(doc);
                Document docu =gestionnaireHardisSession.rechercherDocument(iddoc, ut);
                java.util.Date nowDate = new java.util.Date();
                gestionnaireHardisSession.modifieDevis(devis.getId(), devis.getDateDevis(), devis.getDateIntervSouhaitee(), devis.getIndicateurFact(), devis.getMontantDevis(), devis.getMotifRefus(), devis.getSaisieLibre(), Statut.Presta_terminee, devis.getClient().getId(), devis.getAgence().getId(), ut);
                gestionnaireHardisSession.modifDateFinDevis(devis, nowDate);
                gestionnaireHardisSession.creerHistoriqueEtats(Statut.Presta_terminee, devis.getId(), ut); 
                String server = "cpanel.freehosting.com";
                String user = "lucialei";
                String pass = "rj3fTOw378";
                String remoteFile = "/public_html/FACT"+devis.getId()+".pdf";
                String lien ="ftp://"+user+":"+pass+"@"+server+remoteFile;
                gestionnaireHardisSession.creerFacture(nowDate, devis.getId(), devis.getMontantDevis(), 0, devis.getSaisieLibre(), ut, lien);
                List<Devis> listeDevis = new ArrayList<>();
                        List<HistoriqueTraitement> ht = gestionnaireHardisSession.rechercherHistoriqueTraitementParConsultant(ut.getId(), ut);
                        if (!ht.isEmpty()){
                            for (HistoriqueTraitement lesht: ht){
                                Devis dev = lesht.getDevis();
                                listeDevis.add(dev);
                            }
                        }    
                if (listeDevis==null) listeDevis=new ArrayList<>();
                sess.setAttribute("listeDevis",listeDevis);
                sess.setAttribute("devistraitement",devis);
                
            }
            else{
                message= "Erreur information non inserée dans la base de données";
            
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
