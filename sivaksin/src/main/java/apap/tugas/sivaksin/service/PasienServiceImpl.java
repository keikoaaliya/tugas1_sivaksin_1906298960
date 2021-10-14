package apap.tugas.sivaksin.service;

import apap.tugas.sivaksin.model.FaskesModel;
import apap.tugas.sivaksin.model.PasienModel;
import apap.tugas.sivaksin.repository.PasienDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PasienServiceImpl implements PasienService{
    @Autowired
    PasienDb pasienDb;

    @Override
    public List<PasienModel> getListAllPasien() {
        return pasienDb.findAll();
    }

    @Override
    public void addPasien(PasienModel pasienModel) {
        pasienDb.save(pasienModel);
//        System.out.println("Tanggal Lahir:" + pasienModel.getTanggalLahir());
    }

    @Override
    public PasienModel getPasienByIdPasien(Long idPasien) {
        Optional<PasienModel> pasien = pasienDb.findByIdPasien(idPasien);
        if(pasien.isPresent()) return pasien.get();
        else return null;
    }

    @Override
    public PasienModel updatePasien(PasienModel pasienModel) {
        for(PasienModel i : getListAllPasien()) {
            if(i.getIdPasien() == pasienModel.getIdPasien()) {
                pasienDb.save(pasienModel);
            }
        }
        return pasienModel;
    }
}
