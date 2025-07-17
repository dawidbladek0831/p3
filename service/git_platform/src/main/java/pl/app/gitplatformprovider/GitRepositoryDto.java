package pl.app.gitplatformprovider;

public record GitRepositoryDto(
        String name,
        String url,
        String description,
        boolean isFork
) {}
