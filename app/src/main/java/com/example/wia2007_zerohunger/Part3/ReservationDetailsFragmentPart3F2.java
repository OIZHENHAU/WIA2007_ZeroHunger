package com.example.wia2007_zerohunger.Part3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wia2007_zerohunger.Part3.ResBookingDatabase.ResBooking;
import com.example.wia2007_zerohunger.Part3.ResBookingDatabase.ResBookingViewModel;
import com.example.wia2007_zerohunger.Part3.ReservationDatabase.Reservation;
import com.example.wia2007_zerohunger.Part3.ReservationDatabase.ReservationViewModel;
import com.example.wia2007_zerohunger.R;

import java.util.List;


public class ReservationDetailsFragmentPart3F2 extends Fragment {
    ImageView imageViewDetailsP3F2;
    TextView textViewNameDetailsP3F2, textViewDescriptionDetailsP3F2, textViewLocationDetailsP3F2;
    TextView textViewRatingDetailsP3F2, textViewPriceDetailsP3F2, totalAmountDetailsP3F2;
    EditText bookingDateDetailsP3F2, bookingTimeSlotsP3F2, bookingNumParticipantsP3F2;
    ReservationViewModel reservationViewModel;
    ResBookingViewModel resBookingViewModel;

    Button buttonBookingDetailsP3F2;
    Button backButtonDetailsP3F2, submitButtonDetailsP3F2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reservation_details_part3_f2, container, false);

        //Image & Text View
        imageViewDetailsP3F2 = view.findViewById(R.id.imageViewDetailsP3F2);
        textViewNameDetailsP3F2 = view.findViewById(R.id.textViewNameDetailsP3F2);
        textViewDescriptionDetailsP3F2 = view.findViewById(R.id.textViewDescriptionDetailsP3F2);
        textViewLocationDetailsP3F2 = view.findViewById(R.id.textViewLocationDetailsP3F2);
        textViewRatingDetailsP3F2 = view.findViewById(R.id.textViewRatingDetailsP3F2);
        textViewPriceDetailsP3F2 = view.findViewById(R.id.textViewPriceDetailsP3F2);
        totalAmountDetailsP3F2 = view.findViewById(R.id.totalAmountDetailsP3F2);

        //Edit Text
        bookingDateDetailsP3F2 = view.findViewById(R.id.bookingDateDetailsP3F2);
        bookingTimeSlotsP3F2 = view.findViewById(R.id.bookingTimeSlotsP3F2);
        bookingNumParticipantsP3F2 = view.findViewById(R.id.bookingNumParticipantsP3F2);

        //Button
        backButtonDetailsP3F2 = view.findViewById(R.id.backButtonDetailsP3F2);
        submitButtonDetailsP3F2 = view.findViewById(R.id.submitButtonDetailsP3F2);
        buttonBookingDetailsP3F2 = view.findViewById(R.id.buttonBookingDetailsP3F2);

        Bundle bundle = getArguments();
        int reservationID = bundle.getInt("reservationId");
        Log.d("Reservation ID: ", String.valueOf(reservationID));

        reservationViewModel = new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())
                .create(ReservationViewModel.class);
        resBookingViewModel = new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())
                .create(ResBookingViewModel.class);

        reservationViewModel.getAllReservations().observe(getViewLifecycleOwner(), new Observer<List<Reservation>>() {
            @Override
            public void onChanged(List<Reservation> reservations) {
                for (Reservation reservation : reservations) {
                    if (reservation.getReservationId() == reservationID) {
                        int imageID = reservation.getImageId();
                        setImageID(imageID);

                        textViewNameDetailsP3F2.setText(reservation.getReservationName());
                        textViewDescriptionDetailsP3F2.setText(reservation.getReservationDescription());
                        textViewLocationDetailsP3F2.setText(reservation.getLocation());
                        textViewRatingDetailsP3F2.setText(String.valueOf(reservation.getRating()));
                        textViewPriceDetailsP3F2.setText(String.valueOf(reservation.getPrice()));

                    }

                }
            }
        });

        bookingNumParticipantsP3F2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null && s.length() > 0) {
                    double price = Double.parseDouble(textViewPriceDetailsP3F2.getText().toString());
                    int numParticipants = Integer.parseInt(s.toString());
                    double totalAmount = price * numParticipants;

                    totalAmountDetailsP3F2.setText(String.valueOf(totalAmount));

                } else {
                    totalAmountDetailsP3F2.setText("-");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        buttonBookingDetailsP3F2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                ReservationBookingFragmentPart3 reservationBookingFragmentPart3 = new ReservationBookingFragmentPart3();
                fragmentTransaction.replace(R.id.viewPageMainPart3, reservationBookingFragmentPart3);
                fragmentTransaction.commit();

            }
        });

        backButtonDetailsP3F2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                ReservationFragmentPart3 reservationFragmentPart3 = new ReservationFragmentPart3();
                fragmentTransaction.replace(R.id.viewPageMainPart3, reservationFragmentPart3);
                fragmentTransaction.commit();

            }
        });

        submitButtonDetailsP3F2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bookingDateDetailsP3F2.getText().toString().isEmpty() && bookingTimeSlotsP3F2.getText().toString().isEmpty() &&
                        bookingNumParticipantsP3F2.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();

                } else {
                    String name = textViewNameDetailsP3F2.getText().toString();
                    int imageId = reservationID;
                    String location = textViewLocationDetailsP3F2.getText().toString();
                    String date = bookingDateDetailsP3F2.getText().toString();
                    String timeSlots = bookingTimeSlotsP3F2.getText().toString();
                    int numParticipants = Integer.parseInt(bookingNumParticipantsP3F2.getText().toString());

                    ResBooking resBooking = new ResBooking(name, imageId, location, date, timeSlots, numParticipants);
                    resBookingViewModel.insert(resBooking);

                    Toast.makeText(getContext(), "Booking Successful", Toast.LENGTH_SHORT).show();

                    FragmentManager fragmentManager = getParentFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    ReservationFragmentPart3 reservationFragmentPart3 = new ReservationFragmentPart3();
                    fragmentTransaction.replace(R.id.viewPageMainPart3, reservationFragmentPart3);
                    fragmentTransaction.commit();

                }
            }
        });

        return view;
    }

    public void setImageID(int imageID) {
        if (imageID == 1) {
            imageViewDetailsP3F2.setImageResource(R.drawable.strawberry_picking);

        } else if (imageID == 2) {
            imageViewDetailsP3F2.setImageResource(R.drawable.farm_volunteer);

        } else if (imageID == 3) {
            imageViewDetailsP3F2.setImageResource(R.drawable.farming_workshop);

        } else {
            imageViewDetailsP3F2.setImageResource(R.drawable.farm_tour);
        }
    }
}