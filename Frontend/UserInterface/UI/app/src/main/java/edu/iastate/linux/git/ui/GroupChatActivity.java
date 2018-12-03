package edu.iastate.linux.git.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class GroupChatActivity extends AppCompatActivity {

    Button sendChat, start;
    TextView group;
    TextView output;
    EditText sendMSG;
    private WebSocketClient cc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat);


        sendChat = findViewById(R.id.sendTXTButton);
        output = findViewById(R.id.chatDisplayTXT);
        sendMSG = findViewById(R.id.sendMSGedittext);
        start = findViewById(R.id.connectButton);

        start.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {


                Draft[] drafts = {new Draft_6455()};

                //URL that will be used to access the Socket server
                //TODO add on Group's ID for specific friend
                String w = URLConstants.GROUP_URL + CurrentLoggedInUser.getInstance(getApplicationContext()).getUser().getId() + "/111";
                //String w = "ws://echo.websocket.org";

                try {
                    Log.d("Socket:", "Trying socket");
                    //cc = new WebSocketClient(new URI(w),drafts[0]);
                    cc = new WebSocketClient(new URI(w), (Draft) drafts[0]) {
                        @Override
                        public void onOpen(ServerHandshake serverHandshake) {
                            Log.d("OPEN", "run() returned: " + "is connecting");
                        }

                        @Override
                        public void onMessage(String msg) {
                            Log.d("stuff", "run() returned: " + msg);
                            String s = output.getText().toString();
                            //Do I need to clear sendMSG here as welL?
                            output.setText(s + "Server:  " + msg + "\n");

                        }

                        @Override
                        public void onClose(int code, String reason, boolean remote) {
                            Log.d("CLOSE", "onClose() returned:" + " is connecting");
                        }

                        @Override
                        public void onError(Exception e) {
                            Log.d("Exception: ", e.toString());
                        }
                    };
                } catch (URISyntaxException e) {
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
                    e.printStackTrace();
                    Log.d("ExceptionSendMessage:", e.getMessage());
                }
            }
        });
    }


    private void groupDisplay(){



    }
}
