package forum.control;

import forum.Main;
import forum.model.Post;
import forum.service.post.PostUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * PostControllerTest.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 7/3/2020
 */
@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application.properties")
@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
@Sql(scripts = "classpath:schema.sql", config = @SqlConfig(encoding = "UTF-8"))
public class PostControllerTest {
    @MockBean
    private PostUserService service;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessage() throws Exception {
        final LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("description", "куплю С ради С");
        requestParams.add("name", "C");
        requestParams.add("names", "user");
        requestParams.add("date", "");

        this.mockMvc.perform(post("/create")
                .params(requestParams))
               // .param("names", "user")
              //  .param("date", ""))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        final ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(this.service).add(argument.capture());
        assertThat(argument.getValue().getName(), is("С"));
    }

    @Test
    public void updateMessage() {
    }

    @Test
    public void deleteMessage() {
    }

    @Test
    public void createMessage() {
    }

    @Test
    public void move404() {
    }

    @Test
    public void moveToCreate() {
    }

    @Test
    public void update() {
    }

    @Test
    public void testUpdate() {
    }

    @Test
    public void create() {
    }

    @Test
    public void remove() {
    }
}

