package yalantis.com.sidemenu.foodies.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import yalantis.com.sidemenu.foodies.model.FoodDiatList;
import yalantis.com.sidemenu.foodies.model.HotelMenuItem;
import yalantis.com.sidemenu.foodies.model.HotelsMenu;
import yalantis.com.sidemenu.foodies.model.HotelsMenuList;
import yalantis.com.sidemenu.foodies.utils.PrefUtils;
import yalantis.com.sidemenu.sample.R;

public class MenuItemListActivity extends ActionBarActivity {
    private Toolbar toolbar;
    HotelsMenu hotelsMenuList;
    private ListView menuItemListView;
    private ArrayList<HotelMenuItem> hotelMenuItemArrayList;
    private MyAppAdapter myAppAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item_list);
        setToolbar();
        hotelsMenuList= PrefUtils.getHotelsMenuItems(MenuItemListActivity.this);
        hotelMenuItemArrayList=hotelsMenuList.hotelMenuItemArrayList;
        menuItemListView= (ListView) findViewById(R.id.menuItenList);
        myAppAdapter=new MyAppAdapter(hotelMenuItemArrayList,MenuItemListActivity.this);
        menuItemListView.setAdapter(myAppAdapter);
    }


    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {

            toolbar.setTitle("Menu Items List");
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

    public class MyAppAdapter extends BaseAdapter {

        public class ViewHolder {
            public TextView name,email,mobile,address;
            public ImageView image;

        }

        public List<HotelMenuItem> parkingList;

        public Context context;

        private MyAppAdapter(List<HotelMenuItem> apps, Context context) {
            this.parkingList = apps;
            this.context = context;
        }

        @Override
        public int getCount() {
            return parkingList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, final ViewGroup parent) {

            View rowView = convertView;
            ViewHolder viewHolder;

            if (rowView == null) {

                LayoutInflater inflater = getLayoutInflater();
                rowView = inflater.inflate(R.layout.item_menu_name, null);
                // configure view holder
                viewHolder = new ViewHolder();
                viewHolder.name = (TextView) rowView.findViewById(R.id.name);



                rowView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.name.setText(parkingList.get(position).ItemaName+"");
//            viewHolder.email.setText(parkingList.get(position).EmailId+"");
//            viewHolder.mobile.setText(parkingList.get(position).PhoneNumber+"");
//            viewHolder.address.setText(parkingList.get(position).StreetAddress+"");

            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PrefUtils.setMenuItemDetail(parkingList.get(position),MenuItemListActivity.this);
                    Intent intent=new Intent(MenuItemListActivity.this,MenuItemDetailActivity.class);
                    startActivity(intent);
                }
            });
            return rowView;
        }
    }
}
