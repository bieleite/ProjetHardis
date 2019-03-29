/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entites.Action;
import Entites.Adresse;
import Entites.Agence;
import Entites.Client;
import Entites.Communication;
import Entites.Devis;

import Entites.Disponibilite;
import Entites.Document;
import Entites.Entreprise;
import Entites.Facturation;
import Entites.Facture;
import Entites.HistoriqueDevis;
import Entites.HistoriqueEtats;
import Entites.HistoriqueTraitement;
import Entites.Interlocuteur;
import Entites.Notification;
import Entites.Offre;
import Entites.Offre_Profil_Util_CV;
import Entites.ProfilMetier;
import Entites.SendMail;
import Entites.Service;
import Entites.ServiceStandard;
import Entites.Statut;
import Entites.TypeDoc;
import Entites.TypeService;
import Entites.TypeUtilisateur;
import Entites.Utilisateur;
import Entites.UtilisateurHardis;
import Entites.testFTP;
import Entites.testPDF;
import Facades.AdresseFacadeLocal;
import Facades.AgenceFacadeLocal;
import Facades.ClientFacadeLocal;
import Facades.CommunicationFacadeLocal;
import Facades.DevisFacadeLocal;
import Facades.DisponibiliteFacadeLocal;
import Facades.DocumentFacadeLocal;
import Facades.EntrepriseFacadeLocal;
import Facades.FactureFacadeLocal;
import Facades.HistoriqueDevisFacadeLocal;
import Facades.HistoriqueEtatsFacadeLocal;
import Facades.HistoriqueTraitementFacadeLocal;
import Facades.InterlocuteurFacadeLocal;
import Facades.LogsFacadeLocal;
import Facades.NotificationFacadeLocal;
import Facades.OffreFacadeLocal;
import Facades.Offre_Profil_Util_CVFacadeLocal;
import Facades.ServiceFacadeLocal;
import Facades.ServiceStandardFacadeLocal;
import Facades.UtilisateurFacadeLocal;
import Facades.UtilisateurHardisFacadeLocal;
import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author gabrielleite
 */
@Stateless
public class ClientSession implements ClientSessionLocal {

    @EJB
    private DisponibiliteFacadeLocal disponibiliteFacade;

    @EJB
    private InterlocuteurFacadeLocal interlocuteurFacade;

    
    @EJB
    private OffreFacadeLocal offreFacade;

    @EJB
    private NotificationFacadeLocal notificationFacade;

    @EJB
    private AdresseFacadeLocal adresseFacade;

    @EJB
    private ServiceStandardFacadeLocal serviceStandardFacade;

    @EJB
    private ServiceFacadeLocal serviceFacade;

    @EJB
    private EntrepriseFacadeLocal entrepriseFacade;

    @EJB
    private FactureFacadeLocal factureFacade;

    @EJB
    private CommunicationFacadeLocal communicationFacade;

    @EJB
    private LogsFacadeLocal logsFacade;

    @EJB
    private AgenceFacadeLocal agenceFacade;

    @EJB
    private UtilisateurHardisFacadeLocal utilisateurHardisFacade;

    @EJB
    private UtilisateurFacadeLocal utilisateurFacade;

    @EJB
    private Offre_Profil_Util_CVFacadeLocal offre_Profil_Util_CVFacade;

    @EJB
    private HistoriqueTraitementFacadeLocal historiqueTraitementFacade;

    @EJB
    private HistoriqueEtatsFacadeLocal historiqueEtatsFacade;

    @EJB
    private DocumentFacadeLocal documentFacade;

    @EJB
    private HistoriqueDevisFacadeLocal historiqueDevisFacade;


    @EJB
    private DevisFacadeLocal devisFacade;

    @EJB
    private ClientFacadeLocal clientFacade;
    
    
    
    
    
    
    
    
    @Override
    public Client authentificationClient(String login, String mdp) {  
       
         return clientFacade.authentificationClient(login,mdp);   
    }
    
    

    @Override
    public void majEntreprise(long idCli, long idEnt) {
        Client cli = clientFacade.rechercheClient(idCli);
        Entreprise ent = entrepriseFacade.rechercheEntrepriseParId(idEnt);
        clientFacade.majEntrepriseClient(cli, ent);
    }

    @Override
    public void demandeDevis(Date dateDevis, Date dateInterv, String infosC, long  idServ, String typeServ, long idCli) {
         Service serv = null;
         ServiceStandard servSt = null;
        if (typeServ.equals("Standard"))
        {
              servSt = serviceStandardFacade.rechercheServiceSParId(idServ);
        }
        else if (typeServ.equals("Non_Standard"))
        {
              serv =  serviceFacade.rechercheServiceParId(idServ);
        }
        Client cli = clientFacade.rechercheClient(idCli);
             
        if(cli.getEntreprise()!=null){
         
        if (servSt!=null) //service standard
        {      
           Devis d =  devisFacade.creerDevis(TypeService.Standard, servSt, dateDevis, dateInterv, Facturation.Auto, 0, "", infosC, Statut.Rep_en_Cours, cli, cli.getAgence());
                    
           HistoriqueEtats he =   historiqueEtatsFacade.creerHistoriqueEtats( Statut.Rep_en_Cours, d);
          
           List<Document> listeDocs = new ArrayList<>();
          
      //    listeDocs.add(documentFacade.creerDocument("conditions contrat",servSt.getConditionsContract(), null, TypeDoc.c));
           
           HistoriqueDevis hd = historiqueDevisFacade.creerHistoriqueDevis(d, null, null, 1, null, listeDocs);
         devisFacade.majHD(d, hd);
devisFacade.majHE(d, he);
SendMail send = new SendMail();
send.sendMail(cli.getLogin(), "Devis disponible", "Bonjour, <br>Le devis numéro "+d.getId()+" est desormais disponible sur votre espace Hardis. <br>Cordialement, <br>Groupe Hardis");
           logsFacade.creerLog(Action.Create, new Date(), "création devis avec id : "+d.getId(), cli);
        }
        else if (serv!=null)
        {
           Devis d  =  devisFacade.creerDevis(TypeService.Non_Standard, serv, dateDevis, dateInterv, Facturation.Manuel, 0, "", infosC, Statut.Rep_en_Cours, cli, cli.getAgence());
           
           HistoriqueEtats he = historiqueEtatsFacade.creerHistoriqueEtats( Statut.Rep_en_Cours, d);
           
           List<UtilisateurHardis>  listeU =  utilisateurHardisFacade.rechercheUtilisateurHParAgence(cli.getAgence());
          
           UtilisateurHardis ref = null;
           
           for (UtilisateurHardis u : listeU )
           {
               List<Offre_Profil_Util_CV> o = offre_Profil_Util_CVFacade.rechercheOPUCParUtilisateur(u);
             
               for (Offre_Profil_Util_CV compteur : o)
               {
                   ProfilMetier pm = compteur.getProfil();
                   if (pm.getNiveauHabilitation().toString().equals("Referent") && compteur.getOffre().equals(d.getService().getOffre()))
                       ref = u;
               }
           }    
           
      //  Document doc = documentFacade.creerDocument("conditions contrat ",serv.getConditionsContract(), null, TypeDoc.c);
         List<Document> listeD = new ArrayList<>();
        // listeD.add(doc);
        HistoriqueDevis hd = historiqueDevisFacade.creerHistoriqueDevis(d, null, null, 1, null, listeD);
      //  documentFacade.majHD(doc, hd);

        //historiqueDevisFacade.ajoutDocHistoDevis(hd, listeD);
                  
        HistoriqueTraitement ht =   historiqueTraitementFacade.creerHistoriqueTraitement(new Date(), null, TypeUtilisateur.r, d, null, ref, null);
            logsFacade.creerLog(Action.Create, new Date(), "création devis avec id : "+d.getId(), cli);
       
                devisFacade.majHD(d, hd);
devisFacade.majHE(d, he);
devisFacade.majHT(d, ht);
        }
    }
    }
    
    
    @Override
    public void accepterDevis(long idCli, long idDevis) {
        Client cli = clientFacade.rechercheClient(idCli);
        Devis d = devisFacade.rechercheDevis(idDevis);
        devisFacade.accepterRefuserDevis(d, "a");
        historiqueEtatsFacade.creerHistoriqueEtats(Statut.Valide, d);
        UtilisateurHardis u = recupValidateur(idDevis,"v");
        if (d.getTypeDevis().toString().equals("Standard"))
        { SendMail send = new SendMail();
             UtilisateurHardis u2 = recupValidateur(idDevis,"ref");
            if (u2!=null)
            send.sendMail(u2.getLogin(), "Devis accepté", "Bonjour, <br>Le devis "+d.getId()+" a été accepté par la société "+d.getClient().getEntreprise().getNomEntreprise());
           
        }else {
        if (u!=null && u.getStatut().toString().equals("Actif"))
        {
            SendMail send = new SendMail();
            send.sendMail(u.getLogin(), "Devis accepté", "Bonjour, <br>Le devis "+d.getId()+" a été accepté par la société "+d.getClient().getEntreprise().getNomEntreprise());
        }
        else {
            SendMail send = new SendMail();
            UtilisateurHardis u1 = recupValidateur(idDevis,"p");
            UtilisateurHardis u2 = recupValidateur(idDevis,"r");
            if (u1!=null)
            send.sendMail(u1.getLogin(), "Devis accepté", "Bonjour, <br>Le devis "+d.getId()+" a été accepté par la société "+d.getClient().getEntreprise().getNomEntreprise());
            if (u2!=null)
             send.sendMail(u2.getLogin(), "Devis accepté", "Bonjour, <br>Le devis "+d.getId()+" a été accepté par la société "+d.getClient().getEntreprise().getNomEntreprise());

        }
        }

        logsFacade.creerLog(Action.Update, new Date(), "maj devis avec id : "+d.getId(), cli);
    }
   

    @Override
    public void refuserDevis(long idCli, long idDevis, String motif) {
       Client cli = clientFacade.rechercheClient(idCli);
        Devis d = devisFacade.rechercheDevis(idDevis);
         devisFacade.accepterRefuserDevis(d, "r");
         historiqueEtatsFacade.creerHistoriqueEtats( Statut.Refuse, d);
         devisFacade.majMotifRefus(d, motif);
          logsFacade.creerLog(Action.Update, new Date(), "maj devis avec id : "+d.getId(), cli);
    }

    
    
    

    @Override
    public void poserQuestion(long idCli, long idDevis, String text) {
          Client cli = clientFacade.rechercheClient(idCli);
        Devis d = devisFacade.rechercheDevis(idDevis);
       communicationFacade.creerCommunication(new Date(), text, d, null, "Q", 0);
       logsFacade.creerLog(Action.Create, new Date(), "creation communication pour devis id : "+d.getId(), cli);  
    }

    
    
    @Override
    public void payerFacture(long idF) {
        Facture f = factureFacade.rechercheFactParId(idF);
        factureFacade.payerFacture(f); 
        
        String server = "cpanel.freehosting.com";
        String user = "lucialei";
        String pass = "rj3fTOw378";
        String remoteFile = "/public_html/FACT"+f.getId()+".pdf";
        String lien ="ftp://"+user+":"+pass+"@"+server+remoteFile;
  
        factureFacade.majLienF(f, lien);
  
        testPDF test = new testPDF();
          
        try {
            test.test(f);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ClientSession.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        testFTP test1 = new testFTP();
        test1.upload("FACT"+f.getId()+".pdf");
        
        SendMail send = new SendMail();
        send.sendMailA(f.getDevis().getClient().getLogin(), "Facture Hardis", "Ci-joint facture hardis","FACT"+f.getId()+".pdf");
        
        devisFacade.changeStatutPaye("1",f.getDevis());
        historiqueEtatsFacade.creerHistoriqueEtats( Statut.Acompte_regle, f.getDevis());
        logsFacade.creerLog(Action.Create, new Date(), "creation facture pour devis id : "+f.getDevis().getId(), f.getDevis().getClient()); 
    }

    @Override
    public void modifDateInterv(Date date, long idCli, long idDevis) {
           Client cli = clientFacade.rechercheClient(idCli);
        Devis d = devisFacade.rechercheDevis(idDevis);
        devisFacade.modifDateInterv(d, date);
       logsFacade.creerLog(Action.Update, new Date(), "maj date interv pour devis id : "+d.getId(), cli);      
    }

    @Override
    public List<Devis> afficherDevisClient(long idCli) {
        Client cli = clientFacade.rechercheClient(idCli);
       return  devisFacade.afficherDevisClient(cli);
    }

    @Override
    public List<Devis> afficherDevisStatut(long idCli, String statut) {
        Client cli = clientFacade.rechercheClient(idCli);
        Statut st = null;
        switch (statut) {
            case "Incomplet" : {
                st = Statut.Incomplet;
                break;
            }
            case "Rep_en_cours" : {
                 st = Statut.Rep_en_Cours;
                break;
            }
             case "Envoye" : {
                 st = Statut.Envoye;
                break;
            }
              case "Valide" : {
                 st = Statut.Valide;
                break;
            }
               case "Refuse" : {
                 st = Statut.Refuse;
                break;
            }
                case "En_nego" : {
                 st = Statut.En_nego;
                break;
            }
                  case "Acompte_regle" : {
                 st = Statut.Acompte_regle;
                break;
            }
              case "Presta_terminee" : {
                 st = Statut.Presta_terminee;
                break;
            }
        }
       return devisFacade.afficherDevisStatut(cli, st);
    }

    @Override
    public Client rechercheCliParLogin(String log) {
        return clientFacade.rechercheClientParLogin(log);
    }

    //creerClient(String Nom,String Prenom, String Login, String MDP, String QuestionSecrete, String ReponseSecrete, int RGPD, Date dateRDGP, Entreprise entreprise, Agence agence, String cp
    @Override
    public Client creerClient(String nom, String prenom, String login, String mdp, String qs, String rs,  String cp) {
       return clientFacade.creerClient(nom, prenom, login, mdp, qs, rs, 1, new Date(), null, null, cp);
    }

    @Override
    public void deconnexion(long id) {
        Client c = clientFacade.rechercheClient(id);
        clientFacade.deconnexion(c);
    }

    @Override
    public Entreprise rechercheEntrepriseParCodeEtMdp(String code, String mdp) {
        Entreprise e = entrepriseFacade.rechercheEntrepriseCodeMdp(code, mdp);
return e;
    }

    @Override
    public Entreprise rechercheEntrepriseParSiret(String siret) {
        Entreprise e = entrepriseFacade.rechercheEntrepriseSiret(siret);
        return e;
    }

    //String numero, String nom, List<Entites.Interlocuteur> interlocuteurs, Adresse adresse
    @Override
    public Entreprise creerEntreprise(String nom, String siret, int nrRue, String nomR, String ville, String cp) {
        Adresse ad = creerAdresse(nrRue, nomR, ville, cp);
       Entreprise ent = entrepriseFacade.creerEntreprise(siret, nom, null, ad);
       return ent;
    }

    @Override
    public Adresse creerAdresse(int nrRue, String nomR, String ville, String cp) {
        return adresseFacade.creerAdresse(nrRue, nomR, ville, cp);
    }

    @Override
    public void creerNotif(long idU, String mess) {
        Utilisateur u = (Utilisateur)utilisateurFacade.find(idU);
        notificationFacade.creerNotif(u, mess);
    }

    @Override
    public List<Notification> getNotifsClient(long id) {
            Client c = clientFacade.rechercheClient(id);
            return notificationFacade.rechercheNotif(c);
    }

    @Override
    public List<Service> recupServices() {
        return serviceFacade.listServices();
    }

//

    @Override
    public Service rechercheServiceParId(long id) {
        return serviceFacade.rechercheServiceParId(id);
    }
    
    

    @Override
    public Devis recupDevis(long id) {
        return devisFacade.rechercheDevis(id);
    }

    @Override
    public List<Communication> rechercheCommDev(long id) {
        Devis d = devisFacade.rechercheDevis(id);
        List<Communication> liste = communicationFacade.rechercheCommunicationParDevis(d);
        return liste;
    }

    @Override
    public void creerComm(String mess, long idD) {
        Devis d = devisFacade.rechercheDevis(idD);
        communicationFacade.creerCommunication(new Date(), mess, d, null, "Q", 0);
    }

    @Override
    public String rechercheDocDevis(long id) {
        String lienDev="";
          Devis d = devisFacade.rechercheDevis(id);
         List<HistoriqueDevis> listeHD = d.getHistoriqueDeviss();
         if (listeHD!=null && listeHD.size()>0)
         {
             for (HistoriqueDevis hd : listeHD)
             {
                 if (hd.getDocuments()!=null && hd.getDocuments().size()>0)
                 {
                     for (Document doc : hd.getDocuments())
                     {
                         if (doc.getTypeDoc().toString().equals("d"))
                             lienDev = doc.getLienDoc();
                     }
                 }
             }
         }
        return lienDev;
    }

    @Override
    public Facture creerFacture(long id, String lienF) {
           Devis d = devisFacade.rechercheDevis(id);
           Facture f = factureFacade.creerFacture(new Date(), d, d.getMontantDevis()/2, 0, "", "");
      
            
           return f;
    }
    

    @Override
    public void payerFactureFinale(long id) {
                Facture f = factureFacade.rechercheFactParId(id);
        factureFacade.payerFacture(f);    
        devisFacade.changeStatutPaye("2",f.getDevis());
        logsFacade.creerLog(Action.Create, new Date(), "creation facture pour devis id : "+f.getDevis().getId(), f.getDevis().getClient()); 
     historiqueEtatsFacade.creerHistoriqueEtats( Statut.Total_regle, f.getDevis());
    }

    @Override
    public List<UtilisateurHardis> rechercheConsultantsOffre(long id) {
       Service serv = serviceFacade.rechercheServiceParId(id); //classique
       ServiceStandard servS = null;
       
       if (serv.getTypeService().toString().equals("Standard"))
       {
           servS = serviceStandardFacade.rechercheServiceSParId(id);
       }
       List<UtilisateurHardis> listeC = new ArrayList<>();
       Offre o = serv.getOffre();
       List<Offre_Profil_Util_CV> liste = offre_Profil_Util_CVFacade.rechercheOPUCParOffre(o);
       
       for (Offre_Profil_Util_CV off : liste)
       {
           if (servS!=null)
           {   
                if (off.getProfil().getNiveauExpertise().toString().equals("Senior"))
                {
                        if (servS.getNbreJoursConsultantS()>0)
                            listeC.add(off.getUtilisateur());
                    }
                else  if (off.getProfil().getNiveauExpertise().toString().equals("Junior"))
                {
                        if (servS.getNbreJoursConsultantJ()>0)
                            listeC.add(off.getUtilisateur());
                    }
                }
               else  if (off.getProfil().getNiveauExpertise().toString().equals("Confirme"))
                {
                        if (servS.getNbreJoursConsultantC()>0)
                            listeC.add(off.getUtilisateur());
                    }
                }
       
    
    return listeC;
       
    }

    @Override
    public List<Facture> rechercheFactParDevis(long id) {
        Devis d = devisFacade.rechercheDevis(id);
        return factureFacade.rechercheFactParDevis(d);
    }

    @Override
    public void creerInter(String nom, String prenom, String email, String fonction, String tel,long idEnt) {
        Entreprise e = entrepriseFacade.rechercheEntrepriseParId(idEnt);
        interlocuteurFacade.creerInterlocuteur(nom, prenom, fonction, tel,email, e);
    }

    @Override
    public List<Interlocuteur> recupInter(long id) {
        Entreprise e = entrepriseFacade.rechercheEntrepriseParId(id);
        return interlocuteurFacade.rechercheInterParEntreprise(e);
    }

    @Override
    public boolean lierEntreprise(long idC, String codeC, String mdp) {
        Client c = clientFacade.rechercheClient(idC);
        boolean b = false;
        Entreprise e = entrepriseFacade.rechercheEntrepriseCodeMdp(codeC,mdp);
        if (e!=null)
        {
            clientFacade.certifierClient(c);
            clientFacade.majAgenceClient(c, e.getAgence());
            clientFacade.majEntrepriseClient(c, e);
            b= true;
        }
        return b;
    }
    
    

    @Override
    public boolean verifRepS(long id, String rep) {
                Client c = clientFacade.rechercheClient(id);
               return clientFacade.verifRepS(c, rep);
    }

    @Override
    public void modifMDP(long id, String mdp) {
        Client c = clientFacade.rechercheClient(id);
        clientFacade.modfiClientMDP(c, mdp);
    }

    @Override
    public List<UtilisateurHardis> rechercheCDisponibles(String typeC, Date date, long idS, String typeS, long idCli) {
         Client c = clientFacade.rechercheClient(idCli);
         List<UtilisateurHardis>  listeUD = new ArrayList<>();
         if (date instanceof java.sql.Date)
         {
               String dateT = date.toString().concat(" 00:00:00");
               date = Timestamp.valueOf(dateT);
         }
       
         
        ServiceStandard servSt = null;
        if (typeS.equals("Standard"))
        {
              servSt = serviceStandardFacade.rechercheServiceSParId(idS);
             
              
        float nbreJ =0;
        
        if (typeC.equals("Confirme"))
        nbreJ= servSt.getNbreJoursConsultantC();
        else if (typeC.equals("Junior"))
        nbreJ= servSt.getNbreJoursConsultantJ();
         if (typeC.equals("Senior"))
        nbreJ= servSt.getNbreJoursConsultantS();
         
         
         
        
        List<UtilisateurHardis>  listeU =  utilisateurHardisFacade.rechercheUtilisateurHParAgence(c.getAgence());
          
     
           
           for (UtilisateurHardis u : listeU )
           {
               List<Offre_Profil_Util_CV> o = offre_Profil_Util_CVFacade.rechercheOPUCParUtilisateur(u);
             
               for (Offre_Profil_Util_CV compteur : o)
               {
                   ProfilMetier pm = compteur.getProfil();
                   if (pm.getNiveauHabilitation().toString().equals("Consultant") && 
                           compteur.getOffre().equals(servSt.getOffre())&&
                           pm.getNiveauExpertise().toString().equals(typeC))
                       listeUD.add(u);
               }
           }
           
           for (UtilisateurHardis u : listeUD)
           {
               List<Disponibilite> liste = disponibiliteFacade.rechercheDisponibiliteParUtilisateur(u);
               boolean b = true;
               
               for (Disponibilite d : liste)
               {
                   
                   if (d.getDateDebut().getDate()==date.getDate() && 
                           d.getDateDebut().getMonth()==date.getMonth() &&
                           d.getDateDebut().getYear()==date.getYear())
                   {
                       if (nbreJ<1) //demi-journée
                       {
                           if (d.getDateDebut().getHours()>=8 && d.getDateFin().getHours()>=14) //si un truc le matin et fini apres 14h
                               b = false;
                           else if (d.getDateDebut().getHours()>=14)
                               b = false;
                       }
                       else b = false;
                   }
                   
                   else {
                       if (nbreJ>1)
                       {
                           int t = (int) nbreJ*24;
                           Date test = date;
                       
                           int te = test.getHours();
                           test.setHours(test.getHours()+t);
                           
                           if (d.getDateFin().before(test))
                               
                               b= false;
                       }
                   }
                       
               }
           }
        
        
        
        
        
                }
        
        
        
        
        return listeUD;
    }

    @Override
    public List<Offre_Profil_Util_CV> rechercheOPUCParU(long idCli, long idO) {
        Client c = clientFacade.rechercheClient(idCli);
        Offre off = offreFacade.rechercheOffreParId(idO);
         List<UtilisateurHardis>  listeU =  utilisateurHardisFacade.rechercheUtilisateurHParAgence(c.getAgence());
              List<Offre_Profil_Util_CV>  listeUD = new ArrayList<>(); 
           for (UtilisateurHardis u : listeU )
           {
               List<Offre_Profil_Util_CV> o = offre_Profil_Util_CVFacade.rechercheOPUCParUtilisateur(u);
             
               for (Offre_Profil_Util_CV compteur : o)
               {
                   ProfilMetier pm = compteur.getProfil();
                   if (pm.getNiveauHabilitation().toString().equals("Consultant") && 
                           compteur.getOffre().equals(off)
                          )
                       listeUD.add(compteur);
               }
           }
        return listeUD;
    }

    @Override
    public void choixConsultants(long idD, String [] c, String [] j, String [] s) {
        Devis d = devisFacade.rechercheDevis(idD);
        List<UtilisateurHardis> listeUt = rechercheCParDevis(idD);
        List<HistoriqueTraitement> listeHT = historiqueTraitementFacade.rechercheHistoriqueTraitementParDevis(d);
     
       
        
        if(c!=null && c.length>0)
        for(String idU : c)
        {
            UtilisateurHardis u = utilisateurHardisFacade.rechercheUtilisateurParId(Long.valueOf(idU));
           
            historiqueTraitementFacade.creerHistoriqueTraitement(d.getDateDebutPresta(), d.getDateFinPresta(), TypeUtilisateur.p, d, u, null, null);
            
        }
        
        if(j!=null && j.length>0)
        for(String idU : j)
        {
            
            UtilisateurHardis u = utilisateurHardisFacade.rechercheUtilisateurParId(Long.valueOf(idU));
      
            historiqueTraitementFacade.creerHistoriqueTraitement(d.getDateDebutPresta(), d.getDateFinPresta(), TypeUtilisateur.p, d, u, null, null);
             
        }
        
        if(s!=null && s.length>0)
        for(String idU : s)
        {
            UtilisateurHardis u = utilisateurHardisFacade.rechercheUtilisateurParId(Long.valueOf(idU));
        
            historiqueTraitementFacade.creerHistoriqueTraitement(d.getDateDebutPresta(), d.getDateFinPresta(), TypeUtilisateur.p, d, u, null, null);
             
        }
    }

    @Override
    public ServiceStandard rechercheSS(long id) {
        return serviceStandardFacade.rechercheServiceSParId(id);
    }

    @Override
    public List<UtilisateurHardis> rechercheCParDevis(long idD) {
      Devis d = devisFacade.rechercheDevis(idD);
        List<HistoriqueTraitement> listeH= historiqueTraitementFacade.rechercheHistoriqueTraitementParDevis(d);
         List<UtilisateurHardis> listeUH = new ArrayList<>();
        for (HistoriqueTraitement h : listeH)
        {
            if (h.getUtilisateurCourant().toString().equals("p"))
                listeUH.add(h.getConsultant());
        }
        return listeUH;
    }

    @Override
    public float recherchePrixOffreC(UtilisateurHardis u, Offre off) {
    float prix = 0;
            List<Offre_Profil_Util_CV> o = offre_Profil_Util_CVFacade.rechercheOPUCParUtilisateur(u);
             
               for (Offre_Profil_Util_CV compteur : o)
               {
                   ProfilMetier pm = compteur.getProfil();
                   if (pm.getNiveauHabilitation().toString().equals("Consultant") && 
                           compteur.getOffre().equals(off)
                          )
                       prix = compteur.getPrixUnit();
               }

        return prix;
    }

    @Override
    public String rechercheLibConsultOffre(UtilisateurHardis u, Offre off) {
      String lib = "";
        List<Offre_Profil_Util_CV> o = offre_Profil_Util_CVFacade.rechercheOPUCParUtilisateur(u);
             
               for (Offre_Profil_Util_CV compteur : o)
               {
                   ProfilMetier pm = compteur.getProfil();
                   if (pm.getNiveauHabilitation().toString().equals("Consultant") && 
                           compteur.getOffre().equals(off)
                          )
                      lib = pm.getNiveauExpertise().toString();
               }
        return lib;
    }

    @Override
    public void certifierClient(long id) {
        Client c =clientFacade.rechercheClient(id);
        clientFacade.certifierClient(c);
    }

    @Override
    public void majAgenceCli(long idA, long idC) {
           Client c = clientFacade.rechercheClient(idC);
        Agence a = agenceFacade.rechercheAgence(idA);
        clientFacade.majAgenceClient(c, a);
    }

    @Override
    public void majAgenceEnt(long idA, long idE) {
        Entreprise c = entrepriseFacade.rechercheEntrepriseParId(idE);
        Agence a = agenceFacade.rechercheAgence(idA);
        entrepriseFacade.majAgenceEnt(c, a);
    }

    @Override
    public List<Devis> recupContratsParAn(int annee, long client) {
        Client c = clientFacade.rechercheClient(client);
        List<Devis> liste = devisFacade.rechercheDevisParClient(c);
         List<Devis> listeD = new ArrayList<>();
      for (Devis d  : liste)
        {
            
            
          Date date =  d.getDateDevis();
SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");         
String inActiveDate = null;
  Calendar cal = Calendar.getInstance();
 java.util.Date parsed = null;
try {
    inActiveDate = format1.format(date);
   parsed = format1.parse(inActiveDate);
   cal.setTime(parsed);
   
   int g =   cal.get(Calendar.YEAR);
   if (g==annee)
   {
       listeD.add(d);
   }
}           
    catch (ParseException e) {
        e.printStackTrace();
    }
            
        }
      
     return listeD; 
    }

    @Override
    public float getCA(int annee, Long idCli) {
       Client c = clientFacade.rechercheClient(idCli);
       float mont = 0;
       List<Devis> listeD = devisFacade.afficherDevisClient(c);
       
       if (listeD!=null)
       {
           for (Devis d : listeD)
           {
                Date date =  d.getDateDevis();
SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");         
String inActiveDate = null;
  Calendar cal = Calendar.getInstance();
 java.util.Date parsed = null;
try {
   inActiveDate = format1.format(date);
   parsed = format1.parse(inActiveDate);
   cal.setTime(parsed);
   
   int g =   cal.get(Calendar.YEAR);
               List<Facture> listeF = factureFacade.rechercheFactParDevis(d);
               if (listeF!=null){
               for (Facture f : listeF)
               {
                   inActiveDate = format1.format(f.getDateFacture());
   parsed = format1.parse(inActiveDate);
   cal.setTime(parsed);
   int a = cal.get(Calendar.YEAR);
                   if (a==g)
                mont+=f.getMontant();
               }
               }
           }
 catch (ParseException e) {
        e.printStackTrace();
    }
       }
       }
       
                   
   
            
        
              
        return mont;
    }

    @Override
    public void changerDateInterv(Devis d, Date dte) {
        devisFacade.modifDateInterv(d, dte);
    }

    @Override
    public void changerStatut(Devis d, String statut) {
                devisFacade.changerStatut(d, statut);

    }

    @Override
    public void majMontantDevis(long idD, float mont) {
        Devis d = devisFacade.rechercheDevis(idD);
        devisFacade.majMontant(d, mont);
    }

    @Override
    public UtilisateurHardis recupValidateur(long idD, String type) {
       Devis d = devisFacade.rechercheDevis(idD);
       UtilisateurHardis u =null;
       List<HistoriqueTraitement> listeH = historiqueTraitementFacade.rechercheHistoriqueTraitementParDevis(d);
       
       for (HistoriqueTraitement h : listeH)
       {
           if (type.equals("v") && h.getValidateur()!=null)
           {
               u = h.getValidateur();
           }
           else if (type.equals("r") && h.getRefLocal()!=null)
           {
               u = h.getRefLocal();
           }
       }
       
       if (type.equals("p"))
       {
       List<Offre_Profil_Util_CV> listeOP = offre_Profil_Util_CVFacade.rechercheOPUCParOffre(d.getService().getOffre());
       
       for (Offre_Profil_Util_CV o : listeOP)
       {
           if (o.getProfil().getNiveauHabilitation().toString().equals("Porteur"))
           {
               u=o.getUtilisateur();
           }
       }
       }
       
        if (type.equals("ref"))
       {
       List<Offre_Profil_Util_CV> listeOP = offre_Profil_Util_CVFacade.rechercheOPUCParOffre(d.getService().getOffre());
       
       for (Offre_Profil_Util_CV o : listeOP)
       {
           if (o.getProfil().getNiveauHabilitation().toString().equals("Referent"))
           {
               u=o.getUtilisateur();
           }
       }
       }
       return u;
       
       
    }

    @Override
    public float calculDelaiMDevis(long idD) {
          Devis d = devisFacade.rechercheDevis(idD);
        return communicationFacade.calculDelaiMDevis(d);
    }

    @Override
    public boolean verifDevisR(long idD) {
         Devis d = devisFacade.rechercheDevis(idD);
        return communicationFacade.verifDevisR(d);
    }

    @Override
    public int calculQSansRep(long idD) {
       Devis d = devisFacade.rechercheDevis(idD);
        return communicationFacade.calculQSansRep(d);
    }

    @Override
    public void majDateDPresta(long id) {
         Devis d = devisFacade.rechercheDevis(id);
         devisFacade.majDateDPresta(d);
    }

    @Override
    public void payerFactureCree(long idF) {
         Facture f = factureFacade.rechercheFactParId(idF);
        factureFacade.payerFacture(f); 
    }

    @Override
    public Entreprise rechercheEntParId(long idEnt) {
        return entrepriseFacade.rechercheEntrepriseParId(idEnt);
    }

    @Override
    public void modifEnt(String ville, String codeP, int nrRue, String nomRue, long idE) {
        Entreprise e = entrepriseFacade.rechercheEntrepriseParId(idE);   
    }

    @Override
    public List<ServiceStandard> recupServicesS() {
        return serviceStandardFacade.listServStandard();
    }

    @Override
    public List<Service> recupSNonSt() {
        return serviceFacade.listServicesNonStandard();
    }

    @Override
    public Service rechercheServiceParNom(String nom) {
        return null;
    }
    

 


    
    
    
    
    
    
    

    

    
    
    
    
    
    
    


    
    
    
    
    
    
    
    
    
    
    

    
    
   
}
