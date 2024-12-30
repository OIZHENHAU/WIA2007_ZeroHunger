package com.example.wia2007_zerohunger.Part3;

import android.os.Bundle;

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
import android.widget.SearchView;

import com.example.wia2007_zerohunger.Part3.SubscriptionDatabase.Subscription;
import com.example.wia2007_zerohunger.Part3.SubscriptionDatabase.SubscriptionAdapter;
import com.example.wia2007_zerohunger.Part3.SubscriptionDatabase.SubscriptionViewModel;
import com.example.wia2007_zerohunger.R;

import java.util.List;

public class SubscriptionFragmentPart3 extends Fragment {

    RecyclerView recyclerViewP3F3;
    SubscriptionViewModel subscriptionViewModel;
    private SearchView editTextFarmerSubscriptionP3F3;
    Button buttonSubscriptionPlanP3F3, buttonMySubscriptionPlanP3F3;
    ImageView searchP3F3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_subscription_part3, container, false);

        recyclerViewP3F3 = view.findViewById(R.id.recyclerViewP3F3);
        recyclerViewP3F3.setLayoutManager(new LinearLayoutManager(getContext()));

        SubscriptionAdapter subscriptionAdapter = new SubscriptionAdapter();
        recyclerViewP3F3.setAdapter(subscriptionAdapter);

        subscriptionViewModel = new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())
                .create(SubscriptionViewModel.class);

        subscriptionViewModel.getAllSubscriptions().observe(getViewLifecycleOwner(), new Observer<List<Subscription>>() {
            @Override
            public void onChanged(List<Subscription> subscriptions) {
                subscriptionAdapter.setSubscriptions(subscriptions);
            }
        });

        return view;
    }
}