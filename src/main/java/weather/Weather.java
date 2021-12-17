package weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class Weather {
    Temp main;
    Date dt;
    String name;



}
