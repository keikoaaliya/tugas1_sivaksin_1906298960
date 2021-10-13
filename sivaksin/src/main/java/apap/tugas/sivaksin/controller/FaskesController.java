package apap.tugas.sivaksin.controller;

import apap.tugas.sivaksin.model.FaskesModel;
import apap.tugas.sivaksin.model.PasienModel;
import apap.tugas.sivaksin.model.VaksinModel;
import apap.tugas.sivaksin.service.FaskesService;
import apap.tugas.sivaksin.service.PasienService;
import apap.tugas.sivaksin.service.VaksinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.model.IModel;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FaskesController {

    @Qualifier("faskesServiceImpl")
    @Autowired
    private FaskesService faskesService;

    @Autowired
    private VaksinService vaksinService;

    @Autowired
    private PasienService pasienService;

    @GetMapping("/faskes")
    public String viewAllFaskes(Model model) {
        List<FaskesModel> listFaskes = faskesService.getListFaskes();
        model.addAttribute("listFaskes", listFaskes);

        return "viewall-faskes";
    }

    @GetMapping("/faskes/tambah")
    public String addFaskesForm(Model model) {
        model.addAttribute("faskes", new FaskesModel());
        model.addAttribute("listVaksin", vaksinService.getListVaksin());
        return "tambah-faskes";
    }

    @PostMapping("faskes/tambah")
    public String addFaskesSubmit(
            @ModelAttribute FaskesModel faskes,
            Model model
    ) {
        faskesService.addFaskes(faskes);
        model.addAttribute("namaFaskes", faskes.getNamaFaskes());
        return "tambah-faskes-submit";
    }

    @GetMapping("/faskes/{idFaskes}")
    public String viewFaskes(
            @PathVariable Long idFaskes,
            Model model
    ) {
        FaskesModel faskes = faskesService.getFaskesByIdFaskes(idFaskes);

        if(faskes != null) {
            model.addAttribute("faskes", faskes);
            model.addAttribute("vaksin", faskes.getVaksin());
            model.addAttribute("listPasien", faskes.getListPasien());
        }
        return "view-faskes";
    }

    @GetMapping("/faskes/ubah/{idFaskes}")
    public String updateFaskesForm(
            @PathVariable Long idFaskes,
            Model model
    ) {
        FaskesModel faskes = faskesService.getFaskesByIdFaskes(idFaskes);
        model.addAttribute("listVaksin", vaksinService.getListVaksin());
        model.addAttribute("faskes", faskes);
        return "update-faskes";
    }

    @PostMapping("/faskes/ubah/{idFaskes}")
    public String updateFaskesSubmit(
            @ModelAttribute FaskesModel faskes,
            Model model
    ){
        FaskesModel faskesUpdate = faskesService.updateFaskes(faskes);

        if(faskesUpdate == null) {
            model.addAttribute("message", "Gagal mengubah faskes! Silahkan periksa waktu operasi faskes atau jumlah pasien!");
            model.addAttribute("faskesUpdate", null);
        }
        else {
            model.addAttribute("faskesUpdate", faskesUpdate);
        }
        return "update-faskes-submit";
    }

    @PostMapping("/faskes/hapus/{idFaskes}")
    public String deleteFaskes(
            @PathVariable Long idFaskes,
            Model model
    ) {
        FaskesModel target = faskesService.getFaskesByIdFaskes(idFaskes);
        FaskesModel faskes = faskesService.deleteFaskes(target);
        if(faskes == null) {
            model.addAttribute("message", "Gagal menghapus faskes! Silahkan periksa waktu operasi faskes atau jumlah pasien!");
            model.addAttribute("faskes", null);
        }
        else {
            model.addAttribute("faskes", faskes);
        }
        return "delete-faskes";
    }

    @GetMapping("/faskes/{idFaskes}/tambah/pasien")
    public String addPasienForm(
            @PathVariable Long idFaskes,
            Model model
    ){
        FaskesModel faskes = faskesService.getFaskesByIdFaskes(idFaskes);
        model.addAttribute("faskes", faskes);
        model.addAttribute("listPasien2", pasienService.getListAllPasien());
        return "tambah-faspas";
    }

    @PostMapping("/faskes/{idFaskes}/tambah/pasien")
    public String addPasienSubmit(
            @ModelAttribute FaskesModel faskes,
            Model model
    ) {
        return "tambah-faspas-submit";
    }

    @GetMapping("cari/faskes")
    private String cariFaskes(
//            @RequestMapping(value = "jenisVaksin") String jenisVaksin,
            Model model
    ) {
        return  null;
    }
}
