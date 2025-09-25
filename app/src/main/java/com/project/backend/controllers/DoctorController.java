package com.project.backend.controllers;

import com.project.backend.models.Doctor;
import com.project.backend.services.DoctorService;
import com.project.backend.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private TokenService tokenService;

    // GET doctor availability by id and date
    @GetMapping("/{doctorId}/availability")
    public ResponseEntity<?> getDoctorAvailability(
            @RequestHeader("Authorization") String token,
            @PathVariable Long doctorId,
            @RequestParam String date) {

        // Validate token
        if (!tokenService.validateToken(token)) {
            return ResponseEntity.status(401).body("Invalid or expired token");
        }

        try {
            LocalDate parsedDate = LocalDate.parse(date);
            List<String> availableSlots = doctorService.getAvailableTimeSlots(doctorId, parsedDate);

            if (availableSlots.isEmpty()) {
                return ResponseEntity.ok("No available slots for this date");
            }

            return ResponseEntity.ok(availableSlots);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid request: " + e.getMessage());
        }
    }
}
