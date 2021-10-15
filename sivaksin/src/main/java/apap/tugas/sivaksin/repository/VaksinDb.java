package apap.tugas.sivaksin.repository;

import apap.tugas.sivaksin.model.VaksinModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VaksinDb extends JpaRepository<VaksinModel, Long> {
    @Query(value = "select * from vaksin where jenis_vaksin = :jenis", nativeQuery = true)
    VaksinModel findByJenisVaksin(String jenis);

}
