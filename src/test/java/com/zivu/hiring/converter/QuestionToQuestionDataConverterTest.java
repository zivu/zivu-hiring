package com.zivu.hiring.converter;

import com.zivu.hiring.model.QuestionData;
import com.zivu.hiring.persistance.Question;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Covers QuestionToQuestionDataConverter with tests ensuring its correct work.
 */
class QuestionToQuestionDataConverterTest {

    private final QuestionToQuestionDataConverter converter = new QuestionToQuestionDataConverter();

    @SuppressWarnings("all")
    @Test
    public void shouldThrowIAEWhenProvidedWithNullInput() {
        //given
        Question nullQuestion = null;
        //when-then
        assertThrows(IllegalArgumentException.class, () -> {
            converter.convert(nullQuestion);
        });
    }

    @SuppressWarnings("all")
    @Test
    public void shouldTransformToQuestionData() {
        //given
        Question testQuestion = new Question(1L, "test question", null, null, null);
        //when
        QuestionData result = converter.convert(testQuestion);
        //then
        assertEquals(testQuestion.getQuestion(), result.question());
        assertEquals(testQuestion.getAnswer(), result.answer());
        assertEquals(testQuestion.getLevel(), result.level());
        assertEquals(testQuestion.getTechnology(), result.technology());
    }

}