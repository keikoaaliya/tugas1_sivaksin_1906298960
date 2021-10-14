package apap.tugas.sivaksin.repository;

import apap.tugas.sivaksin.model.DokterPasienModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DokterPasienDb extends JpaRepository<DokterPasienModel, Long> {
    Optional<DokterPasienModel> findAllByIdFaskes(Long idPasien);
}
