# ÖDEV TAKİP SİSTEMİ 
 
## 1.	GİRİŞ 

Ödev takip sistemi, öğrencilerin akademik hayatını düzenlemelerine ve öğretmenlerin eğitim sürecini daha etkin bir şekilde yönetmelerine yardımcı olan bir araçtır.
Bu sistemler, öğrenci başarısını artırmak, ödevlerin zamanında teslim edilmesini sağlamak ve öğretmenlerin yükünü hafifletmek gibi birçok avantaj sunar.
Ayrıca, günlük hayatta ebeveynler için de büyük kolaylık sağlar. Ödev takip sistemleri, öğrencilere disiplin kazandırır, zaman yönetimi becerilerini geliştirmelerine yardımcı olur ve grup çalışmalarında iş birliği ve iletişimi güçlendirir.
Örneğin, Google Classroom ve Microsoft Teams gibi popüler platformlar, öğrenci ve öğretmenlerin etkileşimini kolaylaştırırken, öğrenci ilerlemesini takip etmeye olanak tanır.
Biz de bu amaçla, Spring Framework kullanarak kendi ödev takip sistemi projemizi geliştirdik.
Bu projemiz, öğrenci ve öğretmen ihtiyaçlarını göz önünde bulundurarak tasarlanmıştır ve kullanıcı dostu bir arayüz sunar.


## 2.	MATERYAL VE YÖNTEM  
Bu bölümde projenin temellerini içeren bileşenlerden bahsedilmektedir. Projenin dosya yapısı Şekil 2.0.1. de gösterildiği gibidir. 

  ![image](https://github.com/muhammetclk/Odev-Takip-Sistemi/blob/main/projectweb/src/main/resources/static/img.png)
  
Şekil 2.0.1. Dizin Yapısı. 

Configuration dizini projenin yapılandırma ayarlarını yönetmektedir. Bu pakette genellikle uygulamanın genel yapılandırmalarını gerçekleştiren sınıflar bulunur. Örneğin, güvenlik ayarları, dış servis bağlantı ayarları, role kısıtlamaları gibi konfigürasyonlar burada yer alır. Bu dizinde, SecurityConfig.java ve AppConfig.java gibi dosyalar bulunmaktadır. 
Controller dizini projenin HTTP isteklerini karşılar ve uygun servislere yönlendirir. Bu pakette, kullanıcılardan gelen HTTP isteklerini alan ve işleyen sınıflar (genellikle Spring'de @RestController veya @Controller anotasyonları ile işaretlenmiş) bulunur. 
İstekleri alır, ilgili iş mantığı sınıflarına (service) yönlendirir ve sonuçları geri döner. Bu dizinde, AssignmentController.java ve UserController.java gibi dosyalar bulunmaktadır. 
Service dizini projenin iş mantığını uygular. Bu pakette, uygulamanın iş mantığını içeren sınıflar (genellikle @Service anotasyonu ile işaretlenmiş) bulunur. Veri tabanı işlemleri, iş kuralları ve diğer iş mantığı bu sınıflarda uygulanır. Bu dizinde, AssignmentService.java ve UserService.java gibi dosyalar bulunmaktadır. 
Dao dizini projenin veri tabanı işlemlerini yönetir. Bu pakette, veri tabanı ile ilgili CRUD (Create, Read, Update, Delete) işlemlerini gerçekleştiren sınıflar veya interfaceler bulunur. Genellikle ORM (Object Relational Mapping) araçları kullanılarak ve hibernate kullanılarak veri erişimi sağlanır. Bu dizinde, AssignmentDAO.java ve UserDAO.java gibi dosyalar bulunmaktadır. 
Dto dizini projenin veri transferini sağlar. Bu pakette, farklı katmanlar arasında veri taşımak için kullanılan sınıflar bulunur. DTO'lar genellikle veri modeli nesnelerinin daha hafif veya özel versiyonlarıdır ve gereksiz bilgileri içermez. Bu dizinde, AssignmentDTO.java ve UserDTO.java gibi dosyalar bulunmaktadır. 
Exception dizini projenin uygulama genelinde özel hata yönetimini sağlar. Bu pakette, uygulamada meydana gelen hataları yönetmek ve uygun hata mesajlarını oluşturmak için kullanılan özel istisna sınıfları bulunur. Bu dizinde, AssignmentNotFoundException.java ve GlobalExceptionHandler.java gibi dosyalar bulunmaktadır. 
Test dizini projenin uygulama bileşenlerini test eder. Bu pakette, uygulamanın farklı bölümlerini test eden birim testleri, entegrasyon testleri ve diğer test türleri için kullanılan sınıflar bulunur. Bu dizinde, AssignmentControllerTest.java ve UserControllerTest.java gibi dosyalar bulunmaktadır. 
Validation dizini projenin veri doğrulama işlemlerini gerçekleştirir. Bu pakette, kullanıcı girdilerini ve veri transfer nesnelerini doğrulamak için kullanılan sınıflar ve kurallar bulunur. Genellikle bean validation (örneğin, Hibernate Validator) kullanılır. Bu dizinde, AssignmentValidator.java ve UserValidator.java gibi dosyalar bulunmaktadır. 
Entity dizini projenin veri tabanı varlıklarını temsil eder. Bu pakette, veri tabanı tablolarını temsil eden ve ORM araçları tarafından kullanılan sınıflar bulunur. Genellikle @Entity anotasyonu ile işaretlenirler. Bu dizinde, AssignmentEntity.java ve UserEntity.java gibi dosyalar bulunmaktadır. 
İnterceptor dizini projenin istekleri ve yanıtları önceden veya sonradan yakalar ve işlemler yapar. Bu pakette, gelen ve giden HTTP isteklerini yakalayan ve belirli işlemler gerçekleştiren sınıflar bulunur. Örneğin, oturum yönetimi, yetkilendirme veya günlük kaydı gibi işlemler için kullanılabilir. 
Response dizini projenin API yanıtlarını özelleştirir ve standart hale getirir. Bu pakette, API'nin istemcilere döneceği standart yanıt formatlarını içeren sınıflar bulunur. Başarı ve hata durumları için özel yanıt yapılarını barındırır. Bu dizinde, AuthResponse.java ve ErrorResponse.java gibi dosyalar bulunmaktadır. 

Çizelge 2.0.2. Parametre Listesi. 

| İstek URI                               | İstek Metodu | Parametreler                                  | Parametre Tipleri        | Geri Dönüş Verisi |
|-----------------------------------------|--------------|-----------------------------------------------|--------------------------|-------------------|
| /teacherProfile/{userId}                | GET          | @PathVariable Long userId                    | Long                     | UserDTO           |
| /users/{id}                             | GET          | @PathVariable long id                        | long                     | UserDTO           |
| /api/v1/search                          | GET          | @RequestParam(name = "search") String search | String                   | List<UserDTO>     |
| /api/v1/home works/{userId}             | GET          | @PathVariable Long userId                    | Long                     | List<FileDTO>     |
| /api/v1/teacher/works/{assignmentId}/submissions | GET  | @PathVariable Long assignmentId              | Long                     | List<FileDTO>     |
| /api/v1/auth                            | POST         | @Valid @RequestBody AuthCredentialsDTO creds | AuthCredentialsDTO       | AuthResponse      |
| /api/v1/assignment/{teacherId}          | GET          | @PathVariable Long teacherId                 | Long                     | List<AssignmentDTO> |
| /api/v1/projectDetail/{assignmentId}    | GET          | @PathVariable Long assignmentId              | Long                     | AssignmentDTO     |
| /api/v1/projectUpload/{assignmentId}/{userId} | POST | @RequestParam("file") MultipartFile file | MultipartFile             | ResponseEntity<String> |
|                                         |              | @PathVariable("assignmentId") long assignmentId | long                  |                   |
|                                         |              | @PathVariable("userId") long userId         | long                     |                   |
| /api/v1/teacher/works/{userId}         | POST         | @RequestBody AssignmentDTO assignmentDTO    | AssignmentDTO            | String            |
| /api/v1/admin/signup                    | POST         | @Valid @RequestBody UserCreateDTO userCreateDto | UserCreateDTO          | String            |

 
•	/teacherProfile/{userId}, /users/{id}, /api/v1/search/, /api/v1/homeworks/{userId}, /api/v1/assignment/{teacherId}, /api/v1/projectDetail/{assignmentId}: GET isteği. Veri almak için kullanılır. 

•	/api/v1/teacher/works/{assignmentId}/submissions, @PreAuthorize: Belirli bir rol gereksinimi olan endpointler. Örneğin, kullanıcı veya öğretmen rolü. 

•	api/v1/auth, @Valid @RequestBody AuthCredentialsDTO creds: İstek gövdesinde kimlik doğrulama bilgilerini alır. 
AuthResponse: Kimlik doğrulama işleminin sonucunu döner. 

•	/api/v1/projectUpload/{assignmentId}/{userId}, 	@RequestParam("file") 
MultipartFile file: Yüklenen dosyayı alır. 
ResponseEntity<String>: Dosya yükleme işleminin sonucunu döner. 

•	/api/v1/teacher/works/{userId}, @RequestBody AssignmentDTO assignmentDTO: İstek gövdesinde ödev bilgilerini alır. 

•	@AuthenticationPrincipal CurrentUser currentUser: Kimliği doğrulanmış mevcut kullanıcıyı alır. 

•	/api/v1/admin/signup, @Valid @RequestBody UserCreateDTO userCreateDto: İstek gövdesinde yeni kullanıcı oluşturma bilgilerini alır. 

### 2.1.	ANA ÖZELLİKLER 
Bu bölümde kullanıcıların giriş yapmadan yapabildikleri işlemler anlatılmaktadır. 
Kullanıcıyı karşılayan ekran Şekil 2.1.1. de gösterildiği gibi öğretmen aramasını isteyen bir formdur. 

   ![image](https://github.com/muhammetclk/Odev-Takip-Sistemi/blob/main/projectweb/src/main/resources/static/img_1.png)
Şekil 2.2.1. Ana Sayfanın Arama Formu. 

Kullanıcının aradığı bilgiler veri tabanında varsa Şekil 2.1.2. de gösterilen arama sonucuna benzer bir çıktı alınmaktadır. 

   ![image](https://github.com/muhammetclk/Odev-Takip-Sistemi/blob/main/projectweb/src/main/resources/static/img_2.png)
   
Şekil 2.1.3. Arama Sonucu Örneği. 

Kullanıcı arama sonucunda Şekil 2.1.2. ye benzer bir sonuç aldıysa, “Detaylar” butonuna tıklayıp Şekil 2.1.3. de gösterilen detaylara ulaşabilir. Bu sayfada, sayfasına gidilen kişinin eklediği ödevleri görülebilir. Ayrıca, oturum açtıysak kendi yüklediğimiz ödev dosyalarını da görüntüleyebilir ve istersek indirebiliriz. 

   ![image](https://github.com/muhammetclk/Odev-Takip-Sistemi/blob/main/projectweb/src/main/resources/static/img_3.png)
Şekil 2.1.4. Detaylar Sayfası Örneği. 
Kullanıcı Şekil 2.1.3. de sağ üstten 2. sırada görünen “Ödevleri Listele” butonuna tıklayıp sayfasında olduğu kişinin daha önceden eklemiş olduğu ödevleri görebilir. Şekil 2.1.4. de gösterildiği gibi her ödevin adı, açıklaması, oluşturulma ve bitiş tarihi görüntülenebilir. 
Aynı zamanda ödevin aktiflik durumunu da görebiliriz. 

   ![image](https://github.com/muhammetclk/Odev-Takip-Sistemi/blob/main/projectweb/src/main/resources/static/img_4.png)
Şekil 2.1.4. Ödevleri Listele Sayfası Örneği. 
Kullanıcı Şekil 2.1.4. de altı çizili olarak görünen ödevlere tıkladığında, Şekil 2.1.5. de gösterilen ödev yükleme sayfasına yönlendirilir. Ama kullanıcı giriş yapmadıysa seçtiği PDF dosyasının yüklenmesine izin verilmez. 

   ![image](https://github.com/muhammetclk/Odev-Takip-Sistemi/blob/main/projectweb/src/main/resources/static/img_5.png)
Şekil 2.1.5. Ödev Yükleme Sayfası Örneği. 
Kullanıcıların yetkileri Çizelge 2.1.6. da gösterilen “roles” tablosuna göre yapılandırıldığı için kullanıcıların gerekli rollere sahip olmadan belirli işlemleri yapamazlar. 
Çizelge 2.1.6. Kullanıcı Yetki Gruplarının Tutulduğu Veri Tabanı Tablosu. 
| Sütun Adı  | Sütun Tipi | Açıklama                                           |
|------------|------------|----------------------------------------------------|
| role_id    | int        | Rolü benzersiz olarak tanımlayan kimlik numarası. |
|            |            | Özellik: Birincil anahtar (PK) olarak kullanılır.|
| role_name  | varchar(45)| Rolün adını saklar (örneğin, "Admin", "Öğrenci", |
|            |            | "Eğitmen" gibi).                                 |
|            |            | Özellik: Maksimum 45 karakter uzunluğunda metin |
|            |            | verisi kabul eder.                               |

 
Kullanıcı ROLE_USER yetkisine sahipse ödev yükleyebilir ve kendi yüklediği ödevleri görüntüleyebilir. ROLE_TEACHER yetkisine sahipse ödev oluşturabilir ve teslim edilen ödevleri görüntüleyebilir. ROLE_ADMIN yetkisine sahipse yeni kullanıcılar kayıt edebilir. 
2.2.	ÖĞRENCİ ÖZELLİKLERİ 
Bu bölümde kullanıcının “ROLE_USER” yetkisine sahip giriş yapması durumunda yapabileceklere anlatılmaktadır. Şekil 2.2.1. de gösterildiği gibi kullanıcı yüklediği ödevleri görüntüleyebilir ve indirebilir. 

    ![image](https://github.com/muhammetclk/Odev-Takip-Sistemi/blob/main/projectweb/src/main/resources/static/img_6.png)
    
Şekil 2.2.1. Ödevlerim Sayfası Örneği. 
Ayrıca kullanıcı Şekil 2.1.5. de gösterilen ödev yükleme sayfasından öğrenci olarak giriş yaptığı için artık ödev yükleyebilecektir. Yüklenen ödevler bayt dizisi olarak detayları Çizelge 2.2.2. de verilen “files” tablosunda saklanır. 
Çizelge 2.2.2. Dosyaların Tutulduğu Veri Tabanı Tablosu. 
| Sütun Adı   | Sütun Tipi | Açıklama                                                 |
|-------------|------------|----------------------------------------------------------|
| file_id     | int        | Dosyayı benzersiz olarak tanımlayan kimlik numarası.    |
|             |            | Özellik: Birincil anahtar (PK) olarak kullanılır.       |
| file_data   | longblob   | Dosyanın içeriğini (verisini) saklar.                    |
|             |            | Özellik: Büyük boyutlu ikili veriyi (BLOB) kabul eder.  |
| assignment_id | int      | Dosyanın bağlı olduğu ödevin kimlik numarasını saklar. |
|             |            | Özellik: Assignments tablosundaki assignment_id alanına  |
|             |            | referans veren dış anahtar (FK) olarak kullanılır.      |
| user_id     | int        | Dosyayı yükleyen kullanıcının kimlik numarasını saklar. |
|             |            | Özellik: Users tablosundaki user_id alanına referans    |
|             |            | veren dış anahtar (FK) olarak kullanılır.              |
 
2.3.	ÖĞRETMEN ÖZELLİKLERİ 
Bu bölümde kullanıcının “ROLE_TEACHER” yetkisine sahip giriş yapması durumunda yapabileceklere anlatılmaktadır. Şekil 2.3.1. de gösterildiği gibi kullanıcı teslim edilen ödevlerin “Gönderimleri Görüntüle” butonuna basarak ilgili ödev için yükleme yapmış öğrencilerin ödevlerini ve bilgilerini görüntüleyebilir. 

   ![image](https://github.com/muhammetclk/Odev-Takip-Sistemi/blob/main/projectweb/src/main/resources/static/img_7.png)
Şekil 2.5.1. Ödevler Sayfası Örneği. 
Şekil 2.3.1. deki “Gönderimleri Görüntüle” butonuna tıklandığında Şekil 2.3.2. de gösterildiği gibi yüklenen ödevler öğrencinin adı ve numarasıyla birlikte gösterilir. 

   ![image](https://github.com/muhammetclk/Odev-Takip-Sistemi/blob/main/projectweb/src/main/resources/static/img_8.png)
Şekil 2.6.2. Yüklenen Ödevler Sayfası Örneği. 
 
Ayrıca Şekil 2.3.3. de gösterildiği gibi yeni ödev ekleyebilir. Burada ödev başlığını, açıklamasını, başlangıç ve bitiş tarihini girerek ödev yükleyebilir. Eklenecek ödevin başlık, açıklama, başlangıç ve bitiş tarihlerinin olması zorunludur. Bitiş tarihi başlangıç tarihinden önce seçilir ise ödev eklenemez. 

   ![image](https://github.com/muhammetclk/Odev-Takip-Sistemi/blob/main/projectweb/src/main/resources/static/img_9.png)
Şekil 2.7.3. Ödev Ekle Sayfası Örneği. 
Kullanıcı tarafından oluşturulan ödevler Çizelge 2.3.4. de detayları verilmiş olan “assigments” tablosunda saklanır. 
Çizelge 2.3.4. Ödev Tanımlamalarının Tutulduğu Veri Tabanı Tablosu. 
| Sütun Adı     | Sütun Tipi   | Açıklama                                                 |
|---------------|--------------|----------------------------------------------------------|
| assignment_id | int          | Ödevi benzersiz olarak tanımlayan kimlik numarasını     |
|               |              | saklar.                                                  |
|               |              | Özellik: Birincil anahtar (PK) olarak kullanılır.       |
| user_id       | int          | Ödevi oluşturan kullanıcıyı belirten kimlik numarasını  |
|               |              | saklar.                                                  |
|               |              | Özellik: Dış anahtar (FK) olarak kullanılır, Users     |
|               |              | tablosundaki user_id alanına referans verir.            |
| name          | varchar(45)  | Ödevin adını saklar.                                    |
|               |              | Özellik: Maksimum 45 karakter uzunluğunda metin verisi |
|               |              | kabul eder.                                              |
| description   | varchar(255) | Ödevin açıklamasını saklar.                             |
|               |              | Özellik: Maksimum 255 karakter uzunluğunda metin verisi |
|               |              | kabul eder.                                              |
| deadline      | date         | Ödevin teslim tarihi ve saatini saklar.                 |
|               |              | Özellik: Tarih verisi kabul eder.                       |
| created_at    | date         | Ödevin oluşturulma tarihini saklar.                     |
|               |              | Özellik: Tarih verisi kabul eder.                       |
| active        | Boolean      | Ödevin aktif olup olmadığını belirten durumu saklar.   |
|               |              | Özellik: Boolean (True/False) verisi kabul eder.        |

 
Şekilden önceki metin ile şekil arasında 1,5 satır aralık bırakılmalıdır.  
2.4.	YÖNETİCİ ÖZELLİKLERİ 
Bu bölümde kullanıcının “ROLE_ADMIN” yetkisine sahip giriş yapması durumunda 
yapabileceklere anlatılmaktadır. Şekil 2.4.1. de gösterildiği gibi kullanıcı yeni kullanıcılar kayıt edebilmektedir. 

   ![image](https://github.com/muhammetclk/Odev-Takip-Sistemi/blob/main/projectweb/src/main/resources/static/img_10.png)
Şekil 2.4.1. Kayıt Sayfası Örneği. 
Şekil 2.4.1. de gösterilen kayıt sayfasında eklenecek olan kullanıcının numara dışındaki tüm değerlerinin girilmesi zorunludur. Ayrıca şifre en az bir küçük harf, bir büyük harf, bir rakam içerirken altı karakter ya da daha uzun olmalıdır. E-posta ise “???@???.??” formatında olmalıdır ve “Şifre” ile “Tekrar Şifre” aynı olmalıdır. Kullanıcı tarafından oluşturulan kullanıcılar detayları Çizelge 2.4.2. de verilmiş olan “users” tablosunda saklanır. 
Çizelge 2.4.2. Kullanıcı Bilgilerinin Tutulduğu Veri Tabanı Tablosu. 
| Sütun Adı       | Sütun Tipi   | Açıklama                                                             |
|-----------------|--------------|----------------------------------------------------------------------|
| user_id         | int          | Bu alan, kullanıcıya özgü benzersiz bir kimlik numarasını saklar.  |
|                 |              | Özellik: Birincil anahtar (PK) olarak kullanılır.                   |
| user_firstname  | varchar(45)  | Kullanıcının adını saklar.                                          |
|                 |              | Özellik: Maksimum 45 karakter uzunluğunda metin verisi kabul eder.  |
| user_lastname   | varchar(45)  | Kullanıcının soyadını saklar.                                       |
|                 |              | Özellik: Maksimum 45 karakter uzunluğunda metin verisi kabul eder.  |
| user_email      | varchar(45)  | Kullanıcının e-posta adresini saklar.                               |
|                 |              | Özellik: Maksimum 45 karakter uzunluğunda metin verisi kabul eder.  |
| user_password   | varchar(255) | Kullanıcının şifresini saklar.                                      |
|                 |              | Özellik: Şifre verisini güvenli bir şekilde saklamak için maksimum  |
|                 |              | 255 karakter uzunluğunda metin verisi kabul eder.                   |
| user_faculty    | varchar(45)  | Kullanıcının bağlı olduğu fakülteyi saklar.                         |
|                 |              | Özellik: Maksimum 45 karakter uzunluğunda metin verisi kabul eder.  |
| user_number     | varchar(9)   | Kullanıcının öğrenci veya personel numarasını saklar.               |
|                 |              | Özellik: Maksimum 9 karakter uzunluğunda metin verisi kabul eder.   |
| role_id         | int          | Kullanıcının rolünü belirleyen rol kimlik numarasını saklar.        |
|                 |              | Özellik: Dış anahtar (FK) olarak kullanılır, role tablosundaki      |
|                 |              | role_id alanına referans verir.                                     |

 
## 3.	SONUÇLAR VE ÖNERİLER 
Spring kullanarak geliştirdiğimiz ödev takip sistemi, modern eğitim ihtiyaçlarına yönelik kapsamlı ve etkili bir çözüm sunmaktadır. Bu proje sayesinde, öğrenciler ve öğretmenler arasındaki iletişim ve iş birliği güçlenmiş, eğitim süreçleri daha düzenli ve verimli hale gelmiştir. Öğrenciler, ödevlerini daha sistematik bir şekilde takip edebilir, teslim tarihlerini kaçırmadan zamanında tamamlayabilirler. Bu da onların akademik başarılarını artırırken, stres düzeylerini azaltır ve zaman yönetimi becerilerini geliştirir. 
Öğrenciler açısından, sistemin sunduğu hizmetler, ödevlerin teslim tarihlerinin unutulmasını engelleyerek son dakika baskısını azaltır. Öğrenciler, tamamladıkları ödevlerin değerlendirmelerini ve geri bildirimlerini hızla alarak, hatalarını anında görüp düzeltme şansı bulurlar. Bu sürekli geri bildirim döngüsü, öğrenme sürecini daha etkin hale getirir ve öğrencilerin motivasyonunu artırır. Ayrıca, öğrenciler farklı dersler için verilen ödevleri tek bir platform üzerinden takip edebilme avantajına sahiptir, bu da dersler arası koordinasyonu ve çalışma planlarını düzenlemeyi kolaylaştırır. 
Öğretmenler için bu sistem, ödevlerin dağıtımı, takibi ve değerlendirilmesi süreçlerini büyük ölçüde kolaylaştırmıştır. Öğretmenler, öğrencilerinin performansını daha yakından izleyebilmekte, bireysel geri bildirimler sunarak onların gelişimine katkıda bulunabilmektedirler. Bu sayede, her öğrencinin ihtiyaçlarına daha iyi yanıt verebilir ve onların güçlü yanlarını destekleyip zayıf noktalarını geliştirebilirler. Ayrıca, öğretmenlerin idari yükleri azaldığı için, daha fazla zaman ve enerjiyle eğitimin kalitesini artırmaya odaklanabilmektedirler. Öğretmenler, aynı zamanda, öğrencilerin ilerlemesini daha detaylı ve analitik bir şekilde raporlayarak, eğitim stratejilerini daha etkili bir şekilde planlayabilirler. 
Projemizin bir diğer önemli avantajı, esnek ve ölçeklenebilir yapısı sayesinde, farklı eğitim düzeylerine ve kurumlarına kolayca uyarlanabilmesidir. İlköğretimden yükseköğretime kadar geniş bir yelpazede kullanılabilecek olan bu sistem, her seviyedeki eğitim kurumunun ihtiyaçlarını karşılayabilecek şekilde tasarlanmıştır. Ayrıca, sistemin özelleştirilebilir özellikleri, kurumların kendi gereksinimlerine göre ayarlamalar yapmasına olanak tanır. 
Teknik açıdan, Spring Framework'ün sunduğu güçlü ve güvenilir altyapı sayesinde, sistemimiz hem performans hem de güvenlik açısından yüksek standartları karşılamaktadır. Kullanıcı verilerinin güvenliği ve sistemin kesintisiz çalışması, proje boyunca en öncelikli hedeflerimiz arasında yer aldı. Bu da kullanıcıların sisteme duyduğu güveni artırmakta ve kullanım yaygınlığını sağlamaktadır. 
Sonuç olarak, Spring tabanlı ödev takip sistemi projemiz, eğitim süreçlerini dijitalleştirerek daha organize, şeffaf ve verimli hale getirmiştir. Bu sistem, kullanıcı dostu arayüzü ve güçlü teknik altyapısıyla, modern eğitimde dijitalleşmenin önemini bir kez daha ortaya koymaktadır. Gelecekte, bu tür teknolojik çözümlerin daha yaygın hale gelmesiyle birlikte, eğitimde kalite ve başarı düzeyinin daha da artacağına inanıyoruz. Projemiz, bu alanda önemli bir adım olup, eğitim teknolojilerinin sunduğu potansiyelin en iyi şekilde değerlendirilmesine katkıda bulunmaktadır. 
## 4.	KAYNAKLAR 
 
[1]	Google LLC, “Google Classroom”, https://classroom.google.com/. 
[2]	Microsoft Corporation, “Microsoft Teams”, https://teams.live.com/. 
[3]	B. Whitmer ve D. Daley, “Canvas”, https://canvas.instructure.com/. 
 
 
 
## 5.	EKLER 
### 5.1.	EK 1: LOG ÖRNEKLERİ 
Bu bölümde uygulamadan alınan örnek loğlar gösterilmiştir. Şekil 5.1.1 de log dosyalarının sistemde nasıl tutulduğu, Şekil 5.1.2. ve Şekil 5.1.3 de örnek log dosyasının içeriği örnek gösterilmiştir. 

   ![image](https://github.com/muhammetclk/Odev-Takip-Sistemi/blob/main/projectweb/src/main/resources/static/img_11.png)
Şekil 5.1.1. Dosya Sistemine Kayıt Edilmiş Log Örneği. 

   ![image](https://github.com/muhammetclk/Odev-Takip-Sistemi/blob/main/projectweb/src/main/resources/static/img_12.png)
Şekil 5.1.2. Log Örneği 1. 
 
   ![image](https://github.com/muhammetclk/Odev-Takip-Sistemi/blob/main/projectweb/src/main/resources/static/img_13.png)
Şekil 5.1.3. Log Örneği 2. 
 
### 5.2.	EK 2: VERİ TABANI 
Şekil 5.2.1. de veri tabanın varlık ilişkileri gösterilirken Şekil 5.2.2. Şekil 5.2.3. Şekil 5.2.4. ve Şekil 5.2.5. de ise veri tabanı verilerinden örnek veriler gösterilmiştir. 
xx

   ![image](https://github.com/muhammetclk/Odev-Takip-Sistemi/blob/main/projectweb/src/main/resources/static/img_14.png)
Şekil 5.2.1. Veri Tabanı Tasarımı. 
Kullanıcı yetki gruplarının tutulduğu tablo Şekil 5.2.2. de gösterilmiştir. 

   ![image](https://github.com/muhammetclk/Odev-Takip-Sistemi/blob/main/projectweb/src/main/resources/static/img_15.png)
Şekil 5.2.2. Roles Tablosu. 
Öğrencilerin yüklediği dosyaların tutulduğu tablo Şekil 5.2.3. de gösterilmiştir. 

   ![image](https://github.com/muhammetclk/Odev-Takip-Sistemi/blob/main/projectweb/src/main/resources/static/img_16.png)
Şekil 5.2.3. Files Tablosu. 
Öğretmenlerin ödevlerinin tutulduğu tablo Şekil 5.2.4. de gösterilmiştir. 

   ![image](https://github.com/muhammetclk/Odev-Takip-Sistemi/blob/main/projectweb/src/main/resources/static/img_17.png)
Şekil 5.2.4. Assignment Tablosu. 
Kullanıcı bilgilerinin tutulduğu tablo Şekil 5.2.5. de gösterilmiştir. 

   ![image](https://github.com/muhammetclk/Odev-Takip-Sistemi/blob/main/projectweb/src/main/resources/static/img_18.png)
Şekil 5.2.5. Users Tablosu. 
 
