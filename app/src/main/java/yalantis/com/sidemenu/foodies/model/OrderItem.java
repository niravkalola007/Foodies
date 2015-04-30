package yalantis.com.sidemenu.foodies.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Android on 28-04-2015.
 */
public class OrderItem {

//    @SerializedName("FoodDietId")
    public String FoodDietId;
//    @SerializedName("MenuItem")
    public String MenuItem;
//    @SerializedName("MenuItemQuantity")
    public String MenuItemQuantity;

    public String ItemPrice;

    public OrderItem(String foodDietId, String menuItem, String menuItemQuantity, String itemPrice) {
        FoodDietId = foodDietId;
        MenuItem = menuItem;
        MenuItemQuantity = menuItemQuantity;
        ItemPrice = itemPrice;
    }
}
