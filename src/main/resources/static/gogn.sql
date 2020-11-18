INSERT INTO users (id,email,name,year,password) VALUES
  (1, 'unnur@gmail.com','Unnur Jónsdóttir',1935,'$2a$10$w4k72mBNTxrT64TolEZ7h.9hoCFap.vm0lHlgyVQieWZsT7RB4tkC'),
  (2, 'jon@gmail.com','Jón',1935,'$2a$10$j5/qNDGIS588bkfUHYv/E.7fHkY7w5bRCRLYEeK9g5uYIrsru9yG6'),
  (3, 'sqlee.g@gmail.com','test',1950,'$2a$10$U/h4wnQ/FVWPe3olQlTub.9wZG8VA9DsBWQinv0braLQOciWSUlgO');

INSERT INTO course (id,title,description) VALUES
  (1,'Grunnatridi','Þetta námskeið fjallar um grunnotkun á spjaltölvu og utśkýrir mikilvæg hugtök sem þar koma fram.'),
  (3,'Internetið', 'Hér verður fjallað um internetið, hvað það er, hvernig það varð til og hvernig notar maður það.' );
INSERT INTO lesson (id,course_id,filename,text,title) VALUES
  (1,1,'1.1_Hvad_er_spjaldtolva.mp4','Hvernig virkar spjaldtölva og hvernig stjórnar maður henni.	','Hvad er spjadtolva'),
  (2,1,'1.2.2_Haekka_og_laekka.mp4','','Slokkva og kveikja'),
  (3,3,'3.1.1_Hvad_er_internetid.mp4','Internetið er alþjóðlegt kerfi tölvuneta...','Hvað er internetið'),
  (4,3,'3.1.2_Hvar_er_len.mp4','','Hvað er lén?'),
  (5,3,'3.2_Leitarstikan.mp4','','Leitarstikan'),
  (6,3,'3.3_Flipar.mp4','','Flipar'),
  (7,3,'3.4_Bokamerki.mp4','','Bókamerki');
