package ch.globaz.tmmas.rentesservice.application.api.web;



import ch.globaz.tmmas.rentesservice.application.service.DossierService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/data")
public class DataManagementController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataManagementController.class);

    @Autowired
    DossierService dossierService;

    /**

    static final Faker faker = new Faker(new Locale("fr"));
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    DateFormat df = new SimpleDateFormat("dd.MM.yyyy");

    @RequestMapping(value = "/sample",method = RequestMethod.POST)
    public ResponseEntity<String> createRentesSampleData(@RequestBody SampleDataDto dto){
        LOGGER.debug("createPerson data samples(), {} elements to generate",dto.getNbValeurs());

        //récupération du nombre de valeurs à générer
        Integer nbRentesToGenerate = Integer.valueOf(dto.getNbValeurs());


        //iteration sur les personnes, et generation des rentes
        IntStream.range(0,nbRentesToGenerate).forEach((iterationNo -> {

            Dossier dossier = Dossier.builder(personne.getTechnicalId(),LocalDate.now());

            dossierService.sauve(dossier);

        });


        return new ResponseEntity<String>("OK,  "+ nbRentesToGenereateEffective +" rentes Created",HttpStatus.CREATED);
    }

    @RequestMapping(value = "/sample-rt",method = RequestMethod.POST)
    public ResponseEntity<String> createRentesSampleDataWithRestTemplate(@RequestBody SampleDataDto dto){
        LOGGER.debug("createPerson data samples(), {} elements to generate",dto.getNbValeurs());

        //récupération du nombre de valeurs à générer
        Integer nbRentesToGenerate = Integer.valueOf(dto.getNbValeurs());
        //récupérationd personnes physiques
        List<PersonnesPhysiqueDto> personnesPhysiques  = restTemplatePersonnesPhysiqueService.getPersonnesPhysique();
        Integer nbOfPersonnesPhysique = personnesPhysiques.size();

        //plafin nbRentes a geerer
        Integer nbRentesToGenereateEffective = (nbRentesToGenerate < nbOfPersonnesPhysique) ? nbRentesToGenerate :
                nbOfPersonnesPhysique;

        LOGGER.debug("createPerson data samples(), found {} personnesPhysique, so {} rentes will be generated ",
                nbOfPersonnesPhysique,nbOfPersonnesPhysique);

        //iteration sur les personnes, et generation des rentes
        personnesPhysiques.subList(0,nbRentesToGenereateEffective).stream().forEach(personne -> {

            Dossier dossier = Dossier.builder(personne.getTechnicalId(),LocalDate.now());

            dossierService.sauve(dossier);

        });


        return new ResponseEntity<String>("OK,  "+ nbRentesToGenereateEffective +" rentes Created",HttpStatus.CREATED);
    }



    private String getNumero () {
        return String.valueOf(faker.number().numberBetween(10000,99999));
    }

    private Long getRequerantId () {
        return Long.valueOf(faker.number().numberBetween(1,10000));
    }

    private LocalDate getDateEnregistrement () {

        Date date = faker.date().birthday(0,1);

        return LocalDate.parse(df.format(date),formatter);
    }


**/
}
