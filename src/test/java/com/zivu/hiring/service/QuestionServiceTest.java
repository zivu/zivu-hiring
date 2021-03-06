package com.zivu.hiring.service;

import com.zivu.hiring.converter.QuestionToQuestionDataConverter;
import com.zivu.hiring.model.Level;
import com.zivu.hiring.model.QuestionData;
import com.zivu.hiring.persistance.Question;
import com.zivu.hiring.persistance.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static com.zivu.hiring.model.Level.*;
import static com.zivu.hiring.model.Technology.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * The class contains unit tests that ensure the correct work of
 * @see QuestionService
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
        //when-then
        assertThrows(IllegalArgumentException.class, () -> {
            service.findQuestions(nullLevel,false, false, false, false);
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
        //when-then
        assertThrows(IllegalArgumentException.class, () -> {
           service.findQuestions(level, hasJava, hasSpring, hasSql, hasJavaScript);
        });
    }

    @SuppressWarnings("all")
    @ParameterizedTest
    @EnumSource(Level.class)
    public void shouldReturnJavaQuestionsForRequestedLevel(Level level) {
        //given
        boolean hasJava = true;
        boolean hasSpring = false;
        boolean hasSql = false;
        boolean hasJavaScript = false;
        Question q = new Question(1L, "test java question", "test answer", level, JAVA);
        QuestionData questionData = new QuestionData(q.getQuestion(), q.getAnswer(), q.getLevel(), q.getTechnology());
        when(converter.convert(q)).thenReturn(questionData);
        when(repository.findByLevelAndTechnologyIn(level, List.of(JAVA))).thenReturn(List.of(q));
        //when
        List<QuestionData> questions = service.findQuestions(level, hasJava, hasSpring, hasSql, hasJavaScript);
        //then
        assertEquals(level, questions.get(0).level());
        assertEquals(JAVA, questions.get(0).technology());
    }

    @SuppressWarnings("all")
    @ParameterizedTest
    @EnumSource(Level.class)
    public void shouldReturnSpringQuestionsForRequestedLevel(Level level) {
        //given
        boolean hasJava = false;
        boolean hasSpring = true;
        boolean hasSql = false;
        boolean hasJavaScript = false;
        Question q = new Question(1L, "test spring question", "test answer", level, SPRING);
        QuestionData questionData = new QuestionData(q.getQuestion(), q.getAnswer(), q.getLevel(), q.getTechnology());
        when(repository.findByLevelAndTechnologyIn(level, List.of(SPRING))).thenReturn(List.of(q));
        when(converter.convert(q)).thenReturn(questionData);
        //when
        List<QuestionData> questions = service.findQuestions(level, hasJava, hasSpring, hasSql, hasJavaScript);
        //then
        assertEquals(level, questions.get(0).level());
        assertEquals(SPRING, questions.get(0).technology());
    }

    @SuppressWarnings("all")
    @ParameterizedTest
    @EnumSource(Level.class)
    public void shouldReturnSqlQuestionsForRequestedLevel(Level level) {
        //given
        boolean hasJava = false;
        boolean hasSpring = false;
        boolean hasSql = true;
        boolean hasJavaScript = false;
        Question q = new Question(1L, "test sql question", "test answer", level, SQL);
        QuestionData questionData = new QuestionData(q.getQuestion(), q.getAnswer(), q.getLevel(), q.getTechnology());
        when(converter.convert(q)).thenReturn(questionData);
        when(repository.findByLevelAndTechnologyIn(level, List.of(SQL))).thenReturn(List.of(q));
        //when
        List<QuestionData> questions = service.findQuestions(level, hasJava, hasSpring, hasSql, hasJavaScript);
        //then
        assertEquals(level, questions.get(0).level());
        assertEquals(SQL, questions.get(0).technology());
    }

    @SuppressWarnings("all")
    @ParameterizedTest
    @EnumSource(Level.class)
    public void shouldReturnJavaScriptQuestionsForRequestedLevel(Level level) {
        //given
        boolean hasJava = false;
        boolean hasSpring = false;
        boolean hasSql = false;
        boolean hasJavaScript = true;
        Question q = new Question(1L, "test js question", "test answer", level, JAVA_SCRIPT);
        when(repository.findByLevelAndTechnologyIn(level, List.of(JAVA_SCRIPT))).thenReturn(List.of(q));
        QuestionData questionData = new QuestionData(q.getQuestion(), q.getAnswer(), q.getLevel(), q.getTechnology());
        when(converter.convert(q)).thenReturn(questionData);
        //when
        List<QuestionData> questions = service.findQuestions(level, hasJava, hasSpring, hasSql, hasJavaScript);
        //then
        assertEquals(level, questions.get(0).level());
        assertEquals(JAVA_SCRIPT, questions.get(0).technology());
    }

    @SuppressWarnings("all")
    @ParameterizedTest
    @EnumSource(Level.class)
    public void shouldReturnJavaAndSpringQuestionsWhenThoseAreRequested(Level level) {
        //given
        boolean hasJava = true;
        boolean hasSpring = true;
        boolean hasSql = false;
        boolean hasJavaScript = false;
        Question javaQuestion = new Question(1L, "test java question", "test answer", level, JAVA);
        Question springQuestion = new Question(2L, "test spring question", "test answer", level, SPRING);
        when(repository.findByLevelAndTechnologyIn(level, List.of(JAVA, SPRING))).thenReturn(List.of(javaQuestion, springQuestion));
        QuestionData javaQuestionData = new QuestionData(javaQuestion.getQuestion(), javaQuestion.getAnswer(), javaQuestion.getLevel(), javaQuestion.getTechnology());
        when(converter.convert(javaQuestion)).thenReturn(javaQuestionData);
        QuestionData springQuestionData = new QuestionData(springQuestion.getQuestion(), springQuestion.getAnswer(), springQuestion.getLevel(), springQuestion.getTechnology());
        when(converter.convert(springQuestion)).thenReturn(springQuestionData);
        //when
        List<QuestionData> questions = service.findQuestions(level, hasJava, hasSpring, hasSql, hasJavaScript);
        //then
        assertTrue(questions.contains(javaQuestionData));
        assertTrue(questions.contains(springQuestionData));
    }

    @SuppressWarnings("all")
    @ParameterizedTest
    @EnumSource(Level.class)
    public void shouldReturnJavaAndSpringAndSqlQuestionsWhenThoseAreRequested(Level level) {
        //given
        boolean hasJava = true;
        boolean hasSpring = true;
        boolean hasSql = true;
        boolean hasJavaScript = false;
        Question javaQ = new Question(1L, "test java question", "test answer", level, JAVA);
        QuestionData javaQD = new QuestionData(javaQ.getQuestion(), javaQ.getAnswer(), javaQ.getLevel(), javaQ.getTechnology());
        when(converter.convert(javaQ)).thenReturn(javaQD);
        Question springQ = new Question(2L, "test spring question", "test answer", level, SPRING);
        QuestionData springQD = new QuestionData(springQ.getQuestion(), springQ.getAnswer(), springQ.getLevel(), springQ.getTechnology());
        when(converter.convert(springQ)).thenReturn(springQD);
        Question sqlQ = new Question(3L, "test sql question", "test answer", level, SQL);
        QuestionData sqlQD = new QuestionData(sqlQ.getQuestion(), sqlQ.getAnswer(), sqlQ.getLevel(), sqlQ.getTechnology());
        when(converter.convert(sqlQ)).thenReturn(sqlQD);
        when(repository.findByLevelAndTechnologyIn(level, List.of(JAVA, SPRING, SQL))).thenReturn(List.of(javaQ, springQ, sqlQ));
        //when
        List<QuestionData> questions = service.findQuestions(level, hasJava, hasSpring, hasSql, hasJavaScript);
        //then
        assertTrue(questions.contains(javaQD));
        assertTrue(questions.contains(springQD));
        assertTrue(questions.contains(sqlQD));
    }

    @SuppressWarnings("all")
    @ParameterizedTest
    @EnumSource(Level.class)
    public void shouldReturnJavaAndSpringAndSqlAndJSQuestionsWhenRequested(Level level) {
        //given
        boolean hasJava = true;
        boolean hasSpring = true;
        boolean hasSql = true;
        boolean hasJavaScript = true;
        Question javaQ = new Question(1L, "test java question", "test answer", level, JAVA);
        QuestionData javaQD = new QuestionData(javaQ.getQuestion(), javaQ.getAnswer(), javaQ.getLevel(), javaQ.getTechnology());
        when(converter.convert(javaQ)).thenReturn(javaQD);
        Question springQ = new Question(2L, "test spring question", "test answer", level
                , SPRING);
        QuestionData springQD = new QuestionData(springQ.getQuestion(), springQ.getAnswer(), springQ.getLevel(), springQ.getTechnology());
        when(converter.convert(springQ)).thenReturn(springQD);
        Question sqlQ = new Question(3L, "test sql question", "test answer", level, SQL);
        QuestionData sqlQD = new QuestionData(sqlQ.getQuestion(), sqlQ.getAnswer(), sqlQ.getLevel(), sqlQ.getTechnology());
        when(converter.convert(sqlQ)).thenReturn(sqlQD);
        Question jsQ = new Question(4L, "test js question", "test answer", level, JAVA_SCRIPT);
        QuestionData jsQD = new QuestionData(jsQ.getQuestion(), jsQ.getAnswer(), jsQ.getLevel(), jsQ.getTechnology());
        when(converter.convert(jsQ)).thenReturn(jsQD);
        when(repository.findByLevelAndTechnologyIn(level, List.of(JAVA, SPRING, SQL, JAVA_SCRIPT))).thenReturn(List.of(javaQ, springQ, sqlQ, jsQ));
        //when
        List<QuestionData> questions = service.findQuestions(level, hasJava, hasSpring, hasSql, hasJavaScript);
        //then
        assertTrue(questions.contains(javaQD));
        assertTrue(questions.contains(springQD));
        assertTrue(questions.contains(sqlQD));
        assertTrue(questions.contains(jsQD));
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