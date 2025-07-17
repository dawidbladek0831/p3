package pl.app.gitplatformprovider;

import java.util.List;

public interface GitPlatformProviderClient {
    GitPlatformProviderType getType();

    List<GitRepositoryDto> getGitRepositories(String username);
}
