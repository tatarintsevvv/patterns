package online.tatarintsev.currencyrates.model.network;

import java.util.List;

import io.reactivex.Observable;
import online.tatarintsev.currencyrates.model.data.models.ApiCurrency;
import retrofit2.http.GET;

public interface JsonplaceholderApiService {

    @GET("users")
    Observable<List<ApiCurrency>> getCurrencies();

}
