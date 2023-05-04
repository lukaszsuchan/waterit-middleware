package agh.iss.wateritmiddleware.field;

import agh.iss.wateritmiddleware.measurement.Measurement;
import agh.iss.wateritmiddleware.user.User;
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
    @Enumerated(EnumType.ORDINAL)
    private CropType actualCropType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    private List<Zone> zones;
    @OneToOne(cascade = CascadeType.ALL)
    private Measurement measurement;
}
