package com.example.blood_locator_app.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.example.blood_locator_app.R;

public class HomeActivity extends AppCompatActivity  implements View.OnClickListener {
    ImageButton aplus,aneg,oplus,oneg,abplus,abneg,bplus,bneg,b;
    int apluss,anegg,opluss,onegg,abpluss,abnegg,bpluss,bnegg,bb;

    Boolean check = true , checkaplus = true,checkaneg = true , checkbplus = true,checkbneg = true , checkoplus = true,checkoneg = true ,checkabplus = true,checkabneg = true , checkb = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        aplus = findViewById(R.id.imageButton2);
        aneg = findViewById(R.id.imageButton11);
        oplus = findViewById(R.id.imageButton3);
        oneg = findViewById(R.id.imageButton10);
        abneg = findViewById(R.id.imageButton5);
        bneg = findViewById(R.id.imageButton8);
        abplus = findViewById(R.id.imageButton6);
        bplus = findViewById(R.id.imageButton4);
        b = findViewById(R.id.imageButton7);

        bneg.setOnClickListener(this);
        aplus.setOnClickListener(this);
        b.setOnClickListener(this);
        oneg.setOnClickListener(this);
        oplus.setOnClickListener(this);
        abplus.setOnClickListener(this);
        abneg.setOnClickListener(this);
        bplus.setOnClickListener(this);
        aneg.setOnClickListener(this);
    }

  @Override
    public void onClick(View v) {

        aplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               checkaplus =false;
                if (check && checkaplus==false && checkaneg==true && checkabneg ==true && checkbneg == true && checkbplus == true && checkoneg ==true && checkb == true && checkoplus == true&&checkabplus ==true) {
                    aplus.setImageResource(R.drawable.acolor);
                    check = false;
                    apluss = 1;
                }
                else {
                    aplus.setImageResource(R.drawable.a);
                    check = true;
              checkaplus =true;
                    apluss = 0;
                }

            }
        });

      aneg.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
             checkaneg = false;
              if (check && checkaplus==true && checkaneg==false && checkabneg ==true && checkbneg == true && checkbplus == true && checkoneg ==true && checkb == true && checkoplus == true &&checkabplus ==true) {
                  aneg.setImageResource(R.drawable.anegativecolor);
                  check = false;
                  anegg = 1;
              }
              else {
                  aneg.setImageResource(R.drawable.anegative);
                  check = true;
                  checkaneg = true;
                  anegg = 0;
              }

          }
      });

      b.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            checkb = false;
              if (check&& checkaplus==true && checkaneg==true && checkabneg ==true && checkbneg == true && checkbplus == true && checkoneg ==true && checkb == false && checkoplus == true&&checkabplus ==true) {
                  b.setImageResource(R.drawable.bcolor);
                  check = false;
              bb =1;
              }
              else {
                  b.setImageResource(R.drawable.b);
                  checkb = true;
                  check = true;
                  bb =0;
              }

          }
      });

      bneg.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            checkbneg = false;
              if (check && checkaplus==true && checkaneg==true && checkabneg ==true && checkbneg == false && checkbplus == true && checkoneg ==true && checkb == true && checkoplus == true&&checkabplus ==true) {
                  bneg.setImageResource(R.drawable.bnegativecolor);
                  check = false;
                  bnegg = 1;
              }
              else {
                  bneg.setImageResource(R.drawable.bnegative);
                  checkbneg = true;
                  check = true;
                  bnegg = 0;
              }

          }
      });

      oplus.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              checkoplus = false;
              if (check && checkaplus==true && checkaneg==true && checkabneg ==true && checkbneg == true && checkbplus == true && checkoneg ==true && checkb == true && checkoplus == false &&checkabplus ==true) {
                  oplus.setImageResource(R.drawable.opluscolor);
                  check = false;
              opluss =1;
              }
              else {
                  oplus.setImageResource(R.drawable.oplus);
                  checkoplus = true;
                  check = true;
                  opluss =0;
              }

          }
      });


      oneg.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
             checkoneg = false;
              if (check && checkaplus==true && checkaneg==true && checkabneg ==true && checkbneg == true && checkbplus == true && checkoneg ==false && checkb == true && checkoplus == true&&checkabplus ==true) {
                  oneg.setImageResource(R.drawable.onegativecolor);
                  check = false;
                  onegg = 1;
              }
              else {
                  oneg.setImageResource(R.drawable.onegative);
                  check = true;
                  checkoneg = true;
                  onegg = 0;
              }

          }
      });


      bplus.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            checkbplus = false;
              if (check && checkaplus==true && checkaneg==true && checkabneg ==true && checkbneg == true && checkbplus == false && checkoneg ==true && checkb == true && checkoplus == true &&checkabplus ==true) {
                  bplus.setImageResource(R.drawable.bpluscolor);
                  check = false;
              bpluss =1;
              }
              else {
                  bplus.setImageResource(R.drawable.bplus);
                  checkbplus = true;
                  check = true;
             bpluss =0;
              }

          }
      });



      abplus.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              checkabplus = false;
              if (check && checkaplus==true && checkaneg==true && checkabneg ==true && checkbneg == true && checkbplus == true && checkoneg ==true && checkb == true && checkoplus == true &&checkabplus ==false) {
                  abplus.setImageResource(R.drawable.abpluscolor);
                  check = false;
                  abpluss=1;
              }
              else {
                  abplus.setImageResource(R.drawable.abplus);
                  check = true;
                  checkabplus = true;
                  abpluss=0;
              }

          }
      });

      abneg.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              checkabneg =false;
              if (check && checkaplus==true && checkaneg==true && checkabneg ==false && checkbneg == true && checkbplus == true && checkoneg ==true && checkb == true && checkoplus == true&&checkabplus ==true) {
                  abneg.setImageResource(R.drawable.abnegitivecolor);
                  check = false;
                  abnegg=1;
              }
              else {
                  abneg.setImageResource(R.drawable.abnegitive);
                  check = true;
                  checkabneg =true;
              abnegg = 0;
              }

          }
      });
    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_error_outline_black_24dp)
                .setTitle("Closing Activity")
                .setMessage("Are you sure you want to close this Application?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }


    public void search(View view) {
        Intent intent = new Intent(this, ListandMapActivity.class);
       Bundle bundle = new Bundle();
       bundle.putInt("aplus",apluss);
       bundle.putInt("aneg",anegg);
        bundle.putInt("abplus",abpluss);
        bundle.putInt("abneg",abnegg);
        bundle.putInt("bplus",bpluss);
        bundle.putInt("bneg",bnegg);
        bundle.putInt("b",bb);
        bundle.putInt("oplus",opluss);
        bundle.putInt("oneg",onegg);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
       intent.putExtra("bun",bundle);
        startActivity(intent);
    }
}