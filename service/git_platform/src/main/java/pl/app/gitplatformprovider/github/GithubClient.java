package pl.app.gitplatformprovider.github;

import lombok.RequiredArgsConstructor;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.springframework.stereotype.Component;
import pl.app.gitplatformprovider.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class GithubClient implements GitPlatformProviderClient {

    private final GitHub gitHub;

    @Override
    public GitPlatformProviderType getType() {
        return GitPlatformProviderType.GITHUB;
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

    private GHUser getGHUser(String username) {
        try {
            return gitHub.getUser(username);
        } catch (IOException e) {
            throw GitPlatformProviderException.NotFoundUserException.fromName(username);
        }
    }
}
