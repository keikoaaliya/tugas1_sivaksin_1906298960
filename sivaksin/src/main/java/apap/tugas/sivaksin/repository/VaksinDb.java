package apap.tugas.sivaksin.repository;

import apap.tugas.sivaksin.model.VaksinModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VaksinDb extends JpaRepository<VaksinModel, Long> {

}
