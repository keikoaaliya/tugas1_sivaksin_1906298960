package apap.tugas.sivaksin.repository;

import apap.tugas.sivaksin.model.DokterPasienModel;
import apap.tugas.sivaksin.model.FaskesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DokterPasienDb extends JpaRepository<DokterPasienModel, Long> {

    @Query(value = "Select * from dokter_pasien where id_faskes = :idFaskes",nativeQuery = true)
    List<DokterPasienModel> findAllPasienByIdFaskes(Long idFaskes);

    @Query(value = "select * from dokter_pasien dp \n" +
            "join pasien p on p.id_pasien = dp.pasien_id \n" +
            "join pasien_faskes pf on pf.id_pasien = p.id_pasien \n" +
            "join faskes f on f.id_faskes = pf.id_faskes \n" +
            "join vaksin v on v.id_vaksin = f.vaksin_id \n" +
            "where v.id_vaksin = :idVaksin",nativeQuery = true)
    List<DokterPasienModel> findAllIdDokterPasienByIdVaksin(Long idVaksin);

    @Query(value = "select * from dokter_pasien dp \n" +
            "join pasien p on p.id_pasien = dp.pasien_id \n" +
            "join pasien_faskes pf on pf.id_pasien = p.id_pasien \n" +
            "join faskes f on f.id_faskes = pf.id_faskes \n" +
            "join vaksin v on v.id_vaksin = f.vaksin_id \n" +
            "where v.id_vaksin = :idVaksin and f.id_faskes = :idFaskes",nativeQuery = true)
    List<DokterPasienModel> findAllIdDokterPasienByIdVaksinAndIdFaskes(Long idVaksin, Long idFaskes);

}
