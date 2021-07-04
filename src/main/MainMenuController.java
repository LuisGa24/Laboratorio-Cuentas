/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Domain.Account;
import Domain.Client;
import Domain.SavingsAccount;
import Domain.TermAccount;
import Domain.accountMaintenance;
import Domain.clientMaintenance;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author LuisGa
 */
public class MainMenuController implements Initializable {

    @FXML
    private TextField txfClientId;
    @FXML
    private Button btnDisplayClientData;
    @FXML
    private Button btnAddAccount;
    @FXML
    private Button btnClients;
    @FXML
    private Text txt1;
    @FXML
    private Text txt2;
    @FXML
    private Text txt3;
    @FXML
    private Text txt4;
    @FXML
    private TextField txf1;
    @FXML
    private TextField txf2;
    @FXML
    private TextField txf3;
    @FXML
    private TextField txf4;
    @FXML
    private Text txtTitle;
    @FXML
    private Button btnAddClient;

    clientMaintenance cm;
    accountMaintenance am;
    @FXML
    private Text txtClientData;
    @FXML
    private Button btnSearch;
    @FXML
    private Text txtClientAccounts;
    @FXML
    private ListView<Account> listAccounts;
    @FXML
    private RadioButton rdbSavings;
    @FXML
    private RadioButton rdbTerm;
    @FXML
    private Button btnSaveAccount;
    @FXML
    private Text txtClientIdLabel;
    @FXML
    private Text txtInfo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clean();
        cm = new clientMaintenance();
        am = new accountMaintenance();
    }

    @FXML
    private void btnDisplayClientData(ActionEvent event) {
        clean();
        this.txfClientId.setText("");
        this.txtClientIdLabel.setVisible(true);
        this.txfClientId.setVisible(true);
        this.btnSearch.setVisible(true);

    }

    @FXML
    private void btnAddAccount(ActionEvent event) {
        clean();
        this.btnSearch.setVisible(true);
        this.txtClientIdLabel.setVisible(true);
        this.txfClientId.setText("");
        this.txfClientId.setVisible(true);
        this.txtTitle.setText("Añadir cuenta");
        rdbSavings.setVisible(true);
        rdbTerm.setVisible(true);
    }

    @FXML
    private void btnClients(ActionEvent event) {
        clean();
        this.txtTitle.setText("Mantenimiento de cliente");
        this.btnAddClient.setVisible(true);
        this.txf1.setText("");
        this.txf2.setText("");
        this.txf3.setText("");
        txt1.setText("Cédula:");
        txf1.setVisible(true);
        txt2.setText("Nombre:");
        txf2.setVisible(true);
        txt3.setText("Teléfono:");
        txf3.setVisible(true);
        txt4.setText("Dirección:");
        txf4.setVisible(true);
    }

    public void clean() {
        txt1.setText("");
        txf1.setVisible(false);
        txt2.setText("");
        txf2.setVisible(false);
        txt3.setText("");
        txf3.setVisible(false);
        txt4.setText("");
        txf4.setVisible(false);
        this.txfClientId.setVisible(false);

        this.rdbSavings.setSelected(false);
        this.rdbTerm.setSelected(false);

        this.txtClientAccounts.setText("");
        this.txtClientData.setText("");
        this.txtTitle.setText("");
        this.txtClientIdLabel.setVisible(false);

        this.btnAddClient.setVisible(false);
        this.btnSaveAccount.setVisible(false);
        this.btnSearch.setVisible(false);

        this.rdbSavings.setVisible(false);
        this.rdbTerm.setVisible(false);

        this.listAccounts.setVisible(false);
        this.listAccounts.getItems().clear();

        this.txtInfo.setText("");
    }

    @FXML
    private void btnAddClient(ActionEvent event) {
        if (txf1.getText().equals("") || txf2.getText().equals("") || txf3.getText().equals("") || txf4.getText().equals("")) {
            alertCreator("Datos incompletos", "Complete los espacios solicitados", "error");
        } else {
            if (cm.addClient(new Client(txf1.getText(), txf2.getText(), txf3.getText(), txf4.getText()))) {
                alertCreator("Cliente añadido", "El cliente ha sido añadido exitosamente", "confirmation");
                this.txf1.setText("");
                this.txf2.setText("");
                this.txf3.setText("");
                this.txf4.setText("");
            } else {
                alertCreator("Cliente no añadido", "El cliente no ha podido ser añadido", "error");
            }
        }

    }

    @FXML
    private void btnSearch(ActionEvent event) {
        this.txtInfo.setText("");
        ClienteUDP.Cliente(txfClientId.getText());
        
//        if (cm.existClient(txfClientId.getText())) {
//
//            txtClientData.setText(cm.getClient(txfClientId.getText()).toString());
//
//            LinkedList<Account> list = am.getAccountsByClientId(txfClientId.getText());
//
//            if (!list.isEmpty()) {
//
//                ObservableList<Account> oL = FXCollections.observableArrayList();
//
//                for (int i = 0; i < list.size(); i++) {
//                    oL.add(list.get(i));
//                }
//
//                listAccounts.setItems(oL);
//                if (!this.txtTitle.getText().equals("Añadir cuenta")) {
//                    this.listAccounts.setVisible(true);
//                    txtClientAccounts.setText("Cuentas del cliente");
//                }
//            } else {
//                if (!this.txtTitle.getText().equals("Añadir cuenta")) {
//                    this.txtClientAccounts.setText("El cliente no tiene cuentas registradas.");
//                }
//            }
//
//        } else {
//
//            txtClientData.setText("El cliente buscado no existe.");
//
//        }

    }

    @FXML
    private void rdbSavings(ActionEvent event) {
        this.listAccounts.setVisible(false);
        txtClientAccounts.setText("");
        this.txf4.setVisible(false);
        this.txt4.setText("");
        if (this.txtClientData.getText().equals("") || this.txtClientData.getText().equals("El cliente buscado no existe.")) {
            rdbSavings.setSelected(false);
            rdbTerm.setSelected(false);
            this.txtInfo.setText("Debe buscar un cliente válido");
        } else {
            this.rdbSavings.setVisible(true);
            this.rdbTerm.setVisible(true);
            this.btnSaveAccount.setVisible(true);
            txt1.setText("Tipo de moneda: (D|C|E)");
            txf1.setVisible(true);
            txt2.setText("Depósito inicial: ");
            txf2.setVisible(true);
            txt3.setText("Tasa de interés: ");
            txf3.setVisible(true);
            rdbSavings.setSelected(true);
            rdbTerm.setSelected(false);
        }
    }

    @FXML
    private void rdbTerm(ActionEvent event) {
        this.listAccounts.setVisible(false);
        txtClientAccounts.setText("");
        if (this.txtClientData.getText().equals("") || this.txtClientData.getText().equals("El cliente buscado no existe.")) {
            rdbSavings.setSelected(false);
            rdbTerm.setSelected(false);
            this.txtInfo.setText("Debe buscar un cliente válido");
        } else {
            this.rdbSavings.setVisible(true);
            this.rdbTerm.setVisible(true);
            this.btnSaveAccount.setVisible(true);
            txt1.setText("Tipo de moneda: (D|C|E)");
            txf1.setVisible(true);
            txt2.setText("Depósito inicial: ");
            txf2.setVisible(true);
            txt3.setText("Tasa de interés: ");
            txf3.setVisible(true);
            txt4.setText("Plazo : (Meses)");
            txf4.setVisible(true);
            rdbSavings.setSelected(false);
            rdbTerm.setSelected(true);
        }
    }

    @FXML
    private void btnSaveAccount(ActionEvent event) {
        try {
            if (rdbSavings.isSelected()) {
                java.util.Date d = java.sql.Date.valueOf(java.time.LocalDate.now());
                if (txf1.getText().equals("") || txf2.getText().equals("") || txf3.getText().equals("")) {
                    alertCreator("Datos incompletos", "Complete los datos solicitados", "error");
                } else {
                    if (am.addAccount(new SavingsAccount(txf1.getText().charAt(0), d, Float.parseFloat(txf2.getText()), txfClientId.getText(), Float.parseFloat(txf3.getText())))) {
                        alertCreator("Cuenta añadida", "La cuenta ha sido añadida al cliente seleccionado ", "confirmation");
                        btnAddAccount(event);
                    } else {
                        alertCreator("Cuenta no añadida", "La cuenta no ha sido añadida al cliente seleccionado ", "error");
                    }
                }
            } else {
                java.util.Date d = java.sql.Date.valueOf(java.time.LocalDate.now());
                if (txf1.getText().equals("") || txf2.getText().equals("") || txf3.getText().equals("") || txf4.getText().equals("")) {
                    alertCreator("Datos incompletos", "Complete los datos solicitados", "error");
                } else {
                    if (am.addAccount(new TermAccount(txf1.getText().charAt(0), d, txfClientId.getText(), Float.parseFloat(txf2.getText()), Float.parseFloat(txf3.getText()), Integer.parseInt(txf4.getText())))) {
                        alertCreator("Cuenta añadida", "La cuenta ha sido añadida al cliente seleccionado ", "confirmation");
                        btnAddAccount(event);
                    } else {
                        alertCreator("Cuenta no añadida", "La cuenta no ha sido añadida al cliente seleccionado ", "error");
                    }
                }
            }
        } catch (NumberFormatException e) {
            alertCreator("Datos incorrectos", "Revise la información proporcionada", "error");
        }

    }

    public void alertCreator(String title, String content, String type) {
        if (type.equals("confirmation")) {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle(type);
            alerta.setContentText(content);
            alerta.setHeaderText(title);
            alerta.showAndWait();
        }
        if (type.equals("error")) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle(type);
            alerta.setContentText(content);
            alerta.setHeaderText(title);
            alerta.showAndWait();
        }
    }

}
