package agh.iss.wateritmiddleware.device;

import agh.iss.wateritmiddleware.field.Field;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String externalDeviceId;
    private boolean active;
}
