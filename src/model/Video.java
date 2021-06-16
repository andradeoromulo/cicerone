package model;

public class Video extends Activity {

    private Integer durationInMinutes;
    private String transcription;

    // Required Fields Only
    public Video(String title, String slug, Section section) {
        super(title, slug, section);
    }

    public void setDurationInMinutes(Integer durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }
}
