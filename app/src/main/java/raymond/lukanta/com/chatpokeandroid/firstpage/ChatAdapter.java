package raymond.lukanta.com.chatpokeandroid.firstpage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import raymond.lukanta.com.chatpokeandroid.R;
import raymond.lukanta.com.chatpokeandroid.model.Chat;
import raymond.lukanta.com.chatpokeandroid.ui.AbstractListAdapter;

/**
 * Created by raymondlukanta on 28/05/16.
 */
public class ChatAdapter extends AbstractListAdapter<Chat, ChatAdapter.ViewHolder> {
    private final Context mContext;
    private final LayoutInflater mInflater;

    private int selectedItem = 0;

    public ChatAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(
                mInflater.inflate(R.layout.row_message_left, viewGroup, false)
        );
    }

    @Override
    public void onBindViewHolder(ViewHolder routeViewHolder, int i) {
        routeViewHolder.itemView.setSelected(selectedItem == i);
        bind(routeViewHolder, i);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView avatarImageView;
        private final TextView nameTextView;
        private final TextView messageTextView;
        private final TextView timestampTextView;

        public ViewHolder(View v) {
            super(v);
            avatarImageView = (ImageView) v.findViewById(R.id.image_view_message_avatar);
            nameTextView = (TextView) v.findViewById(R.id.text_view_message_content_name);
            messageTextView = (TextView) v.findViewById(R.id.text_view_message_content_message);
            timestampTextView = (TextView) v.findViewById(R.id.text_view_message_content_timestamp);

        }
    }

    public void bind(ViewHolder messagingViewHolder, int i) {
        Chat chat = mData.get(i);

//        messagingViewHolder.nameTextView.setText();
        messagingViewHolder.messageTextView.setText(chat.getMessage());
        messagingViewHolder.timestampTextView.setText(chat.getTimestamp());
    }
}