package com.zivu.hiring.converter;

import com.zivu.hiring.model.QuestionData;
import com.zivu.hiring.persistance.Question;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Converts Question persistence type to QuestionData.
 */
@Slf4j
@Component
public class QuestionToQuestionDataConverter implements Converter<Question, QuestionData> {

    @Override
    public QuestionData convert(@NonNull Question source) {
        log.debug("Received the following Question instance to be transformed to QuestionData: {}", source);
        QuestionData questionData = new QuestionData(source.getQuestion(), source.getAnswer(), source.getLevel(), source.getTechnology());
        log.debug("Transformation complete. The result is: {}", questionData);
        return questionData;
    }

}
