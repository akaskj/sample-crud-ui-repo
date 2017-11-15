package sample.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Min(value = 1950, message = "Year cannot be less than 1950")
    @Max(value = 2050, message = "Year cannot be greater than 2050")
    private int year;
    @NotNull(message = "Make cannot be empty")
    @NotEmpty(message = "Make cannot be empty")
    private String make;
    @NotNull(message = "Model cannot be empty")
    @NotEmpty(message = "Model cannot be empty")
    private String model;

    @JsonProperty("Id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @JsonProperty("Year")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @JsonProperty("Make")
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    @JsonProperty("Model")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}