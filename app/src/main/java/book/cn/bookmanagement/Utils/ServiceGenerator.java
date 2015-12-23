package book.cn.bookmanagement.Utils;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import book.cn.bookmanagement.Models.RequestResultInfo;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;

/**
 * Created by uncle_charlie on 23/12/15.
 */
public class ServiceGenerator {
    public static final String  API_BASE_URL ="http://192.168.1.100:9090";

    private static OkHttpClient httpClient = new OkHttpClient();
    private static HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.interceptors().add(logging);
        Retrofit retrofit = builder.client(httpClient).build();
        return retrofit.create(serviceClass);
    }

    public interface BookManagementService{
        @Multipart
        @POST("/nav/login")
        Call<RequestResultInfo> login(@Part("userName") String userName, @Part("pwd") String pwd);
    }
}
