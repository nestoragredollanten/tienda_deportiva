package bean;

import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import JpaController.exceptions.NonexistentEntityException;

import dao.ClientesDao;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import modelo.Clientes;
import util.Utilidad;
//import util.Reporte;

@ManagedBean
@ApplicationScoped
public class ClientesBean extends Clientes {

    public ClientesBean() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(Utilidad.PERSISTENCE);
        this.clientesDao = new ClientesDao(emf);
        this.agregar = true;
        this.btnValue = "Guardar";
        this.dlgTitle = "Agregar";
        this.vbuscar = false;
    }

    public void execute() {
        if (this.agregar) {
            this.agregar_clientes();
        } else {
            this.editar_clientes();
        }
    }

    public void agregar_clientes() {
        String msg = "";
        String nombres = super.getNombres();
        String apellidos = super.getApellidos();
        String cedula = super.getCedula();
        String email = super.getEmail();
        String direccion = super.getDireccion();
        Date fechaNacimiento = super.getFechaNacimiento();
        String celular = super.getCelular();
        Clientes tem = new Clientes();
        tem.setNombres(nombres);
        tem.setApellidos(apellidos);
        tem.setCedula(cedula);
        tem.setEmail(email);
        tem.setDireccion(direccion);
        tem.setFechaNacimiento(fechaNacimiento);
        tem.setCelular(celular);
        org.primefaces.context.RequestContext context = org.primefaces.context.RequestContext.getCurrentInstance();
        try {
            clientesDao.create(tem);
            msg = "Guardado exitosamente";
            context.execute("PF('dlgclientesOpciones').hide();");
        } catch (Exception e) {
            msg = "No se Guardo";
        }
        this.menssage(msg);
        context.update("panel");
    }

    public String convertir_fecha(Date fecha) {
        return Utilidad.convertir_fecha(fecha);
    }

    public void editar_clientes() {
        String msg;
        org.primefaces.context.RequestContext context = org.primefaces.context.RequestContext.getCurrentInstance();
        try {
            this.clientesSelected.setNombres(super.getNombres());
            this.clientesSelected.setApellidos(super.getApellidos());
            this.clientesSelected.setCedula(super.getCedula());
            this.clientesSelected.setEmail(super.getEmail());
            this.clientesSelected.setDireccion(super.getDireccion());
            this.clientesSelected.setFechaNacimiento(super.getFechaNacimiento());
            this.clientesSelected.setCelular(super.getCelular());
            this.clientesDao.edit(this.clientesSelected);
            msg = "Editado exitosamente";
            context.execute("PF('dlgclientesOpciones').hide();");
        } catch (Exception ex) {
            ex.printStackTrace();
            msg = "Error al Actualizar";
        }
        this.menssage(msg);
        context.update("panel");
    }

    public void agregar() {
        String msg = "";
        super.setNombres("");
        super.setApellidos("");
        super.setCedula("");
        super.setEmail("");
        super.setDireccion("");
        super.setFechaNacimiento(null);
        super.setCelular("");
        this.agregar = true;
        this.btnValue = "Guardar";
        this.dlgTitle = "Agregar";
    }

    public void editar() {
        String msg = "";
        super.setNombres(this.clientesSelected.getNombres());
        super.setApellidos(this.clientesSelected.getApellidos());
        super.setCedula(this.clientesSelected.getCedula());
        super.setEmail(this.clientesSelected.getEmail());
        super.setDireccion(this.clientesSelected.getDireccion());
        super.setFechaNacimiento(this.clientesSelected.getFechaNacimiento());
        super.setCelular(this.clientesSelected.getCelular());
        this.agregar = false;
        this.btnValue = "Guardar Cambios";
        this.dlgTitle = "Editar";
    }

    public void eliminar_clientes() {
        String msg = "Seleccione algo para eliminar";
        org.primefaces.context.RequestContext context = org.primefaces.context.RequestContext.getCurrentInstance();
        if (this.clientesSelected != null) {
            try {
                this.clientesDao.destroy(this.clientesSelected.getIdclientes());
                msg = "Eliminado exitosamente";
            } catch (NonexistentEntityException ex) {
                msg = "No pudo ser Eliminado ";
            }
        }
        this.menssage(msg);
        context.update("panel");
    }

    public void buscar() {
        System.err.println(this.tipoBusqueda);
        System.err.println(this.campoBuscar);
        if (this.campoBuscar.isEmpty()) {
            this.listaClientes = this.clientesDao.findClientesEntities();
        } else {
            switch (this.tipoBusqueda) {
                case "nombres":
                    this.listaClientes = this.clientesDao.getClientesName(this.campoBuscar);
                    break;
                case "apellidos":
                    this.listaClientes = this.clientesDao.getClientesLastName(this.campoBuscar);
                    break;
                case "cedula":
                    this.listaClientes = this.clientesDao.getClientesCedula(this.campoBuscar);
                    break;
            }
        }
        this.vbuscar = true;
    }

    public void reporte() {
        //ruta donde se va a guardar el archivo (lista, ruta)
        //new Reporte().clientes(listaClientes);
    }

    public void menssage(String msg) {
        FacesMessage ms = new FacesMessage(msg);
        ms.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, ms);
    }

    public Clientes getClientesSelected() {
        return clientesSelected;
    }

    public void setClientesSelected(Clientes selected) {
        this.clientesSelected = selected;
    }

    public List<Clientes> getListaClientes() {
        if (!this.vbuscar) {
            this.listaClientes = this.clientesDao.findClientesEntities();
        }
        return listaClientes;
    }

    public void setListaClientes(List<Clientes> lista) {
        this.listaClientes = lista;
    }

    public String getCampoBuscar() {
        return this.campoBuscar;
    }

    public void setCampoBuscar(String campoBuscar) {
        this.campoBuscar = campoBuscar;
    }

    public String getTipoBusqueda() {
        return this.campoBuscar;
    }

    public void setTipoBusqueda(String tipoBusqueda) {
        this.tipoBusqueda = tipoBusqueda;
    }

    public String getBtnValue() {
        return btnValue;
    }

    public String getDlgTitle() {
        return dlgTitle;
    }

    private ClientesDao clientesDao;
    private List<Clientes> listaClientes;
    private Clientes clientesSelected;
    private String campoBuscar;
    private String tipoBusqueda;
    private boolean agregar;
    private String btnValue;
    private String dlgTitle;
    private boolean vbuscar;
}
