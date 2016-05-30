package raymond.lukanta.com.chatpokeandroid.app;

import android.app.Application;

import raymond.lukanta.com.chatpokeandroid.networking.ApiService;
import raymond.lukanta.com.chatpokeandroid.networking.RestClient;

/**
 * Created by raymondlukanta on 28/05/16.
 */
public class ChatPokeAndroidApplication extends Application {
    private ApiService mApiService;

    @Override
    public void onCreate() {
        super.onCreate();

        RestClient restClient = new RestClient();
        mApiService =  restClient.getApiService();
    }

    public ApiService getApiService() {
        return mApiService;
    }
}
