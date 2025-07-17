package pl.app.gitplatformprovider.github;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import pl.app.gitplatformprovider.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class GithubClient implements GitPlatformProviderClient {

    private final WebClient webClient;
    private final GitHub gitHub;

    public GithubClient(@Qualifier("githubWebClient") WebClient webClient, GitHub gitHub) {
        this.webClient = webClient;
        this.gitHub = gitHub;
    }

    @Override
    public GitPlatformProviderType getType() {
        return GitPlatformProviderType.GITHUB;
    }

    public GHUser getGHUser(String username) {
        try {
            return gitHub.getUser(username);
        } catch (IOException e) {
            throw GitPlatformProviderException.NotFoundUserException.fromName(username);
        }
    }

    @Override
    public List<GitRepositoryDto> getGitRepositories(String username) {
        GHUser ghUser = getGHUser(username);
        try {
            Map<String, GHRepository> ghRepositories = ghUser.getRepositories();
            return ghRepositories.values().stream()
                    .filter(ghRepository -> !ghRepository.isFork())
                    .map(ghRepository -> {
                        List<GitBranchDto> branches = null;
                        try {
                            branches = ghRepository.getBranches().values().stream().map(ghBranch -> new GitBranchDto(
                                    ghBranch.getName(),
                                    ghBranch.getSHA1()
                            )).toList();
                        } catch (IOException e) {
                            throw new GitPlatformProviderException.ClientException("unable to download repository branches");
                        }
                        return new GitRepositoryDto(
                                ghRepository.getName(),
                                username,
                                branches);
                    })
                    .toList();
        } catch (IOException e) {
            throw new GitPlatformProviderException.ClientException("unable to download user repositories");
        }
    }
}
