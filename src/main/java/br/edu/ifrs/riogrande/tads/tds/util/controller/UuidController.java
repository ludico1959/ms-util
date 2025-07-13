package br.edu.ifrs.riogrande.tads.tds.util.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifrs.riogrande.tads.tds.util.service.UuidService;
import br.edu.ifrs.riogrande.tads.tds.util.controller.dto.UuidResponseDTO;
import br.edu.ifrs.riogrande.tads.tds.util.controller.dto.ApiResponse;

@RestController
public class UuidController {

    private final UuidService uuidService;

    public UuidController(UuidService UuidService) {
        this.uuidService = UuidService;
    }

    @Deprecated(since = "2025-07-12", forRemoval = false)
    @GetMapping(
        value = "/uuid", 
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> generate(
        @RequestParam(name = "no_passwords", defaultValue = "1") int noPasswords
    ) {
        try {
            List<String> passwords = this.uuidService.generate(noPasswords);

            return ResponseEntity.ok(passwords);
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                .badRequest()
                .body(List.of(e.getMessage())); 
        }

    }

    @GetMapping(
        value = "/api/v1/uuid", 
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<UuidResponseDTO>> generateV1(
        @RequestParam(name = "no_passwords", defaultValue = "1") int noPasswords
    ) {
        try {
            List<String> uuids = this.uuidService.generate(noPasswords);
            UuidResponseDTO response = new UuidResponseDTO(uuids);

            return ResponseEntity.ok(new ApiResponse<>(response));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}

