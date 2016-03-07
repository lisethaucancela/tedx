/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeanSession;

import ec.edu.espoch.entidades.CSession;
import ec.edu.espoch.modelo.MPersona;
import ec.edu.espoch.modelo.MSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.DefaultRequestContext;
import org.primefaces.model.UploadedFile;
import recursos.Util;

/**
 *
 * @author William
 */
@ManagedBean
@SessionScoped
public class MbSLogin implements Serializable {

    //private String usuario;
    //private String contrasenia;
    private CSession objSessionUsuario = new CSession();
    private String aux;
    public String codigoUsuario;
    private Session session;
    private Transaction transaccion;
    public static int codigo;
    private String password;

    public MbSLogin() {
        HttpSession miSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        miSession.setMaxInactiveInterval(5000);
    }

    public CSession getObjSessionUsuario() {
        return objSessionUsuario;
    }

    public void setObjSessionUsuario(CSession objSessionUsuario) {
        this.objSessionUsuario = objSessionUsuario;
    }

    public String getAux() {
        return aux;
    }

    public void setAux(String aux) {
        this.aux = aux;
    }

    public String login() {
        this.session = null;
        this.transaccion = null;

        try {
            MSession objSession = new MSession();
            ArrayList<CSession> lts = new ArrayList<>();
            lts = objSession.obtenerUsuario(objSessionUsuario);
            if (lts != null) {
                for (CSession lis : lts) {
                    this.aux = "" + lis.getNombre() + "  " + lis.getApellido();
                    this.codigoUsuario = lis.getCedula();
                    this.password = lis.getPassword();
                    //this.codigo =lis.getCodigoUsuario();
                    if (lis.getTipoUsuarioDescripcion().equals("Administrador")) {
                        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                        httpSession.setAttribute("cedula", this.objSessionUsuario.getCedula());
                        return "/paginas/frmRegistrados";
                    }
                    if (lis.getTipoUsuarioDescripcion().equals("Evaluador")) {
                        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                        httpSession.setAttribute("cedula", this.objSessionUsuario.getCedula());
                        return "/paginas/frmRegistrados";
                    }

                }
            }

            this.transaccion.commit();
            this.objSessionUsuario = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de acceso:", "Usuario o contraseña incorrecto"));

            return "/index";

        } catch (Exception ex) {
            if (this.transaccion != null) {
                this.transaccion.rollback();
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error de acceso:", "Usuario o contraseña incorrectos " + ex.getMessage()));

            return null;
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    public String cerrarSesion() {
        this.objSessionUsuario = null;
        //this.contrasenia=null;

        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        httpSession.invalidate();

        return "/Tedx/faces/paginas/login.xhtml";
    }

    public static int getCodigo() {
        return codigo;
    }

    public static void setCodigo(int codigo) {
        MbSLogin.codigo = codigo;
    }

    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    //metodo para ingresar imagenes
    private UploadedFile avatar;

    public void actualizarAvatar() throws IOException {
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            if (this.avatar.getSize() <= 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "Ud. debe seleccionar un archivo de imagen \".png\""));
                return;
            }

            if (!this.avatar.getFileName().endsWith(".png")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "El archivo debe ser con extensión \".png\""));
                return;
            }

            if (this.avatar.getSize() > 2097152) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "El archivo no puede ser más de 2mb"));
                return;
            }

            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String carpetaAvatar = (String) servletContext.getRealPath("/avatar");

            outputStream = new FileOutputStream(new File(carpetaAvatar + "/" + this.codigoUsuario + ".png"));
            inputStream = this.avatar.getInputstream();

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "Imagen actualizado correctamente"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor contacte con su administrador " + ex.getMessage()));
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }

            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    public UploadedFile getAvatar() {
        return avatar;
    }

    public void setAvatar(UploadedFile avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
