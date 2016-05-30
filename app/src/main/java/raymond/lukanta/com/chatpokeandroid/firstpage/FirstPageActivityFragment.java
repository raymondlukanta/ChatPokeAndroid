package raymond.lukanta.com.chatpokeandroid.firstpage;

import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import raymond.lukanta.com.chatpokeandroid.R;
import raymond.lukanta.com.chatpokeandroid.app.ChatPokeAndroidApplication;
import raymond.lukanta.com.chatpokeandroid.model.Chat;
import raymond.lukanta.com.chatpokeandroid.model.Messaging;
import raymond.lukanta.com.chatpokeandroid.util.BaseFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirstPageActivityFragment extends BaseFragment {
    private ChatPokeAndroidApplication mApp;
    private ChatAdapter mChatAdapter;
    private RecyclerView mChatHistoryRecyclerView;

    public FirstPageActivityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApp = (ChatPokeAndroidApplication) getActivity().getApplication();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_first_page, container, false);

        final EditText chatEditorEditText = (EditText) layout.findViewById(R.id.edit_text_first_page_chat_editor);

        FloatingActionButton sendChatFloatingActionButton = (FloatingActionButton) layout.findViewById(R.id.floating_action_button_first_page_send_button);
        sendChatFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Chat chat = new Chat();
                chat.setMessage(chatEditorEditText.getText().toString().trim());
                chat.setType("b");

                Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

                chat.setTimestamp(calendar.getTime());
                mChatAdapter.addEntity(chat);
                chatEditorEditText.setText("");
                scrollChatHistoryRecyclerViewToBottom();
            }
        });

        mChatHistoryRecyclerView = (RecyclerView) layout.findViewById(R.id.recycler_view_first_page_chat_history);
        mChatHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mChatHistoryRecyclerView.setHasFixedSize(true);
        mChatAdapter = new ChatAdapter(getActivity());
        mChatHistoryRecyclerView.setAdapter(mChatAdapter);

        callChatApi();
        return layout;
    }

    private void scrollChatHistoryRecyclerViewToBottom() {
        mChatHistoryRecyclerView.scrollToPosition(mChatAdapter.getItemCount() - 1);
    }

    private void callChatApi() {
        showProgressDialog();

        Call<Messaging> chatCall = mApp.getApiService().getChat();
        chatCall.enqueue(new Callback<Messaging>() {
            @Override
            public void onResponse(Call<Messaging> call, Response<Messaging> response) {
                hideProgressDialog();
                if (response.isSuccessful()) {
                    Messaging messaging = response.body();

                    mChatAdapter.setOffer(messaging.getOffer());
                    mChatAdapter.setData(messaging.getChats());
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
}
