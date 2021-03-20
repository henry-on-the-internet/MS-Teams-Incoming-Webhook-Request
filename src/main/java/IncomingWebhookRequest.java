package org.example;

import org.json.JSONObject;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.*;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.JsonNode;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.RequestBody;
import org.example.WebhookUri;

public class IncomingWebhookRequest {
    public static void init() {
        Unirest.setObjectMapper(new ObjectMapper() {
            com.fasterxml.jackson.databind.ObjectMapper mapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            public String writeValue(Object value) {
                try {
                    return mapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return mapper.readValue(value, valueType);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public static void main(String args[]){
        try {
            init();

            WebhookUri webhookUri = new WebhookUri("YOUR WEBHOOK URI");

            RequestBody requestBody = new RequestBody("IDTest1213", "Guide to Rest");
            HttpResponse<String> response = null;
            response = Unirest.post(webhookUri.getUri())
                    .header("accept", "application/json").header("Content-Type", "application/json")
                    .body(requestBody)
                    .asString()
            ;
            if(response != null) {
                String str = response.getBody();
                System.out.println(str);
            }
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
    }
}