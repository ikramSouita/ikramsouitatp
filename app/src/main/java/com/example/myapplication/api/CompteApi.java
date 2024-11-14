package com.example.myapplication.api;

import com.example.myapplication.model.Compte;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.*;

public interface CompteApi {
    @GET("comptes")
    Call<List<Compte>> getComptes();

    @GET("comptes/{id}")
    Call<Compte> getCompte(@Path("id") Long id);

    @POST("comptes")
    Call<Compte> addCompte(@Body Compte compte);

    @PUT("comptes/{id}")
    Call<Compte> updateCompte(@Path("id") Long id, @Body Compte compte);

    @DELETE("comptes/{id}")
    Call<Void> deleteCompte(@Path("id") Long id);
}
