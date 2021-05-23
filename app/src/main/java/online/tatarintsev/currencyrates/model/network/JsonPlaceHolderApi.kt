package online.tatarintsev.currencyrates.model.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JsonPlaceHolderApi {
    public fun getJsonplaceholderApiService(): JsonPlaceHolderApiService {
        var gson: Gson = GsonBuilder().create()

        var interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        var client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        var retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(" https://alpha.as50464.net:29870")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return retrofit.create(JsonPlaceHolderApiService::class.java)
    }

}