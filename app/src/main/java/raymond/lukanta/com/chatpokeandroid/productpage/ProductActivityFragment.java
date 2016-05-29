package raymond.lukanta.com.chatpokeandroid.productpage;

import android.content.Context;
import android.support.annotation.Nullable;
import android.os.Bundle;
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

    public ProductActivityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mApp = (ChatPokeAndroidApplication) getActivity().getApplication();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_product, container, false);

        mProductPrice = (TextView) layout.findViewById(R.id.text_view_product_page_product_price);
        mProductDescription = (TextView) layout.findViewById(R.id.text_view_product_page_product_description);

        callProductApi();
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
                    Product product = productResponse.getProduct();
                    mProductPrice.setText(product.getPrice());
                    mProductDescription.setText(product.getDescription());

                    mListener.onProductResultSuccess(product.getImageUrl(), product.getName());

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
