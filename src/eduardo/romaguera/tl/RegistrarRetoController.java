package eduardo.romaguera.tl;

import eduardo.romaguera.bl.entities.atleta.Atleta;
import eduardo.romaguera.bl.entities.canton.Canton;
import eduardo.romaguera.bl.entities.distrito.Distrito;
import eduardo.romaguera.bl.entities.pais.Pais;
import eduardo.romaguera.bl.entities.provincia.Provincia;
import eduardo.romaguera.bl.entities.reto.Reto;
import eduardo.romaguera.bl.entities.usuario.Usuario;
import eduardo.romaguera.bl.logic.*;
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
import java.time.Period;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RegistrarRetoController implements Initializable {
    private RetoGestor gestorReto = new RetoGestor();
    private PaisGestor gestorPais = new PaisGestor();



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
    private TextField txtCodigo;
    @FXML
    private TextField txtKilometros;
    @FXML
    private ComboBox<String> txtPais;
    @FXML
    private TextField txtMedalla;
    @FXML
    private TextField txtImagen;
    @FXML
    private TextArea txtDescripcion;

    @FXML
    private Label txtNombreAdministrador;

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

    public void setUsuarioLogueado(Usuario usuario) {
        this.usuarioLogueado = usuario;
        txtNombreAdministrador.setText(usuarioLogueado.getNombre()+" "+usuarioLogueado.getApellidos());
    }

    public void registrarReto() throws Exception {
        try {
            String Nombre = txtNombre.getText();
            String Codigo = txtCodigo.getText();
            double Kilometros = Double.parseDouble(txtKilometros.getText());
            Pais Pais = new Pais((String) txtPais.getValue());
            String Medalla = txtMedalla.getText();
            String Fotografia = (String) txtImagen.getText();
            String Descripcion = txtDescripcion.getText();

            Reto nuevoReto = new Reto(Nombre, Codigo, Kilometros, Pais, Medalla, Fotografia, Descripcion);
            String Registro;
            Registro = gestorReto.registrarReto(nuevoReto);
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/indexAdministrador.fxml"));
            root = loader.load();
            stage = (Stage)sqrItemID.getScene().getWindow();
            IndexAdministradorController index = loader.getController();
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


