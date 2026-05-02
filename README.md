# II-Tarea-Semana-5
En este proyecto se trabajo la seguridad del sistema usando Spring Security, para controlar quien entra y que puede ver cada usuario.

Primero se dejaron algunas rutas publicas, como la pagina principal o login, para que cualquiera pudiera entrar sin iniciar sesion.

Despues se protegieron las demas rutas, donde ya se necesita usuario y contrasena para ingresar.

Se crearon dos roles:

ADMIN = tiene acceso completo al sistema. Puede entrar a productos, clientes, ventas, usuarios y demas modulos.
USER = acceso limitado. Solo puede entrar a secciones generales, como productos.

Cuando una persona intenta entrar a una ruta protegida sin haber iniciado sesin, automaticamente el sistema lo manda al login.

Si entra con usuario normal y trata de ir a una parte de administrador, el sistema le niega el acceso.

Con usuario ADMIN si se puede entrar sin problema a todas las secciones permitidas.

Se hicieron pruebas con ambos usuarios para comprobar que la seguridad funcionara bien y corregir errores encontrados.

En conclusion, el proyecto quedo funcionando con inicio de sesion, control por roles y proteccion de rutas importantes.