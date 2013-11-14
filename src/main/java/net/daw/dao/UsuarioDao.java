package net.daw.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import net.daw.bean.UsuarioBean;
import net.daw.data.Mysql;
import net.daw.helper.Enum;
import net.daw.helper.FilterBean;

public class UsuarioDao {

    private final Mysql oMysql;
    private final Enum.Connection enumTipoConexion;
    private final String strTabla = "usuario";

    public UsuarioDao(Enum.Connection tipoConexion) throws Exception {
        oMysql = new Mysql();
        enumTipoConexion = tipoConexion;
    }

    public UsuarioBean getFromLogin(UsuarioBean oUsuario) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            String strId = oMysql.getId("usuario", "login", oUsuario.getLogin());
            if (strId == null) {
                oUsuario.setId(0);
            } else {
                oUsuario.setId(Integer.parseInt(strId));
                oUsuario.setPassword(oMysql.getOne("usuario", "password", oUsuario.getId()));
            }
            oMysql.desconexion();
            return oUsuario;
        } catch (Exception e) {
            throw new Exception("ClienteDao.getPage: Error: " + e.getMessage());
        }
    }
    
     public int getPages(int intRegsPerPag, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getPages("usuario", intRegsPerPag, alFilter, hmOrder);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("UsuarioDao.getPages: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    public ArrayList<UsuarioBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBean> alFilter, HashMap<String, String> hmOrder) throws Exception {
        ArrayList<Integer> arrId;
        ArrayList<UsuarioBean> arrUsuario = new ArrayList<>();
        try {
            oMysql.conexion(enumTipoConexion);
            arrId = oMysql.getPage("usuario", intRegsPerPag, intPage, alFilter, hmOrder);
            Iterator<Integer> iterador = arrId.listIterator();
            while (iterador.hasNext()) {
                UsuarioBean oUsuarioBean = new UsuarioBean(iterador.next());
                arrUsuario.add(this.get(oUsuarioBean));
            }
            oMysql.desconexion();
            return arrUsuario;
        } catch (Exception e) {
            throw new Exception("UsaurioDao.getPage: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }
    
       public UsuarioBean get(UsuarioBean oUsuarioBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oUsuarioBean.setLogin(oMysql.getOne(strTabla, "login", oUsuarioBean.getId()));
            oUsuarioBean.setPassword(oMysql.getOne(strTabla, "password", oUsuarioBean.getId()));
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("TiporoductoDao.get: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
        return oUsuarioBean;
    }
}
