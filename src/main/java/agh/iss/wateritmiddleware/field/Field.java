package agh.iss.wateritmiddleware.field;

import agh.iss.wateritmiddleware.device.Device;
import agh.iss.wateritmiddleware.measurement.Measurement;
import agh.iss.wateritmiddleware.user.User;
import agh.iss.wateritmiddleware.waterrequirement.WaterRequirement;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

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
    @OneToOne
    private Device device;
    @Column(name = "device_id", insertable = false, updatable = false)
    private Long deviceId;
    @OneToOne(mappedBy = "field", cascade = CascadeType.ALL)
    private Measurement measurement;
    @OneToOne(mappedBy = "field", cascade = CascadeType.ALL)
    private WaterRequirement waterRequirement;
}
