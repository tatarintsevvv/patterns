package online.tatarintsev.currencyrates.model.repositories;

import io.reactivex.Observable;
import online.tatarintsev.currencyrates.model.entities.CurrencyEntity;

public interface CurrencyRepository {
    Observable<CurrencyEntity> getCurrency(int id);
}