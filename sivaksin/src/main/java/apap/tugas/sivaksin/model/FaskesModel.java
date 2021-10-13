package apap.tugas.sivaksin.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "faskes")
public class FaskesModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFaskes;

    @NotNull
    @Size(max=255)
    @Column(name = "nama_faskes", nullable = false)
    private String namaFaskes;

    @NotNull
    @Size(max=255)
    @Column(name = "kabupaten", nullable = false)
    private String kabupaten;

    @NotNull
    @Size(max=255)
    @Column(name = "provinsi", nullable = false)
    private String provinsi;

    @NotNull
    @Column(name = "kuota", nullable = false)
    private Integer kuota;

    @NotNull
    @Column(nullable = false)
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime jamMulai;

    @NotNull
    @Column(nullable = false)
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime jamTutup;

    // Relasi dengan VaksinModel
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "vaksin_id", referencedColumnName = "idVaksin", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private VaksinModel vaksin;

    // Relasi dengan PasienModel
    @ManyToMany(mappedBy = "listFaskes")
    List<PasienModel> listPasien;
}
