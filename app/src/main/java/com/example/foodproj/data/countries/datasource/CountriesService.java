package com.example.foodproj.data.countries.datasource;




import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface CountriesService {
    @GET("list.php?a=list")
    Observable<CountriesResponse> getCountries();
}
