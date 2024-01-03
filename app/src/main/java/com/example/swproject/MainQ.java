package com.example.swproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Constraints;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainQ extends AppCompatActivity {

    Button testbtn;
    Button yes;
    Button no;


    ImageView imageView1,imageView2;
    TextView questionView,textView_test;
    String questionString;
    ConstraintLayout maincontent;
    Integer QuestionCount = 1;
    ArrayList<String> testDataSet= new ArrayList<>();
    List<String> firstQuestion;
    //ConstraintLayout main;

    String catagoryFirst,categorySecond,categoryThird;

    // 여기 선언부 추가되었습니다! 12월 4일

    List<String> lessonQuestionList; // 수업
    ArrayList<String> testDataSet2= new ArrayList<>();
    List<String> QuestionList;
    String query;
    Button returnAkiBtn;

    //선언부 추가입니다. 12월 11일
    EditText searchedit;
    ImageView search_btn, ivMenu;
    private DrawerLayout drawerLayout, main;
    private Toolbar toolbar;
    Button foodrecommendTest_Btn;
    ImageView yangsik,hansik,jungsik,ilsik,bunsik;
    ConstraintLayout foodrecommendxml;
    List<String> restaurant;
    private NavigationView nav;
    private static final String TAG = "MainQ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager((Context) this);

        toolbar = findViewById(R.id.toolbar);
        ivMenu = findViewById(R.id.menu_button);
        drawerLayout = findViewById(R.id.maind);
        nav = findViewById(R.id.menu_var);
        toolbar = findViewById(R.id.toolbar);

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
                    Intent intent = new Intent(MainQ.this, MainActivity.class);
                    startActivity(intent);
                }
                if (item.getItemId() == R.id.item_3) {
                    setContentView(R.layout.activity_main);
                    Intent intent = new Intent(MainQ.this, MainQ.class);
                    startActivity(intent);
                }
                if (item.getItemId() == R.id.item_4) {
                    setContentView(R.layout.activity_main);
                    Intent intent = new Intent(MainQ.this, MainQ.class);
                    startActivity(intent);
                }
                return false;
            }
        });
        //메뉴 끝




        //뷰 아이디 연결 코드
        questionView = findViewById(R.id.question);
        yes = findViewById(R.id.yesbtn);
        no = findViewById(R.id.nobtn);

        testbtn =findViewById(R.id.testbtn);
        maincontent = findViewById(R.id.mainactivity);
        main = findViewById(R.id.maind);


        // 아키네이터 알고리즘 클래스 연결 코드
        akinatterAlgorithm aki = new akinatterAlgorithm();
        firstQuestion = aki.newfirstQuestionList();


        //추가입니다 12월 11일 ----------------표시까지요!
        searchedit = findViewById(R.id.editTextSerch);
        search_btn = findViewById(R.id.search_btn);

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchstring = "SELECT * FROM Question where Question LIKE '%"+searchedit.getText().toString()+"%'";
                maincontent = findViewById(R.id.mainactivity);
                maincontent.setVisibility(View.GONE);
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View li = inflater.inflate(R.layout.li,null);
                main.addView(li);
                RecyclerView recyclerView =findViewById(R.id.arecyclerView);
                recyclerView.setLayoutManager(linearLayoutManager);
                returnAkiBtn = findViewById(R.id.returnBtn);
                QuestiongetVal(searchstring);
                CustomAdapter customAdapter = new CustomAdapter(testDataSet,testDataSet2);
                returnAkiBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        main.removeView(li);
                        maincontent.setVisibility(View.VISIBLE);
                        recyclerView.setLayoutManager(null);
                        customAdapter.removeAllItems();
                        firstQuestion = aki.newfirstQuestionList();
                        questionString =aki.returnQuestion(firstQuestion);
                        questionView.setText(questionString);
                        QuestionCount = 1;

                    }
                });

                recyclerView.setAdapter(customAdapter);
            }
        });

        //여기서 부턴 음식추천관련 추가입니다

        foodrecommendxml = findViewById(R.id.foodrecommendxml);



        //요건 이미지 추천 임시로 뛰우는 버튼입니다만 안에 코드들은 실제로 써야합니다 나중에 음식 추천 뛰울때 넣어주세용!

        foodrecommendTest_Btn = findViewById(R.id.test_foodxml);

        foodrecommendTest_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maincontent = findViewById(R.id.mainactivity);
                maincontent.setVisibility(View.GONE);
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View FoodrecommendView = inflater.inflate(R.layout.foodrecommend,null);
                main.addView(FoodrecommendView);


                hansik = findViewById(R.id.hansik);
                hansik.setImageResource(R.drawable.hansik);
                // xml 짤때 분식이 있는줄 알고 짜버렸어요.... 그냥 그대로 복붙하시면 됩니다.!
                ilsik = findViewById(R.id.bunsik);
                ilsik.setImageResource(R.drawable.ilsik);
                yangsik = findViewById(R.id.yangsik);
                yangsik.setImageResource(R.drawable.yangsik);
                jungsik = findViewById(R.id.junsik);
                jungsik.setImageResource(R.drawable.jungsik);

                hansik.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String query ="SELECT * FROM Restaurant where RestaurantTypeCode = '1'";
                        FoodQuestiongetVal(query);
                        Random random = new Random();
                        int randomIndex = random.nextInt(restaurant.size());

                        String requesttion = restaurant.get(randomIndex);
                        AlertDialog.Builder dialog = new AlertDialog.Builder(MainQ.this);
                        dialog.setTitle("추천하는 음식점은?");
                        dialog.setMessage(requesttion+"입니다!");
                        dialog.setPositiveButton("확인",null);
                        dialog.show();

                    }
                });

                yangsik.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String query ="SELECT * FROM Restaurant where RestaurantTypeCode = '4'";
                        FoodQuestiongetVal(query);
                        Random random = new Random();
                        int randomIndex = random.nextInt(restaurant.size());

                        String requesttion = restaurant.get(randomIndex);
                        AlertDialog.Builder dialog = new AlertDialog.Builder(MainQ.this);
                        dialog.setTitle("추천하는 음식점은?");
                        dialog.setMessage(requesttion+"입니다!");
                        dialog.setPositiveButton("확인",null);
                        dialog.show();

                    }
                });
                jungsik.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String query ="SELECT * FROM Restaurant where RestaurantTypeCode = '2'";
                        FoodQuestiongetVal(query);
                        Random random = new Random();
                        int randomIndex = random.nextInt(restaurant.size());

                        String requesttion = restaurant.get(randomIndex);
                        AlertDialog.Builder dialog = new AlertDialog.Builder(MainQ.this);
                        dialog.setTitle("추천하는 음식점은?");
                        dialog.setMessage(requesttion+"입니다!");
                        dialog.setPositiveButton("확인",null);
                        dialog.show();

                    }
                });
                ilsik.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String query ="SELECT * FROM Restaurant where RestaurantTypeCode = '3'";
                        FoodQuestiongetVal(query);
                        Random random = new Random();
                        int randomIndex = random.nextInt(restaurant.size());

                        String requesttion = restaurant.get(randomIndex);
                        AlertDialog.Builder dialog = new AlertDialog.Builder(MainQ.this);
                        dialog.setTitle("추천하는 음식점은?");
                        dialog.setMessage(requesttion+"입니다!");
                        dialog.setPositiveButton("확인",null);
                        dialog.show();

                    }
                });











            }
        });






        //----------------------------------여기까지 추가입니다






        // 첫 질문 표시 코드

        questionString =aki.returnQuestion(firstQuestion);
        questionView.setText(questionString);



        // 버튼부분은 많이 추가되었어요! 복붙 추천드립니다...안되면 바로 불러주세요...
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (QuestionCount == 1){
                    catagoryFirst = aki.yesbtn(questionString);
                    //questionView.setText(catagoryFirst);
                    QuestionCount++;
                    if(catagoryFirst == "학사"){

                        QuestionList = aki.newAcademicQuestionList();
                        questionString = aki.returnQuestion(QuestionList);
                        questionView.setText(questionString);

                    } else if (catagoryFirst == "이용시간") {


                        QuestionList = aki.newHoursOfUseQuestionList();
                        questionString = aki.returnQuestion(QuestionList);
                        questionView.setText(questionString);

                    }
                    //이후 두번째 카테고리 질문들 카테고리 2번으로 끝나는건 바로 끝으로 표시하게 했습니다. 시간있으면 클래스화 시키는데 일단 그냥 컨트롤 c v 했습니다...
                    // 3개 짜리는 카운트 3로! 
                } else if (QuestionCount ==2) {
                    categorySecond = aki.yesbtn(questionString);
                    QuestionCount++;
                    if (catagoryFirst == "학사"&&categorySecond == "수강신청"){

                        QuestionList = aki.newEnrolmentQuestionList();
                        questionString = aki.returnQuestion(QuestionList);
                        questionView.setText(questionString);
                        
                    } else if (catagoryFirst == "학사"&&categorySecond == "수업") {
                        lessonQuestionList = aki.newlessonQuestionList();
                        questionString = aki.returnQuestion(lessonQuestionList);
                        questionView.setText(questionString);
                        
                    } else if (catagoryFirst == "학사"&&categorySecond == "학적") {

                        QuestionList = aki.newrecordQuestionList();
                        questionString = aki.returnQuestion(QuestionList);
                        questionView.setText(questionString);
                        
                    } else if (catagoryFirst == "학사"&&categorySecond == "성적") {
                        query = query2();
                        maincontent = findViewById(R.id.mainactivity);
                        maincontent.setVisibility(View.GONE);
                        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View li = inflater.inflate(R.layout.li,null);
                        main.addView(li);
                        RecyclerView recyclerView =findViewById(R.id.arecyclerView);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        returnAkiBtn = findViewById(R.id.returnBtn);
                        QuestiongetVal(query);
                        CustomAdapter customAdapter = new CustomAdapter(testDataSet,testDataSet2);
                        returnAkiBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                main.removeView(li);
                                maincontent.setVisibility(View.VISIBLE);
                                recyclerView.setLayoutManager(null);
                                customAdapter.removeAllItems();
                                firstQuestion = aki.newfirstQuestionList();
                                questionString =aki.returnQuestion(firstQuestion);
                                questionView.setText(questionString);
                                QuestionCount = 1;

                            }
                        });

                        recyclerView.setAdapter(customAdapter);            
                    } else if (catagoryFirst == "학사"&&categorySecond == "졸업") {
                        query = query2();
                        maincontent = findViewById(R.id.mainactivity);
                        maincontent.setVisibility(View.GONE);
                        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View li = inflater.inflate(R.layout.li,null);
                        main.addView(li);
                        RecyclerView recyclerView =findViewById(R.id.arecyclerView);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        returnAkiBtn = findViewById(R.id.returnBtn);
                        QuestiongetVal(query);
                        CustomAdapter customAdapter = new CustomAdapter(testDataSet,testDataSet2);
                        returnAkiBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                main.removeView(li);
                                maincontent.setVisibility(View.VISIBLE);
                                recyclerView.setLayoutManager(null);
                                customAdapter.removeAllItems();
                                firstQuestion = aki.newfirstQuestionList();
                                questionString =aki.returnQuestion(firstQuestion);
                                questionView.setText(questionString);
                                QuestionCount = 1;

                            }
                        });

                        recyclerView.setAdapter(customAdapter);
                    } else if (catagoryFirst == "학사"&&categorySecond == "등록") {

                        QuestionList = aki.newregistrationQuestionList();
                        questionString = aki.returnQuestion(QuestionList);
                        questionView.setText(questionString);
                    } else if (catagoryFirst == "학사"&&categorySecond == "장학") {
                        query = query2();
                        maincontent = findViewById(R.id.mainactivity);
                        maincontent.setVisibility(View.GONE);
                        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View li = inflater.inflate(R.layout.li,null);
                        main.addView(li);
                        RecyclerView recyclerView =findViewById(R.id.arecyclerView);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        returnAkiBtn = findViewById(R.id.returnBtn);
                        QuestiongetVal(query);
                        CustomAdapter customAdapter = new CustomAdapter(testDataSet,testDataSet2);
                        returnAkiBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                main.removeView(li);
                                maincontent.setVisibility(View.VISIBLE);
                                recyclerView.setLayoutManager(null);
                                customAdapter.removeAllItems();
                                firstQuestion = aki.newfirstQuestionList();
                                questionString =aki.returnQuestion(firstQuestion);
                                questionView.setText(questionString);
                                QuestionCount = 1;

                            }
                        });
                        recyclerView.setAdapter(customAdapter);
                    } else if (catagoryFirst == "이용시간"&&categorySecond == "이용시간") {
                        query = query2();
                        maincontent = findViewById(R.id.mainactivity);
                        maincontent.setVisibility(View.GONE);
                        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View li = inflater.inflate(R.layout.li,null);
                        main.addView(li);
                        RecyclerView recyclerView =findViewById(R.id.arecyclerView);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        returnAkiBtn = findViewById(R.id.returnBtn);
                        QuestiongetVal(query);
                        CustomAdapter customAdapter = new CustomAdapter(testDataSet,testDataSet2);
                        returnAkiBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                main.removeView(li);
                                maincontent.setVisibility(View.VISIBLE);
                                recyclerView.setLayoutManager(null);
                                customAdapter.removeAllItems();
                                firstQuestion = aki.newfirstQuestionList();
                                questionString =aki.returnQuestion(firstQuestion);
                                questionView.setText(questionString);
                                QuestionCount = 1;

                            }
                        });
                        recyclerView.setAdapter(customAdapter);

                    }else if (catagoryFirst == "이용시간"&&categorySecond == "위치") {
                        query = query2();
                        maincontent = findViewById(R.id.mainactivity);
                        maincontent.setVisibility(View.GONE);
                        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View li = inflater.inflate(R.layout.li,null);
                        main.addView(li);
                        RecyclerView recyclerView =findViewById(R.id.arecyclerView);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        returnAkiBtn = findViewById(R.id.returnBtn);
                        QuestiongetVal(query);
                        CustomAdapter customAdapter = new CustomAdapter(testDataSet,testDataSet2);
                        returnAkiBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                main.removeView(li);
                                maincontent.setVisibility(View.VISIBLE);
                                recyclerView.setLayoutManager(null);
                                customAdapter.removeAllItems();
                                firstQuestion = aki.newfirstQuestionList();
                                questionString =aki.returnQuestion(firstQuestion);
                                questionView.setText(questionString);
                                QuestionCount = 1;

                            }
                        });
                        recyclerView.setAdapter(customAdapter);

                    }else if (catagoryFirst == "기타"&&categorySecond == "기숙사") {
                        query = query2();
                        maincontent = findViewById(R.id.mainactivity);
                        maincontent.setVisibility(View.GONE);
                        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View li = inflater.inflate(R.layout.li,null);
                        main.addView(li);
                        RecyclerView recyclerView =findViewById(R.id.arecyclerView);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        returnAkiBtn = findViewById(R.id.returnBtn);
                        QuestiongetVal(query);
                        CustomAdapter customAdapter = new CustomAdapter(testDataSet,testDataSet2);
                        returnAkiBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                main.removeView(li);
                                maincontent.setVisibility(View.VISIBLE);
                                recyclerView.setLayoutManager(null);
                                customAdapter.removeAllItems();
                                firstQuestion = aki.newfirstQuestionList();
                                questionString =aki.returnQuestion(firstQuestion);
                                questionView.setText(questionString);
                                QuestionCount = 1;

                            }
                        });
                        recyclerView.setAdapter(customAdapter);

                    }else if (catagoryFirst == "기타"&&categorySecond == "기타") {
                        query = query2();
                        maincontent = findViewById(R.id.mainactivity);
                        maincontent.setVisibility(View.GONE);
                        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View li = inflater.inflate(R.layout.li,null);
                        main.addView(li);
                        RecyclerView recyclerView =findViewById(R.id.arecyclerView);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        returnAkiBtn = findViewById(R.id.returnBtn);
                        QuestiongetVal(query);
                        CustomAdapter customAdapter = new CustomAdapter(testDataSet,testDataSet2);
                        returnAkiBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                main.removeView(li);
                                maincontent.setVisibility(View.VISIBLE);
                                recyclerView.setLayoutManager(null);
                                customAdapter.removeAllItems();
                                firstQuestion = aki.newfirstQuestionList();
                                questionString =aki.returnQuestion(firstQuestion);
                                questionView.setText(questionString);
                                QuestionCount = 1;

                            }
                        });
                        recyclerView.setAdapter(customAdapter);

                    }
                    //카운터 3의 경우 마지막 선택까지 한 경우라 바로 실행입니다!
                } else if (QuestionCount == 3) {
                    categoryThird = aki.yesbtn(questionString);
                    query = query3();
                    maincontent = findViewById(R.id.mainactivity);
                    maincontent.setVisibility(View.GONE);
                    LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View li = inflater.inflate(R.layout.li,null);
                    main.addView(li);
                    RecyclerView recyclerView =findViewById(R.id.arecyclerView);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    returnAkiBtn = findViewById(R.id.returnBtn);
                    QuestiongetVal(query);
                    CustomAdapter customAdapter = new CustomAdapter(testDataSet,testDataSet2);
                    returnAkiBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            main.removeView(li);
                            maincontent.setVisibility(View.VISIBLE);
                            recyclerView.setLayoutManager(null);
                            customAdapter.removeAllItems();
                            firstQuestion = aki.newfirstQuestionList();
                            questionString =aki.returnQuestion(firstQuestion);
                            questionView.setText(questionString);
                            QuestionCount = 1;

                        }
                    });
                    recyclerView.setAdapter(customAdapter);

                }


            }
        });


        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(QuestionCount == 1){
                    questionString =aki.returnQuestion(firstQuestion);
                    if(questionString =="기타")
                    {
                        catagoryFirst = "기타";
                        QuestionCount++;
                        QuestionList = aki.newExtraQuestionList();
                        questionString = aki.returnQuestion(QuestionList);
                        questionView.setText(questionString);
                    }else {
                        questionView.setText(questionString);
                    }
                } else if(QuestionCount==2) {
                    questionString = aki.returnQuestion(QuestionList);
                    if(questionString =="기타")
                    {
                        categorySecond = "기타";
                        query = query2();
                        maincontent = findViewById(R.id.mainactivity);
                        maincontent.setVisibility(View.GONE);
                        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View li = inflater.inflate(R.layout.li,null);
                        main.addView(li);
                        RecyclerView recyclerView =findViewById(R.id.arecyclerView);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        returnAkiBtn = findViewById(R.id.returnBtn);
                        QuestiongetVal(query);
                        CustomAdapter customAdapter = new CustomAdapter(testDataSet,testDataSet2);
                        returnAkiBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                main.removeView(li);
                                maincontent.setVisibility(View.VISIBLE);
                                recyclerView.setLayoutManager(null);
                                customAdapter.removeAllItems();
                                firstQuestion = aki.newfirstQuestionList();
                                questionString =aki.returnQuestion(firstQuestion);
                                questionView.setText(questionString);
                                QuestionCount = 1;

                            }
                        });
                        recyclerView.setAdapter(customAdapter);

                    }else {
                        questionView.setText(questionString);
                    }

                }else {
                    questionString = aki.returnQuestion(QuestionList);
                    if(questionString =="기타")
                    {
                        categoryThird = "기타";
                        query = query3();
                        maincontent = findViewById(R.id.mainactivity);
                        maincontent.setVisibility(View.GONE);
                        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View li = inflater.inflate(R.layout.li,null);
                        main.addView(li);
                        RecyclerView recyclerView =findViewById(R.id.arecyclerView);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        returnAkiBtn = findViewById(R.id.returnBtn);
                        QuestiongetVal(query);
                        CustomAdapter customAdapter = new CustomAdapter(testDataSet,testDataSet2);
                        returnAkiBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                main.removeView(li);
                                maincontent.setVisibility(View.VISIBLE);
                                recyclerView.setLayoutManager(null);
                                customAdapter.removeAllItems();
                                firstQuestion = aki.newfirstQuestionList();
                                questionString =aki.returnQuestion(firstQuestion);
                                questionView.setText(questionString);
                                QuestionCount = 1;

                            }
                        });
                        recyclerView.setAdapter(customAdapter);

                    }else {
                        questionView.setText(questionString);
                    }

                }


            }
        });

        // 돌아오는 버튼입니다!
        /*returnAkiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //setContentView();
            }
        });*/

        //질문 끝난 후 뿌리는 예시 test 버튼
        testbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                catagoryFirst = "학사";
                categorySecond = "학적";
                categoryThird = "휴학";
                String query = "SELECT * FROM Question where Maincategory= '"+catagoryFirst+"' AND SmallCategory1 = '"+categorySecond+"' AND SmallCategory2 = '"+categoryThird+"'";

                maincontent = findViewById(R.id.mainactivity);
                maincontent.setVisibility(View.GONE);
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View li = inflater.inflate(R.layout.li,null);
                main.addView(li);
                RecyclerView recyclerView =findViewById(R.id.arecyclerView);
                recyclerView.setLayoutManager(linearLayoutManager);
                QuestiongetVal(query);
                CustomAdapter customAdapter = new CustomAdapter(testDataSet,testDataSet2);
                recyclerView.setAdapter(customAdapter);
            }
        });



        //mainviewgone();


        //리사이클러뷰 표시 코드  잠시 주석
       /* maincontent = findViewById(R.id.mainactivity);
        maincontent.setVisibility(View.GONE);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View li = inflater.inflate(R.layout.li,null);
        //ConstraintLayout main = findViewById(R.id.main);
        main.addView(li);

        RecyclerView recyclerView =findViewById(R.id.arecyclerView);
        //LinearLayoutManager linearLayoutManager =new LinearLayoutManager((Context) this);
        recyclerView.setLayoutManager(linearLayoutManager);


        getVal();
        CustomAdapter customAdapter = new CustomAdapter(testDataSet);
        recyclerView.setAdapter(customAdapter);*/

    }



    public void mainviewgone(){
        maincontent.setVisibility(View.GONE);

    }

    public void getVal() {

        DataBaseHelper dbHelper = new DataBaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();


        Cursor cursor = db.rawQuery("SELECT * FROM Question where Maincategory= '학사'",null);
        //" and name = ?",new String[]{"홍길동"});
        String s= new String();

        while (cursor.moveToNext())
        {
             testDataSet.add(cursor.getString(1)) ;

        }
        //t.setText(s);
        cursor.close();
        dbHelper.close();
    }
    public void QuestiongetVal(String query) {

        DataBaseHelper dbHelper = new DataBaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(query,null);
        //" and name = ?",new String[]{"홍길동"});
        String s= new String();

        while (cursor.moveToNext())
        {
            testDataSet.add(cursor.getString(1)) ;
            testDataSet2.add(cursor.getString(2));

        }
        //t.setText(s);
        cursor.close();
        dbHelper.close();
    }
    
    // 여기 함수 추가 입니다. 12월 4일
    public String query2(){
        return "SELECT * FROM Question where Maincategory LIKE '"+catagoryFirst+"%' AND SmallCategory1 LIKE '"+categorySecond+"%'";
    }
    public String query3(){
        return "SELECT * FROM Question where Maincategory LIKE '"+catagoryFirst+"%' AND SmallCategory1 LIKE '"+categorySecond+"%' AND SmallCategory2 LIKE '"+categoryThird+"%'";
    }



    // 12월 11일 함수 추가 입니다.------
    public void FoodQuestiongetVal(String query) {

        DataBaseHelper dbHelper = new DataBaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        //" and name = ?",new String[]{"홍길동"});
        restaurant = new ArrayList<>();

        while (cursor.moveToNext()) {
            restaurant.add(cursor.getString(1));

        }

        //t.setText(s);
        cursor.close();
        dbHelper.close();
    }

}