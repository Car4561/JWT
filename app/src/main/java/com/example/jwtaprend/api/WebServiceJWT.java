package com.example.jwtaprend.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebServiceJWT {

    private static final String BASE_URL_JWT = "http://192.168.1.25:8046";
    private static WebServiceJWT instance;
    private Retrofit retrofit;
    private OkHttpClient.Builder okHttpClient;
    private HttpLoggingInterceptor httpLoggingInterceptor;

    private  WebServiceJWT (){
        httpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor);
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL_JWT).client(okHttpClient.build()).addConverterFactory(GsonConverterFactory.create()).build();


    }
    public static  synchronized WebServiceJWT getInstance(){

        if(instance==null){
            instance= new WebServiceJWT();
        }
        return instance;

    }

    public <D> D createService(Class<D> serviceClass ){
        return retrofit.create(serviceClass);
    }






}
