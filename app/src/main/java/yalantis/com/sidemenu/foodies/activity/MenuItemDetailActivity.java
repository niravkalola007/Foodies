package yalantis.com.sidemenu.foodies.activity;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import yalantis.com.sidemenu.foodies.model.AppConstants;
import yalantis.com.sidemenu.foodies.model.HotelMenuItem;
import yalantis.com.sidemenu.foodies.utils.PostServiceCall;
import yalantis.com.sidemenu.foodies.utils.PrefUtils;
import yalantis.com.sidemenu.sample.R;

public class MenuItemDetailActivity extends ActionBarActivity {
    private Toolbar toolbar;
    private HotelMenuItem hotelMenuItem;
    private TextView placeOrder;
    private ProgressDialog progressDialog;
    private JSONObject object;
    private JSONArray jsonArray;
    private JSONObject innerObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hotelMenuItem=PrefUtils.getMenuItemDetail(MenuItemDetailActivity.this);
        Log.e("menu item details",hotelMenuItem.ItemaName+"");
        setContentView(R.layout.activity_menu_item_detail);
        placeOrder= (TextView) findViewById(R.id.placeOrder);
        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOutOrdered();

            }
        });
        setToolbar();
    }

    private void checkOutOrdered() {
        progressDialog=new ProgressDialog(MenuItemDetailActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        object=new JSONObject();
        jsonArray=new JSONArray();
        innerObject=new JSONObject();



//        {
//            "CustomerFirstName":"Sapan",
//                "CustomerLastName":"Modi",
//                "DeliveryArea":"Address11",
//                "DeliveryCity":1,
//                "DeliveryCountry":1,
//                "DeliveryState":1,
//                "Userid":2,
//                "HotelId":2,
//                "OrderBy":2,
//                "OrderDesc":"spicy",
//                "OrderStatus":1,
//                "PaymentTypeId":1,
//                "PriceToPay":120,
//                "Tax":5,
//                "TotalPrice":125,
//                "lstOrderitem":[{
//            "FoodDietId":1,
//                    "MenuItem":2,
//                    "MenuItemQuantity":1
//        }]
//        }


        try {

            innerObject.put("FoodDietId","1");
            innerObject.put("MenuItem","2");
            innerObject.put("MenuItemQuantity","1");
//            innerObject.put("OrderId","");
//            innerObject.put("OrderItemId","");
            jsonArray.put(innerObject);





            object.put("CustomerFirstName","nk");
            object.put("CustomerLastName","Modi");
            object.put("DeliveryArea","Address11");
            object.put("DeliveryCity","1");
            object.put("DeliveryCountry","1");
            object.put("DeliveryState","1");
            object.put("Userid","2");
//            object.put("CreatedOn","");
//            object.put("DiscountPercent","");
//            object.put("DiscountPrice","");
            object.put("HotelId","2");
            object.put("OrderBy","2");
            object.put("OrderDesc","spicy");
//            object.put("OrderId","");
            object.put("OrderStatus","1");
            object.put("PaymentTypeId","1");
            object.put("PriceToPay","120");
            object.put("Tax","5");
            object.put("TotalPrice","125");
//            object.put("UpdatedOn","");
            object.put("lstOrderitem",jsonArray);
            Log.e("object:",object+"");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        new PostServiceCall(AppConstants.CHECKOUT_ORDER,object){

            @Override
            public void response(String response) {
                progressDialog.dismiss();
                Log.e("response:",response+"");
            }

            @Override
            public void error(String error) {
                progressDialog.dismiss();
            }
        }.call();
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {

            toolbar.setTitle("Item Details");
            setSupportActionBar(toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_navigation_arrow_back);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }
}
