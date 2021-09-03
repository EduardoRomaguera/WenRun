package eduardo.romaguera.tl;

import eduardo.romaguera.bl.entities.usuario.Usuario;
import eduardo.romaguera.bl.logic.UsuarioGestor;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.io.*;

import java.io.IOException;

public class IndexController {
    private UsuarioGestor gestorUsuario = new UsuarioGestor();
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField txtUsuario;
    @FXML
    private TextField txtContrasena;
    @FXML
    private Rectangle sqrItemID;

    public void registrarUsuario(MouseEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/registrarAtleta.fxml"));
        root = loader.load();
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void login() throws Exception {
        String correo = txtUsuario.getText();
        String contrasena = txtContrasena.getText();
        String tipoUsuario = "error";
        Usuario usuarioBuscado = new Usuario();
        usuarioBuscado.setEmail(correo);
        usuarioBuscado.setContrasena(contrasena);
        usuarioBuscado = verificarAcceso(usuarioBuscado);
        redireccionar(usuarioBuscado);
        }

    private Usuario verificarAcceso(Usuario usuarioBuscado) throws Exception {
        Usuario Usuario;
        Usuario = gestorUsuario.verificarAcceso(usuarioBuscado);
        return Usuario;
    }

    public void redireccionar(Usuario usuarioBuscado) throws IOException {
        String tipoUsuario = usuarioBuscado.getTipoUsuario();
        if(tipoUsuario.equals("Atleta")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/indexAtleta.fxml"));
            root = loader.load();
            stage = (Stage)sqrItemID.getScene().getWindow();

            IndexAtletaController index = loader.getController();
            index.setUsuarioLogueado(usuarioBuscado);
            index.cargarAvatar();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        if(tipoUsuario.equals("Administrador")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/indexAdministrador.fxml"));
            root = loader.load();
            stage = (Stage)sqrItemID.getScene().getWindow();

            IndexAdministradorController index = loader.getController();
            index.setUsuarioLogueado(usuarioBuscado);

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }
        if(tipoUsuario.equals("Error")) {
//            system.out.println;
        }

    }



    public void salir() {
        System.exit(0);
    }

}


