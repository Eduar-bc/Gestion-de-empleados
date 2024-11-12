/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import igu.Panel;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Eduar
 */
//Los metodos de esta clase no usan try-with-resources
public class LogicaEmpleado {

    /*
 * Método `obtenerCodMunicipio`: Obtiene el código de municipio y departamento basado en sus nombres.
     */
    public String[] obtenerCodMunicipio(Connection connection, String municipio, String departamento) {

        // Consulta SQL que selecciona el código del municipio y el código del departamento
        // basándose en los nombres de municipio y departamento proporcionados.
        String query = "SELECT m.cod_municipio, m.cod_departamento "
                + "FROM municipios m "
                + "JOIN departamentos d ON m.cod_departamento = d.cod_departamento "
                + "WHERE m.nombre = ? AND d.nombre = ?;";

        try {
            // Prepara la sentencia SQL utilizando la conexión proporcionada.
            PreparedStatement statement = connection.prepareStatement(query);

            // Establece los parámetro de la consulta con el nombre del municipio y departamento.
            statement.setString(1, municipio);
            statement.setString(2, departamento);

            // Ejecuta la consulta y almacena los resultados en un `ResultSet`.
            ResultSet resultSet = statement.executeQuery();

            // Obtiene el número de columnas en el resultado de la consulta.
            int numColum = resultSet.getMetaData().getColumnCount();

            // Verifica si el número de columnas es diferente de 2, indicando un error en el resultado.
            if (numColum != 2) {
                // Muestra un mensaje de error si el número de columnas no es el esperado.
                JOptionPane.showMessageDialog(null, "Error al obtener los codigos del municipio ", "Error", JOptionPane.ERROR_MESSAGE);
                return null;
            } else {
                // Declara un arreglo `codigos` para almacenar el código de municipio y de departamento.
                String[] codigos = new String[2];

                // Verifica si el `ResultSet` contiene al menos una fila de datos.
                if (resultSet.next()) {
                    // Almacena el código del municipio en la primera posición del arreglo.
                    codigos[0] = resultSet.getString("cod_municipio");

                    // Almacena el código del departamento en la segunda posición del arreglo.
                    codigos[1] = resultSet.getString("cod_departamento");
                }
                // Retorna el arreglo `codigos` con los resultados.
                return codigos;
            }

        } catch (SQLException ex) {
            // Registra el error en el logger especificado, indicando que ocurrió una excepción SQL.
            Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);

            // Imprime un mensaje en la consola para indicar que ocurrió un error al obtener los códigos.
            System.out.println("Eror Q obtener codigos municipio y departamento");
        }

        // Retorna `null` si ocurrió un error o no se encontraron resultados.
        return null;
    }

    /*
 * Método `obtenerCodCargo`: Obtiene el código de un cargo basado en su nombre.
     */
    public String obtenerCodCargo(Connection connection, String nombre) {

        // Consulta SQL que selecciona el código del cargo basado en el nombre del cargo.
        String query = "SELECT cod_cargo FROM cargos WHERE nombre = ?";

        // Variable `codigoCargo`: Almacena el código del cargo encontrado en la base de datos.
        String codigoCargo = null;

        try {
            // Prepara la sentencia SQL utilizando la conexión proporcionada.
            PreparedStatement statement = connection.prepareStatement(query);

            // Establece el primer parámetro de la consulta con el nombre del cargo.
            statement.setString(1, nombre);

            // Ejecuta la consulta y almacena los resultados en un `ResultSet`.
            ResultSet resultSet = statement.executeQuery();

            // Verifica si `resultSet` contiene al menos una fila de datos.
            if (resultSet.next()) {
                // Almacena el código del cargo obtenido en la variable `codigoCargo`.
                codigoCargo = resultSet.getString("cod_cargo");
            } else {
                // Muestra un mensaje de advertencia si no se encontró el código del cargo.
                JOptionPane.showMessageDialog(null, "No se encontró el código para el cargo especificado", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }

            // Cierra el `PreparedStatement` para liberar los recursos.
            statement.close();

            // Cierra el `ResultSet` para liberar los recursos.
            resultSet.close();

        } catch (SQLException ex) {
            // Registra el error en el logger especificado, indicando que ocurrió una excepción SQL.
            Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);

            // Imprime un mensaje en la consola para indicar que ocurrió un error al obtener el código.
            System.out.println("Eror Q obtener codigo cargo");
        }

        // Retorna el código del cargo, o `null` si no se encontró o ocurrió un error.
        return codigoCargo;
    }

    /*
 * Método `guardarEmpleado`: Guarda un nuevo empleado en la base de datos si no existe previamente.
     */
    public void guardarEmpleado(Connection connection, String cedula, String nombre, String apellido, String cod_cargo, String cod_departamento, String cod_municipio) {

        // Llama al método `compruebaExistenciaEmpleado` para verificar si el empleado ya existe en la base de datos.
        ResultSet resultSet = compruebaExistenciaEmpleado(connection, cedula);

        try {
            // Verifica si no se encuentra el empleado con la cédula proporcionada (si no hay resultado en `resultSet`).
            if (!resultSet.next()) {

                // Definición de la llamada al procedimiento almacenado para guardar el empleado.
                String call = "{CALL guardarEmpleado(?, ?, ?, ?, ?, ?)}"; // Llamada al procedimiento almacenado

                // Prepara la llamada al procedimiento almacenado utilizando un `CallableStatement`.
                try (CallableStatement statement = connection.prepareCall(call)) {

                    // Establece los parámetros de la llamada al procedimiento.
                    statement.setString(1, cedula);
                    statement.setString(2, nombre);
                    statement.setString(3, apellido);
                    statement.setString(4, cod_cargo);
                    statement.setString(5, cod_departamento);
                    statement.setString(6, cod_municipio);

                    // Ejecuta la llamada al procedimiento almacenado.
                    ResultSet rs = statement.executeQuery();

                    // Procesa el mensaje devuelto por el procedimiento almacenado, si es que hay resultados.
                    if (rs.next()) {
                        // Recupera el resultado del procedimiento almacenado.
                        String resultado = rs.getString("resultado");

                        // Muestra el mensaje de resultado al usuario.
                        JOptionPane.showMessageDialog(null, resultado, "Resultado", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            } else {
                // Muestra un mensaje de error si el empleado ya existe en la base de datos.
                JOptionPane.showMessageDialog(null, "El empleado que intenta ingresar ya existe. ", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            // Registra el error en el logger especificado, indicando que ocurrió una excepción SQL.
            Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);

            // Muestra un mensaje de error si no se pudo guardar el empleado.
            JOptionPane.showMessageDialog(null, "Empleado no guardado Q", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    /*
 * Método `cargarDatosComboBoxes`: Carga los datos de departamentos y cargos en dos `JComboBox`.
     */
    public JComboBox[] cargarDatosComboBoxes(Connection connection) {

        // Consulta SQL para obtener los datos de los departamentos.
        String queryDepartamentos = "SELECT * FROM departamentos";

        // Consulta SQL para obtener los datos de los cargos.
        String queryCargos = "SELECT * FROM cargos";

        // Crea un `JComboBox` para los departamentos.
        JComboBox<String> comboDepartamentos = new JComboBox<>();

        // Crea un `JComboBox` para los cargos.
        JComboBox<String> comboCargos = new JComboBox<>();

        // Agrega un valor `null` como opción inicial en ambos `JComboBox`.
        comboDepartamentos.addItem(null);
        comboCargos.addItem(null);

        try (
                // Prepara la consulta para obtener los departamentos y ejecuta la consulta.
                PreparedStatement stmtDepartamentos = connection.prepareStatement(queryDepartamentos); ResultSet rsDepartamentos = stmtDepartamentos.executeQuery(); // Prepara la consulta para obtener los cargos y ejecuta la consulta.
                 PreparedStatement stmtCargos = connection.prepareStatement(queryCargos); ResultSet rsCargos = stmtCargos.executeQuery();) {

            // Itera sobre los resultados de departamentos y agrega cada nombre al `JComboBox` de departamentos.
            while (rsDepartamentos.next()) {
                String nombreDepartamento = rsDepartamentos.getString("nombre");
                comboDepartamentos.addItem(nombreDepartamento);
            }

            // Itera sobre los resultados de cargos y agrega cada nombre al `JComboBox` de cargos.
            while (rsCargos.next()) {
                String nombreCargo = rsCargos.getString("nombre");
                comboCargos.addItem(nombreCargo);
            }

            // Muestra un mensaje en la consola indicando que los datos fueron cargados correctamente.
            System.out.println("Cargos y departamentos cargados correctamente");

        } catch (SQLException ex) {
            // Registra el error en el logger especificado, indicando que ocurrió una excepción SQL.
            Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);

            // Muestra un mensaje en la consola indicando que los datos no se cargaron correctamente.
            System.out.println("Cargos y departamentos NO cargados");
        }

        // Retorna un arreglo con los dos `JComboBox` llenos de datos.
        return new JComboBox[]{comboDepartamentos, comboCargos};
    }


    /*
 * Método `cargarMunicipios`: Carga los municipios de un departamento específico en un `JComboBox`.
     */
    public JComboBox<String> cargarMunicipios(Connection connection, String departamento) {

        // Muestra un mensaje en la consola indicando que se están cargando los municipios.
        System.out.println("cargando municipios");

        // Crea un `JComboBox` para los municipios.
        JComboBox<String> comboMunicipios = new JComboBox<>();

        // Agrega un valor `null` como opción inicial en el `JComboBox` de municipios.
        comboMunicipios.addItem(null);

        // Consulta SQL para obtener los municipios de un departamento basado en su nombre.
        String queryMunicipios = "SELECT * FROM municipios m WHERE m.cod_departamento = "
                + "(SELECT cod_departamento FROM departamentos WHERE nombre = ?)";

        try {
            // Prepara la consulta SQL para obtener los municipios.
            PreparedStatement statement = connection.prepareStatement(queryMunicipios);

            // Establece el parámetro de la consulta con el nombre del departamento.
            statement.setString(1, departamento);

            // Ejecuta la consulta y almacena los resultados en un `ResultSet`.
            ResultSet resultSet = statement.executeQuery();

            // Itera sobre los resultados obtenidos y agrega cada municipio al `JComboBox`.
            while (resultSet.next()) {
                // Recupera el nombre del municipio de la columna "nombre".
                String municipio1 = resultSet.getString("nombre"); // Asegúrate que el nombre de la columna es correcto

                // Agrega el municipio al `JComboBox`.
                comboMunicipios.addItem(municipio1);
            }

            // Cierra el `PreparedStatement` para liberar los recursos.
            statement.close();

            // Cierra el `ResultSet` para liberar los recursos.
            resultSet.close();
        } catch (SQLException ex) {
            // Registra el error en el logger especificado, indicando que ocurrió una excepción SQL.
            Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Retorna el `JComboBox` con los municipios cargados.
        return comboMunicipios;
    }


    /*
 * Método `buscarUsuarios`: Realiza una búsqueda de usuarios en la base de datos utilizando la cédula y retorna un modelo de tabla con los resultados.
     */
    public DefaultTableModel buscarUsuarios(Connection connection, String busqueda) {

        // Definición de las columnas para la tabla de resultados.
        String[] columnas = {"Cédula", "Nombre", "Apellido", "Cargo", "Sueldo", "Municipio"};

        // Crear el modelo de la tabla con las columnas definidas.
        DefaultTableModel modeloTabla = new DefaultTableModel(null, columnas);

        try {
            // Consulta SQL para buscar un usuario en la base de datos con la cédula especificada.
            String queryUser = "SELECT e.cedula, e.nombre, e.apellido, c.nombre, c.sueldo, m.nombre "
                    + "FROM db.empleados e "
                    + "JOIN cargos c ON e.cod_cargo = c.cod_cargo "
                    + "JOIN municipios m ON e.cod_municipio = m.cod_municipio "
                    + "AND e.cod_departamento = m.cod_departamento "
                    + "WHERE e.cedula = ?";

            // Prepara la consulta SQL utilizando la conexión y el parámetro de búsqueda.
            PreparedStatement statement = connection.prepareStatement(queryUser);
            statement.setString(1, busqueda);

            // Ejecuta la consulta y obtiene el conjunto de resultados.
            ResultSet resultSet = statement.executeQuery();

            // Obtiene el número de columnas de los resultados.
            int numColum = resultSet.getMetaData().getColumnCount();

            // Array para almacenar los valores de cada fila de la tabla.
            Object[] filas = new Object[numColum];

            // Variable para verificar si se encontró al menos un usuario.
            boolean found = false;

            // Itera sobre los resultados y agrega cada fila al modelo de la tabla.
            while (resultSet.next()) {
                found = true; // Se encontró al menos un usuario
                for (int i = 0; i < numColum; i++) {
                    // Recupera el valor de cada columna y lo agrega al array de filas.
                    filas[i] = resultSet.getObject(i + 1);
                }
                // Agrega la fila de datos al modelo de la tabla.
                modeloTabla.addRow(filas);
            }

            // Si no se encontró ningún usuario, muestra un mensaje de advertencia.
            if (!found) {
                JOptionPane.showMessageDialog(null, "Usuario NO encontrado", "Búsqueda", JOptionPane.INFORMATION_MESSAGE);
            }

            // Cierra el `PreparedStatement` para liberar los recursos.
            statement.close();

            // Cierra el `ResultSet` para liberar los recursos.
            resultSet.close();

        } catch (SQLException ex) {
            // Manejo de errores relacionados con la base de datos (SQL).

        } catch (Exception ex) {
            // Captura de otras excepciones que no sean SQL.
            Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
            // Muestra un mensaje de error en caso de una excepción inesperada.
            JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado. Intente nuevamente más tarde.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Retorna el modelo de tabla con los datos de los usuarios encontrados.
        return modeloTabla;
    }


    /*
 * Método `actualizar`: Actualiza los datos de un empleado en la base de datos.
     */
    public void actualizar(Connection connection, String cedula, String nombre, String apellido, String cod_cargo, String cod_departamento, String cod_municipio) {
        try {
            // Verifica si el empleado con la cédula especificada existe en la base de datos.
            ResultSet resultSet = compruebaExistenciaEmpleado(connection, cedula);

            // Si el empleado existe, muestra una ventana de confirmación.
            if (resultSet.next()) {

                // Muestra un cuadro de confirmación con los datos actuales del empleado.
                int confirmacion = JOptionPane.showConfirmDialog(null,
                        "¿Desea cambiar los datos del usuario con cédula " + cedula + "?\n" + "nombre: " + resultSet.getString("nombre"),
                        "Confirmación de Actualización",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                // Si el usuario confirma la actualización, procede con la actualización de los datos.
                if (confirmacion == JOptionPane.OK_OPTION) {
                    // Define la consulta SQL para actualizar los datos del empleado.
                    String queryActualizar = "UPDATE db.empleados SET nombre = ?, apellido = ?, cod_cargo = ?, cod_departamento = ?, cod_municipio = ? WHERE cedula = ?";

                    // Prepara la consulta para actualizar los datos del empleado.
                    try (PreparedStatement statementActualizar = connection.prepareStatement(queryActualizar)) {
                        // Asigna los valores a los parámetros de la consulta de actualización.
                        statementActualizar.setString(1, nombre);
                        statementActualizar.setString(2, apellido);
                        statementActualizar.setString(3, cod_cargo);
                        statementActualizar.setString(4, cod_departamento);
                        statementActualizar.setString(5, cod_municipio);
                        statementActualizar.setString(6, cedula); // La cédula es la clave para identificar el registro a actualizar.

                        // Ejecuta la actualización y obtiene el número de filas afectadas.
                        int rowsUpdated = statementActualizar.executeUpdate();

                        // Si se actualizó al menos una fila, muestra un mensaje de éxito.
                        if (rowsUpdated > 0) {
                            JOptionPane.showMessageDialog(null, "El empleado ha sido actualizado correctamente.", "Actualización Exitosa", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            // Si no se encontró ningún empleado con la cédula especificada, muestra un mensaje de advertencia.
                            JOptionPane.showMessageDialog(null, "No se encontró un empleado con la cédula especificada.", "Sin Resultados", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                } else {
                    // Si el usuario cancela la actualización, muestra un mensaje informando que la actualización fue cancelada.
                    JOptionPane.showMessageDialog(null, "Actualización cancelada.", "Cancelar", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                // Si no se encuentra al empleado con la cédula proporcionada, muestra un mensaje de advertencia.
                JOptionPane.showMessageDialog(null, "No se encontró un empleado con la cédula especificada.", "Sin Resultados", JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException ex) {
            // Si ocurre un error durante el proceso de actualización, se captura la excepción SQL y se muestra un mensaje de error.
            Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al actualizar. " + ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    /*
 * Método `eliminarEmpleado`: Elimina un empleado de la base de datos.
     */
    public void eliminarEmpleado(Connection connection, String cedula) {
        // Verifica si el empleado con la cédula especificada existe en la base de datos.
        ResultSet resultSet = compruebaExistenciaEmpleado(connection, cedula);
        try {
            // Si el empleado existe, muestra una ventana de confirmación.
            if (resultSet.next()) {
                // Muestra un cuadro de confirmación con los datos del empleado a eliminar.
                int confirmacion = JOptionPane.showConfirmDialog(null,
                        "¿Desea ELIMINAR al empleado con datos cédula " + cedula + "?\n" + "nombre: " + resultSet.getString("nombre"),
                        "Confirmación de Eliminación",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                // Si el usuario confirma la eliminación, procede con la eliminación del empleado.
                if (confirmacion == JOptionPane.OK_OPTION) {
                    // Define la consulta SQL para eliminar al empleado por su cédula.
                    String query = "DELETE FROM empleados WHERE cedula = ?";

                    // Prepara la consulta para eliminar al empleado.
                    try (PreparedStatement statementEliminar = connection.prepareStatement(query)) {
                        // Asigna el valor de la cédula a la consulta de eliminación.
                        statementEliminar.setString(1, cedula);

                        // Ejecuta la eliminación del empleado en la base de datos.
                        statementEliminar.execute();

                        // Muestra un mensaje informando que el empleado ha sido eliminado correctamente.
                        JOptionPane.showMessageDialog(null, "El empleado ha sido ELIMINADO correctamente.", "Eliminación Exitosa", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    // Si el usuario cancela la eliminación, muestra un mensaje informando que la operación fue cancelada.
                    JOptionPane.showMessageDialog(null, "Eliminación de Empleado Cancelada.", "Cancelar", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                // Si no se encuentra al empleado con la cédula proporcionada, muestra un mensaje de error.
                JOptionPane.showMessageDialog(null, "El empleado a eliminar no existe. ", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            // Si ocurre un error durante el proceso de eliminación, se captura la excepción SQL y se muestra un mensaje de error.
            Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al eliminar. " + ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    /*
 * Método `compruebaExistenciaEmpleado`: Verifica si un empleado con la cédula especificada existe en la base de datos.
     */
    public ResultSet compruebaExistenciaEmpleado(Connection connection, String cedula) {
        // Consulta SQL para verificar si un empleado con la cédula especificada existe en la base de datos.
        String queryBuscar = "SELECT cedula , nombre FROM empleados WHERE cedula = ?";
        ResultSet resultSet = null;
        try {
            // Prepara la consulta SQL para buscar un empleado por su cédula.
            PreparedStatement statementBuscar = connection.prepareStatement(queryBuscar);
            statementBuscar.setString(1, cedula); // Asigna la cédula proporcionada al primer parámetro de la consulta.

            // Ejecuta la consulta y obtiene el resultado en un ResultSet.
            resultSet = statementBuscar.executeQuery();

        } catch (SQLException ex) {
            // Si ocurre un error durante la ejecución de la consulta, captura la excepción y muestra un mensaje de error.
            Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al comprobar existencia de empleado. " + ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
        // Retorna el ResultSet con los resultados de la consulta (o null si ocurre un error).
        return resultSet;
    }
}
