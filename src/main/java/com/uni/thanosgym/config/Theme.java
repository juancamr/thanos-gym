package com.uni.thanosgym.config;

import java.awt.Color;
import java.awt.Font;

import com.uni.thanosgym.utils.Utils;

public class Theme {

    public static final String defaultProfilePhoto = "https://static.vecteezy.com/system/resources/previews/027/312/306/non_2x/portrait-of-a-dj-with-headphone-isolated-essential-workers-avatar-icons-characters-for-social-media-and-networking-user-profile-website-and-app-3d-render-illustration-png.png";

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
