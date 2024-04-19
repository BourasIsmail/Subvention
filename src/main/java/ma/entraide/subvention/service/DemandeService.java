package ma.entraide.subvention.service;

import ma.entraide.subvention.entity.Coordination;
import ma.entraide.subvention.entity.Deleguation;
import ma.entraide.subvention.entity.Demande;
import ma.entraide.subvention.repository.DemandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


import java.util.*;

@Service
public class DemandeService {

    @Autowired
    private DemandeRepository demandeRepository;

    @Autowired
    private DeleguationService deleguationService;

    @Autowired
    private CoordinationService coordinationService;

    public String generateCode(Demande demande){
//        UUID id = UUID.randomUUID(); // Generating a unique ID
//        long seed = System.currentTimeMillis(); // Using current time as seed for randomness
//        Random rng = new Random(seed);
//        long number;
//        do {
//            number = Math.abs(rng.nextLong() % 100000L); // Limiting the range to 5 digits
//        } while (demandeRepository.findByCodeDemande(number).isPresent());
//        return number;
        Calendar cal = Calendar.getInstance();
        cal.setTime(demande.getDateDemande());
        int year = cal.get(Calendar.YEAR);
        String code = demande.getNumAutorisation() + "-" + year;
        return code;
    }

    public Demande getDemandeById(Long id){
        Optional<Demande> demandeOp = demandeRepository.findById(id);
        Demande demande = null;
        if(demandeOp.isPresent()){
            demande = demandeOp.get();
        }
        else{
            throw new ResourceNotFoundException("Demande introuvable");
        }
        return demande;
    }

    public List<Demande> getAllDemande(){
        return demandeRepository.findAllDemandesPresent();
    }

    public List<Demande> getDemandesSupprime(){
        return demandeRepository.findAllDemandesNotPresent();
    }

    public Demande getDemandeByCode(String code){
        Optional<Demande> demandeOp = demandeRepository.findByCodeDemande(code);
        Demande demande = null;
        if(demandeOp.isPresent()){
            demande = demandeOp.get();
        }
        else{
            throw new ResourceNotFoundException("Demande introuvable");
        }
        return demande;

    }

    public Demande addDemande(Demande demande) throws Exception {

        Coordination coordination = coordinationService.findCoordinationById(demande.getCoordination().getId());
        Deleguation deleguation = deleguationService.getDeleguationById(demande.getDeleguation().getId());

        //generer les champs automatique
        //1- champs date
        Date date = new Date();
        demande.setDateDemande(date);
        //2-champs code
        String  uniqueCode = generateCode(demande);
        demande.setCodeDemande(uniqueCode);
        //3-champs nbre total beneficiare
        demande.setNbrTotalBeneficiaires(demande.getNbrBeneficiairesFemmes()+
                demande.getNbrBeneficiairesHommes());
        //4-champs nbre total agents
        demande.setNbrTotalAgents(demande.getNbrAgentsFemmes()+demande.getNbrAgentsHommes());
        //5- champs etat
        demande.setEtat("قيد العمل");
        //6- champs supprimé
        demande.setSupprime(false);

        // Check if there is a demande with the same numAutorisation created in the same year
        String numAutorisation = demande.getNumAutorisation();
        Calendar cal = Calendar.getInstance();
        cal.setTime(demande.getDateDemande());
        int year = cal.get(Calendar.YEAR);
        List<Demande> demandesWithSameNumAutorisationAndYear = demandeRepository.findByNumAutorisationAndYear(numAutorisation, year);
        if (!demandesWithSameNumAutorisationAndYear.isEmpty()) {
            // If there are demandes with the same emailPresident and year, you can handle it here
            // For example, you can merge the new demande with existing ones or throw an exception
            throw new Exception("Demande existantes");
        }

        demande.setCoordination(coordination);
        demande.setDeleguation(deleguation);

        return demandeRepository.save(demande);
    }

    public Demande updateDemande(Long id, Demande newDemande){
        Demande demande = demandeRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Demande intouvable !"));

        demande.setNomAssociation(newDemande.getNomAssociation());
        demande.setNomEtablissement(newDemande.getNomEtablissement());

        Coordination coordination = coordinationService.findCoordinationById(newDemande.getCoordination().getId());
        Deleguation deleguation = deleguationService.getDeleguationById(newDemande.getDeleguation().getId());

        demande.setCoordination(coordination);
        demande.setDeleguation(deleguation);

        demande.setNumAutorisation(newDemande.getNumAutorisation());
        demande.setAddresse(newDemande.getAddresse());
        demande.setTelephonePresident(newDemande.getTelephonePresident());
        demande.setEmailPresident(newDemande.getEmailPresident());
        demande.setEmailDirecteur(newDemande.getEmailDirecteur());
        demande.setTelDirecteur(newDemande.getTelDirecteur());
        demande.setNbrBeneficiairesHommes(newDemande.getNbrBeneficiairesHommes());
        demande.setNbrBeneficiairesFemmes(newDemande.getNbrBeneficiairesFemmes());
        demande.setNbrTotalBeneficiaires(newDemande.getNbrTotalBeneficiaires());
        demande.setNbrAgentsHommes(newDemande.getNbrAgentsHommes());
        demande.setNbrAgentsFemmes(newDemande.getNbrAgentsFemmes());
        demande.setNbrTotalAgents(newDemande.getNbrTotalAgents());
        demande.setSujetDemande(newDemande.getSujetDemande());
        demande.setRib(newDemande.getRib());
        demande.setCapaciteChargeTotal(newDemande.getCapaciteChargeTotal());
        demande.setNbrBeneficiairesServiceTotal(newDemande.getNbrBeneficiairesServiceTotal());
        demande.setNbrBeneficiairesServiceMatinal(newDemande.getNbrBeneficiairesServiceMatinal());
        demande.setNbrBeneficiairesServicePartiel(newDemande.getNbrBeneficiairesServicePartiel());
        demande.setNomPresident(newDemande.getNomPresident());
        demande.setNomDirecteur(newDemande.getNomDirecteur());
        demande.setDateCollecte(newDemande.getDateCollecte());
        demande.setDureeValidite(newDemande.getDureeValidite());
        demande.setRevenuTotalAnneePrecedente(newDemande.getRevenuTotalAnneePrecedente());
        demande.setRecetteTotalAnneePrecedente(newDemande.getRecetteTotalAnneePrecedente());
        demande.setTypeMilieu(newDemande.getTypeMilieu());
        demande.setCodeDemande(newDemande.getCodeDemande());
        demande.setCible(newDemande.getCible());
        demande.setMontantSuggereParAssoc(newDemande.getMontantSuggereParAssoc());
        demande.setMontantSuggereParDeleg(newDemande.getMontantSuggereParDeleg());

        //date de modification
        Date date = new Date();
        demande.setDateDerniereModification(date);

        //zipData
        demande.setEtat(newDemande.getEtat());

        return demandeRepository.save(demande);
    }

    public void deleteDemande(Long id){
        Date date = new Date();
        Demande demande = this.getDemandeById(id);
        demande.setDateSuppression(date);
        this.archiveDemande(id);
    }

    public Demande archiveDemande(Long id){
        Demande demande = this.getDemandeById(id);
        demande.setSupprime(true);
        return demandeRepository.save(demande);
    }

    public Demande desarchiveDemande(Long id){
        Demande demande = this.getDemandeById(id);
        demande.setSupprime(false);
        return demandeRepository.save(demande);
    }

    public int demandesEnCours(){
        return demandeRepository.countDemandesEnCours();
    }
    public int demandesAcceptees(){
        return demandeRepository.countDemandesAcceptees();
    }
    public int demandesRefusees(){
        return demandeRepository.countDemandesRefusees();
    }

    public List<Demande> getDemandeByCoordination(Long id){
        try {
            return demandeRepository.findByDeleguation(id);
        }
        catch (Exception e){
            throw new ResourceNotFoundException("Coordination introuvable");
        }
    }

    public List<Demande> getDemandeByDeleguation(Long id){
        return demandeRepository.findByDeleguation(id);
    }


    public Object nullData(){
        Object data = new Object(){
            public int totalDemandes = 0;
            public int demandesEnCours = 0;
            public int demandesAcceptees = 0;
            public int demandesRefusees = 0;
            public int demandeUrbaine = 0;
            public int demandesRurals = 0;
            public int beneficiaireHommes = 0;
            public int beneficiaireFemmes = 0;
            public int totalAgents = 0;
            public int totalBeneficiaires = 0;
            public int beneficiaireServiceMatinal = 0;
            public int beneficiaireServicePartiel = 0;
            public int beneficiaireServiceTotal = 0;
            public int beneficiaireTousService = 0;
        };
        return data;
    }

    public Object dataByDeleguation(Long id){
        if(getDemandeByDeleguation(id).isEmpty()){
            return nullData();
        }
        else {
            Object data = new Object(){
                public int totalDemandes = demandeRepository.countByDeleguationId(id);
                public int demandesEnCours = demandeRepository.countDemandesEnCoursByDeleguation(id);
                public int demandesAcceptees = demandeRepository.countDemandesAccepteesByDeleguation(id);
                public int demandesRefusees = demandeRepository.countDemandesRefuseesByDeleguation(id);
                public int demandeUrbaine = demandeRepository.countByTypeMilieuUrbainAndDeleguation(id);
                public int demandesRurals = demandeRepository.countByTypeMilieuRuralAndDeleguation(id);
                public int beneficiaireHommes = demandeRepository.sumNbrBeneficiairesHommesByDeleguation(id);
                public int beneficiaireFemmes = demandeRepository.sumNbrBeneficiairesFemmesByDeleguation(id);
                public int totalAgents = demandeRepository.sumNbrTotalAgentsByDeleguation(id);
                public int totalBeneficiaires = demandeRepository.sumNbrTotalBeneficiairesByDeleguation(id);
                public int beneficiaireServiceMatinal = demandeRepository.sumNbrBeneficiairesServiceMatinalByDeleguation(id);
                public int beneficiaireServicePartiel = demandeRepository.sumNbrBeneficiairesServicePartielByDeleguation(id);
                public int beneficiaireServiceTotal = demandeRepository.sumNbrBeneficiairesServiceTotalByDeleguation(id);
                public int beneficiaireTousService = beneficiaireServiceMatinal + beneficiaireServicePartiel + beneficiaireServiceTotal;
            };
            return data;
        }
    }

    public Object dataByCoordination(Long id){
        if(getDemandeByCoordination(id).isEmpty()){
            return nullData();
        }
        else {
            Object data = new Object(){
                public int totalDemandes = demandeRepository.countByCoordinationId(id);
                public int demandesEnCours = demandeRepository.countDemandesEnCoursByCoordination(id);
                public int demandesAcceptees = demandeRepository.countDemandesAccepteesByCoordination(id);
                public int demandesRefusees = demandeRepository.countDemandesRefuseesByCoordination(id);
                public int demandesRurals = demandeRepository.countByTypeMilieuRuralAndCoordination(id);
                public int demandesUrbaines = demandeRepository.countByTypeMilieuUrbainAndCoordination(id);
                public int beneficiaireHommes = demandeRepository.sumNbrBeneficiairesHommesByCoordination(id);
                public int beneficiaireFemmes = demandeRepository.sumNbrBeneficiairesFemmesByCoordination(id);
                public int totalAgents = demandeRepository.sumNbrTotalAgentsByCoordination(id);
                public int totalBeneficiaires = demandeRepository.sumNbrTotalBeneficiairesByCoordination(id);
                public int beneficiaireServiceMatinal = demandeRepository.sumNbrBeneficiairesServiceMatinalByCoordination(id);
                public int beneficiaireServicePartiel = demandeRepository.sumNbrBeneficiairesServicePartielByCoordination(id);
                public int beneficiaireServiceTotal = demandeRepository.sumNbrBeneficiairesServiceTotalByCoordination(id);
                public int beneficiaireTousService = beneficiaireServiceMatinal + beneficiaireServicePartiel + beneficiaireServiceTotal;
            };
            return data;
        }
    }

    public Object dataglobale(){
        Object data = new Object(){
            public int totalDemandes = demandeRepository.countDemandes();
            public int demandesEnCours = demandeRepository.countDemandesEnCours();
            public int demandesAcceptees = demandeRepository.countDemandesAcceptees();
            public int demandesRefusees = demandeRepository.countDemandesRefusees();
            public int demandesUrbaines = demandeRepository.countByTypeMilieuUrbain();
            public int demandesRurales = demandeRepository.countByTypeMilieuRural();
            public int agentsHommes = demandeRepository.sumNbrAgentsHommes();
            public int agentsFemmes = demandeRepository.sumNbrAgentsFemmes();
            public int beneficiairesHommes = demandeRepository.sumNbrBeneficiairesHommes();
            public int beneficiairesFemmes = demandeRepository.sumNbrBeneficiairesFemmes();
            public int totalAgents = demandeRepository.sumNbrTotalAgents();
            public int totalBeneficiaires = demandeRepository.sumNbrTotalBeneficiaires();
            public int beneficiaireServiceMatinal = demandeRepository.sumNbrBeneficiairesServiceMatinal();
            public int beneficiaireServicePartiel = demandeRepository.sumNbrBeneficiairesServicePartiel();
            public int beneficiaireServiceTotal = demandeRepository.sumNbrBeneficiairesServiceTotal();
            public int beneficiaireTousService = beneficiaireServiceMatinal + beneficiaireServicePartiel + beneficiaireServiceTotal;
            public int beneficaireHommeRural = demandeRepository.sumNbrBeneficiairesHommesByTypeMilieu("قروي");
            public int beneficaireFemmeRural = demandeRepository.sumNbrBeneficiairesFemmesByTypeMilieu("قروي");
            public int beneficaireHommeUrbain = demandeRepository.sumNbrBeneficiairesHommesByTypeMilieu("حضري");
            public int beneficaireFemmeUrbain = demandeRepository.sumNbrBeneficiairesFemmesByTypeMilieu("حضري");
        };
        return data;
    }

    public Object dashBoardData(Long idDeleguation, Long idCoordination){
        if(idDeleguation !=null && idCoordination ==null){
            return dataByDeleguation(idDeleguation);
        }
            else if (idDeleguation ==null && idCoordination !=null) {
            return dataByCoordination(idCoordination);
        }
        else
            return dataglobale();
    }

    public Demande uploadFile(Long id, MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try{
            if(fileName.contains("..")){
                throw new Exception("Filemane contains invalid path sequence"
                        + fileName);
            }
            Demande demande = getDemandeById(id);
            demande.setFileName(fileName);
            demande.setFileType(file.getContentType());
            demande.setZipData(file.getBytes());
            return demandeRepository.save(demande);
        }catch (Exception e){
            throw new ResourceNotFoundException("File not uploaded"+fileName);
        }
    }

}
