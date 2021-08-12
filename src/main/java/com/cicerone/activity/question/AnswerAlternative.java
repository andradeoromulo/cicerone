package com.cicerone.activity.question;

import javax.persistence.*;

import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;

@Entity
public class AnswerAlternative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean correct;

    @Column(columnDefinition = "TEXT")
    private String explanation;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "orderPosition")
    private Integer order;

    @ManyToOne
    @JoinColumn(name = "questionId")
    private Question question;

    @Deprecated
    public AnswerAlternative(){};

    // Required fields only
    public AnswerAlternative(String description, boolean correct, Question question) {
        notBlank(description);
        notNull(question);

        this.description = description;
        this.correct = correct;
        this.question = question;
    }

}
