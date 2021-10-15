package apap.tugas.sivaksin.controller;

import apap.tugas.sivaksin.model.DokterPasienModel;
import apap.tugas.sivaksin.model.FaskesModel;
import apap.tugas.sivaksin.model.PasienModel;
import apap.tugas.sivaksin.model.VaksinModel;
import apap.tugas.sivaksin.service.DokterPasienService;
import apap.tugas.sivaksin.service.FaskesService;
import apap.tugas.sivaksin.service.PasienService;
import apap.tugas.sivaksin.service.VaksinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PasienController {
    @Qualifier("pasienServiceImpl")
    @Autowired
    private PasienService pasienService;

    @Autowired
    private DokterPasienService dokterPasienService;

    @Autowired
    private FaskesService faskesService;

    @Autowired
    private VaksinService vaksinService;

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
            @ModelAttribute DokterPasienModel dokterPasienModel,
            Model model
    ){
        PasienModel pasien = pasienService.getPasienByIdPasien(idPasien);

        if(pasien!=null) {
            model.addAttribute("pasien", pasien);
            model.addAttribute("listPasienDokter", pasien.getListPasienDokter());
            model.addAttribute("listFaskes", pasien.getListFaskes());
//            System.out.println("============debugging===============");
//            for (DokterPasienModel i : pasien.getListPasienDokter()
//                 ) {
//                System.out.println("nama pasien " + i.getPasien().getNamaPasien());
//                System.out.println("batch id: " + i.getBatchId());
//            }

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

    @GetMapping("cari/pasien")
    public String cariPasien (
            @Param("jenisVaksin") String jenisVaksin,
            @Param("namaFaskes") String namaFaskes,
            Model model
    ){
        List<VaksinModel> listVaksin = vaksinService.getListVaksin();
        List<FaskesModel> listFaskes = faskesService.getListFaskes();
        VaksinModel vaksin = new VaksinModel();
        FaskesModel faskes = new FaskesModel();

        if(jenisVaksin != null && namaFaskes != null) {
            List<DokterPasienModel> listDokterPasien = dokterPasienService.getListPasienByJenisVaksinAndNamaFaskes(jenisVaksin, namaFaskes);
            model.addAttribute("listDokterPasien", listDokterPasien);
        }
        if(jenisVaksin != null){
            List<DokterPasienModel> listDokterPasien = dokterPasienService.getListPasienByJenisVaksin(jenisVaksin);
            model.addAttribute("listDokterPasien", listDokterPasien);
        }
        if(namaFaskes != null) {
            List<DokterPasienModel> listDokterPasien = dokterPasienService.getListPasienByNamaFaskes(namaFaskes);
            model.addAttribute("listDokterPasien", listDokterPasien);
        }

        model.addAttribute("listVaksin", listVaksin);
        model.addAttribute("listFaskes", listFaskes);

        model.addAttribute("vaksin", vaksin);
        model.addAttribute("faskes", faskes);
        return "cari-pasien";
    }
}
