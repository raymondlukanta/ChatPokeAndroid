package raymond.lukanta.com.chatpokeandroid.firstpage;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import raymond.lukanta.com.chatpokeandroid.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class FirstPageActivityFragment extends Fragment {

    public FirstPageActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first_page, container, false);
    }
}
