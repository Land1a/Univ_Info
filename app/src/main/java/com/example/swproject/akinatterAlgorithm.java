package com.example.swproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class akinatterAlgorithm {

    String a;
     public List<String> firstQuestionList;

     //선언부는 여기부터 추가입니다.12월 4일 이후
     public List<String>AcademicQuestionList;
     public List<String>HoursOfUse; //이용시간
     public List<String>Extra;// 기타
     public List<String>record; //학적
     public List<String>lesson; // 수업
     public List<String>Enrolment;// 수강신청
     public List<String>registration;// 등록




    public void akinatterAlgorithm(){

    }





    // 질문들을 하나씩 반환해 주는 함수
    public String returnQuestion(List<String> questionlist) {
        if (questionlist == null || questionlist.isEmpty()) {
            return "기타"; // 리스트가 비어있다면 기타 반환
        }

        Random random = new Random();
        int randomIndex = random.nextInt(questionlist.size());

        String requesttion = questionlist.get(randomIndex);
        questionlist.remove(randomIndex);

        return requesttion;

    }

    public String yesbtn(String Question){
        switch (Question){
            // 카테고리 1
            case "학사와 관련된 질문인가요?":

                return "학사";

            case "이용시간 혹은 위치와 관련된 질문인가요?":
                return "이용시간";
            // 카테고리 2
            //학사
            case "한학기의 시작! 수강신청과 관련되어있나요?":
                return "수강신청";
            case "수업과 관련되어있나요?(결석,폐강,채플?)":
                return "수업";
            case "당신의 학적상태와 관련되어있나요? (휴복학,복수전공등)":
                return "학적";
            case "가장 중요한 성적! 과 관련되어있나요?":
                return "성적";
            case "졸업장은 따야지… 졸업과 관련되어있나요?":
                return "졸업";
            case "등록금과 관련되어있나요?(장학금은 따로 있어요!)":
                return "등록";
            case "장학금과 관련되어 있나요?":
                return "장학";
            //이용시간
            case "이용시간과 관련되어 있나요?":
                return "이용시간";
            case "위치와 관련되어 있나요?":
                return "위치";
            //기타
            case "기숙사와 관련되어 있나요?":
                return "기숙사";
            // 카테고리 3
            // 학적
            case "휴학,복학,편입과 관련되어 있나요?":
                return "휴학";
            //수업
            case "결석과 관련되어 있나요?":
                return "결석";
            case "채플과 관련되어 있나요?":
                return "채플";
            //수강신청
            case "계절학기와 관련되어 있나요?":
                return "계절학기";
            case "폐강과 관련되어 있나요?":
                return "폐강";
            case "신청학점과 관련되어 있나요?":
                return "학점";
            //등록
            case "등록금 납부와 관련되어 있나요?":
                return "납부";
            case "등록금 고지서와 관련되어 있나요?":
                return "고지서";

            default:
                return "기타";

        }

    }
    public List<String> newfirstQuestionList(){
        firstQuestionList= new ArrayList<>();
        firstQuestionList.add("학사와 관련된 질문인가요?");
        firstQuestionList.add("이용시간 혹은 위치와 관련된 질문인가요?");
        return firstQuestionList;
    }

    // 여기서 부터 새로 추가된 부분입니다 12월 4일

    public List<String> newAcademicQuestionList(){
        AcademicQuestionList= new ArrayList<>();
        AcademicQuestionList.add("한학기의 시작! 수강신청과 관련되어있나요?");
        AcademicQuestionList.add("수업과 관련되어있나요?(결석,폐강,채플?)");
        AcademicQuestionList.add("당신의 학적상태와 관련되어있나요? (휴복학,복수전공등)");
        AcademicQuestionList.add("가장 중요한 성적! 과 관련되어있나요?");
        AcademicQuestionList.add("졸업장은 따야지… 졸업과 관련되어있나요?");
        AcademicQuestionList.add("등록금과 관련되어있나요?(장학금은 따로 있어요!)");
        AcademicQuestionList.add("장학금과 관련되어 있나요?");
        return AcademicQuestionList;
    }
    public List<String> newHoursOfUseQuestionList(){
        HoursOfUse= new ArrayList<>();
        HoursOfUse.add("이용시간과 관련되어 있나요?");
        HoursOfUse.add("위치와 관련되어 있나요?");
        return HoursOfUse;
    }
    public List<String> newExtraQuestionList(){
        Extra= new ArrayList<>();
        Extra.add("기숙사와 관련되어 있나요?");
        return Extra;
    }
    public List<String> newrecordQuestionList(){
        record =new ArrayList<>();
        record.add("휴학,복학,편입과 관련되어 있나요?");
        return record;
    }
    public List<String> newlessonQuestionList(){
        lesson= new ArrayList<>();
        lesson.add("결석과 관련되어 있나요?");
        lesson.add("채플과 관련되어 있나요?");
        return lesson;
    }
    public List<String> newEnrolmentQuestionList(){
        Enrolment= new ArrayList<>();
        Enrolment.add("계절학기와 관련되어 있나요?");
        Enrolment.add("폐강과 관련되어 있나요?");
        Enrolment.add("신청학점과 관련되어 있나요?");
        return Enrolment;
    }

    public List<String> newregistrationQuestionList(){
        registration= new ArrayList<>();
        registration.add("등록금 납부와 관련되어 있나요?");
        registration.add("등록금 고지서와 관련되어 있나요?");
        return registration;
    }



















}
