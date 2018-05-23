package com.example.monicamamdouh.rxjavaparellerrequesttask.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.monicamamdouh.rxjavaparellerrequesttask.R;
import com.example.monicamamdouh.rxjavaparellerrequesttask.common.models.MergedObjectList;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.MainViewHolder> {


    private MergedObjectList mergedObjectList;

    MainRecyclerViewAdapter() {
        mergedObjectList = new MergedObjectList();

    }

    public MergedObjectList getMergedObjectList() {
        return mergedObjectList;
    }

    public void setMergedObjectList(MergedObjectList mergedObjectList) {
        this.mergedObjectList = mergedObjectList;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_merge_data, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        if (mergedObjectList.getNameList() != null && mergedObjectList.getNameList().size() > position)

            holder.name.setText(mergedObjectList.getNameList().get(position).getName());
        else
            holder.name.setText(" ");
        if (mergedObjectList.getNewsList() != null && mergedObjectList.getNewsList().size() > position)

            holder.news.setText(mergedObjectList.getNewsList().get(position).getName());
        else
            holder.news.setText(" ");

    }

    @Override
    public int getItemCount() {

//     if(mergedObjectList!=null) {
//         if (mergedObjectList.getNewsList().size() > mergedObjectList.getNameList().size())
//             return mergedObjectList.getNameList().size();
//         else
//             return mergedObjectList.getNewsList().size();
//     }
     return 20;

    }

    class MainViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView news;

        MainViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            news = itemView.findViewById(R.id.news);
        }
    }

}
