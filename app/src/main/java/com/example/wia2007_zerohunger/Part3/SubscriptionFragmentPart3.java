package com.example.wia2007_zerohunger.Part3;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
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
import android.widget.ImageView;
import android.widget.Toast;

import com.example.wia2007_zerohunger.Part3.SubscriptionDatabase.Subscription;
import com.example.wia2007_zerohunger.Part3.SubscriptionDatabase.SubscriptionAdapter;
import com.example.wia2007_zerohunger.Part3.SubscriptionDatabase.SubscriptionViewModel;
import com.example.wia2007_zerohunger.R;

import java.util.ArrayList;
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

        editTextFarmerSubscriptionP3F3 = view.findViewById(R.id.editTextFarmerSubscriptionP3F3);

        buttonSubscriptionPlanP3F3 = view.findViewById(R.id.buttonSubscriptionPlanP3F3);
        buttonMySubscriptionPlanP3F3 = view.findViewById(R.id.mySubscriptionPlanP3F3);

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

        if (getArguments() != null) {
            double searchPrice = getArguments().getDouble("searchPrice");
            String searchProduct = getArguments().getString("searchProduct");

            filterListBasedOnCondition(searchPrice, searchProduct);

        }

        subscriptionAdapter.setOnItemClickListener(new SubscriptionAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Subscription subscription) {
                Bundle bundle = new Bundle();
                bundle.putInt("subscriptionId", subscription.getSubscriptionId());

                SubscriptionDetailsFragmentPart3 subscriptionDetailsFragment = new SubscriptionDetailsFragmentPart3();
                subscriptionDetailsFragment.setArguments(bundle);

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.viewPageMainPart3, subscriptionDetailsFragment)
                        .addToBackStack(null).commit();
            }
        });

        editTextFarmerSubscriptionP3F3.clearFocus();

        editTextFarmerSubscriptionP3F3.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

        buttonMySubscriptionPlanP3F3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                SubBookingFragmentPart3F3 subBookingFragment = new SubBookingFragmentPart3F3();
                fragmentTransaction.replace(R.id.viewPageMainPart3, subBookingFragment);
                fragmentTransaction.commit();
            }
        });

        searchP3F3 = view.findViewById(R.id.searchP3F3);

        searchP3F3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                SubscriptionFilterFragmentPart3 subscriptionFragmentFilterPart3 = new SubscriptionFilterFragmentPart3();
                fragmentTransaction.replace(R.id.viewPageMainPart3, subscriptionFragmentFilterPart3);
                fragmentTransaction.commit();
            }
        });

        return view;
    }

    public void filterList(String newText) {
        List<Subscription> filterList = new ArrayList<>();
        String lowerNewText = newText.toLowerCase();

        subscriptionViewModel.getAllSubscriptions().observe(getViewLifecycleOwner(), new Observer<List<Subscription>>() {
            @Override
            public void onChanged(List<Subscription> subscriptions) {
                for (Subscription subscription : subscriptions) {
                    String subscriptionName = subscription.getSubscriptionName().toLowerCase();

                    if (subscriptionName.equals(lowerNewText)) {
                        filterList.add(subscription);
                    }

                }

                SubscriptionAdapter subscriptionAdapter = new SubscriptionAdapter();

                if (filterList.isEmpty()) {
                    subscriptionAdapter.setSubscriptions(subscriptions);
                    recyclerViewP3F3.setAdapter(subscriptionAdapter);

                } else {
                    Toast.makeText(getContext(), "Data Found", Toast.LENGTH_SHORT).show();
                    subscriptionAdapter.setSubscriptions(filterList);
                    recyclerViewP3F3.setAdapter(subscriptionAdapter);
                }

                subscriptionAdapter.setOnItemClickListener(new SubscriptionAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Subscription subscription) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("subscriptionId", subscription.getSubscriptionId());

                        SubscriptionDetailsFragmentPart3 subscriptionDetailsFragment = new SubscriptionDetailsFragmentPart3();
                        subscriptionDetailsFragment.setArguments(bundle);

                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.viewPageMainPart3, subscriptionDetailsFragment)
                                .addToBackStack(null).commit();
                    }
                });
            }
        });
    }

    public void filterListBasedOnCondition(double searchPrice, String searchProduct) {
        List<Subscription> filterList = new ArrayList<>();

        subscriptionViewModel.getAllSubscriptions().observe(getViewLifecycleOwner(), new Observer<List<Subscription>>() {
            @Override
            public void onChanged(List<Subscription> subscriptions) {

                for(Subscription subscription : subscriptions) {
                    double weeklyPrice = subscription.getWeeklyPrice();
                    double monthlyPrice = subscription.getMonthlyPrice();
                    String subscriptionCategory = subscription.getSubscriptionCategory().toLowerCase();

                    if (searchProduct.toLowerCase().equals(subscriptionCategory) &&
                            (searchPrice >= weeklyPrice || searchPrice >= monthlyPrice)) {
                        filterList.add(subscription);
                    }
                }

                SubscriptionAdapter subscriptionAdapter = new SubscriptionAdapter();

                if (filterList.isEmpty()) {
                    subscriptionAdapter.setSubscriptions(subscriptions);
                    recyclerViewP3F3.setAdapter(subscriptionAdapter);

                } else {
                    Toast.makeText(getContext(), "Subscription Found", Toast.LENGTH_SHORT).show();
                    subscriptionAdapter.setSubscriptions(filterList);
                    recyclerViewP3F3.setAdapter(subscriptionAdapter);
                }

                subscriptionAdapter.setOnItemClickListener(new SubscriptionAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Subscription subscription) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("subscriptionId", subscription.getSubscriptionId());

                        SubscriptionDetailsFragmentPart3 subscriptionDetailsFragment = new SubscriptionDetailsFragmentPart3();
                        subscriptionDetailsFragment.setArguments(bundle);

                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.viewPageMainPart3, subscriptionDetailsFragment)
                                .addToBackStack(null).commit();
                    }
                });
            }
        });
    }
}