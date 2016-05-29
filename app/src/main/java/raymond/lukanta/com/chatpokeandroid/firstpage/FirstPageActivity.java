package raymond.lukanta.com.chatpokeandroid.firstpage;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.kogitune.activity_transition.ActivityTransitionLauncher;
import com.squareup.picasso.Picasso;

import raymond.lukanta.com.chatpokeandroid.R;
import raymond.lukanta.com.chatpokeandroid.app.ChatPokeAndroidApplication;
import raymond.lukanta.com.chatpokeandroid.model.Chat;
import raymond.lukanta.com.chatpokeandroid.model.Messaging;
import raymond.lukanta.com.chatpokeandroid.productpage.ProductActivity;
import raymond.lukanta.com.chatpokeandroid.ui.RoundedTransformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirstPageActivity extends AppCompatActivity {
    private View.OnClickListener mOnToolbarClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            goToProductScreen(v);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_first_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ImageView toolbarImageView = (ImageView) findViewById(R.id.image_view_first_page_toolbar_image);
        toolbarImageView.setOnClickListener(mOnToolbarClickListener);
        int imageSizeInPx = (int) getResources().getDimension(R.dimen.toolbar_picture_size);
        Picasso.with(this).load(R.drawable.pokeball)
                .resize(imageSizeInPx, imageSizeInPx)
                .centerInside()
                .transform(new RoundedTransformation(imageSizeInPx / 2, 0))
                .into(toolbarImageView);

        TextView toolbarTitleTextView = (TextView) findViewById(R.id.text_view_first_page_toolbar_title);
        toolbarTitleTextView.setOnClickListener(mOnToolbarClickListener);
    }

    private void goToProductScreen(View v) {
        Intent intent = new Intent(this, ProductActivity.class);
        ActivityTransitionLauncher.with(this).from(v).launch(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                goToProductScreen(new View(this));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
