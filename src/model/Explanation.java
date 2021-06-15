package model;

import utils.Validation;

public class Explanation extends Activity {

    private String text;

    public Explanation(String title, String slug, String text) {
        super(title, slug);
        this.setText(text);
    }

    public void setText(String text) {
        Validation.isNotBlankString(text, "Explanation.text");
        this.text = text;
    }
}
