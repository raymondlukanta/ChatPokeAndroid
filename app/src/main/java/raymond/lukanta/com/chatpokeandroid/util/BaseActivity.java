package raymond.lukanta.com.chatpokeandroid.util;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import raymond.lukanta.com.chatpokeandroid.R;

/**
 * Created by raymondlukanta on 28/05/16.
 */
public class BaseActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;

    public void showAlertDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(getString(R.string.dialog_got_it), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        builder.create().show();
    }

    public void showProgressDialog(String message) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.setMessage(message);

        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }
    protected void showProgressDialog() {
        showProgressDialog(getString(R.string.dialog_loading));
    }

    protected void hideProgressDialog() {
        if (progressDialog != null  && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

    @Override
    public void onDestroy() {
        hideProgressDialog();
        super.onDestroy();
    }
}
