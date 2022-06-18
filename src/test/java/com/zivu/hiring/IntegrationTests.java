package com.zivu.hiring;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Integration tests that ensure the correct work of REST API of the app.
 */
@SpringBootTest
@ExtendWith({SpringExtension.class})
class IntegrationTests {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@BeforeEach
	public void setUp() {
		this.mockMvc = webAppContextSetup(this.wac).build();
	}

	@SneakyThrows
	@Test
	public void shouldReturnJuniorJavaQuestionsWhenJavaParameterIsProvided() {
		this.mockMvc.perform(get("/questions").param("has_java", "true"))
				.andExpect(jsonPath("$[*].level", everyItem(is("JUNIOR"))))
				.andExpect(jsonPath("$[*].technology", everyItem(is("JAVA"))))
				.andExpect(jsonPath("$[*].question", everyItem(notNullValue())))
				.andExpect(jsonPath("$[*].answer", everyItem(notNullValue())));
	}

	@SneakyThrows
	@Test
	public void shouldReturnJuniorSpringQuestionsWhenSpringParamIsProvided() {
		this.mockMvc.perform(get("/questions").param("has_spring", "true"))
				.andExpect(jsonPath("$[*].level", everyItem(is("JUNIOR"))))
				.andExpect(jsonPath("$[*].technology", everyItem(is("SPRING"))))
				.andExpect(jsonPath("$[*].question", everyItem(notNullValue())))
				.andExpect(jsonPath("$[*].answer", everyItem(notNullValue())));
	}

	@SneakyThrows
	@Test
	public void shouldReturnJuniorSqlQuestionsWhenSqlParameterIsProvided() {
		this.mockMvc.perform(get("/questions").param("has_sql", "true"))
				.andExpect(jsonPath("$[*].level", everyItem(is("JUNIOR"))))
				.andExpect(jsonPath("$[*].technology", everyItem(is("SQL"))))
				.andExpect(jsonPath("$[*].question", everyItem(notNullValue())))
				.andExpect(jsonPath("$[*].answer", everyItem(notNullValue())));
	}

	@SneakyThrows
	@Test
	public void shouldReturnJuniorJavaScriptQuestionsWhenJSParameterIsProvided() {
		this.mockMvc.perform(get("/questions").param("has_js", "true"))
				.andExpect(jsonPath("$[*].level", everyItem(is("JUNIOR"))))
				.andExpect(jsonPath("$[*].technology", everyItem(is("JAVA_SCRIPT"))))
				.andExpect(jsonPath("$[*].question", everyItem(notNullValue())))
				.andExpect(jsonPath("$[*].answer", everyItem(notNullValue())));
	}

	@SneakyThrows
	@Test
	public void shouldReturnJuniorJavaAndSpringQuestionsWhenThoseTechnologiesRequested() {
		this.mockMvc.perform(get("/questions").param("has_java", "true")
				.param("has_spring", "true")
				.param("level", "junior"))
				.andExpect(jsonPath("$[*].level", everyItem(is("JUNIOR"))))
				.andExpect(jsonPath("$[*].technology", hasItems("JAVA", "SPRING")))
				.andExpect(jsonPath("$[*].question", everyItem(notNullValue())))
				.andExpect(jsonPath("$[*].answer", everyItem(notNullValue())));
	}

	@SneakyThrows
	@Test
	public void shouldReturnJuniorJavaAndSpringAndSqlQuestionsWhenThoseTechnologiesRequested() {
		this.mockMvc.perform(get("/questions").param("has_java", "true")
						.param("has_spring", "true")
						.param("has_sql", "true")
						.param("level", "junior"))
				.andExpect(jsonPath("$[*].level", everyItem(is("JUNIOR"))))
				.andExpect(jsonPath("$[*].technology", hasItems("JAVA", "SPRING", "SQL")))
				.andExpect(jsonPath("$[*].question", everyItem(notNullValue())))
				.andExpect(jsonPath("$[*].answer", everyItem(notNullValue())));
	}

	@SneakyThrows
	@Test
	public void shouldReturnJuniorJavaAndSpringAndSqlAndJavaScriptQuestionsWhenRequested() {
		this.mockMvc.perform(get("/questions").param("has_java", "true")
						.param("has_spring", "true")
						.param("has_js", "true")
						.param("has_sql", "true")
						.param("level", "junior"))
				.andExpect(jsonPath("$[*].level", everyItem(is("JUNIOR"))))
				.andExpect(jsonPath("$[*].technology", hasItems("JAVA", "SPRING", "SQL", "JAVA_SCRIPT")))
				.andExpect(jsonPath("$[*].question", everyItem(notNullValue())))
				.andExpect(jsonPath("$[*].answer", everyItem(notNullValue())));
	}

}
