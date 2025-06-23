package mk.repository;

import mk.model.Personaje;

import java.util.*;

public class PersonajeRepository {

    private final Map<Long, Personaje> personajes = new HashMap<>();

    public void agregar(Personaje p){
        personajes.put(p.getId(), p);
    }

    public List<Personaje> listarTodos(){
        return new ArrayList<>(personajes.values());
    }

    public Optional<Personaje> buscarPorId(Long id){
        return Optional.ofNullable(personajes.get(id));
    }

    public void eliminar(Long id){
        personajes.remove(id);
    }
}
