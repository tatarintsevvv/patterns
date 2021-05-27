package online.tatarintsev.weather.model.data

import io.reactivex.Observable
import online.tatarintsev.weather.model.entities.WeatherEntity
import online.tatarintsev.weather.model.network.JsonPlaceHolderApi

class WeatherRemoteDataSource(private val jsonPlaceHolderApi: JsonPlaceHolderApi): WeatherDataSource {

    override fun getCurrencies(): Observable<WeatherEntity> {

        return jsonPlaceHolderApi.getJsonplaceholderApiService().getCurrencies().
            map{ apiCurrency ->
                var currency = WeatherEntity(
                        apiCurrency.code,
                        apiCurrency.messageTitle,
                        apiCurrency.message,
                        apiCurrency.rid,
                        apiCurrency.downloadDate,
                        apiCurrency.rates,
                        apiCurrency.productState,
                        apiCurrency.ratesDate
                )
                currency
            }
    }
}