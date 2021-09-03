package eduardo.romaguera.tl;

import eduardo.romaguera.bl.entities.administrador.Administrador;
import eduardo.romaguera.bl.entities.atleta.Atleta;
import eduardo.romaguera.bl.entities.canton.Canton;
import eduardo.romaguera.bl.entities.distrito.Distrito;
import eduardo.romaguera.bl.entities.pais.Pais;
import eduardo.romaguera.bl.entities.provincia.Provincia;
import eduardo.romaguera.bl.logic.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RegistrarAdministradorController implements Initializable {

    private AdministradorGestor gestorAdministrador = new AdministradorGestor();
    private PaisGestor gestorPais = new PaisGestor();

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Rectangle sqrItemID;
    // Obtener el scene de un elemento
    // stage = (Stage)sqrItemID.getScene().getWindow();

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtIdentificacion;
    @FXML
    private ComboBox<String> txtPais;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtContrasena;

    ObservableList<String> paisesLista;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> listaPaises = new ArrayList<>();
        try {
            listaPaises = gestorPais.listarPaises();
        } catch (Exception e) {
            e.printStackTrace();
        }
        paisesLista = FXCollections.observableArrayList();
        paisesLista.addAll(listaPaises);
        txtPais.setItems(paisesLista);
        }

    public void registrarNuevoAdministrador() throws Exception {
        try {
            String Nombre = txtNombre.getText();
            String Apellidos = txtApellidos.getText();
            String Identificacion = txtIdentificacion.getText();
            Pais Pais = new Pais((String) txtPais.getValue());
            String Email = txtEmail.getText();
            String Contrasena = txtContrasena.getText();
            Administrador nuevo = new Administrador(Nombre, Apellidos, Identificacion, Pais, Email, Contrasena);
            String Registro;
            Registro = gestorAdministrador.registrarAdministrador(nuevo);
            if (Registro.equals("Good")) {
                redireccionar("login");
            }
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public void login(MouseEvent actionEvent) throws IOException {
        redireccionar("login");
    }

    public void redireccionar(String menu) throws IOException {
        if(menu.equals("login")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/index.fxml"));
            root = loader.load();
            stage = (Stage)sqrItemID.getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void salir() {
        System.exit(0);
    }

}


