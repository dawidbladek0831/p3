package pl.app.gitplatformprovider;

import pl.app.common.exception.IllegalArgumentException;
import pl.app.common.exception.NotFoundException;


public interface GitPlatformProviderException {
    class ClientException extends RuntimeException {
        public ClientException() {
            super("client exception occurred");
        }

        public ClientException(String message) {
            super(message);
        }

        public ClientException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    class NotFoundUserException extends NotFoundException {
        public NotFoundUserException() {
            super("not found user");
        }

        public NotFoundUserException(String message) {
            super(message);
        }

        public static NotFoundUserException fromName(String name) {
            return new NotFoundUserException("not found user with name: " + name);
        }
    }
    class UnsupportedProviderException extends IllegalArgumentException {
        public UnsupportedProviderException() {
            super("unsupported provider");
        }

        public UnsupportedProviderException(String message) {
            super(message);
        }

        public static UnsupportedProviderException fromType(GitPlatformProviderType type) {
            return new UnsupportedProviderException("unsupported provider: " + type);
        }
    }
}
