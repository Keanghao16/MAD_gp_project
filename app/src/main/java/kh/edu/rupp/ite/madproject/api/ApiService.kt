package kh.edu.rupp.ite.madproject.api


import kh.edu.rupp.ite.madproject.model.ApiResponse
import kh.edu.rupp.ite.madproject.model.Profile
import retrofit2.http.GET

interface ApiService {

    @GET("profile.json")
    suspend fun loadProfileData(): ApiResponse<Profile>



}