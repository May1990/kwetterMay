package twitter_webservice.backingbeans;

/**
 * Created by Anna-May on 29/05/2017.
 */
public class UpdateTweetsEvent {
    private String message;

    public UpdateTweetsEvent(String message) {
        this.message = message;

    }

    public String getMessage() {
        return message;
    }
}
