package apap.tugas.sivaksin.service;

import apap.tugas.sivaksin.model.VaksinModel;
import apap.tugas.sivaksin.repository.VaksinDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class VaksinServiceImpl implements VaksinService{

    @Autowired
    VaksinDb vaksinDb;

    @Override
    public List<VaksinModel> getListVaksin() { return vaksinDb.findAll(); }
}
