package apap.tugas.sivaksin.service;

import apap.tugas.sivaksin.model.DokterPasienModel;
import apap.tugas.sivaksin.model.FaskesModel;

import java.util.List;

public interface DokterPasienService {
    Boolean addDokterPasien(DokterPasienModel dokterPasienModel, Long idFaskes);
    String generateBatchId(DokterPasienModel dokterPasienModel);
    List<DokterPasienModel> getListPasienByNamaFaskes(String namaFaskes);
    List<DokterPasienModel> getListPasienByJenisVaksin(String jenisVaksin);
    List<DokterPasienModel> getListPasienByJenisVaksinAndNamaFaskes(String idVaksin, String idFaskes);
}

