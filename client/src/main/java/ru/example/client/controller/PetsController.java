package ru.example.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.example.client.service.PetsService;

@Controller
public class PetsController {

    private final PetsService petsService;


    public PetsController(PetsService petsService) {
        this.petsService = petsService;
    }

    @GetMapping(path = "/petList")
    public String getPetList(Model model) {
        model.addAttribute("petList", petsService.getPetList());
        return "petlist";
    }

}