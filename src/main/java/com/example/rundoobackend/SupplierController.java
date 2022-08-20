package com.example.rundoobackend;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


@RestController
@RequestMapping("/api")
public class SupplierController {

    private final SupplierRepository repository;

    SupplierController(SupplierRepository repository) {
        this.repository = repository;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/supplier")
    ResponseEntity<Supplier> saveSupplier(@RequestParam(value = "name", defaultValue = "") String companyName,
                          @RequestParam("logo") MultipartFile logo,
                          @RequestParam(value = "streetAddress", defaultValue = "") String streetAddress,
                          @RequestParam(value = "city", defaultValue = "") String city,
                          @RequestParam(value = "state", defaultValue = "") String state,
                          @RequestParam(value = "zip", defaultValue = "") String zip,
                          @RequestParam(value = "country", defaultValue = "") String country) {

        String logoPath = "";
        if (null == logo.getOriginalFilename()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            byte[] bytes = logo.getBytes();
            Path path = Paths.get("logos", UUID.randomUUID() + ".jpeg");
            Files.write(path, bytes);
            logoPath = String.valueOf(path.getFileName());
            System.out.println(path.getFileName());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Supplier newSupplier = new Supplier(companyName, logoPath, streetAddress, city, state, zip, country);

        repository.save(newSupplier);

        return new ResponseEntity<>(newSupplier, HttpStatus.OK);

    }
}
