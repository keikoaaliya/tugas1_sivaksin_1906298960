package apap.tugas.sivaksin.service;

import apap.tugas.sivaksin.model.FaskesModel;

import java.util.List;

public interface FaskesService {
    List<FaskesModel> getListFaskes();
    void addFaskes(FaskesModel faskesModel);
    FaskesModel getFaskesByIdFaskes(Long idFaskes);
    FaskesModel updateFaskes(FaskesModel faskesModel);
    FaskesModel deleteFaskes(FaskesModel faskesModel);
    List<FaskesModel> getListFaskesByJenisVaksin(String jenisVaksin);
//    List<Long> test(String test);
}
