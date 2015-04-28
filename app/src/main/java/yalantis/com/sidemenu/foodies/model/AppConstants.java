package yalantis.com.sidemenu.foodies.model;

/**
 * Created by palak on 17-04-2015.
 */
public class AppConstants {

    public static final boolean DEBUG = true;
    public static final String DEBUG_TAG = "charityApp";


    public static int TYPE_NOT_CONNECTED = 0;
    public static int TYPE_WIFI = 1;
    public static int TYPE_MOBILE = 2;
    public static int TYPE_2G = 3;
    public static int TYPE_3G = 4;
    public static int TYPE_4G = 5;

    public static final String BASE_URL="http://ws-srv-net.in.webmyne.com/Applications/FoodiesWS/";
    public static final String GET_CITY=BASE_URL+"Hotel.svc/json/GetCityList/1";
    public static final String GET_HOTELS=BASE_URL+"Hotel.svc/json/GetAllHotels/5";
    public static final String IMAGE_PATH="http://ws-srv-net.in.webmyne.com/Applications/FoodBaazar/";
    public static final String GET_HOTELS_MENU=BASE_URL+"Hotel.svc/json/GetHotelMenu/1";
    public static final String CHECKOUT_ORDER=BASE_URL+"Hotel.svc/json/CheckOutOrdered";

}
