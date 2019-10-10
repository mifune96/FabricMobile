package com.fabric.apps.mobile.connection;

import com.fabric.apps.mobile.model.cartModel.CartItem;
import com.fabric.apps.mobile.model.cartModel.ResponseCart;
import com.fabric.apps.mobile.model.productModel.ResponseProduc;
import com.fabric.apps.mobile.model.siginModel.ResponseLogin;
import com.fabric.apps.mobile.model.signupModel.ResponseSignup;
import com.fabric.apps.mobile.utils.SessionSharedPreferences;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {


    /**
    ** for get all produc for catalog and home
     **/

    @GET("product")
    Call<ResponseProduc> getProduc(@Query("apiKey") String apiKey,
                                   @Query("accessToken") String accessToken);

    @FormUrlEncoded
    @POST("customer/signin")
    Call<ResponseLogin> sigIn(
            @Field("username") String username,
            @Field("password") String password);


    /**
    ** for Post if Costumer Registration
     **/
    @FormUrlEncoded
    @POST("customer/register")
    Call<ResponseSignup> Signup(
            @Field("name") String name,
            @Field("username") String username,
            @Field("phoneNumber") String phonenumber,
            @Field("password") String password,
            @Field("retypePassword") String rtpassword);

    /**
     *
     *============================================================================
     * ============= Fungsi untuk Cart Get/update/delet===========================
     * ===========================================================================
    /*
    For Get all data cart by id costumer
     */
    @GET("cart/{id}")
    Call<ResponseCart> getCart(
                                 @Path("id")int id,
                                 @Query("apiKey") String apiKey,
                                 @Query("accessToken") String accessToken);

    /*
    Post Cart Data
     */
    @FormUrlEncoded
    @POST("cart")
    Call<CartItem> postCart(@Query("apiKey") String apiKey,
                            @Query("accessToken") String accessToken,
                            @Field("CustomerId") int costumerid,
                            @Field("ProductId") int produkid,
                            @Field("permeter") double permeter);
    /*
    For Update Cart
     */
    @FormUrlEncoded
    @PUT("cart/{id}")
    Call<ResponseCart> updateCart(@Path("id") int idcard,
                                  @Query("apiKey") String apiKey,
                                  @Query("accessToken") String accessToken,
                                  @Field("CustomerId")int idcustomer,
                                  @Field("ProductId") int idproduk,
                                  @Field("permeter") double permeter);
    /*
    Delete Cart
     */
    @DELETE("cart/{id}")
    Call<ResponseCart> deletCart  (@Path("id") int id,
                                   @Query("apiKey") String apiKey,
                                   @Query("accessToken") String accessToken);
}
