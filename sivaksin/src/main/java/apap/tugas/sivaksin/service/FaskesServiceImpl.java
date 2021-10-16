package apap.tugas.sivaksin.service;

import apap.tugas.sivaksin.model.FaskesModel;
import apap.tugas.sivaksin.model.VaksinModel;
import apap.tugas.sivaksin.repository.FaskesDb;
import apap.tugas.sivaksin.repository.VaksinDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FaskesServiceImpl implements FaskesService{
    @Autowired
    FaskesDb faskesDb;

    @Autowired
    VaksinDb vaksinDb;

    @Override
    public List<FaskesModel> getListFaskes() {
        return faskesDb.findAll();
    }

    @Override
    public void addFaskes(FaskesModel faskesModel) {
        faskesDb.save(faskesModel);
    }

    @Override
    public FaskesModel getFaskesByIdFaskes(Long idFaskes) {
        Optional<FaskesModel> faskes = faskesDb.findByIdFaskes(idFaskes);
        if(faskes.isPresent()) return faskes.get();
        else return null;
    }

    @Override
    public FaskesModel updateFaskes(FaskesModel faskesModel) {
        Long idFaskes = faskesModel.getIdFaskes();
        // belum nambah validasi pasien
        if(validation(idFaskes) == true) {
            faskesDb.save(faskesModel);
        } else {
            return null;
        }
        return faskesModel;
    }

    @Override
    public FaskesModel deleteFaskes(FaskesModel faskesModel) {
        Long idFaskes = faskesModel.getIdFaskes();
        // belum nambah validasi pasien
        if(validation(idFaskes) == true) {
            faskesDb.deleteById(idFaskes);
        } else {
            return null;
        }
        return faskesModel;
    }

    @Override
    public List<FaskesModel> getListFaskesByJenisVaksin(String jenisVaksin) {
        VaksinModel vaksin = vaksinDb.findByJenisVaksin(jenisVaksin);
        return faskesDb.findAllFaskesByIdVaksin(vaksin.getIdVaksin());
    }

//    public List<FaskesModel> getListFaskesByMonth() {
//        return faskesDb.findByLastMonth();
//    }
//
//    public List<Integer> listCountPasienByMonth() {
//        return faskesDb.findByLastMonthCount();
//    }

    public List<Object> listByMonth() {
        return faskesDb.findByMonth();
    }

    public boolean validation(Long idFaskes) {
        FaskesModel target = getFaskesByIdFaskes(idFaskes);

        LocalTime waktuBuka = target.getJamMulai();
        LocalTime waktuTutup = target.getJamTutup();
        LocalTime currTime = LocalTime.now();

        boolean flag = false;
        int temp = waktuBuka.compareTo(waktuTutup);
        if(temp > 0) { // waktu tutup udh besok
            if(currTime.isBefore(waktuBuka) && currTime.isBefore(waktuTutup)) {
                flag = true;
            }
        } else {
            if(currTime.isAfter(waktuBuka) && currTime.isAfter(waktuTutup)) {
                flag = true;
            } else if(currTime.isBefore(waktuBuka) && currTime.isBefore(waktuTutup)) {
                flag = true;
            }
        }
        return flag;
    }
}
