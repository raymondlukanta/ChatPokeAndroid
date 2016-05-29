package raymond.lukanta.com.chatpokeandroid.productpage;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import raymond.lukanta.com.chatpokeandroid.R;
import raymond.lukanta.com.chatpokeandroid.ui.RoundedTransformation;

public class ProductActivity extends AppCompatActivity implements ProductActivityFragment.OnFragmentInteractionListener {

    private Toolbar mToolbar;
    private TextView mProductName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setPadding(0, getStatusBarHeight(), 0, 0);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        mProductName = (TextView) findViewById(R.id.text_view_product_page_toolbar_product_name);


    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    final Target mPicassoTarget = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            Drawable d = new BitmapDrawable(getResources(), bitmap);
            mToolbar.setBackgroundDrawable(d);
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {
        }
    };

    @Override
    public void onProductResultSuccess(String imageUrl, String productName) {
        mProductName.setText(productName);

        Picasso picasso = Picasso.with(this);
        picasso.setIndicatorsEnabled(true);
        picasso.setLoggingEnabled(true);
        picasso
                .load(imageUrl)
                .into(mPicassoTarget);
    }
}
