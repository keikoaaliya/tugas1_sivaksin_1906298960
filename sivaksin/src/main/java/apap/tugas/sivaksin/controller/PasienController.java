package apap.tugas.sivaksin.controller;

import apap.tugas.sivaksin.model.FaskesModel;
import apap.tugas.sivaksin.model.PasienModel;
import apap.tugas.sivaksin.service.PasienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PasienController {
    @Qualifier("pasienServiceImpl")
    @Autowired
    private PasienService pasienService;

    @GetMapping("/pasien")
    public String viewAllPasien(Model model) {
        List<PasienModel> listAllPasien = pasienService.getListAllPasien();
        model.addAttribute("listAllPasien", listAllPasien);

        return "viewall-pasien";
    }

    @GetMapping("/pasien/tambah")
    public String addPasienForm(Model model) {
        model.addAttribute("pasien", new PasienModel());
        return "tambah-pasien";
    }

    @PostMapping("pasien/tambah")
    public String addPasienSubmit(
            @ModelAttribute PasienModel pasienModel,
            Model model
    ) {
        if(pasienModel.getTempatLahir().length() >= 2) {
            pasienService.addPasien(pasienModel);
            model.addAttribute("pasien", pasienModel);
        } else {
            model.addAttribute("pasien", null);
        }
        return "tambah-pasien-submit";
    }

    @GetMapping("/pasien/{idPasien}")
    public String viewPasien(
            @PathVariable Long idPasien,
            Model model
    ){
        PasienModel pasien = pasienService.getPasienByIdPasien(idPasien);

        if(pasien!=null) {
            model.addAttribute("pasien", pasien);
        }
        return "view-pasien";
    }

    @GetMapping("pasien/ubah/{idPasien}")
    public String updatePasienForm(
            @PathVariable Long idPasien,
            Model model
    ){
        PasienModel pasien = pasienService.getPasienByIdPasien(idPasien);
        model.addAttribute("pasien", pasien);
        return "update-pasien";
    }

    @PostMapping("pasien/ubah/{idPasien}")
    public String updatePasienSubmit(
            @ModelAttribute PasienModel pasien,
            Model model
    ){
        PasienModel pasienUpdate = pasienService.updatePasien(pasien);

        if(pasienUpdate == null) {
            model.addAttribute("pasienUpdate", null);
        } else {
            model.addAttribute("pasienUpdate", pasienUpdate);
        }
        return "update-pasien-submit";
    }
}
