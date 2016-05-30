package raymond.lukanta.com.chatpokeandroid.productpage;

import android.animation.Animator;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import raymond.lukanta.com.chatpokeandroid.R;

public class ProductActivity extends AppCompatActivity implements ProductActivityFragment.OnFragmentInteractionListener {
    ProductActivityFragment mProductActivityFragment;
    private Toolbar mToolbar;
    private TextView mProductName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        FragmentManager fm = getSupportFragmentManager();
        mProductActivityFragment = (ProductActivityFragment) fm.findFragmentByTag("productActivityFragment");

        if (mProductActivityFragment == null) {
            mProductActivityFragment = new ProductActivityFragment();
            fm.beginTransaction().add(R.id.fragment, mProductActivityFragment, "productActivityFragment").commit();
        }

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            mToolbar.setPadding(0, getStatusBarHeight(), 0, 0);

            boolean hasMenuKey = ViewConfiguration.get(this).hasPermanentMenuKey();
            boolean hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
            int id = getResources().getIdentifier("config_showNavigationBar", "bool", "android");

            if ((!hasMenuKey && !hasBackKey) || id > 0) {
                View fragment = findViewById(R.id.fragment);

                int screenOrientation = getResources().getConfiguration().orientation;
                switch (screenOrientation) {
                    case Configuration.ORIENTATION_PORTRAIT:
                        fragment.setPadding(16, 16, 16, getNavigationBarHeight());
                        break;
                    case Configuration.ORIENTATION_LANDSCAPE:
                        fragment.setPadding(16, 16, getNavigationBarHeight(), 16);
                        break;
                }
            }
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

    public int getNavigationBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("navigation_bar_height", "dimen", "android");
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
            Drawable drawable = new BitmapDrawable(getResources(), bitmap);
            mToolbar.setBackgroundDrawable(drawable);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mToolbar.post(new Runnable() {
                    @Override
                    public void run() {
                        int cx = mToolbar.getWidth() / 2;
                        int cy = mToolbar.getHeight() / 2;

                        float finalRadius = (float) Math.hypot(cx, cy);
                        Animator anim =
                                ViewAnimationUtils.createCircularReveal(mToolbar, cx, cy, 0, finalRadius);
                        anim.start();
                    }
                });
            }
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {
            mToolbar.setBackgroundDrawable(errorDrawable);
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {
            mToolbar.setBackgroundDrawable(placeHolderDrawable);
        }
    };

    @Override
    public void onProductResultSuccess(String imageUrl, String productName) {
        mProductName.setText(productName);

        Picasso.with(this)
                .load(imageUrl)
                .error(android.R.drawable.stat_notify_error)
                .placeholder(R.drawable.progress_image)
                .into(mPicassoTarget);
    }
}
