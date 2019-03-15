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
import Entites.Facturation;
import Entites.FacturationFrais;
import Entites.HistoriqueDevis;
import Entites.HistoriqueEtats;
import Entites.LieuIntervention;
import Entites.Livrable;
import Entites.Offre;
import Entites.ProfilTechnique;
import Entites.Service;
import Entites.ServiceStandard;
import Entites.Statut;
import Entites.TypeService;
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
            else if(act.equals("CreerAdresse"))
            {
               UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
               doActionCreerAdresse(request,response);
            }
             else if(act.equals("CreerAtelier"))
            {
               UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
               doActionCreerAtelier(request,response);
            }
             else if(act.equals("listeDevisComunnication"))
            {
                List<Devis> listdevis= administrateurHardisSession.listDevis();
                request.setAttribute("listeDevis",listdevis);
                jspClient="/Admin/CreerCommunication.jsp";
            }
            else if(act.equals("CreerCommunication"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                doActionCreerCommunication(request,response);
            }
            else if(act.equals("CreerDisponibilite"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                doActionCreerDisponibilite(request,response);
            }
            else if(act.equals("listeHistoriquesDevisDoc"))
            {
                List<HistoriqueDevis> listhistdevis= administrateurHardisSession.listHistoriqueDevis();
                request.setAttribute("listHistDevis",listhistdevis);
                jspClient="/Admin/CreerCommunication.jsp";
            }
            else if(act.equals("CreerDocument"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                doActionCreerDocument(request,response);
            }
             else if(act.equals("listeDevisEchangeTel"))
            {
                List<Devis> listdevis= administrateurHardisSession.listDevis();
                request.setAttribute("listeDevis",listdevis);
                jspClient="/Admin/CreerEchangeTel.jsp";
            }
            else if(act.equals("CreerEchangeTel"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                doActionCreerEchangeTel(request,response);
            }
            else if(act.equals("listesPourModifierDevis"))
            {
                List<Agence> listagence= administrateurHardisSession.listAgence();
                request.setAttribute("listeAgence",listagence);
                List<Client> listclient= administrateurHardisSession.listClient();
                request.setAttribute("listeClient",listclient);
                List<Devis> listdevis= administrateurHardisSession.listDevis();
                request.setAttribute("listeDevis",listdevis);
                jspClient="/Admin/ModifierDevis.jsp";
            }
            else if(act.equals("modifierDevis"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                doActionModifierDevis(request,response);
            }
            else if(act.equals("listesCreerLivrable"))
            {
                List<Service> listservice= administrateurHardisSession.listService();
                request.setAttribute("listeService",listservice);
                jspClient="/Admin/CreerLivrable.jsp";
            }
            else if(act.equals("CreerLivrable"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                doActionCreerLivrable(request,response);
            }
            else if(act.equals("CreerOffre"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                doActionCreerOffre(request,response);
            }
            else if(act.equals("listesCreerService"))
            {
                List<Offre> listoffre= administrateurHardisSession.listOffre();
                request.setAttribute("listoffre",listoffre);
                jspClient="/Admin/CreerService.jsp";
            }
            else if(act.equals("CreerService"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                doActionCreerService(request,response);
            }
            else if(act.equals("listesCreerServiceStandard"))
            {
                List<Offre> listoffre= administrateurHardisSession.listOffre();
                request.setAttribute("listoffre",listoffre);
                jspClient="/Admin/CreerServiceStandard.jsp";
            }
            else if(act.equals("CreerServiceStandard"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                doActionCreerServiceStandard(request,response);
            }
            else if(act.equals("listesCreerUtiliateurHardis"))
            {
                List<Agence> listagence= administrateurHardisSession.listAgence();
                request.setAttribute("listeAgence",listagence);
                jspClient="/Admin/CreerServiceStandard.jsp";
            }
            else if(act.equals("CreerUtilisateurHardis"))
            {
                UtilisateurHardis utilisateur= (UtilisateurHardis) sess.getAttribute("entr");
                doActionCreerUtilisateur(request,response);
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
        String service = request.getParameter("serviceLivrable");
        String message = null;
        if(nom.trim().isEmpty()||service.trim().isEmpty()){
            message = "Erreur - Vous n'avez pas rempli tous les champs obligatoires." + "<br/><a href=\"CreerContratEntraineur.jsp\">Clique ici </a>pour accéder au formulaire de creation.";
        }
        else {
            UtilisateurHardis ut = (UtilisateurHardis) sess.getAttribute("utilisateur");
            if(ut!=null){
                Long idservice = Long.valueOf(service);
                Livrable o = administrateurHardisSession.creerLivrable(nom, idservice, ut);
                String nomentite = o.getNomLivrable();
                String classe = o.getClass().toString();
                message= " "+classe+":"+ nomentite+" créé avec succès !";
            }
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
        String typeS= request.getParameter("typeS");
        String message = null;
        if(nomService.trim().isEmpty()||descriptionService.trim().isEmpty()||lieuInterv.trim().isEmpty()||offre.trim().isEmpty()||cout.trim().isEmpty()||facturation.trim().isEmpty()||listeCond.trim().isEmpty()||delai.trim().isEmpty()||typeS.trim().isEmpty()){
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
                TypeService typeSs= null;
                if(typeS.equals("Standard")){
                    typeSs = TypeService.Standard;
                }
                else if (facturation.equals("Non_Standard")){
                    typeSs = TypeService.Non_Standard;
                }
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
        String[] listidlivrable = request.getParameterValues("nomService");
        String[] listeidAtelier= request.getParameterValues("nomService");
        String nomService = request.getParameter("nomService");
        String descriptionService= request.getParameter("descriptionService");
        String lieuInterv= request.getParameter("lieuInterv");
        String offre= request.getParameter("offre");
        String cout= request.getParameter("cout");
        String facturation= request.getParameter("facturation");
        String listeCond= request.getParameter("listeCond");
        String delai = request.getParameter("delai");
        String typeS= request.getParameter("typeS");
        String message = null;
        if(nomService.trim().isEmpty()||descriptionService.trim().isEmpty()||lieuInterv.trim().isEmpty()||offre.trim().isEmpty()||cout.trim().isEmpty()||facturation.trim().isEmpty()||listeCond.trim().isEmpty()||delai.trim().isEmpty()||typeS.trim().isEmpty()){
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
                TypeService typeSs= null;
                if(typeS.equals("Standard")){
                    typeSs = TypeService.Standard;
                }
                else if (facturation.equals("Non_Standard")){
                    typeSs = TypeService.Non_Standard;
                }
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
        String nom = request.getParameter("nomUtilisateur");
        String prenom= request.getParameter("prenomUtilisateur");
        String login= request.getParameter("loginUtilisateur");
        String mdp= request.getParameter("mdpUtilisateur");
        String profil = request.getParameter("PFUtilisateur");
        String agence= request.getParameter("libelleOffre");
        String message = null;
        if(nom.trim().isEmpty()||prenom.trim().isEmpty()||login.trim().isEmpty()||mdp.trim().isEmpty()||profil.trim().isEmpty()||agence.trim().isEmpty()){
            message = "Erreur - Vous n'avez pas rempli tous les champs obligatoires." + "<br/><a href=\"CreerContratEntraineur.jsp\">Clique ici </a>pour accéder au formulaire de creation.";
        }
        else {
            UtilisateurHardis ut = (UtilisateurHardis) sess.getAttribute("utilisateur");
            if(ut!=null){
                UtilisateurHardis o =administrateurHardisSession.rechercherUtilisateurHardisParLogin(login, ut);
                if(o==null){
                ProfilTechnique profilt= null;
                if(profil.equals("Admin")){
                    profilt = ProfilTechnique.Admin;
                }
                else if (profil.equals("Gestion")){
                    profilt = ProfilTechnique.Gestion;
                }
                else if (profil.equals("Visiteur")){
                    profilt = ProfilTechnique.Visiteur;
                }
                Long idagence = Long.valueOf(agence);
                o = administrateurHardisSession.creerUtilisateurHardis(nom, prenom, login, mdp, profilt, idagence, ut);
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
