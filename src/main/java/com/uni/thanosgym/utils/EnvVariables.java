package com.uni.thanosgym.utils;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvVariables {
    public static Dotenv dotenv;
    
    public static Dotenv getInstance() {
        if (dotenv == null)
            return dotenv = Dotenv.load();
        return dotenv;
    }
}
