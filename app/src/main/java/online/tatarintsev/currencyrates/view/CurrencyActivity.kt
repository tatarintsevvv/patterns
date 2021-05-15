package online.tatarintsev.currencyrates.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import online.tatarintsev.currencyrates.R
import online.tatarintsev.currencyrates.model.entities.CurrencyEntity
import online.tatarintsev.currencyrates.presenter.CurrencyPresenter
import online.tatarintsev.currencyrates.presenter.CurrencyView

class CurrencyActivity: AppCompatActivity(), CurrencyView {

    private var currencyActivityState: CurrencyActivityState = CurrencyActivityState

    private lateinit var presenter: CurrencyPresenter
//    private lateinit var progressBar: ProgressBar
//    private var textView: TextView
//    private var userIdTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        Button button = findViewById(R.id.button);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.text);
        userIdTextView = findViewById(R.id.userId);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // действия пользователя передаются в презентер
                presenter.onUserAction();
            }
        });
        */
        // получаем синглтон экземпляр состояния активити
//        currencyActivityState = CurrencyActivityState.getInstance()

        // активити получает презентер из внешнего класса
        // он лениво создается при первом обращении
        // если активити будет пересоздано, а процесс приложения не будет убит,
        // при следующем обращении вернётся уже созданный презентер,
        // в котором можно хранить необходимые данные
        presenter = currencyActivityState.getPresenter()
        presenter.onCreate(savedInstanceState)
    }

    protected override fun onSaveInstanceState(outState: Bundle) {
        // одним из вариантов сохранения данных, переживающих уничтожение процесса
        // является использование Bundle. Это не очень чисто архитектурно, но можно допустить в качестве исключения
        presenter.onSaveInstanceState(outState);

        super.onSaveInstanceState(outState);
    }

    override fun onStart() {
        super.onStart()

        // в этом методе активити будет присоединено к презентеру
        currencyActivityState.onStart(this);
    }

    protected override fun onStop() {
        // в этом методе активити будет отсоединено от презентера, чтобы не допускать утечек памяти
        currencyActivityState.onStop()
        super.onStop()
    }

    @Override
    protected override fun onDestroy() {
        // isFinishing означает, что пользователь явно уходит из активити
        if (isFinishing()) {
            // презентер больше не нужно хранить, ссылка на него будет обнулена
            currencyActivityState.onDestroy()
        }

        super.onDestroy()
    }

    /**
     * View выполняет максимально простые команды презентера и содержит минимум логики
     */
    /*
    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }
*/
    /**
     * здесь презентер не знает, каким образом View будет отображать данные
     * но можно сделать более подробные методы, например showUserName()
     */

    override fun showCurrency(currency: CurrencyEntity) {
//        textView.setText(user.getName());
//        userIdTextView.setText(String.valueOf(user.getId()));
    }

    override fun showError() {
//        textView.setText("Error!")
    }

    override fun showResult() {
//        textView.setText("Result");
    }
}