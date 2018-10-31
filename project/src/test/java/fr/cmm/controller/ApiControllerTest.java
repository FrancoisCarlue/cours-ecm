package fr.cmm.controller;


import com.mongodb.util.JSON;
import fr.cmm.domain.Recipe;
import fr.cmm.helper.PageQuery;
import fr.cmm.service.RecipeService;
import org.json.JSONArray;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;

import java.util.ArrayList;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApiControllerTestConfig.class)
@WebAppConfiguration
public class ApiControllerTest {

    @Autowired
    RecipeService recipeService;

    @Autowired
    WebApplicationContext wac;

    MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = webAppContextSetup(wac).build();

        Mockito.reset(recipeService);
    }

    @Test
    public void recipeWithID() throws Exception {
        String id = "5bbe19a1d5142a1074d0f61e";

        Mockito.when(recipeService.findById(id)).thenReturn(new Recipe());
        mockMvc.perform(get("/api/recipes/" + id).contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().is(200))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
        ;
    }

    @Test
    public void recipes() throws Exception {
        Mockito.when(recipeService.findByQueryApi(any())).thenReturn(new ArrayList<Recipe>());
        Mockito.when(recipeService.toJsonWithPage(any(),anyInt())).thenReturn(new JSONArray());
        mockMvc.perform(get("/api/recipes").contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));

    }

    @Test
    public void recette404() throws Exception {
        String id = "blablabla";

        mockMvc.perform(get("/api/recipes/" + id))
                .andExpect(status().is(404));
    }

}


