package to.nishant.shoppin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAApZRW36c:APA91bEvYU-16fVkfLSkJdG-CQo8koT4pVcm89QpFpCKSjXoqDG1Y03z_9ksKnenqtYEeKQgOJg1l9EdmsW8b9wZ_YvB90R1p48mGCBG-kbVw9rDQs_cA6X-k5_pzqo9nhJe168VCYlo" // Your server key refer to video for finding your server key
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotifcation(@Body NotificationSender body);
}
