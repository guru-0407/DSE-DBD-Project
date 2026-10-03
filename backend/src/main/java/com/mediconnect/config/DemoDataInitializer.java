package com.mediconnect.config;

import com.mediconnect.model.Doctor;
import com.mediconnect.repository.DoctorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoDataInitializer {
    @Bean
    CommandLineRunner seedDoctors(DoctorRepository doctors) {
        return args -> {
            if (doctors.count() > 0) return;

            saveDoctor(doctors, "Dr. Rahul Kumar", "Cardiology",
                    "Cardiologist", 4.9, 10);
            saveDoctor(doctors, "Dr. Priya Sharma", "Neurology",
                    "Neurologist", 4.8, 8);
            saveDoctor(doctors, "Dr. Arjun Reddy", "Orthopedics",
                    "Orthopedic Surgeon", 4.9, 12);
            saveDoctor(doctors, "Dr. Sneha Rao", "Pediatrics",
                    "Pediatrician", 4.7, 7);
        };
    }

    private void saveDoctor(DoctorRepository repository, String name,
            String department, String specialization,
            double rating, int experience) {
        Doctor doctor = new Doctor();
        doctor.setName(name);
        doctor.setDepartment(department);
        doctor.setSpecialization(specialization);
        doctor.setRating(rating);
        doctor.setExperience(experience);
        doctor.setAvailable(true);
        repository.save(doctor);
    }
}
