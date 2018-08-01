package dazzi.com.nysl;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import dazzi.com.nysl.utils.Settings;
import dazzi.com.nysl.models.futApi.Matches;

public interface FutAPI {

    public static final String API_URL = Settings.API_URL;
    String key = Settings.API_KEY;

    @GET("competitions/2013/matches")
    Call<Matches> ListMatches(
            @Header("X-Auth-Token") String key,
            @Query("dateFrom") String date_From,
            @Query("dateTo") String date_To
    );

}
