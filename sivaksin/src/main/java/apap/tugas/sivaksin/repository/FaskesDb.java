package apap.tugas.sivaksin.repository;

import apap.tugas.sivaksin.model.FaskesModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FaskesDb extends JpaRepository<FaskesModel, Long> {
    Optional<FaskesModel> findByIdFaskes(Long idFaskes);
}
