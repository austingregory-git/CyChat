package edu.iastate.linux.git.ui.Utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import edu.iastate.linux.git.ui.HomeActivity;
import edu.iastate.linux.git.ui.R;

public class GroupChatAdapter extends RecyclerView.Adapter<GroupChatAdapter.MyViewHolder> {

    private Context context;
    //private String[] chatNames, chatMsg;
    private ArrayList<String> chatNames, chatMsg;
    private static ClickListener clickListener;
    private int chatType;

    public GroupChatAdapter(Context context, ArrayList<String> names, ArrayList<String> msg) {
        this.context = context;
        this.chatNames = names;
        this.chatMsg = msg;
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        GroupChatAdapter.clickListener = clickListener;
    }

    @NonNull
    @Override
    public GroupChatAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_convo_item, parent, false);
        GroupChatAdapter.MyViewHolder vh = new GroupChatAdapter.MyViewHolder(v); // pass the view to View Holder

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull GroupChatAdapter.MyViewHolder holder, int position) {
        if(chatNames.size() != 0 && chatMsg.size() != 0) {
            holder.name.setText(chatNames.get(position));
            holder.msg.setText(chatMsg.get(position));
            holder.liv.setOval(true);
            holder.liv.setLetter(chatNames.get(position).charAt(0));
        }
    }

    @Override
    public int getItemCount() {
        return chatNames.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;// init the item view's
        TextView msg;
        LetterImageView liv;
        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            // get the reference of item view's
            name = (TextView) itemView.findViewById(R.id.textGrpChatName);
            msg = (TextView) itemView.findViewById(R.id.textGrpChatMsg);
            liv = (LetterImageView) itemView.findViewById(R.id.imageGrpChat);

        }

        @Override
        public void onClick(View v) {
            clickListener.OnItemClick(getAdapterPosition(), v);
        }
    }

    public interface ClickListener {
        void OnItemClick(int position, View v);
    }
}

