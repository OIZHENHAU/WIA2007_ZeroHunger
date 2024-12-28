package com.example.wia2007_zerohunger.Part3;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPageAdapter extends FragmentStateAdapter {

    public ViewPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new ConnectionFragmentPart3();
            case 1:
                return new ReservationFragmentPart3();
            case 2:
                return new SubscriptionFragmentPart3();
            default:
                return new ConnectionFragmentPart3();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
