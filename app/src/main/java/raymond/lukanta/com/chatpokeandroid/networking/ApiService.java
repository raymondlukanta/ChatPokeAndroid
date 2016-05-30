package raymond.lukanta.com.chatpokeandroid.networking;

import raymond.lukanta.com.chatpokeandroid.model.Messaging;
import raymond.lukanta.com.chatpokeandroid.model.ProductResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by raymondlukanta on 28/05/16.
 */
public interface ApiService {
    @GET("chat")
    Call<Messaging> getChat();

    @GET("product")
    Call<ProductResponse> getProduct();
}
