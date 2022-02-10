package com.zivu.hiring.model;

/**
 * Represents question metadata: question itself, answer, level of seniority and technology (category).
 */
public record QuestionData(String question, String answer, Level level, Technology technology) {}
