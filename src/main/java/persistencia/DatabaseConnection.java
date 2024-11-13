/*
 * Paquete `persistencia`: Contiene las clases relacionadas con la gestión de la persistencia de datos.
 */
package persistencia;

// Importación de las clases necesarias para establecer una conexión con la base de datos.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Clase `DatabaseConnection`: Proporciona métodos para establecer una conexión con una base de datos MySQL.
 */
public class DatabaseConnection {
    // Constante `URL`: Contiene la URL de la base de datos MySQL, incluyendo el nombre del host, el puerto y el nombre de la base de datos.
    private static final String URL = "jdbc:mysql://localhost:3306/nombre-base-de-datos";
    
    // Constante `USER`: Almacena el nombre de usuario para autenticarse en la base de datos.
    private static final String USER = "usuario-base-de-datos";
    
    // Constante `PASSWORD`: Almacena la contraseña del usuario para acceder a la base de datos.
    private static final String PASSWORD = "contraseña-base-de-datos";
    
    /*
     * Método `getConnection`: Establece y retorna una conexión a la base de datos.
     * @return `Connection` objeto de conexión a la base de datos o `null` si falla la conexión.
     */
    public Connection getConnection(){
        // Variable `connection`: Almacena la conexión con la base de datos.
        Connection connection = null;
        
        try {
            // Intenta obtener una conexión usando la URL, el usuario y la contraseña proporcionados.
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos."); // Imprime un mensaje si la conexión es exitosa.
        } catch(SQLException e) {
            // Imprime un mensaje de error si ocurre un problema al intentar conectarse.
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        
        // Retorna el objeto `Connection` o `null` si no se pudo establecer la conexión.
        return connection;
    }
}
