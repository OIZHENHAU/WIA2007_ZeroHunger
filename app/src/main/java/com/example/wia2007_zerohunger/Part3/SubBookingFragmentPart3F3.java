package com.example.wia2007_zerohunger.Part3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.wia2007_zerohunger.Part3.SubBookingDatabase.SubBooking;
import com.example.wia2007_zerohunger.Part3.SubBookingDatabase.SubBookingAdapter;
import com.example.wia2007_zerohunger.Part3.SubBookingDatabase.SubBookingViewModel;
import com.example.wia2007_zerohunger.R;

import java.util.List;


public class SubBookingFragmentPart3F3 extends Fragment {

    Button buttonSubscriptionPlanP3F3, buttonMySubscriptionP3F3;
    RecyclerView recyclerViewSubBookingP3F3;
    SubBookingViewModel subBookingViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sub_booking_part3_f3, container, false);

        buttonSubscriptionPlanP3F3 = view.findViewById(R.id.buttonSubscriptionPlanP3F3);
        buttonMySubscriptionP3F3 = view.findViewById(R.id.buttonMySubscriptionP3F3);

        recyclerViewSubBookingP3F3 = view.findViewById(R.id.recyclerViewSubBookingP3F3);
        recyclerViewSubBookingP3F3.setLayoutManager(new LinearLayoutManager(getContext()));

        SubBookingAdapter subBookingAdapter = new SubBookingAdapter();
        recyclerViewSubBookingP3F3.setAdapter(subBookingAdapter);

        subBookingViewModel = new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())
                .create(SubBookingViewModel.class);

        subBookingViewModel.getAllSubBookings().observe(getViewLifecycleOwner(), new Observer<List<SubBooking>>() {
            @Override
            public void onChanged(List<SubBooking> subBookings) {
                subBookingAdapter.setSubBookings(subBookings);
            }
        });

        buttonSubscriptionPlanP3F3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                SubscriptionFragmentPart3 subscriptionFragmentPart3 = new SubscriptionFragmentPart3();
                fragmentTransaction.replace(R.id.viewPageMainPart3, subscriptionFragmentPart3);
                fragmentTransaction.commit();

            }
        });

        return view;
    }
}