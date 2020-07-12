package forum.control;

import forum.Main;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * IndexControlTest.
 *
 * @author Maxim Vanny
 * @version 5.0
 * @since 7/3/2020
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class IndexControlTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/forum"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("forum"));
    }

    @Test
    @WithMockUser
    public void shouldReturnCabinetUser() throws Exception {
        this.mockMvc.perform(get("/cabinet/user?name=user"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("cabinet"));
    }

    @Test
    @WithMockUser
    public void shouldReturnCabinetAdmin() throws Exception {
        this.mockMvc.perform(get("/cabinet/admin?name=admin"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("cabinet"));
    }

    @Test
    @WithMockUser
    public void shouldReturnPostAndActionShows() throws Exception {
        this.mockMvc.perform(get("/post?action=shows&id=1&name=user"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post"));
    }

    @Test
    @WithMockUser
    public void shouldReturnPostAndActionCreate() throws Exception {
        this.mockMvc.perform(get("/post?action=create&name=user"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("edit"));
    }

    @Test
    @WithMockUser
    public void shouldReturnPostAndActionUpdate() throws Exception {
        this.mockMvc.perform(get("/post?action=update&id=1&name=user"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("edit"));
    }

}
