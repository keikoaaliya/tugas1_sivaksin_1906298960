package apap.tugas.sivaksin.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "pasien")
public class PasienModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPasien;

    @NotNull
    @Size(max=255)
    @Column(name="nama_pasien", nullable = false)
    private String namaPasien;

    @NotNull
    @Size(max=16)
    @Column(name="nik", nullable = false)
    private String nik;

    @NotNull
    @Size(max=13)
    @Column(name="nomor_telepon", nullable = false)
    private String noTelepon;

    @NotNull
    @Column(name = "jenis_kelamin", nullable = false)
    private Integer jenisKelamin;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "tanggal_lahir", nullable = false)
    private LocalDateTime tanggalLahir;

    @NotNull
    @Size(max=255)
    @Column(name = "tempat_lahir", nullable = false)
    private String tempatLahir;

    @NotNull
    @Size(max=255)
    @Column(name = "pekerjaan", nullable = false)
    private String pekerjaan;

    // relasi dengan FaskesModel
    @ManyToMany
    @JoinTable(
            name = "pasien_faskes",
            joinColumns = @JoinColumn(name = "id_pasien"),
            inverseJoinColumns = @JoinColumn(name = "id_faskes")
    )
    List<FaskesModel> listFaskes;
}
