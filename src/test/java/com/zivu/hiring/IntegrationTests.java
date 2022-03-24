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
	@Test
	public void shouldReturnJuniorJavaQuestionsWhenNoParams() {
		this.mockMvc.perform(get("/questions"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[*].level", everyItem(is("JUNIOR"))))
				.andExpect(jsonPath("$[*].technology", everyItem(is("JAVA"))))
				.andExpect(jsonPath("$[*].question", everyItem(notNullValue())))
				.andExpect(jsonPath("$[*].answer", everyItem(notNullValue())));
	}

	@SneakyThrows
	@Test
	public void shouldReturnJuniorQuestionsWhenLevelIsJunior() {
		this.mockMvc.perform(get("/questions").param("level", "JUNIOR"))
				.andExpect(jsonPath("$[*].level", everyItem(is("JUNIOR"))))
				.andExpect(jsonPath("$[*].technology", everyItem(is("JAVA"))))
				.andExpect(jsonPath("$[*].question", everyItem(notNullValue())))
				.andExpect(jsonPath("$[*].answer", everyItem(notNullValue())));
	}

	@SneakyThrows
	@Test
	public void shouldReturnMiddleJavaQuestionsWhenLevelIsMiddle() {
		this.mockMvc.perform(get("/questions").param("level", "MIDDLE"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[*].level", everyItem(is("MIDDLE"))))
				.andExpect(jsonPath("$[*].technology", everyItem(is("JAVA"))))
				.andExpect(jsonPath("$[*].question", everyItem(notNullValue())))
				.andExpect(jsonPath("$[*].answer", everyItem(notNullValue())));
	}

	@SneakyThrows
	@Test
	public void shouldReturnSeniorQuestionsWhenLevelIsSenior() {
		this.mockMvc.perform(get("/questions").param("level", "SENIOR"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[*].level", everyItem(is("SENIOR"))))
				.andExpect(jsonPath("$[*].technology", everyItem(is("JAVA"))))
				.andExpect(jsonPath("$[*].question", everyItem(notNullValue())))
				.andExpect(jsonPath("$[*].answer", everyItem(notNullValue())));
	}

	@SneakyThrows
	@Test
	public void shouldReturnJuniorJavaQuestionsWhenJavaParameterIsProvided() {
		this.mockMvc.perform(get("/questions").param("hasJava", "true"))
				.andExpect(jsonPath("$[*].level", everyItem(is("JUNIOR"))))
				.andExpect(jsonPath("$[*].technology", everyItem(is("JAVA"))))
				.andExpect(jsonPath("$[*].question", everyItem(notNullValue())))
				.andExpect(jsonPath("$[*].answer", everyItem(notNullValue())));
	}

	@SneakyThrows
	@Test
	public void shouldReturnJuniorSpringQuestionsWhenSpringParamIsProvided() {
		this.mockMvc.perform(get("/questions").param("hasSpring", "true"))
				.andExpect(jsonPath("$[*].level", everyItem(is("JUNIOR"))))
				.andExpect(jsonPath("$[*].technology", everyItem(is("SPRING"))))
				.andExpect(jsonPath("$[*].question", everyItem(notNullValue())))
				.andExpect(jsonPath("$[*].answer", everyItem(notNullValue())));
	}

	@SneakyThrows
	@Test
	public void shouldReturnJuniorSqlQuestionsWhenSqlParameterIsProvided() {
		this.mockMvc.perform(get("/questions").param("hasSql", "true"))
				.andExpect(jsonPath("$[*].level", everyItem(is("JUNIOR"))))
				.andExpect(jsonPath("$[*].technology", everyItem(is("SQL"))))
				.andExpect(jsonPath("$[*].question", everyItem(notNullValue())))
				.andExpect(jsonPath("$[*].answer", everyItem(notNullValue())));
	}

	@SneakyThrows
	@Test
	public void shouldReturnJuniorJavaScriptQuestionsWhenJSParameterIsProvided() {
		this.mockMvc.perform(get("/questions").param("hasJavaScript", "true"))
				.andExpect(jsonPath("$[*].level", everyItem(is("JUNIOR"))))
				.andExpect(jsonPath("$[*].technology", everyItem(is("JAVA_SCRIPT"))))
				.andExpect(jsonPath("$[*].question", everyItem(notNullValue())))
				.andExpect(jsonPath("$[*].answer", everyItem(notNullValue())));
	}

	@SneakyThrows
	@Test
	public void shouldNotReturnJuniorJavaQuestionsWhenJavaParameterIsFalse() {
		this.mockMvc.perform(get("/questions").param("hasJava", "false"))
				.andExpect(jsonPath("$[*].level", everyItem(empty())))
				.andExpect(jsonPath("$[*].technology", everyItem(empty())))
				.andExpect(jsonPath("$[*].question", everyItem(empty())))
				.andExpect(jsonPath("$[*].answer", everyItem(empty())));
	}

	@SneakyThrows
	@Test
	public void shouldNotReturnJuniorSpringQuestionsWhenSpringParamIsFalse() {
		this.mockMvc.perform(get("/questions").param("hasSpring", "false")
						.param("hasJava", "false"))
				.andExpect(jsonPath("$[*].level", everyItem(empty())))
				.andExpect(jsonPath("$[*].technology", everyItem(empty())))
				.andExpect(jsonPath("$[*].question", everyItem(empty())))
				.andExpect(jsonPath("$[*].answer", everyItem(empty())));
	}

	@SneakyThrows
	@Test
	public void shouldNOTReturnJuniorSqlQuestionsWhenSqlParameterIsFalse() {
		this.mockMvc.perform(get("/questions").param("hasSql", "false")
						.param("hasJava", "false"))
				.andExpect(jsonPath("$[*].level", everyItem(empty())))
				.andExpect(jsonPath("$[*].technology", everyItem(empty())))
				.andExpect(jsonPath("$[*].question", everyItem(empty())))
				.andExpect(jsonPath("$[*].answer", everyItem(empty())));
	}

	@SneakyThrows
	@Test
	public void shouldNotReturnJuniorJavaScriptQuestionsWhenJSParameterIsFalse() {
		this.mockMvc.perform(get("/questions").param("hasJavaScript", "false")
						.param("hasJava", "false"))
				.andExpect(jsonPath("$[*].level", everyItem(empty())))
				.andExpect(jsonPath("$[*].technology", everyItem(empty())))
				.andExpect(jsonPath("$[*].question", everyItem(empty())))
				.andExpect(jsonPath("$[*].answer", everyItem(empty())));
	}

	@SneakyThrows
	@Test
	public void shouldReturnJuniorJavaAndSpringQuestionsWhenThoseTechnologiesRequested() {
		this.mockMvc.perform(get("/questions").param("hasJava", "true")
				.param("hasSpring", "true"))
				.andExpect(jsonPath("$[*].level", everyItem(is("JUNIOR"))))
				.andExpect(jsonPath("$[*].technology", containsInAnyOrder("JAVA", "SPRING")))
				.andExpect(jsonPath("$[*].question", everyItem(notNullValue())))
				.andExpect(jsonPath("$[*].answer", everyItem(notNullValue())));
	}

	@SneakyThrows
	@Test
	public void shouldReturnJuniorJavaAndSpringAndSqlQuestionsWhenThoseTechnologiesRequested() {
		this.mockMvc.perform(get("/questions").param("hasJava", "true")
						.param("hasSpring", "true")
						.param("hasSql", "true"))
				.andExpect(jsonPath("$[*].level", everyItem(is("JUNIOR"))))
				.andExpect(jsonPath("$[*].technology", containsInAnyOrder("JAVA", "SPRING", "SQL")))
				.andExpect(jsonPath("$[*].question", everyItem(notNullValue())))
				.andExpect(jsonPath("$[*].answer", everyItem(notNullValue())));
	}

	@SneakyThrows
	@Test
	public void shouldReturnJuniorJavaAndSpringAndSqlAndJavaScriptQuestionsWhenRequested() {
		this.mockMvc.perform(get("/questions").param("hasJava", "true")
						.param("hasSpring", "true")
						.param("hasJavaScript", "true")
						.param("hasSql", "true"))
				.andExpect(jsonPath("$[*].level", everyItem(is("JUNIOR"))))
				.andExpect(jsonPath("$[*].technology", containsInAnyOrder("JAVA", "SPRING", "SQL", "JAVA_SCRIPT")))
				.andExpect(jsonPath("$[*].question", everyItem(notNullValue())))
				.andExpect(jsonPath("$[*].answer", everyItem(notNullValue())));
	}

}
