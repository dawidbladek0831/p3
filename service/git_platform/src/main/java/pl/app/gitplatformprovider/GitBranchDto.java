package pl.app.gitplatformprovider;

public record GitBranchDto(
        String name,
        String lastCommitSHA
) {
}
