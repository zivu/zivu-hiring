package com.zivu.hiring;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

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
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@SneakyThrows
	@Test
	public void shouldReturnJuniorJavaQuestionsWhenNoParams() {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/questions"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$[*].level", everyItem(is("JUNIOR"))))
				.andExpect(jsonPath("$[*].technology", everyItem(is("JAVA"))));
	}

	@SneakyThrows
	@Test
	public void shouldReturnJuniorQuestionsWhenLevelIsJunior() {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/questions").param("level", "JUNIOR"))
				.andExpect(jsonPath("$[*].level", everyItem(is("JUNIOR"))))
				.andExpect(jsonPath("$[*].technology", everyItem(is("JAVA"))));
	}

	@SneakyThrows
	@Test
	public void shouldReturnMiddleJavaQuestionsWhenLevelIsMiddle() {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/questions").param("level", "MIDDLE"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$[*].level", everyItem(is("MIDDLE"))))
				.andExpect(jsonPath("$[*].technology", everyItem(is("JAVA"))));
	}

	@SneakyThrows
	@Test
	public void shouldReturnSeniorQuestionsWhenLevelIsSenior() {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/questions").param("level", "SENIOR"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$[*].level", everyItem(is("SENIOR"))))
				.andExpect(jsonPath("$[*].technology", everyItem(is("JAVA"))));
	}

	@SneakyThrows
	@Test
	public void shouldReturnJuniorJavaQuestionsWhenJavaParameterIsProvided() {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/questions").param("hasJava", "true"))
				.andExpect(jsonPath("$[*].level", everyItem(is("JUNIOR"))))
				.andExpect(jsonPath("$[*].technology", everyItem(is("JAVA"))));
	}

	@SneakyThrows
	@Test
	public void shouldReturnJuniorSpringQuestionsWhenSpringParamIsProvided() {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/questions").param("hasSpring", "true"))
				.andExpect(jsonPath("$[*].level", everyItem(is("JUNIOR"))))
				.andExpect(jsonPath("$[*].technology", everyItem(is("SPRING"))));
	}

	@SneakyThrows
	@Test
	public void shouldReturnJuniorSqlQuestionsWhenSqlParameterIsProvided() {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/questions").param("hasSql", "true"))
				.andExpect(jsonPath("$[*].level", everyItem(is("JUNIOR"))))
				.andExpect(jsonPath("$[*].technology", everyItem(is("SQL"))));
	}

	@SneakyThrows
	@Test
	public void shouldReturnJuniorJavaScriptQuestionsWhenJSParameterIsProvided() {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/questions").param("hasJavaScript", "true"))
				.andExpect(jsonPath("$[*].level", everyItem(is("JUNIOR"))))
				.andExpect(jsonPath("$[*].technology", everyItem(is("JAVA_SCRIPT"))));
	}

	@SneakyThrows
	@Test
	public void shouldNotReturnJuniorJavaQuestionsWhenJavaParameterIsFalse() {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/questions").param("hasJava", "false"))
				.andExpect(jsonPath("$[*].level", everyItem(empty())))
				.andExpect(jsonPath("$[*].technology", everyItem(empty())));
	}

	@SneakyThrows
	@Test
	public void shouldNotReturnJuniorSpringQuestionsWhenSpringParamIsFalse() {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/questions").param("hasSpring", "false"))
				.andExpect(jsonPath("$[*].level", everyItem(empty())))
				.andExpect(jsonPath("$[*].technology", everyItem(empty())));
	}

	@SneakyThrows
	@Test
	public void shouldNOTReturnJuniorSqlQuestionsWhenSqlParameterIsFalse() {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/questions").param("hasSql", "false"))
				.andExpect(jsonPath("$[*].level", everyItem(empty())))
				.andExpect(jsonPath("$[*].technology", everyItem(empty())));
	}

	@SneakyThrows
	@Test
	public void shouldNotReturnJuniorJavaScriptQuestionsWhenJSParameterIsFalse() {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/questions").param("hasJavaScript", "false"))
				.andExpect(jsonPath("$[*].level", everyItem(empty())))
				.andExpect(jsonPath("$[*].technology", everyItem(empty())));
	}

	@SneakyThrows
	@Test
	public void shouldReturnJuniorJavaAndSpringQuestionsWhenThoseTechnologiesRequested() {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/questions").param("hasJava", "true")
				.param("hasSpring", "true"))
				.andExpect(jsonPath("$[*].level", everyItem(is("JUNIOR"))))
				.andExpect(jsonPath("$[*].technology", containsInAnyOrder("JAVA", "SPRING")));
	}

	@SneakyThrows
	@Test
	public void shouldReturnJuniorJavaAndSpringAndSqlQuestionsWhenThoseTechnologiesRequested() {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/questions").param("hasJava", "true")
						.param("hasSpring", "true")
						.param("hasSql", "true"))
				.andExpect(jsonPath("$[*].level", everyItem(is("JUNIOR"))))
				.andExpect(jsonPath("$[*].technology", containsInAnyOrder("JAVA", "SPRING", "SQL")));
	}

	@SneakyThrows
	@Test
	public void shouldReturnJuniorJavaAndSpringAndSqlAndJavaScriptQuestionsWhenRequested() {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/questions").param("hasJava", "true")
						.param("hasSpring", "true")
						.param("hasJavaScript", "true")
						.param("hasSql", "true"))
				.andExpect(jsonPath("$[*].level", everyItem(is("JUNIOR"))))
				.andExpect(jsonPath("$[*].technology", containsInAnyOrder("JAVA", "SPRING", "SQL", "JAVA_SCRIPT")));
	}

}
