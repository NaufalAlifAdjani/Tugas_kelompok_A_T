import java.util.Scanner;

public class PengisianKRS {
        static int MAX_DATA = 0; // Maksimal data KRS yang dapat disimpan
        static String[][] dataKRS = new String[MAX_DATA][5]; // Array 2D untuk menyimpan data KRS
        static int jumlahData = 0; // Jumlah data yang sudah diinput
        static Scanner scanner = new Scanner(System.in);
    
        public static void main(String[] args) {
            int pilihan;
            do {
                System.out.println("\nMenu:");
                System.out.println("1. Tambah Data KRS");
                System.out.println("2. Tampilkan KRS Berdasarkan NIM");
                System.out.println("3. Analisis Mahasiswa dengan SKS < 20");
                System.out.println("4. Keluar");
                System.out.print("Pilih menu: ");
                pilihan = scanner.nextInt();
                scanner.nextLine(); // Membersihkan buffer
    
                switch (pilihan) {
                    case 1:
                        tambahDataKRS();
                        break;
                    case 2:
                        // tampilkanKRS();
                        break;
                    case 3:
                        // analisisSKS();
                        break;
                    case 4:
                        System.out.println("Keluar dari program.");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid.");
                }
            } while (pilihan != 4);
        }
    
        static void tambahDataKRS() {
            if (jumlahData > MAX_DATA) {
                System.out.println("Data KRS sudah penuh.");
                return;
            }
    
            System.out.print("Nama: ");
            String nama = scanner.nextLine();
            System.out.print("NIM: ");
            String nim = scanner.nextLine();
            System.out.print("Kode Mata Kuliah: ");
            String kodeMatkul = scanner.nextLine();
            System.out.print("Nama Mata Kuliah: ");
            String namaMatkul = scanner.nextLine();
            System.out.print("Jumlah SKS (1-3): ");
            int sks = scanner.nextInt();
    
            while (sks < 1 || sks > 3) {
                System.out.println("Jumlah SKS harus antara 1-3.");
                System.out.print("Masukkan kembali: ");
                sks = scanner.nextInt();
            }
            
        scanner.nextLine(); // Membersihkan buffer

        int totalSKS = hitungTotalSKS(nim);
        if (totalSKS + sks > 24) {
            System.out.println("Total SKS melebihi batas 24. Data tidak dapat ditambahkan.");
        } else {
            dataKRS[jumlahData][0] = nama;
            dataKRS[jumlahData][1] = nim;
            dataKRS[jumlahData][2] = kodeMatkul;
            dataKRS[jumlahData][3] = namaMatkul;
            dataKRS[jumlahData][4] = Integer.toString(sks);
            jumlahData++;
            System.out.println("Data KRS berhasil ditambahkan!");
        }
    }

    static int hitungTotalSKS(String nim) {
        int total = 0;
        for (int i = 0; i < jumlahData; i++) {
            if (dataKRS[i][1].equalsIgnoreCase(nim)) {
                total += Integer.parseInt(dataKRS[i][4]);
            }
        }
        return total;
    }

    static void tampilkanKRS() {
        System.out.print("Masukkan NIM: ");
        String nim = scanner.nextLine();
        int totalSKS = 0;
        boolean ditemukan = false;

        System.out.println("\nKRS Mahasiswa dengan NIM: " + nim);
        for (int i = 0; i < jumlahData; i++) {
            if (dataKRS[i][1].equalsIgnoreCase(nim)) {
                System.out.println("Kode Matkul: " + dataKRS[i][2] + ", Nama Matkul: " + dataKRS[i][3] +
                        ", SKS: " + dataKRS[i][4]);
                totalSKS += Integer.parseInt(dataKRS[i][4]);
                ditemukan = true;
            }
        }

        if (!ditemukan) {
            System.out.println("Data KRS tidak ditemukan.");
        } else {
            System.out.println("Total SKS: " + totalSKS);
        }
    }

    static void analisisSKS() {
        String[] mahasiswaSKS = new String[MAX_DATA];
        int jumlahMahasiswa = 0;

        for (int i = 0; i < jumlahData; i++) {
            String nim = dataKRS[i][1];
            if (hitungTotalSKS(nim) < 20 && !sudahTercatat(mahasiswaSKS, nim, jumlahMahasiswa)) {
                mahasiswaSKS[jumlahMahasiswa] = nim;
                jumlahMahasiswa++;
            }
        }

        System.out.println("\nMahasiswa dengan total SKS kurang dari 20:");
        if (jumlahMahasiswa == 0) {
            System.out.println("Tidak ada mahasiswa dengan total SKS kurang dari 20.");
        } else {
            for (int i = 0; i < jumlahMahasiswa; i++) {
                System.out.println("NIM: " + mahasiswaSKS[i]);
            }
        }
    }

    static boolean sudahTercatat(String[] mahasiswaSKS, String nim, int jumlahMahasiswa) {
        for (int i = 0; i < jumlahMahasiswa; i++) {
            if (mahasiswaSKS[i].equalsIgnoreCase(nim)) {
                return true;
            }
        }
        return false;
    }
}
