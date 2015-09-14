DELETE FROM artist;
DELETE FROM song;
DELETE FROM playlist;
DELETE FROM playlist_association;

-- Artists
INSERT INTO artist (id, name) VALUES
(1, 'Metallica'),
(2, 'Iron Maiden'),
(3, 'Ramones'),
(4, 'Tom Jobim'),
(5, 'Toquinho');

-- Songs
INSERT INTO song (id, artist_id, name, file) VALUES
(1, 1, 'Fuel', 'fuel.ogg'),
(2, 1, 'Nothing else matters', 'nothing-else-matters.ogg'),
(3, 2, 'Two minutes to midnight', 'two-minutes-to-midnight.ogg'),
(4, 2, 'Run to the hills', 'run-to-the-hills.ogg'),
(5, 2, 'Rainmaker', 'rainmaker.ogg'),
(6, 3, 'Blitzkrieg bop', 'blitzkrieg-bop.ogg'),
(7, 3, 'I wanna be sedated', 'i-wanna-be-sedated.ogg'),
(8, 3, 'Pet cemetary', 'pet-cemetary.ogg'),
(9, 4, 'Garota de Ipanema', 'garota-de-ipanema.ogg'),
(10, 4, 'Águas de março', 'aguas-de-marco.ogg'),
(11, 5, 'Aquarela', 'aquarela.ogg'),
(12, 5, 'Tarde em Itapuã', 'tarde-em-itapua.ogg');

-- Playlists
INSERT INTO playlist (id, name, cover) VALUES
(1, 'Metallica', 'metallica.jpg'),
(2, 'Iron Maiden', 'iron-maiden.jpg'),
(3, 'Ramones', 'ramones.jpg'),
(4, 'Tom Jobim', 'tom-jobim.jpg'),
(5, 'Toquinho', 'toquinho.jpg'),
(6, 'Metal', 'metal.jpg'),
(7, 'Punk rock', 'punk.jpg'),
(8, 'MPB', 'mpb.jpg');

-- Playlist association
INSERT INTO playlist_association (playlist_id, song_id) VALUES
(1, 1),
(1, 2),
(2, 3),
(2, 4),
(2, 5),
(3, 6),
(3, 7),
(3, 8),
(4, 9),
(4, 10),
(5, 11),
(5, 12),
(6, 1),
(6, 2),
(6, 3),
(6, 4),
(6, 5),
(7, 6),
(7, 7),
(7, 8),
(8, 9),
(8, 10),
(8, 11),
(8, 12);

SELECT setval('song_id_seq', (SELECT max(id) FROM song));
SELECT setval('artist_id_seq', (SELECT max(id) FROM artist));
SELECT setval('playlist_id_seq', (SELECT max(id) FROM playlist));
SELECT setval('playlist_association_id_seq', (SELECT max(id) FROM playlist_association));
