package edu.iastate.linux.git.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

public class ChatRoom extends AppCompatActivity {

    String formattedURL;
    Button sendChat, connectToServer;
    TextView viewChats;
    EditText sendMSG;
    private WebSocketClient cc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        sendChat = findViewById(R.id.sendTXTButton);
        viewChats = findViewById(R.id.chatDisplayTXT);
        sendMSG = findViewById(R.id.sendMSGedittext);
        connectToServer = findViewById(R.id.connectButton);
        connectToServer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Draft[] drafts = {new Draft_6455()};

                //URL that will be used to access the Socket server
                String w = URLConstants.SOCKET_URL + Integer.toString(CurrentLoggedInUser.getInstance(getApplicationContext()).getUser().getId());

                try {
                    Log.d("Socket:","Trying socket");
                    cc = new WebSocketClient(new URI(w),(Draft) drafts[0]) {
                        @Override
                        public void onOpen(ServerHandshake serverHandshake) {
                            Log.d("OPEN", "run() returned: " + "is connecting");
                        }

                        @Override
                        public void onMessage(String msg)
                        {
                            Log.d("", "run() returned: " + msg);
                            String s = viewChats.getText().toString();
                            //Do I need to clear sendMSG here as welL?
                            viewChats.setText(s + " Server:" + msg);

                        }

                        @Override
                        public void onClose(int code, String reason, boolean remote)
                        {
                            Log.d("CLOSE", "onClose() returned:" + " is connecting");
                        }

                        @Override
                        public void onError(Exception e)
                        {
                            Log.d("Exception: ", e.toString());
                        }
                    };
                }
                catch (URISyntaxException e)
                {
                    Log.d("Exception", e.getMessage().toString());
                    e.printStackTrace();
                }
                cc.connect();
            }
        });

        sendChat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try{
                    cc.send(sendMSG.getText().toString());
                }
                catch (Exception e)
                {
                    Log.d("ExceptionSendMessage:", e.getMessage().toString());
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.topright_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.settings:
                Intent i1 = new Intent(ChatRoom.this, SettingsActivity.class);
                startActivity(i1);
                return(true);
            case R.id.options:
                Intent i2 = new Intent(ChatRoom.this, OptionsActivity.class);
                startActivity(i2);
                return(true);
            case R.id.map:
                Intent i3 = new Intent(ChatRoom.this, GoogleMapActivity.class);
                startActivity(i3);
                return(true);
            case R.id.exit:
                finish();
                System.exit(0);
                return(true);
            case R.id.log_out:
                // CurrentLoggedInUser.logout();
                Intent i4 = new Intent(ChatRoom.this, LoginActivity.class);
                startActivity(i4);

                return(true);
        }
        return super.onOptionsItemSelected(item);
    }
}
