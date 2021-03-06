package com.elcom.eonline.quizupapp.utils;

import android.graphics.Bitmap;

import com.elcom.eonline.quizupapp.ui.custom.SocketManage;
import com.facebook.AccessToken;

public class ConstantsApp {
    public static final boolean DEBUG = true;

    public static enum FilterType {
        CONTRAST, GRAYSCALE, SHARPEN, SEPIA, SOBEL_EDGE_DETECTION, THREE_X_THREE_CONVOLUTION, FILTER_GROUP, EMBOSS, POSTERIZE, GAMMA, BRIGHTNESS, INVERT, HUE, PIXELATION,
        SATURATION, EXPOSURE, HIGHLIGHT_SHADOW, MONOCHROME, OPACITY, RGB, WHITE_BALANCE, VIGNETTE, TONE_CURVE, BLEND_COLOR_BURN, BLEND_COLOR_DODGE, BLEND_DARKEN, BLEND_DIFFERENCE,
        BLEND_DISSOLVE, BLEND_EXCLUSION, BLEND_SOURCE_OVER, BLEND_HARD_LIGHT, BLEND_LIGHTEN, BLEND_ADD, BLEND_DIVIDE, BLEND_MULTIPLY, BLEND_OVERLAY, BLEND_SCREEN, BLEND_ALPHA,
        BLEND_COLOR, BLEND_HUE, BLEND_SATURATION, BLEND_LUMINOSITY, BLEND_LINEAR_BURN, BLEND_SOFT_LIGHT, BLEND_SUBTRACT, BLEND_CHROMA_KEY, BLEND_NORMAL, LOOKUP_AMATORKA,
        GAUSSIAN_BLUR, CROSSHATCH, BOX_BLUR, CGA_COLORSPACE, DILATION, KUWAHARA, RGB_DILATION, SKETCH, TOON, SMOOTH_TOON, BULGE_DISTORTION, GLASS_SPHERE, HAZE, LAPLACIAN, NON_MAXIMUM_SUPPRESSION,
        SPHERE_REFRACTION, SWIRL, WEAK_PIXEL_INCLUSION, FALSE_COLOR, COLOR_BALANCE, LEVELS_FILTER_MIN, BILATERAL_BLUR, HALFTONE, TRANSFORM2D
    }

    public static final String DB_NAME = "quizupelcom";
    public static String BASE64_AUTH_TOKEN = "";
    public static String BASE64_HEADER = "";
    public static String SERVER_URL = "http://api.giaido.vn/api/";
//    public static String SERVER_URL = "http://api-dev.giaido.vn/api/";

    public static Bitmap mImage = null;
    public static String EXTRA_URI_STR = "EXTRA_URI_STR ";
    public static int REQUEST_CODE_START_ACTIVITY = 1000;
    public static int START_ACTIVITY_TO_PLAY_GAME_FROM_QUIZUPACTIVITY = 100;
    public static int START_ACTIVITY_TO_GO_TO_QUESTION_INFO = 101;
    public static int START_ACTIVITY_TO_HISTORY = 102;
    public static int RESULT_CODE_TO_STOP_GAME_FROM_QUIZUPACTIVITY = 10;
    public static int RESULT_CODE_TO_CONTINUE_TO_PLAY_GAME_FROM_QUIZUPACTIVITY = 9;
    public static int RESULT_CODE_FROM_CHALLENGE_RESULT = 103;

    public static final int RESULT_CODE_CROP_IMAGE = 4444;
    public static int REQUEST_CODE_START_FACEBOOK_LOGIN= 64206;

    public static int START_ACTIVITY_TO_MOVE_FROM_LIVECHALLENGE = 100;
    public static int START_ACTIVITY_TO_MOVE_FROM_LIVECHALLENGE_EXIT = 101;
    public static String KEY_LIVECHALLENGE_SHOWID = "KEY_LIVECHALLENGE_SHOWID";
    public static String KEY_LIVECHALLENGE_TOTAL = "KEY_LIVECHALLENGE_TOTAL";


    public static String USER_AVATAR_ME = "USER_AVATAR_ME";


    // SoloQuestionIntro
    public static String KEY_TYPE_OF_GAME= "KEY_TYPE_OF_GAME";
    public static String KEY_MINUS_GAME= "KEY_MINUS_GAME";
    public static String KEY_QUESTION_ID= "KEY_QUESTION_ID";
    public static String KEY_IMAGE_TOPIC= "KEY_IMAGE_TOPIC";
    public static String KEY_NAME_TOPIC= "KEY_NAME_TOPIC";
    public static String KEY_ANSWER_ID= "KEY_ANSWER_ID";
    public static String KEY_SOLO_MATCH_ID= "KEY_SOLO_MATCH_ID";
    public static String KEY_CHALLENGE_IS_OPPONENT= "KEY_CHALLENGE_IS_OPPONENT";
    public static String KEY_AVATAR_OPPONENT= "KEY_AVATAR_OPPONENT";
    public static String KEY_NAME_OPPONENT= "KEY_NAME_OPPONENT";
    public static String KEY_QUESTION_NUMBER= "KEY_QUESTION_NUMBER";
    public static String KEY_LAST_QUESTION= "KEY_LAST_QUESTION";
    public static String KEY_INTRODUCTION_VALUE= "KEY_INTRODUCTION_VALUE";
    public static int REQUEST_CODE_FROM_QUESTION_INTRO_ACTIVITY = 102;
    public static int RESULT_CODE_FROM_RIGHT_ANSWER = 103;
    public static int RESULT_CODE_FROM_RIGHT_ANSWER_USING_COINS = 104;

    public static String KEY_LIVE_CHALLENGE_VALUE= "KEY_LIVE_CHALLENGE_VALUE";
    public static String KEY_LIVE_CHALLENGE_ID_VALUE= "KEY_LIVE_CHALLENGE_ID_VALUE";

    public static String KEY_TOPIC_ID = "KEY_TOPIC_ID";
    public static String KEY_CATEROGY_ID = "KEY_CATEROGY_ID";
    public static String KEY_CATEROGY_VALUEKEY = "KEY_CATEROGY_VALUEKEY";
    public static String KEY_CATEROGY_NAME = "KEY_CATEROGY_NAME";



    public static String KEY_CORRECT_ANSWER = "1";
    public static int PLAY_GAME_SOLO = 0;
    public static int PLAY_GAME_CHALLENGE = 1;

    public static String KEY_CHALLENGE_TOTAL_RIGHT_ANSWER_ME = "KEY_CHALLENGE_TOTAL_RIGHT_ANSWER_ME";

    public static SocketManage socketManage;

    public static String CHALLENGE_TIME_COUNT_DOWN = "0";

    public static int MP3_WRONG_ANSWER = 0;
    public static int MP3_CORRECT_ANSWER = 1;

    public static String KEY_CHALLENGE_TO_ID = "KEY_CHALLENGE_TO_ID";
    public static String KEY_CHALLENGE_USER_ID = "KEY_CHALLENGE_USER_ID";
    public static String KEY_CHALLENGE_USER_BOT = "KEY_CHALLENGE_USER_BOT";

}
