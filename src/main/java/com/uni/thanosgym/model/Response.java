package com.uni.thanosgym.model;

import java.util.List;

public class Response<T> {

    private boolean success;
    private String message;
    private T data;
    private int id;
    private List<T> dataList;

    public Response(boolean success) {
        this(success, null, null, null, 0);
    }

    public Response(boolean success, String message) {
        this(success, message, null, null, 0);
    }

    public Response(boolean success, T data) {
        this(success, null, data, null, 0);
    }

    public Response(boolean success, T data, int id) {
        this(success, null, data, null, id);
    }

    public Response(boolean success, List<T> dataList) {
        this(success, null, null, dataList, 0);
    }

    public Response(boolean success, String message, T data) {
        this(success, message, data, null, 0);
    }

    public Response(boolean success, String message, List<T> dataList) {
        this(success, message, null, dataList, 0);
    }

    public Response(boolean success, String message, T data, List<T> dataList, int id) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.id = id;
        this.dataList = dataList;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public int getId() {
        return id;
    }

    public Response<T> somethingWentWrong(Exception e) {
        System.out.println(e);
        return new Response<T>(false, "Algo salio mal");
    }

}
