package country;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Country {
    /**
     * "country": "Afghanistan",
     *             "cities":
     */

    String country;
    ArrayList<String> cities;


}
