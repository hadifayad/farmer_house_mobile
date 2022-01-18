package com.farmerhouse;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.ClientError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

public class NetworkHelper {

    public static final String TAG = NetworkHelper.class.getName();

    //    public static final String SERVER_IP = "10.0.2.2/wen-arkhas-web";
//    public static final String SERVER_IP = "5.189.150.68/room";

//    public static final String SERVER_IP = "192.168.0.158/room_web";
//    public static final String SERVER_IP = "192.168.0.158/room_web";
//    public static final String SERVER_IP = "192.168.43.196/room_web";
//    public static final String SERVER_IP = "localhost/farmer_house";
    public static final String SERVER_IP = "192.168.10.109/farmer_house";
//    public static final String SERVER_IP = "192.168.2.157/farmer_house";
//    public static final String SERVER_IP = "192.168.1.9/farmer_house";
    public static final String IMAGES_PATH_POST_PICTURES = "http://" + SERVER_IP + "/web/newsUploads/";
    public static final String IMAGES_PATH_DATA_PICTURES = "http://" + SERVER_IP + "/web/dataImages/";

    public static final String ACTION_SIGNUP_USER = "mobile/signup";
    public static final String ACTION_GET_VILLAGES = "mobile/get-villages";
    public static final String ACTION_GET_USER_CHATS = "mobile/get-user-chats";
    public static final String ACTION_GET_NEWS ="mobile/get-news";
    public static final String ACTION_GET_CHILDREN ="mobile/get-children";
    public static final String ACTION_GET_TOP_PARENT ="mobile/get-top-parent";
    public static final String ACTION_GET_TOP_PARENT_AFTER_PICKING_PLANT ="mobile/get-top-parent-after-picking-plant";
    public static final String ACTION_GET_PLANTS ="mobile/get-search-plants";
    public static final String ACTION_CREATE_CHAT ="mobile/create-chat";
    public static final String ACTION_GET_CHILDREN_AND_SAVE_MESSAGE = "mobile/get-children-and-save-message";
    public static final String ACTION_GET_CHAT_DATA = "mobile/get-chat-data";
    public static final String ACTION_LOGIN_USER = "mobile/login" ;
    public static final String ACTION_GET_ZERA3AT_TYPES = "mobile/get-data" ;
    public static final String ACTION_UPDATE_PROFILE = "mobile/update-profile" ;


    public static String getUrl(String action) {
        String serverURL = "http://" + SERVER_IP + "/web/index.php?r=";
        String url = serverURL + "api/" + action;

        return url;
    }


    public static void handleError(VolleyError error) {
        NetworkResponse response = error.networkResponse;
        String statusCode = "", message = "";
        //statCode
        if (response != null && response.data != null) {
            statusCode = ": " + Integer.toString(error.networkResponse.statusCode);
        }
        //message
        try {
            if (error instanceof NetworkError) {
                message = "networkError";
            } else if (error instanceof ClientError) {
                message = "clientError";
            } else if (error instanceof ServerError) {
                message = "serverError";
            } else if (error instanceof AuthFailureError) {
                message = "authFailureError";
            } else if (error instanceof ParseError) {
                message = "parseError";
            } else if (error instanceof NoConnectionError) {
                message = "noConnectionError";
            } else if (error instanceof TimeoutError) {
                message = "timeoutError";
            } else {
                message = "defaultError";
            }
            Log.d(TAG, "Network message error: " + message + " " + statusCode);

        } catch (Exception e) {

        }
    }

}
