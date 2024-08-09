package GitHubRestapi.controller;

import org.springframework.http.HttpHeaders;

public class GitHubHeader {


    public static HttpHeaders getUserAgentHeader(String token) {
//        String token = "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";
        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", "dopiotrko");
        headers.add("Accept", "application/json");
        if (token != null) {
            headers.add("Authorization", "Bearer " + token);
        }
        return headers;
    }
}
