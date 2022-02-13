package com.example.marvalentertainment.retrofit;

import androidx.viewbinding.BuildConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CertificatePinner;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitNetwork {

    private static Retrofit retrofit = null;


    public static Retrofit getRetrofitInstance(String baseUrl) {

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
//                .client(getOkHttpClient(true))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }


//    private static OkHttpClient getOkHttpClient(boolean allowInterceptor) {
//        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        builder.connectTimeout(30, TimeUnit.SECONDS);
//        builder.readTimeout(30, TimeUnit.SECONDS);
//        builder.writeTimeout(30, TimeUnit.SECONDS);
//        if (BuildConfig.IS_SSL_PINNING) builder.certificatePinner(getCertificatePinner());
//        if (allowInterceptor) builder.addInterceptor(new AuthorizationInterceptor());
//        return builder.build();
//    }

//    private static CertificatePinner getCertificatePinner() {
//        // To get latest certificate https://www.ssllabs.com/ssltest/analyze.html?d=www.iciciprulife.com
//        // To get latest certificate https://www.ssllabs.com/ssltest/analyze.html?d=api.iciciprulife.com
//        return new CertificatePinner.Builder()
//                .add("www.iciciprulife.com", "sha256/tFocH/Ja895aiMLVpTSC7ze9Lo1etKJvyKk03S/VJFs=")
//                .add("api.iciciprulife.com", "sha256/1ucAg2vNFArUk07uXF3N6MZo7WSITjq4GjA5l+Lmk4U=")
//                .build();
//    }
//
//    private static class AuthorizationInterceptor implements Interceptor {
//
//        @Override
//        public Response intercept(Chain chain) throws IOException {
//            Request mainRequest = chain.request();
//            String url = mainRequest.url().toString();
//            // add header if applicable
//            if (url.contains("ineomobilityapi") || url.contains("pdrevival"))
//                mainRequest = mainRequest.newBuilder().header(API.AUTHORIZATION, Global.token).build();
//            // proceed with request
//            Response mainResponse = chain.proceed(mainRequest);
//
//            if (mainResponse.code() == 401 && (url.contains("ineomobilityapi") || url.contains("pdrevival"))) {
//                // request new token from server
//                String newToken = requestNewToken();
//                if (newToken != null && !newToken.isEmpty()) {
//                    // save token
//                    Global.token = API.BEARER + newToken;
//                    // add new token to header and call again
//                    Request request = mainRequest.newBuilder()
//                            .header(API.AUTHORIZATION, Global.token)
//                            .build();
//                    mainResponse = chain.proceed(request);
//                }
//            }
//            return mainResponse;
//        }
//
//        private String requestNewToken() {
//            try {
//                // request
//                AuthTokenRequest authTokenRequest = new AuthTokenRequest(
//                        Global.mobileNo, Global.emailId.toLowerCase(), Global.clientId);
//                // retrofit
//
//                retrofit = new Retrofit.Builder()
//                        .baseUrl(API.WebService.WEB_SERVICE_BASE_URL)
//                        .client(getOkHttpClient(false))
//                        .addConverterFactory(GsonConverterFactory.create())
//                        .build();
//                // api
//                BaseAPIMain baseAPIMain = retrofit.create(BaseAPIMain.class);
//                // call
//                retrofit2.Response<AuthTokenResponse> response = baseAPIMain.getAuthToken(authTokenRequest).execute();
//                // extract token
//                AuthTokenResponse authTokenResponse = response.body();
//                if (authTokenResponse != null && authTokenResponse.getToken() != null) {
//                    // save token
//                    return authTokenResponse.getToken();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return "";
//        }
//    }
}
