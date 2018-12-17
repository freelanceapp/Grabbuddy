package grabbuddy.infobite.grabbuddy;

import android.app.Application;

public class CustomFontApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FontsOverride.setDefaultFont(this, "DEFAULT", "font/nexa_light.otf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "font/nexa_light.otf");
        FontsOverride.setDefaultFont(this, "SERIF", "font/nexa_bold.otf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "font/nexa_light.otf");
    }
}
