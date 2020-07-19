package forum.control;

import forum.Main;
import forum.model.Message;
import forum.model.Post;
import forum.repository.MessageRepository;
import forum.repository.PostRepository;
import forum.service.message.MessageService;
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
public class MessagePostControllerTest {
    @MockBean
    private PostRepository posts;
    @MockBean
    private MessageRepository messages;
    @Autowired
    private PostUserService service;
    @Autowired
    private MessageService serviceMsg;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void whenCreateMessageIsOk() throws Exception {
        given(this.service.findByNameAndId("user", 1)).willReturn(
                new Post(1, "A", "Куплю А ради А",
                        LocalDateTime.of(2020, 7, 13, 13, 9),
                        "user"));
        final LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("id", "1");
        requestParams.add("message", "хочу купить А");
        requestParams.add("authorPost", "user");
        this.mockMvc.perform(post("/post/message/create")
                .params(requestParams))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        final ArgumentCaptor<Message> argument = ArgumentCaptor.forClass(Message.class);
        verify(this.messages).save(argument.capture());
        final String actual = argument.getValue().getDescription();
        assertSame("хочу купить А", actual);
    }

    @Test
    @WithMockUser
    public void whenUpdateMessageOk() throws Exception {
        final Post post = new Post(1, "A", "Куплю А ради А",
                LocalDateTime.of(2020, 7, 13, 13, 9),
                "user");
        final Message msg = new Message(1, "Куплю UPDATE ради А",
                LocalDateTime.of(2020, 7, 13, 13, 9),
                "user");
        given(this.service.findByNameAndId("user", 1)).willReturn(post);
        given(this.serviceMsg.findByPostAndMsgId(post, 1)).willReturn(msg);
        final LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("idMsgUpdate", "1");
        requestParams.add("idMsgPostUpdate", "1");
        requestParams.add("authorPost", "user");
        requestParams.add("authorMsg", "user");
        requestParams.add("msgUpdate", "UPDATE");
        this.mockMvc.perform(post("/post/message/update")
                .params(requestParams))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        final ArgumentCaptor<Message> argument = ArgumentCaptor.forClass(Message.class);
        verify(this.messages).save(argument.capture());
        final String actual = argument.getValue().getDescription();
        assertSame("UPDATE", actual);
    }

    @Test
    @WithMockUser
    public void whenRemoveMessageOk() throws Exception {
        final Message msg = new Message(1, "DELETE",
                LocalDateTime.of(2020, 7, 13, 13, 9),
                "user");
        final Post post = new Post(1, "A", "Куплю А ради А",
                LocalDateTime.of(2020, 7, 13, 13, 9),
                "user");
        given(this.service.findByNameAndId("user", 1)).willReturn(post);
        given(this.serviceMsg.findByPostAndMsgId(post, 1)).willReturn(msg);
        final LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("idMsgD", "1");
        requestParams.add("idPostD", "1");
        requestParams.add("namesD", "user");
        requestParams.add("authorPostD", "user");
        this.mockMvc.perform(post("/post/message/delete")
                .params(requestParams))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        final ArgumentCaptor<Post> argPost = ArgumentCaptor.forClass(Post.class);
        final ArgumentCaptor<Message> argMsg = ArgumentCaptor.forClass(Message.class);
        verify(this.messages).deleteByPostAndMsg(argPost.capture(), argMsg.capture());
        final String actual = argMsg.getValue().getDescription();
        assertSame("DELETE", actual);
    }
}


