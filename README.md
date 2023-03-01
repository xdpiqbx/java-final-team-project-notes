# java-final-team-project-notes

## BeesTeam

---

Default credentials:

`admin` `super_secret_password`

---

Environment Variables:

- `PROFILE` (default - `dev`; production - `prod`)
- `DB_URL`
- `DB_USERNAME`
- `DB_PASSWORD`

---

### Technologies used in project

- Spring Boot
- Spring MVC
- Spring Security
- Spring Data
- Spring Validation
- Thymeleaf + Bootstrap
- Flyway
- Database production PostgreSQL
- Database development H2
- Commonmark
- JavaScript
- HTML

---

### Login page

![login](./imgs_for_readme/login.png)

---

### Register page

>Here  we have custom validation

![register](./imgs_for_readme/register.png)

---

### Notes List page

![notes list](./imgs_for_readme/notes-page.png)

Here you can: Create, Read, Update, Delete your notes. Copy link by pressing button Link.
Add mock notes. Use admin panel if you logged as admin. And Quit.

---

### Service users page

![admin](./imgs_for_readme/admin.png)

As Admin here you can: see all users, choose user to update, delete any user.

---

### Edit user page

![edit user](./imgs_for_readme/edit%20user%20page.png)

As Admin here you can: edit information about user.

---

Every input field has validation

---

Database PostgreSQL is located on home Synology NAS in a docker container.