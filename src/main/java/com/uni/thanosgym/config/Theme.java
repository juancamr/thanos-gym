package com.uni.thanosgym.config;

import java.awt.Color;
import java.awt.Font;

import com.uni.thanosgym.utils.Utils;

public class Theme {

    public static final String defaultProfilePhoto = "https://secure.gravatar.com/avatar/b7046e5d769b09a3045d73b89ad61fe4?d=https%3A%2F%2Favatar-management--avatars.us-west-2.prod.public.atl-paas.net%2Fdefault-avatar-0.png";

    public static Font getMainFont(int weight, int size) {
        return new Font("Montserrat", weight, size);
    }

    public static final Color inputColor = new Color(240, 240, 240);

    public static class colors {
        public static final Color primary = Utils.hexToRGB("#FFDB00");
        public static final Color blue = Utils.hexToRGB("#26355D");
        public static final Color purple = Utils.hexToRGB("#AF47D2");
        public static final Color orange = Utils.hexToRGB("#FF8F00");
        public static final Color blueCenizo = Utils.hexToRGB("#2F4172");
        public static final Color gray = Utils.hexToRGB("#D4D4D4");
        public static final Color grayCenizo = Utils.hexToRGB("#F2F2F2");
        public static final Color green = Color.GREEN;
    }
}
