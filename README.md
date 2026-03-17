# MC-Core: De Joven a Desarrollador 🚀

Este proyecto es una evolución personal. Nació hace muchos años, cuando era joven, como un simple plugin para servidores de Minecraft 1.8.x. Hoy, he decidido retomarlo para actualizarlo, probar mis habilidades actuales y, sobre todo, por pura diversión.

El proyecto se divide en dos etapas:

## 📜 Versión Legacy (Minecraft 1.8.x)
Es el corazón original del plugin, súper optimizado para la versión 1.8.8 de Spigot.
- **Auth**: Sistema integrado de login/registro (sin bases de datos externas).
- **Spawn**: Gestión de punto de aparición y protección de zona.
- **Anti-Grief**: Bloqueo de construcción y límites de zona.
- **Habilidades**: Doble salto dinámico y velocidad en el spawn.
- **Hologramas**: Mensajes flotantes nativos sin plugins de terceros.

## ✨ Versión Modern v2 (Minecraft 1.20.x+)
La evolución mejorada con estándares actuales.
- **Soporte Moderno**: Compatible con las últimas versiones de Paper/Spigot.
- **Menú Interactivo (GUI)**: Panel de control dentro del juego para gestionar el plugin.
- **Código Refactorizado**: Aplicando mejores prácticas de diseño.
- **Funcionalidades Extendidas**: Más personalización y mensajes dinámicos.

---

## 📊 Comparativa de Funciones: Legacy vs Modern

Aquí tienes un listado detallado de lo que incluye cada versión del **MC-MegaCore**. La versión Moderna es la más avanzada, pero la Legacy está optimizada para el rendimiento clásico.

| Función | 📜 Mega-Core Legacy (1.8.x) | ✨ Mega-Core Modern v2 (1.20.1+) |
| :--- | :--- | :--- |
| **Arquitectura** | Spigot API clásica | **Paper API** avanzada (más eficiente) |
| **Colores** | Código de colores `&` básico | **MiniMessage (Adventure)** con degradados |
| **Interfaz** | Comandos de chat rápidos | **Admin GUI** interactivo (`/core menu`) |
| **Portales** | Portales de Nether fijos | **Selección libre** (Estilo WorldEdit) |
| **Permisos** | Sistema YAML (`permissions.yml`) | **Base de Datos SQLite** (`database.db`) |
| **Auto-Premium** | `/premium` (Salto de login) | `/premium` (Integración v2) |
| **Habilidades** | Doble salto y velocidad básica | Habilidades mejoradas y configurables |
| **Persistencia** | Archivos .yml planos | SQL Pro (Rápido y fiable) |
| **Requisitos Java** | **Java 8** | **Java 17 / 21** |

## 📖 Guía Maestra de Comandos

### 📜 Mega-Core Legacy (1.8.x)
Enfocado en estabilidad y comandos clásicos de Lobby.
- **/register <password>**: Registra tu cuenta en el servidor.
- **/login <password>**: Inicia sesión para poder moverte.
- **/premium**: Activa el modo "Premium". Si tu cuenta es original, el core lo recordará y no te pedirá login nunca más.
- **/spawn**: Te teletransporta al punto de inicio.
- **/setspawn**: (Admin) Establece el lugar donde aparecerán los jugadores.
- **/rank <jugador> <rank>**: (Admin) Cambia el rango de un jugador (`MEMBER`, `VIP`, `ADMIN`).

### ✨ Mega-Core Modern v2 (1.20.1+)
Usa interfaces visuales y tecnología moderna.
- **/core menu**: Abre el **Panel de Control** visual. Desde aquí puedes activar/desactivar el Doble Salto y la Velocidad con un click.
- **/premium**: Activa el modo Premium v2. Usa la potencia de SQL para recordar tu estado instantáneamente.
- **/spawn**: Teletransporte rápido al punto de inicio.
- **/core reload**: Recarga la configuración del plugin sin reiniciar el servidor.

---

## 🛠️ Cómo Compilar
He incluido los scripts de **Maven Wrapper (`mvnw`)** para que no necesites instalar Maven manualmente.

Para compilar cualquiera de las versiones:
1. Navega a la carpeta correspondiente (`legacy` o `modern`).
2. Ejecuta el comando:
   ```powershell
   ./mvnw clean package
   ```
3. El plugin final (`.jar`) estará en la carpeta `target/`.

## 🎮 Funcionamiento
- Al entrar, si no estás registrado, deberás usar `/register <clave>`.
- Una vez logueado con `/login <clave>`, podrás moverte y usar el spawn.
- En la zona de spawn (50 bloques):
  - No puedes construir ni romper.
  - Tienes velocidad aumentada.
  - Puedes hacer **Doble Salto** (presionando espacio dos veces).
  - No puedes salir del recinto (a menos que seas admin).
