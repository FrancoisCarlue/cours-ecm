package fr.cmm.controller;

import fr.cmm.domain.Recipe;
import fr.cmm.service.RecipeService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;


@RestController
public class ApiController {

    @Inject
    private RecipeService recipeService;


    @RequestMapping("/api/recipes")
    public String hello(){
        return "hello World";
    }


    @RequestMapping("/api/recipes/{id}")
    public Recipe hello2(@PathVariable String id){
        Recipe recipeApi = recipeService.findById(id);
        return recipeApi;
    }
}