package GitHubRestapi.service;

import GitHubRestapi.model.Branch;
import GitHubRestapi.model.Repository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GitHubService {

    @Getter
    static List<Repository> repositories = new ArrayList<Repository>();

    @Override
    public String toString() {
        return repositories.stream().map(String::valueOf).collect(Collectors.joining("<br>","",""));
//        return new Gson().toJson(repositories);
    }

    public void getRepos(String json) {
        Gson gson = new Gson();
        Type repositoryListType = new TypeToken<List<Repository>>() {}.getType();

        List<Repository> data = gson.fromJson(json, repositoryListType);
        repositories.clear();
        repositories.addAll(data);
    }

    public void filterByFork(){
        List<Repository> filteredRepositories = new ArrayList<>();
        for(Repository repository: repositories){
            if (repository.getFork().equals(true)){
                continue;
            }else {
                filteredRepositories.add(repository);
            }
        }
        repositories.clear();
        repositories.addAll(filteredRepositories);
    }

    public void updateBranches(Repository repository, String branches) {
        Gson gson = new Gson();
        Type branchListType = new TypeToken<List<Branch>>() {}.getType();
        List<Branch> data = gson.fromJson(branches, branchListType);
        repository.setBranches(data);
    }
}
