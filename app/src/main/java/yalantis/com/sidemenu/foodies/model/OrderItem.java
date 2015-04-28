package yalantis.com.sidemenu.foodies.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Android on 28-04-2015.
 */
public class OrderItem {

    @SerializedName("FoodDietId")
    public String FoodDietId;
    @SerializedName("MenuItem")
    public String MenuItem;
    @SerializedName("MenuItemQuantity")
    public String MenuItemQuantity;
    @SerializedName("OrderId")
    public String OrderId;
    @SerializedName("OrderItemId")
    public String OrderItemId;
}
