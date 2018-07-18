package ch.globaz.tmmas.rentesservice.application;


import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;

public class ModulesDependenciesTest {


    private static JavaClasses classes;


    @BeforeAll
    public static void setUpClasses() {
        classes = new ClassFileImporter().importPackages(BASE_PACKAGE_NAME);
    }

}
