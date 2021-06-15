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

        this.description = description;
        this.correct = correct;
        this.question = question;
    }

    // Getters and Setters
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
