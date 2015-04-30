package yalantis.com.sidemenu.foodies.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import yalantis.com.sidemenu.foodies.model.AppConstants;
import yalantis.com.sidemenu.foodies.model.City;
import yalantis.com.sidemenu.foodies.model.FoodDiatList;
import yalantis.com.sidemenu.foodies.model.HotelMenuItem;
import yalantis.com.sidemenu.foodies.utils.PostServiceCall;
import yalantis.com.sidemenu.foodies.utils.PrefUtils;
import yalantis.com.sidemenu.sample.R;

public class MenuItemDetailActivity extends ActionBarActivity {
    private Toolbar toolbar;
    private HotelMenuItem hotelMenuItem;
    private TextView addToCart,price;
    private ProgressDialog progressDialog;
    private JSONObject object;
    private JSONArray jsonArray;
    private JSONObject innerObject;
    private Spinner spinnerFoodDietType;
    private TextView name,tagline,cuisine,quantity;
    private FoodDietSpinnerAdapter foodDietSpinnerAdapter;
    private ImageView remove,add;
    int i=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item_detail);
        hotelMenuItem=PrefUtils.getMenuItemDetail(MenuItemDetailActivity.this);
        initView();
        setToolbar();
        initData();
    }

    private void initView() {
        addToCart= (TextView) findViewById(R.id.addToCart);
        price= (TextView) findViewById(R.id.price);
        quantity= (TextView) findViewById(R.id.quantity);
        remove= (ImageView) findViewById(R.id.remove);
        add= (ImageView) findViewById(R.id.add);
        name= (TextView) findViewById(R.id.name);
        tagline= (TextView) findViewById(R.id.tagline);
        cuisine= (TextView) findViewById(R.id.cuisine);
    }

    private void initData() {
        quantity.setText("Quantity "+i);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity.setText("Quantity "+(++i));
                price.setText(getResources().getString(R.string.rupees)+" "+(Integer.parseInt(hotelMenuItem.Price)*i)+"");
            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i > 1) {
                    quantity.setText("Quantity " + (--i));
                    price.setText(getResources().getString(R.string.rupees) + " " + (Integer.parseInt(hotelMenuItem.Price) * i) + "");
                }
            }
        });
        price.setText(getResources().getString(R.string.rupees) + " " + hotelMenuItem.Price + "");
        foodDietSpinnerAdapter=new FoodDietSpinnerAdapter(MenuItemDetailActivity.this,hotelMenuItem.foodDiatListArrayList);
        spinnerFoodDietType= (Spinner) findViewById(R.id.spinnerFoodDietType);
        spinnerFoodDietType.setAdapter(foodDietSpinnerAdapter);
        name.setText(hotelMenuItem.ItemaName);
        tagline.setText(hotelMenuItem.TagLine);
        cuisine.setText(hotelMenuItem.cuisineObject.CuisineName);
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuItemDetailActivity.this, CartActivity.class);

                startActivity(intent);

//                checkOutOrdered();
            }
        });
    }

    private void checkOutOrdered() {
        progressDialog=new ProgressDialog(MenuItemDetailActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        object=new JSONObject();
        jsonArray=new JSONArray();
        innerObject=new JSONObject();
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

            toolbar.setTitle(getIntent().getStringExtra("item_name"));
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


    public class FoodDietSpinnerAdapter extends BaseAdapter implements SpinnerAdapter {

        private final Context activity;
        private ArrayList<FoodDiatList> asr;

        public FoodDietSpinnerAdapter(Context context, ArrayList<FoodDiatList> asr) {
            this.asr = asr;
            activity = context;
        }

        public int getCount() {
            return asr.size();
        }

        public Object getItem(int i) {
            return asr.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            TextView txt = new TextView(MenuItemDetailActivity.this);
            txt.setPadding(16, 16, 16, 16);
            txt.setTextSize(18);
            txt.setGravity(Gravity.CENTER_VERTICAL);
            txt.setText(asr.get(position).DietName);
            txt.setTextColor(Color.parseColor("#000000"));
            return txt;
        }

        public View getView(int i, View view, ViewGroup viewgroup) {
            TextView txt = new TextView(MenuItemDetailActivity.this);
            txt.setGravity(Gravity.CENTER_VERTICAL);
            txt.setPadding(16, 16, 16, 16);
            txt.setTextSize(18);
            txt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_down, 0);
            txt.setText(asr.get(i).DietName);
            txt.setTextColor(Color.parseColor("#000000"));
            return txt;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_cart) {
            Intent intent=new Intent(MenuItemDetailActivity.this,CartActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
