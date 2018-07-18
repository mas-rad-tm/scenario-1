package ch.globaz.tmmas.rentesservice.application;


import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

public class ModulesDependenciesTest {


    private static JavaClasses classes;
    private final static String BASE_PACKAGE_NAME = "ch.globaz.tmmas.rentesservice";

    //layer name
    private final static String APPLICATION_LAYER_NAME = "Application";
    private final static String INFRASTRUCTURE_LAYER_NAME = "Infrastructure";
    private final static String DOMAINE_LAYER_NAME = "Domaine";

    private final static String APPLICATION_PACKAGE = BASE_PACKAGE_NAME + ".application";
    private final static String DOMAINE_PACKAGE = BASE_PACKAGE_NAME + ".domaine";
    private final static String INFRASTRUCTURE_PACKAGE = BASE_PACKAGE_NAME + ".infrastructure";

    private final static String INFRASTRUCTURE_IMPL_PACKAGE = INFRASTRUCTURE_PACKAGE + ".repository";
    private final static String CONTROLLERS_PACKAGES = APPLICATION_PACKAGE + "..web.controller..";
    private final static String SERVICE_IMPL_PACKAGE = APPLICATION_PACKAGE + "..service.impl..";



    @BeforeAll
    static void setupClasses() {
        classes = new ClassFileImporter().importPackages(BASE_PACKAGE_NAME);
    }

    /**
     * Test s'assurant du respect des couches principales des architectures backend </br>
     */
    @Test
    public void assertThatModuleDependenciesConformToHexagonalPattern() {

        ArchRule hexagonalModuleRule = layeredArchitecture()
                .layer(APPLICATION_LAYER_NAME).definedBy(APPLICATION_PACKAGE + "..")
                .layer(DOMAINE_LAYER_NAME).definedBy(DOMAINE_PACKAGE + "..")
                .layer(INFRASTRUCTURE_LAYER_NAME).definedBy(INFRASTRUCTURE_PACKAGE + "..")

                .whereLayer(APPLICATION_LAYER_NAME).mayNotBeAccessedByAnyLayer()
                .whereLayer(INFRASTRUCTURE_LAYER_NAME).mayOnlyBeAccessedByLayers(APPLICATION_LAYER_NAME)
                .whereLayer(DOMAINE_LAYER_NAME).mayOnlyBeAccessedByLayers(APPLICATION_LAYER_NAME, INFRASTRUCTURE_LAYER_NAME);

        hexagonalModuleRule.check(classes);

    }

    /**
     * Test s'assurant que aucun cycle ne soit présent dans les dépendances. Maven le fait déjà au build (il ne build
     * pas si il détecte des cycéles). Mais nous ne devons pas êtres dépendandts de maven.
     */
    @Test
    public void assertThatNoCyclePresentsInLayers() {

        ArchRule noCycleRules = slices().matching(BASE_PACKAGE_NAME +".(*)..").should().beFreeOfCycles();

        noCycleRules.check(classes);
    }

    /**
     * Tests s'assurant que les controlleurs ne dépendent pas d'implémentations de services
     */
    @Test
    public void assertThatControllerDontDependOnServiceImplementation() {

        ArchRule controllerDependenciesRules = noClasses().that().resideInAPackage(CONTROLLERS_PACKAGES )
                .should().accessClassesThat().resideInAPackage(SERVICE_IMPL_PACKAGE);

        controllerDependenciesRules.check(classes);
    }

    /**
     * Test s'assurant que les services ne dépendant pas d'implémentation de repository
     */
    @Test
    public void assertThatServiceDontDependOnRepositoryImplementation() {

        ArchRule serviceDependenciesRules = noClasses().that().resideInAPackage(APPLICATION_PACKAGE + "..")
                .should().accessClassesThat().resideInAPackage(INFRASTRUCTURE_IMPL_PACKAGE + "..");

        serviceDependenciesRules.check(classes);
    }

}
