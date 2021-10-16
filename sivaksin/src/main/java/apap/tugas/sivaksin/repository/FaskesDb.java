package apap.tugas.sivaksin.repository;

import apap.tugas.sivaksin.model.FaskesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public interface FaskesDb extends JpaRepository<FaskesModel, Long> {
    Optional<FaskesModel> findByIdFaskes(Long idFaskes);
    FaskesModel findByNamaFaskes(String namaFaskes);

    @Query(value = "Select * from faskes where vaksin_id = :idVaksin",nativeQuery = true)
    List<FaskesModel> findAllFaskesByIdVaksin(Long idVaksin);

//    @Query(value = "select * from faskes f \n" +
//            "join pasien_faskes pf on pf.id_faskes = f.id_faskes \n" +
//            "join pasien p on p.id_pasien = pf.id_pasien \n" +
//            "join dokter_pasien dp on p.id_pasien = dp.pasien_id \n" +
//            "where dp.waktu_suntik > date_sub(now(), interval 1 month); ", nativeQuery = true)
//    List<FaskesModel> findByLastMonth();
//
//    @Query(value = "select count(*) from pasien p \n" +
//            "join dokter_pasien dp on dp.pasien_id = p.id_pasien \n" +
//            "join pasien_faskes pf on pf.id_pasien = p.id_pasien \n" +
//            "where dp.waktu_suntik > date_sub(now(), interval 1 month)\n" +
//            "group by p.id_pasien;", nativeQuery = true)
//    List<Integer> findByLastMonthCount();

    @Query(value = "select f.nama_faskes, count(distinct p.id_pasien) jumlah_pasien from faskes f \n" +
            "join pasien_faskes pf on pf.id_faskes = f.id_faskes \n" +
            "join pasien p on p.id_pasien = pf.id_pasien \n" +
            "join dokter_pasien dp on p.id_pasien = dp.pasien_id \n" +
            "where dp.waktu_suntik > date_sub(now(), interval 1 month)\n" +
            "group by f.nama_faskes; \n", nativeQuery = true)
    List<Object> findByMonth();
}