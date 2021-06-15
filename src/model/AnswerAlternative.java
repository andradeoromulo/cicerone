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
        this.setDescription(description);
        this.setCorrect(correct);
        this.setQuestion(question);
    }

    public void setDescription(String description) {
        Validation.isNotBlankString(description, "AnswerAlternative.description");
        this.description = description;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
