/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_programacion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 *
 * @author vasqu
 */
public class Persona {
    
    // === Atributos requeridos ===
    private String cui;              // Puede incluir ceros a la izquierda -> String
    private String nit;              // En GT puede llevar guion/verificador -> String
    private String domicilio;
    private LocalDate fechaNacimiento;
    private String nombres;
    private String apellidos;
    private String celular;          // Mejor como String por prefijos y formato

    
    
        // Constructores
    public Persona(String cui, String nit, String domicilio,
            LocalDate fechaNacimiento, String nombres,
            String apellidos, String celular) {

        // Validaciones mínimas (puedes ampliar según pida el profe)
        if (cui == null || cui.isBlank()) {
            throw new IllegalArgumentException("CUI es obligatorio");
        }
        if (nit == null || nit.isBlank()) {
            throw new IllegalArgumentException("NIT es obligatorio");
        }
        if (nombres == null || nombres.isBlank()) {
            throw new IllegalArgumentException("Nombres obligatorios");
        }
        if (apellidos == null || apellidos.isBlank()) {
            throw new IllegalArgumentException("Apellidos obligatorios");
        }
        if (fechaNacimiento == null) {
            throw new IllegalArgumentException("Fecha de nacimiento obligatoria");
        }
            
        // Constructores 
        this.cui = cui.trim();
        this.nit = nit.trim();
        this.domicilio = domicilio == null ? "" : domicilio.trim();
        this.fechaNacimiento = fechaNacimiento;
        this.nombres = nombres.trim();
        this.apellidos = apellidos.trim();
        this.celular = celular == null ? "" : celular.trim();
    }
    
    /**
     * Fábrica conveniente cuando recibes la fecha como texto (formato
     * dd/MM/yyyy o yyyy-MM-dd).
     */
    public static Persona of(String cui, String nit, String domicilio, String fechaNacStr, String nombres, String apellidos, String celular) {
        LocalDate fn = parseFecha(fechaNacStr);
        return new Persona(cui, nit, domicilio, fn, nombres, apellidos, celular);
    }

    
        private static LocalDate parseFecha(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Fecha nula");
        }
        s = s.trim();
        DateTimeFormatter[] formatos = new DateTimeFormatter[]{
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ISO_LOCAL_DATE // yyyy-MM-dd
        };
        for (DateTimeFormatter f : formatos) {
            try {
                return LocalDate.parse(s, f);
            } catch (DateTimeParseException ignored) {
            }
        }
        throw new IllegalArgumentException("Fecha inválida (usa dd/MM/yyyy o yyyy-MM-dd)");
    }
        
        // === Getters/Setters ===
    public String getCui() {
        return cui;
    }

    public String getNit() {
        return nit;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getCelular() {
        return celular;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio == null ? "" : domicilio.trim();
    }

    public void setCelular(String celular) {
        this.celular = celular == null ? "" : celular.trim();
    }

        // Conveniencias
    public String nombreCompleto() {
        return (nombres + " " + apellidos).trim();
    }

    public int getEdad() {
        return LocalDate.now().getYear() - fechaNacimiento.getYear()
                - (LocalDate.now().getDayOfYear() < fechaNacimiento.getDayOfYear() ? 1 : 0);
    }
    
    @Override
    public String toString() {
        return "Persona{cui='" + cui + "', nit='" + nit + "', nombre='" + nombreCompleto()
                + "', nacimiento=" + fechaNacimiento + ", cel='" + celular + "', dom='" + domicilio + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Persona)) {
            return false;
        }
        Persona persona = (Persona) o;
        return Objects.equals(cui, persona.cui); // CUI como identificador
    }

    @Override
    public int hashCode() {
        return Objects.hash(cui);
    }
}

