package andy.example.commons.utils;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

/**
 * Rest adapter for creating retrofit's rest adapter
 *
 * @author Anand Shinde
 */
public class RestAdapter {
    private static volatile RestAdapter instance = null;
    private static Retrofit restAdapter;

    private RestAdapter() {
    }

    private static RestAdapter getInstance() {
        if (instance == null) {
            synchronized (RestAdapter.class) {
                if (instance == null) {
                    instance = new RestAdapter();
                    createRestAdapter();
                }
            }
        }
        return instance;
    }

    public static Retrofit getRestAdapter() {
        return getInstance().restAdapter;
    }


    /**
     * Method to create rest adapter with base url
     */
    private static void createRestAdapter() {
        // Create OkHttpBuilder
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.connectTimeout(30, TimeUnit.SECONDS);
        okHttpBuilder.readTimeout(30, TimeUnit.SECONDS);

        // Create HTTPLogggingInterceptor
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpBuilder.addInterceptor(loggingInterceptor);

        // Create OkHttpClient
        OkHttpClient okHttpClient = okHttpBuilder.build();

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(Constants.BASE_URL);
        builder.client(okHttpClient);
        builder.addConverterFactory(GsonConverterFactory.create());
        restAdapter = builder.build();
    }
}