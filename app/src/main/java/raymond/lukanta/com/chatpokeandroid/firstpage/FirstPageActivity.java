package raymond.lukanta.com.chatpokeandroid.firstpage;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import raymond.lukanta.com.chatpokeandroid.R;
import raymond.lukanta.com.chatpokeandroid.app.ChatPokeAndroidApplication;
import raymond.lukanta.com.chatpokeandroid.model.Chat;
import raymond.lukanta.com.chatpokeandroid.model.Messaging;
import raymond.lukanta.com.chatpokeandroid.productpage.ProductActivity;
import raymond.lukanta.com.chatpokeandroid.ui.RoundedTransformation;
import raymond.lukanta.com.chatpokeandroid.ui.ViewHelper;
import raymond.lukanta.com.chatpokeandroid.util.BaseActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirstPageActivity extends BaseActivity {

    private ChatPokeAndroidApplication mApp;
    private Messaging mMessaging;
    private ChatAdapter mChatAdapter;
    private RecyclerView mChatHistoryRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mApp = (ChatPokeAndroidApplication) getApplicationContext();

        setContentView(R.layout.activity_first_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ImageView toolbarImageView = (ImageView) findViewById(R.id.image_view_first_page_toolbar_image);

        final EditText chatEditorEditText = (EditText) findViewById(R.id.edit_text_first_page_chat_editor);

        FloatingActionButton sendChatFloatingActionButton = (FloatingActionButton) findViewById(R.id.floating_action_button_first_page_send_button);
        sendChatFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Chat chat = new Chat();
                chat.setMessage(chatEditorEditText.getText().toString().trim());
                chat.setType("b");
                chat.setTimestamp("DDDD");
                mChatAdapter.addEntity(chat);
                chatEditorEditText.setText("");
                scrollChatHistoryRecyclerViewToBottom();
            }
        });
        int imageSizeInPx = (int) getResources().getDimension(R.dimen.toolbar_picture_size);
        Picasso.with(this).load(R.drawable.pokeball)
                .resize(imageSizeInPx, imageSizeInPx)
                .centerInside()
                .transform(new RoundedTransformation(imageSizeInPx / 2, 0))
                .into(toolbarImageView);

        mChatHistoryRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_first_page_chat_history);
        mChatHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mChatHistoryRecyclerView.setHasFixedSize(true);

        callChatApi();
    }

    private void scrollChatHistoryRecyclerViewToBottom() {
        mChatHistoryRecyclerView.scrollToPosition(mChatAdapter.getItemCount()-1);
    }

    private void callChatApi() {
        showProgressDialog();

        Call<Messaging> chatCall = mApp.getApiService().getChat();
        chatCall.enqueue(new Callback<Messaging>() {
            @Override
            public void onResponse(Call<Messaging> call, Response<Messaging> response) {
                hideProgressDialog();
                if (response.isSuccessful()) {
                    mMessaging = response.body();

                    mChatAdapter = new ChatAdapter(FirstPageActivity.this, mMessaging.getOffer());
                    mChatHistoryRecyclerView.setAdapter(mChatAdapter);

                    mChatAdapter.setData(mMessaging.getChats());
                    scrollChatHistoryRecyclerViewToBottom();

                } else {
                    showAlertDialog(getString(R.string.alert_dialog_oops), getString(R.string.error_common));
                }
            }

            @Override
            public void onFailure(Call<Messaging> call, Throwable t) {
                hideProgressDialog();
                showAlertDialog(getString(R.string.alert_dialog_oops), getString(R.string.error_common));
                t.printStackTrace();
            }
        });
    }

    private void goToProductScreen(){
        startActivity(new Intent(this, ProductActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                goToProductScreen();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
