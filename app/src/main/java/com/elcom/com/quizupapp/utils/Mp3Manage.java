package com.elcom.com.quizupapp.utils;

import android.content.Context;
import android.media.MediaPlayer;

import com.elcom.com.quizupapp.R;
import com.elcom.com.quizupapp.ui.listener.OnMp3FinishListener;

/**
 * Created by Hailpt on 6/26/2018.
 */


public class Mp3Manage {
    private  MediaPlayer mp;
    private  int[] resID = {R.raw.fail, R.raw.correct};
    private OnMp3FinishListener onMp3FinishListener;
    private int mSongIndex = 0;
    public void playSong(Context context,int songIndex) {
        mSongIndex = songIndex;
        if(mp != null){
            mp.reset();
        }
        mp = MediaPlayer.create(context, resID[songIndex]);

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
               LogUtils.e("hailpt"," Mp3Manage end");
                onMp3FinishListener.onMp3RightOrWrongAnswerFinished(mSongIndex);
            }

        });

        mp.start();
    }

    public void release(){
        if (mp != null){
            mp.release();
        }
    }

    public void setOnMp3FinishListener(OnMp3FinishListener onMp3FinishListener){
        this.onMp3FinishListener = onMp3FinishListener;
    }
}
