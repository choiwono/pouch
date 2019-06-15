package my.examples.pouch.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import my.examples.pouch.controller.api.AccountController;
import my.examples.pouch.domain.Account;
import my.examples.pouch.dto.Joinform;
import my.examples.pouch.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {AccountController.class}, secure = false)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private AccountService accountService;

    @Test
    @WithMockUser(username="테스트1", authorities = {"ROLE_USER"})
    public void 이메일중복테스트() throws Exception{
        Account account = Account.builder()
                .id(10L)
                .email("test1@gmail.com")
                .nickName("테스트1")
                .passwd("12345")
                .build();

        Mockito.when(accountService.findAccountByEmail("test1@gmail.com"))
                .thenReturn(account);

        mockMvc.perform(post("/api/accounts/emailcheck")
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().is(226))
                .andDo(print());
    }

    @Test
    public void 회원가입테스트() throws Exception {
        Joinform joinUser = Joinform.builder()
                //.id(5L)
                .email("test1@gmail.com")
                .nickname("테스트1")
                .passwd("123456")
                .build();

        Account account = Account.builder()
                .email("test1@gmail.com")
                .nickName("테스트1")
                .passwd(joinUser.getPasswd())
                .build();
    }
}
