package com.zivu.hiring.persistance;


import com.zivu.hiring.model.Level;
import com.zivu.hiring.model.Technology;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Entity class used to fetch DB questions.
 */
@Entity
public class Question {

    @Id
    @GeneratedValue
    private Long id;
    private String question;
    private String answer;
    private Level level;
    private Technology technology;

}
