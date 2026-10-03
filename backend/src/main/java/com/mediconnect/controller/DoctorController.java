package com.mediconnect.controller;

import com.mediconnect.model.Doctor;
import com.mediconnect.repository.DoctorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    private final DoctorRepository doctors;

    public DoctorController(DoctorRepository doctors) {
        this.doctors=doctors;
    }

    @GetMapping
    public List<Doctor> all() {
        return doctors.findAll();
    }

    @GetMapping("/available")
    public List<Doctor> available() {
        return doctors.findByAvailableTrue();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> one(@PathVariable String id) {
        return doctors.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Doctor create(@RequestBody Doctor doctor) {
        return doctors.save(doctor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> update(
            @PathVariable String id,
            @RequestBody Doctor d) {
        return doctors.findById(id).map(existing -> {
            existing.setName(d.getName());
            existing.setDepartment(d.getDepartment());
            existing.setSpecialization(d.getSpecialization());
            existing.setRating(d.getRating());
            existing.setExperience(d.getExperience());
            existing.setAvailable(d.isAvailable());
            return ResponseEntity.ok(doctors.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        if (!doctors.existsById(id))
            return ResponseEntity.notFound().build();
        doctors.deleteById(id);
        return ResponseEntity.ok(Map.of("message","Doctor deleted successfully"));
    }
}
