package com.shajib.imageuploadtoserver;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.jeevandeshmukh.glidetoastlib.GlideToast;
import com.shajib.imageuploadtoserver.model.ImageUpload;
import com.shajib.imageuploadtoserver.network.ApiClient;
import com.shajib.imageuploadtoserver.network.ApiServices;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText etTitle, etDesc;
    private ImageView image;

    private static final int IMAGE_CHOSER = 1010;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        etTitle = findViewById(R.id.etTitle);
        etDesc = findViewById(R.id.etDescription);
        image = findViewById(R.id.imageId);
    }

    public void uploadImage(View view) {

        ApiServices apiServices = ApiClient.getApiClient().create(ApiServices.class);
        ImageUpload imageUpload = new ImageUpload();
        imageUpload.setTitle(etTitle.getText().toString().trim());
        imageUpload.setDescription(etDesc.getText().toString().trim());
        imageUpload.setImageUrl(imageToString());

        Call<ImageUpload> call = apiServices.uploadImage(imageUpload);

        call.enqueue(new Callback<ImageUpload>() {
            @Override
            public void onResponse(Call<ImageUpload> call, Response<ImageUpload> response) {
                if (response.isSuccessful()){
                    new GlideToast.makeToast(MainActivity.this,"Succussful "+response, GlideToast.LENGTHLONG,GlideToast.SUCCESSTOAST).show();

                }
            }

            @Override
            public void onFailure(Call<ImageUpload> call, Throwable t) {
                new GlideToast.makeToast(MainActivity.this,"Failed "+t.getMessage(), GlideToast.LENGTHLONG,GlideToast.FAILTOAST).show();

            }
        });


    }

    private String imageToString(){

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imageByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imageByte, Base64.DEFAULT);
    }

    public void selectImage(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_CHOSER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == IMAGE_CHOSER && resultCode == RESULT_OK && data != null){
            Uri path = data.getData();
            try {
                 bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                 image.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
