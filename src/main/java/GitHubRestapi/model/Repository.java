package GitHubRestapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class Repository {
    @JsonProperty
    private String name;
    @JsonProperty
    private Owner owner;
    @JsonProperty
    private String branches_url;
    @JsonProperty
    private Boolean fork;
    @JsonProperty
    private List<Branch> branches;
    @Override
    public String toString() {
        return "\n name="
                + name
                + "<br> "
                + owner
                + "<br> branches= ["
                + branches.stream().map(String::valueOf).collect(Collectors.joining("<br>","",""))
                + "]<br>";
    }
}
