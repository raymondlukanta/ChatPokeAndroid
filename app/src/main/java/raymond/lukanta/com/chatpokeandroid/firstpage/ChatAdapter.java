package raymond.lukanta.com.chatpokeandroid.firstpage;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Locale;

import raymond.lukanta.com.chatpokeandroid.R;
import raymond.lukanta.com.chatpokeandroid.model.Chat;
import raymond.lukanta.com.chatpokeandroid.model.Offer;
import raymond.lukanta.com.chatpokeandroid.ui.AbstractListAdapter;
import raymond.lukanta.com.chatpokeandroid.ui.RoundedTransformation;

/**
 * Created by raymondlukanta on 28/05/16.
 */
public class ChatAdapter extends AbstractListAdapter<Chat, ChatAdapter.ViewHolder> {
    private final static int BUYER_VIEW = 1;
    private final static int SELLER_VIEW = 2;

    private final Context mContext;
    private final LayoutInflater mInflater;

    private final SimpleDateFormat mChatTimestampSimpleDateFormat;

    private String mSellerName;
    private String mBuyerName;
    private String mSellerImageUrl;
    private String mBuyerImageUrl;

    public ChatAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);

        mChatTimestampSimpleDateFormat = new SimpleDateFormat("MMM d, h:mm", Locale.getDefault());
    }

    public void setOffer(Offer offer) {
        mSellerName = offer.getSellerName();
        mSellerImageUrl = offer.getSellerImageUrl();

        mBuyerName = offer.getBuyerName();
        mBuyerImageUrl = offer.getBuyerImageUrl();
    }

    @Override
    public int getItemViewType(int position) {
        Chat chat = mData.get(position);

        switch (chat.getType()) {
            case Chat.BUYER_CHAT_TYPE:
                return BUYER_VIEW;
            default:
                return SELLER_VIEW;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        int layoutResourceId = 0;

        switch (viewType) {
            case BUYER_VIEW:
                layoutResourceId = R.layout.row_message_right;
                break;
            case SELLER_VIEW:
                layoutResourceId = R.layout.row_message_left;
                break;
        }
        return new ViewHolder(
                mInflater.inflate(layoutResourceId, viewGroup, false)
        );
    }

    @Override
    public void onBindViewHolder(ViewHolder routeViewHolder, int position) {
        bind(routeViewHolder, position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final LinearLayout messageContentHolderLinearLayout;
        private final ImageView avatarImageView;
        private final TextView nameTextView;
        private final TextView messageTextView;
        private final TextView timestampTextView;

        public ViewHolder(View v) {
            super(v);
            messageContentHolderLinearLayout = (LinearLayout) v.findViewById(R.id.linear_layout_message_content_holder);
            avatarImageView = (ImageView) v.findViewById(R.id.image_view_message_avatar);
            nameTextView = (TextView) v.findViewById(R.id.text_view_message_content_name);
            messageTextView = (TextView) v.findViewById(R.id.text_view_message_content_message);
            timestampTextView = (TextView) v.findViewById(R.id.text_view_message_content_timestamp);

        }
    }

    public void bind(ViewHolder messagingViewHolder, int position) {
        Chat chat = mData.get(position);

        int chatBackgroundResourceId = 0;
        int senderNameTextColorResourceId = 0;
        String senderName = "";
        String senderImageUrl = "";

        switch (messagingViewHolder.getItemViewType()) {
            case BUYER_VIEW:
                chatBackgroundResourceId = R.drawable.message_bg_right;
                senderNameTextColorResourceId = R.color.colorRightName;
                senderName = mBuyerName;
                senderImageUrl = mBuyerImageUrl;
                break;
            case SELLER_VIEW:
                chatBackgroundResourceId = R.drawable.message_bg_left;
                senderNameTextColorResourceId = R.color.colorLeftName;
                senderName = mSellerName;
                senderImageUrl = mSellerImageUrl;
                break;
        }

        messagingViewHolder.messageContentHolderLinearLayout.setBackgroundResource(chatBackgroundResourceId);
        messagingViewHolder.nameTextView.setText(senderName);
        messagingViewHolder.nameTextView.setTextColor(ContextCompat.getColor(mContext, senderNameTextColorResourceId));
        messagingViewHolder.messageTextView.setText(chat.getMessage());
        messagingViewHolder.timestampTextView.setText(mChatTimestampSimpleDateFormat.format(chat.getTimestamp()));

        int imageSizeInPx = (int) mContext.getResources().getDimension(R.dimen.chat_picture_size);

        Picasso.with(mContext)
                .load(senderImageUrl)
                .resize(imageSizeInPx, imageSizeInPx)
                .centerInside()
                .transform(new RoundedTransformation(imageSizeInPx / 2, 0))
                .into(messagingViewHolder.avatarImageView);

    }
}