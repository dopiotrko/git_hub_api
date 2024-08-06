package GitHubRestapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Commit {
    @JsonProperty("sha")
    private String sha;

    @Override
    public String toString() {
        return "sha= " + sha;
    }
}
