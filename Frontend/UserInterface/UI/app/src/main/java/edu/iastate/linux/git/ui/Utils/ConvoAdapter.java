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

public class ConvoAdapter extends RecyclerView.Adapter<ConvoAdapter.MyViewHolder> {

    private Context context;
    //private String[] chatNames, chatMsg;
    private String fromName;
    private ArrayList<String> chatToMsg, chatFromMsg, chatToTime, chatFromTime;
    private static ClickListener clickListener;

    public ConvoAdapter(Context context, String name, ArrayList<String> chatToMsg,  ArrayList<String> chatFromMsg, ArrayList<String> chatToTime,  ArrayList<String> chatFromTime) {
        this.context = context;
        this.fromName = name;
        this.chatToMsg = chatToMsg;
        this.chatFromMsg = chatFromMsg;
        this.chatToTime = chatToTime;
        this.chatFromTime = chatFromTime;
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        ConvoAdapter.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ConvoAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.convo_item, parent, false);
        ConvoAdapter.MyViewHolder vh = new ConvoAdapter.MyViewHolder(v); // pass the view to View Holder

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ConvoAdapter.MyViewHolder holder, int position) {
        holder.name.setText(fromName);
        holder.msg.setText(chatFromMsg.get(position));
        holder.time.setText(chatFromTime.get(position));
        holder.liv.setOval(true);
        holder.liv.setLetter(fromName.charAt(0));
    }

    @Override
    public int getItemCount() {
        return chatFromMsg.size();
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
            name = (TextView) itemView.findViewById(R.id.convoName);
            msg = (TextView) itemView.findViewById(R.id.convoChatMsg);
            time = (TextView) itemView.findViewById(R.id.convoChatTime);
            liv = (LetterImageView) itemView.findViewById(R.id.imageConvo);

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

