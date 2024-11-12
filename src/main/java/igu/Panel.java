package igu;

import java.sql.Connection;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logica.LogicaCargo;
import logica.LogicaEmpleado;
import persistencia.DatabaseConnection;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author Eduar
 */
public class Panel extends javax.swing.JFrame {

// Crea una nueva instancia de la clase DatabaseConnection para establecer una conexión con la base de datos.
    DatabaseConnection db = new DatabaseConnection();

// Obtiene la conexión a la base de datos llamando al método `getConnection` de la clase DatabaseConnection.
    Connection connection = db.getConnection();

// Crea una nueva instancia de la clase LogicaCargo.
    LogicaCargo logicaCargo = new LogicaCargo();

// Crea una nueva instancia de la clase LogicaEmpleado.
    LogicaEmpleado logicaEmpleado = new LogicaEmpleado();

// Constructor de la clase Panel, llamado cuando se crea una instancia del panel.
    public Panel() {
        // Inicializa los componentes gráficos del panel (por ejemplo, botones, cuadros de texto, tablas).
        initComponents();

        // Llama al método `cargarDatosComboBoxes` de la clase LogicaEmpleado para obtener los JComboBox con los datos de cargos y departamentos.
        JComboBox<String>[] cargosDepartmentos = logicaEmpleado.cargarDatosComboBoxes(connection);

        // Llena el JComboBox `departamentoBox` con los elementos de los departamentos obtenidos del primer JComboBox.
        for (int i = 0; i < cargosDepartmentos[0].getItemCount(); i++) {
            // Agrega cada elemento del JComboBox de departamentos al `departamentoBox`.
            departamentoBox.addItem(cargosDepartmentos[0].getItemAt(i));
        }

        // Llena el JComboBox `cargoBox` con los elementos de los cargos obtenidos del segundo JComboBox.
        for (int i = 0; i < cargosDepartmentos[1].getItemCount(); i++) {
            // Agrega cada elemento del JComboBox de cargos al `cargoBox`.
            cargoBox.addItem(cargosDepartmentos[1].getItemAt(i));
        }

        // Llama al método `buscarCargos` de la clase LogicaCargo para obtener un modelo de tabla con los cargos.
        DefaultTableModel cargosTable = logicaCargo.buscarCargos(connection);

        // Establece el modelo de datos obtenido en la tabla `tablaCargos`, para que los datos se muestren en ella.
        tablaCargos.setModel(cargosTable);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        nombre = new javax.swing.JLabel();
        apellido = new javax.swing.JLabel();
        cedula = new javax.swing.JLabel();
        municipio = new javax.swing.JLabel();
        municipioBox = new javax.swing.JComboBox<>();
        cargo = new javax.swing.JLabel();
        cargoBox = new javax.swing.JComboBox<>();
        departamento = new javax.swing.JLabel();
        departamentoBox = new javax.swing.JComboBox<>();
        nombreTXT = new javax.swing.JTextField();
        apellidoTXT = new javax.swing.JTextField();
        cedulaTXT = new javax.swing.JTextField();
        guardar = new javax.swing.JButton();
        actualizar = new javax.swing.JButton();
        eliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable();
        buscar = new javax.swing.JLabel();
        buscarUser = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        Cod_Cargo = new javax.swing.JLabel();
        Nombre_cargo = new javax.swing.JLabel();
        Sueldo_cargo = new javax.swing.JLabel();
        Cod_cargoTXT = new javax.swing.JTextField();
        Nombre_cargoTXT = new javax.swing.JTextField();
        Sueldo_cargoTXT = new javax.swing.JTextField();
        guardarCargo = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaCargos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 500));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Nuevo Empleado"));

        nombre.setText("Nombre:");

        apellido.setText("Apellido:");

        cedula.setText("Cedula:");

        municipio.setText("Municipio");

        municipioBox.setMaximumSize(new java.awt.Dimension(72, 22));
        municipioBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                municipioBoxItemStateChanged(evt);
            }
        });
        municipioBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                municipioBoxActionPerformed(evt);
            }
        });

        cargo.setText("Cargo");

        cargoBox.setMaximumSize(new java.awt.Dimension(72, 22));
        cargoBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargoBoxActionPerformed(evt);
            }
        });

        departamento.setText("Departamento");

        departamentoBox.setMaximumSize(new java.awt.Dimension(72, 22));
        departamentoBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                departamentoBoxActionPerformed(evt);
            }
        });

        nombreTXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreTXTActionPerformed(evt);
            }
        });

        cedulaTXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cedulaTXTActionPerformed(evt);
            }
        });

        guardar.setText("Guardar");
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });

        actualizar.setText("Actualizar");
        actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarActionPerformed(evt);
            }
        });

        eliminar.setText("Eliminar");
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(nombre)
                                .addGap(18, 18, 18)
                                .addComponent(nombreTXT, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(apellido)
                                    .addComponent(cedula))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cedulaTXT, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(apellidoTXT, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(departamento)
                            .addComponent(municipio)
                            .addComponent(cargo))
                        .addGap(75, 75, 75)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cargoBox, 0, 150, Short.MAX_VALUE)
                            .addComponent(departamentoBox, 0, 150, Short.MAX_VALUE)
                            .addComponent(municipioBox, 0, 150, Short.MAX_VALUE))
                        .addContainerGap(43, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(guardar)
                        .addGap(18, 18, 18)
                        .addComponent(actualizar)
                        .addGap(18, 18, 18)
                        .addComponent(eliminar)
                        .addGap(184, 184, 184))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombre)
                    .addComponent(nombreTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cargo)
                    .addComponent(cargoBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(apellido)
                    .addComponent(apellidoTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(departamento)
                    .addComponent(departamentoBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cedula)
                    .addComponent(cedulaTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(municipio)
                    .addComponent(municipioBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(guardar)
                    .addComponent(actualizar)
                    .addComponent(eliminar))
                .addContainerGap())
        );

        tablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cedula", "Nombre", "Apellido", "Cargo", "Sueldo", "Municipio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaUsuarios);
        if (tablaUsuarios.getColumnModel().getColumnCount() > 0) {
            tablaUsuarios.getColumnModel().getColumn(0).setResizable(false);
            tablaUsuarios.getColumnModel().getColumn(1).setResizable(false);
            tablaUsuarios.getColumnModel().getColumn(2).setResizable(false);
            tablaUsuarios.getColumnModel().getColumn(3).setResizable(false);
            tablaUsuarios.getColumnModel().getColumn(4).setResizable(false);
            tablaUsuarios.getColumnModel().getColumn(5).setResizable(false);
        }

        buscar.setText("Busca por cedula:");

        buscarUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(buscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buscarUser, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buscar)
                    .addComponent(buscarUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Empleados", jPanel2);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Nuevo Cargo"));

        Cod_Cargo.setText("Codigo:");

        Nombre_cargo.setText("Nombre:");

        Sueldo_cargo.setText("Sueldo");

        Cod_cargoTXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cod_cargoTXTActionPerformed(evt);
            }
        });

        Sueldo_cargoTXT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sueldo_cargoTXTActionPerformed(evt);
            }
        });

        guardarCargo.setText("Guardar");
        guardarCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarCargoActionPerformed(evt);
            }
        });

        jButton1.setText("Actualizar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Eliminar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Nombre_cargo)
                    .addComponent(Sueldo_cargo)
                    .addComponent(Cod_Cargo))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Cod_cargoTXT)
                    .addComponent(Sueldo_cargoTXT, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                    .addComponent(Nombre_cargoTXT, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 197, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(guardarCargo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(105, 105, 105))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Cod_Cargo)
                            .addComponent(Cod_cargoTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Nombre_cargo)
                            .addComponent(Nombre_cargoTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Sueldo_cargo)
                            .addComponent(Sueldo_cargoTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(guardarCargo)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        tablaCargos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre", "Sueldo"
            }
        ));
        jScrollPane2.setViewportView(tablaCargos);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 668, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(79, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cargos", jPanel5);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void guardarCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarCargoActionPerformed

// Verifica si alguno de los campos de texto de entrada está vacío: `Cod_cargoTXT`, `Nombre_cargoTXT` o `Sueldo_cargoTXT`.
        if (Cod_cargoTXT.getText().trim().isEmpty()
                || Nombre_cargoTXT.getText().trim().isEmpty()
                || Sueldo_cargoTXT.getText().trim().isEmpty()) {

            // Si algún campo está vacío, muestra un mensaje de advertencia indicando que todos los campos deben ser completados.
            JOptionPane.showMessageDialog(null, "Por favor llene todos los campos", "Advertencia", JOptionPane.WARNING_MESSAGE);

        } else {
            // Si todos los campos están llenos, obtiene los valores ingresados, eliminando espacios en blanco al inicio y final.
            String codigo = Cod_cargoTXT.getText().trim();
            String nombreCargo = Nombre_cargoTXT.getText().trim();
            String salario = Sueldo_cargoTXT.getText().trim();

            // Llama al método `guardarCargo` de la clase `logicaCargo` para guardar el nuevo cargo en la base de datos.
            logicaCargo.guardarCargo(connection, codigo, nombreCargo, salario);

            // Actualiza el modelo de la tabla de cargos llamando al método `buscarCargos` para obtener los datos actualizados.
            DefaultTableModel cargosTable = logicaCargo.buscarCargos(connection);
            tablaCargos.setModel(cargosTable);  // Asigna el modelo actualizado a la tabla `tablaCargos`.

            // Llama al método `cargarDatosComboBoxes` para actualizar los JComboBox con los datos de cargos y departamentos.
            JComboBox<String>[] comboBoxes = logicaEmpleado.cargarDatosComboBoxes(connection);

            // Limpia los elementos actuales en el JComboBox `cargoBox` para evitar duplicados.
            cargoBox.removeAllItems();

            // Rellena el JComboBox `cargoBox` con los cargos obtenidos en el arreglo `cargosDepartmentos`.
            for (int i = 0; i < comboBoxes[1].getItemCount(); i++) {
                cargoBox.addItem(comboBoxes[1].getItemAt(i));
            }
        }

    }//GEN-LAST:event_guardarCargoActionPerformed

    private void Sueldo_cargoTXTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sueldo_cargoTXTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Sueldo_cargoTXTActionPerformed

    private void Cod_cargoTXTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cod_cargoTXTActionPerformed

    }//GEN-LAST:event_Cod_cargoTXTActionPerformed

    private void buscarUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarUserActionPerformed

// Llama al método `buscarUsuarios` de la clase `logicaEmpleado`, pasando la conexión a la base de datos y el texto ingresado en `buscarUser`.
// El método `buscarUsuarios` devuelve un `DefaultTableModel` con los resultados de la búsqueda de usuarios.
        DefaultTableModel rsConsulta = logicaEmpleado.buscarUsuarios(connection, buscarUser.getText().trim());

// Establece el `DefaultTableModel` devuelto por la consulta como el modelo de la tabla `tablaUsuarios`.
// Esto actualiza la tabla en la interfaz para mostrar los datos de usuario encontrados.
        tablaUsuarios.setModel(rsConsulta);

    }//GEN-LAST:event_buscarUserActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed

        // Verifica si el campo de texto `cedulaTXT` no está vacío después de eliminar los espacios en blanco.
// Si contiene un valor, procede a eliminar el empleado con la cédula especificada.
        if (!cedulaTXT.getText().trim().isEmpty()) {

            // Llama al método `eliminarEmpleado` de la clase `logicaEmpleado`, 
            // pasando la conexión y el valor de la cédula ingresada en `cedulaTXT`.
            // Este método se encarga de eliminar al empleado de la base de datos.
            logicaEmpleado.eliminarEmpleado(connection, cedulaTXT.getText().trim());

        } else {
            // Muestra un mensaje de advertencia al usuario si el campo `cedulaTXT` está vacío.
            JOptionPane.showMessageDialog(null, "Ingrese una cedula de empleado para eliminar",
                    "Advertencia", JOptionPane.INFORMATION_MESSAGE);
        }


    }//GEN-LAST:event_eliminarActionPerformed

    private void actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarActionPerformed

// Verifica si alguno de los campos de texto (nombreTXT, apellidoTXT, cedulaTXT) está vacío,
// o si alguna de las listas desplegables (cargoBox, departamentoBox, municipioBox) no tiene un valor seleccionado.
// Si alguna de estas condiciones se cumple, muestra un mensaje de advertencia.
        if (nombreTXT.getText().trim().isEmpty()
                || apellidoTXT.getText().trim().isEmpty()
                || cedulaTXT.getText().trim().isEmpty()
                || cargoBox.getSelectedItem() == null
                || departamentoBox.getSelectedItem() == null
                || municipioBox.getSelectedItem() == null) {

            // Muestra un mensaje de advertencia para indicar que todos los campos deben estar completos.
            JOptionPane.showMessageDialog(null, "Por favor llene todos los campos",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);

        } else {
            // Obtiene los códigos de municipio y departamento asociados con el municipio y departamento seleccionados
            // llamando al método `obtenerCodMunicipio`, que devuelve un arreglo de códigos.
            String[] codigosMunicipios = logicaEmpleado.obtenerCodMunicipio(connection,
                    municipioBox.getSelectedItem().toString(),
                    departamentoBox.getSelectedItem().toString());

            // Obtiene el código del cargo seleccionado en `cargoBox` llamando al método `obtenerCodCargo`.
            String codCargo = logicaEmpleado.obtenerCodCargo(connection, cargoBox.getSelectedItem().toString());

            // Llama al método `actualizar` de `logicaEmpleado` para actualizar los datos del empleado.
            // Pasa la conexión, la cédula, el nombre, el apellido y los códigos obtenidos de cargo, departamento y municipio.
            logicaEmpleado.actualizar(connection,
                    cedulaTXT.getText().trim(),
                    nombreTXT.getText().trim(),
                    apellidoTXT.getText(),
                    codCargo,
                    codigosMunicipios[1],
                    codigosMunicipios[0]);
        }


    }//GEN-LAST:event_actualizarActionPerformed

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
// Verifica si alguno de los campos de texto (nombreTXT, apellidoTXT, cedulaTXT) está vacío,
// o si alguna de las listas desplegables (cargoBox, departamentoBox, municipioBox) no tiene un valor seleccionado.
// Si alguna de estas condiciones se cumple, muestra un mensaje de advertencia.
        if (nombreTXT.getText().trim().isEmpty()
                || apellidoTXT.getText().trim().isEmpty()
                || cedulaTXT.getText().trim().isEmpty()
                || cargoBox.getSelectedItem() == null
                || departamentoBox.getSelectedItem() == null
                || municipioBox.getSelectedItem() == null) {

            // Muestra un mensaje de advertencia para indicar que todos los campos deben estar completos.
            JOptionPane.showMessageDialog(null, "Por favor llene todos los campos",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);

        } else {
            // Obtiene los códigos de municipio y departamento asociados con el municipio y departamento seleccionados
            // llamando al método `obtenerCodMunicipio`, que devuelve un arreglo de códigos.
            String[] codigosMunicipios = logicaEmpleado.obtenerCodMunicipio(connection,
                    municipioBox.getSelectedItem().toString(),
                    departamentoBox.getSelectedItem().toString());

            // Obtiene el código del cargo seleccionado en `cargoBox` llamando al método `obtenerCodCargo`.
            String codCargo = logicaEmpleado.obtenerCodCargo(connection, cargoBox.getSelectedItem().toString());

            // Llama al método `guardarEmpleado` de `logicaEmpleado` para guardar un nuevo empleado en la base de datos.
            // Pasa la conexión, la cédula, el nombre, el apellido y los códigos obtenidos de cargo, departamento y municipio.
            logicaEmpleado.guardarEmpleado(connection,
                    cedulaTXT.getText().trim(),
                    nombreTXT.getText().trim(),
                    apellidoTXT.getText().trim(),
                    codCargo,
                    codigosMunicipios[1],
                    codigosMunicipios[0]);
        }

    }//GEN-LAST:event_guardarActionPerformed

    private void cedulaTXTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cedulaTXTActionPerformed

    }//GEN-LAST:event_cedulaTXTActionPerformed

    private void nombreTXTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreTXTActionPerformed

    }//GEN-LAST:event_nombreTXTActionPerformed

    private void departamentoBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_departamentoBoxActionPerformed
// Carga una lista de municipios para el departamento seleccionado llamando al método `cargarMunicipios` de `logicaEmpleado`.
// Se pasa la conexión a la base de datos y el departamento seleccionado como parámetros.
        JComboBox boxMunicipio = logicaEmpleado.cargarMunicipios(connection,
                departamentoBox.getSelectedItem().toString());

// Elimina todos los elementos actualmente en `municipioBox` para evitar duplicados y preparar la lista para los nuevos valores.
        municipioBox.removeAllItems();

// Recorre los elementos en `boxMunicipio`, que contiene los municipios cargados desde la base de datos.
// Agrega cada municipio a `municipioBox`.
        for (int i = 0; i < boxMunicipio.getItemCount(); i++) {
            municipioBox.addItem((String) boxMunicipio.getItemAt(i));
        }

    }//GEN-LAST:event_departamentoBoxActionPerformed

    private void cargoBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargoBoxActionPerformed

    }//GEN-LAST:event_cargoBoxActionPerformed

    private void municipioBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_municipioBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_municipioBoxActionPerformed

    private void municipioBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_municipioBoxItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_municipioBoxItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// Verifica si los campos de texto Cod_cargoTXT, Nombre_cargoTXT o Sueldo_cargoTXT están vacíos.
// Si alguno de ellos está vacío, muestra un mensaje de advertencia solicitando que todos los campos sean llenados.
        if (Cod_cargoTXT.getText().trim().isEmpty()
                || Nombre_cargoTXT.getText().trim().isEmpty()
                || Sueldo_cargoTXT.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Por favor llene todos los campos", "Advertensia", JOptionPane.WARNING_MESSAGE);
        } else {
            // Si todos los campos están llenos, llama al método actualizarCargo de logicaCargo para actualizar el cargo
            // usando los valores ingresados en los campos de texto.
            logicaCargo.actualizarCargo(connection,
                    Cod_cargoTXT.getText().trim(),
                    Nombre_cargoTXT.getText().trim(),
                    Sueldo_cargoTXT.getText().trim());

            // Actualiza la tabla de cargos después de la modificación.
            // Llama al método buscarCargos de logicaCargo para obtener el modelo de la tabla con los cargos actualizados.
            DefaultTableModel cargosTable = logicaCargo.buscarCargos(connection);
            tablaCargos.setModel(cargosTable);

            // Actualiza el contenido de cargoBox con los cargos disponibles tras la modificación.
            // Llama al método cargarDatosComboBoxes de logicaEmpleado para obtener los combos de departamentos y cargos.
            JComboBox<String>[] cargosDepartmentos = logicaEmpleado.cargarDatosComboBoxes(connection);
            cargoBox.removeAllItems(); // Limpia todos los elementos actuales de cargoBox.

            // Itera a través de los cargos obtenidos y los añade a cargoBox.
            for (int i = 0; i < cargosDepartmentos[1].getItemCount(); i++) {
                cargoBox.addItem(cargosDepartmentos[1].getItemAt(i));
            }
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
// Verifica si el campo de texto Cod_cargoTXT está vacío.
// Si está vacío, muestra un mensaje de advertencia indicando que se debe ingresar al menos el código de un cargo.
        if (Cod_cargoTXT.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor ingrese al menos el codigo de un cargo.", "Advertensia", JOptionPane.WARNING_MESSAGE);
        } else {
            // Si el campo de texto no está vacío, llama al método eliminarCargo de logicaCargo
            // para eliminar el cargo que corresponde al código ingresado.
            logicaCargo.eliminarCargo(connection, Cod_cargoTXT.getText().trim());

            // Actualiza la tabla de cargos después de la eliminación.
            // Llama al método buscarCargos de logicaCargo para obtener el modelo de la tabla con los cargos actualizados.
            DefaultTableModel cargosTable = logicaCargo.buscarCargos(connection);
            tablaCargos.setModel(cargosTable);

            // Actualiza el contenido de cargoBox con los cargos disponibles tras la eliminación.
            // Llama al método cargarDatosComboBoxes de logicaEmpleado para obtener los combos de departamentos y cargos.
            JComboBox<String>[] cargosDepartmentos = logicaEmpleado.cargarDatosComboBoxes(connection);
            cargoBox.removeAllItems(); // Limpia todos los elementos actuales de cargoBox.

            // Itera a través de los cargos obtenidos y los añade a cargoBox.
            for (int i = 0; i < cargosDepartmentos[1].getItemCount(); i++) {
                cargoBox.addItem(cargosDepartmentos[1].getItemAt(i));
            }
        }

    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Cod_Cargo;
    private javax.swing.JTextField Cod_cargoTXT;
    private javax.swing.JLabel Nombre_cargo;
    private javax.swing.JTextField Nombre_cargoTXT;
    private javax.swing.JLabel Sueldo_cargo;
    private javax.swing.JTextField Sueldo_cargoTXT;
    private javax.swing.JButton actualizar;
    private javax.swing.JLabel apellido;
    private javax.swing.JTextField apellidoTXT;
    private javax.swing.JLabel buscar;
    private javax.swing.JTextField buscarUser;
    private javax.swing.JLabel cargo;
    private javax.swing.JComboBox<String> cargoBox;
    private javax.swing.JLabel cedula;
    private javax.swing.JTextField cedulaTXT;
    private javax.swing.JLabel departamento;
    private javax.swing.JComboBox<String> departamentoBox;
    private javax.swing.JButton eliminar;
    private javax.swing.JButton guardar;
    private javax.swing.JButton guardarCargo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel municipio;
    private javax.swing.JComboBox<String> municipioBox;
    private javax.swing.JLabel nombre;
    private javax.swing.JTextField nombreTXT;
    private javax.swing.JTable tablaCargos;
    private javax.swing.JTable tablaUsuarios;
    // End of variables declaration//GEN-END:variables

}
