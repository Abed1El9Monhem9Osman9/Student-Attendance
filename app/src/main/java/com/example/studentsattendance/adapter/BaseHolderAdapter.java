package com.example.studentsattendance.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentsattendance.R;
import com.example.studentsattendance.data.BaseClass;
import com.example.studentsattendance.data.Session;
import com.example.studentsattendance.data.Student;
import com.example.studentsattendance.helper.BaseHelper;
import com.example.studentsattendance.view.DetailViewActivity;

import java.util.ArrayList;
import java.util.List;


public class BaseHolderAdapter<B> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    int viewType;
    public List<B> studentDataList = new ArrayList<>();
    public List<B> listSession = new ArrayList<>();
    public List<B> detailList = new ArrayList<>();
    public Context c;
    public BaseHelper.OnItemClick onItemClick;

    public BaseHolderAdapter(List<B> studentDataList, BaseHelper.OnItemClick onItemClick, int viewType) {
        this.studentDataList = studentDataList;
        this.onItemClick = onItemClick;
        this.viewType = viewType;
    }

    public BaseHolderAdapter(List<B> listSession, int viewType, Context c) {
        this.listSession = listSession;
        this.viewType = viewType;
        this.c = c;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView;
        if (viewType == 0) {
            itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_checked_students, viewGroup, false);
            return new MyViewHolder(itemView);
        } else if (viewType == 1) {
            itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_session_view, viewGroup, false);
            return new SessionViewHolder(itemView);
        } else
            itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_detail_view, viewGroup, false);
            return new DetailedViewHolder(itemView);


    }


    @NonNull
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof MyViewHolder) {
            final MyViewHolder viewHolder1 = (MyViewHolder) viewHolder;

            if (studentDataList != null && !studentDataList.isEmpty()) {
                Student data = (Student) studentDataList.get(i);
                viewHolder1.fullName.setText(data.getFullName());
                viewHolder1.fileNumber.setText(String.valueOf(data.getFileNumber()));
                viewHolder1.box.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    if (isChecked) {
                        data.setChecked(1);
                    } else {
                        data.setChecked(0);
                    }
                });

                onItemClick.getStd((List<BaseClass>) studentDataList);
            }
        } else if (viewHolder instanceof SessionViewHolder) {
            final SessionViewHolder sv = (SessionViewHolder) viewHolder;
            if (listSession != null && !listSession.isEmpty()) {
                Session data = (Session) listSession.get(i);
                sv.titleSession.setText(data.getTitle());
                sv.titleSession.setOnClickListener(v -> {
                    BaseHelper.createToast(c, data.getTitle());

                    Intent intent = new Intent(c, DetailViewActivity.class);
                    intent.putExtra("123", data.getTitle());
                    c.startActivity(intent);
                });
            }
        } else if (viewHolder instanceof DetailedViewHolder) {
            final DetailedViewHolder dv = (DetailedViewHolder) viewHolder;
            if (listSession != null && !listSession.isEmpty()) {
                Student data = (Student) listSession.get(i);
                dv.idTv.setText(data.getFileNumber()+"");
                dv.nameTv.setText(data.getFullName());
                if(data.getChecked() == 1){
                    dv.checkedTv.setText("present");
                }else{
                    dv.checkedTv.setText("absent");
                }
            }
        }

    }

    @Override
    public int getItemCount() {
        if (viewType == 0) {
            return studentDataList.size();
        } else {
            return listSession.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }



    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView fileNumber, fullName;
        CheckBox box;

        MyViewHolder(View itemView) {
            super(itemView);
            box = itemView.findViewById(R.id.checked_std);
            fileNumber = itemView.findViewById(R.id.row_file_number);
            fullName = itemView.findViewById(R.id.row_full_name);
        }

    }

    static class SessionViewHolder extends RecyclerView.ViewHolder {
        TextView titleSession;
        Context context;
        CardView cardView;

        SessionViewHolder(View itemView) {
            super(itemView);
            titleSession = itemView.findViewById(R.id.item_session_tv);
            cardView = itemView.findViewById(R.id.card_view_session);
        }
    }

    static class DetailedViewHolder extends RecyclerView.ViewHolder {
        TextView idTv, checkedTv, nameTv;

        DetailedViewHolder(View itemView) {
            super(itemView);
            idTv = itemView.findViewById(R.id.textView_id);
            nameTv = itemView.findViewById(R.id.textView_name);
            checkedTv = itemView.findViewById(R.id.textView_checked);
        }
    }
}
