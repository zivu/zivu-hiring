package com.zivu.hiring.config;

import com.zivu.hiring.converter.QuestionToQuestionDataConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration that responsible for informing Spring about the new converter.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new QuestionToQuestionDataConverter());
    }

}
