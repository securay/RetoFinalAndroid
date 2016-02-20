package la.hackspace.reto2.service;

import retrofit.RestAdapter;

/**
 * Created by SCURAY on 20/02/2016.
 */
public class APIImplementation {
    private static RestAdapter restAdapter = new RestAdapter.Builder()
            .setEndpoint("http://victorcasass.com/api/")
            .build();

    public  static APIService getService() {
        return  restAdapter.create(APIService.class);
    }
}
