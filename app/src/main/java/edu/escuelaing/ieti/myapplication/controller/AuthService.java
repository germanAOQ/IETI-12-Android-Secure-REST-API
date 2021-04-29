package edu.escuelaing.ieti.myapplication.controller;

import edu.escuelaing.ieti.myapplication.model.LoginWrapper;
import edu.escuelaing.ieti.myapplication.model.Token;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {
    @POST("login")
    Call<Token> login(@Body LoginWrapper loginWrapper);
}
