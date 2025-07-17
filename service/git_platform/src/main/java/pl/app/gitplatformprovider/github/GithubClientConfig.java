package pl.app.gitplatformprovider.github;

import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@EnableConfigurationProperties(GithubProperties.class)
class GithubClientConfig {

    @Bean
    public GitHub gitHub(GithubProperties properties) throws IOException {
        return new GitHubBuilder().withOAuthToken(properties.getToken()).build();
    }

}