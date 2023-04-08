package ru.example.client.service;

import org.springframework.stereotype.Service;
import ru.example.client.api.PetsApi;
import ru.example.client.model.Pet;

import java.util.List;

@Service
public class PetsService {

    private final PetsApi petsApi;

    public PetsService(PetsApi petsApi) {
        this.petsApi = petsApi;
    }

    public List<Pet> getPetList() {
        return petsApi.listPets(10);
    }

}