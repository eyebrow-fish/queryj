package fish.eyebrow.queryj.querypane;

import java.time.Duration;

public record QueryResponse(Duration time, String status, String body, boolean isError) {
    public String statusText() {
        String timeMs = time.toMillis() + "ms";
        return status != null ? status + " - " + timeMs : timeMs;
    }
}
