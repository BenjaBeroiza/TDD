package mk.service;

import mk.model.NivelPoder;
import mk.model.Personaje;
import mk.repository.PersonajeRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PersonajeService {

    private final PersonajeRepository repo;

    public PersonajeService(PersonajeRepository repo) {
        this.repo = repo;
    }

    public Personaje agregar(String nombre, int saludMaxima, NivelPoder nivelPoder, List<String> movimientos) {
        Personaje p = new Personaje(nombre, saludMaxima, nivelPoder, movimientos);
        repo.agregar(p);
        return p;
    }

    public boolean editar (Long id, String nombre, int saludMaxima, NivelPoder nivelPoder, List<String> movimientos) {
        Optional<Personaje> opt = repo.buscarPorId(id);
        if (opt.isEmpty()) return false;
        Personaje p = opt.get();
        p.setNombre(nombre);
        p.setSaludMaxima(saludMaxima);
        p.setNivelPoder(nivelPoder);
        p.setMovimientosEspeciales(movimientos);
        return true;
    }

    public List<Personaje>listarTodos(){
        return repo.listarTodos();
    }

    public boolean eliminar(Long id) {
        Optional<Personaje> opt = repo.buscarPorId(id);
        if (opt.isEmpty()) return false;
        repo.eliminar(id);
        return true;
    }


    public List<Personaje> buscarPorNivelMinimo(NivelPoder min) {

        return repo.listarTodos().stream()
                .filter(p -> p.getNivelPoder().ordinal() >= min.ordinal())
                .collect(Collectors.toList());
    }

    public List<Personaje> buscarPorNombre(String subcadena) {

        return repo.listarTodos().stream()
                .filter(p -> p.getNombre().toLowerCase().contains(subcadena.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Personaje> listarPorSaludDesc() {

        return repo.listarTodos().stream()
                .sorted(Comparator.comparingInt(Personaje::getSaludMaxima).reversed())
                .collect(Collectors.toList());
    }
}
