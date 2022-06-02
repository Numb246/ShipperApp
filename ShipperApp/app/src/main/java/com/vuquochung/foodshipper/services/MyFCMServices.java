package com.vuquochung.foodshipper.services;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.vuquochung.foodshipper.common.Common;
import com.vuquochung.foodshipper.model.eventbus.UpdateShippingOrderEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;
import java.util.Random;

public class MyFCMServices extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        Map<String,String> dataRecv=message.getData();
        if(dataRecv!=null)
        {
            Common.showNotification(this,new Random().nextInt(),
                    dataRecv.get(Common.NOTI_TITLE),
                    dataRecv.get(Common.NOTI_CONTENT),
                    null);
            EventBus.getDefault().postSticky(new UpdateShippingOrderEvent());
        }
    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        Common.updateToken(this,s,false,true);
    }
}
