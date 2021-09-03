package eduardo.romaguera.tl;

import eduardo.romaguera.bl.entities.pais.Pais;
import eduardo.romaguera.bl.entities.provincia.Provincia;
import eduardo.romaguera.bl.entities.usuario.Usuario;
import eduardo.romaguera.bl.logic.PaisGestor;
import eduardo.romaguera.bl.logic.ProvinciaGestor;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistrarProvinciaController implements Initializable {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setUsuarioLogueado(Usuario usuario) {
        this.usuarioLogueado = usuario;
        txtNombreAdministrador.setText(usuarioLogueado.getNombre()+" "+usuarioLogueado.getApellidos());
    }

    public void registrarProvincia() throws Exception {
        try {
            String Nombre = txtNombre.getText();
            Provincia nuevaProvincia = new Provincia(Nombre);
            String Registro;
            Registro = gestorProvincia.registrarProvincia(nuevaProvincia);
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


