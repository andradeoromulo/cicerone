package model;

import utils.Validation;

public class Question extends Activity {

    private String text;
    private QuestionType type;

    public Question(String title, String slug, String text, QuestionType type) {
        super(title, slug);
        this.setText(text);
        this.setType(type);
    }

    public void setText(String text) {
        Validation.isNotBlankString(text, "Question.text");
        this.text = text;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }
}
