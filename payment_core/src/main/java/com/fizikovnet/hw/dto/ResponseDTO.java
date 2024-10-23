package com.fizikovnet.hw.dto;

public class ResponseDTO<T> {

    private final int status;
    private final T data;

    public ResponseDTO(int status, T data) {
        this.status = status;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }
}
