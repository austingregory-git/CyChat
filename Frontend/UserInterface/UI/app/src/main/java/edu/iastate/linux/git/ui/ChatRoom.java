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
                String w = URLConstants.SOCKET_URL;
                //String w = "ws://echo.websocket.org";
                // 10.26.44.40
                try {
                    Log.d("Socket:","Trying socket");
                    //cc = new WebSocketClient(new URI(w),drafts[0]);
                     cc = new WebSocketClient(new URI(w)) {
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
                    //Log.d("ExceptionSendMessage:", e.getMessage());
                }
            }
        });

    }
    /*
    private final class EchoWebSocketListener extends WebSocketListener {
        private static final int NORMAL_CLOSURE_STATUS = 1000;
        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            webSocket.send("Hello, it's SSaurel !");
            webSocket.send("What's up ?");
            webSocket.send(ByteString.decodeHex("deadbeef"));
           // webSocket.close(NORMAL_CLOSURE_STATUS, "Goodbye !");
        }
        @Override
        public void onMessage(WebSocket webSocket, String text) {
            output("Receiving : " + text);
        }
        @Override
        public void onMessage(WebSocket webSocket, ByteString bytes) {
            output("Receiving bytes : " + bytes.hex());
        }
        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            webSocket.close(NORMAL_CLOSURE_STATUS, null);
            output("Closing : " + code + " / " + reason);
        }
        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
            output("Error : " + t.getMessage());
        }
    }
    private void start() {
        Request request = new Request.Builder().url("wss://echo.websocket.org").build();
        EchoWebSocketListener listener = new EchoWebSocketListener();
        WebSocket ws = client.newWebSocket(request, listener);
        client.dispatcher().executorService().shutdown();
    }
    private void output(final String txt) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                output.setText(output.getText().toString() + "\n\n" + txt);
            }
        });
    }*/


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
