#Trello Web Servis Otomasyon Projesi
Bu proje, Java programlama dilinde Maven projesi açılarak Rest-Asured kütüphanelerini kullanarak oluşturulmuştur.


##API Bilgileri
Proje için gerekli olan API bilgilerine  bilgiyi Trello Developer'dan alabilirsiniz.

##Proje Yapısı
APIsTrello/TrelloBoardTransactions: Trello ile ilgili işlemleri gerçekleştiren sınıf.
TestAPI/TrelloTestSenarios: JUnit test sınıfı, TrelloBoard sınıfını test etmek için yazılmıştır.
TestAPI/BaseTrelloBoard: Gerekli konfigürasyon işlemlerinin yapılır sınıfı temsil eder.
Utilities/Log: log işlemlerini gerçekleştiren yardımcı sınıf.



Senaryo Adımları
- Trello üzerinde bir board oluşturunuz.
- Oluşturduğunuz board’ a iki tane kart oluşturunuz.
- Oluştrduğunuz bu iki karttan random olacak sekilde bir tanesini güncelleyiniz.
- Oluşturduğunuz kartları siliniz.
- Oluşturduğunuz board’ u siliniz.



##Notlar
Token url ulaşmak için trelloda login olup ayrı bir
sekmeden token url’ine gitmeniz gerekmektedir.












