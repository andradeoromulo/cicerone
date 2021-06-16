package model;

import utils.Validation;

public class Explanation extends Activity {

    private String text;

    public Explanation(String title, String slug, String text, Section section) {
        super(title, slug, section);

        Validation.isNotBlankString(text, "Explanation.text");

        this.text = text;
    }

}
