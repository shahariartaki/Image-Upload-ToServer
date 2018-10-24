package com.shajib.imageuploadtoserver.network;

import com.shajib.imageuploadtoserver.model.ImageUpload;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiServices {

    //for CRUD OPERATION

    @POST("api/insert.php")
    Call<ImageUpload> uploadImage(@Body ImageUpload upload);
}
