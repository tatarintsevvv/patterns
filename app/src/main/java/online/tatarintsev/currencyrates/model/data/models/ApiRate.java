package online.tatarintsev.currencyrates.model.data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiRate {

    @SerializedName("tp")
    @Expose
    Integer tp;
    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("from")
    @Expose
    Integer from;
    @SerializedName("currMnemFrom")
    @Expose
    String currMnemFrom;
    @SerializedName("to")
    @Expose
    Integer to;
    @SerializedName("currMnemTo")
    @Expose
    String currMnemTo;
    @SerializedName("basic")
    @Expose
    String basic;
    @SerializedName("buy")
    @Expose
    String buy;
    @SerializedName("sell")
    @Expose
    String sell;
    @SerializedName("deltaBuy")
    @Expose
    String deltaBuy;
    @SerializedName("deltaSell")
    @Expose
    String deltaSell;

    public Integer getTp() {
        return tp;
    }

    public String getName() {
        return name;
    }

    public Integer getFrom() {
        return from;
    }

    public String getCurrMnemFrom() {
        return currMnemFrom;
    }

    public Integer getTo() {
        return to;
    }

    public String getCurrMnemTo() {
        return currMnemTo;
    }

    public String getBasic() {
        return basic;
    }

    public String getBuy() {
        return buy;
    }

    public String getSell() {
        return sell;
    }

    public String getDeltaBuy() {
        return deltaBuy;
    }

    public String getDeltaSell() {
        return deltaSell;
    }

}
