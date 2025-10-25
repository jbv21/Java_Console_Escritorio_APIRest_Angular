package pe.com.galaxy.training.escritorio.persistence.ubicacion;

import pe.com.galaxy.training.escritorio.bean.ubicacion.Distrito;
import pe.com.galaxy.training.escritorio.persistence.util.PersistenciaException;
import pe.com.galaxy.training.escritorio.util.BdConexion;
import pe.com.galaxy.training.escritorio.util.BdParametros;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DistritoBDImpl implements DistritoBD {
    @Override
    public List<Distrito> listar(Distrito obj) throws PersistenciaException {
        try {
            String sql = "SELECT ID_DIS, NOMBRE_DIS " +
                    "FROM UBIGEO " +
                    "WHERE ID_DEP = ? " +
                    "   AND ID_PRO = ? " +
                    "GROUP BY " +
                    "   ID_DIS, NOMBRE_DIS";
            Connection cn = BdConexion.getConexion(BdParametros.BD_URL,BdParametros.BD_USUARIO, BdParametros.BD_CLAVE);
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, obj.getProvincia().getDepartamento().getId());
            ps.setString(2, obj.getProvincia().getId());
            ResultSet rs = ps.executeQuery();
            List<Distrito> listObj = this.getMapping(rs);
            rs.close();ps.close();cn.close();
            return listObj.isEmpty() ? Collections.emptyList() : listObj;
        } catch (SQLException e) {
            System.err.println("==> ERROR: listar <====");
            System.err.println(e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public Distrito seleccionar(Distrito obj) throws PersistenciaException {
        try {
            String sql = "SELECT ID_DIS, NOMBRE_DIS " +
                    "FROM UBIGEO " +
                    "WHERE ID_DEP = ? " +
                    "   AND ID_PRO = ? " +
                    "   AND ID_DIS = ? " +
                    "GROUP BY " +
                    "   ID_DIS, NOMBRE_DIS";
            Connection cn = BdConexion.getConexion(BdParametros.BD_URL,BdParametros.BD_USUARIO, BdParametros.BD_CLAVE);
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, obj.getProvincia().getDepartamento().getId());
            ps.setString(2, obj.getProvincia().getId());
            ps.setString(3, obj.getId());
            ResultSet rs = ps.executeQuery();
            List<Distrito> listObj = this.getMapping(rs);
            rs.close();ps.close();cn.close();
            return listObj.isEmpty() ? null : listObj.getFirst();
        } catch (SQLException e) {
            System.err.println("==> ERROR: seleccionar <====");
            System.err.println(e.getMessage());
            return null;
        }
    }
    
    private List<Distrito> getMapping(ResultSet rs) throws SQLException {
        List<Distrito> listObj = new ArrayList<>();
        while (rs.next()){
            Distrito obj = new Distrito();
            obj.setId(rs.getString("ID_DIS"));
            obj.setNombre(rs.getString("NOMBRE_DIS"));
            listObj.add(obj);
        }
        return listObj;
    }

}
