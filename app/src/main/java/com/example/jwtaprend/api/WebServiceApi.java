package com.example.jwtaprend.api;

import com.example.jwtaprend.model.Login;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface WebServiceApi {

    @POST("/token")
    Call<List<String>> obtenerToken(@Body Login login);

    @GET("api/acceso_solo_con_jwt")
    Call<List<String>> obtenerRecursosToken(@Header("Authorization") String authHedaer);

}
