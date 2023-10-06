package com.example.web.dataRepository;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "weather_data")
public class DataEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = true)
    private String city;

    @Column(name = "date", columnDefinition = "DATE")
    private LocalDate date ;

    @Column(name = "high_temperature", nullable = false)
    private String highTemperature;

    @Column(name = "low_temperature", nullable = false)
    private String lowTemperature;

    @Column(name = "carry_umbrella", nullable = false)
    private boolean carryUmbrella;

    @Column(name = "use_sunscreen", nullable = false)
    private boolean useSunscreen;

    // Define a self-referential relationship for weather entries
    @OneToMany(mappedBy = "parentDataEntity", cascade = CascadeType.ALL)
    private List<DataEntity> weatherEntries;

    // A reference to the parent data entity (if this is a weather entry)
    @ManyToOne
    @JoinColumn(name = "parent_data_entity_id")
    private DataEntity parentDataEntity;

    // Constructors, getters, setters, and other methods

    public DataEntity() {
        // Provide default values for highTemperature and lowTemperature if needed
        this.highTemperature = "N/A";
        this.lowTemperature = "N/A";
    }
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getHighTemperature() {
        return highTemperature;
    }

    public void setHighTemperature(String highTemperature) {
        this.highTemperature = highTemperature;
    }

    public String getLowTemperature() {
        return lowTemperature;
    }

    public void setLowTemperature(String lowTemperature) {
        this.lowTemperature = lowTemperature;
    }

    public boolean isCarryUmbrella() {
        return carryUmbrella;
    }

    public void setCarryUmbrella(boolean carryUmbrella) {
        this.carryUmbrella = carryUmbrella;
    }

    public boolean isUseSunscreen() {
        return useSunscreen;
    }

    public void setUseSunscreen(boolean useSunscreen) {
        this.useSunscreen = useSunscreen;
    }

    public List<DataEntity> getWeatherEntries() {
        return weatherEntries;
    }

    public void setWeatherEntries(List<DataEntity> weatherEntries) {
        this.weatherEntries = weatherEntries;
    }

    public DataEntity getParentDataEntity() {
        return parentDataEntity;
    }

    public void setParentDataEntity(DataEntity parentDataEntity) {
        this.parentDataEntity = parentDataEntity;
    }

    public void setDate() {
    }
}
