package yalantis.com.sidemenu.foodies.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import yalantis.com.sidemenu.foodies.model.HotelMenuItem;
import yalantis.com.sidemenu.foodies.model.HotelsMenu;
import yalantis.com.sidemenu.foodies.model.HotelsMenuList;
import yalantis.com.sidemenu.foodies.model.SubmitOrder;


public class PrefUtils {

    public static void setArea(String area, Context ctx){

        SharedPreferences  preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("selectedArea", area);
        editor.commit();
    }

    public static String getArea( Context ctx){

        SharedPreferences  preferences = PreferenceManager.getDefaultSharedPreferences(ctx);

        String area=preferences.getString("selectedArea","");
        return  area;
    }



    public static void setHotelsMenu(HotelsMenuList currentUser, Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "hotel_menu_pref", 0);
        complexPreferences.putObject("hotel_menu_pref_value", currentUser);
        complexPreferences.commit();
    }

//    public static void clearCurrentUser( Context ctx){
//        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "user_prefs", 0);
//        complexPreferences.clearObject();
//        complexPreferences.commit();
//    }
//
//
    public static HotelsMenuList getHotelsMenu(Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "hotel_menu_pref", 0);
        HotelsMenuList currentUser = complexPreferences.getObject("hotel_menu_pref_value", HotelsMenuList.class);
        return currentUser;
    }





    public static void setHotelsMenuItems(HotelsMenu currentUser, Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "hotel_menu_items_pref", 0);
        complexPreferences.putObject("hotel_menu_items_pref_value", currentUser);
        complexPreferences.commit();
    }

    public static HotelsMenu getHotelsMenuItems(Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "hotel_menu_items_pref", 0);
        HotelsMenu currentUser = complexPreferences.getObject("hotel_menu_items_pref_value", HotelsMenu.class);
        return currentUser;
    }



    public static void setMenuItemDetail(HotelMenuItem currentUser, Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "hotel_menu_items_pref", 0);
        complexPreferences.putObject("hotel_menu_items_pref_value", currentUser);
        complexPreferences.commit();
    }

    public static HotelMenuItem getMenuItemDetail(Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "hotel_menu_items_pref", 0);
        HotelMenuItem currentUser = complexPreferences.getObject("hotel_menu_items_pref_value", HotelMenuItem.class);
        return currentUser;
    }


    public static void AddItemToCart(SubmitOrder currentUser, Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "cart_pref", 0);
        complexPreferences.putObject("cart_pref_value", currentUser);
        complexPreferences.commit();
    }

    public static SubmitOrder getCartItems(Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "cart_pref", 0);
        SubmitOrder currentUser = complexPreferences.getObject("cart_pref_value", SubmitOrder.class);
        return currentUser;
    }


}
