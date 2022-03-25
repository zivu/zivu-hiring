package com.zivu.hiring.service;

import com.zivu.hiring.model.Level;
import com.zivu.hiring.persistance.QuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
class QuestionServiceTest {

    @Mock
    private QuestionRepository repository;

    @InjectMocks
    private QuestionService service;

    @SuppressWarnings("all")
    @Test
    public void shouldThrowIAEWhenProvidedWithNullLevel() {
        //given
        Level nullLevel = null;
        //when-then
        Assertions.assertThrows(NullPointerException.class, () -> {
           service.findQuestions(nullLevel, false, false, false, false);
        });
    }

}