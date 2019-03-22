package com.poi.apirest.endpoint;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poi.apirest.ApiRestApplication;
import com.poi.apirest.models.PointOfInterest;
import com.poi.apirest.repository.PointOfInterestRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = ApiRestApplication.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
public class PointOfInterestApiTests {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mvc;

	@Autowired
	private PointOfInterestRepository poiRepository;

	@Before
	public void run() {
		List<PointOfInterest> listOfAllPOIs = poiRepository.findAll();
		poiRepository.deleteAll(listOfAllPOIs);

		List<PointOfInterest> listOfPOIs = Arrays.asList(new PointOfInterest(27, 12, "Lanchonete"),
				new PointOfInterest(31, 18, "Posto"), new PointOfInterest(15, 12, "Joalheria"),
				new PointOfInterest(19, 21, "Floricultura"), new PointOfInterest(23, 6, "Supermercado"),
				new PointOfInterest(12, 8, "Pub"), new PointOfInterest(28, 2, "Churrascaria"));

		poiRepository.saveAll(listOfPOIs);
	}

	@Test
	public void testGetAllPoiShouldReturnOkAnAsExpected() throws Exception {
		String apiUrl = "/api/poi";
		System.out.println(this.mvc.perform(get(apiUrl)));
		this.mvc.perform(get(apiUrl)).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void testGetAllNearbyPoiShouldReturnAsExpected() throws Exception {
		String apiUrl = "/api/poi-nearby?x=20&y=10&d=10";
		System.out.println(this.mvc.perform(get(apiUrl)));
		this.mvc.perform(get(apiUrl)).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void testCreateNewPoiShouldReturn201() throws Exception {
		String apiUrl = "/api/poi";

		PointOfInterest poi = new PointOfInterest(10, 10, "UNESP");
		String objectAsJSON = objectMapper.writeValueAsString(poi);

		this.mvc.perform(post(apiUrl).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(objectAsJSON)).andExpect(status().isCreated()).andExpect(jsonPath("$.name", is("UNESP")))
				.andExpect(jsonPath("$.x", is(10))).andExpect(jsonPath("$.y", is(10)));
	}
}
