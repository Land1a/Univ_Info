package com.example.swproject;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity{
    private static final String TAG = "Main_Activity";

    private ImageView ivMenu;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private Button university, foodinfo, university_dep, university_con, kfood, jfood, cfood, all, sfood, wfood, food_de, foodBtn;
    private LinearLayout ucontainer, fcontainer, dep_list, con_list;
    private HorizontalScrollView foodtypebar;
    private View detail_food, university_info;
    private ArrayList<String> restaurantList, dataList, typeList, Dep_name_list, Dep_data_List;
    private ArrayList<Integer> imageList, Dep_image_List;
    private ImageView foodImage, Dep_image, faciImage;
    private TextView restname, restdata, resttype, redb, faciname, facidata;
    private GridLayout food_det;
    public String code, type, name;

    //private GridLayout chaplaincy;
    private NavigationView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //매칭
        ivMenu = findViewById(R.id.menu_button);
        university = findViewById(R.id.university);
        ucontainer = findViewById(R.id.ucontainer);
        fcontainer = findViewById(R.id.fcontainer);
        foodtypebar = findViewById(R.id.foodtypebar);
        foodinfo = findViewById(R.id.foodinfo);
        university_dep = findViewById(R.id.University_Dep);
        university_con = findViewById(R.id.University_Con);
        dep_list = findViewById(R.id.Dep_list);
        con_list = findViewById(R.id.Con_list);

        //chaplaincy=findViewById(R.id.chaplaincy);

        //음식 종류 버튼
        all = findViewById(R.id.all);
        kfood = findViewById(R.id.kfood);
        jfood = findViewById(R.id.jfood);
        cfood = findViewById(R.id.cfood);
        sfood = findViewById(R.id.sfood);
        wfood = findViewById(R.id.wfood);

        //메뉴를 위한 코드들
        drawerLayout = findViewById(R.id.Main_View);
        toolbar = findViewById(R.id.toolbar);
        nav = findViewById(R.id.menu_var);

        //액션바 변경하기(들어갈 수 있는 타입 : Toolbar type
        setSupportActionBar(toolbar);

        ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                if (id == R.id.item_1) {

                    Toast.makeText(getApplicationContext(), "메인화면 입니다.", Toast.LENGTH_SHORT).show();
                }
                else if (item.getItemId() == R.id.item_3) {
                    setContentView(R.layout.activity_main);
                    Intent intent = new Intent(MainActivity.this, MainQ.class);
                    startActivity(intent);
                }
                else if (item.getItemId() == R.id.item_4) {
                    setContentView(R.layout.activity_main);
                    Intent intent = new Intent(MainActivity.this, MainQ.class);
                    startActivity(intent);
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
        //메뉴 끝
        
        //화면 전환을 위한 코드들
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //학교
        university.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ucontainer.setVisibility(View.VISIBLE);
                if (foodtypebar.getVisibility()==View.VISIBLE){
                    foodtypebar.setVisibility(View.GONE);
                }
                if (fcontainer.getVisibility()==View.VISIBLE){
                    fcontainer.setVisibility(View.GONE);
                }
                if (ucontainer.getVisibility()==View.GONE){
                    ucontainer.setVisibility(View.VISIBLE);
                }
            }
        });

        Dep_name_list = new ArrayList<>(); Dep_image_List = new ArrayList<>(); Dep_data_List = new ArrayList<>();
        Dep_name_list.add("행정본부"); Dep_name_list.add("직속기구"); Dep_name_list.add("부속기관");
        Dep_data_List.add("설명"); Dep_data_List.add("설명"); Dep_data_List.add("설명");
        Dep_image_List.add(R.drawable.error); Dep_image_List.add(R.drawable.error); Dep_image_List.add(R.drawable.error);

        university_dep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dep_list.getVisibility()==View.GONE){dep_list.setVisibility(View.VISIBLE);}
                else{dep_list.setVisibility(View.GONE);}    //각종부서 버튼을 누르면 리스트(리니어 레이아웃) 출력/미출력
            }
        });

        university_con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(con_list.getVisibility()==View.GONE){con_list.setVisibility(View.VISIBLE);}
                else{con_list.setVisibility(View.GONE);}    //편의시설 버튼을 누르면 리스트(리니어 레이아웃) 출력/미출력
            }
        });

        //교목처
        /*chaplaincy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_main);
                Intent intent = new Intent(MainActivity.this, University_detail.class);
                startActivity(intent);
            }
        });*/

        restaurantList = new ArrayList<>(); dataList = new ArrayList<>(); typeList = new ArrayList<>(); imageList = new ArrayList<>();

        getrestaurnt();

        //음식 화면
        if(rname !=null ){
            for(int i = 0; i < rname.size(); i++) {
                name = rname.get(i);
                type = rtype.get(i);
                code = rcode.get(i);

                detail_food = inflater.inflate(R.layout.detail_food, fcontainer, false);
                //사진
                foodImage = detail_food.findViewById(R.id.Restaurant_image);
//                foodImage.setImageResource(imageList.get(i));
                //이름
                restname = detail_food.findViewById(R.id.Restaurant_name);
                restname.setText(name);
                //타입
                resttype = detail_food.findViewById(R.id.Restaurant_type);
                switch (rtype.get(i)){
                    case "1": resttype.setText("한식"); break;
                    case "2": resttype.setText("중식"); break;
                    case "3": resttype.setText("일식"); break;
                    case "4": resttype.setText("양식"); break;
                    case "5": resttype.setText("분식"); break;
                }
                //설명
                restdata = detail_food.findViewById(R.id.Restaurant_detail);
                restdata.setText(rinst.get(i));//rinst.get(i));

                Intent intent = new Intent(MainActivity.this, Foodinfo.class);
                intent.putExtra("name", name);
                intent.putExtra("code", code);
                intent.putExtra("type", type);
                foodBtn = detail_food.findViewById(R.id.foodDetailBtn);
                foodBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {
                        startActivity(intent);
                    }
                });
                fcontainer.addView(detail_food);


            }
        }


        //음식
        foodinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (foodtypebar.getVisibility()==View.GONE){
                    foodtypebar.setVisibility(View.VISIBLE);
                    ucontainer.setVisibility(View.GONE);
                }
                if (fcontainer.getVisibility()==View.GONE){
                    fcontainer.setVisibility(View.VISIBLE);
                }
            }
        });

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fcontainer.removeAllViews();
                if(rname !=null ) {
                    for(int i = 0; i < rname.size(); i++) {
                        name = rname.get(i);
                        type = rtype.get(i);
                        code = rcode.get(i);
                        detail_food = inflater.inflate(R.layout.detail_food, fcontainer, false);
                        //사진
                        foodImage = detail_food.findViewById(R.id.Restaurant_image);
                        foodImage.setImageResource(imageList.get(i));
                        //이름
                        restname = detail_food.findViewById(R.id.Restaurant_name);
                        restname.setText(rname.get(i));
                        //타입
                        resttype = detail_food.findViewById(R.id.Restaurant_type);
                        switch (rtype.get(i)){
                            case "1": resttype.setText("한식"); break;
                            case "2": resttype.setText("중식"); break;
                            case "3": resttype.setText("일식"); break;
                            case "4": resttype.setText("양식"); break;
                            case "5": resttype.setText("분식"); break;
                        }
                        //설명
                        restdata = detail_food.findViewById(R.id.Restaurant_detail);
                        restdata.setText(rinst.get(i));//rinst.get(i));
                        Intent intent = new Intent(MainActivity.this, Foodinfo.class);

                        intent.putExtra("name", name);
                        intent.putExtra("code", code);
                        intent.putExtra("type", type);
                        foodBtn = detail_food.findViewById(R.id.foodDetailBtn);
                        foodBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view){
                                startActivity(intent);
                            }
                        });
                        fcontainer.addView(detail_food);
                    }
                }
            }
        });

        kfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fcontainer.removeAllViews();
                if(rname !=null ) {
                    for (int i = 0; i < rname.size(); i++) {
                        if(Integer.parseInt(rtype.get(i))==1) {
                            name = rname.get(i);
                            type = rtype.get(i);
                            code = rcode.get(i);
                            detail_food = inflater.inflate(R.layout.detail_food, fcontainer, false);
                            //사진
                            foodImage = detail_food.findViewById(R.id.Restaurant_image);
                            foodImage.setImageResource(imageList.get(i));
                            //이름
                            restname = detail_food.findViewById(R.id.Restaurant_name);
                            restname.setText(rname.get(i));
                            //타입
                            resttype = detail_food.findViewById(R.id.Restaurant_type);
                            resttype.setText("한식");
                            //설명
                            restdata = detail_food.findViewById(R.id.Restaurant_detail);
                            restdata.setText(rinst.get(i));
                            Intent intent = new Intent(MainActivity.this, Foodinfo.class);
                            intent.putExtra("name", name);
                            intent.putExtra("code", code);
                            intent.putExtra("type", type);
                            foodBtn = detail_food.findViewById(R.id.foodDetailBtn);
                            foodBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view){
                                    startActivity(intent);

                                }
                            });
                            fcontainer.addView(detail_food);
                        }
                    }
                }
            }
        });


        jfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fcontainer.removeAllViews();
                if(rname !=null ) {
                    for (int i = 0; i < rname.size(); i++) {
                        if(Integer.parseInt(rtype.get(i))==3) {
                            name = rname.get(i);
                            type = rtype.get(i);
                            code = rcode.get(i);
                            detail_food = inflater.inflate(R.layout.detail_food, fcontainer, false);
                            //사진
                            foodImage = detail_food.findViewById(R.id.Restaurant_image);
                            foodImage.setImageResource(imageList.get(i));
                            //이름
                            restname = detail_food.findViewById(R.id.Restaurant_name);
                            restname.setText(rname.get(i));
                            //타입
                            resttype = detail_food.findViewById(R.id.Restaurant_type);
                            resttype.setText("일식");
                            //설명
                            /*restdata = detail_food.findViewById(R.id.Restaurant_detail);
                            restdata.setText("  ");//rinst.get(i));*/
                            Intent intent = new Intent(MainActivity.this, Foodinfo.class);

                            intent.putExtra("name", name);
                            intent.putExtra("code", code);
                            intent.putExtra("type", type);
                            foodBtn = detail_food.findViewById(R.id.foodDetailBtn);
                            foodBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view){
                                    startActivity(intent);

                                }
                            });
                            fcontainer.addView(detail_food);
                        }
                    }
                }
            }
        });

        cfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fcontainer.removeAllViews();
                if(rname !=null ) {
                    for (int i = 0; i < rname.size(); i++) {
                        if(Integer.parseInt(rtype.get(i))==2) {
                            name = rname.get(i);
                            type = rtype.get(i);
                            code = rcode.get(i);
                            detail_food = inflater.inflate(R.layout.detail_food, fcontainer, false);
                            //사진
                            foodImage = detail_food.findViewById(R.id.Restaurant_image);
                            foodImage.setImageResource(imageList.get(i));
                            //이름
                            restname = detail_food.findViewById(R.id.Restaurant_name);
                            restname.setText(rname.get(i));
                            //타입
                            resttype = detail_food.findViewById(R.id.Restaurant_type);
                            resttype.setText("중식");
                            //설명
                            /*restdata = detail_food.findViewById(R.id.Restaurant_detail);
                            restdata.setText("  ");//rinst.get(i));*/
                            Intent intent = new Intent(MainActivity.this, Foodinfo.class);

                            intent.putExtra("name", name);
                            intent.putExtra("code", code);
                            intent.putExtra("type", type);
                            foodBtn = detail_food.findViewById(R.id.foodDetailBtn);
                            foodBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view){
                                    startActivity(intent);
                                }
                            });
                            fcontainer.addView(detail_food);
                        }
                    }
                }
            }
        });

        wfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fcontainer.removeAllViews();
                if(rname !=null ) {
                    for (int i = 0; i < rname.size(); i++) {
                        if(Integer.parseInt(rtype.get(i))==4) {
                            name = rname.get(i);
                            type = rtype.get(i);
                            code = rcode.get(i);
                            detail_food = inflater.inflate(R.layout.detail_food, fcontainer, false);
                            //사진
                            foodImage = detail_food.findViewById(R.id.Restaurant_image);
                            foodImage.setImageResource(imageList.get(i));
                            //이름
                            restname = detail_food.findViewById(R.id.Restaurant_name);
                            restname.setText(rname.get(i));
                            //타입
                            resttype = detail_food.findViewById(R.id.Restaurant_type);
                            resttype.setText("양식");
                            //설명
                            /*restdata = detail_food.findViewById(R.id.Restaurant_detail);
                            restdata.setText("  ");//rinst.get(i));*/
                            Intent intent = new Intent(MainActivity.this, Foodinfo.class);

                            intent.putExtra("name", name);
                            intent.putExtra("code", code);
                            intent.putExtra("type", type);
                            foodBtn = detail_food.findViewById(R.id.foodDetailBtn);
                            foodBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view){
                                    startActivity(intent);
                                }
                            });
                            fcontainer.addView(detail_food);
                        }
                    }
                }
            }
        });

        sfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fcontainer.removeAllViews();
                if(rname !=null ) {
                    for (int i = 0; i < rname.size(); i++) {
                        if(Integer.parseInt(rtype.get(i))==5) {
                            name = rname.get(i);
                            type = rtype.get(i);
                            code = rcode.get(i);
                            detail_food = inflater.inflate(R.layout.detail_food, fcontainer, false);
                            //사진
                            foodImage = detail_food.findViewById(R.id.Restaurant_image);
                            foodImage.setImageResource(imageList.get(i));
                            //이름
                            restname = detail_food.findViewById(R.id.Restaurant_name);
                            restname.setText(rname.get(i));
                            //타입
                            resttype = detail_food.findViewById(R.id.Restaurant_type);
                            resttype.setText("분식");
                            //설명
                            /*restdata = detail_food.findViewById(R.id.Restaurant_detail);
                            restdata.setText("  ");//rinst.get(i));*/
                            Intent intent = new Intent(MainActivity.this, Foodinfo.class);

                            intent.putExtra("name", name);
                            intent.putExtra("code", code);
                            intent.putExtra("type", type);
                            foodBtn = detail_food.findViewById(R.id.foodDetailBtn);
                            foodBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view){
                                    startActivity(intent);
                                }
                            });
                            fcontainer.addView(detail_food);
                        }
                    }
                }
            }
        });




        for(int i = 0; i < rname.size(); i++) {
            imageList.add(R.drawable.error);
        }




        getUniversity();    //DB에서 교내정보를 가져온다

        //시설 리스트 출력 및 해당 데이터 전달
        dep_list.removeAllViews();
        if(cName !=null ) {
            for (int i = 0; i < cName.size(); i++) {
                if(Integer.parseInt(cType.get(i))==0) { //각종부서 정보
                    //정보를 출력할 xml(university_info)에 정보를 전달
                    university_info = inflater.inflate(R.layout.university_info, dep_list, false);
                    //부서 이름
                    faciname = university_info.findViewById(R.id.Facility_name);
                    faciname.setText(cName.get(i));
                    //부서 설명
                    facidata = university_info.findViewById(R.id.Facility_detail);
                    String introduction = cIntro.get(i);
                    if(introduction.length() > 120){ facidata.setText(introduction.substring(0, 120) + "..."); }
                    else{ facidata.setText(introduction); } //120자 보다 많으면 자른다.
                    //Intent를 이용하여 university_detail에 데이터 전달
                    Intent intent = new Intent(MainActivity.this, University_detail.class);
                    //key value (json방식)
                    intent.putExtra("name", (String) faciname.getText());   //부서이름
                    intent.putExtra("location", cLoc.get(i));               //부서위치
                    intent.putExtra("locationMap", cLocMap.get(i));         //지도에 사용할 위치
                    intent.putExtra("phoneNumber", cPhone.get(i));          //전화번호
                    intent.putExtra("fax", cFax.get(i));                    //Fax 번호
                    intent.putExtra("email", cEmail.get(i));                //eMail
                    intent.putExtra("introduction", cIntro.get(i));         //부서소개
                    intent.putExtra("url", cUrl.get(i));                    //브라우저 연결을 위한 url
                    university_info.setOnClickListener(new View.OnClickListener() {
                        @Override
                        //university_detail 출력 버튼
                        public void onClick(View view){
                            startActivity(intent);
                        }
                    });
                    //화면에 출력
                    dep_list.addView(university_info);

                }
            }
        }


        con_list.removeAllViews();
        if(cName !=null ) {
            for (int i = 0; i < cName.size(); i++) {
                if(Integer.parseInt(cType.get(i))==1) { //편의시설 정보, 나머지 동일
                    university_info = inflater.inflate(R.layout.university_info, con_list, false);
                    faciname = university_info.findViewById(R.id.Facility_name);
                    faciname.setText(cName.get(i));
                    facidata = university_info.findViewById(R.id.Facility_detail);
                    String introduction = cIntro.get(i);
                    if(introduction.length() > 120){ facidata.setText(introduction.substring(0, 120) + "..."); }
                    else{ facidata.setText(introduction); }
                    Intent intent = new Intent(MainActivity.this, University_detail.class);
                    intent.putExtra("name", (String) faciname.getText());
                    intent.putExtra("location", cLoc.get(i));
                    //StringTokenizer locMap = new StringTokenizer(cLoc.get(i));
                    intent.putExtra("locationMap", cLocMap.get(i));
                    intent.putExtra("phoneNumber", cPhone.get(i));
                    intent.putExtra("fax", cFax.get(i));
                    intent.putExtra("email", cEmail.get(i));
                    intent.putExtra("introduction", cIntro.get(i));
                    intent.putExtra("url", cUrl.get(i));
                    university_info.setOnClickListener(new View.OnClickListener() {
                        @Override
                        //university_detail 출력
                        public void onClick(View view){
                            startActivity(intent);
                        }
                    });
                    con_list.addView(university_info);

                }
            }
        }

    }

    ArrayList<String> rcode= new ArrayList<>();
    ArrayList<String> rname= new ArrayList<>();
    ArrayList<String> rtype= new ArrayList<>();
    ArrayList<String> rinst= new ArrayList<>();

    //DB에서 데이터 받아오기
    public void getrestaurnt() {
        //DB 데이터 가져오는 자바 코드 가져오기
        DBH dbHelper = new DBH(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        //Restaurant라는 이름의 테이블 탐색
        Cursor code = db.rawQuery("SELECT * FROM Restaurant",null);
        Cursor name = db.rawQuery("SELECT * FROM Restaurant",null);
        Cursor typecode = db.rawQuery("SELECT * FROM Restaurant",null);
        Cursor inst = db.rawQuery("SELECT * FROM Restaurant",null);

        while (code.moveToNext()){
            rcode.add(code.getString(0)); //DB 0번째 컬럼 리스트에 추가하기
        }

        while (name.moveToNext()) {
            rname.add(name.getString(1)) ; //DB 1번째 컬럼 리스트에 추가하기
        }
        while (typecode.moveToNext()){
            rtype.add(typecode.getString(2)); //DB 2번째 컬럼 리스트에 추가하기
        }
        while (inst.moveToNext()){
            rinst.add(inst.getString(3)); //DB 3번째 컬럼 리스트에 추가하기
        }
        //아래는 DB 불러오기 종료
        code.close();
        name.close();
        typecode.close();
        inst.close();
        dbHelper.close();
    }


    //시설 정보를 위한 변수
    ArrayList<String> cCode= new ArrayList<>();
    ArrayList<String> cName= new ArrayList<>();
    ArrayList<String> cLoc= new ArrayList<>();
    ArrayList<String> cLocMap= new ArrayList<>();
    ArrayList<String> cPhone= new ArrayList<>();
    ArrayList<String> cEmail= new ArrayList<>();
    ArrayList<String> cFax= new ArrayList<>();
    ArrayList<String> cUrl= new ArrayList<>();
    ArrayList<String> cIntro= new ArrayList<>();
    ArrayList<String> cType= new ArrayList<>();
    public void getUniversity() {
        //DB 데이터 가져오기
        DBH dbHelper = new DBH(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        //CampusInfo라는 이름의 테이블 탐색
        Cursor campusCode = db.rawQuery("SELECT * FROM CampusInfo",null);
        Cursor campusName = db.rawQuery("SELECT * FROM CampusInfo",null);
        Cursor location = db.rawQuery("SELECT * FROM CampusInfo",null);
        Cursor locationMap = db.rawQuery("SELECT * FROM CampusInfo",null);
        Cursor phoneNumber = db.rawQuery("SELECT * FROM CampusInfo",null);
        Cursor email = db.rawQuery("SELECT * FROM CampusInfo",null);
        Cursor fax = db.rawQuery("SELECT * FROM CampusInfo",null);
        Cursor url = db.rawQuery("SELECT * FROM CampusInfo",null);
        Cursor introduction = db.rawQuery("SELECT * FROM CampusInfo",null);
        Cursor campusType = db.rawQuery("SELECT * FROM CampusInfo",null);

        while (campusCode.moveToNext()){
            cCode.add(campusCode.getString(0));     //DB 0번째 컬럼 리스트에 추가하기
        }
        while (campusName.moveToNext()) {
            cName.add(campusName.getString(1));     //DB 1번째 컬럼 리스트에 추가하기
        }
        while (location.moveToNext()){
            cLoc.add(location.getString(2));        //DB 2번째 컬럼 리스트에 추가하기
        }
        while (locationMap.moveToNext()){
            cLocMap.add(locationMap.getString(3));  //DB 3번째 컬럼 리스트에 추가하기
        }
        while (phoneNumber.moveToNext()){
            cPhone.add(phoneNumber.getString(4));   //DB 4번째 컬럼 리스트에 추가하기
        }
        while (email.moveToNext()){
            cEmail.add(email.getString(5));         //DB 5번째 컬럼 리스트에 추가하기
        }
        while (fax.moveToNext()){
            cFax.add(fax.getString(6));             //DB 6번째 컬럼 리스트에 추가하기
        }
        while (url.moveToNext()){
            cUrl.add(url.getString(7));             //DB 7번째 컬럼 리스트에 추가하기
        }
        while (introduction.moveToNext()){
            cIntro.add(introduction.getString(8));  //DB 8번째 컬럼 리스트에 추가하기
        }
        while (campusType.moveToNext()){
            cType.add(campusType.getString(9));     //DB 9번째 컬럼 리스트에 추가하기
        }

        //아래는 DB 불러오기 종료
        campusCode.close();
        campusName.close();
        location.close();
        locationMap.close();
        phoneNumber.close();
        email.close();
        fax.close();
        url.close();
        introduction.close();
        campusType.close();
        dbHelper.close();
    }

}