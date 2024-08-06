package GitHubRestapi.controller;

import GitHubRestapi.model.Repository;
import GitHubRestapi.service.GitHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.NoHandlerFoundException;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TutorialController {

  private final GitHubService gitHubService;

  @Autowired
  public TutorialController(GitHubService gitHubService) {
    this.gitHubService = gitHubService;
  }

  @GetMapping("/{user}")
  public ResponseEntity<String> getRepositories(@PathVariable String user) throws NoHandlerFoundException {
    String response = githubReposFromUser(user);
    gitHubService.getRepos(response);
    gitHubService.filterByFork();
    for (Repository repository : GitHubService.getRepositories()){
      String branches = githubBranchesFromRepo(repository.getBranches_url());
      gitHubService.updateBranches(repository, branches);
    }
    return new ResponseEntity<>(gitHubService.toString(), HttpStatus.OK);

  }

  private String githubReposFromUser(String user) throws NoHandlerFoundException {

    HttpEntity<String> entity = new HttpEntity<>("parameters", GitHubHeader.getUserAgentHeader());

    RestTemplate rest = new RestTemplate();
    String exchangeBody = "";
    try {
      ResponseEntity<String> exchange = rest.exchange(
              "https://api.github.com/users/" + user + "/repos",
              HttpMethod.GET,
              entity,
              String.class);
      exchangeBody = exchange.getBody();
    } catch (Exception e) {
      throw new NoHandlerFoundException(HttpMethod.GET.name(), user, HttpHeaders.EMPTY);
    }
    return exchangeBody;
  }

  private String githubBranchesFromRepo(String branches_url) {

    HttpEntity<String> entity = new HttpEntity<>("parameters", GitHubHeader.getUserAgentHeader());

    RestTemplate rest = new RestTemplate();
    ResponseEntity<String> exchange = rest.exchange(
            branches_url.split("\\{")[0],
            HttpMethod.GET,
            entity,
            String.class);
    return exchange.getBody();
  }
}