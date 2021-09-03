package eduardo.romaguera.tl;

import eduardo.romaguera.bl.entities.canton.Canton;
import eduardo.romaguera.bl.entities.pais.Pais;
import eduardo.romaguera.bl.entities.usuario.Usuario;
import eduardo.romaguera.bl.logic.CantonGestor;
import eduardo.romaguera.bl.logic.PaisGestor;
import eduardo.romaguera.bl.logic.ProvinciaGestor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RegistrarCantonController implements Initializable {
    private CantonGestor gestorCanton = new CantonGestor();
    private ProvinciaGestor gestorProvincia = new ProvinciaGestor();
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
    private Label txtNombreAdministrador;

    @FXML
    private ComboBox<String> txtProvincia;

    ObservableList<String> provinciasLista;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> listaProvincias = new ArrayList<>();
        try {
            listaProvincias = gestorProvincia.listarProvincias();
        } catch (Exception e) {
            e.printStackTrace();
        }
        provinciasLista = FXCollections.observableArrayList();
        provinciasLista.addAll(listaProvincias);
        txtProvincia.setItems(provinciasLista);
    }

    public void setUsuarioLogueado(Usuario usuario) {
        this.usuarioLogueado = usuario;
        txtNombreAdministrador.setText(usuarioLogueado.getNombre()+" "+usuarioLogueado.getApellidos());
    }

    public void registrarCanton() throws Exception {
        try {
            String Nombre = txtNombre.getText();
            String NombreProvincia = txtProvincia.getValue();
            Canton nuevo = new Canton(Nombre, NombreProvincia);
            String Registro;
            Registro = gestorCanton.registrarCanton(nuevo);
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


