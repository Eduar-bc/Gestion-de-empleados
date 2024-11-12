/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package logica;

import igu.Panel;
import persistencia.DatabaseConnection;

/**
 *
 * @author Eduar
 */
/*
 * Clase `GestiondePersonal`: Es la clase principal de la aplicación para gestionar el sistema de personal.
 */
public class GestiondePersonal {

    /*
     * Método `main`: Es el punto de entrada de la aplicación.
     */
    public static void main(String[] args) {
        // Crea una instancia de la clase `Panel`, que representa la interfaz gráfica de usuario.
        Panel panel = new Panel();

        // Establece que el panel sea visible para el usuario.
        panel.setVisible(true);

        // Centra el panel en la pantalla.
        panel.setLocationRelativeTo(null);
    }
}
