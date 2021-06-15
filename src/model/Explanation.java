package model;

import utils.Validation;

public class Explanation extends Activity {

    private String text;

    public Explanation(String title, String slug, String text) {
        super(title, slug);

        Validation.isNotBlankString(text, "Explanation.text");
        this.text = text;
    }

    // Getters and Setters
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
