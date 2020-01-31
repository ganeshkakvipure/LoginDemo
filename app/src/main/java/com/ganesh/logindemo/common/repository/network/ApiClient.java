package com.ganesh.logindemo.common.repository.network;


/**
 * Created by Ganesh on 23/05/17.
 */
public class ApiClient {

  /*   public static final String PATH_URL ="";// BuildConfig.PATH_URL;
    private static final String BASE_URL ="";// BuildConfig.BASE_URL;/*"http://10.202.3.152:9000";

    private static Retrofit retrofit = null;


    public static ApiInterface getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(getOkkHttpClient())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()))
                    .build();
        }
        return retrofit.create(ApiInterface.class);
    }


    private static OkHttpClient getOkkHttpClient() {
        return new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(new HeaderInterceptor())
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
    }

    /**
     * Web service method name /*

    public interface ApiMethod {
        String LOGIN_USER = "login.php";

    }

    /**
     * Created by Ganesh on 19/6/17.
     /

    public static class HeaderInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain)
                throws IOException {
            Request request = chain.request();
            request = request.newBuilder()
                    .addHeader("Content-Type", "application/json;charset=utf-8")
                    .addHeader("Accept", "application/json")
                    .build();
            Response response = chain.proceed(request);
            return response;
        }
    }

    **/
}
