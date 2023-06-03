package ru.example.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.example.client.service.PetsService;

@Controller
@RequestMapping("/api/v1")
public class PetsController {

    private final PetsService petsService;


    public PetsController(PetsService petsService) {
        this.petsService = petsService;
    }

    @GetMapping(path = "/getAllPets")
    public String getPetList(Model model) {
        model.addAttribute("petList", petsService.getAllPetsAsync());
        return "petlist";
    }

}