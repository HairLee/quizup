package com.elcom.com.quizupapp.ui.network

import com.elcom.com.quizupapp.ui.activity.model.entity.Person
import com.google.gson.JsonElement
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import java.util.ArrayList

/**
 * Created by admin on 3/8/2018.
 */
interface RestService {
    @POST("api/v1/anons/auth/phone/check")
    abstract fun checkPhoneNumber(@Body json: RequestBody): Call<JsonElement>

    @GET("api/volleyJsonObject")
    abstract fun findIdByPhone(): Call<Person>

    @GET("api/volleyJsonObject")
    abstract fun testJavaRx(): Single<List<Person>>

    @GET("api/volleyJsonObject")
    abstract fun testJavaRx2(): Single<Person>


    @GET("api/volleyJsonArray")
    abstract fun getMobiles(): Call<List<Person>>

    @DELETE("/api/v1/auth/groups/{groupID}/members")
    abstract fun deleteMemberGroup(@Path("groupID") groupId: Int, @Query("ids") idDelete: String): Call<Person>

    @Multipart
    @POST("api/v1/anons/ask")
    abstract fun uploadFileQuestion(@Part file: MultipartBody.Part,
                                    @Part("question1") questionOne: RequestBody,
                                    @Part("question2") questionTwo: RequestBody,
                                    @Part("question3") questionThree: RequestBody,
                                    @Part("question4") questionFour: RequestBody,
                                    @Part("email") email: RequestBody): Call<JsonElement>

    @Multipart
    @POST("/api/v1/auth/attachments/chat")
    abstract fun uploadChatFilePhoto(@Part files: ArrayList<MultipartBody.Part>,
                                     @Part("ChannelSid") channelSid: RequestBody): Call<JsonElement>

    @Multipart
    @POST("/api/user/bussiness_join")
    abstract fun uploadTTest(@Part files: ArrayList<MultipartBody.Part>,
                             @Part("data") data: RequestBody): Call<JsonElement>
}