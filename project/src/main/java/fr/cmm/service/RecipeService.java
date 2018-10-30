package fr.cmm.service;

import java.util.*;

import javax.inject.Inject;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.bson.types.ObjectId;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import fr.cmm.domain.Recipe;
import fr.cmm.helper.PageQuery;

@Service
public class RecipeService {
    @Inject
    private MongoCollection recipeCollection;

    public Iterable<Recipe> findByQuery(PageQuery query) {
        String mongoQuery = "{}";
        String[] params = {};

        if (query.getTag() != null && !"".equals(query.getTag())) {
            mongoQuery = "{tags: #}";
            params = new String[] {query.getTag()};
        }

        return recipeCollection
                .find(mongoQuery, (Object[]) params)
                .skip(query.skip())
                .limit(query.getSize())
                .as(Recipe.class);
    }


    public Iterable<Recipe> findByQueryApi(PageQuery query) {
        String mongoQuery = "{}";
        String[] params = {};

        if (query.getTag() != null && !"".equals(query.getTag())) {
            mongoQuery = "{tags: #}";
            params = new String[] {query.getTag()};
        }

        Iterable<Recipe> iterableRecipeRaw = recipeCollection
                .find(mongoQuery, (Object[]) params)
                .skip(query.skip())
                .as(Recipe.class); //no size limit here

        ArrayList<Recipe> arrayRecipe= new ArrayList<Recipe>();

        for(Recipe recipe : iterableRecipeRaw){
            arrayRecipe.add(recipe);
        }
        Collections.sort(arrayRecipe); //Recipe's method CompareTo was overriden to have a sorting by date
        Collections.reverse(arrayRecipe);

        return arrayRecipe;
    }

    public JSONArray toJsonWithPage(Iterable<Recipe> list,int pageSize) {
        Gson gson = new Gson();
        JSONArray returnedJSON = new JSONArray();
        int counter=0;
        int page;
        for(Recipe recipe : list) {
            page = counter/pageSize+1;
            JSONObject json1 = new JSONObject(gson.toJson(recipe));
            JSONObject json2 = new JSONObject("{page: "+page+"}");
            returnedJSON.put(mergeJSONObjects(json1,json2));
            counter = counter +1;
        }
        return returnedJSON;
    }

    public String mergeJSONObjects(JSONObject json1, JSONObject json2) {
        JSONObject mergedJSON ;
        try {
            mergedJSON = new JSONObject(json1, JSONObject.getNames(json1));
            for (String key : JSONObject.getNames(json2)) {
                mergedJSON.put(key, json2.get(key));
            }

        } catch (JSONException e) {
            throw new RuntimeException("JSON Exception" + e);
        }
        return mergedJSON.toString();
    }

    public long countByQuery(PageQuery query) {
        return recipeCollection.count();
    }

    public Iterator<Recipe> findRandom(int count) {
        return recipeCollection.find("{randomLocation: {$near: [#, 0]}}", Math.random()).limit(count).as(Recipe.class);
    }

    public Recipe findById(String id) {
        return recipeCollection.findOne(new ObjectId(id)).as(Recipe.class);
    }

    public void save(Recipe recipe) {
        recipeCollection.save(recipe);
    }

    public List<String> findAllTags() {
        return recipeCollection.distinct("tags").as(String.class);
    }
}
