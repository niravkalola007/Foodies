package yalantis.com.sidemenu.foodies.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alertdialogpro.AlertDialogPro;
import com.android.volley.VolleyError;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import yalantis.com.sidemenu.foodies.model.AppConstants;
import yalantis.com.sidemenu.foodies.model.Area;
import yalantis.com.sidemenu.foodies.model.City;
import yalantis.com.sidemenu.foodies.model.CityList;
import yalantis.com.sidemenu.foodies.utils.GetServiceCall;
import yalantis.com.sidemenu.interfaces.ScreenShotable;
import yalantis.com.sidemenu.sample.R;

/**
 * Created by Konstantin on 22.12.2014.
 */
public class CitySelectionFragment extends Fragment implements ScreenShotable {
    public static final String CLOSE = "Close";
    public static final String BUILDING = "Building";
    private static final int NATIVE_THEME = Integer.MIN_VALUE;
    private int mTheme = -1;
    private  String mCheckedItem;
    private ProgressDialog progressDialog;
    private ArrayList<City> cityArrayList;
    private ArrayList<String> cityStringList;
    String[] cityListValue;

    private ArrayList<Area> areaArrayList;
    private ArrayList<String> areaStringList;
    String[] areaListValue;


    private View containerView;
    protected ImageView mImageView;
    protected int res;
    private Bitmap bitmap;
    private TextView btnSubmit;
    private EditText etCityetCity,etArea;

    public static CitySelectionFragment newInstance() {
        CitySelectionFragment contentFragment = new CitySelectionFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Integer.class.getName(), android.R.drawable.screen_background_light_transparent);
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
        res = getArguments().getInt(Integer.class.getName());

    }

    @Override
    public void onResume() {
        super.onResume();
        progressDialog=new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        getCityList();

    }

    private void getCityList(){
        new GetServiceCall(AppConstants.GET_CITY,GetServiceCall.TYPE_JSONOBJECT){

            @Override
            public void response(String response) {
                Log.e("response:", response + "");
                progressDialog.dismiss();
                CityList cityList = new GsonBuilder().create().fromJson(response, CityList.class);
                cityArrayList=cityList.cityArrayList;
                cityStringList=new ArrayList<String>();
                for(int i=0;i<cityArrayList.size();i++){
                    cityStringList.add(cityArrayList.get(i).cityName);
                }
                cityListValue = cityStringList.toArray(new String[cityStringList.size()]);
                Log.e("list", cityListValue.length+"");


            }


            @Override
            public void error(VolleyError error) {
                progressDialog.dismiss();

            }
        }.call();
    }

    private void getAreaList() {
        new GetServiceCall(AppConstants.GET_CITY,GetServiceCall.TYPE_JSONOBJECT){

            @Override
            public void response(String response) {
                Log.e("response:", response + "");
                progressDialog.dismiss();
                CityList cityList = new GsonBuilder().create().fromJson(response, CityList.class);
                cityArrayList=cityList.cityArrayList;
                cityStringList=new ArrayList<String>();
                for(int i=0;i<cityArrayList.size();i++){
                    cityStringList.add(cityArrayList.get(i).cityName);
                }
                cityListValue = cityStringList.toArray(new String[cityStringList.size()]);
                Log.e("list", cityListValue.length+"");


            }


            @Override
            public void error(VolleyError error) {
                progressDialog.dismiss();

            }
        }.call();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
//        mImageView = (ImageView) rootView.findViewById(R.id.image_content);
//        mImageView.setClickable(true);
//        mImageView.setFocusable(true);
//        mImageView.setImageResource(res);
//        Toast.makeText(getActivity(),"content fragment 0",Toast.LENGTH_LONG).show();
        etCityetCity= (EditText) rootView.findViewById(R.id.etCity);
        etArea= (EditText) rootView.findViewById(R.id.etArea);
        btnSubmit= (TextView) rootView.findViewById(R.id.btnSubmit);
        etCityetCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                final String[] list = new String[]{"Vadodara", "Surat"};
                int checkedItemIndex = 0;
                mCheckedItem = cityListValue[checkedItemIndex];

                createAlertDialogBuilder()
                        .setTitle("Select City")
                        .setSingleChoiceItems(cityListValue,
                                checkedItemIndex,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        mCheckedItem = cityListValue[which];
//                                        showToast(mCheckedItem);
                                    }
                                })
                        .setNegativeButton("Cancel", new ButtonClickedListener("Cancel"))
                        .setPositiveButton(
                                "Save",
                                new ButtonClickedListener("Save")

                        )
                        .show();

            }
        });

//        etArea.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
////                final String[] list = new String[]{"Vadodara", "Surat"};
//                int checkedItemIndex = 0;
//                mCheckedItem = cityListValue[checkedItemIndex];
//
//                createAlertDialogBuilder()
//                        .setTitle("Select Area")
//                        .setSingleChoiceItems(cityListValue,
//                                checkedItemIndex,
//                                new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        mCheckedItem = cityListValue[which];
////                                        showToast(mCheckedItem);
//                                    }
//                                })
//                        .setNegativeButton("Cancel", new ButtonClickedListener("Cancel"))
//                        .setPositiveButton(
//                                "Save",
//                                new ButtonClickedListener("Save")
//
//                        )
//                        .show();
//
//            }
//        });
        return rootView;
    }

//    private void showToast(CharSequence toastText) {
//        Toast mToast = null;
//        if (mToast != null) {
//            mToast.cancel();
//        }
//        mToast = Toast.makeText(getActivity(), toastText, Toast.LENGTH_SHORT);
//        mToast.show();
//    }

    private AlertDialog.Builder createAlertDialogBuilder() {
        if (mTheme == NATIVE_THEME) {
            return new AlertDialog.Builder(getActivity());
        }

        return new AlertDialogPro.Builder(getActivity(), mTheme);
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
//                CitySelectionFragment.this.bitmap = bitmap;
//            }
//        };
//
//        thread.start();

    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }

    private class ButtonClickedListener implements DialogInterface.OnClickListener {
        private CharSequence mShowWhenClicked;

        public ButtonClickedListener(CharSequence showWhenClicked) {
            mShowWhenClicked = showWhenClicked;


        }

        @Override
        public void onClick(DialogInterface dialog, int which) {
//            showToast("\"" + mShowWhenClicked + "\"" + " button clicked.");
            etCityetCity.setText(mCheckedItem);


        }
    }
}

