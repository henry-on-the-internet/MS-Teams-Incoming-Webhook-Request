package org.example;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class RequestBody {
    private String title;
    private String text;

    public RequestBody(String title, String text) {
        this.title = title;
        this.text = text;
    }
}
