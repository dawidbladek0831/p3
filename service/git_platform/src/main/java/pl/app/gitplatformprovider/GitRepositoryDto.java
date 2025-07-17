package pl.app.gitplatformprovider;

import java.util.List;

public record GitRepositoryDto(
        String name,
        String ownerName,
        List<GitBranchDto> branches
) {
}
