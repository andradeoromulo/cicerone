package model;

import utils.Validation;

public class AnswerAlternative {

    private String description;
    private Integer order;
    private boolean correct;
    private String explanation;
    private Question question;

    // Required fields only
    public AnswerAlternative(String description, boolean correct, Question question) {
        Validation.isNotBlankString(description, "AnswerAlternative.description");
        Validation.isNotNullObject(question, "AnswerAlternative.question");

        this.description = description;
        this.correct = correct;
        this.question = question;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

}
