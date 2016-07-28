package com.example.emillozev.todo.recycler;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.emillozev.todo.ContextGetter;
import com.example.emillozev.todo.MainActivity;
import com.example.emillozev.todo.R;
import com.example.emillozev.todo.implementation.TaskImpl;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private List<TaskImpl> mTaskList;

    public RecyclerAdapter(List<TaskImpl> taskList) {
        mTaskList = taskList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter
                .createFromResource(ContextGetter.getApplicationCtxt(), R.array.status_array, android.R.layout.simple_spinner_dropdown_item);
        holder.mStatus.setAdapter(adapter);

        holder.mStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        holder.mStatusText.setText("DONE");
                        break;
                    case 1:
                        holder.mStatusText.setText("DOING");
                        break;
                    case 2:
                        holder.mStatusText.setText("TODO");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        holder.mTitle.setText(mTaskList.get(position).getTaskTitle());
        holder.mDescription.setText(mTaskList.get(position).getDescription());
        holder.mStatusText.setText(mTaskList.get(position).getStatus().toString());
        holder.mPriority.setText(mTaskList.get(position).getPriority().toString());
        holder.mDel.setOnClickListener(view -> {
            mTaskList.remove(position);
            MainActivity.mAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return mTaskList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        CardView mCard;
        TextView mTitle;
        TextView mDescription;
        Spinner mStatus;
        TextView mStatusText;
        TextView mPriority;
        Button mDel;

        public MyViewHolder(View itemView) {
            super(itemView);
            mCard = (CardView) itemView.findViewById(R.id.todoCard);
            mTitle = (TextView) itemView.findViewById(R.id.taskTitle);
            mDescription = (TextView) itemView.findViewById(R.id.taskDescription);
            mDel = (Button) itemView.findViewById(R.id.taskDelete);
            mStatus = (Spinner) itemView.findViewById(R.id.taskStatus);
            mStatusText = (TextView) itemView.findViewById(R.id.textSpinner);
            mPriority = (TextView) itemView.findViewById(R.id.taskPriority);
        }
    }
}
