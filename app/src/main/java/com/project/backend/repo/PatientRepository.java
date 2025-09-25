package com.project.back_end.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prescriptions")
public class PrescriptionController {

    @GetMapping
    public String getAllPrescriptions() {
        return "List of prescriptions";
    }

    @PostMapping
    public String createPrescription() {
        return "Prescription created";
    }
}
