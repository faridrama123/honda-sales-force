/**
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.langit7.hondasalesforce.FCM;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.langit7.hondasalesforce.R;
import com.langit7.hondasalesforce.view.activity.SplashActivity;


public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // [START_EXCLUDE]
        // There are two types of messages data messages and notification messages. Data messages are handled
        // here in onMessageReceived whether the app is in the foreground or background. Data messages are the producttype
        // traditionally used with GCM. Notification messages are only received here in onMessageReceived when the app
        // is in the foreground. When the app is in the background an automatically generated notification is displayed.
        // When the user taps on the notification they are returned to the app. Messages containing both notification
        // and data payloads are treated as notification messages. The Firebase console always sends notification
        // messages. For more see: https://firebase.google.com/docs/cloud-messaging/concept-options
        // [END_EXCLUDE]

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
//        Log.e(TAG, "From: " + remoteMessage.getFrom());
//        Log.e(TAG, "msgid: " + remoteMessage.getMessageId());
//        Log.e(TAG, "to: " + remoteMessage.getTo());
//
//        // Check if message contains a data payload.
//        Log.e(TAG, "Message " + remoteMessage.getData());
//        Log.e(TAG, "notif " + remoteMessage.getNotification().getBody() );
//        Log.e(TAG, "title notif " + remoteMessage.getNotification().getTitle() );

        sendNotification( remoteMessage.getNotification().getBody(),null);

//        if (remoteMessage.getData().size() > 0) {
//            Log.e(TAG, "Message data payload: " + remoteMessage.getData());
//
//            //String msg=remoteMessage.getData().get("message");
//
//
//            //if(msg!=null && msg.length()>0) {
//                String img = remoteMessage.getData().get("img_url");
//                String producttype = remoteMessage.getData().get("producttype");
//                if (img != null && img.length() > 0) {
//                    senImageNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody(),img,producttype);
////                    try {
////                        Gson gs = new Gson();
////                        fcmdata n = gs.fromJson(json, fcmdata.class);
////                        if (n != null) {
////                            sendNotification(n.getTitle());
////                            return;
////                        }
////                    } catch (JsonSyntaxException e) {
////                        e.printStackTrace();
////                    }
//                }else{
//                    Log.e("NOTIF","Json unparse:" + remoteMessage.getData().get("etc"));
//                }
//
//                //sendNotification(msg);
//            //}
//        }

        // Check if message contains a notification payload.
//        if (remoteMessage.getNotification() != null) {
//            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
//            sendNotification(remoteMessage.getNotification().getBody());
//        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
    // [END receive_message]

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     */
    private void sendNotification(String messageBody) {
        sendNotification(messageBody,null);
    }
    private void sendNotification(String messageBody, String title) {

        if(!broadcastIntent()) {
//            Log.e("NOTIF", "Showing Notif:" + messageBody);

            Intent intent = new Intent(this, SplashActivity.class);
            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            intent.putExtra("notif", true);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                    PendingIntent.FLAG_ONE_SHOT);

            if (title == null)
                title = "You have new message";


            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            Notification notif = null;

            Notification.Builder builder = new Notification.Builder(this)
                    .setContentIntent(pendingIntent)
                    .setContentTitle(title)
                    .setContentText(messageBody)
                    .setSmallIcon(R.drawable.iconapp_96x96);
//                    .setStyle(new Notification.BigPictureStyle().bigPicture(result));



            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                String channelId = this.getString(R.string.default_notification_channel_id);
                NotificationChannel channel = new NotificationChannel(channelId, title, NotificationManager.IMPORTANCE_DEFAULT);
                channel.setDescription("");
                channel.enableLights(true);
                channel.setImportance(NotificationManager.IMPORTANCE_HIGH);
                notificationManager.createNotificationChannel(channel);
                builder.setChannelId(channelId);

            }
            notif = builder.build();

            notif.flags |= Notification.FLAG_AUTO_CANCEL;
            notificationManager.notify(1, notif);


        }
    }



    private void senImageNotification(String title, String message, String urlImage, String type){
        new generatePictureStyleNotification(this,title, message, urlImage,type).execute();
    }

    public boolean broadcastIntent() {
        Intent intent = new Intent("got-message");
        intent.putExtra("message","msg");
        // We should use LocalBroadcastManager when we want INTRA app
        // communication
        return LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
    }
}
