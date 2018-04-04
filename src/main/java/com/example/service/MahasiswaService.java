package com.example.service;

import com.example.dao.MahasiswaMapper;
import com.example.model.MahasiswaModel;

import java.util.List;

public interface MahasiswaService {

    MahasiswaModel selectMahasiswa (String npm);

    MahasiswaModel selectKelulusan (String tahun_masuk,Integer id_prodi);

    void tambahMahasiswa(MahasiswaModel mahasiswa);

    void ubahMahasiswa(MahasiswaModel mahasiswa);

    List<MahasiswaModel> cariMahasiswa(int id_prodi, int id_fakultas, int id_univ);
}
