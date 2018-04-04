package com.example.controller;

import com.example.model.MahasiswaModel;
import com.example.model.ProgramStudiModel;
import com.example.service.MahasiswaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MahasiswaController {

    @Autowired
    MahasiswaService mahasiswaDAO;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

//    @RequestMapping("/mahasiswa/kelulusan")
//    public String add ()
//    {
//        return "form-add";
//    }

    @RequestMapping("/mahasiswa/tambah")
    public String add ()
    {
        return "tambahMahasiswa";
    }

    @RequestMapping("/mahasiswa")
    public String viewMahasiswa(Model model, @RequestParam(value = "npm", required = false) String npm) {
        MahasiswaModel mahasiswa = mahasiswaDAO.selectMahasiswa(npm);
        model.addAttribute("mahasiswa", mahasiswa);

        return "view-mahasiswa";
    }

    @RequestMapping("/mahasiswa/kelulusan")
    public String viewKululusan(Model model,
            @RequestParam(value = "tahun_masuk") String tahun_masuk,
            @RequestParam(value = "id_prodi") Integer id_prodi)
    {
        MahasiswaModel mahasiswa = mahasiswaDAO.selectKelulusan(tahun_masuk,id_prodi);
        model.addAttribute("mahasiswa", mahasiswa);
        model.addAttribute("tahun_masuk",tahun_masuk);
        model.addAttribute("id_prodi",id_prodi);
        return "viewKelulusan";
    }

    @RequestMapping(value = "/mahasiswa/tambah/submit", method = RequestMethod.POST)
    public String tmabahMahasiswa(
            @RequestParam(value = "nama", required = false) String nama,
            @RequestParam(value = "tempat_lahir", required = false) String tempat_lahir,
            @RequestParam(value = "tanggal_lahir", required = false) String tanggal_lahir,
            @RequestParam(value = "jenis_kelamin", required = false) int jenis_kelamin,
            @RequestParam(value = "agama", required = false) String agama,
            @RequestParam(value = "golongan_darah", required = false) String golongan_darah,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "tahun_masuk", required = false) String tahun_masuk,
            @RequestParam(value = "jalur_masuk", required = false) String jalur_masuk,
            @RequestParam(value = "id_prodi", required = false) int id_prodi,Model model)
    {
        MahasiswaModel mahasiswa = new MahasiswaModel(null,nama,tempat_lahir,tanggal_lahir,jenis_kelamin,agama,golongan_darah,
                "Aktif",tahun_masuk,jalur_masuk,id_prodi);
        mahasiswaDAO.tambahMahasiswa(mahasiswa);
        return "success-tambah";
    }

    @RequestMapping(value = "/mahasiswa/ubah/{npm}")
    public String ubahMahasiswa(Model model, @PathVariable(value="npm") String npm)
    {
        MahasiswaModel mahasiswa = mahasiswaDAO.selectMahasiswa(npm);

        if(mahasiswa != null){
            model.addAttribute("mahasiswa",mahasiswa);
            return "ubahMahasiswa";
        }else {
            model.addAttribute("npm",npm);
            return "not-found";
        }
    }

    @RequestMapping(value = "/mahasiswa/ubah/submit", method = RequestMethod.POST)
    public String ubahSubmit(
            @RequestParam(value = "npm", required = false) String npm,
            @RequestParam(value = "nama", required = false) String nama,
            @RequestParam(value = "tempat_lahir", required = false) String tempat_lahir,
            @RequestParam(value = "tanggal_lahir", required = false) String tanggal_lahir,
            @RequestParam(value = "jenis_kelamin", required = false) int jenis_kelamin,
            @RequestParam(value = "agama", required = false) String agama,
            @RequestParam(value = "golongan_darah", required = false) String golongan_darah,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "tahun_masuk", required = false) String tahun_masuk,
            @RequestParam(value = "jalur_masuk", required = false) String jalur_masuk,
            @RequestParam(value = "id_prodi", required = false) int id_prodi,Model model)
    {
        MahasiswaModel mahasiswa = new MahasiswaModel(npm,nama,tempat_lahir,tanggal_lahir,jenis_kelamin,agama,golongan_darah,
                status,tahun_masuk,jalur_masuk,id_prodi);
        mahasiswaDAO.ubahMahasiswa(mahasiswa);
        return "success-ubah";
    }

    @RequestMapping("/mahasiswa/cari")
    public String cariMahasiswa(Model model,
                                @RequestParam(value = "id_prodi") int id_prodi,
                                @RequestParam(value = "id_fakultas") int id_fakultas,
                                @RequestParam(value = "id_univ") int id_univ)
    {
        List<MahasiswaModel> mahasiswa = mahasiswaDAO.cariMahasiswa(id_prodi,id_fakultas,id_univ);
        model.addAttribute("mahasiswa",mahasiswa);

        return "cariMahasiswa";
    }

}
