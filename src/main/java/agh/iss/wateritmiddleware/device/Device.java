package agh.iss.wateritmiddleware.device;

import agh.iss.wateritmiddleware.field.Zone;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "device")
public class Device {

    boolean active;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String externalDeviceId;
    @OneToOne(mappedBy = "device", orphanRemoval = true)
    private Zone zone;
}
