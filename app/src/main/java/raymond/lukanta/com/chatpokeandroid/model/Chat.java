package raymond.lukanta.com.chatpokeandroid.model;

import java.util.Date;

/**
 * Created by raymondlukanta on 28/05/16.
 */
public class Chat {
    public static final String SELLER_CHAT_TYPE = "s";
    public static final String BUYER_CHAT_TYPE = "b";

    private Date timestamp;
    private String message;
    private String type;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}