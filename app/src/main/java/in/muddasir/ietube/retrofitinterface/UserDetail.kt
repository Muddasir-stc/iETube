package `in`.muddasir.ietube.retrofitinterface

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface UserDetail {
    @GET("checkAuthCode")
    fun checkDisplayAuthentication(
        @Header("Authorization") Authorization: String?,
        @Query("id") id: Int
    ): Call<ResponseBody?>?

    @GET("/api/display/{id}")
    fun getAuthCode(
        @Path("id") id: String?, @Query("passcode") passcode: String?,
        @Query("ipAddress") ipAddress: String?, @Query("plateform") plateform: String?
    ): Call<ResponseBody?>?

    @GET("/api/display/{id}")
    fun getData(
        @Path("id") id: String?,
        @Query("passcode") passcode: String?,
        @Query("ipAddress") ipAddress: String?,
        @Query("plateform") plateform: String?,
        @Header("Authorization") Authorization: String?
    ): Call<ResponseBody?>?

    @GET("prayertime")
    fun prayerTimings(
        @Header("Authorization") Authorization: String?,
        @Query("id") id: Int
    ): Call<ResponseBody?>?

    @GET("youtube/v3/search?")
    fun youTubeList(
        @Query("topicId") topicId: String?,
        @Query("maxResults") maxResults: Int,
        @Query("part") part: String?,
        @Query("key") key: String?
    ): Call<ResponseBody?>?

    @Streaming
    @GET
    fun downloadFileWithDynamicUrlAsync(@Url fileUrl: String?): Call<ResponseBody?>?
}
