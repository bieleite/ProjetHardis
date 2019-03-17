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
import Entites.DevisNonStandard;
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
import Entites.Service;
import Entites.ServiceStandard;
import Entites.Statut;
import Entites.TypeDoc;
import Entites.TypeService;
import Entites.TypeUtilisateur;
import Entites.Utilisateur;
import Entites.UtilisateurHardis;
import Facades.AdresseFacadeLocal;
import Facades.AgenceFacadeLocal;
import Facades.ClientFacadeLocal;
import Facades.CommunicationFacadeLocal;
import Facades.DevisFacadeLocal;
import Facades.DevisNonStandardFacadeLocal;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author gabrielleite
 */
@Stateless
public class ClientSession implements ClientSessionLocal {

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
    private DevisNonStandardFacadeLocal devisNonStandardFacade;

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
           Devis d =  devisFacade.creerDevis(TypeService.Standard, serv, dateDevis, dateInterv, Facturation.Auto, 0, "", infosC, Statut.Rep_en_Cours, cli, cli.getEntreprise().getAgence());
                    
           HistoriqueEtats he =   historiqueEtatsFacade.creerHistoriqueEtats( Statut.Rep_en_Cours, d);
          
           List<Document> listeDocs = new ArrayList<>();
          
           listeDocs.add(documentFacade.creerDocument("conditions contrat",serv.getConditionsContract(), null, TypeDoc.c));
           
           HistoriqueDevis hd = historiqueDevisFacade.creerHistoriqueDevis(d, null, null, 1, null, listeDocs);
           
     //      HistoriqueTraitement ht =   historiqueTraitementFacade.creerHistoriqueTraitement(new Date(), null, TypeUtilisateur.r, d, null, ref, null);

         //  historiqueDevisFacade.ajoutDocHistoDevis(hd, listeDocs);
                 devisFacade.majHD(d, hd);
devisFacade.majHE(d, he);
//devisFacade.majHT(d, ht);
           logsFacade.creerLog(Action.Create, new Date(), "création devis avec id : "+d.getId(), cli);
        }
        else if (serv!=null)
        {
           Devis d  =  devisFacade.creerDevis(TypeService.Non_Standard, serv, dateDevis, dateInterv, Facturation.Manuel, 0, "", infosC, Statut.Rep_en_Cours, cli, cli.getEntreprise().getAgence());
           
           HistoriqueEtats he = historiqueEtatsFacade.creerHistoriqueEtats( Statut.Rep_en_Cours, d);
           
           List<UtilisateurHardis>  listeU =  utilisateurHardisFacade.rechercheUtilisateurHParAgence(cli.getEntreprise().getAgence());
          
           UtilisateurHardis ref = null;
           
           for (UtilisateurHardis u : listeU )
           {
               List<Offre_Profil_Util_CV> o = offre_Profil_Util_CVFacade.rechercheOPUCParUtilisateur(u);
             
               for (Offre_Profil_Util_CV compteur : o)
               {
                   ProfilMetier pm = compteur.getProfil();
                   if (pm.getNiveauHabilitation().toString().equals("Referent"))
                       ref = u;
               }
           }    
           
        Document doc = documentFacade.creerDocument("conditions contrat ",serv.getConditionsContract(), null, TypeDoc.c);
         List<Document> listeD = new ArrayList<>();
         listeD.add(doc);
        HistoriqueDevis hd = historiqueDevisFacade.creerHistoriqueDevis(d, null, null, 1, null, listeD);
        documentFacade.majHD(doc, hd);

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
        logsFacade.creerLog(Action.Update, new Date(), "maj devis avec id : "+d.getId(), cli);
    }
   

    @Override
    public void refuserDevis(long idCli, long idDevis) {
       Client cli = clientFacade.rechercheClient(idCli);
        Devis d = devisFacade.rechercheDevis(idDevis);
          devisFacade.accepterRefuserDevis(d, "r");
         historiqueEtatsFacade.creerHistoriqueEtats( Statut.Refuse, d);
         
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
            case "Réponse en cours" : {
                 st = Statut.Rep_en_Cours;
                break;
            }
             case "Envoyé" : {
                 st = Statut.Envoye;
                break;
            }
              case "Validé" : {
                 st = Statut.Valide;
                break;
            }
               case "Refusé" : {
                 st = Statut.Refuse;
                break;
            }
                case "En négociation" : {
                 st = Statut.En_nego;
                break;
            }
                  case "Acompte réglé" : {
                 st = Statut.Acompte_regle;
                break;
            }
              case "Prestation terminée" : {
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
    public Client creerClient(String nom, String prenom, String login, String mdp, String cp, String qs, String rs) {
       return clientFacade.creerClient(nom, prenom, login, mdp, qs, rs, 1, new Date(), null, null, cp);
    }

    @Override
    public void deconnexion(long id) {
        Client c = clientFacade.rechercheClient(id);
        clientFacade.deconnexion(c);
    }

    @Override
    public Entreprise rechercheEntrepriseParCodeEtMdp(String code, String mdp) {
        Entreprise e = entrepriseFacade.rechercheEntrepriseSiretMdp(code, mdp);
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
    public Facture creerFacture(long id) {
           Devis d = devisFacade.rechercheDevis(id);
          Facture f = factureFacade.creerFacture(new Date(), d, d.getMontantDevis(), 0, "");
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
    public void creerInter(String nom, String prenom, String email, String fonction, String tel, long idEnt) {
        Entreprise e = entrepriseFacade.rechercheEntrepriseParId(idEnt);
        interlocuteurFacade.creerInterlocuteur(nom, prenom, fonction, tel, e);
    }

    @Override
    public List<Interlocuteur> recupInter(long id) {
        Entreprise e = entrepriseFacade.rechercheEntrepriseParId(id);
        return interlocuteurFacade.rechercheInterParEntreprise(e);
    }


    
    
    
    
    
    
    


    
    
    
    
    
    
    
    
    
    
    

    
    
   
}
