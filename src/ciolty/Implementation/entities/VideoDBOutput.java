package ciolty.Implementation.entities;

public final class VideoDBOutput {
    private final int id;
    private final String message;

    public VideoDBOutput(final int id, final String message) {
        this.id = id;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}
