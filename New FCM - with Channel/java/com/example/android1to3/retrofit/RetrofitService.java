package com.example.android1to3.retrofit;

import com.example.android1to3.imageupload.ImageRoot;
import com.example.android1to3.php_employee.EmployeeRoot;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitService {

    @FormUrlEncoded
    @POST("employee.php")
    Call<String> saveEmployee(@Field("flag") String flag,
                              @Field("firstname") String firstName,
                              @Field("lastname")String lastName,
                              @Field("mobile")String mobile);

    @FormUrlEncoded
    @POST("employee.php")
    Call<EmployeeRoot> selectEmployee(@Field("flag") String flag);


    @FormUrlEncoded
    @POST("employee.php")
    Call<String> deleteEmployee(@Field("flag") String flag,
    @Field("id") String id);

    @FormUrlEncoded
    @POST("image.php")
    Call<String> uploadImage(@Field("image") String image,
                                @Field("name") String name);


    @FormUrlEncoded
    @POST("employee.php")
    Call<ImageRoot> getImages(@Field("flag") String flag);



}
