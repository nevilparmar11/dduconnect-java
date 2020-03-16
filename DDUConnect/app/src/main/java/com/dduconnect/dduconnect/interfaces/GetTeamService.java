package com.dduconnect.dduconnect.interfaces;

import com.dduconnect.dduconnect.AllTeamModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetTeamService {

    @GET("/DDUConnect/json/teams.json")
    Call<List<AllTeamModel>> getAllTeams();
}
