package model;

public class Video extends Activity {

    private Integer durationInMinutes;
    private String transcription;

    // Required Fields Only
    public Video(String title, String slug) {
        super(title, slug);
    }

    // Getters and Setters
    public Integer getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(Integer durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public String getTranscription() {
        return transcription;
    }

    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }
}
