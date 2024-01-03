package com.example.swproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{
    private ArrayList<String> localDataSet;
    private ArrayList<String> testDataSet;
    private ArrayList<String> testDataSet2;
    private AdapterView.OnItemClickListener listener;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            textView = itemView.findViewById(R.id.retextview);

        }
        public TextView getTextView(){
            return textView;
        }


    }
    /*public CustomAdapter(ArrayList<String> dataSet){
        localDataSet= dataSet;
    }*/
    @NonNull
    @Override // ViewHolder 객체를 생성하여 리턴
    public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
        CustomAdapter.ViewHolder viewHolder = new CustomAdapter.ViewHolder(view);
        return viewHolder;
    }
    public CustomAdapter(ArrayList<String> dataSet, ArrayList<String> dataSet2) {
        testDataSet = dataSet;
        testDataSet2 = dataSet2;
    }
    // ViewHolder안의 내용을 position에 해당되는 데이터로 교체
    /*@Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position){
        String text = localDataSet.get(position);
        holder.textView.setText(text);
    }*/
    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {

        String text = testDataSet.get(position);

        holder.textView.setText(text);
        final int itemPosition = holder.getAdapterPosition();
        AtomicBoolean OriginalState = new AtomicBoolean(true);
        // 리사이클러뷰 아이템을 클릭했을 때의 이벤트 처리
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 해당 아이템에 대한 testDataSet2의 데이터 가져오기
                String dataForSlide = testDataSet2.get(itemPosition);
                if (OriginalState.get()){
                    holder.textView.setText(dataForSlide);
                }else {
                    holder.textView.setText(text);
                }
                OriginalState.set(!OriginalState.get());


            }
        });
    }

    public void removeAllItems() {
        testDataSet.clear();
        testDataSet2.clear();
        notifyDataSetChanged();
    }

    @Override// 전체 데이터의 갯수를 리턴
    public int getItemCount(){
        return testDataSet.size();
    }

}
