package agh.iss.wateritmiddleware.waterrequirement;

import agh.iss.wateritmiddleware.field.Field;
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
@Table(name = "water_requirement")
public class WaterRequirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private BigDecimal value;
    @OneToOne
    private Field field;
}
