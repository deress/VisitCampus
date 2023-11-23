package com.dicoding.visitcampus.data.model

import com.dicoding.visitcampus.R

object UniversityData {
    val dummyUniversity = listOf(
        University(1,
            "Institut Teknologi Bandung",
            "Institut Teknologi Bandung adalah sebuah perguruan tinggi negeri yang berkedudukan di Kota Bandung. Institut Teknologi Bandung (ITB) merupakan sekolah tinggi teknik pertama di Indonesia yang didirikan pada tanggal 2 Maret 1959 yang mengemban misi pengabdian ilmu pengetahuan dan teknologi untuk memajukan Indonesia. Lahir dalam suasana penuh dinamika yang dilandasi dengan semangat perjuangan Proklamasi Kemerdekaan, ITB hadir untuk mengoptimalkan pembangunan bangsa yang maju dan bermartabat.\n" +
                    "ITB telah memiliki 27 program studi yang terakreditasi secara internasional (sebelas dari ABET, sebelas dari ASIIN). Rektor ITB saat ini adalah Prof. Ir. N. R. Reini Djuhraeni Wirahadikusuma, MSCE, PhD.",
            R.drawable.logo_institut_teknologi_bandung,
            R.drawable.cover_institut_teknologi_bandung,
            -6.89261334618102,
            107.610389453164,
            listOf(
                CollegeAchievement(1, "Juara 1 Di Hati", "Juni 2023"),
                CollegeAchievement(2, "Juara 1 LOMBA 17an", "Agustus 2023"),
            ),
            listOf(
                AlumnusProfile(1, "Joko Anwar", "Tahun Kelulusan 1999", "Sutradara"),
                AlumnusProfile(2, "Ridwan Kamil", "Tahun Kelulusan 1995", "Gubernur Jawa Barat"),
                AlumnusProfile(3, "Pandji Pragiwaksono", "Tahun Kelulusan 1997", "Stand Up Comedian")
            ),
            listOf(
                RegistrationPath(1, "Seleksi Mandiri ITB (SM-ITB)", "Seleksi Mandiri ITB (SM-ITB) diprogramkan sebagai jalur penerimaan mahasiswa program sarjana yang akan berjalan tanpa subsidi biaya. Mahasiswa yang diterima di ITB melalui program Seleksi Mandiri ITB (SM-ITB) bertanggung jawab untuk membiayai secara penuh pendidikan program sarjananya di ITB.\n" +
                        "Hasil Ujian Seleksi (daring), Nilai UTBK, dan Nilai Rapor bagi pendaftar ke fakultas/sekolah selain FSRD\n" +
                        "Hasil Ujian Seleksi (daring), Nilai UTBK, Nilai Rapor dan Hasil Tes Keterampilan Seni Rupa (daring), bagi pendaftar ke FSRD"),
            ),
            listOf(
                Faculty("Fakultas Matematika dan Ilmu Pengetahuan Alam",
                    listOf(
                        Major("Fisika" , "S1","Unggul"),
                        Major("Astronomi" , "S1","Unggul"),
                        Major("Kimia" , "S1","Unggul"),
                        Major("Aktuaria" , "S1","Baik"),
                    )
                ),
                Faculty("Fakultas Teknik Pertambangan dan Perminyakan",
                    listOf(
                        Major("Teknik Pertambangan" , "S1","Unggul"),
                        Major("Teknik Perminyakan" , "S1","Unggul"),
                        Major("Teknik Geofisika" , "S1","Unggul"),
                        Major("Teknik Metalurgi" , "S1","Unggul"),
                    )
                ),
            ),
        ),


        University(2,
            "Universitas Hasanuddin",
            "Universitas Hasanuddin (Unhas) adalah sebuah perguruan tinggi yang terletak di Makassar, Sulawesi Selatan, Indonesia. Didirikan pada tahun 1956, Unhas menjadi salah satu universitas terkemuka di Indonesia. Kampus ini menawarkan beragam program studi di tingkat sarjana, magister, dan doktor, melibatkan mahasiswa dalam berbagai bidang ilmu.\n" +
                    "Unhas dikenal karena komitmennya terhadap penelitian dan pengembangan, dengan fokus pada pembangunan berkelanjutan, keberlanjutan lingkungan, dan inovasi. Kampus ini memiliki fasilitas modern, termasuk laboratorium, perpustakaan, dan pusat penelitian yang mendukung aktivitas akademis dan riset.",
            R.drawable.logo_universitas_hasanuddin,
            R.drawable.foto_homepage,
            -5.13476861284853,
            119.487858913438,
            listOf(
                    CollegeAchievement(3, "Juara 1 Lagi", "November 2023"),
                    CollegeAchievement(4, "Juara 1 Hadroh", "Januari 2023"),
                ),
            listOf(
                AlumnusProfile(4, "Muhammad Sapri Pamulu, Ph.D", "Fakultas Pokok Lulus 2005", "CEO at PT Indah Karya Persero (BUMN)"),
            ),
            listOf(
                RegistrationPath(2,
                    "Jalur Non Subsidi (JNS) UTBK",
                    "Jalur ini menerapkan penliaian berdasarkan skor UTBK-SNBT 2023 dan satu nilai materi ujian yang diselenggarakan oleh UNHAS. Pendaftaran jalur JNS UTBK dimulai pada 21 – 30 Juni 2023."
                ),
                RegistrationPath(3,
                    "Jalur Non Subsidi (JNS) Non UTBK",
                    "\"Penilaiannya berdasarkan nilai ujian yang diselenggarakan oleh UNHAS tanpa memperhitungkan nilai UTBK-SNBT. Jalur ini mulai membuka pendaftaran pada 2 Mei – 30 Juni 2023. "
                ),
                RegistrationPath(4,
                    "POSK (Prestasi Olahraga, Seni, dan Keilmuan)",
                    "Jalur untuk menjaring calon mahasiswa baru yang punya prestasi akademik, olahraga, dan keilmuan. Penilaian di jalur POSK berdasarkan skor UTBK-SNBT 2023 dan nilai ujian yang diselenggarakan UNHAS."
                ),
            ),
            listOf(
                Faculty("Fakultas Kedokteran",
                    listOf(
                        Major("Kedokteran" , "S1","A"),
                        Major("Kedokteran Hewan" , "S1","B"),
                        Major("Psikologi" , "S1","A"),
                    )
                ),
                Faculty("Fakultas Ekonomi dan Bisnis",
                    listOf(
                        Major("Akuntansi" , "S1","Unggul"),
                        Major("Eknomi Pembangunan" , "S1","Unggul"),
                        Major("Manajemen" , "S1","Unggul"),
                    ),
                ),
            )
        )
    )

    val dummyFacultyMajor: List<Any> =  listOf(
        "Fakultas Kedokteran",
        Major("Kedokteran" , "S1","A"),
        Major("Kedokteran Hewan" , "S1","B"),
        Major("Psikologi" , "S1","A"),
        "Fakultas Ekonomi dan Bisnis",
        Major("Akuntansi" , "S1","Unggul"),
        Major("Eknomi Pembangunan" , "S1","Unggul"),
        Major("Manajemen" , "S1","Unggul"),
    )
}