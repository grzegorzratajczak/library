package pl.grzex.library;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.ModelAndView;
import pl.grzex.library.services.LibraryService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LibraryApplicationTests {

	@Autowired
	MockMvc mockMvc;
	@Autowired
	LibraryService libraryService;

	@Before
	public void loadingBefore(){

	}

	/**
	 * Unit and integration Test
	 * */
	@Test
	public void basicTestShouldReturnHello() throws Exception {
		mockMvc.perform(get("/hello"))
				.andExpect(status().isOk())
				.andExpect(content().string("Hello"));
	}

	@Test
	public void shouldReturnViewTable() throws Exception {
		mockMvc.perform(get("/bookspage"))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("table"));
	}

	@Test
	public void shouldReturnModelOfTable() throws Exception {
		ModelAndView response = mockMvc.perform(get("/bookspage"))
				.andExpect(status().isOk())
				.andReturn().getModelAndView();
		Assert.assertEquals("["+ libraryService.findAllBooks()+"]",response.getModel().values().toString() );
		//THIS RESPONSE BELOW IS THE SAME -> problem with testing, to correct test use equals string
		//MockMvcResultMatchers.model().attribute("books",libraryService.findAllBooks())
		//.match( mockMvc.perform(get("/bookspage"))
		//.andExpect(status().isOk())
		// .andReturn());
	}

}
