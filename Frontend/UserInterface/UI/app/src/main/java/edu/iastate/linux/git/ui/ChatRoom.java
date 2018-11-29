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
import android.widget.Toast;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

//import nbouma.com.wstompclient.implementation.StompClient;
//import nbouma.com.wstompclient.model.Frame;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class ChatRoom extends AppCompatActivity {


    Button sendChat, start;
    TextView output;
    EditText sendMSG;
    private WebSocketClient cc;
    private OkHttpClient client;
    //private StompClient stompclient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        sendChat = findViewById(R.id.sendTXTButton);
        output = findViewById(R.id.chatDisplayTXT);
        sendMSG = findViewById(R.id.sendMSGedittext);
        start = findViewById(R.id.connectButton);
        client = new OkHttpClient();
       /* start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start();
            }
        });
        sendChat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String msg = sendMSG.getText().toString();
                sendMSG.setText("");
                websocket.send()
            }
        });*/

        start.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {


                Draft[] drafts = {new Draft_6455()};

                //URL that will be used to access the Socket server
                //TODO add on reciever's ID for specific friend
                String w = URLConstants.SOCKET_URL + CurrentLoggedInUser.getInstance(getApplicationContext()).getUser().getId() + "/112";
                //String w = "ws://echo.websocket.org";
                Log.d("url",w);

                try {
                    Log.d("Socket:","Trying socket");
                    //cc = new WebSocketClient(new URI(w),drafts[0]);
                     cc = new WebSocketClient(new URI(w),(Draft) drafts[0]) {
                        @Override
                        public void onOpen(ServerHandshake serverHandshake) {
                            Log.d("OPEN", "run() returned: " + "is connecting");
                        }

                        @Override
                        public void onMessage(String msg)
                        {
                            Log.d("stuff", "run() returned: " + msg);
                            String s = output.getText().toString();
                            //Do I need to clear sendMSG here as welL?
                            output.setText(s + "Server:  " + msg + "\n");

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


                //trying Stomp
                //connect to server
                /*
                stompclient = new StompClient(w) { //example "ws://localhost:8080/message-server"
                    @Override
                    protected void onStompError(String errorMessage) {
                        Log.d("something", "error : " + errorMessage);
                    }

                    @Override
                    protected void onConnection(boolean connected) {
                        Log.d("something", "connected : " + String.valueOf(connected));
                    }

                    @Override
                    protected void onDisconnection(String reason) {
                        Log.d("something", "disconnected : " + reason);
                    }

                   // @Override
                  //  protected void onStompMessage(Frame frame) {
                   //
                   // }

                    @Override
                    protected void onStompMessage(final Frame frame) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), frame.getBody(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                };*/
               // stompclient.unSubscribe("destination");
            }



            //  disconnect from stomp server and websocket

//          stompClient.unSubscribe("destination");





            });
        //});

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
            case R.id.chatroom:
                Intent i5 = new Intent(ChatRoom.this, ChatRoom.class);
                startActivity(i5);
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
