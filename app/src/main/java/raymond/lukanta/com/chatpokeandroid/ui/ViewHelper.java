package raymond.lukanta.com.chatpokeandroid.ui;

import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * Created by raymondlukanta on 28/05/16.
 */
public class ViewHelper {
    public static float convertDpToPixel(float dp){
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }
}
