package model;

public class Video extends Activity {

    private Integer durationInMinutes;
    private String transcription;

    public Video(String title, String slug) {
        super(title, slug);
    }

    public void setDurationInMinutes(Integer durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }
}
