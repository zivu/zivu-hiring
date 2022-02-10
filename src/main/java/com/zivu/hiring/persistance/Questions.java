package com.zivu.hiring.persistance;


import com.zivu.hiring.model.Level;
import com.zivu.hiring.model.Technology;
import jakarta.persistence.*;

/**
 * Entity class used to fetch DB questions.
 */
@Entity
public class Questions {

    @Id
    @GeneratedValue
    private Long id;

    private String question;

    private String answer;

    @Enumerated(EnumType.STRING)
    private Level level;

    @Enumerated(EnumType.STRING)
    private Technology technology;

}
