package com.zivu.hiring.model;

/**
 * Represents question metadata: question itself, level of seniority and technology (category).
 */
public record Question(String question, Level level, Technology technology) {}
