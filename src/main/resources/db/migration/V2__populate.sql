INSERT INTO author(name, password, authority)
  VALUES
    ('admin', '{bcrypt}$2a$08$1dz1z.hv3D/Ad/vuiLZZrODTO/ncdoVXdMtkRn1/F7SvQjpiSDR/O', 'ADMIN');

INSERT INTO note(id, title, content, access_type, author_id)
    VALUES
    ('e04a3bd9-555d-4c25-ab1a-5efdfc53aba7', 'title_1', 'content_1', 'PRIVATE', 1),
    ('77ab424a-082d-4249-aea0-426efff0f2b4', 'title_2', 'content_2', 'PUBLIC', 1),
    ('69c56bc4-d46a-4393-8d73-69cb4cc6bbb6', 'title_3', 'content_3', 'PRIVATE', 1),
    ('121f80e3-37d5-4223-bd48-3a554148c593', 'title_4', 'content_4', 'PUBLIC', 1);