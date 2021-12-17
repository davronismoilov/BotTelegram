package country;

import country.Country;

import java.util.ArrayList;
@lombok.Data
public class Data {

    /**
     *  "error": false,
     *     "msg": "countries and cities retrieved",
     *     "data":
     */
    Boolean error ;
    String msg;
    ArrayList<Country> data;

}
