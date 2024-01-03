package com.example.swproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;

import com.google.android.material.navigation.NavigationView;

import java.util.List;

//부서 정보를 출력하기 위한 화면 제어
public class University_detail extends AppCompatActivity {
    private static final String TAG = "University_detail";

    private Button btnMap, btnCall, btnUrl;
    private Toolbar toolbar;
    private ImageView ivMenu;
    private DrawerLayout drawerLayout;
    private NavigationView nav;
    private TextView faciName, faciIntro, faciEmail, faciFax;
    private String name, location, locationMap, phoneNumber, fax, email, introduction, url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.university_detail);

        //메뉴
        toolbar = findViewById(R.id.toolbar);
        ivMenu = findViewById(R.id.menu_button);
        drawerLayout = findViewById(R.id.dinfo_View);
        nav = findViewById(R.id.menu_var);
        faciName = findViewById(R.id.FaciName);
        faciIntro = findViewById(R.id.faciIntro);
        faciEmail = findViewById(R.id.faciEmail);
        faciFax = findViewById(R.id.faciFax);
        btnMap = findViewById(R.id.btnMap);
        btnCall = findViewById(R.id.btnCall);
        btnUrl = findViewById(R.id.btnUrl);

        //MainActivity에서 데이터를 받아오기 위한 Intent.
        Intent intent = getIntent();
        //key값으로 value값을 가져온다. 같은 이름으로 지정해 놓음
        name = intent.getStringExtra("name");                   //시설이름
        faciName.setText(name);             
        introduction= intent.getStringExtra("introduction");    //부서소개
        faciIntro.setText(introduction);    
        location = intent.getStringExtra("location");           //위치
        btnMap.setText(location);           
        locationMap = intent.getStringExtra("locationMap");     //지도에 전달할 위치정보
        phoneNumber = intent.getStringExtra("phoneNumber");     //전화번호
        btnCall.setText(phoneNumber);
        fax = intent.getStringExtra("fax");                     //Fax 번호
        faciFax.setText("Fax: " + fax);
        email = intent.getStringExtra("email");                 //eMail주소
        faciEmail.setText(email);
        url = intent.getStringExtra("url");                     //자세히 보기 위한 사이트 링크
        
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
                    Intent intent = new Intent(University_detail.this, MainActivity.class);
                    startActivity(intent);
                }
                if (item.getItemId() == R.id.item_3) {
                    setContentView(R.layout.activity_main);
                    Intent intent = new Intent(University_detail.this, MainQ.class);
                    startActivity(intent);
                }
                if (item.getItemId() == R.id.item_4) {
                    setContentView(R.layout.activity_main);
                    Intent intent = new Intent(University_detail.this, MainQ.class);
                    startActivity(intent);
                }
                return false;
            }
        });
        //메뉴 끝

        btnMap.setOnClickListener(new View.OnClickListener() {  //지도에 값을 넘겨 위치를 표시
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

        btnCall.setOnClickListener(new View.OnClickListener() { //전화 걸기
            @Override
            public void onClick(View view) {
                Intent call = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + phoneNumber));
                startActivity(call);
            }
        });

        btnUrl.setOnClickListener(new View.OnClickListener() {  //자세히보기 위한 브라우저 열기
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
    }

}
