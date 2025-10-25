package pe.com.galaxy.training.console.persistence.escuela;

import pe.com.galaxy.training.console.bean.escuela.EscuelaConduccion;
import pe.com.galaxy.training.console.persistence.util.PersistenciaException;
import pe.com.galaxy.training.console.util.BdConexion;
import pe.com.galaxy.training.console.util.BdParametros;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EscuelaConduccionBDImpl implements EscuelaConduccionBD {

    @Override
    public List<EscuelaConduccion> listar(EscuelaConduccion obj) throws PersistenciaException {
        String sql = "{CALL sp_listar_escuelas(?,?,?,?)}";
        try {
            Connection cn = BdConexion.getConexion(BdParametros.BD_URL, BdParametros.BD_USUARIO, BdParametros.BD_CLAVE);
            CallableStatement cs = cn.prepareCall(sql);
            cs.setString(1, obj.getIdDepartamento());
            cs.setString(2, obj.getIdProvincia());
            cs.setString(3, obj.getIdDistrito());
            cs.setString(4, obj.getRuc());
            ResultSet rs = cs.executeQuery();
            List<EscuelaConduccion> listObj = this.getMapping(rs);
            return listObj.isEmpty() ? Collections.emptyList() : listObj;
        } catch (Exception e) {
            System.err.println("==> ERROR: listar <====");
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public EscuelaConduccion seleccionar(EscuelaConduccion obj) throws PersistenciaException {
        String sql = "{CALL sp_buscar_escuela(?)}";
        try {
            Connection cn = BdConexion.getConexion(BdParametros.BD_URL, BdParametros.BD_USUARIO, BdParametros.BD_CLAVE);
            CallableStatement cs = cn.prepareCall(sql);
            cs.setInt(1, obj.getId());
            ResultSet rs = cs.executeQuery();
            List<EscuelaConduccion> listObj = this.getMapping(rs);
            return listObj.isEmpty() ? null : listObj.getFirst();
        } catch (Exception e) {
            System.err.println("==> ERROR: seleccionar <====");
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Boolean insertar(EscuelaConduccion obj) throws PersistenciaException {
        String sql = "{CALL sp_insert_escuela(?,?,?,?,?,?,?,?,?,?)}";
        try {
            Connection cn = BdConexion.getConexion(BdParametros.BD_URL, BdParametros.BD_USUARIO, BdParametros.BD_CLAVE);
            CallableStatement cs = cn.prepareCall(sql);

            cs.setString(1, obj.getRuc());
            cs.setString(2, obj.getNombre());
            cs.setString(3, obj.getDireccion());
            cs.setString(4, obj.getEstado());
            cs.setString(5, obj.getTelefono());
            cs.setString(6, obj.getCorreo());
            cs.setString(7, obj.getIdDepartamento());
            cs.setString(8, obj.getIdProvincia());
            cs.setString(9, obj.getIdDistrito());
            cs.registerOutParameter(10, Types.INTEGER);
            cs.executeUpdate();
            int idGenerado = cs.getInt(10);
            obj.setId(idGenerado);
            return true;
        } catch (Exception e) {
            System.err.println("==> ERROR: insertar <====");
            System.err.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean actualizar(EscuelaConduccion obj) throws PersistenciaException {
        String sql = "{CALL sp_update_escuela(?,?,?,?,?,?,?,?,?,?)}";
        try {
            Connection cn = BdConexion.getConexion(BdParametros.BD_URL, BdParametros.BD_USUARIO, BdParametros.BD_CLAVE);
            CallableStatement cs = cn.prepareCall(sql);
            cs.setInt(1, obj.getId());
            cs.setString(2, obj.getRuc());
            cs.setString(3, obj.getNombre());
            cs.setString(4, obj.getDireccion());
            cs.setString(5, obj.getEstado());
            cs.setString(6, obj.getTelefono());
            cs.setString(7, obj.getCorreo());
            cs.setString(8, obj.getIdDepartamento());
            cs.setString(9, obj.getIdProvincia());
            cs.setString(10, obj.getIdDistrito());
            cs.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("==> ERROR: actualizar <====");
            System.err.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean eliminar(EscuelaConduccion obj) throws PersistenciaException {
        String sql = "{CALL sp_delete_escuela(?)}";
        try {
            Connection cn = BdConexion.getConexion(BdParametros.BD_URL, BdParametros.BD_USUARIO, BdParametros.BD_CLAVE);
            CallableStatement cs = cn.prepareCall(sql);
            cs.setInt(1, obj.getId());
            cs.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("==> ERROR: eliminar <====");
            System.err.println(e.getMessage());
            return false;
        }
    }

    private List<EscuelaConduccion> getMapping(ResultSet rs) throws SQLException {
        List<EscuelaConduccion> listObj = new ArrayList<>();
        while (rs.next()) {
            EscuelaConduccion obj = new EscuelaConduccion();
            obj.setId(rs.getInt("ID"));
            obj.setRuc(rs.getString("RUC"));
            obj.setNombre(rs.getString("NOMBRE"));
            obj.setDireccion(rs.getString("DIRECCION"));
            obj.setEstado(rs.getString("ESTADO"));
            obj.setTelefono(rs.getString("TELEFONO"));
            obj.setCorreo(rs.getString("CORREO"));
            obj.setIdDistrito(rs.getString("ID_DIS"));
            obj.setIdProvincia(rs.getString("ID_PRO"));
            obj.setIdDepartamento(rs.getString("ID_DEP"));
            obj.setActivo(rs.getInt("ACTIVO"));
            listObj.add(obj);
        }
        return listObj;
    }
}
