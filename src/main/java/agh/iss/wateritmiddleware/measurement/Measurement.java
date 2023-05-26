package agh.iss.wateritmiddleware.measurement;

import agh.iss.wateritmiddleware.field.Field;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
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
    @OneToOne
    private Field field;

    @AssertTrue(message = "Measurement can only be assigned to field")
    private boolean isValid() {
        return field != null;
    }
}
