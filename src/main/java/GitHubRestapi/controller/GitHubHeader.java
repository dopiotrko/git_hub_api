package GitHubRestapi.controller;

import org.springframework.http.HttpHeaders;

public class GitHubHeader {


    public static HttpHeaders getUserAgentHeader() {
        String token = "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";
        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", "dopiotrko");
        headers.add("Accept", "application/json");
        headers.add("Authorization", "Bearer " + token);
        return headers;
    }
}
//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