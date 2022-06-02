package com.vuquochung.foodshipper.remote;

import com.vuquochung.foodshipper.model.FCMResponse;
import com.vuquochung.foodshipper.model.FCMSenData;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IFCMService {
    @Headers({
            "Content-Type:application/json",
            "Authorization:key=AAAAnIeviu4:APA91bGKT5DVr-krdO6yMsJfhweaVuuZM3gCqGup4wqBfgciCr6ReRmOrCPCve1mE4GSTpBfT2WQV5QADUGbP3cdauKTTt9hx8XBYh_Cqs1NfiNTyZW1lT7kLeVUVn7gHf5xsMlfwCI1"

    })
    @POST("fcm/send")
    Observable<FCMResponse> sendNotification(@Body FCMSenData body);

}
