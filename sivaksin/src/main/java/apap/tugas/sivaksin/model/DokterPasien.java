package apap.tugas.sivaksin.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;

@Embeddable
class DokterPasienKey implements Serializable {

    @Column(name = "pasien_id")
    Long pasienId;

    @Column(name = "dokter_id")
    Long dokterId;
}

@Entity
public class DokterPasien implements Serializable {
    @EmbeddedId
    DokterPasienKey idTest;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "batch_id", nullable = false)
    private String batchId;

    @NotNull
    @Column(name = "waktu_suntik", nullable = false)
    private Timestamp waktuSuntik;

    @NotNull
    @Column(name = "id_faskes", nullable = false)
    private Long idFaskes;

    @ManyToOne
    @MapsId("pasienId")
    @JoinColumn(name = "pasien_id")
    PasienModel pasien;

    @ManyToOne
    @MapsId("dokterId")
    @JoinColumn(name = "dokter_id")
    DokterModel dokter;

}