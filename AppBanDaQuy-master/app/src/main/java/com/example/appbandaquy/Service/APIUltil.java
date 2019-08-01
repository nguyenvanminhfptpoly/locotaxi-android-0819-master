package com.example.appbandaquy.Service;

public class APIUltil {
    public static final String baseUrl = "http://10.22.205.170/QLSV/";

    public static DataClient getData(){
        return RetrofitClient.getRetrofit(baseUrl).create(DataClient.class);
    }
}
