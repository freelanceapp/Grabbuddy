package grabbuddy.infobite.grabbuddy.interfaces;

/**
 * Created by Natraj3 on 8/26/2017.
 */

import android.os.Bundle;

public interface FragmentService {

    void inflateFragment(Bundle bundle, int fragmentId, String tag, boolean isBackstack);
}
