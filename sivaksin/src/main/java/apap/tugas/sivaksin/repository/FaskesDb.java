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
    FaskesModel findByNamaFaskes(String namaFaskes);

    @Query(value = "Select * from faskes where vaksin_id = :idVaksin",nativeQuery = true)
    List<FaskesModel> findAllFaskesByIdVaksin(Long idVaksin);


}