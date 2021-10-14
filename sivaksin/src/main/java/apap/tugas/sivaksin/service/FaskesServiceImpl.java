package apap.tugas.sivaksin.service;

import apap.tugas.sivaksin.model.FaskesModel;
import apap.tugas.sivaksin.repository.FaskesDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FaskesServiceImpl implements FaskesService{
    @Autowired
    FaskesDb faskesDb;

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
        return faskesDb.findAll(Sort.by(Sort.Direction.ASC, "vaksin"));
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

//    @Override
//    public List<Long> test(String pasien) {
//        return faskesDb.findIdFaskesByPasien(pasien);
//    }
}
