package com.example.wia2007_zerohunger.Part3;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wia2007_zerohunger.R;


public class ConnectionFragmentFilterPart3 extends Fragment {

    EditText distanceFilterFragmentP3F1, productFilterFragmentP3F1, priceFilterFragmentP3F1;
    Button searchButtonFilterFragmentP3F1;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_connection_filter_part3, container, false);

        distanceFilterFragmentP3F1 = view.findViewById(R.id.distanceFilterFragmentP3F1);
        productFilterFragmentP3F1 = view.findViewById(R.id.productFilterFragmentP3F1);
        priceFilterFragmentP3F1 = view.findViewById(R.id.priceFilterFragmentP3F1);

        searchButtonFilterFragmentP3F1 = view.findViewById(R.id.searchButtonFilterFragmentP3F1);


        searchButtonFilterFragmentP3F1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!distanceFilterFragmentP3F1.getText().toString().isEmpty()
                        && !productFilterFragmentP3F1.getText().toString().isEmpty()  && !priceFilterFragmentP3F1.getText().toString().isEmpty()) {
                    double distance = Double.parseDouble(distanceFilterFragmentP3F1.getText().toString());
                    String product = productFilterFragmentP3F1.getText().toString();
                    double price = Double.parseDouble(priceFilterFragmentP3F1.getText().toString());

                    Bundle bundle = new Bundle();
                    bundle.putDouble("distance", distance);
                    bundle.putString("product", product);
                    bundle.putDouble("price", price);

                    FragmentManager fragmentManager = getParentFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    ConnectionFragmentPart3 connectionFragmentPart3 = new ConnectionFragmentPart3();
                    connectionFragmentPart3.setArguments(bundle);
                    fragmentTransaction.replace(R.id.viewPageMainPart3, connectionFragmentPart3);
                    fragmentTransaction.commit();

                } else {
                    Toast.makeText(getContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }
}