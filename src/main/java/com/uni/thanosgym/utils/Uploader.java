package com.uni.thanosgym.utils;

import java.io.File;
import java.io.IOException;

import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;

public class Uploader {
    private static Cloudinary cloudinary;

    private static Cloudinary createCloudinary() {
        Cloudinary cloudinary = new Cloudinary(EnvVariables.getInstance().get("CLOUDINARY_URL"));
        cloudinary.config.secure = true;
        return cloudinary;
    }

    public static UploaderResponse uploadImage(File image) {
        Cloudinary cloudinary = getCloudinaryIntance();
        try {
            String url = cloudinary.uploader().upload(image, ObjectUtils.asMap(
                    "use_filename", true,
                    "unique_filename", false,
                    "overwrite", true)).get("url").toString();
            UploaderResponse response = new UploaderResponse();
            response.setUrl(url);
            response.setSuccess(true);
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            UploaderResponse response = new UploaderResponse();
            response.setSuccess(false);
            response.setMessage("No se pudo subir la imagen");
            return response;
        }
    }

    public static Cloudinary getCloudinaryIntance() {
        if (cloudinary == null)
            cloudinary = createCloudinary();
        return cloudinary;
    }

    public static class UploaderResponse {
        private String url;
        private boolean success;
        private String message;

        public UploaderResponse() {}

        public void setMessage(String url) {
            this.message = url;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getMessage() {
            return message;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getUrl() {
            return url;
        }
    }
}
