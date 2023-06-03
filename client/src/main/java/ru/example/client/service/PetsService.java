package ru.example.client.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.example.client.api.PetsApi;
import ru.example.client.model.GetAllPetsRq;
import ru.example.client.model.GetAllPetsRs;
import ru.example.client.model.Pet;
import ru.example.client.model.RqParams;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class PetsService {

    private final PetsApi petsApi;
    private final RestService restService;

    public List<Pet> getAllPets() {
        GetAllPetsRq request = new GetAllPetsRq();
        request.setId("999");
        request.setCityCode("Moscow");
        GetAllPetsRs response = petsApi.getAllPets(request,10);
        System.out.println(response.getCode());
        System.out.println(response.getDescription());
        return response.getPetList();
    }

    public List<Pet> getAllPetsAsync() {
        GetAllPetsRq request = new GetAllPetsRq();
        request.setId("999");
        request.setCityCode("Moscow");
        RqParams<GetAllPetsRq, GetAllPetsRs> rqParams = new RqParams<>();
        rqParams.setUrl("http://localhost:8080/api/v1/pets");
        rqParams.setRequest(request);
        rqParams.setResponseClass(GetAllPetsRs.class);
        rqParams.setHeaders(Map.of("myHeader", "myHeader"));
        rqParams.setSendByWebClient(true);
        GetAllPetsRs response = restService.send(rqParams);
        return response.getPetList();
    }

}