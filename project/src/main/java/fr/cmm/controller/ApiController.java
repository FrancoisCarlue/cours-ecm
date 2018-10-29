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
import java.util.Collections;
import java.util.Iterator;


@RestController
public class ApiController {

    @Inject
    private RecipeService recipeService;


    @RequestMapping(value = "/api/recipes",produces = "application/json")
    public String listingRecipes(){
        PageQuery pageQuery = new PageQuery();
        Iterable<Recipe> iterableRecipes = recipeService.findByQueryApi(pageQuery);
        return recipeService.toJsonWithPage(iterableRecipes,pageQuery.getSize());


    }

    @RequestMapping(value="/api/recipes/{id}", produces = "application/json")
    public Recipe showRecipe(@PathVariable String id){
        Recipe recipeApi = recipeService.findById(id);
        return recipeApi;
    }
}