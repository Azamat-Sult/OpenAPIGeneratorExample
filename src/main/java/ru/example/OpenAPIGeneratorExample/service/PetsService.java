package ru.example.OpenAPIGeneratorExample.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.example.OpenAPIGeneratorExample.api.PetsApiDelegate;
import ru.example.OpenAPIGeneratorExample.model.Pet;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetsService implements PetsApiDelegate {

    @Override
    public ResponseEntity<Void> createPets() {
        System.out.println("New pet created");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Pet>> listPets(Integer limit) {
        List<Pet> petList = new ArrayList<>();
        Pet pet1 = new Pet();
        pet1.setName("pet1");
        petList.add(pet1);
        Pet pet2 = new Pet();
        pet2.setName("pet2");
        petList.add(pet2);
        Pet pet3 = new Pet();
        pet3.setName("pet3");
        petList.add(pet3);
        return ResponseEntity.ok(petList);
    }

    @Override
    public ResponseEntity<Pet> showPetById(String petId) {
        Pet pet1 = new Pet();
        pet1.setName("pet1");
        return ResponseEntity.ok(pet1);
    }

}