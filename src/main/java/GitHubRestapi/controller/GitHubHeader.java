package GitHubRestapi.controller;

import org.springframework.http.HttpHeaders;

public class GitHubHeader {


    public static HttpHeaders getUserAgentHeader(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", "dopiotrko");
        headers.add("Accept", "application/json");
        if (token != null) {
            headers.add("Authorization", "Bearer " + token);
        }
        return headers;
    }
}
