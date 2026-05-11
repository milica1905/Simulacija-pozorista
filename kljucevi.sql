use `projekat`;

alter table `karta`
ADD foreign key (posjetilac_id) references posjetilac_pozorista(id);

alter table `karta`
ADD foreign key (izvodjenje_predstave_id) references izvodjenje_predstave(id);

alter table `izvodjenje_predstave`
ADD foreign key (predstava_id) references predstava(id);

alter table `izvodjenje_predstave`
ADD foreign key (pozoriste_id) references pozoriste(id);

alter table `osoblje_predstave`
ADD foreign key (osoblje_id) references osoblje(id);

alter table `osoblje_predstave`
ADD foreign key (predstava_id) references predstava(id);

alter table `radnik_pozorista`
ADD foreign key (pozoriste_id) references pozoriste(id);
