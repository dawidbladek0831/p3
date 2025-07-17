package pl.app.gitplatformprovider;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(GitRepositoryController.resourcePath)
@RequiredArgsConstructor
@Getter
class GitRepositoryController {
    public static final String resourceName = "repositories";
    public static final String resourcePath = "/api/v1/providers/{provider}/users/{username}/" + resourceName;
    private final GitPlatformProviderStrategyContext strategyContext;

    @GetMapping()
    public List<GitRepositoryDto> getRepos(
            @PathVariable String provider,
            @PathVariable String username) {
        GitPlatformProviderType providerType = GitPlatformProviderType.valueOf(provider.toUpperCase());
        GitPlatformProviderClient client = strategyContext.resolve(providerType);
        return client.getGitRepositories(username);
    }
}
