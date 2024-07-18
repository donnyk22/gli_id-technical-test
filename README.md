https://github.com/kaketsukeru/gli_id-technical-test
<br>
- Start service MySQL
- Buat database baru "order_db", dan import "order_db.sql". Skema database bisa di lihat di DB_Schema.png (saya melakukan inisiatif untuk membuat skema sendiri, selengkapnya ada di bawah)
- Sesuaikan Username & Password DB di application.properties
- Run "mvn install" untuk install semua dependency
- Run "spring-boot:run"
- Buka http://localhost:8080/swagger-ui/index.html 
<br><br>
<h3>Implementasi soal</h3>
- Semua/beberapa fitur yang ada di soal sudah diterapkan.<br>
  <b>Main objektif:</b>
- Menggunakan Java Spring boot
- Terdapat unit testing
- Yang menggunakan singleton pattern ada pada class **com.gli.id.configurations.SwaggerConfig** karena menggunakan @Bean. Namun sebenarnya Spring Boot menggunakan singleton secara default apabila kita menggunakan anotasi dari springframework stereotype, context, dll
- Menerapkan Timeout pada end-point /dog-public-api. Jika breed == null, maka timeout 5000. Jika breed != null, maka timeout 2000
- Menerapkan lambda class pada DogPublicApiController pada method findLambda()
- Melakukan manipulasi JSON pada dog breed shiba, sheepdog, dan terrier.<br>
<b>Secondary objektif:</b>
- Membuat CRUD pada order-api menggunakan MySQL database. Saya melakukan inisiatif dengan membuat skema database sendiri dikarenakan skema pada Dog API dan data yang disajikan kurang begitu lengkap/terlalu sedikit. Sehingga saya bingung untuk menerapkannya pada skema database baru untuk proses CRUD
<br><br>
<h3>Dog-API</h3>
- Pada bagian "dog-public-api-controller" di swagger, klik try it out pada /dog-public-api
- Json yang muncul akan sesuai dengan JSON di dog-api documentation, tapi dengan JSON yang sudah dimodifikasi sesuai dengan soal yang diberikan
- Jika fetch gagal, pada file com.gli.id.services.dog_public_api.DogPublicApiServiceImpl, hapus/comment line 55, khususnya pada bagian timeout. Karena respond bisa lebih lama dari 5 detik
- Isikan param breed, dan akan melakukan filter dog by breed.
- Json yang muncul juga akan termodifikasi apabila mengisikan breed "shiba", "sheepdog", atau "terrier"
- Jika fetch gagal, pada file com.gli.id.services.dog_public_api.DogPublicApiServiceImpl, hapus/comment line 24, khususnya pada bagian timeout.
- Pada bagian /dog-public-api/lambda adalah end-point penerapan lambda function yang sesuai dengan soal yang diberikan
<br><br>
<h3>Order-API</h3>
- Terdapat CRUD method. Bisa dicoba satu-persatu, sesuai dengan struktur request body yang diberikan. Sebagai catatan, data order masih kosong, sehingga perlu menambahkan data order terlebih dahulu.
- Terdapat Unit Test di folder test/java.com.gli.id.controllers.*