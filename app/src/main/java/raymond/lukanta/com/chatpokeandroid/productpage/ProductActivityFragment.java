package raymond.lukanta.com.chatpokeandroid.productpage;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import raymond.lukanta.com.chatpokeandroid.R;
import raymond.lukanta.com.chatpokeandroid.app.ChatPokeAndroidApplication;
import raymond.lukanta.com.chatpokeandroid.model.Product;
import raymond.lukanta.com.chatpokeandroid.model.ProductResponse;
import raymond.lukanta.com.chatpokeandroid.util.BaseFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class ProductActivityFragment extends BaseFragment {
    private OnFragmentInteractionListener mListener;

    private ChatPokeAndroidApplication mApp;
    private TextView mProductPrice;
    private TextView mProductDescription;
    private Product mProduct;

    public ProductActivityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
        mApp = (ChatPokeAndroidApplication) getActivity().getApplication();
        callProductApi();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_product, container, false);

        mProductPrice = (TextView) layout.findViewById(R.id.text_view_product_page_product_price);
        mProductDescription = (TextView) layout.findViewById(R.id.text_view_product_page_product_description);
        updateUI();
        return layout;
    }

    private void callProductApi() {
        showProgressDialog();

        Call<ProductResponse> chatCall = mApp.getApiService().getProduct();
        chatCall.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                hideProgressDialog();
                if (response.isSuccessful()) {
                    ProductResponse productResponse = response.body();
                    mProduct = productResponse.getProduct();
                    updateUI();

                } else {
                    showAlertDialog(getString(R.string.alert_dialog_oops), getString(R.string.error_common));
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                hideProgressDialog();
                showAlertDialog(getString(R.string.alert_dialog_oops), getString(R.string.error_common));
                t.printStackTrace();
            }
        });
    }

    private void updateUI() {
        if (mProductPrice != null && mProduct != null) {
            mProductPrice.setText(mProduct.getPrice());
            mProductDescription.setText(mProduct.getDescription());

            mListener.onProductResultSuccess(mProduct.getImageUrl(), mProduct.getName());
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onProductResultSuccess(String imageUrl, String productName);
    }
}
