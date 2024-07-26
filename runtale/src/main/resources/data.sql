-- src/main/resources/data.sql
INSERT INTO tier (id, description, name, image_url) VALUES
                                                        (1, '가장 빠른 속도의 티어', '치타', 'https://example.com/images/cheetah.png'),
                                                        (2, '빠른 속도의 티어', '말', 'https://example.com/images/horse.png'),
                                                        (3, '중간 속도의 티어', '토끼', 'https://example.com/images/rabbit.png'),
                                                        (4, '느린 속도의 티어', '거북이', 'https://example.com/images/turtle.png'),
                                                        (5, '가장 느린 속도의 티어', '달팽이', 'https://example.com/images/snail.png')
    ON DUPLICATE KEY UPDATE
                         description = VALUES(description),
                         name = VALUES(name),
                         image_url = VALUES(image_url);
