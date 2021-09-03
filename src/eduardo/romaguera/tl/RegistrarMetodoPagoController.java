package eduardo.romaguera.tl;

import eduardo.romaguera.bl.entities.metodoPago.MetodoPago;
import eduardo.romaguera.bl.entities.pais.Pais;
import eduardo.romaguera.bl.entities.reto.Reto;
import eduardo.romaguera.bl.entities.usuario.Usuario;
import eduardo.romaguera.bl.logic.MetodoPagoGestor;
import eduardo.romaguera.bl.logic.PaisGestor;
import eduardo.romaguera.bl.logic.RetoGestor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RegistrarMetodoPagoController implements Initializable {
    private MetodoPagoGestor gestorMetodoPago = new MetodoPagoGestor();



    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Usuario usuarioLogueado;
    @FXML
    private Rectangle sqrItemID;
    // Obtener el scene de un elemento
    // stage = (Stage)sqrItemID.getScene().getWindow();

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtNúmero;
    @FXML
    private TextField txtCVV;
    @FXML
    private ComboBox<String> txtProveedor;
    @FXML
    private DatePicker txtFecha;

    @FXML
    private Label txtNombreUsuario;

    ObservableList<String> Proveedor;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Proveedor = FXCollections.observableArrayList();
        Proveedor.addAll("Visa", "MasterCard", "American Express");
        txtProveedor.setItems(Proveedor);
    }

    public void setUsuarioLogueado(Usuario usuario) {
        this.usuarioLogueado = usuario;
        txtNombreUsuario.setText(usuarioLogueado.getNombre()+" "+usuarioLogueado.getApellidos());

    }

    public void registrarMetodoPago() throws Exception {
        try {
            String Nombre = txtNombre.getText();
            double Numero = Double.parseDouble(txtNúmero.getText());
            int cvv = Integer.parseInt(txtCVV.getText());
            String Proveedor = txtProveedor.getValue();
            LocalDate Fecha = txtFecha.getValue();
            String Email = usuarioLogueado.getEmail();

            MetodoPago nuevo = new MetodoPago(Nombre, Numero, Proveedor, Fecha, cvv, Email);
            String Registro;
            Registro = gestorMetodoPago.registrarMetodoPago(nuevo);
            if (Registro.equals("Good")) {
                redireccionar("menu");
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void menu() throws IOException {
        redireccionar("menu");
    }

    public void redireccionar(String menu) throws IOException {
        if(menu.equals("menu")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/indexAtleta.fxml"));
            root = loader.load();
            stage = (Stage)sqrItemID.getScene().getWindow();
            IndexAtletaController index = loader.getController();
            index.setUsuarioLogueado(usuarioLogueado);
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void salir() {
        System.exit(0);
    }
}


