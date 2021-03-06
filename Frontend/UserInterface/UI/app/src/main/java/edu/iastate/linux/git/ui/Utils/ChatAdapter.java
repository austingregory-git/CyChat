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

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {

    private Context context;
    //private String[] chatNames, chatMsg;
    private ArrayList<String> chatNames, chatMsg, chatTime;
    private static ClickListener clickListener;
    private int chatType;

    public ChatAdapter(Context context, ArrayList<String> names, ArrayList<String> msg, ArrayList<String> time, int chatType) {
        this.context = context;
        this.chatNames = names;
        this.chatMsg = msg;
        this.chatTime = time;
        this.chatType = chatType;
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        ChatAdapter.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ChatAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, parent, false);
        ChatAdapter.MyViewHolder vh = new ChatAdapter.MyViewHolder(v); // pass the view to View Holder

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.MyViewHolder holder, int position) {
        if(chatNames.size() != 0 && chatMsg.size() != 0 && chatTime.size() != 0) {
            holder.name.setText(chatNames.get(position));
            holder.msg.setText(chatMsg.get(position));
            holder.time.setText(chatTime.get(position));
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
        TextView time;
        LetterImageView liv;
        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            // get the reference of item view's
            name = (TextView) itemView.findViewById(R.id.textChatName);
            msg = (TextView) itemView.findViewById(R.id.textChatMsg);
            time = (TextView) itemView.findViewById(R.id.textChatTime);
            liv = (LetterImageView) itemView.findViewById(R.id.imageChat);

        }

        @Override
        public void onClick(View v) {
            clickListener.OnItemClick(getAdapterPosition(), chatType, v);
        }
    }

    public interface ClickListener {
        void OnItemClick(int position, int chatType, View v);
    }
}
