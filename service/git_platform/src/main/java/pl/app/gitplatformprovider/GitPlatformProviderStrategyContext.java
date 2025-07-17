package pl.app.gitplatformprovider;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class GitPlatformProviderStrategyContext {

    private final Map<GitPlatformProviderType, GitPlatformProviderClient> strategies;

    public GitPlatformProviderStrategyContext(List<GitPlatformProviderClient> strategyList) {
        this.strategies = strategyList.stream()
                .collect(Collectors.toMap(GitPlatformProviderClient::getType, Function.identity()));
    }

    public GitPlatformProviderClient resolve(GitPlatformProviderType type) {
        return Optional.ofNullable(strategies.get(type))
                .orElseThrow(() -> GitPlatformProviderException.UnsupportedProviderException.fromType(type));
    }
}