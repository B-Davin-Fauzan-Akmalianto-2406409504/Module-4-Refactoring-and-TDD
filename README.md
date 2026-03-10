# Reflection 1 -- Module 1
- Dari segi clean code, saya sudah berusaha mengaplikasikannya, yaitu yang paling simple adalah penulisan variable pada function editProduct ataupun deleteProduct. Dalam function editProduct, saya dengan jelas membedakan variable productLama dengan productBaru agar terlihat jelas perbedaan antara keduanya.
- Untuk secure coding standards, saya mengganti type input pada quantity product yang tadinya text menjadi number. Hal ini membuat isian tersebut hanya dapat diisi oleh angka.
- Masih banyak kesalahan yang saya lakukan, terutama pada secure coding standard, dimana saya kurang melakukan validasi input terhadap nama produk. Kemudian, mungkin ada beberapa ketidakkonsistenan dalam penamaan variable, seperti kadang id, kadang productId, dan semacamnya.

# Reflection 2 -- Module 1
- Saya rasa untuk segi banyaknya unit test sudah cukup untuk sekarang, karna test sudah hampir mencakup semua function dan/atau atribut. Pada function edit dan delete juga sudah dibuat test saat kondisi gagal ataupun sukses.
- Kalau code coverage mencapai 100%, itu hanya memastikan kode berjalan sesuai apa yang ditulis, bukan berarti akan sesuai yang kita inginkan. Bisa juga ada edge cases yang lupa ditest namun tetap memberikan coverage 100%.
- Sebenarnya function test yang ku buat itu belum efisien, karena setupnya tidak difokuskan ke satu file saja melainkan semua file punya setup masing masing. Seharusnya, setup dilakukan sekali dalam satu file, kemudian test2 lainnya bisa inherit setup tersebut sehingga dapat mengefisienkan kode. 

# Reflection -- Module 2
- Saya memperbaiki warning yang berlabel Blocker, hal itu terjadi karna dalam EshopApplicationTests saya sebelumnya, saya hanya menjalankan fungsi main() saja tanpa menyertakan assertion apapun, sehingga SonarQube mendeteksi code smell dan setelah saya menambahkan assertion, warningnya pun hilang.
- Menurut saya sudah, karena file ci.yml, scorecard.yml, dan sonarcloud.yml sudah mewakili bagian CI dengan mengetes dan memindai kode. Sementara itu, di dalam file deploy.yml ada bagian yang mengeksekusi deployment ke Heroku. Jadi, definisi CI dan CD sudah terpenuhi disini.   

# Reflection -- Module 3
## SRP -- Single Responsibility Principle
- Sebuah principle yang mengatakan bahwa suatu class itu seharusnya mempunyai satu (sedikit) tanggung jawab yang spesifik, agar programmer tidak bingung dan pusing ketika membaca code. Disini, saya memisahkan CarController dari ProductController.java karena yang mereka lakukan sangat berbeda dan tidak ada hubungannya.
## OCP -- Open Closed Principle
- Sebuah principle yang mengatakan bahwa suatu kode itu seharusnya terbuka terhadap extension, namun tertutup terhadap modifikasi. Disini, saya membuat CarRepositoryInterface, agar jika suatu saat ingin ditambahkan fitur dimana repo terhubung ke database, tinggal mengimplement interface ini dan mengoverride fungsi fungsinya di file yang separate.
## LSP -- Liskov Substitution Principle
- Sebuah principle yang mengatakan bahwa suatu subclass harus dapat menggantikan peran superclassnya dimanapun itu. Disini, saya tidak secara eksplisit menerapkannya, namun dengan dipisahnya CarController dari yang tadinya meng-extend ProductController (padahal CarController tidak mungkin bisa mengsubstitute ProductController) saya kira itu sudah cukup menggambarkan.
## ISP -- Interface Segregation Principle
- Sebuah principle yang mengatakan bahwa jangan menggunakan satu interface besar, namun gunakanlah interface masing-masing dengan skala yang lebih kecil supaya tidak ada fungsi redundant yang diimplementasikan di class yang tidak membutuhkannya. Disini, CarRepositoryInterface sudah terpisah dengan yang produk sehingga Car tidak perlu mengimplement fungsi Product, dan juga sebaliknya.
## DIP -- Dependency Inversion Principle
- Sebuah principle yang mengatakan bahwa intinya modul Service tidak boleh bergantung ke Repository, melainkan harus bergantung ke Interface aja, agar ketika ingin ganti repo, Service tidak rusak dan perlu diganti. Disini, CarServiceImpl sudah depend ke CarRepositoryInterface.

# Reflection -- Module 4
- Unit test yang saya buat seharusnya sudah mencukupi lingkup fitur yang diminta oleh soal, dan edge cases sudah terpenuhi (seperti delete ketika id not found).
- Saya yakin unit test yg saya buat sudah sangat sesuai untuk cases yang diperlukan, sehingga kalopun refactor saya tidak khawatir akan fail testnya (kecuali emang salah pas refactor)
- Sejujurnya, saya tidak terlalu menyukai TDD ini, karena saya kurang terbayang apa saja test yang diperlukan ketika bahkan fungsinya dalam bentuk kode aja belum ada. Namun, saya mengerti kelebihan dari TDD ini, yaitu edge cases yang sebelumnya mungkin tidak terpikirkan ketika langsung bikin function akan langsung ke eliminasi, dan akan sangat membantu mengurangi bug ketika launch pertama kali. Jadi, mungkin kedepannya saat bikin test saya akan lebih melek terhadap edge cases.
## F.I.R.S.T
### Fast 
- Unit test sudah berjalan dengan cepat, apalagi saya menggunakan @Mock sehingga tidak perlu input output ke db asli.
### Independent
- Setiap test berdiri sendiri dan tidak mempengaruhi test yang lain, karna ada BeforeEach setUp yang memastikan tiap test itu selalu ada setup yang tersedia.
### Repeatable
- Test menghasilkan output yang konsisten dan repeatable.
### Self-Validating
- Test memvalidasi dirinya sendiri melalui assertions seperti assertEquals dll.
### Timely
- Test dibuat sesuai ketentuan TDD, yaitu dibuat dulu sebelum kode aslinya dibuat, sebagai baseline untuk kode yang nanti ditulis.
