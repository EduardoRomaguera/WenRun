package eduardo.romaguera.tl;

import eduardo.romaguera.bl.entities.email.Email;
import eduardo.romaguera.bl.entities.usuario.Usuario;
import eduardo.romaguera.bl.logic.EmailGestor;
import eduardo.romaguera.bl.logic.UsuarioGestor;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class IndexAdministradorController implements Initializable {
    private UsuarioGestor gestorUsuario = new UsuarioGestor();
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Rectangle sqrItemID;
    private Usuario usuarioLogueado;
    @FXML
    private Label txtNombreAdministrador;
    @FXML
    private Label txtNombreAdministrador2;
    @FXML
    private  TextField txtmail;
    @FXML
    private Button send;

    private EmailGestor cartero = new EmailGestor();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setUsuarioLogueado(Usuario usuario) { // Setting the client-object in ClientViewController
        this.usuarioLogueado = usuario;
        txtNombreAdministrador.setText(usuarioLogueado.getNombre());
        txtNombreAdministrador2.setText(usuarioLogueado.getNombre()+" "+usuarioLogueado.getApellidos());

    }

    public void registrarReto(MouseEvent actionEvent) throws IOException {
        redireccionar("reto");
    }

    public void listarReto(MouseEvent mouseEvent) throws IOException {
        redireccionar("listarReto");
    }

    public void registrarHito(MouseEvent actionEvent) throws IOException {
        redireccionar("hito");
    }

    public void listarHitos(MouseEvent mouseEvent) throws IOException {
        redireccionar("listarHitos");
    }

    public void registrarActividadDeportiva(MouseEvent actionEvent) throws IOException {
        redireccionar("actividadDeportiva");
    }

    public void listarActividadDeportiva(MouseEvent mouseEvent) throws IOException {
        redireccionar("listarActividadDeportiva");
    }

    public void registrarPais(MouseEvent actionEvent) throws IOException {
        redireccionar("pais");
    }

    public void listarPais(MouseEvent mouseEvent) throws IOException {
        redireccionar("listarPais");
    }

    public void registrarProvincia(MouseEvent mouseEvent) throws IOException {
        redireccionar("provincia");
    }

    public void listarProvincia(MouseEvent mouseEvent) throws IOException {
        redireccionar("listarProvincia");
    }

    public void registrarCanton(MouseEvent actionEvent) throws IOException {
        redireccionar("canton");
    }

    public void listarCanton(MouseEvent mouseEvent) throws IOException {
        redireccionar("listarCanton");
    }

    public void registrarDistrito(MouseEvent mouseEvent) throws IOException {
        redireccionar("distrito");
    }

    public void listarDistrito(MouseEvent mouseEvent) throws IOException {
        redireccionar("listarDistrito");
    }

    public void cerrarSesion(MouseEvent actionEvent) throws IOException {
        redireccionar("login");
    }

    public void send() throws Exception {
        String direccion = txtmail.getText();
        Email email = new Email(direccion, "Bienvenido a WeRun", "" +
                "Bienvenido a WeRun.  "+
                "Ahora eres parte de la plataforma para retos fit más grande del mundo.  "+
                "¡Ejercitarte nunca fue tan fácil!  "+
                "");
        cartero.sendEmail(email);
    }

    public void redireccionar(String menu) throws IOException {
        if(menu.equals("reto")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/registrarReto.fxml"));
            root = loader.load();
            stage = (Stage)sqrItemID.getScene().getWindow();
            RegistrarRetoController index = loader.getController();
            index.setUsuarioLogueado(usuarioLogueado);
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        if(menu.equals("hito")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/registrarHito.fxml"));
            root = loader.load();
            stage = (Stage)sqrItemID.getScene().getWindow();
            RegistrarHitoController index = loader.getController();
            index.setUsuarioLogueado(usuarioLogueado);
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        if(menu.equals("login")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/index.fxml"));
            root = loader.load();
            stage = (Stage)sqrItemID.getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        if(menu.equals("actividadDeportiva")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/registrarActividadDeportiva.fxml"));
            root = loader.load();
            stage = (Stage)sqrItemID.getScene().getWindow();
            RegistrarActividadDeportivaController index = loader.getController();
            index.setUsuarioLogueado(usuarioLogueado);
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        if(menu.equals("pais")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/registrarPais.fxml"));
            root = loader.load();
            stage = (Stage)sqrItemID.getScene().getWindow();
            RegistrarPaisController index = loader.getController();
            index.setUsuarioLogueado(usuarioLogueado);
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        if(menu.equals("provincia")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/registrarProvincia.fxml"));
            root = loader.load();
            stage = (Stage)sqrItemID.getScene().getWindow();
            RegistrarProvinciaController index = loader.getController();
            index.setUsuarioLogueado(usuarioLogueado);
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        if(menu.equals("canton")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/registrarCanton.fxml"));
            root = loader.load();
            stage = (Stage)sqrItemID.getScene().getWindow();
            RegistrarCantonController index = loader.getController();
            index.setUsuarioLogueado(usuarioLogueado);
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        if(menu.equals("distrito")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/registrarDistrito.fxml"));
            root = loader.load();
            stage = (Stage)sqrItemID.getScene().getWindow();
            RegistrarDistritoController index = loader.getController();
            index.setUsuarioLogueado(usuarioLogueado);
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        if(menu.equals("listarPais")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/listarPais.fxml"));
            root = loader.load();
            stage = (Stage)sqrItemID.getScene().getWindow();
            ListarPaisController index = loader.getController();
            index.setUsuarioLogueado(usuarioLogueado);
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        if(menu.equals("listarProvincia")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/listarProvincia.fxml"));
            root = loader.load();
            stage = (Stage)sqrItemID.getScene().getWindow();
            ListarProvinciaController index = loader.getController();
            index.setUsuarioLogueado(usuarioLogueado);
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        if(menu.equals("listarCanton")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/listarCanton.fxml"));
            root = loader.load();
            stage = (Stage)sqrItemID.getScene().getWindow();
            ListarCantonController index = loader.getController();
            index.setUsuarioLogueado(usuarioLogueado);
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        if(menu.equals("listarDistrito")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/listarDistrito.fxml"));
            root = loader.load();
            stage = (Stage)sqrItemID.getScene().getWindow();
            ListarDistritoController index = loader.getController();
            index.setUsuarioLogueado(usuarioLogueado);
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        if(menu.equals("listarReto")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/listarReto.fxml"));
            root = loader.load();
            stage = (Stage)sqrItemID.getScene().getWindow();
            ListarRetoController index = loader.getController();
            index.setUsuarioLogueado(usuarioLogueado);
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        if(menu.equals("listarHitos")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/listarHitos.fxml"));
            root = loader.load();
            stage = (Stage)sqrItemID.getScene().getWindow();
            ListarHitoController index = loader.getController();
            index.setUsuarioLogueado(usuarioLogueado);
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        if(menu.equals("listarActividadDeportiva")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/listarActividadDeportiva.fxml"));
            root = loader.load();
            stage = (Stage)sqrItemID.getScene().getWindow();
            ListarActividadDeportivaController index = loader.getController();
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


