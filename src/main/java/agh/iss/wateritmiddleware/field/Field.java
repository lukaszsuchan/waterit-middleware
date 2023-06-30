package agh.iss.wateritmiddleware.field;

import agh.iss.wateritmiddleware.device.Device;
import agh.iss.wateritmiddleware.measurement.Measurement;
import agh.iss.wateritmiddleware.user.User;
import agh.iss.wateritmiddleware.waterrequirement.WaterRequirement;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "field")
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal area;
    @Enumerated(EnumType.STRING)
    private CropType actualCropType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "device_id", referencedColumnName = "id")
    private Device device;
    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    private List<Measurement> measurements;
    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    private List<WaterRequirement> waterRequirements;
}
