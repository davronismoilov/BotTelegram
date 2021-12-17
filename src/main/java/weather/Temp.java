package weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Temp {
    /**
     * temp": 278.12,
     * "feels_like": 275.55,
     * "temp_min": 278.12,
     * "temp_max": 278.12,
     * "pressure": 1031,
     * "humidity": 65
     */

    float feels_like;
    int humidity;
    float temp;

}
