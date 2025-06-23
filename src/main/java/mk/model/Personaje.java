package mk.model;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Personaje {

    private static final AtomicLong ID_GENERATOR = new AtomicLong(1);

    private final Long id;
    private String nombre;
    private int saludMaxima;
    private NivelPoder nivelPoder;
    private List<String> movimientosEspeciales;

    public Personaje( String nombre, int saludMaxima, NivelPoder nivelPoder, List<String> movimientosEspeciales) {
        this.id = ID_GENERATOR.getAndIncrement();
        this.nombre = nombre;
        this.saludMaxima = saludMaxima;
        this.nivelPoder = nivelPoder;
        this.movimientosEspeciales = movimientosEspeciales;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSaludMaxima() {
        return saludMaxima;
    }

    public void setSaludMaxima(int saludMaxima) {
        this.saludMaxima = saludMaxima;
    }

    public NivelPoder getNivelPoder() {
        return nivelPoder;
    }

    public void setNivelPoder(NivelPoder nivelPoder) {
        this.nivelPoder = nivelPoder;
    }

    public List<String> getMovimientosEspeciales() {
        return movimientosEspeciales;
    }

    public void setMovimientosEspeciales(List<String> movimientosEspeciales) {
        this.movimientosEspeciales = movimientosEspeciales;
    }
}
