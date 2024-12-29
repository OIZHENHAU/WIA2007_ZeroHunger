package com.example.wia2007_zerohunger.Part3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wia2007_zerohunger.R;


public class ReservationDetailsFragmentPart3F2 extends Fragment {
    ImageView imageViewDetailsP3F2;
    TextView textViewNameDetailsP3F2, textViewDescriptionDetailsP3F2, textViewLocationDetailsP3F2;
    TextView textViewRatingDetailsP3F2;
    EditText bookingDateDetailsP3F2, bookingTimeSlotsP3F2, bookingNumParticipantsP3F2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reservation_details_part3_f2, container, false);

        //Bundle bundle = getArguments();

        return view;
    }
}