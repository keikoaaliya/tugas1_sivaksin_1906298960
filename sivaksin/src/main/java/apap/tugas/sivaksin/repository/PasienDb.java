package apap.tugas.sivaksin.repository;

import apap.tugas.sivaksin.model.PasienModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasienDb extends JpaRepository<PasienModel, Long> {
    Optional<PasienModel> findByIdPasien(Long idPasien);
}
