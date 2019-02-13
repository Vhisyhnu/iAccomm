package com.example.vhisyhnu.iaccomm.Chat.Fragments;

import com.example.vhisyhnu.iaccomm.Chat.Notification.MyResponse;
import com.example.vhisyhnu.iaccomm.Chat.Notification.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAAHu3Udj0:APA91bEmj0a4IDMSIA27PNvzIGMfqqkqV5pMaphr1keX4yXcSjKJUyUcPDDfjBUrzumZ7oW-slnXdLH-E1hkYrG9_xmqf6fMNf9_aZqlaGEmZR1yB0rV_j8LcqkvdFWl57JkfPrZ-3CH"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
