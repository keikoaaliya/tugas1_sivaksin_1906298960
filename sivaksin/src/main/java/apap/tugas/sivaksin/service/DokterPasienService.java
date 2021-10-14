package apap.tugas.sivaksin.service;

import apap.tugas.sivaksin.model.DokterPasienModel;

public interface DokterPasienService {
    Boolean addDokterPasien(DokterPasienModel dokterPasienModel, Long idFaskes);
    String generateBatchId(DokterPasienModel dokterPasienModel);
//    DokterPasienModel getPasienByIdFaskesInDokterPasien(DokterPasienModel dokterPasienModel);
    DokterPasienModel getDokterPasienByIdFaskes(Long idFaskes);
}

