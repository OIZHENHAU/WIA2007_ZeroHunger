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


public class ReservationFragmentFilterPart3 extends Fragment {

    EditText ratingFilterFragmentP3F2, priceFilterFragmentP3F2;

    Button searchButtonFilterFragmentP3F2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reservation_filter_part3, container, false);

        ratingFilterFragmentP3F2 = view.findViewById(R.id.ratingFilterFragmentP3F2);
        priceFilterFragmentP3F2 = view.findViewById(R.id.priceFilterFragmentP3F2);

        searchButtonFilterFragmentP3F2 = view.findViewById(R.id.searchButtonFilterFragmentP3F2);

        ratingFilterFragmentP3F2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    Double.parseDouble(s.toString());
                    ratingFilterFragmentP3F2.setError(null);

                } catch (NumberFormatException e) {
                    Toast.makeText(getContext(), "Please enter an integer value for Rating", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        priceFilterFragmentP3F2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    Double.parseDouble(s.toString());
                    priceFilterFragmentP3F2.setError(null);

                } catch (NumberFormatException e) {
                    Toast.makeText(getContext(), "Please enter an integer value for Price", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        searchButtonFilterFragmentP3F2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!ratingFilterFragmentP3F2.getText().toString().isEmpty() &&
                        !priceFilterFragmentP3F2.getText().toString().isEmpty()) {

                    double rating = Double.parseDouble(ratingFilterFragmentP3F2.getText().toString());
                    double price = Double.parseDouble(priceFilterFragmentP3F2.getText().toString());

                    Bundle bundle = new Bundle();
                    bundle.putDouble("reservationRating", rating);
                    bundle.putDouble("reservationPrice", price);

                    FragmentManager fragmentManager = getParentFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    ReservationFragmentPart3 reservationFragmentPart3 = new ReservationFragmentPart3();
                    reservationFragmentPart3.setArguments(bundle);
                    fragmentTransaction.replace(R.id.viewPageMainPart3, reservationFragmentPart3);
                    fragmentTransaction.commit();

                } else {
                    Toast.makeText(getContext(), "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}