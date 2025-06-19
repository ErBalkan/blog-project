# Bağımlılıklar

1. `spring-boot-starter-web:` REST API yazmak için gerekli olan temel yapı.

2. `spring-boot-starter-data-jpa:` ORM (nesne ilişkisel eşleme) sistemi. Java class’larını tabloya dönüştürmek için.

3. `postgresql:` PostgreSQL veritabanı sürücüsü.

4. `lombok:` Java sınıflarında getter/setter/constructor yazmaktan kurtarır.

5. `validation:` Gelen request’leri kontrol etmek için.

6. `devtools:` Kod değiştiğinde otomatik yeniden başlatma.


# Application.yml 

## Neden application.properties değil de yml ? 

Spring Boot’ta hem application.properties hem de application.yml dosyalarını konfigürasyon için kullanabilirsin. İkisi de aynı işi yapar. Ancak application.yml formatı bazı önemli avantajlar sağlar.

1. Daha Temiz ve Hiyerarşik Yapı
2. Multi-Profil Desteği Daha Kolay
Farklı ortamlar için (dev, test, prod) tanımlama YAML ile daha esnektir.
3. Nested (İç içe) Yapılar İçin Uygun
Bazı konfigürasyonlar iç içe yapılar gerektirir. Örneğin security, kafka, mail servisleri gibi. Bunlarda .yml çok daha sade kalır.

## Bu projede application.yml kullanmamızın nedeni:

- Proje büyüyecek,

- Ortam ayrımları olacak (dev/test/prod),

- Yapıların okunabilir olması öncelikli,

- Seni kurumsal standartlara şimdiden hazırlamak.

Yani: Kurumsal düzeyde çalışmak istiyorsan YAML öğrenmek şart.

