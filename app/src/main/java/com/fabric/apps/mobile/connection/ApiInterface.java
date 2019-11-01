package com.fabric.apps.mobile.connection;

import com.fabric.apps.mobile.model.addressModel.ResponseAddressModel;
import com.fabric.apps.mobile.model.cekkurirModel.CostItemKurirModel;
import com.fabric.apps.mobile.model.cekkurirModel.CostsItemKurirModel;
import com.fabric.apps.mobile.model.cekkurirModel.ResponseKurirModel;
import com.fabric.apps.mobile.model.daftarModel.ResponseDaftarModel;
import com.fabric.apps.mobile.model.kotaModel.ResponseKotaModel;
import com.fabric.apps.mobile.model.profileModel.ResponseProfilModel;
import com.fabric.apps.mobile.model.provinsirajaongkirModel.ResponseProvinceModel;
import com.fabric.apps.mobile.model.cartModel.CartItem;
import com.fabric.apps.mobile.model.cartModel.ResponseCart;
import com.fabric.apps.mobile.model.productModel.ResponseProduc;
import com.fabric.apps.mobile.model.siginModel.ResponseLogin;
import com.fabric.apps.mobile.model.transaksiGetModel.ResponseGetModel;
import com.fabric.apps.mobile.model.transaksiGetModel.TransactionItemGetModel;
import com.fabric.apps.mobile.model.transaksiPostModel.ResponseTransaksiPostModel;


import java.util.List;

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

    /**
     *
     *============================================================================
     * ============= Fungsi untuk Api address dan kurir raja ongkir Get/update/delet===========================
     * ===========================================================================
     /*

     /*
     get data adrress
     */
    @GET("address/{id}")
    Call<ResponseAddressModel> getAddress(@Path("id")int id,
                                          @Query("apiKey") String apiKey,
                                          @Query("accessToken") String accessToken);

    /*
    cek provinsi from raja ongkir
     */
    @GET("cek-ongkir/provinsi")
    Call<ResponseProvinceModel> getProvince();

    /*
      cek kota from raja ongkir
       */
    @GET("cek-ongkir/kota/{id}")
    Call<ResponseKotaModel> getKota(@Path("id") String idprovinsi);


    /**
     *
     * Cek Ongkir dan kurir
     */
    @FormUrlEncoded
    @POST("cek-ongkir")
    Call<ResponseKurirModel> getKurir(
            @Field("CustomerId") int idcustomer,
            @Field("courier") String kurir);

    /**
     *
     *============================================================================
     * ============= Fungsi untuk registrasi login dan profil Get/update/delet===========================
     * ===========================================================================
     /*

    /*
      get data from api profil
       */
    @GET("customer/{id}")
    Call<ResponseProfilModel> getProfil( @Path("id")int id,
                                         @Query("apiKey") String apiKey,
                                         @Query("accessToken") String accessToken);

      /*
    Registrasi
     */
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
    Call<ResponseDaftarModel> Signup(
            @Field("name") String name,
            @Field("username") String username,
            @Field("phoneNumber") String phonenumber,
            @Field("password") String password,
            @Field("retypePassword") String rtpassword,
            @Field("ProvinceId") String ProvinceId,
            @Field("CitiesId") String CitiesId);

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

/**
 *
 *============================================================================
 * ============= Fungsi untuk transaksi Get/update/delet===========================
 * ===========================================================================
 /*

 /**
 * Post Transaksi
 */

@FormUrlEncoded
@POST("transaction")
Call<ResponseTransaksiPostModel> postTransaksi(@Query("apiKey") String apiKey,
                                               @Query("accessToken") String accessToken,
                                               @Field("CustomerId") int costumerid,
                                               @Field("AddressId") int addressid,
                                               @Field("totalHarga") int totalHarga,
                                               @Field("typeOfOngkir") String kurir,
                                               @Field("note") String note,
                                               @Field("shippingCosts") int ongkir);

/**
 * Get Transaksi
 */

@GET("transaction/{id}")
Call<ResponseGetModel> getTransaksi(@Path("id")int id,
                                           @Query("apiKey") String apiKey,
                                           @Query("accessToken") String accessToken);
}
