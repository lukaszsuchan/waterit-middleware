package agh.iss.wateritmiddleware.measurement;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "measurement")
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private BigDecimal lightIntensity;
    private BigDecimal temperature;
    private BigDecimal humidity;
    private BigDecimal moistureHumidity;
    private BigDecimal rainfall;
    private BigDecimal airPurity;

}
