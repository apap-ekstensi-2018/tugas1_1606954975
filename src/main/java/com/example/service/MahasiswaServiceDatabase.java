package com.example.service;

import com.example.dao.MahasiswaMapper;
import com.example.dao.MahasiswaMapper;
import com.example.model.MahasiswaModel;
import com.example.model.MahasiswaModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MahasiswaServiceDatabase implements MahasiswaService {

    @Autowired
    private MahasiswaMapper mahasiswaMapper;

    @Override
    public MahasiswaModel selectMahasiswa(String npm)
    {
        log.info("Select Mahasiswa with npm{}", npm);
        return mahasiswaMapper.selectMahasiswa (npm);
    }

    @Override
    public MahasiswaModel selectKelulusan(String tahun_masuk,Integer id_prodi){
        log.info("view kelulusan mahasiswa");
        return mahasiswaMapper.selectKelulusan(tahun_masuk,id_prodi);
    }

    @Override
    public void tambahMahasiswa(MahasiswaModel mahasiswa){
        log.info("Mahasiswa ditambah");
        mahasiswaMapper.tambahMahasiswa(mahasiswa);
    }

    @Override
    public void ubahMahasiswa(MahasiswaModel mahasiswa){
        log.info("Mahasiswa npm{} diubah", mahasiswa.getNpm());
        mahasiswaMapper.ubahMahasiswa(mahasiswa);
    }

    @Override
    public List<MahasiswaModel> cariMahasiswa(int id_prodi, int id_fakultas, int id_univ){
        log.info("view Mahasiswa");
        return mahasiswaMapper.cariMahasiswa(id_prodi,id_fakultas,id_univ);
    }
}
