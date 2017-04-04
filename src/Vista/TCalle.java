/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Geiss
 */
@Entity
@Table(name = "TCalle", catalog = "EmpresaCondominio", schema = "public")
@NamedQueries({
    @NamedQuery(name = "TCalle.findAll", query = "SELECT t FROM TCalle t")
    , @NamedQuery(name = "TCalle.findByIdCalle", query = "SELECT t FROM TCalle t WHERE t.idCalle = :idCalle")
    , @NamedQuery(name = "TCalle.findByNroCasaCalle", query = "SELECT t FROM TCalle t WHERE t.nroCasaCalle = :nroCasaCalle")})
public class TCalle implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id_Calle")
    private String idCalle;
    @Column(name = "Nro_Casa_Calle")
    private Integer nroCasaCalle;

    public TCalle() {
    }

    public TCalle(String idCalle) {
        this.idCalle = idCalle;
    }

    public String getIdCalle() {
        return idCalle;
    }

    public void setIdCalle(String idCalle) {
        String oldIdCalle = this.idCalle;
        this.idCalle = idCalle;
        changeSupport.firePropertyChange("idCalle", oldIdCalle, idCalle);
    }

    public Integer getNroCasaCalle() {
        return nroCasaCalle;
    }

    public void setNroCasaCalle(Integer nroCasaCalle) {
        Integer oldNroCasaCalle = this.nroCasaCalle;
        this.nroCasaCalle = nroCasaCalle;
        changeSupport.firePropertyChange("nroCasaCalle", oldNroCasaCalle, nroCasaCalle);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCalle != null ? idCalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TCalle)) {
            return false;
        }
        TCalle other = (TCalle) object;
        if ((this.idCalle == null && other.idCalle != null) || (this.idCalle != null && !this.idCalle.equals(other.idCalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Vista.TCalle[ idCalle=" + idCalle + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
