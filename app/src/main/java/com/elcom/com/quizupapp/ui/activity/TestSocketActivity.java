package com.elcom.com.quizupapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.elcom.com.quizupapp.R;
import com.elcom.com.quizupapp.ui.activity.model.entity.Test;
import com.elcom.com.quizupapp.utils.LogUtils;
import com.elcom.com.quizupapp.utils.Utils;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

/**
 * Created by Hailpt on 4/11/2018.
 */
public class TestSocketActivity extends AppCompatActivity {

    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://192.168.6.82:3000");
        } catch (URISyntaxException e) {

        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_socket);
//        mSocket.connect();
//        mSocket.on("send", onNewSend);
//        mSocket.on("connect", onNewMessage);
//        mSocket.on("countDown", onNewMessage);
//        mSocket.on("resultQuestion", resultQuestion);

        Button btn_emit = (Button)findViewById(R.id.btn_emit);
        btn_emit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendAnswer();
            }
        });


        Intent intent = new Intent();
        intent.setAction("com.example.SendBroadcast");
        intent.putExtra("name","Ambition");
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(intent);

    }

    private Emitter.Listener onNewSend = new Emitter.Listener() {

        @Override
        public void call(final Object... args) {

            Log.e("TestSocketActivity", " onNewSend ");

        }
    };

    private Emitter.Listener resultQuestion = new Emitter.Listener() {

        @Override
        public void call(final Object... args) {
            Log.e("TestSocketActivity", " resultQuestion ");

        }
    };

    private Emitter.Listener onNewMessage = new Emitter.Listener() {

        @Override
        public void call(final Object... args) {

            Log.e("TestSocketActivity", " mSocket ");

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if (!(args[0] instanceof JSONObject)){
                        Toast.makeText(TestSocketActivity.this, " ERROR "+ args[0].toString(), Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(TestSocketActivity.this, LoginActivity.class));
                        new Utils.showNotify().show(TestSocketActivity.this);
                        return;
                    }
//                    startActivity(new Intent(TestSocketActivity.this, LoginActivity.class));
                    JSONObject data = (JSONObject) args[0];
                    String username;
                    int countDown;
                    try {
                        countDown = data.getInt("CountDownTime");
                        Log.e("TestSocketActivity", " countDown "+countDown);
                    } catch (JSONException e) {
                        return;
                    }
                    // add the message to view

                }
            });
        }
    };

    public void addMessage(String name, String mess){
        Toast.makeText(TestSocketActivity.this, "name "+name +" and mess "+mess, Toast.LENGTH_SHORT).show();
        LogUtils.e("TestSocketActivity", "name "+name +" and mess "+mess);
    }

    private void attemptSend() {

        JSONObject student1 = new JSONObject();
        try {
            student1.put("id", 108);

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        mSocket.emit("connectServer", student1);
    }

    private void sendAnswer() {

        JSONObject student1 = new JSONObject();
        try {
            student1.put("send_id", 14);
            student1.put("to_id", 35);
            student1.put("match_id", 431);
            student1.put("topic_id", 1);
            student1.put("number_question", 1);
            student1.put("result_question", "true");
            student1.put("status_user_bot", "true");

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        mSocket.emit("resultQuestion", student1);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mSocket.disconnect();
        mSocket.off("new message", onNewMessage);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        mSocket.disconnect();
//        mSocket.off("new message", onNewMessage);
    }
}
