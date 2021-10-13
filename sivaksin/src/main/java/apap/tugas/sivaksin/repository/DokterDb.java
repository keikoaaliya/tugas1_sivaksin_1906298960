package apap.tugas.sivaksin.repository;

import apap.tugas.sivaksin.model.DokterModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DokterDb extends JpaRepository<DokterModel, Long> {

}
