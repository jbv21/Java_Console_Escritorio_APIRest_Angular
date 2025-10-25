package pe.com.galaxy.training.escritorio.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BdConexion {

    private static String url = "";
    private static String usuario = "";
    private static String clave = "";

    public static Connection getConexion() throws SQLException {
        try {
            Connection cn = DriverManager.getConnection(url, usuario, clave);
            return cn;
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    public static Connection getConexion(String usuario, String clave ) throws SQLException {
        BdConexion.usuario = usuario;
        BdConexion.clave = clave;
        return getConexion();
    }

    public static Connection getConexion(String url, String usuario, String clave) throws SQLException{
        BdConexion.url = url;
        BdConexion.usuario = usuario;
        BdConexion.clave = clave;

        return BdConexion.getConexion();
    }

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        BdConexion.url = url;
    }

    public static String getUsuario() {
        return usuario;
    }

    public static void setUsuario(String usuario) {
        BdConexion.usuario = usuario;
    }

    public static String getClave() {
        return clave;
    }

    public static void setClave(String clave) {
        BdConexion.clave = clave;
    }
}
