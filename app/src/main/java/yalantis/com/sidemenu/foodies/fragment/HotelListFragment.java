package yalantis.com.sidemenu.foodies.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import yalantis.com.sidemenu.foodies.model.AppConstants;
import yalantis.com.sidemenu.foodies.model.CityList;
import yalantis.com.sidemenu.foodies.model.Hotel;
import yalantis.com.sidemenu.foodies.model.HotelsList;
import yalantis.com.sidemenu.foodies.utils.GetServiceCall;
import yalantis.com.sidemenu.interfaces.ScreenShotable;
import yalantis.com.sidemenu.sample.R;

/**
 * Created by Konstantin on 22.12.2014.
 */
public class HotelListFragment extends Fragment implements ScreenShotable {

    public static final String BOOK = "Book";


    private MyAppAdapter customAdapter;
    private ListView hotelsList;
    private View containerView;
    protected ImageView mImageView;
    protected int res;
    private Bitmap bitmap;
    private ProgressDialog progressDialog;
    private ArrayList<Hotel> hotelArrayList;

    public static HotelListFragment newInstance() {
        HotelListFragment contentFragment = new HotelListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Integer.class.getName(),android.R.drawable.screen_background_light_transparent);
        contentFragment.setArguments(bundle);
        return contentFragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.containerView = view.findViewById(R.id.container);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        res = getArguments().getInt(Integer.class.getName());


    }

    private void getHotelList() {
        progressDialog=new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        new GetServiceCall(AppConstants.GET_HOTELS,GetServiceCall.TYPE_JSONOBJECT){

            @Override
            public void response(String response) {
                Log.e("response:", response + "");
                progressDialog.dismiss();
                HotelsList hotelsList = new GsonBuilder().create().fromJson(response, HotelsList.class);
                hotelArrayList=hotelsList.hotelArrayList;
                Log.e("list size", hotelArrayList.size()+"");
            }

            @Override
            public void error(VolleyError error) {
                progressDialog.dismiss();
                customAdapter=new MyAppAdapter(hotelArrayList,getActivity());
                hotelsList.setAdapter(customAdapter);
            }
        }.call();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hotels, container, false);
        hotelsList= (ListView) rootView.findViewById(R.id.hotelsList);
        getHotelList();
        return rootView;
    }

    @Override
    public void takeScreenShot() {
//        Thread thread = new Thread() {
//            @Override
//            public void run() {
//                Bitmap bitmap = Bitmap.createBitmap(containerView.getWidth(),
//                        containerView.getHeight(), Bitmap.Config.ARGB_8888);
//                Canvas canvas = new Canvas(bitmap);
//                containerView.draw(canvas);
//                HotelListFragment.this.bitmap = bitmap;
//            }
//        };
//
//        thread.start();

    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }

    public class MyAppAdapter extends BaseAdapter {

        public class ViewHolder {
            public TextView name,email,mobile,address;
            public ImageView image;

        }

        public List<Hotel> parkingList;
        public Context context;

        private MyAppAdapter(List<Hotel> apps, Context context) {
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
        public View getView(final int position, View convertView, ViewGroup parent) {

            View rowView = convertView;
            ViewHolder viewHolder;

            if (rowView == null) {

                LayoutInflater inflater = getActivity().getLayoutInflater();
                rowView = inflater.inflate(R.layout.item_hotel_list, null);
                // configure view holder
                viewHolder = new ViewHolder();
                viewHolder.name = (TextView) rowView.findViewById(R.id.name);
                viewHolder.email = (TextView) rowView.findViewById(R.id.email);
                viewHolder.mobile = (TextView) rowView.findViewById(R.id.mobile);
                viewHolder.address = (TextView) rowView.findViewById(R.id.address);


                rowView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.name.setText(parkingList.get(position).HotelName+"");
            viewHolder.email.setText(parkingList.get(position).EmailId+"");
            viewHolder.mobile.setText(parkingList.get(position).PhoneNumber+"");
            viewHolder.address.setText(parkingList.get(position).StreetAddress+"");

            return rowView;
        }
    }
}

