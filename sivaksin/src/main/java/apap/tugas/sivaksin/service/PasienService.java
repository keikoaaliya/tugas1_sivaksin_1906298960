package apap.tugas.sivaksin.service;

import apap.tugas.sivaksin.model.PasienModel;

import java.util.List;

public interface PasienService {
    List<PasienModel> getListAllPasien();
    void addPasien(PasienModel pasienModel);
    PasienModel getPasienByIdPasien(Long idPasien);
    PasienModel updatePasien(PasienModel pasienModel);
}
