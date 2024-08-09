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
public class GitHubApiController {

  private final GitHubService gitHubService;

  @Autowired
  public GitHubApiController(GitHubService gitHubService) {
    this.gitHubService = gitHubService;
  }

  @GetMapping("/{user}")
  public ResponseEntity<String> getRepositories(
          @PathVariable String user,
          @RequestParam (required = false)
          String token) throws NoHandlerFoundException {
    String response = githubReposFromUser(user, token);
    gitHubService.getRepos(response);
    gitHubService.filterByFork();
    for (Repository repository : GitHubService.getRepositories()){
      String branches = githubBranchesFromRepo(repository.getBranches_url(), token);
      gitHubService.updateBranches(repository, branches);
    }
    return new ResponseEntity<>(gitHubService.toString(), HttpStatus.OK);

  }

  private String githubReposFromUser(String user, String token) throws NoHandlerFoundException {

    HttpEntity<String> entity = new HttpEntity<>("parameters", GitHubHeader.getUserAgentHeader(token));

    RestTemplate rest = new RestTemplate();

    ResponseEntity<String> exchange = rest.exchange(
              "https://api.github.com/users/" + user + "/repos",
              HttpMethod.GET,
              entity,
              String.class);
    return exchange.getBody();

  }

  private String githubBranchesFromRepo(String branches_url, String token) {

    HttpEntity<String> entity = new HttpEntity<>("parameters", GitHubHeader.getUserAgentHeader(token));

    RestTemplate rest = new RestTemplate();
    ResponseEntity<String> exchange = rest.exchange(
            branches_url.split("\\{")[0],
            HttpMethod.GET,
            entity,
            String.class);
    return exchange.getBody();
  }
}