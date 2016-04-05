package net.eazyhealth.id.app.rest;

import android.app.Application;

import net.eazyhealth.id.app.model.Patients;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by GALIH ADITYO on 4/3/2016.
 */
public class RestClient {

    private static RestClient instance = null;
    private Retrofit retrofit;

    public RestClient() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .addHeader("application-id","469CF0F5-E169-AD1D-FF1B-3EF6C2E61900")
                        .addHeader("secret-key", "472BC705-76EF-9039-FF05-1AA304CF5200")
                        .build();
                return chain.proceed(request);
            }
        };

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(interceptor);
        OkHttpClient client = builder.build();

        retrofit = new Retrofit.Builder()
                .baseUrl(ServiceAddress.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public static RestClient getInstance() {
        if (instance == null) {
            Class clazz = RestClient.class;
            synchronized (clazz) {
                instance = new RestClient();
            }
        }
        return instance;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
