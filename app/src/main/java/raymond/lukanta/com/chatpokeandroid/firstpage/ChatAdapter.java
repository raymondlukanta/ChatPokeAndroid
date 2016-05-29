package raymond.lukanta.com.chatpokeandroid.firstpage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import raymond.lukanta.com.chatpokeandroid.R;
import raymond.lukanta.com.chatpokeandroid.model.Chat;
import raymond.lukanta.com.chatpokeandroid.model.Offer;
import raymond.lukanta.com.chatpokeandroid.ui.AbstractListAdapter;

/**
 * Created by raymondlukanta on 28/05/16.
 */
public class ChatAdapter extends AbstractListAdapter<Chat, ChatAdapter.ViewHolder> {
    private final static int BUYER_VIEW = 1;
    private final static int SENDER_VIEW = 2;

    private final Context mContext;
    private final LayoutInflater mInflater;
    private final Offer mOffer;

    public ChatAdapter(Context context, Offer offer) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mOffer = offer;
    }

    @Override
    public int getItemViewType(int position) {
        Chat chat = mData.get(position);

        switch (chat.getType()) {
            case Chat.BUYER_CHAT_TYPE: return BUYER_VIEW;
            default: return SENDER_VIEW;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        int layoutResourceId = 0;

        switch (viewType) {
            case BUYER_VIEW : layoutResourceId = R.layout.row_message_right;
                break;
            case SENDER_VIEW : layoutResourceId = R.layout.row_message_left;
                break;
        }
        return new ViewHolder(
                mInflater.inflate(layoutResourceId, viewGroup, false)
        );
    }

    @Override
    public void onBindViewHolder(ViewHolder routeViewHolder, int i) {
        bind(routeViewHolder, i);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView avatarImageView;
        private final TextView nameTextView;
        private final TextView messageTextView;
        private final TextView timestampTextView;

        public ViewHolder(View v) {
            super(v);
            avatarImageView = (ImageView) v.findViewById(R.id.image_view_message_left_avatar);
            nameTextView = (TextView) v.findViewById(R.id.text_view_message_content_name);
            messageTextView = (TextView) v.findViewById(R.id.text_view_message_content_message);
            timestampTextView = (TextView) v.findViewById(R.id.text_view_message_content_timestamp);

        }
    }

    public void bind(ViewHolder messagingViewHolder, int i) {
        Chat chat = mData.get(i);


        switch (chat.getType()) {

        }
//        messagingViewHolder.nameTextView.setText();
        messagingViewHolder.messageTextView.setText(chat.getMessage());
        messagingViewHolder.timestampTextView.setText(chat.getTimestamp());
    }
}