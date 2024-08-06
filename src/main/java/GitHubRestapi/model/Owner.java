package GitHubRestapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Owner {
    @JsonProperty("login")
    private String login;

    @Override
    public String toString() {
        return "login=" + login;
    }
}
