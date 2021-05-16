package online.tatarintsev.currencyrates.model.entities

import online.tatarintsev.currencyrates.model.data.models.ApiRate

data class CurrencyEntity(var code: Int,
                          var messageTitle: String,
                          var message: String,
                          var rid: String,
                          var downloadDate: String,
                          var rates: MutableList<ApiRate>,
                          var productState: Int,
                          var ratesDate: String)
