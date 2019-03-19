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
import Entites.Devis;
import Entites.Disponibilite;
import Entites.Document;
import Entites.EchangeTel;
import Entites.Expertise;
import Entites.Facturation;
import Entites.FacturationFrais;
import Entites.Helpers;
import Entites.HistoriqueDevis;
import Entites.HistoriqueEtats;
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
import Entites.TypeService;
import Entites.UtilisateurHardis;
import Facades.ClientFacade;
import Session.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.sql.Date;
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
        

        String act = request.getParameter("action");
        
        if((act==null)||(act.equals("vide")))
            {   
                request.setAttribute("message","Bienvenue s'il vous plait remplir votre login et mot de passe");
                jspClient="/Admin/Login.jsp";
            }
            else if(act.equals("LoginAdmin")){
                String login = request.getParameter("login");
                String pass = request.getParameter("pass");
                if(!(login.trim().isEmpty())||!(pass.trim().isEmpty())){
                UtilisateurHardis utilisateur = administrateurHardisSession.authentificationUtilisateurHardis(login, pass);
                    if(utilisateur!=null){
                        request.setAttribute("message","Bienvenue: "+ utilisateur.getNom());
                        sess.setAttribute("utilisateur", utilisateur);
                        List<Communication> listeCommunication= administrateurHardisSession.rechercherCommunication(0, utilisateur.getId(), utilisateur);
                        List<Notification> listeNotif = administrateurHardisSession.getNotifsAdmin(utilisateur);
                        List<Devis> listeDevis = administrateurHardisSession.listDevis();
                        List<Client> listeClient = administrateurHardisSession.listClient();
                        if (listeCommunication==null) listeCommunication=new ArrayList<>();
                        if (listeNotif==null) listeNotif=new ArrayList<>();
                        if (listeDevis==null) listeDevis=new ArrayList<>();
                        if (listeClient==null) listeClient=new ArrayList<>();
                        sess.setAttribute("listeCommunication",listeCommunication);
                        sess.setAttribute("listeNotif",listeNotif);
                        sess.setAttribute("listeDevis",listeDevis);
                        sess.setAttribute("listeClient",listeClient);
                        jspClient="/Admin/dashboardAdmin.jsp";    
                                }   
                    else{
                        request.setAttribute("message","Utilisateur non trouvé");
                        jspClient="/Admin/Login.jsp";
                    }
                }
                else{
                    request.setAttribute("message","Login ou/et password non rempli");
                    jspClient="/Admin/Login.jsp";
                }
                
            }
            else if(act.equals("MotDePassOublie"))
            {

                jspClient="/Admin/QSRS.jsp";
            }
            else if(act.equals("DSRSAdmin")){
                String Login = request.getParameter("Login");
                String QS = request.getParameter("QS");
                String RS = request.getParameter("RS");
                UtilisateurHardis ut = administrateurHardisSession.rechercherUtilisateurHardisParLogin(Login);
                if(ut!=null){
                    if(!(QS.trim().isEmpty())||!(RS.trim().isEmpty())){
                        UtilisateurHardis  utilisateur = administrateurHardisSession.recupererUtilisateurHardisQSRS(QS, RS);
                        if(utilisateur != null){
                            if(utilisateur.equals(ut)){
                                sess.setAttribute("utilisateur", utilisateur);
                                jspClient="/Admin/newpass.jsp";    
                            }   
                            else{
                                request.setAttribute("message","Question Secret ou/et Response secret ne correspond pas au Login");
                                jspClient="/Admin/Login.jsp";
                            }
                        }
                        else{
                            request.setAttribute("message","Question Secret ou/et Response secret ne correspond pas au Login");
                            jspClient="/Admin/Login.jsp";
                        }
                    }
                    else{
                        request.setAttribute("message","Question Secret ou/et Response secret non rempli");
                        jspClient="/Admin/Login.jsp";
                    }
                }
                else{
                request.setAttribute("message","Utilisateur non trouvé");
                jspClient="/Admin/Login.jsp";}
            }
            else if(act.equals("ModifierPass"))
            {
               UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("utilisateur");
               doActionModifierUtilisateur(request,response);
               jspClient="/Admin/Login.jsp";
            }
            else if(act.equals("Menu"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                jspClient="/Admin/dashboardAdmin.jsp";
                request.setAttribute("message","pas d'information");
            }
            else if(act.equals("CreerAgence"))
            {
                jspClient="/Admin/creerAgence.jsp";
            }
            else if(act.equals("InsererAgence"))
            {
               UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
               doActionCreerAgence(request,response);
               jspClient="/Admin/dashboardAdmin.jsp";
            }
            else if(act.equals("AfficherAgence"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                List<Agence> listeAgence = administrateurHardisSession.listAgence();
                if (listeAgence==null) listeAgence=new ArrayList<>();
                request.setAttribute("listeAgence",listeAgence);
                jspClient="/Admin/afficherAgence.jsp";
            }
            else if(act.equals("RechercherAgence"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                List<Agence> listeAgence= new ArrayList<>();
                String champ = request.getParameter("champ");
                Agence a = administrateurHardisSession.rechercherAgence(0, champ, utilisateur);
                if(a!=null){
                    listeAgence.add(a);
                }else{
                listeAgence=new ArrayList<>();}
                request.setAttribute("listeAgence",listeAgence);
                jspClient="/Admin/afficherAgence.jsp";
            }
            else if(act.equals("formAgence"))
            {
                UtilisateurHardis utilisateur  = (UtilisateurHardis) sess.getAttribute("utilisateur");
                String champ = request.getParameter("idAgence");
                Long idagence = Long.valueOf(champ);
                Agence a = administrateurHardisSession.rechercherAgenceParId(idagence);
                request.setAttribute("agence",a);
                jspClient="/Admin/modifierAgence.jsp";
            }
             else if(act.equals("ModifierAgence"))
            {
               UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
               doActionModifierAgence(request,response);
               jspClient="/Admin/dashboardAdmin.jsp";
            }
            else if(act.equals("CreerAdresse"))
            {
                UtilisateurHardis utilisateur  = (UtilisateurHardis) sess.getAttribute("utilisateur");
                List<Communication> listeCommunication= administrateurHardisSession.rechercherCommunication(0, utilisateur.getId(), utilisateur);
                List<Notification> listeNotif = administrateurHardisSession.getNotifsAdmin(utilisateur);
                List<Devis> listeDevis = administrateurHardisSession.listDevis();
                List<Client> listeClient = administrateurHardisSession.listClient();
                if (listeCommunication==null) listeCommunication=new ArrayList<>();
                if (listeNotif==null) listeNotif=new ArrayList<>();
                if (listeDevis==null) listeDevis=new ArrayList<>();
                if (listeClient==null) listeClient=new ArrayList<>();
                request.setAttribute("listeCommunication",listeCommunication);
                request.setAttribute("listeNotif",listeNotif);
                request.setAttribute("listeDevis",listeDevis);
                request.setAttribute("listeClient",listeClient);
               jspClient="/Admin/creerAdresse.jsp";
            }
            else if(act.equals("InsererAdresse"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
               doActionCreerAdresse(request,response);
               jspClient="/Admin/dashboardAdmin.jsp";
               
            }
            else if(act.equals("AfficherAdresse"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                List<Adresse> listeAdresse = administrateurHardisSession.listAdresse();
                if (listeAdresse==null) listeAdresse=new ArrayList<>();
                request.setAttribute("listeAdresse",listeAdresse);
                jspClient="/Admin/afficherAdresse.jsp";
            }
            else if(act.equals("CreerAtelier"))
            {
               jspClient="/Admin/creerAtelier.jsp";
            }
             else if(act.equals("InsererAtelier"))
            {
               UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
               doActionCreerAtelier(request,response);
               jspClient="/Admin/dashboardAdmin.jsp";
            }
             else if(act.equals("AfficherAtelier"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                List<Atelier> listeAtelier = administrateurHardisSession.listAtelier();
                if (listeAtelier==null) listeAtelier=new ArrayList<>();
                request.setAttribute("listeAtelier",listeAtelier);
                jspClient="/Admin/afficherAtelier.jsp";
            }
             else if(act.equals("listeDevisComunnication"))
            {
                List<Devis> listdevis= administrateurHardisSession.listDevis();
                if (listdevis==null) listdevis=new ArrayList<>();
                request.setAttribute("listeDevis",listdevis);
                jspClient="/Admin/CreerCommunication.jsp";
            }
            else if(act.equals("InsererCommunication"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                doActionCreerCommunication(request,response);
            }
            else if(act.equals("CreerDisponibilite"))
            {
               jspClient="/Admin/creerDisponibilite.jsp";
            }
            else if(act.equals("InsererDisponibilite"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                doActionCreerDisponibilite(request,response);
                jspClient="/Admin/dashboardAdmin.jsp";
            }
            else if(act.equals("listeHistoriquesDevisDoc"))
            {
                List<HistoriqueDevis> listhistdevis= administrateurHardisSession.listHistoriqueDevis();
                if (listhistdevis==null) listhistdevis=new ArrayList<>();
                request.setAttribute("listHistDevis",listhistdevis);
                jspClient="/Admin/CreerCommunication.jsp";
            }
            else if(act.equals("InsererDocument"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                doActionCreerDocument(request,response);
                jspClient="/Admin/dashboardAdmin.jsp";
            }
             else if(act.equals("listeDevisEchangeTel"))
            {
                List<Devis> listdevis= administrateurHardisSession.listDevis();
                if (listdevis==null) listdevis=new ArrayList<>();
                request.setAttribute("listeDevis",listdevis);
                jspClient="/Admin/CreerEchangeTel.jsp";
            }
            else if(act.equals("InsererEchangeTel"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                doActionCreerEchangeTel(request,response);
                jspClient="/Admin/dashboardAdmin.jsp";
            }
            else if(act.equals("listesPourModifierDevis"))
            {
                List<Agence> listagence= administrateurHardisSession.listAgence();
                if (listagence==null) listagence=new ArrayList<>();
                request.setAttribute("listeAgence",listagence);
                List<Client> listclient= administrateurHardisSession.listClient();
                if (listclient==null) listclient=new ArrayList<>();
                request.setAttribute("listeClient",listclient);
                List<Devis> listdevis= administrateurHardisSession.listDevis();
                if (listdevis==null) listdevis=new ArrayList<>();
                request.setAttribute("listeDevis",listdevis);
                jspClient="/Admin/ModifierDevis.jsp";
            }
            else if(act.equals("modifierDevis"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                doActionModifierDevis(request,response);
                jspClient="/Admin/dashboardAdmin.jsp";
            }
            else if(act.equals("listesCreerLivrable"))
            {
                
                jspClient="/Admin/creerLivrable.jsp";
            }
            else if(act.equals("InsererLivrable"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                doActionCreerLivrable(request,response);
                jspClient="/Admin/dashboardAdmin.jsp";
            }
            else if(act.equals("AfficherLivrable"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                List<Livrable> listeLivrable = administrateurHardisSession.listLivrable();
                if (listeLivrable==null) listeLivrable=new ArrayList<>();
                request.setAttribute("listeLivrable",listeLivrable);
                jspClient="/Admin/afficherLivrable.jsp";
            }
            else if(act.equals("CreerOffre"))
            {
               jspClient="/Admin/creerOffre.jsp";
            }
            else if(act.equals("InsererOffre"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                doActionCreerOffre(request,response);
                jspClient="/Admin/dashboardAdmin.jsp";
            }
            else if(act.equals("AfficherOffre"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                List<Offre> listeOffre = administrateurHardisSession.listOffre();
                if (listeOffre==null) listeOffre=new ArrayList<>();
                request.setAttribute("listeOffre",listeOffre);
                jspClient="/Admin/afficherOffre.jsp";
            }
            else if(act.equals("listesCreerService"))
            {
                List<Offre> listoffre= administrateurHardisSession.listOffre();
                if (listoffre==null) listoffre=new ArrayList<>();
                request.setAttribute("listoffre",listoffre);
                jspClient="/Admin/creerService.jsp";
            }
            else if(act.equals("InsererService"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                doActionCreerService(request,response);
                jspClient="/Admin/dashboardAdmin.jsp";
            }
            else if(act.equals("AfficherService"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("utilisateur");
                List<Service> listeService = administrateurHardisSession.listServiceNonStandard();
                if (listeService==null) listeService=new ArrayList<>();
                request.setAttribute("listeService",listeService);
                jspClient="/Admin/afficherService.jsp";
            }
            else if(act.equals("listesCreerServiceStandard"))
            {
                List<Offre> listoffre= administrateurHardisSession.listOffre();
                List<Atelier> listatelier= administrateurHardisSession.listAtelier();
                List<Livrable> listlivrable = administrateurHardisSession.listLivrable();
                if (listoffre==null) listoffre=new ArrayList<>();
                if (listatelier==null) listatelier=new ArrayList<>();
                if (listlivrable==null) listlivrable=new ArrayList<>();
                request.setAttribute("listoffre",listoffre);
                request.setAttribute("listatelier",listatelier);
                request.setAttribute("listlivrable",listlivrable);
                jspClient="/Admin/creerServiceStandard.jsp";
            }
            else if(act.equals("InsererServiceStandard"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                doActionCreerServiceStandard(request,response);
                jspClient="/Admin/dashboardAdmin.jsp";
            }
            else if(act.equals("AfficherServiceStandard"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("utilisateur");
                List<ServiceStandard> listeServiceStandard = administrateurHardisSession.listServiceStandard();
                if (listeServiceStandard==null) listeServiceStandard=new ArrayList<>();
                request.setAttribute("listeServiceStandard",listeServiceStandard);
                jspClient="/Admin/afficherServiceStandard.jsp";
            }
            else if(act.equals("listesCreerUtiliateurHardis"))
            {
                List<Agence> listagence= administrateurHardisSession.listAgence();
                List<Offre> listOffres = administrateurHardisSession.listOffre();
                if (listagence==null) listagence=new ArrayList<>();
                request.setAttribute("listagence",listagence);
                request.setAttribute("listOffres",listOffres);
                jspClient="/Admin/creerUtilisateur.jsp";
            }
            else if(act.equals("InsererUtilisateurHardis"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                doActionCreerUtilisateur(request,response);
                jspClient="/Admin/dashboardAdmin.jsp";
            }
             else if(act.equals("AfficherUtilisateur"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("utilisateur");
                List<UtilisateurHardis> listeUtilisateurHardis = administrateurHardisSession.listUtilisateurHardis();
                if (listeUtilisateurHardis==null) listeUtilisateurHardis=new ArrayList<>();
                request.setAttribute("listeUtilisateurHardis",listeUtilisateurHardis);
                jspClient="/Admin/afficherUtilisateur.jsp";
            }
             else if(act.equals("listesCertifierClient"))
            {
                List<Client> listClient= administrateurHardisSession.listClientNonCertifies();
                if (listClient==null) listClient=new ArrayList<>();
                request.setAttribute("listClient",listClient);
                jspClient="/Admin/certifierClient.jsp";
            }
             else if(act.equals("RechercherClient"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                List<Client> listClient= new ArrayList<>();
                String mailClient = request.getParameter("mail");
                Client client = administrateurHardisSession.rechercherClient(0, jspClient, mailClient, utilisateur);
                if(client!=null){
                    listClient.add(client);
                }else{
                listClient=new ArrayList<>();}
                request.setAttribute("listClient",listClient);
                jspClient="/Admin/certifierClient.jsp";
            }
            else if(act.equals("CertifierClient"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                doActionCertifierClient(request,response);
                jspClient="/Admin/dashboardAdmin.jsp";
            }
            else if(act.equals("RechercherAtelier"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                List<Client> listClient= new ArrayList<>();
                String mailClient = request.getParameter("mail");
                Client client = administrateurHardisSession.rechercherClient(0, jspClient, mailClient, utilisateur);
                if(client!=null){
                    listClient.add(client);
                }else{
                listClient=new ArrayList<>();}
                request.setAttribute("listClient",listClient);
                jspClient="/Admin/certifierClient.jsp";
            }
         else if(act.equals("listesAfficherClient"))
            {
                List<Client> listClient= administrateurHardisSession.listClientNonCertifies();
                if (listClient==null) listClient=new ArrayList<>();
                request.setAttribute("listClient",listClient);
                jspClient="/Admin/afficherClient.jsp";
            }
             else if(act.equals("RechercherClient1"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                List<Client> listClient= new ArrayList<>();
                String mailClient = request.getParameter("champ");
                Client client = administrateurHardisSession.rechercherClient(0, jspClient, mailClient, utilisateur);
                if(client!=null){
                    listClient.add(client);
                }else{
                listClient=new ArrayList<>();}
                request.setAttribute("listClient",listClient);
                jspClient="/Admin/afficherClient.jsp";
            }
            else if(act.equals("formClient"))
            {
                UtilisateurHardis utilisateur  = (UtilisateurHardis) sess.getAttribute("utilisateur");
                String champ = request.getParameter("idClient");
                Long idagence = Long.valueOf(champ);
                Client a = administrateurHardisSession.rechercherClient(idagence, "", "", utilisateur);
                request.setAttribute("client",a);
                jspClient="/Admin/detailClient.jsp";
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

        RequestDispatcher Rd;
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
                Agence agence =  administrateurHardisSession.rechercherAgence(0, nomAgence, ut);
                if(agence!=null){
                    message= "Erreur agence: "+nomAgence +" déjà dans la base de données";
                }
                else{
                    administrateurHardisSession.creerAgence(nomAgence, ut);
                    agence = administrateurHardisSession.rechercherAgence(0, nomAgence, ut);
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
                Agence agence =  administrateurHardisSession.rechercherAgence(0, nomAgence, ut);
                if(agence!=null){
                    message= "Erreur agence: "+nomAgence +" déjà dans la base de données";
                }
                else{
                    Long id = Long.valueOf(idAgence);
                    administrateurHardisSession.modifierAgence(id, nomAgence, ut);
                    agence = administrateurHardisSession.rechercherAgence(0, nomAgence, ut);
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
                Adresse adresse = administrateurHardisSession.creerAdresse(numRue, NomRue, Ville, CodePostal, ut);
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
        String message = null;
        if(idClient.trim().isEmpty()){
            message = "Erreur - Vous n'avez pas rempli tous les champs obligatoires.";
        }
        else {
            UtilisateurHardis ut = (UtilisateurHardis) sess.getAttribute("utilisateur");
            if(ut!=null){
                Long id = Long.valueOf(idClient);
                administrateurHardisSession.certifieClient(id, ut);
                Client adresse = administrateurHardisSession.rechercherClient(id, "", "", ut);
                String nomentite = adresse.getNom() ;
                String classe = adresse.getClass().toString();
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
                Atelier atelier = administrateurHardisSession.rechercherAtelier(0, nomAtelier, ut);
                if(atelier!=null){
                    message= "Erreur atelier: "+nomAtelier +" déjà dans la base de données";
                }else{
                administrateurHardisSession.creerAtelier(nomAtelier, ut);
                atelier = administrateurHardisSession.rechercherAtelier(0, nomAtelier, ut);
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
                Document document = administrateurHardisSession.creerDocument(descriptif, liendoc, idhistorique, ut, typeDoc);
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
    protected void doActionModifierDevis(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String facturation = request.getParameter("facturationDevis"); 
        String montantdevis= request.getParameter("montantdevisDevis"); 
        String motifrefus = request.getParameter("motifrefusDevis");
        String saisielibre= request.getParameter("saisielibreDevis"); 
        String statut = request.getParameter("statutDevis");
        String agence = request.getParameter("agenceDevis");
        String devis = request.getParameter("Devis");
        String client = request.getParameter("clientDevis");
        String message = null;
            UtilisateurHardis ut = (UtilisateurHardis) sess.getAttribute("utilisateur");
            if(ut!=null){
                Facturation fact = null;
                if(facturation.equals("Auto")){
                    fact = Facturation.Auto;
                }
                else if (facturation.equals("Manuel")){
                    fact = Facturation.Manuel;
                }
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
                Long idagence = Long.valueOf(agence);
                Long iddevis = Long.valueOf(devis);
                Long idclient = Long.valueOf(client);
                Statut sta = administrateurHardisSession.rechercherStatutParDevis(iddevis, ut);
                Float montantadevis = Float.valueOf(montantdevis);
                administrateurHardisSession.modifieDevis(iddevis, null, null, fact, montantadevis, motifrefus, saisielibre, statuts, idclient, idagence, ut);
                if(sta!=statuts){
                    List<HistoriqueEtats> liste = administrateurHardisSession.rechercherHistoriqueEtatsParDevis(iddevis, ut);
                    HistoriqueEtats he = administrateurHardisSession.creerHistoriqueEtats(statuts, iddevis, ut);
                    liste.add(he);
                }
                Devis o= administrateurHardisSession.rechercherDevis(iddevis, 0, ut);
                String nomentite = o.getId().toString();
                String classe = o.getClass().toString();
                message= " "+classe+":"+ nomentite+" créé avec succès !";
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
                
                Livrable o = administrateurHardisSession.creerLivrable(nom,  ut);
                
                o = administrateurHardisSession.creerLivrable(nom, ut);
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
                Offre o =administrateurHardisSession.rechercherOffreLibelle(libelle, ut);
                if(o==null){
                o = administrateurHardisSession.creerOffre(libelle, ut);
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
                Service o = administrateurHardisSession.creerService(nomService, descriptionService, lieuIntervs, idoffre, coutService, facturations, listeCond, delaiService, typeSs, ut);
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
                ServiceStandard o = administrateurHardisSession.creerServiceStandard(nomService, descriptionService, lieuIntervs, idoffre, coutService, facturations, listeCond, delaiService, typeSs, descPresta, nbJS, nbJC, nbJJ, nbHA, listidlivrable, listeidAtelier, nbHS, ut);
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
                UtilisateurHardis o =administrateurHardisSession.rechercherUtilisateurHardisParLogin(login);
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
                
                o = administrateurHardisSession.creerUtilisateurHardis(nom, prenom, login, mdp, profilt, idagence, ut);
                
                Offre off = administrateurHardisSession.rechercheOffreParId(Long.valueOf(offre));
                
                ProfilMetier pm = administrateurHardisSession.creerProfilMetier(niveauH, exp, Float.valueOf(plafond), new String[1], ut);
                
                Offre_Profil_Util_CV offP = administrateurHardisSession.creerOffre_Profil_Util_CV(off.getId(), pm.getId(), o.getId(), "", ut);
                
        try {
           mailHache = Helpers.sha1(login);
        } 
       catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ClientFacade.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
         SendMail send = new SendMail();
         String messa = "<p>Veuillez cliquer <a href=\"http://localhost:8080/ProjetHardis-war/servEmployes?action=majMDP&email="+mailHache+"\">ici</a> pour créer votre mot de passe</p>";
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
                
               administrateurHardisSession.modifieUtilisateurHardisMDP(ut, pass);
            message= "Mot de passe modifié";
                

                
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
