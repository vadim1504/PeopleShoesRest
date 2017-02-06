package by.training.rest.exception;

public final class RestPreconditions {

    private RestPreconditions() {
        throw new AssertionError();
    }

    public static void checkFound(final boolean expression) {
        if (!expression) {
            throw new MyResourceNotFoundException();
        }
    }

    public static <T> T checkFound(final T resource) {
        if (resource == null) {
            throw new MyResourceNotFoundException();
        }
        return resource;
    }
}