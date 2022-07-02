package com.zivu.hiring.resource;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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
	@ParameterizedTest
	@ValueSource(strings = {"JUNIOR", "MIDDLE", "SENIOR"})
	public void shouldReturnJavaQuestionsWhenJavaParameterAndLevelProvided(String level) {
		this.mockMvc.perform(get("/questions/selected").param("has_java", "true"))
				.andExpect(jsonPath("$[*].level", everyItem(is(level))))
				.andExpect(jsonPath("$[*].technology", everyItem(is("JAVA"))))
				.andExpect(jsonPath("$[*].question", everyItem(notNullValue())))
				.andExpect(jsonPath("$[*].answer", everyItem(notNullValue())));
	}

	@SneakyThrows
	@ParameterizedTest
	@ValueSource(strings = {"JUNIOR", "MIDDLE", "SENIOR"})
	public void shouldReturnSpringQuestionsWhenSpringParamAndLevelProvided(String level) {
		this.mockMvc.perform(get("/questions/selected").param("has_spring", "true"))
				.andExpect(jsonPath("$[*].level", everyItem(is(level))))
				.andExpect(jsonPath("$[*].technology", everyItem(is("SPRING"))))
				.andExpect(jsonPath("$[*].question", everyItem(notNullValue())))
				.andExpect(jsonPath("$[*].answer", everyItem(notNullValue())));
	}

	@SneakyThrows
	@ParameterizedTest
	@ValueSource(strings = {"JUNIOR", "MIDDLE", "SENIOR"})
	public void shouldReturnSqlQuestionsWhenSqlParameterAndLevelProvided(String level) {
		this.mockMvc.perform(get("/questions/selected").param("has_sql", "true"))
				.andExpect(jsonPath("$[*].level", everyItem(is(level))))
				.andExpect(jsonPath("$[*].technology", everyItem(is("SQL"))))
				.andExpect(jsonPath("$[*].question", everyItem(notNullValue())))
				.andExpect(jsonPath("$[*].answer", everyItem(notNullValue())));
	}

	@SneakyThrows
	@ParameterizedTest
	@ValueSource(strings = {"JUNIOR", "MIDDLE", "SENIOR"})
	public void shouldReturnJavaScriptQuestionsWhenJSParameterAndLevelProvided(String level) {
		this.mockMvc.perform(get("/questions/selected").param("has_js", "true"))
				.andExpect(jsonPath("$[*].level", everyItem(is(level))))
				.andExpect(jsonPath("$[*].technology", everyItem(is("JAVA_SCRIPT"))))
				.andExpect(jsonPath("$[*].question", everyItem(notNullValue())))
				.andExpect(jsonPath("$[*].answer", everyItem(notNullValue())));
	}

	@SneakyThrows
	@ParameterizedTest
	@ValueSource(strings = {"JUNIOR", "MIDDLE", "SENIOR"})
	public void shouldReturnJavaAndSpringQuestionsWhenThoseTechnologiesRequested(String level) {
		this.mockMvc.perform(get("/questions/selected").param("has_java", "true")
				.param("has_spring", "true")
				.param("level", level))
				.andExpect(jsonPath("$[*].level", everyItem(is(level))))
				.andExpect(jsonPath("$[*].technology", hasItems("JAVA", "SPRING")))
				.andExpect(jsonPath("$[*].question", everyItem(notNullValue())))
				.andExpect(jsonPath("$[*].answer", everyItem(notNullValue())));
	}

	@SneakyThrows
	@ParameterizedTest
	@ValueSource(strings = {"JUNIOR", "MIDDLE", "SENIOR"})
	public void shouldReturnJavaAndSpringAndSqlQuestionsWhenThoseTechnologiesRequested(String level) {
		this.mockMvc.perform(get("/questions/selected").param("has_java", "true")
						.param("has_spring", "true")
						.param("has_sql", "true")
						.param("level", level))
				.andExpect(jsonPath("$[*].level", everyItem(is(level))))
				.andExpect(jsonPath("$[*].technology", hasItems("JAVA", "SPRING", "SQL")))
				.andExpect(jsonPath("$[*].question", everyItem(notNullValue())))
				.andExpect(jsonPath("$[*].answer", everyItem(notNullValue())));
	}

	@SneakyThrows
	@ParameterizedTest
	@ValueSource(strings = {"JUNIOR", "MIDDLE", "SENIOR"})
	public void shouldReturnJavaAndSpringAndSqlAndJavaScriptQuestionsWhenRequested(String level) {
		this.mockMvc.perform(get("/questions/selected").param("has_java", "true")
						.param("has_spring", "true")
						.param("has_js", "true")
						.param("has_sql", "true")
						.param("level", level))
				.andExpect(jsonPath("$[*].level", everyItem(is(level))))
				.andExpect(jsonPath("$[*].technology", hasItems("JAVA", "SPRING", "SQL", "JAVA_SCRIPT")))
				.andExpect(jsonPath("$[*].question", everyItem(notNullValue())))
				.andExpect(jsonPath("$[*].answer", everyItem(notNullValue())));
	}

	@SneakyThrows
	@Test
	public void shouldReturnAllQuestions() {
		this.mockMvc.perform(get("/questions/all"))
				.andExpect(status().is2xxSuccessful())
				.andExpect(jsonPath("$[*].level", hasItems("JUNIOR", "MIDDLE", "SENIOR")))
				.andExpect(jsonPath("$[*].technology", hasItems("JAVA", "SPRING", "SQL", "JAVA_SCRIPT")))
				.andExpect(jsonPath("$[*].answer", everyItem(notNullValue())))
				.andExpect(jsonPath("$[*].question", everyItem(notNullValue())));
	}

}
