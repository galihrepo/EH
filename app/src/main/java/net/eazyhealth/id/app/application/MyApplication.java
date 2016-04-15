package net.eazyhealth.id.app.application;

import android.app.Application;

import com.backendless.Backendless;

import net.danlew.android.joda.JodaTimeAndroid;
import net.eazyhealth.id.app.preferences.DataPreferences;
import net.eazyhealth.id.app.rest.ServiceAddress;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by GALIH ADITYO on 4/7/2016.
 */
public class MyApplication extends Application {

    public static final String BL_APP_VERSION = "v1";
    public static final String BL_APP_ID = "DC65F61D-434E-30C3-FF61-33AC7AE03100";
    public static final String BL_REST_SECRET_KEY = "C418FA96-BB50-D77E-FFB5-0F134FFDFB00";
    public static final String BL_ANDROID_SECRET_KEY = "CAB066E3-4504-3CCF-FF7F-79BB992FDE00";


    private static MyApplication mInstance;
    private Retrofit retrofit;
    private DataPreferences dataPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        dataPreferences = new DataPreferences(getApplicationContext());

        JodaTimeAndroid.init(this);
        Backendless.initApp(this, BL_APP_ID, BL_ANDROID_SECRET_KEY, BL_APP_VERSION);
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public Retrofit getRetrofit() {
        if (retrofit == null) {
            Interceptor interceptor = new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request request = chain.request().newBuilder()
                            .addHeader("application-id", BL_APP_ID)
                            .addHeader("secret-key", BL_REST_SECRET_KEY)
                            .build();
                    return chain.proceed(request);
                }
            };

            // enable logging
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addInterceptor(loggingInterceptor);
            builder.interceptors().add(interceptor);
            OkHttpClient client = builder.build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(ServiceAddress.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }

        return retrofit;
    }

    public DataPreferences getDataPreferences() {
        if (dataPreferences == null) {
            dataPreferences = new DataPreferences(getApplicationContext());
        }

        return dataPreferences;
    }
}
