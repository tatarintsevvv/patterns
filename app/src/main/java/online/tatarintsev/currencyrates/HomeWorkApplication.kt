package online.tatarintsev.currencyrates

import android.app.Application
//import androidx.annotation.RestrictTo
import io.reactivex.CompletableObserver
import io.reactivex.disposables.Disposable
import io.reactivex.plugins.RxJavaPlugins.onSubscribe
import toothpick.Toothpick
import java.util.logging.Logger

class HomeWorkApplication: Application() {
    private val TAG: String = "HomeWorkApplication"

    public override fun onCreate() {
        super.onCreate();

        initDi()
        processAppStart()
    }

    private fun initDi() {
        var appScope: RestrictTo.Scope = Toothpick.openScope(Scopes.APP);
        appScope.installModules(AppModule(this));
    }

    private void processAppStart() {
        val appStartScope: RestrictTo.Scope = Toothpick.openScopes(Scopes.APP, Scopes.APP_START)
        appStartScope.installModules(AppStartModule())

        var appStartInteractor: AppStartInteractor = appStartScope.getInstance(AppStartInteractor::class.java)

        appStartInteractor.countAppStart().subscribe(CompletableObserver() {
            public override fun onSubscribe(d: Disposable) {

            }

            public override fun onComplete() {
                Toothpick.closeScope(Scopes.APP_START)
            }

            @Override
            public override fun onError(e: Throwable) {
                Logger logger = appStartScope.getInstance(Logger.class)
                logger.logException(TAG, "Could not count app start", e)
                Toothpick.closeScope(Scopes.APP_START)
            }
        });
    }

}