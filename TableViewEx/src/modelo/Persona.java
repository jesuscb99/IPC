package modelo;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Persona {
	
        private final IntegerProperty id = new SimpleIntegerProperty();
	private final StringProperty nombreCompleto = new SimpleStringProperty();
        private final ObjectProperty<Residence> residence = new SimpleObjectProperty<Residence>();
        private final StringProperty pathImagen = new SimpleStringProperty();
        
	public Persona(Integer d, String f, String c, String p, String path)
	{
                id.setValue(d);
		nombreCompleto.setValue(f);
		pathImagen.setValue(path);
                residence.setValue(new Residence(c, p));
	}
        
	public class Residence{
            String ciudad;
            String provincia;
            
            public Residence(String c, String p) {
                ciudad = c;
                provincia = p;
            }
            
            public String getCiudad() {
                return ciudad;
            }
            public String getProvincia() {
                return provincia;
            }
            
            public void setCiudad(String c) {
                ciudad = c;
            }
            
            public void setProvincia(String p) {
                provincia = p;
            }
        }

        
	
	public final java.lang.String getNombre() {
		return this.nombreCompleto.get();
	}
	public final void setNombre(final java.lang.String Nombre) {
		this.nombreCompleto.setValue(Nombre);
	}
	public final StringProperty nombreCompletoProperty() {
		return this.nombreCompleto;
	}
	
        public final Integer getID() {
            return this.id.get();
        }
        
        public final IntegerProperty idProperty() {
            return id;
        }
        
        public final void setId(Integer i) {
            id.setValue(i);
        }
        
        public final ObjectProperty<Residence> residenceProperty() {
            return residence;
        }
        public final Residence getResidence() {
            return residence.get();
        }
        
        public final String getPath() {
            return pathImagen.get();
        }
        
        public void setPath(String s) {
            pathImagen.setValue(s);
        }
        
        public final StringProperty pathImageProperty() {
            return pathImagen;
        }
}