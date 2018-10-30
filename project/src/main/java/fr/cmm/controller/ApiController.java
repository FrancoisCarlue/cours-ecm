package fr.cmm.controller;

import fr.cmm.domain.Recipe;
import fr.cmm.helper.PageQuery;
import fr.cmm.service.RecipeService;
import org.json.JSONArray;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;


@RestController
public class ApiController {

    @Inject
    private RecipeService recipeService;


    @RequestMapping(value = "/api/recipes", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray listingRecipes(){
        PageQuery pageQuery = new PageQuery();
        Iterable<Recipe> iterableRecipes = recipeService.findByQueryApi(pageQuery);
        return recipeService.toJsonWithPage(iterableRecipes,pageQuery.getSize());
    }

    @RequestMapping(value="/api/recipes/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Recipe showRecipe(@PathVariable String id){
        Recipe recipeApi = recipeService.findById(id);
        return recipeApi;
    }
}