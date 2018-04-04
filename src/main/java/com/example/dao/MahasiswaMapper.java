package com.example.dao;

import com.example.model.FakultasModel;
import com.example.model.MahasiswaModel;
import com.example.model.ProgramStudiModel;
import com.example.model.UniversitasModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MahasiswaMapper {
    @Select("select * from mahasiswa where npm = #{npm}")
    MahasiswaModel selectMahasiswa(@Param("npm") String npm);

    @Select("SELECT s.tahun_masuk,s.nama_prodi,s.nama_fakultas,s.nama_univ,\n" +
            "sum(s.total) as total_mhs,\n" +
            "MAX(CASE WHEN status = 'Aktif' then s.total else NULL END) as 'Aktif',\n" +
            "MAX(CASE WHEN status = 'Drop Out' then s.total ELSE NULL END) as 'Drop_Out',\n" +
            "MAX(CASE WHEN status = 'Lulus' then s.total ELSE NULL END) as 'Lulus'\n" +
            "from\n" +
            "(SELECT m.tahun_masuk,\n" +
            "p.nama_prodi,\n" +
            "m.id_prodi,\n" +
            "f.nama_fakultas,\n" +
            "u.nama_univ,\n" +
            "m.status,\n" +
            "count(*) as total\n" +
            "FROM mahasiswa m\n" +
            "JOIN program_studi p ON m.id_prodi = p.id\n" +
            "JOIN fakultas f ON p.id_fakultas = f.id\n" +
            "JOIN universitas u ON f.id_univ = u.id\n" +
            "GROUP BY tahun_masuk,status,id_prodi) s\n" +
            "  WHERE s.tahun_masuk = #{tahun_masuk}and s.id_prodi = #{id_prodi}\n" +
            "GROUP BY tahun_masuk,id_prodi;")
    MahasiswaModel selectKelulusan(@Param("tahun_masuk") String tahun_masuk,
                                   @Param("id_prodi") Integer id_prodi);

    @Select("CALL sp_tambah_mahasiswa(#{nama},#{tempat_lahir}," +
            "#{tanggal_lahir},#{jenis_kelamin},#{agama},#{golongan_darah}," +
            "#{status},#{tahun_masuk},#{jalur_masuk},#{id_prodi})")
    void tambahMahasiswa(MahasiswaModel mahasiswa);

    @Update("UPDATE mahasiswa\n" +
            "SET nama=#{nama},tempat_lahir =#{tempat_lahir} ,\n" +
            "tanggal_lahir =#{tanggal_lahir} ,\n" +
            "jenis_kelamin =#{jenis_kelamin} ,\n" +
            "agama =#{agama} ,\n" +
            "golongan_darah =#{golongan_darah} ,\n" +
            "status =#{status} ,\n" +
            "tahun_masuk =#{tahun_masuk} ,\n" +
            "jalur_masuk =#{jalur_masuk} ,\n" +
            "id_prodi =#{id_prodi}\n" +
            "WHERE npm =#{npm};")
    void ubahMahasiswa(MahasiswaModel mahasiswa);

    @Select("SELECT m.*\n" +
            "FROM mahasiswa m\n" +
            "JOIN program_studi p ON m.id_prodi = p.id\n" +
            "JOIN fakultas f ON p.id_fakultas = f.id\n" +
            "JOIN universitas u ON f.id_univ = u.id\n" +
            "where p.id = #{id_prodi} and f.id = #{id_fakultas} and u.id = #{id_univ};")
    List<MahasiswaModel> cariMahasiswa(@Param("id_prodi") int id_prodi,
                       @Param("id_fakultas") int id_fakultas,
                       @Param("id_univ") int id_univ);
}
