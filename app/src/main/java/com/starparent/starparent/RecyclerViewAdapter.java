package com.starparent.starparent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.starparent.starparent.StaticClasses.*;

import java.util.List;

/**
 * Borrowed from https://developer.android.com/training/material/lists-cards.html
 * A little extra help came from https://stackoverflow.com/questions/43703837/onclicklistener-recyclerview-list-of-buttons
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";
    private List<IdeasBankProblem> problems;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        Button problem;
        ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.textView);
            problem = (Button) v.findViewById(R.id.text_view_button);
        }
    }

    public RecyclerViewAdapter(List<IdeasBankProblem> problems, Context context) {
        this.problems = problems;
        this.context = context;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.problem.setText(problems.get(position).title);
        holder.problem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startProblemActivity(position);
            }
        });
    }

    private void startProblemActivity(int position) {
        Log.d(TAG, "startingProblemActivity: " + position);
        Intent i = new Intent(context, ProblemActivity.class);
        Bundle b = new Bundle();
        b.putSerializable("problem", problems.get(position));
        i.putExtras(b);
        context.startActivity(i);
    }

    @Override
    public int getItemCount() {
        return problems.size();
    }
}
