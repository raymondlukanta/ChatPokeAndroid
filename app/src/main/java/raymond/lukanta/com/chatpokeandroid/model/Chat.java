package raymond.lukanta.com.chatpokeandroid.model;

/**
 * Created by raymondlukanta on 28/05/16.
 */
public class Chat {
    public static final String SENDER_CHAT_TYPE = "s";
    public static final String BUYER_CHAT_TYPE = "b";

    private String timestamp;
    private String message;
    private String type;

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getType() {
        return type;
    }
}