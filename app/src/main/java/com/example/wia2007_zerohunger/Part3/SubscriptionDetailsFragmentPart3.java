package com.example.wia2007_zerohunger.Part3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wia2007_zerohunger.Part3.SubscriptionDatabase.Subscription;
import com.example.wia2007_zerohunger.Part3.SubscriptionDatabase.SubscriptionViewModel;
import com.example.wia2007_zerohunger.R;

import java.util.List;


public class SubscriptionDetailsFragmentPart3 extends Fragment {

    ImageView imageViewSubscriptionDetailsP3F3;
    TextView textViewSubscriptionNameDetailsP3F3, textViewLocationDetailsP3F3, textViewCategoryDetailsP3F3;
    TextView textViewWeeklyPriceDetailsP3F3, textViewMonthlyPriceDetailsP3F3;
    Button buttonSubscriptionDetailsP3F3, buttonMySubscriptionDetailsP3F3;
    Button buttonWeeklyPriceDetailsP3F3, buttonMonthlyPriceDetailsP3F3;
    Button backButtonSubscriptionDetailsP3F3, subscribeButtonSubscriptionDetailsP3F3;
    Button prevButton;
    TextView totalAmountSubscriptionDetailsP3F3;

    SubscriptionViewModel subscriptionViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_subscription_details_part3, container, false);

        buttonSubscriptionDetailsP3F3 = view.findViewById(R.id.buttonSubscriptionDetailsP3F3);
        buttonMySubscriptionDetailsP3F3 = view.findViewById(R.id.buttonMySubscriptionDetailsP3F3);

        imageViewSubscriptionDetailsP3F3 = view.findViewById(R.id.imageViewSubscriptionDetailsP3F3);
        textViewSubscriptionNameDetailsP3F3 = view.findViewById(R.id.textViewSubscriptionNameDetailsP3F3);
        textViewLocationDetailsP3F3 = view.findViewById(R.id.textViewLocationDetailsP3F3);
        textViewCategoryDetailsP3F3 = view.findViewById(R.id.textViewCategoryDetailsP3F3);
        textViewWeeklyPriceDetailsP3F3 = view.findViewById(R.id.textViewWeeklyPriceDetailsP3F3);
        textViewMonthlyPriceDetailsP3F3 = view.findViewById(R.id.textViewMonthlyPriceDetailsP3F3);

        buttonWeeklyPriceDetailsP3F3 = view.findViewById(R.id.buttonWeeklyPriceDetailsP3F3);
        buttonMonthlyPriceDetailsP3F3 = view.findViewById(R.id.buttonMonthlyPriceDetailsP3F3);

        totalAmountSubscriptionDetailsP3F3 = view.findViewById(R.id.totalAmountSubscriptionDetailsP3F3);

        backButtonSubscriptionDetailsP3F3 = view.findViewById(R.id.backButtonSubscriptionDetailsP3F3);
        subscribeButtonSubscriptionDetailsP3F3 = view.findViewById(R.id.subscribeButtonSubscriptionDetailsP3F3);

        Bundle bundle = getArguments();
        int subscriptionID = bundle.getInt("subscriptionId");
        Log.d("subscriptionID", String.valueOf(subscriptionID));

        subscriptionViewModel = new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())
                .create(SubscriptionViewModel.class);

        subscriptionViewModel.getAllSubscriptions().observe(getViewLifecycleOwner(), new Observer<List<Subscription>>() {
            @Override
            public void onChanged(List<Subscription> subscriptions) {
                for (Subscription subscription : subscriptions) {
                    if (subscription.getSubscriptionId() == subscriptionID) {

                        int imageID = subscription.getSubscriptionImageId();

                        setImageID(imageID);

                        textViewSubscriptionNameDetailsP3F3.setText(subscription.getSubscriptionName());
                        textViewLocationDetailsP3F3.setText(subscription.getSubscriptionLocation());
                        textViewCategoryDetailsP3F3.setText(subscription.getSubscriptionCategory());

                        textViewWeeklyPriceDetailsP3F3.setText(String.valueOf(subscription.getWeeklyPrice()));
                        textViewMonthlyPriceDetailsP3F3.setText(String.valueOf(subscription.getMonthlyPrice()));

                    }

                }
            }
        });

        buttonWeeklyPriceDetailsP3F3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonWeeklyPriceDetailsP3F3.setBackgroundColor(getResources().getColor(R.color.green));
                buttonWeeklyPriceDetailsP3F3.setTextColor(getResources().getColor(R.color.white));

                if (prevButton != null) {
                    prevButton.setBackgroundColor(getResources().getColor(R.color.light_grey));
                    prevButton.setTextColor(getResources().getColor(R.color.black));
                }

                totalAmountSubscriptionDetailsP3F3.setText(textViewWeeklyPriceDetailsP3F3.getText());

                prevButton = buttonWeeklyPriceDetailsP3F3;
            }
        });

        buttonMonthlyPriceDetailsP3F3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonMonthlyPriceDetailsP3F3.setBackgroundColor(getResources().getColor(R.color.green));
                buttonMonthlyPriceDetailsP3F3.setTextColor(getResources().getColor(R.color.white));

                if (prevButton != null) {
                    prevButton.setBackgroundColor(getResources().getColor(R.color.light_grey));
                    prevButton.setTextColor(getResources().getColor(R.color.black));
                }

                totalAmountSubscriptionDetailsP3F3.setText(textViewMonthlyPriceDetailsP3F3.getText());

                prevButton = buttonMonthlyPriceDetailsP3F3;

            }
        });

        backButtonSubscriptionDetailsP3F3.setOnClickListener(new View.OnClickListener() {
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

    public void setImageID(int imageID) {
        if (imageID == 1) {
            imageViewSubscriptionDetailsP3F3.setImageResource(R.drawable.cabbage);

        } else if (imageID == 2) {
            imageViewSubscriptionDetailsP3F3.setImageResource(R.drawable.kangkung);

        } else if (imageID == 3) {
            imageViewSubscriptionDetailsP3F3.setImageResource(R.drawable.carrot);

        } else if (imageID == 4) {
            imageViewSubscriptionDetailsP3F3.setImageResource(R.drawable.sellbanana);

        } else {
            imageViewSubscriptionDetailsP3F3.setImageResource(R.drawable.milk);
        }
    }
}