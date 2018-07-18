package ch.globaz.tmmas.rentesservice.application;


import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

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


    @BeforeAll
    static void setupClasses() {
        classes = new ClassFileImporter().importPackages(BASE_PACKAGE_NAME);
    }

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

}
