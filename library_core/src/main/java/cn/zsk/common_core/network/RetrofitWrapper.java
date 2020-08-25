package cn.zsk.common_core.network;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import cn.zsk.common_core.network.custom.CustomGsonConverterFactory;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Author : ZSK
 * Date : 2020/1/31
 * Description : 统一接口实例的管理类RetrofitWrapper
 */
public class RetrofitWrapper {

    private Retrofit mRetrofit;
    private OkHttpClient.Builder builder;
    private String baseURL;

    /**
     * 获取实例，使用单例模式
     * 传递URL参数，方便项目中访问不同地址
     *
     * @param url baseUrl
     * @return 实例对象
     */
    public static RetrofitWrapper getInstance(String url) {
        //synchronized 避免同时调用多个接口，导致线程并发
        RetrofitWrapper instance;
        synchronized (RetrofitWrapper.class) {
            instance = new RetrofitWrapper(url);
        }
        return instance;
    }

    private RetrofitWrapper(String url) {
        baseURL = url;
        initInterceptor();
        initTimeOut();
        initSSL();
    }


    private void initInterceptor() {
        builder = new OkHttpClient.Builder();
       /* HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(loggingInterceptor);*/
        builder.addNetworkInterceptor(new StethoInterceptor());
    }

    public <T> T create(final Class<T> service) {
        // 配置Retrofit
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .client(builder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(CustomGsonConverterFactory.create())
                //.addConverterFactory(GsonConverterFactory.create())
                .build();
        return mRetrofit.create(service);
    }

    //添加拦截器
    public RetrofitWrapper addInterceptor(Interceptor interceptor) {
        builder.addInterceptor(interceptor);
        return this;
    }

    /**
     * 初始化完全信任的信任管理器
     */
    private void initSSL() {
        try {
            final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[]{};
                }
            }};

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new SecureRandom());
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            builder.sslSocketFactory(sslSocketFactory);
            builder.hostnameVerifier((hostname, session) -> true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void initTimeOut() {
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
    }
}
