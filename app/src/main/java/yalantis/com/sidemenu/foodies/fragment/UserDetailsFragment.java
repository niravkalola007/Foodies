package yalantis.com.sidemenu.foodies.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import yalantis.com.sidemenu.foodies.activity.CartActivity;
import yalantis.com.sidemenu.sample.R;

public class UserDetailsFragment extends Fragment {

    View convertView;
    private TextView btnPaymentType;
    public static UserDetailsFragment newInstance() {
        UserDetailsFragment fragment = new UserDetailsFragment();


        return fragment;
    }


    public UserDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        convertView= inflater.inflate(R.layout.fragment_user_details, container, false);
        btnPaymentType= (TextView) convertView.findViewById(R.id.btnPaymentType);
        btnPaymentType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CartActivity)getActivity()).setCurrentTab(3);
            }
        });
        return convertView;
    }






}