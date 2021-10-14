package apap.tugas.sivaksin.controller;

import apap.tugas.sivaksin.model.DokterModel;
import apap.tugas.sivaksin.model.DokterPasienModel;
import apap.tugas.sivaksin.model.FaskesModel;
import apap.tugas.sivaksin.model.PasienModel;
import apap.tugas.sivaksin.service.DokterPasienService;
import apap.tugas.sivaksin.service.DokterService;
import apap.tugas.sivaksin.service.FaskesService;
import apap.tugas.sivaksin.service.PasienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class DokterPasienController {
    @Qualifier("dokterPasienServiceImpl")
    @Autowired
    private DokterPasienService dokterPasienService;

    @Autowired
    private FaskesService faskesService;

    @Autowired
    private PasienService pasienService;

    @Autowired
    private DokterService dokterService;

    @GetMapping("/faskes/{idFaskes}/tambah/pasien")
    public String addPasienForm(
            @PathVariable Long idFaskes,
            Model model
    ){
        FaskesModel faskes = faskesService.getFaskesByIdFaskes(idFaskes);
//        if(faskes.getListPasien() == null) {
//            faskes.setListPasien(new ArrayList<PasienModel>());
//        }
//        faskes.getListPasien().add(new PasienModel());

        model.addAttribute("faskes", faskes);
        model.addAttribute("listPasien2", pasienService.getListAllPasien());
        model.addAttribute("listDokter2", dokterService.getListDokter());
        model.addAttribute("dokterPasien", new DokterPasienModel());
//        model.addAttribute("listPasien3", pasienService.getListAllPasien());
        return "tambah-faspas";
    }

    @PostMapping("/faskes/{idFaskes}/tambah/pasien")
    public String addPasienSubmit(
            @PathVariable Long idFaskes,
            @ModelAttribute DokterPasienModel dokterPasienModel,
            Model model
    ) {

//        FaskesModel faskes = faskesService.getFaskesByIdFaskes(idFaskes);

        if(dokterPasienService.addDokterPasien(dokterPasienModel, idFaskes)) {
            model.addAttribute("dokterPasien", dokterPasienModel);
            model.addAttribute("pasien", dokterPasienModel.getPasien());
        } else {
            model.addAttribute("dokterPasien", false);
        }
        model.addAttribute("idFaskes", dokterPasienModel.getIdFaskes());
        return "tambah-faspas-submit";
    }
}
