package com.example.wia2007_zerohunger.Part3;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.wia2007_zerohunger.Part3.ReservationDatabase.Reservation;
import com.example.wia2007_zerohunger.Part3.ReservationDatabase.ReservationAdapter;
import com.example.wia2007_zerohunger.Part3.ReservationDatabase.ReservationViewModel;
import com.example.wia2007_zerohunger.R;

import java.util.ArrayList;
import java.util.List;


public class ReservationFragmentPart3 extends Fragment {

    RecyclerView recyclerViewP3F2;
    ReservationViewModel reservationViewModel;
    private SearchView editTextFarmerConnectionP3F2;
    Button buttonExperienceP3F2, buttonBookingP3F2;
    ImageView searchP3F2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reservation_part3, container, false);

        recyclerViewP3F2 = view.findViewById(R.id.recyclerViewP3F2);
        recyclerViewP3F2.setLayoutManager(new LinearLayoutManager(getContext()));

        ReservationAdapter reservationAdapter = new ReservationAdapter();
        recyclerViewP3F2.setAdapter(reservationAdapter);

        reservationViewModel = new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())
                .create(ReservationViewModel.class);

        reservationViewModel.getAllReservations().observe(getViewLifecycleOwner(), new Observer<List<Reservation>>() {
            @Override
            public void onChanged(List<Reservation> reservations) {
                reservationAdapter.setReservations(reservations);
            }
        });

        editTextFarmerConnectionP3F2 = view.findViewById(R.id.editTextFarmerConnectionP3F2);
        editTextFarmerConnectionP3F2.clearFocus();

        editTextFarmerConnectionP3F2.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        reservationAdapter.setOnItemClickListener(new ReservationAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Reservation reservation) {
                Bundle bundle = new Bundle();
                bundle.putInt("reservationId", reservation.getReservationId());

                ReservationDetailsFragmentPart3F2 reservationDetailsFragment = new ReservationDetailsFragmentPart3F2();
                reservationDetailsFragment.setArguments(bundle);

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.viewPageMainPart3, reservationDetailsFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }

    public void filterList(String newText) {
        List<Reservation> filteredList = new ArrayList<>();
        String lowerNewText = newText.toLowerCase();

        reservationViewModel.getAllReservations().observe(getViewLifecycleOwner(), new Observer<List<Reservation>>() {
            @Override
            public void onChanged(List<Reservation> reservations) {
                for (Reservation reservation : reservations) {
                    String reservationName = reservation.getReservationName().toLowerCase();

                    if (reservationName.equals(lowerNewText)) {
                        filteredList.add(reservation);
                    }
                }

                ReservationAdapter reservationAdapter = new ReservationAdapter();

                if (filteredList.isEmpty()) {
                    reservationAdapter.setReservations(reservations);
                    recyclerViewP3F2.setAdapter(reservationAdapter);

                } else {
                    Toast.makeText(getContext(), "Data Found", Toast.LENGTH_SHORT).show();
                    reservationAdapter.setReservations(filteredList);
                    recyclerViewP3F2.setAdapter(reservationAdapter);
                }
            }
        });
    }
}