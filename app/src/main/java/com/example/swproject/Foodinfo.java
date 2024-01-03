package com.example.swproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class Foodinfo extends AppCompatActivity {
    private static final String TAG = "Foodinfo";

    private Button btnMap, btnCall, btnUrl;
    private Toolbar toolbar;
    private ImageView ivMenu, foodImage;
    private DrawerLayout drawerLayout;
    private NavigationView nav;
    private TextView rename,retype, food_name, food_price;
    private String name, type, price, code, recode;
    private LinearLayout foodmenuco;
    private View food_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_info);


        //메뉴
        toolbar = findViewById(R.id.toolbar);
        ivMenu = findViewById(R.id.menu_button);
        drawerLayout = findViewById(R.id.foodinfoview);
        nav = findViewById(R.id.menu_var);
        rename = findViewById(R.id.reName);
        retype = findViewById(R.id.reType);
        btnMap = findViewById(R.id.reMap);
        btnCall = findViewById(R.id.reCall);
        foodmenuco = findViewById(R.id.foodmenuco);

        //데이터를 가져오기 위한 Intent
        Intent intent = getIntent();

        //key값으로 value값을 가져온다.
        name = intent.getStringExtra("name");
        rename.setText(name);
        type= intent.getStringExtra("type");
        switch (type){
            case "1": retype.setText("한식"); break;
            case "2": retype.setText("중식"); break;
            case "3": retype.setText("일식"); break;
            case "4": retype.setText("양식"); break;
            case "5": retype.setText("분식"); break;
        }
        code = intent.getStringExtra("code");

        /*
        location = intent.getStringExtra("location");
        btnMap.setText(location);
        locationMap = intent.getStringExtra("locationMap");
        phoneNumber = intent.getStringExtra("phoneNumber");
        btnCall.setText(phoneNumber);
        url = intent.getStringExtra("url");

         */

        //메뉴를 위한 코드들
        //액션바 변경하기(들어갈 수 있는 타입 : Toolbar type
        setSupportActionBar(toolbar);

        ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: 클릭됨");
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.item_1) {
                    setContentView(R.layout.activity_main);
                    Intent intent = new Intent(Foodinfo.this, MainActivity.class);
                    startActivity(intent);
                }
                if (item.getItemId() == R.id.item_3) {
                    setContentView(R.layout.activity_main);
                    Intent intent = new Intent(Foodinfo.this, MainQ.class);
                    startActivity(intent);
                }
                if (item.getItemId() == R.id.item_4) {
                    setContentView(R.layout.activity_main);
                    Intent intent = new Intent(Foodinfo.this, MainQ.class);
                    startActivity(intent);
                }
                return false;
            }
        });
        //메뉴 끝
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        getrestaurnt();
        if(rname !=null ){
            for(int i = 0; i < rname.size(); i++) {
                name = rname.get(i);
                price = rprice.get(i);
                recode = rcode.get(i);
                code = intent.getStringExtra("code");
                if (Integer.parseInt(code)==Integer.parseInt(recode)) {
                    food_menu = inflater.inflate(R.layout.food_menu, foodmenuco, false);
                    //사진
                    foodImage = food_menu.findViewById(R.id.Restaurant_image);
                    //foodImage.setImageResource(imageList.get(i));
                    //이름
                    food_name = food_menu.findViewById(R.id.food_name);
                    food_name.setText(name);
                    //타입
                    food_price = food_menu.findViewById(R.id.food_price);
                    food_price.setText(price);
                    foodmenuco.addView(food_menu);
                }
            }
        }

        /*
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri location = Uri.parse("geo:0.0?q=삼육대학교 " + locationMap);
                Intent map = new Intent(Intent.ACTION_VIEW, location);

                //인텐트를 수신할 앱이 있는지 확인
                PackageManager packageManager = getPackageManager();
                List<ResolveInfo> activty = packageManager.queryIntentActivities(map, 0);
                boolean existIntent = activty.size() > 0;

                //인텐트를 처리할 앱 실행
                if(existIntent){
                    startActivity(map);
                }
            }
        });

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent call = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + phoneNumber));
                startActivity(call);
            }
        });

        btnUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });

         */

    }
    ArrayList<String> rname= new ArrayList<>();
    ArrayList<String> rprice= new ArrayList<>();
    ArrayList<String> rcode= new ArrayList<>();

    public void getrestaurnt() {
        //DB 데이터 가져오는 자바 코드 가져오기
        DBH dbHelper = new DBH(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        //Restaurant라는 이름의 테이블 탐색
        Cursor name = db.rawQuery("SELECT * FROM RestaurantMenu",null);
        Cursor typecode = db.rawQuery("SELECT * FROM RestaurantMenu",null);
        Cursor code = db.rawQuery("SELECT * FROM RestaurantMenu",null);


        while (name.moveToNext()) {
            rname.add(name.getString(1)) ; //DB 1번째 컬럼 리스트에 추가하기
        }
        while (typecode.moveToNext()){
            rprice.add(typecode.getString(2)); //DB 2번째 컬럼 리스트에 추가하기
        }
        while (code.moveToNext()){
            rcode.add(code.getString(4)); //DB 3번째 컬럼 리스트에 추가하기
        }

        //아래는 DB 불러오기 종료
        name.close();
        typecode.close();
        code.close();
        dbHelper.close();
    }

}
