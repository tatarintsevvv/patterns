package online.tatarintsev.currencyrates.model.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ApiCurrency {

    @SerializedName("code")
    @Expose
    Integer code;
    @SerializedName("messageTitle")
    @Expose
    String messageTitle;
    @SerializedName("message")
    @Expose
    String message;
    @SerializedName("rid")
    @Expose
    String rid;
    @SerializedName("downloadDate")
    @Expose
    String downloadDate;
    @SerializedName("rates")
    @Expose
    ArrayList<ApiRate> rates;
    @SerializedName("productState")
    @Expose
    int productState;
    @SerializedName("ratesDate")
    @Expose
    String ratesDate;

    public Integer getCode() {
        return code;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public String getMessage() {
        return message;
    }

    public String getRid() {
        return rid;
    }

    public String getDownloadDate() {
        return downloadDate;
    }

    public ArrayList<ApiRate> getRates() {
        return rates;
    }

    public int getProductState() {
        return productState;
    }

    public String getRatesDate() {
        return ratesDate;
    }

}

