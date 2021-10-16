package apap.tugas.sivaksin.controller;

import apap.tugas.sivaksin.model.*;
import apap.tugas.sivaksin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.model.IModel;

import javax.validation.Valid;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
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

    @Autowired
    private DokterService dokterService;

    @Autowired
    private DokterPasienService dokterPasienService;

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

    @GetMapping("/cari/faskes")
    private String cariFaskes(
            @Param("jenisVaksin") String jenisVaksin,
            Model model
    ) {
        List<VaksinModel> listVaksin = vaksinService.getListVaksin();
        VaksinModel vaksin = new VaksinModel();
        if(jenisVaksin != null){
            List<FaskesModel> listFaskes = faskesService.getListFaskesByJenisVaksin(jenisVaksin);
            model.addAttribute("listFaskes", listFaskes);
        }
        model.addAttribute("vaksin", vaksin);
        model.addAttribute("listVaksin", listVaksin);
        model.addAttribute("jenisVaksin",jenisVaksin);
        return "cari-faskes";
    }
}
