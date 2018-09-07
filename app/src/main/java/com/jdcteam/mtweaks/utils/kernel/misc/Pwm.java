package com.jdcteam.mtweaks.utils.kernel.misc;

        import android.content.Context;

        import com.jdcteam.mtweaks.fragments.ApplyOnBootFragment;
        import com.jdcteam.mtweaks.utils.Utils;
        import com.jdcteam.mtweaks.utils.root.Control;

/**
 * Created by Morogoku on 25/04/2017.
 */

public class Pwm {

    private static final String PWM_ENABLE = "/sys/class/lcd/panel/smart_on";

    public static void enablePwm(boolean enable, Context context) {
        run(Control.write(enable ? "1" : "0", PWM_ENABLE), PWM_ENABLE, context);
    }

    public static boolean isPwmEnabled() {
        return Utils.readFile(PWM_ENABLE).equals("1");
    }

    public static boolean supported() {
        return Utils.existFile(PWM_ENABLE);
    }

    private static void run(String command, String id, Context context) {
        Control.runSetting(command, ApplyOnBootFragment.MISC, id, context);
    }
}
