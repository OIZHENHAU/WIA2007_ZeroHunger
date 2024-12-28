package com.example.wia2007_zerohunger.Part3;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.wia2007_zerohunger.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivityPart3 extends AppCompatActivity {

    TabLayout mainTabLayoutPart3;
    ViewPager2 viewPageMainPart3;
    ViewPageAdapter viewPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_part3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mainTabLayoutPart3 = findViewById(R.id.mainTabLayoutPart3);

        viewPageMainPart3 = findViewById(R.id.viewPageMainPart3);
        viewPageAdapter = new ViewPageAdapter(this);
        viewPageMainPart3.setAdapter(viewPageAdapter);

        mainTabLayoutPart3.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPageMainPart3.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPageMainPart3.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mainTabLayoutPart3.getTabAt(position).select();
            }
        });
    }
}