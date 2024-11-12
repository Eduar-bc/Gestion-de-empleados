/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import igu.Panel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Eduar
 */
//Los metodos de esta clase usan try-with-resources
public class LogicaCargo {

    /*
 * Método `guardarCargo`: Guarda un nuevo cargo en la base de datos si el código del cargo no existe previamente.
     */
    public void guardarCargo(Connection connection, String codigo, String nombre, String salario) {
        // Verifica si el cargo con el código especificado ya existe en la base de datos.
        boolean existeCargo = compruebaExistenciaCargo(connection, codigo);

        // Si el cargo no existe, procede a guardarlo en la base de datos.
        if (!existeCargo) {
            // Define la consulta SQL para insertar un nuevo cargo en la base de datos.
            String query = "INSERT INTO cargos(cod_cargo, nombre, sueldo) VALUES(?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(query);) {
                // Asigna los valores proporcionados a los parámetros de la consulta.
                statement.setString(1, codigo);
                statement.setString(2, nombre);
                statement.setString(3, salario);

                // Ejecuta la consulta de inserción y obtiene el número de filas afectadas.
                int filas = statement.executeUpdate();

                // Si se insertó al menos una fila, muestra un mensaje de éxito.
                if (filas > 0) {
                    JOptionPane.showMessageDialog(null, "Cargo guardado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Si no se insertó ninguna fila, muestra un mensaje de error.
                    JOptionPane.showMessageDialog(null, "Cargo NO guardado", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException ex) {
                // Si ocurre un error durante la ejecución de la consulta, captura la excepción y la registra.
                Logger.getLogger(LogicaCargo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            // Si el código del cargo ya existe, muestra un mensaje de error.
            JOptionPane.showMessageDialog(null, "Código del cargo existente", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /*
 * Método `buscarCargos`: Obtiene todos los registros de cargos de la base de datos y los devuelve en un modelo de tabla.
     */
    public DefaultTableModel buscarCargos(Connection connection) {
        // Define la consulta SQL para obtener todos los registros de la tabla "cargos".
        String query = "SELECT * FROM cargos";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            // Ejecuta la consulta y obtiene los resultados en un ResultSet.
            try (ResultSet resultSet = statement.executeQuery()) {
                // Crea un modelo de tabla para almacenar los datos de los cargos.
                DefaultTableModel model1 = new DefaultTableModel();
                model1.addColumn("Codigo");
                model1.addColumn("Nombre");
                model1.addColumn("Sueldo");

                // Obtiene el número de columnas del ResultSet.
                int numColum = resultSet.getMetaData().getColumnCount();
                Object[] filas = new Object[numColum];  // Crea un arreglo para almacenar cada fila de datos.

                // Itera sobre los resultados del ResultSet y agrega cada fila al modelo de tabla.
                while (resultSet.next()) {
                    // Recorre todas las columnas de la fila actual y las guarda en el arreglo "filas".
                    for (int i = 0; i < numColum; i++) {
                        filas[i] = resultSet.getObject(i + 1);  // Recupera el valor de cada columna.
                    }
                    model1.addRow(filas);  // Agrega la fila al modelo de tabla.
                }
                // Retorna el modelo de tabla con los datos de los cargos.
                return model1;
            }
        } catch (SQLException ex) {
            // Si ocurre un error durante la consulta o el procesamiento de los resultados, captura la excepción.
            Logger.getLogger(LogicaCargo.class.getName()).log(Level.SEVERE, null, ex);
            // Muestra un mensaje de error en caso de una excepción.
            JOptionPane.showMessageDialog(null, "Cargo no guardado Q", "Error", JOptionPane.INFORMATION_MESSAGE);
            // Retorna null en caso de error.
            return null;
        }
    }

    /*
 * Método `actualizarCargo`: Actualiza los detalles de un cargo existente en la base de datos.
     */
    public void actualizarCargo(Connection connection, String codigo, String nombre, String sueldo) {
        // Comprueba si el cargo existe en la base de datos mediante el código proporcionado.
        boolean existeCargo = compruebaExistenciaCargo(connection, codigo);

        // Si el cargo existe, procede con la actualización.
        if (existeCargo) {
            // Define la consulta SQL para actualizar los datos del cargo.
            String query = "UPDATE cargos SET nombre = ?, sueldo = ? WHERE cod_cargo = ? ";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                // Asigna los valores proporcionados a los parámetros de la consulta.
                statement.setString(1, nombre);   // Establece el nuevo nombre del cargo.
                statement.setString(2, sueldo);   // Establece el nuevo sueldo del cargo.
                statement.setString(3, codigo);   // Establece el código del cargo para identificar el registro a actualizar.

                // Ejecuta la actualización y obtiene el número de filas afectadas.
                int rowUpdated = statement.executeUpdate();

                // Si se actualizó al menos una fila, muestra un mensaje de éxito.
                if (rowUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "El cargo ha sido actualizado correctamente.", "Actualización Exitosa", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Si no se actualizó ninguna fila, muestra un mensaje de error.
                    JOptionPane.showMessageDialog(null, "El cargo NO ha sido actualizado correctamente.", "Error", JOptionPane.INFORMATION_MESSAGE);
                }

            } catch (SQLException ex) {
                // Si ocurre un error durante la consulta o la ejecución, captura la excepción.
                Logger.getLogger(LogicaCargo.class.getName()).log(Level.SEVERE, null, ex);
                // Muestra un mensaje de error en caso de excepción.
                JOptionPane.showMessageDialog(null, "Error al actualizar el cargo", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            // Si el cargo no existe, muestra un mensaje de error.
            JOptionPane.showMessageDialog(null, "El cargo no existe", "Error", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    /*
 * Método `eliminarCargo`: Elimina un cargo de la base de datos si existe.
     */
    public void eliminarCargo(Connection connection, String codigo) {
        // Verifica si el cargo existe en la base de datos mediante el código proporcionado.
        boolean cargoExiste = compruebaExistenciaCargo(connection, codigo);

        // Si el cargo existe, muestra un cuadro de confirmación para eliminarlo.
        if (cargoExiste) {
            // Muestra un cuadro de confirmación para el usuario.
            int confirmacion = JOptionPane.showConfirmDialog(null,
                    "¿Desea ELIMINAR al Cargo con codigo " + codigo,
                    "Confirmación de Actualización",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            // Si el usuario confirma la eliminación.
            if (confirmacion == JOptionPane.OK_OPTION) {
                // Define la consulta SQL para eliminar el cargo.
                String query = "DELETE FROM cargos WHERE cod_cargo = ?";
                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    // Establece el código del cargo a eliminar en el parámetro de la consulta.
                    statement.setString(1, codigo);

                    // Ejecuta la eliminación del cargo en la base de datos.
                    statement.execute();

                    // Muestra un mensaje indicando que el cargo ha sido eliminado correctamente.
                    JOptionPane.showMessageDialog(null, "El Cargo ha sido ELIMINADO correctamente.", "Actualización Exitosa", JOptionPane.INFORMATION_MESSAGE);

                } catch (SQLException ex) {
                    // Si ocurre un error durante la eliminación, captura la excepción y muestra un mensaje de error.
                    Logger.getLogger(LogicaCargo.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Error al Eliminar el cargo", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                // Si el usuario cancela la eliminación, muestra un mensaje indicando que la eliminación fue cancelada.
                JOptionPane.showMessageDialog(null, "Eliminación de Cargo Cancelada.", "Cancelar", JOptionPane.INFORMATION_MESSAGE);
            }

        } else {
            // Si el cargo no existe, muestra un mensaje indicando que el código de cargo no se encontró.
            JOptionPane.showMessageDialog(null, "El código de cargo ingresado no existe", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    /*
 * Método `compruebaExistenciaCargo`: Verifica si un cargo existe en la base de datos.
     */
    public boolean compruebaExistenciaCargo(Connection connection, String codigo) {
        // Definición de la consulta SQL para buscar un cargo por su código.
        String query = "SELECT cod_cargo FROM cargos WHERE cod_cargo = ?";

        // Variable que se inicializa en falso para indicar si el cargo no existe.
        boolean existe = false;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            // Establece el código del cargo en el parámetro de la consulta SQL.
            statement.setString(1, codigo);

            // Ejecuta la consulta y obtiene el resultado.
            ResultSet resultSet = statement.executeQuery();

            // Si se encuentra un registro, significa que el cargo existe.
            if (resultSet.next()) {
                existe = true; // El cargo existe en la base de datos.
            }
        } catch (SQLException ex) {
            // Si ocurre un error en la ejecución de la consulta, captura la excepción y muestra un mensaje de error.
            Logger.getLogger(LogicaCargo.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Cargo no existe", "Error", JOptionPane.INFORMATION_MESSAGE);
        }

        // Retorna el resultado, verdadero si el cargo existe, falso si no.
        return existe;
    }
}
