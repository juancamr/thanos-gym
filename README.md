# Pasos para establecer tu entorno de desarrollo

1. Tener instalado jdk 21
2. Crear un archivo .env en la raiz del proyecto con las siguientes variables
```
DB_HOST=jdbc:mysql://localhost:3306/thanosgym
DB_USER=root
DB_PASSWORD=
```
3. Crear una base de datos usando el archivo script.sql

# Requerimientos funcionales

- [x] iniciar sesion como administrador
- [x] registrar nueva cuenta de administrador
- [x] crear plan
- [x] eliminar plan
- [x] editar plan
- [x] registrar a un nuevo cliente al gimnasio
- [ ] buscar un cliente atraves del dni
- [ ] buscar un cliente con codigo
- [ ] autocompletar datos del cliente con su dni al registrar (beta)
- [ ] enviar correo de confirmacion cuando te suscribes a la membresia, boleta del gimnasio (beta)
- [ ] cuanto tiempo le queda a su suscripcion cuando se busca al cliente (verde mas de un mes, rojo menos de una semana)
- [ ] extender su membresia con el mismo o otro plan
- [ ] buscar en tiempo real en una tabla de clientes
- [ ] que la sesion persista (no es necesario tener que estar iniciando sesion cada vez que el programa se cierra)
