package tn.esprit.devops_project.controllers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import tn.esprit.devops_project.services.Iservices.IStockService;

@WebMvcTest(StockController.class)
public class StockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IStockService stockService;




    @Test
    public void testAddRetrieveAndDeleteStock() throws Exception {

        mockMvc.perform(post("/stock")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Test Stock\"}"))

                .andExpect(status().isOk());

      
    }
}