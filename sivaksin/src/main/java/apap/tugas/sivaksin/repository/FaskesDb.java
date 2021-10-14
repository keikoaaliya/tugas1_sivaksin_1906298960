package apap.tugas.sivaksin.repository;

import apap.tugas.sivaksin.model.FaskesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FaskesDb extends JpaRepository<FaskesModel, Long> {
    Optional<FaskesModel> findByIdFaskes(Long idFaskes);
//    @Query(value = "select dp.id_faskes from dokter_pasien dp where dp.pasien_id in (select p.id_pasien from pasien p where p.nama_pasien = :pasien);", nativeQuery = true)
//    List<Long> findIdFaskesByPasien(String pasien);
}