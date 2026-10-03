package com.mediconnect.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AppointmentRequest(
    @NotBlank String patientName,
    @NotBlank @Email String email,
    @NotBlank String department,
    @NotBlank String doctor,
    @NotBlank String date,
    @NotBlank String time,
    String reason
) {}
