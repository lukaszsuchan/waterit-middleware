package agh.iss.wateritmiddleware.field;

import agh.iss.wateritmiddleware.measurement.Measurement;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "zone")
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal area;
    @Enumerated(EnumType.STRING)
    private CropType actualCropType;
    private Boolean autoSeperated;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "field_id")
    private Field field;
//    @OneToOne(cascade = CascadeType.ALL)
//    private MeasurementDevice measurementDevice;
    @OneToOne(cascade = CascadeType.ALL)
    private Measurement measurement;

}
