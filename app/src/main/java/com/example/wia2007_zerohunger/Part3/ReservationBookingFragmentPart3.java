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

import com.example.wia2007_zerohunger.Part3.ResBookingDatabase.ResBooking;
import com.example.wia2007_zerohunger.Part3.ResBookingDatabase.ResBookingAdapter;
import com.example.wia2007_zerohunger.Part3.ResBookingDatabase.ResBookingViewModel;
import com.example.wia2007_zerohunger.R;

import java.util.List;


public class ReservationBookingFragmentPart3 extends Fragment {

    Button buttonResExperienceP3F2, buttonResBookingP3F2;
    RecyclerView recyclerViewResBookingP3F2;

    private ResBookingViewModel resBookingViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reservation_booking_part3, container, false);

        buttonResExperienceP3F2 = view.findViewById(R.id.buttonResExperienceP3F2);

        buttonResExperienceP3F2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                ReservationFragmentPart3 reservationFragment = new ReservationFragmentPart3();
                fragmentTransaction.replace(R.id.viewPageMainPart3, reservationFragment);
                fragmentTransaction.commit();

            }
        });

        recyclerViewResBookingP3F2 = view.findViewById(R.id.recyclerViewResBookingP3F2);
        recyclerViewResBookingP3F2.setLayoutManager(new LinearLayoutManager(getContext()));

        ResBookingAdapter resBookingAdapter = new ResBookingAdapter();
        recyclerViewResBookingP3F2.setAdapter(resBookingAdapter);

        resBookingViewModel = new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())
                .create(ResBookingViewModel.class);

        resBookingViewModel.getAllResBookings().observe(getViewLifecycleOwner(), new Observer<List<ResBooking>>() {
            @Override
            public void onChanged(List<ResBooking> resBookings) {
                resBookingAdapter.setResBookings(resBookings);
            }
        });

        return view;
    }
}