package pl.app.gitplatformprovider;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponentsBuilder;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GitRepositoryControllerIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void whenUserExistsAndHasPublicRepositories_thenShouldReturnNotEmptyResponse() throws Exception {
        var provider = "github";
        var username = "dawidbladek0831";
        var uri = UriComponentsBuilder
                .fromUriString(GitRepositoryController.resourcePath)
                .buildAndExpand(provider, username)
                .toUriString();

        mockMvc.perform(get(uri))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(greaterThan(0))));
    }
}