package com.cicerone.model;

import com.cicerone.util.validation.Validator;

public class AnswerAlternative {

    private Long id;
    private String description;
    private Integer order;
    private boolean correct;
    private String explanation;
    private Question question;

    // Required fields only
    public AnswerAlternative(String description, boolean correct, Question question) {
        Validator.isNotBlankString(description);
        Validator.isNotNullObject(question);

        this.description = description;
        this.correct = correct;
        this.question = question;
    }

}
