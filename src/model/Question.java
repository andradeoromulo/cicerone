package model;

import utils.Validation;

public class Question extends Activity {

    private String text;
    private QuestionType type;

    public Question(String title, String slug, Section section, String text, QuestionType type) {
        super(title, slug, section);

        Validation.isNotBlankString(text, "Question.text");
        Validation.isNotNullObject(type, "Question.type");

        this.text = text;
        this.type = type;
    }

}
