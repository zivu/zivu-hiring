package com.zivu.hiring.persistance;


import com.zivu.hiring.model.Level;
import com.zivu.hiring.model.Technology;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Entity class that saves question, answer for that question, level of complexity and technology category.
 */
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Question {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String question;

    @Column(nullable = false, length = 1000)
    private String answer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Level level;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Technology technology;

}
