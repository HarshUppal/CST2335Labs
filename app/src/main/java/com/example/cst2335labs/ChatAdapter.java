package com.example.cst2335labs;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import java.util.List;

public class ChatAdapter extends BaseAdapter {

    private List<MessageModel> messageModels;
    private Context context;
    private LayoutInflater inflater;

    public ChatAdapter(List<MessageModel> messageModels, Context context) {
        this.messageModels = messageModels;
        this.context = context;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return messageModels.size();
    }

    @Override
    public Object getItem(int position) {
        return messageModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;


            if (messageModels.get(position).isSend()){
                view = inflater.inflate(R.layout.activity_main_send, null);

            } else {
                view = inflater.inflate(R.layout.activity_main_receive, null);
            }
            TextView  messageText = (TextView)view.findViewById(R.id.textViewMessage);
            messageText.setText(messageModels.get(position).message);
        
        return view;
    }
}
