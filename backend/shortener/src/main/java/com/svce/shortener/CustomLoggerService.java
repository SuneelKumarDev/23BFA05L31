package com.svce.shortener;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomLoggerService {
    private final String LOG_ENDPOINT = "http://20.244.56.144/evaluation-service/logs";
    private String BEARER_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJNYXBDbGFpbXMiOnsiYXVkIjoiaHR0cDovLzIwLjI0NC41Ni4xNDQvZXZhbHVhdGlvbi1zZXJ2aWNlIiwiZW1haWwiOiJzdW5lZWxrdW1hci5qYXZhZGV2QGdtYWlsLmNvbSIsImV4cCI6MTc1Mzc2OTA3NSwiaWF0IjoxNzUzNzY4MTc1LCJpc3MiOiJBZmZvcmQgTWVkaWNhbCBUZWNobm9sb2dpZXMgUHJpdmF0ZSBMaW1pdGVkIiwianRpIjoiNTdjZTc2ODgtYzE1My00OWU5LWI2OTUtMDg0OWFhMWU3N2ZlIiwibG9jYWxlIjoiZW4tSU4iLCJuYW1lIjoia2F0dGEgc3VuZWVsIGt1bWFyIiwic3ViIjoiNjI4NGFlZTAtNGQyYi00Y2JjLWFkNGItNDFhMTkxOWQzYzQ0In0sImVtYWlsIjoic3VuZWVsa3VtYXIuamF2YWRldkBnbWFpbC5jb20iLCJuYW1lIjoia2F0dGEgc3VuZWVsIGt1bWFyIiwicm9sbE5vIjoiMjNiZmEwNWwzMSIsImFjY2Vzc0NvZGUiOiJQcmp5UUYiLCJjbGllbnRJRCI6IjYyODRhZWUwLTRkMmItNGNiYy1hZDRiLTQxYTE5MTlkM2M0NCIsImNsaWVudFNlY3JldCI6Imh4UWR5eVlOVkZnWHVkVUoifQ.68HDyBleTBUU4HldfTO1bVjLCk3wUY_UoK5NEsE4FvI";

    public void setToken(String token) {
        this.BEARER_TOKEN = token;
    }

    public void log(String stack, String level, String pkg, String message) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(BEARER_TOKEN);

        String body = String.format(
                "{\"stack\":\"%s\",\"level\":\"%s\",\"package\":\"%s\",\"message\":\"%s\"}",
                stack, level, pkg, message
        );

        HttpEntity<String> req = new HttpEntity<>(body, headers);

        try {
            restTemplate.postForEntity(LOG_ENDPOINT, req, String.class);
        } catch (Exception ignored) {

        }
    }
}