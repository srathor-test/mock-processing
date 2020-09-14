package app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MockTest {
	
	@Autowired
    private MockMvc mvc;

	@WithMockUser(value = "user", password = "password")
	@Test
	public void greetingShouldReturnDefaultMessage() throws Exception {		
		
		mvc.perform(get("/transaction/retrieve")
				.header("correlationId", "1234")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk());
			     
	}

}

