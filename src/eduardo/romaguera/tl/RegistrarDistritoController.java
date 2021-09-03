package eduardo.romaguera.tl;

import eduardo.romaguera.bl.entities.canton.Canton;
import eduardo.romaguera.bl.entities.distrito.Distrito;
import eduardo.romaguera.bl.entities.usuario.Usuario;
import eduardo.romaguera.bl.logic.CantonGestor;
import eduardo.romaguera.bl.logic.DistritoGestor;
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

public class RegistrarDistritoController implements Initializable {
    private DistritoGestor gestorDistrito = new DistritoGestor();
    private CantonGestor gestorCanton = new CantonGestor();

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
    private ComboBox<String> txtCanton;

    ObservableList<String> cantonesLista;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Canton> listaCantones = new ArrayList<>();
        ArrayList<String> listaCantones2 = new ArrayList<>();
        try {
            listaCantones = gestorCanton.listarCantones();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Canton canton : listaCantones) {
            if (true) {
                listaCantones2.add(canton.getNombre());
            }
        }
        cantonesLista = FXCollections.observableArrayList();
        cantonesLista.addAll(listaCantones2);
        txtCanton.setItems(cantonesLista);
    }

    public void setUsuarioLogueado(Usuario usuario) {
        this.usuarioLogueado = usuario;
        txtNombreAdministrador.setText(usuarioLogueado.getNombre()+" "+usuarioLogueado.getApellidos());
    }

    public void registrarDistrito() throws Exception {
        try {
            String Nombre = txtNombre.getText();
            String NombreCanton = txtCanton.getValue();
            Distrito nuevo = new Distrito(Nombre, NombreCanton);
            String Registro;
            Registro = gestorDistrito.registrarDistrito(nuevo);
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


