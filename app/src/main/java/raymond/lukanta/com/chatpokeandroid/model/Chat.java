package raymond.lukanta.com.chatpokeandroid.model;

/**
 * Created by raymondlukanta on 28/05/16.
 */
public class Chat {
    public static final String SELLER_CHAT_TYPE = "s";
    public static final String BUYER_CHAT_TYPE = "b";

    private String timestamp;
    private String message;
    private String type;

    public static String getBuyerChatType() {
        return BUYER_CHAT_TYPE;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static String getSellerChatType() {
        return SELLER_CHAT_TYPE;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}