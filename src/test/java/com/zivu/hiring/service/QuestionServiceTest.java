package com.zivu.hiring.service;

import com.zivu.hiring.converter.QuestionToQuestionDataConverter;
import com.zivu.hiring.model.Level;
import com.zivu.hiring.model.QuestionData;
import com.zivu.hiring.persistance.Question;
import com.zivu.hiring.persistance.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static com.zivu.hiring.model.Level.*;
import static com.zivu.hiring.model.Technology.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * The class contains unit tests that ensure the correct work of QuestionService.
 */
class QuestionServiceTest {

    @Mock
    private QuestionToQuestionDataConverter converter;

    @Mock
    private QuestionRepository repository;

    @InjectMocks
    private QuestionService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @SuppressWarnings("all")
    @Test
    public void shouldThrowIAEWhenProvidedWithNullLevel() {
        //given
        Level nullLevel = null;
        int number = 0;
        //when-then
        assertThrows(IllegalArgumentException.class, () -> {
            service.findQuestions(nullLevel, number, false, false, false, false);
        });
    }

    @SuppressWarnings("all")
    @Test
    public void shouldThrowIAEWhenNoTechnologyChosen() {
        //given
        Level level = MIDDLE;
        boolean hasJava = false;
        boolean hasSpring = false;
        boolean hasSql = false;
        boolean hasJavaScript = false;
        int number = 0;
        //when-then
        assertThrows(IllegalArgumentException.class, () -> {
           service.findQuestions(level, number, hasJava, hasSpring, hasSql, hasJavaScript);
        });
    }

    @SuppressWarnings("all")
    @Test
    public void shouldReturnJuniorJavaQuestionsWhenJuniorJavaRequested() {
        //given
        Level level = JUNIOR;
        int number = 0;
        boolean hasJava = true;
        boolean hasSpring = false;
        boolean hasSql = false;
        boolean hasJavaScript = false;
        Question q = new Question(1L, "test java question", "test answer", JUNIOR, JAVA);
        QuestionData questionData = new QuestionData(q.getQuestion(), q.getAnswer(), q.getLevel(), q.getTechnology());
        when(converter.convert(q)).thenReturn(questionData);
        when(repository.findByLevelAndTechnologyIn(JUNIOR, List.of(JAVA))).thenReturn(List.of(q));
        //when
        List<QuestionData> questions = service.findQuestions(level, number, hasJava, hasSpring, hasSql, hasJavaScript);
        //then
        assertEquals(JUNIOR, questions.get(0).level());
        assertEquals(JAVA, questions.get(0).technology());
    }

    @SuppressWarnings("all")
    @Test
    public void shouldReturnSpringMiddleQuestionsWhenThoseAreRequested() {
        //given
        Level level = MIDDLE;
        int number = 0;
        boolean hasJava = false;
        boolean hasSpring = true;
        boolean hasSql = false;
        boolean hasJavaScript = false;
        Question q = new Question(1L, "test spring question", "test answer", MIDDLE, SPRING);
        QuestionData questionData = new QuestionData(q.getQuestion(), q.getAnswer(), q.getLevel(), q.getTechnology());
        when(repository.findByLevelAndTechnologyIn(MIDDLE, List.of(SPRING))).thenReturn(List.of(q));
        when(converter.convert(q)).thenReturn(questionData);
        //when
        List<QuestionData> questions = service.findQuestions(level, number, hasJava, hasSpring, hasSql, hasJavaScript);
        //then
        assertEquals(MIDDLE, questions.get(0).level());
        assertEquals(SPRING, questions.get(0).technology());
    }

    @SuppressWarnings("all")
    @Test
    public void shouldReturnSeniorSqlQuestionsWhenThoseAreRequested() {
        //given
        Level level = SENIOR;
        int number = 0;
        boolean hasJava = false;
        boolean hasSpring = false;
        boolean hasSql = true;
        boolean hasJavaScript = false;
        Question q = new Question(1L, "test sql question", "test answer", SENIOR, SQL);
        QuestionData questionData = new QuestionData(q.getQuestion(), q.getAnswer(), q.getLevel(), q.getTechnology());
        when(converter.convert(q)).thenReturn(questionData);
        when(repository.findByLevelAndTechnologyIn(SENIOR, List.of(SQL))).thenReturn(List.of(q));
        //when
        List<QuestionData> questions = service.findQuestions(level, number, hasJava, hasSpring, hasSql, hasJavaScript);
        //then
        assertEquals(SENIOR, questions.get(0).level());
        assertEquals(SQL, questions.get(0).technology());
    }

    @SuppressWarnings("all")
    @Test
    public void shouldReturnMiddleJavaScriptQuestionsWhenThoseAreRequested() {
        //given
        Level level = MIDDLE;
        int number = 0;
        boolean hasJava = false;
        boolean hasSpring = false;
        boolean hasSql = false;
        boolean hasJavaScript = true;
        Question q = new Question(1L, "test js question", "test answer", MIDDLE, JAVA_SCRIPT);
        when(repository.findByLevelAndTechnologyIn(MIDDLE, List.of(JAVA_SCRIPT))).thenReturn(List.of(q));
        QuestionData questionData = new QuestionData(q.getQuestion(), q.getAnswer(), q.getLevel(), q.getTechnology());
        when(converter.convert(q)).thenReturn(questionData);
        //when
        List<QuestionData> questions = service.findQuestions(level, number, hasJava, hasSpring, hasSql, hasJavaScript);
        //then
        assertEquals(MIDDLE, questions.get(0).level());
        assertEquals(JAVA_SCRIPT, questions.get(0).technology());
    }

    @SuppressWarnings("all")
    @Test
    public void shouldReturnJuniorJavaAndSpringQuestionsWhenThoseAreRequested() {
        //given
        Level level = JUNIOR;
        int number = 0;
        boolean hasJava = true;
        boolean hasSpring = true;
        boolean hasSql = false;
        boolean hasJavaScript = false;
        Question javaQuestion = new Question(1L, "test java question", "test answer", JUNIOR, JAVA);
        Question springQuestion = new Question(2L, "test spring question", "test answer", JUNIOR, SPRING);
        when(repository.findByLevelAndTechnologyIn(JUNIOR, List.of(JAVA, SPRING))).thenReturn(List.of(javaQuestion, springQuestion));
        QuestionData javaQuestionData = new QuestionData(javaQuestion.getQuestion(), javaQuestion.getAnswer(), javaQuestion.getLevel(), javaQuestion.getTechnology());
        when(converter.convert(javaQuestion)).thenReturn(javaQuestionData);
        QuestionData springQuestionData = new QuestionData(springQuestion.getQuestion(), springQuestion.getAnswer(), springQuestion.getLevel(), springQuestion.getTechnology());
        when(converter.convert(springQuestion)).thenReturn(springQuestionData);
        //when
        List<QuestionData> questions = service.findQuestions(level, number, hasJava, hasSpring, hasSql, hasJavaScript);
        //then
        assertTrue(questions.contains(javaQuestionData));
        assertTrue(questions.contains(springQuestionData));
    }

    @SuppressWarnings("all")
    @Test
    public void shouldReturnMiddleJavaAndSpringAndSqlQuestionsWhenThoseAreRequested() {
        //given
        Level level = MIDDLE;
        int number = 0;
        boolean hasJava = true;
        boolean hasSpring = true;
        boolean hasSql = true;
        boolean hasJavaScript = false;
        Question javaQuestion = new Question(1L, "test java question", "test answer", MIDDLE, JAVA);
        Question springQuestion = new Question(2L, "test spring question", "test answer", MIDDLE, SPRING);
        Question sqlQuestion = new Question(3L, "test sql question", "test answer", MIDDLE, SQL);
        when(repository.findByLevelAndTechnologyIn(MIDDLE, List.of(JAVA, SPRING, SQL))).thenReturn(List.of(javaQuestion, springQuestion, sqlQuestion));
        //when
        List<QuestionData> questions = service.findQuestions(level, number, hasJava, hasSpring, hasSql, hasJavaScript);
        //then
        assertTrue(questions.contains(javaQuestion));
        assertTrue(questions.contains(springQuestion));
        assertTrue(questions.contains(sqlQuestion));
    }

    @SuppressWarnings("all")
    @Test
    public void shouldReturnJavaAndSpringAndSqlAndJSQuestionsWhenRequested() {
        //given
        Level level = MIDDLE;
        int number = 0;
        boolean hasJava = true;
        boolean hasSpring = true;
        boolean hasSql = true;
        boolean hasJavaScript = true;
        Question javaQuestion = new Question(1L, "test java question", "test answer", MIDDLE, JAVA);
        Question springQuestion = new Question(2L, "test spring question", "test answer", MIDDLE, SPRING);
        Question sqlQuestion = new Question(3L, "test sql question", "test answer", MIDDLE, SQL);
        Question jsQuestion = new Question(4L, "test js question", "test answer", MIDDLE, JAVA_SCRIPT);
        when(repository.findByLevelAndTechnologyIn(MIDDLE, List.of(JAVA, SPRING, SQL, JAVA_SCRIPT))).thenReturn(List.of(javaQuestion, springQuestion, sqlQuestion, jsQuestion));
        //when
        List<QuestionData> questions = service.findQuestions(level, number, hasJava, hasSpring, hasSql, hasJavaScript);
        //then
        assertTrue(questions.contains(javaQuestion));
        assertTrue(questions.contains(springQuestion));
        assertTrue(questions.contains(sqlQuestion));
        assertTrue(questions.contains(jsQuestion));
    }

    @Test
    public void shouldReturnAllQuestionsAvailable() {
        //given
        Question javaTestQuestion = new Question(1L, "test question", "test answer", JUNIOR, JAVA);
        Question springTestQuestion = new Question(2L, "spring test question", "spring test answer", SENIOR, JAVA);
        QuestionData javaQuestionData = new QuestionData(javaTestQuestion.getQuestion(), javaTestQuestion.getAnswer(), javaTestQuestion.getLevel(), javaTestQuestion.getTechnology());
        QuestionData springQuestionData = new QuestionData(springTestQuestion.getQuestion(), springTestQuestion.getAnswer(), springTestQuestion.getLevel(), springTestQuestion.getTechnology());
        List<QuestionData> expectedResult = List.of(javaQuestionData, springQuestionData);
        when(repository.findAll()).thenReturn(List.of(javaTestQuestion, springTestQuestion));
        when(converter.convert(javaTestQuestion)).thenReturn(javaQuestionData);
        when(converter.convert(springTestQuestion)).thenReturn(springQuestionData);
        //when
        List<QuestionData> allQuestions = service.findAllQuestions();
        //then
        verify(repository).findAll();
        assertEquals(2, allQuestions.size());
        assertEquals(expectedResult, allQuestions);
    }

}