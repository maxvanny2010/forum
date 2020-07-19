package forum.control;

import forum.Main;
import forum.model.Post;
import forum.repository.PostRepository;
import forum.service.post.PostUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;

import java.time.LocalDateTime;

import static org.junit.Assert.assertSame;
import static org.mockito.BDDMockito.given;
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
@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class PostControllerTest {
    @MockBean
    private PostRepository repository;
    @Autowired
    private PostUserService service;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void shouldReturnDefaultPost() throws Exception {
        final LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("description", "куплю С ради С");
        requestParams.add("name", "NEW");
        requestParams.add("names", "user");
        requestParams.add("date", "");

        this.mockMvc.perform(post("/create")
                .params(requestParams))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        final ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(this.repository).save(argument.capture());
        final String actual = argument.getValue().getName();
        assertSame("NEW", actual);
    }

    @Test
    @WithMockUser
    public void whenUpdatePostOk() throws Exception {
        given(this.service.findByNameAndId("user", 1)).willReturn(
                new Post(1, "A", "Куплю А ради А",
                        LocalDateTime.of(3000, 1, 1, 0, 0),
                        "user"));
        final LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("id", "1");
        requestParams.add("description", "куплю С ради С");
        requestParams.add("name", "UPDATE");
        requestParams.add("authorPost", "user");
        requestParams.add("date", "3000-10-10T11:11");
        this.mockMvc.perform(post("/update")
                .params(requestParams))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        final ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(this.repository).save(argument.capture());
        final String actual = argument.getValue().getName();
        assertSame("UPDATE", actual);
    }

    @Test
    @WithMockUser
    public void whenRemovePostOk() throws Exception {
        given(this.service.findByNameAndId("user", 1)).willReturn(
                new Post(1, "A", "Куплю А ради А",
                        LocalDateTime.of(3000, 1, 1, 0, 0),
                        "user"));
        this.mockMvc.perform(post("/remove")
                .param("id", "1")
                .param("postAuthor", "user"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        final ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(this.repository).delete(argument.capture());
    }
}

