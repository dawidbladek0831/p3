package pl.app.gitplatformprovider.github;

import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

@Configuration
@EnableConfigurationProperties(GithubProperties.class)
class GithubClientConfig {

    @Bean
    public GitHub gitHub(GithubProperties properties) throws IOException {
        return new GitHubBuilder().withOAuthToken(properties.getToken()).build();
    }

    @Bean
    public WebClient githubWebClient(GithubProperties properties) {
        return WebClient.builder()
                .baseUrl(properties.getBaseUrl())
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + properties.getToken())
                .defaultHeader(HttpHeaders.ACCEPT, "application/vnd.github+json")
                .defaultHeader("X-GitHub-Api-Version", "2022-11-28")
                .build();
    }
}