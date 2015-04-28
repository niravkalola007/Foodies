package yalantis.com.sidemenu.foodies.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import yalantis.com.sidemenu.foodies.model.HotelMenuItem;
import yalantis.com.sidemenu.foodies.model.HotelsMenu;
import yalantis.com.sidemenu.foodies.model.HotelsMenuList;


public class PrefUtils {



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

}
