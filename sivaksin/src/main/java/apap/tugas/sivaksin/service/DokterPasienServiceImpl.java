package apap.tugas.sivaksin.service;

import apap.tugas.sivaksin.model.DokterPasienModel;
import apap.tugas.sivaksin.model.FaskesModel;
import apap.tugas.sivaksin.model.PasienModel;
import apap.tugas.sivaksin.repository.DokterPasienDb;
import apap.tugas.sivaksin.repository.FaskesDb;
import apap.tugas.sivaksin.repository.PasienDb;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class DokterPasienServiceImpl implements DokterPasienService{
    @Autowired
    DokterPasienDb dokterPasienDb;

    @Autowired
    FaskesDb faskesDb;

    @Autowired
    PasienDb pasienDb;

    @Override
    public Boolean addDokterPasien(DokterPasienModel dokterPasienModel, Long idFaskes) {
        dokterPasienModel.setBatchId(generateBatchId(dokterPasienModel));

        LocalDateTime waktuSuntik = dokterPasienModel.getWaktuSuntik();
        LocalTime jamSuntik = waktuSuntik.toLocalTime();

        FaskesModel faskesModel = faskesDb.getById(dokterPasienModel.getIdFaskes());

        if(faskesModel.getListPasien() == null) {
            faskesModel.setListPasien(new ArrayList<PasienModel>());
        }
        faskesModel.getListPasien().add(dokterPasienModel.getPasien());
        faskesDb.save(faskesModel);

//        if (faskesModel.getIdFaskes() == idFaskes) {
//            FaskesModel faskes = new FaskesModel();
//            List<PasienModel> listPasien = new ArrayList<>();
//            listPasien.add(dokterPasienModel.getPasien());
//
//            faskes.setListPasien(listPasien);

//            faskes.setListPasien(new ArrayList<PasienModel>(dokterPasienModel.getPasien()));
//
//            pasien.getListFaskes().add(faskesModel);

//        }

        PasienModel pasienModel = pasienDb.getById(dokterPasienModel.getPasien().getIdPasien());
        if(pasienModel.getListFaskes() == null) {
            pasienModel.setListFaskes(new ArrayList<FaskesModel>());
        }
        pasienModel.getListFaskes().add(faskesModel);
        pasienDb.save(pasienModel);

//        List<FaskesModel> listFaskes = new ArrayList<>();
//        listFaskes.add(faskesModel);
//
//        PasienModel pasien = new PasienModel();
//        pasien.setListFaskes(listFaskes);



        LocalTime jamMulai = faskesModel.getJamMulai();
        LocalTime jamTutup = faskesModel.getJamTutup();

        Boolean isValid;
        if(jamSuntik.isBefore(jamTutup) && jamSuntik.isAfter(jamMulai)) {
            dokterPasienDb.save(dokterPasienModel);
            isValid = true;
        } else {
            isValid = false;
        }
        return isValid;
    }

    @Override
    public String generateBatchId(DokterPasienModel dokterPasienModel) {
        StringBuilder batchId = new StringBuilder("");

        if(dokterPasienModel.getPasien().getJenisKelamin() == 1) {
            batchId.insert(0, '1');
        }
        if(dokterPasienModel.getPasien().getJenisKelamin() == 0) {
            batchId.insert(0, '2');
        }

        String namaPasien = dokterPasienModel.getPasien().getNamaPasien().toUpperCase();
        char lastCharName = namaPasien.charAt(namaPasien.length()-1);
        batchId.insert(1, lastCharName);

        String namaTempat = dokterPasienModel.getPasien().getTempatLahir().toUpperCase();
        String duaHurufAwal = namaTempat.substring(0, 2);
        batchId.insert(2, duaHurufAwal);

        LocalDateTime dateTime = dokterPasienModel.getPasien().getTanggalLahir();
        batchId.insert(4, dateTime.getDayOfMonth());
        if(dateTime.getMonthValue() < 10) {
            batchId.insert(6, '0');
            batchId.insert(7, dateTime.getMonthValue());
        } else {
            batchId.insert(6, dateTime.getMonthValue());
        }

        int tahun = dateTime.getYear();
        int result = Math.floorDiv(tahun, 10);
        batchId.insert(8, result);

        int lowerLimit = 65;
        int upperLimit = 90;
        Random random = new Random();
        for(int i = 0; i < 2; i++) {
            int nextRandom = lowerLimit + (int)(random.nextFloat()*(upperLimit-lowerLimit));
            batchId.append((char)nextRandom);
        }

        return batchId.toString();
    }

    @Override
    public DokterPasienModel getDokterPasienByIdFaskes(Long idFaskes) {
        Optional<DokterPasienModel> dokterPasien = dokterPasienDb.findAllByIdFaskes(idFaskes);
        if(dokterPasien.isPresent()) return dokterPasien.get();
        else return null;
    }

//    @Override
//    public DokterPasienModel getPasienByIdFaskesInDokterPasien(DokterPasienModel dokterPasienModel) {
//        Optional<DokterPasienModel> pasien = dokterPasienDb.findByIdFaskes(dokterPasienModel.getIdFaskes());
//        if(pasien.isPresent()) {
//            return pasien.get();
//        } else {
//            return null;
//        }
//    }


}
