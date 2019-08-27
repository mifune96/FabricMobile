package com.fabric.apps.mobile.connection;

import com.fabric.apps.mobile.model.cartModel.Cart_data;
import com.fabric.apps.mobile.model.cartModel.Cart_parent;
import com.fabric.apps.mobile.model.productModel.ResponseProduc;
import com.fabric.apps.mobile.model.siginModel.ResponseLogin;
import com.fabric.apps.mobile.model.signupModel.ResponseSignup;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {



    @GET("product?apiKey=oa00000000app&accessToken=")
    Call<ResponseProduc> getProduc();

    @GET("carts/{id}/customer")
    Call<Cart_parent> getCartparent(@Path("id")int id);

    @FormUrlEncoded
    @POST("carts")
    Call<Cart_data> postCart(
            @Field("productInboundId") int productinboundid,
            @Field("customerId") int costumerid,
            @Field("note") String note,
            @Field("length_per_meter") int lengthpermeter);


    @DELETE("carts/{id}")
    Call<Cart_data> deleteCart(@Path("id") int id);

    @FormUrlEncoded
    @POST("customer/signin")
    Call<ResponseLogin> sigIn(
            @Field("username") String username,
            @Field("password") String password);


    @FormUrlEncoded
    @POST("customer/register")
    Call<ResponseSignup> Signup(
            @Field("name") String name,
            @Field("username") String username,
            @Field("phoneNumber") String phonenumber,
            @Field("password") String password,
            @Field("retypePassword") String rtpassword);








}
