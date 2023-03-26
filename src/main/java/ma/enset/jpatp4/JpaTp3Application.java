package ma.enset.jpatp4;

import ma.enset.jpatp4.entities.Patient;
import ma.enset.jpatp4.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
@SpringBootApplication
public class JpaTp3Application  {
    @Autowired
    private PatientRepository patientRepository;
    public static void main(String[] args) {
        SpringApplication.run(JpaTp3Application.class, args);
    }
    //@Bean
    CommandLineRunner commandlineRunner() {
        return args -> {
        patientRepository.save(
                new Patient(null,"Oumaima",new Date(),false,150)
        );
        patientRepository.save(
                new Patient(null,"Salim",new Date(),true,100)
        );
        patientRepository.save(
                new Patient(null,"Ahmed",new Date(),false,230)
        );
        patientRepository.save(
                new Patient(null,"Salma",new Date(),true,130)
        );
        // affichage de tous les patients
            patientRepository.findAll().forEach(
                    p-> {
                        System.out.println(p.getNom());
                    });
        };

    }
}
