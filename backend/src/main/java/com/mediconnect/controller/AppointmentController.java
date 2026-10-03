package com.mediconnect.controller;

import com.mediconnect.dto.AppointmentRequest;
import com.mediconnect.model.Appointment;
import com.mediconnect.repository.AppointmentRepository;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    private final AppointmentRepository appointments;

    public AppointmentController(AppointmentRepository appointments) {
        this.appointments=appointments;
    }

    @PostMapping
    public ResponseEntity<?> book(@Valid @RequestBody AppointmentRequest r) {
        if (appointments.existsByDoctorAndDateAndTime(
                r.doctor(), r.date(), r.time())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message",
                            "Doctor already booked for this date and time"));
        }

        Appointment a=new Appointment();
        a.setPatientName(r.patientName().trim());
        a.setEmail(r.email().trim().toLowerCase());
        a.setDepartment(r.department());
        a.setDoctor(r.doctor());
        a.setDate(r.date());
        a.setTime(r.time());
        a.setReason(r.reason());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(appointments.save(a));
    }

    @GetMapping
    public List<Appointment> all() {
        return appointments.findAll();
    }

    @GetMapping("/patient/{email}")
    public List<Appointment> patient(@PathVariable String email) {
        return appointments.findByEmailOrderByCreatedAtDesc(email.toLowerCase());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        if (!appointments.existsById(id))
            return ResponseEntity.notFound().build();

        appointments.deleteById(id);
        return ResponseEntity.ok(Map.of("message","Appointment cancelled"));
    }
}
