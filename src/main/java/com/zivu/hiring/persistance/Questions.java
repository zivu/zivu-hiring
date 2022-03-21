package com.zivu.hiring.persistance;


import com.zivu.hiring.model.Level;
import com.zivu.hiring.model.Technology;
import jakarta.persistence.*;

/**
 * Entity class that saves question, answer for that question, level of complexity and technology category.
 */
@Entity
public class Questions {

    @Id
    @GeneratedValue
    private Long id;

    private String question;

    @Column(length = 1000)
    private String answer;

    @Enumerated(EnumType.STRING)
    private Level level;

    @Enumerated(EnumType.STRING)
    private Technology technology;

}
