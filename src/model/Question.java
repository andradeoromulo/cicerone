package model;

import utils.Validation;

public class Question extends Activity {

    private String text;
    private QuestionType type;

    public Question(String title, String slug, String text, QuestionType type) {
        super(title, slug);

        Validation.isNotBlankString(text, "Question.text");

        this.text = text;
        this.type = type;
    }

    // Getters and Setters
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }
}
