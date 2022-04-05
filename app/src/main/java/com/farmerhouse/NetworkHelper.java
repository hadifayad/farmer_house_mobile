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
//    public static final String SERVER_IP = "192.168.0.109/farmer_house";
//    public static final String SERVER_IP = "localhost/farmer_house";
    public static final String SERVER_IP = "wonderful-archimedes.194-60-87-55.plesk.page/farmer_house";
//    public static final String SERVER_IP = "192.168.2.157/farmer_house";
//    public static final String SERVER_IP = "192.168.1.9/farmer_house";
    public static final String IMAGES_PATH_POST_PICTURES = "https://" + SERVER_IP + "/web/newsUploads/";
    public static final String IMAGES_PATH_DATA_PICTURES = "https://" + SERVER_IP + "/web/dataImages/";

    public static final String ACTION_SIGNUP_USER = "mobile/signup";
    public static final String ACTION_GET_VILLAGES = "mobile/get-villages";
    public static final String ACTION_GET_USER_CHATS = "mobile/get-user-chats";
    public static final String ACTION_GET_NEWS ="mobile/get-news";
    public static final String ACTION_GET_CHILDREN ="mobile/get-children";
    public static final String ACTION_GET_TOP_PARENT ="mobile/get-top-parent";
    public static final String ACTION_GET_USER_CHATS_WITH_MANDOUB ="mobile/get-user-chats-with-mandoub";
    public static final String ACTION_GET_TOP_PARENT_AFTER_PICKING_PLANT ="mobile/get-top-parent-after-picking-plant";
    public static final String ACTION_GET_PLANTS ="mobile/get-search-plants";
    public static final String ACTION_CREATE_CHAT ="mobile/create-chat";
    public static final String ACTION_GET_CHILDREN_AND_SAVE_MESSAGE = "mobile/get-children-and-save-message";
    public static final String ACTION_GET_CHAT_DATA = "mobile/get-chat-data";
    public static final String ACTION_LOGIN_USER = "mobile/login" ;
    public static final String ACTION_GET_ZERA3AT_TYPES = "mobile/get-data" ;
    public static final String ACTION_UPDATE_PROFILE = "mobile/update-profile" ;
    public static final String ACTION_GET_PROFILE_DATA = "mobile/get-profile-data" ;
    public static final String ACTION_ADD_ACTIVITY = "mobile/add-activity" ;
    public static final String ACTION_GET_USER_ACTIVITIES ="mobile/get-user-activities" ;
    public static final String ACTION_GET_CHAT_COMMENTS ="mobile/get-comments-by-post" ;
    public static final String ACTION_CREATE_CHAT_WITH_MANDOOB = "mobile/create-chat-with-mandoub";
    public static final String ACTION_AD_COMMENT ="mobile/add-comment";
    public static final String ACTION_GET_MANDOUB_FARMERS = "mobile/get-mandoub-farmers";
    public static final String ACTION_GET_ACTIVITIES_TYPES = "mobile/get-activities-types";
    public static final String ACTION_GET_MANDOUB_CHATS_WITH_USERS ="mobile/get-mandoub-chats-with-users";
    public static final String CREATE_MANDOUB_ACTIVTY =  "mobile/create-mandoub-activity" ;
    public static final String GET_MANDOUB_ACTIVTIES =  "mobile/get-mandoob-activities" ;
    public static final String GET_MANDOUB_AKEDS =  "mobile/get-mandoob-akeds" ;
    public static final String CREATE_MANDOUB_AKED = "mobile/create-mandoub-aked" ;
    public static final String CREATE_FARMER_AKED ="mobile/create-farmer-aked" ;
    public static final String GET_FARMER_AKEDS =  "mobile/get-farmer-akeds"    ;
    public static final String GET_MANDOUB_FARMER_AKEDS ="mobile/get-mandoub-farmer-akeds"  ;
    public static final String GET_FARMER_OFFICIAL_AKEDS = "mobile/get-farmer-official-akeds"  ;
    public static final String ACTION_UPDATE_TOKEN = "mobile/update-token"  ;

    public static String getUrl(String action) {
        String serverURL = "https://" + SERVER_IP + "/web/index.php?r=";
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
