package com.example.hospital2;

import com.example.hospital2.entities.Patient;
import com.example.hospital2.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class Hospital2Application implements CommandLineRunner {

@Autowired
private PatientRepository patientRepository;
    public static void main(String[] args) {
        SpringApplication.run(Hospital2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        patientRepository.save(new Patient(null,"Meryem",new Date(),false,34));
        patientRepository.save(new Patient(null,"Adnane",new Date(),false,88));
        patientRepository.save(new Patient(null,"BennisBo",new Date(),true,16));







    }
}
