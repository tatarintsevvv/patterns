package online.tatarintsev.currencyrates.model.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ApiCurrency {

    @SerializedName("id")
    @Expose
    private var id: Int


    @SerializedName("name")
    @Expose
    private var name: String?


    @SerializedName("username")
    @Expose
    var username: String


    @SerializedName("email")
    @Expose
    var email: String


    @SerializedName("phone")
    @Expose
    var phone: String


    @SerializedName("website")
    @Expose
    var website: String

    public fun getId(): Int {
        return id
    }

    public fun getName(): String {
        return name
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

}