package com.example.wia2007_zerohunger.Part3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wia2007_zerohunger.R;


public class SubscriptionFilterFragmentPart3 extends Fragment {

    EditText productFilterFragmentP3F3, priceFilterFragmentP3F3;;
    Button searchButtonFilterFragmentP3F3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_subscription_filter_part3, container, false);

        productFilterFragmentP3F3 = view.findViewById(R.id.productFilterFragmentP3F3);
        priceFilterFragmentP3F3 = view.findViewById(R.id.priceFilterFragmentP3F3);

        searchButtonFilterFragmentP3F3 = view.findViewById(R.id.searchButtonFilterFragmentP3F3);

        priceFilterFragmentP3F3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    Double.parseDouble(s.toString());
                    priceFilterFragmentP3F3.setError(null);

                } catch (NumberFormatException e) {
                    Toast.makeText(getContext(), "Please enter an valid price", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        searchButtonFilterFragmentP3F3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!productFilterFragmentP3F3.getText().toString().isEmpty() &&
                        !priceFilterFragmentP3F3.getText().toString().isEmpty()) {

                    double price = Double.parseDouble(priceFilterFragmentP3F3.getText().toString());
                    String product = productFilterFragmentP3F3.getText().toString();

                    Bundle bundle = new Bundle();
                    bundle.putDouble("searchPrice", price);
                    bundle.putString("searchProduct", product);

                    FragmentManager fragmentManager = getParentFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    SubscriptionFragmentPart3 subscriptionFragmentPart3 = new SubscriptionFragmentPart3();
                    subscriptionFragmentPart3.setArguments(bundle);
                    fragmentTransaction.replace(R.id.viewPageMainPart3, subscriptionFragmentPart3);
                    fragmentTransaction.commit();

                } else {
                    Toast.makeText(getContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}