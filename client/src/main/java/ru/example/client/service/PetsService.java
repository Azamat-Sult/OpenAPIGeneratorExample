package ru.example.client.service;

import org.springframework.stereotype.Service;
import ru.example.client.api.PetsApi;
import ru.example.client.model.GetAllPetsRq;
import ru.example.client.model.GetAllPetsRs;
import ru.example.client.model.Pet;

import java.util.List;

@Service
public class PetsService {

    private final PetsApi petsApi;

    public PetsService(PetsApi petsApi) {
        this.petsApi = petsApi;
    }

    public List<Pet> getAllPets() {
        GetAllPetsRq request = new GetAllPetsRq();
        request.setId("999");
        request.setCityCode("Moscow");
        GetAllPetsRs response = petsApi.getAllPets(request,10);
        System.out.println(response.getCode());
        System.out.println(response.getDescription());
        return response.getPetList();
    }

}