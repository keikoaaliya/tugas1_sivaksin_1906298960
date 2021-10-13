package apap.tugas.sivaksin.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "vaksin")
public class VaksinModel implements Serializable {
    @Id
    @Size(max = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVaksin;

    @NotNull
    @Column(name = "efikasi", nullable = false)
    private Double efikasi;

    @NotNull
    @Size(max = 255)
    @Column(name = "jenis_vaksin", nullable = false)
    private String jenisVaksin;

    @NotNull
    @Size(max = 255)
    @Column(name = "asal_negara", nullable = false)
    private String asalNegara;

    // Relasi dengan FaskesModel
    @OneToMany(mappedBy = "vaksin", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FaskesModel> listFaskes;

}
