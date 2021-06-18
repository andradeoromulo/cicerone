package com.cicerone.model;

import com.cicerone.utils.Validation;

public class AnswerAlternative {

    private String description;
    private Integer order;
    private boolean correct;
    private String explanation;
    private Question question;

    // Required fields only
    public AnswerAlternative(String description, boolean correct, Question question) {
        Validation.isNotBlankString(description);
        Validation.isNotNullObject(question);

        this.description = description;
        this.correct = correct;
        this.question = question;
    }

}
