package com.example.cst2335labs;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class ChatRoomActivity extends AppCompatActivity {

    ListView listView;
    EditText editText;
    List<MessageModel> listMessage = new ArrayList<>();
    Button sendBtn;
    Button receiveBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        listView = (ListView)findViewById(R.id.ListView);
        editText = (EditText)findViewById(R.id.ChatEditText);
        sendBtn = (Button)findViewById(R.id.SendBtn);
        receiveBtn = (Button)findViewById(R.id.ReceiveBtn);
        ChatAdapter adt = new ChatAdapter(listMessage, getApplicationContext());
        listView.setAdapter(adt);

        sendBtn.setOnClickListener(c -> {
            String message = editText.getText().toString();
            MessageModel model = new MessageModel(message, true);
            listMessage.add(model);
            editText.setText("");
            adt.notifyDataSetChanged();
        });

        receiveBtn.setOnClickListener(c -> {
            String message = editText.getText().toString();
            MessageModel model = new MessageModel(message, false);
            listMessage.add(model);
            editText.setText("");
            adt.notifyDataSetChanged();
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder adb = new AlertDialog.Builder(ChatRoomActivity.this);
                //adb.setView(Main.this);
                adb.setTitle("Do you want to delete this?");
                adb.setMessage("Selected Row is " + position + " and Database ID is " +id);

                adb.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ChatAdapter adt = new ChatAdapter(listMessage, getApplicationContext());
                        listMessage.remove(position); //deletes the messaage from array of messages
                        adt.notifyDataSetChanged();
                        listView.setAdapter(adt); //resets the view after you delete message
                        Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
                    } });

                adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Canceled", Toast.LENGTH_LONG).show();
                        //finish();
                    } });

                AlertDialog alertDialog = adb.create();
                alertDialog.show();

                return true;
            }
        });

        Log.d("ChatRoomActivity","onCreate");
    }
}


