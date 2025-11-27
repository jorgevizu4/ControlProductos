# 📦 ControlProductos

Aplicación en **Java** para la gestión de productos con conexión a **MySQL**, que permite la **exportación de datos a XML** y la **importación desde XML a la base de datos** utilizando la librería **JABX**.  
El proyecto incluye ejemplos de transformación con **XSLT** y una interfaz HTML básica para visualizar los productos.

---

## ✨ Funcionalidades
- Conexión a una base de datos **MySQL** para almacenar y consultar productos.
- **Exportación** de registros a archivos **XML**.
- **Importación** de productos desde XML a la base de datos.
- Transformación de datos mediante **XSLT** (`transformador.xsl`).
- Visualización de productos en una página **HTML** (`productos.html`).
- Proyecto gestionado con **Maven** para facilitar la compilación y dependencias.

---

## 📂 Estructura del proyecto
ControlProductos/ 
│── src/main/ # Código fuente principal 
│ ├── java/ # Paquetes Java (modelo, controlador, DAO, etc.) 
│ └── resources/ # Recursos adicionales 
│── productos.html # Página HTML para mostrar productos 
│── transformador.xsl # Hoja de estilo XSLT para transformar XML 
│── pom.xml # Configuración de Maven 
│── .gitignore # Archivos ignorados por Git

---

## ⚙️ Requisitos
- **Java 17+**  
- **Maven 3.6+**  
- **MySQL 8.0+**  
- Librería **JABX** para manejo de XML  

---

## 🚀 Instalación y ejecución
1. Clonar el repositorio:
   ```bash
   git clone https://github.com/jorgevizu4/ControlProductos.git
   cd ControlProductos

## 📖 Uso
Exportar productos a XML: Genera un archivo XML con los registros de la base de datos.

Importar productos desde XML: Lee un archivo XML y carga los datos en la base de datos.

Transformación XSLT: Convierte el XML en un formato legible (HTML).

## 🛠️ Tecnologías utilizadas
Java

MySQL

Maven

XSLT / HTML

## 📌 Próximas mejoras
Interfaz gráfica con JavaFX o Swing.

Validación avanzada de XML con XSD.

API REST para gestionar productos desde aplicaciones externas.

## 👨‍💻 Autor
Proyecto desarrollado por jorgevizu4.
