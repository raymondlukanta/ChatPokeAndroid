package raymond.lukanta.com.chatpokeandroid.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raymondlukanta on 28/05/16.
 */
public class Messaging {
    private List<Chat> chats = new ArrayList<>();
    private Offer offer;

    public List<Chat> getChats() {
        return chats;
    }

    public Offer getOffer() {
        return offer;
    }
}
