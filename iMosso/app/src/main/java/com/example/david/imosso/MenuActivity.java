package com.example.david.imosso;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {
    private int contentView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //SIDEBAR TRANSPARENT
        Window g = getWindow();
        g.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.TYPE_STATUS_BAR);
        contentView = R.layout.activity_menu;
        //BUTTON CHANGE VIEW ON CLIC
        Button BTN_Guia=(Button)findViewById(R.id.BTN_Guia);
        BTN_Guia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MenuActivity.this,
                        Pdf_Guide.class);
                startActivity(myIntent);
            }
        });
        Button BTN_Test=(Button)findViewById(R.id.BTN_Test);
        BTN_Test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MenuActivity.this,
                        TestMenu.class);
                startActivity(myIntent);
            }
        });
    }
}


