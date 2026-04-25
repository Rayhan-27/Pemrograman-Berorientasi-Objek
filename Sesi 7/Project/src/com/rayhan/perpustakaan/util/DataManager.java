package com.rayhan.perpustakaan.util;

import java.util.ArrayList;

public class DataManager<T> {

    private ArrayList<T> dataList = new ArrayList<>();

    public void tambahData(T data) {
        dataList.add(data);
    }

    public ArrayList<T> getAllData() {
        return dataList;
    }
}