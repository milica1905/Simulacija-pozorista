insert into projekat.posjetilac_pozorista (ime, prezime, korisnicko_ime, lozinka) value ("Milica", "Kremenovic", "milica19",MD5("1234"));
insert into projekat.posjetilac_pozorista (ime, prezime, korisnicko_ime, lozinka) value ("Marija", "Trivundza", "marija01",MD5("mare"));
insert into projekat.posjetilac_pozorista (ime, prezime, korisnicko_ime, lozinka) value ("Petar", "Markovic", "peroo",MD5("petar123"));

insert into projekat.radnik_pozorista (ime, prezime, korisnicko_ime, lozinka, pozoriste_id) value ("Lazar", "Lazarevic", "lazarlazo",MD5("laki123"),1);
insert into projekat.radnik_pozorista (ime, prezime, korisnicko_ime, lozinka, pozoriste_id) value ("Tara", "Jovanic", "taraj",MD5("tarica"),1);
insert into projekat.radnik_pozorista (ime, prezime, korisnicko_ime, lozinka, pozoriste_id) value ("Ivana", "Lakic", "laka2002",MD5("laka5"),2);
insert into projekat.radnik_pozorista (ime, prezime, korisnicko_ime, lozinka, pozoriste_id) value ("Monika", "Mihajlovic", "monika",MD5("moni"),2);
insert into projekat.radnik_pozorista (ime, prezime, korisnicko_ime, lozinka, pozoriste_id) value ("Ljubisa", "Marinkovic", "ljubo12",MD5("ljuboo"),3);
insert into projekat.radnik_pozorista (ime, prezime, korisnicko_ime, lozinka, pozoriste_id) value ("Marko", "Markovic", "makimaki",MD5("2005"),3);
insert into projekat.radnik_pozorista (ime, prezime, korisnicko_ime, lozinka, pozoriste_id) value ("Jovan", "Jovanovic", "jovanjovan",MD5("joco"),4);
insert into projekat.radnik_pozorista (ime, prezime, korisnicko_ime, lozinka, pozoriste_id) value ("Sara", "Jovanic", "sarajo",MD5("saraa123"),4);
insert into projekat.radnik_pozorista (ime, prezime, korisnicko_ime, lozinka, pozoriste_id) value ("Dunja", "Balaban", "dunjica",MD5("tosamja"),5);
insert into projekat.radnik_pozorista (ime, prezime, korisnicko_ime, lozinka, pozoriste_id) value ("Ana", "Spasojevic", "anas27",MD5("malana"),6);


insert into projekat.karta (izvodjenje_predstave_id, status, posjetilac_id, broj_karta) value(1,1,1,5);
insert into projekat.karta (izvodjenje_predstave_id, status, posjetilac_id, broj_karta) value(2,2,1,3);
insert into projekat.karta (izvodjenje_predstave_id, status, posjetilac_id, broj_karta) value(3,1,3,1);
insert into projekat.karta (izvodjenje_predstave_id, status, posjetilac_id, broj_karta) value(4,3,4,7);
insert into projekat.karta (izvodjenje_predstave_id, status, posjetilac_id, broj_karta) value(5,2,5,4);
insert into projekat.karta (izvodjenje_predstave_id, status, posjetilac_id, broj_karta) value(6,1,3,10);
insert into projekat.karta (izvodjenje_predstave_id, status, posjetilac_id, broj_karta) value(7,3,4,5);

insert into projekat.osoblje(ime, prezime, tip) value("Nikola","Kojo",1);
insert into projekat.osoblje(ime, prezime, tip) value("Nebojša","Glogovac",1);
insert into projekat.osoblje(ime, prezime, tip) value("Ivana","Miličević",1);
insert into projekat.osoblje(ime, prezime, tip) value("Milena","Dravić",1);
insert into projekat.osoblje(ime, prezime, tip) value("Mira","Banjac",1);
insert into projekat.osoblje(ime, prezime, tip) value("Dragan","Nikolić",1);
insert into projekat.osoblje(ime, prezime, tip) value("Radoš","Bajić",2);
insert into projekat.osoblje(ime, prezime, tip) value("Emir","Kusturica",2);
insert into projekat.osoblje(ime, prezime, tip) value("Stefan","Arsenijević",2);
insert into projekat.osoblje(ime, prezime, tip) value("Ivo","Andrić",3);
insert into projekat.osoblje(ime, prezime, tip) value("Meša","Selimović",3);
insert into projekat.osoblje(ime, prezime, tip) value("Đura","Jakšić",3);

insert into projekat.osoblje_predstave(osoblje_id, predstava_id) value (1,1);
insert into projekat.osoblje_predstave(osoblje_id, predstava_id) value (3,1);
insert into projekat.osoblje_predstave(osoblje_id, predstava_id) value (4,1);
insert into projekat.osoblje_predstave(osoblje_id, predstava_id) value (11,1);
insert into projekat.osoblje_predstave(osoblje_id, predstava_id) value (2,2);
insert into projekat.osoblje_predstave(osoblje_id, predstava_id) value (3,2);
insert into projekat.osoblje_predstave(osoblje_id, predstava_id) value (6,2);
insert into projekat.osoblje_predstave(osoblje_id, predstava_id) value (12,2);
insert into projekat.osoblje_predstave(osoblje_id, predstava_id) value (10,3);
insert into projekat.osoblje_predstave(osoblje_id, predstava_id) value (13,3);
insert into projekat.osoblje_predstave(osoblje_id, predstava_id) value (5,3);




insert into projekat.pozoriste(naziv, grad, broj_sjedista) value ("Narodno Pozorište RS","Banjaluka",150);
insert into projekat.pozoriste(naziv, grad, broj_sjedista) value ("Dječije pozorište","Banjaluka",80);
insert into projekat.pozoriste(naziv, grad, broj_sjedista) value ("Pinokio","Beograd",100);
insert into projekat.pozoriste(naziv, grad, broj_sjedista) value ("Jugoslovensko dramsko pozorište","Beograd",300);
insert into projekat.pozoriste(naziv, grad, broj_sjedista) value ("Novosadsko pozorište","Novi Sad",500);
insert into projekat.pozoriste(naziv, grad, broj_sjedista) value ("Hrvatsko narodno kazalište","Zagreb",250);

insert into projekat.predstava(naziv,zanr) value("Romeo i Julija",5);
insert into projekat.predstava(naziv,zanr) value("Umri muški",1);
insert into projekat.predstava(naziv,zanr) value("Pijanista",2);
insert into projekat.predstava(naziv,zanr) value("Antigona",5);
insert into projekat.predstava(naziv,zanr) value("Cats",7);
insert into projekat.predstava(naziv,zanr) value("Crvenkapica",6);
insert into projekat.predstava(naziv,zanr) value("Gospođa ministarka",3);
insert into projekat.predstava(naziv,zanr) value("Kako vam drago",1);
insert into projekat.predstava(naziv,zanr) value("Žensko, ne žensko",4);

insert into projekat.izvodjenje_predstave(predstava_id, pozoriste_id, cijena, datum_i_vrijeme) value (1,4,15.00,'2024-02-14 19:00:00');
insert into projekat.izvodjenje_predstave(predstava_id, pozoriste_id, cijena, datum_i_vrijeme) value(2,1,10.00,'2023-10-14 18:30:00');
insert into projekat.izvodjenje_predstave(predstava_id, pozoriste_id, cijena, datum_i_vrijeme) value(2,1,10.00,'2023-10-21 19:30:00');
insert into projekat.izvodjenje_predstave(predstava_id, pozoriste_id, cijena, datum_i_vrijeme) value(3,4,8.50,'2023-09-29 19:15:00');
insert into projekat.izvodjenje_predstave(predstava_id, pozoriste_id, cijena, datum_i_vrijeme) value(4,5,12.00,'2023-10-06 19:30:00');
insert into projekat.izvodjenje_predstave(predstava_id, pozoriste_id, cijena, datum_i_vrijeme) value(5,3,7.00,'2023-11-15 18:00:00');
insert into projekat.izvodjenje_predstave(predstava_id, pozoriste_id, cijena, datum_i_vrijeme) value(6,2,7,'2023-12-22 19:45:00');
insert into projekat.izvodjenje_predstave(predstava_id, pozoriste_id, cijena, datum_i_vrijeme) value(7,1,11.00,'2023-11-18 17:45:00');
insert into projekat.izvodjenje_predstave(predstava_id, pozoriste_id, cijena, datum_i_vrijeme) value(8,5,10.00,'2023-10-05 20:00:00');
insert into projekat.izvodjenje_predstave(predstava_id, pozoriste_id, cijena, datum_i_vrijeme) value(9,3,10,'2024-12-06 18:45:00');




update projekatradnik_pozorista.osoblje set ime="Zarko" where id=1;
update projekat.osoblje set tip=1 where id=8;


delete from projekat.posjetilac_pozorista where id=6;
delete from projekat.posjetilac_pozorista where id=7;


SELECT * from projekat.osoblje_predstave;
SELECT * from projekat.osoblje ;
SELECT * from projekat.karta;
SELECT * from projekat.posjetilac_pozorista ;
SELECT * from projekat.radnik_pozorista ;
SELECT * from projekat.pozoriste;
SELECT * from projekat.izvodjenje_predstave;
SELECT * from projekat.predstava;