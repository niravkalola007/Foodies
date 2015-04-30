package yalantis.com.sidemenu.foodies.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import yalantis.com.sidemenu.foodies.activity.CartActivity;
import yalantis.com.sidemenu.foodies.model.SubmitOrder;
import yalantis.com.sidemenu.foodies.utils.PrefUtils;
import yalantis.com.sidemenu.sample.R;

public class CartDetailFragment extends Fragment {

    View convertView;
    private LinearLayout addOrderLayout;
    private SubmitOrder submitOrder;
    private View itemView;
    private TextView btnDeliveryType;
    public static CartDetailFragment newInstance() {
        CartDetailFragment fragment = new CartDetailFragment();


        return fragment;
    }


    public CartDetailFragment() {
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
        convertView= inflater.inflate(R.layout.fragment_cart_detail, container, false);
        btnDeliveryType= (TextView) convertView.findViewById(R.id.btnDeliveryType);
        addOrderLayout= (LinearLayout) convertView.findViewById(R.id.addOrderLayout);
        submitOrder= PrefUtils.getCartItems(getActivity());
        for(int i=0; i<submitOrder.orderItemArrayList.size();i++) {

            itemView = getActivity().getLayoutInflater().inflate(R.layout.single_order_item_view, addOrderLayout, false);
            TextView itemName= (TextView) itemView.findViewById(R.id.name);
            TextView itemPrice= (TextView) itemView.findViewById(R.id.price);
//            itemName.setText(submitOrder.orderItemArrayList.get(i).);
            addOrderLayout.addView(itemView);

        }
        btnDeliveryType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CartActivity)getActivity()).setCurrentTab(1);
            }
        });
        return convertView;
    }






}