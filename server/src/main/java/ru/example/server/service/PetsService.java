package ru.example.server.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.example.server.api.PetsApiDelegate;
import ru.example.server.model.GetAllPetsRq;
import ru.example.server.model.GetAllPetsRs;
import ru.example.server.model.Pet;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetsService implements PetsApiDelegate {

    @Override
    public ResponseEntity<Void> createPet() {
        System.out.println("New pet created");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<GetAllPetsRs> getAllPets(GetAllPetsRq getAllPetsRq, Integer limit) {
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
        Pet pet4 = new Pet();
        pet4.setName("pet4");
        petList.add(pet4);
        GetAllPetsRs response = new GetAllPetsRs();
        response.setPetList(petList);
        response.setCode(200);
        response.setDescription("OK, id: " + getAllPetsRq.getId() + ", city code: " + getAllPetsRq.getCityCode());
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Pet> getPetById(String petId) {
        Pet pet1 = new Pet();
        pet1.setName("pet with id: " + petId);
        return ResponseEntity.ok(pet1);
    }

}