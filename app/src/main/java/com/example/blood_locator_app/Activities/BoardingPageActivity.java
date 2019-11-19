package com.example.blood_locator_app.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.blood_locator_app.R;
import com.ramotion.paperonboarding.PaperOnboardingEngine;
import com.ramotion.paperonboarding.PaperOnboardingFragment;
import com.ramotion.paperonboarding.PaperOnboardingPage;
import com.ramotion.paperonboarding.listeners.PaperOnboardingOnRightOutListener;

import java.util.ArrayList;

public class BoardingPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boarding_page);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        PaperOnboardingPage page = new PaperOnboardingPage("DONATE BLOOD","Donate Blood , Donate Life", Color.parseColor("#E7E7E7"),R.drawable.ok1,R.drawable.ok1);
        PaperOnboardingPage page1 = new PaperOnboardingPage("HELP WHO NEEDS","A life may depend on a gesture from you, a bottle of Blood.!", Color.parseColor("#BF1F2D"),R.drawable.ok2,R.drawable.ok2);
        PaperOnboardingPage page2 = new PaperOnboardingPage("REQUEST BLOOD","A bottle of blood saved my life, was it yours.?", Color.parseColor("#E7E7E7"),R.drawable.ok3,R.drawable.ok3);
        ArrayList<PaperOnboardingPage> arrayList = new ArrayList<>();
        arrayList.add(page);
        arrayList.add(page1);
        arrayList.add(page2);
        PaperOnboardingFragment onboardingFragment = PaperOnboardingFragment.newInstance(arrayList);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.con,onboardingFragment);
        ft.commit();

        onboardingFragment.setOnRightOutListener(new PaperOnboardingOnRightOutListener() {
            @Override
            public void onRightOut() {
                Intent intent = new Intent(BoardingPageActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
