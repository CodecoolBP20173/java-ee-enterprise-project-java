package com.codecool.library.controller;

import com.codecool.library.model.Book;
import com.codecool.library.repository.BookRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Configuration
class DataSourceSetup {
    @Bean
    @Primary
    DataSource getDataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(H2)
                .build();
    }

}


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LibraryTest {


    @Autowired
    BookRepository bookRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    private void setup()  {
        bookRepository.deleteAll();
        bookRepository.save(new Book("Mock book Volume One"));
        bookRepository.save(new Book("Mock book Volume Two"));
    }

    @Test
    public void indexLoads() throws Exception {
        setup();
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void adminLoads() throws Exception {
        this.mockMvc.perform(get("/admin"))
                .andDo(print())
                .andExpect(status().isOk());
    }


}