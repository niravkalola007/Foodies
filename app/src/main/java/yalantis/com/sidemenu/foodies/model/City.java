package yalantis.com.sidemenu.foodies.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Android on 27-04-2015.
 */
public class City {

    @SerializedName("cityId")
    public int cityId;
    @SerializedName("stateId")
    public  int stateId;
    @SerializedName("cityName")
    public  String cityName;
}
