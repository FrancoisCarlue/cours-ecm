package fr.cmm.controller;

import fr.cmm.domain.Recipe;
import fr.cmm.helper.PageQuery;
import fr.cmm.helper.Pagination;
import fr.cmm.service.RecipeService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.ArrayList;


@RestController
public class ApiController {

    @Inject
    private RecipeService recipeService;


    @RequestMapping("/api/recipes")
    public Iterable<Recipe> hello(){
        PageQuery pageQuery = new PageQuery();

        Iterable<Recipe> arrayRecipe = recipeService.findByQuery(pageQuery);
        return arrayRecipe;
    }


    @RequestMapping("/api/recipes/{id}")
    public Recipe hello2(@PathVariable String id){
        Recipe recipeApi = recipeService.findById(id);
        return recipeApi;
    }
}