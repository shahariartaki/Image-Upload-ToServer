package com.shajib.imageuploadtoserver.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    //for Retrofit

   private static final String BASE_URL = "http://www.mi-shajib.com/image_upload/";
   // private static final String BASE_URL = "http://192.168.200.2/image_upload/";

    public static Retrofit retrofit = null;

    public static Retrofit getApiClient(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).
                    addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
