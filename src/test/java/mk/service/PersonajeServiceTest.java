package mk.service;

import mk.model.NivelPoder;
import mk.model.Personaje;
import mk.repository.PersonajeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PersonajeServiceTest {
    private PersonajeRepository repo;
    private PersonajeService service;

    @BeforeEach
    void setup() {
        repo = new PersonajeRepository();
        service = new PersonajeService(repo);
    }

    @Test
    void testAgregarYListar() {
        service.agregar("Scorpion", 100, NivelPoder.ALTO, Arrays.asList("Teleport", "Fireball"));
        service.agregar("Sub-Zero", 90, NivelPoder.MEDIO, Arrays.asList("Ice Blast"));
        List<Personaje> todos = service.listarTodos();
        assertEquals(2, todos.size());
    }


    @Test
    void testEditar() {
        Personaje p = service.agregar("Kano", 85, NivelPoder.BAJO, Arrays.asList("Eye Laser"));
        boolean editado = service.editar(p.getId(), "Kano", 95, NivelPoder.MEDIO, Arrays.asList("Knife Throw"));
        assertTrue(editado);
        Personaje actualizado = service.listarTodos().get(0);
        assertEquals(95, actualizado.getSaludMaxima());
        assertEquals(NivelPoder.MEDIO, actualizado.getNivelPoder());
        assertEquals("Knife Throw", actualizado.getMovimientosEspeciales().get(0));
    }

    @Test
    void testEliminar() {
        Personaje p = service.agregar("Sonya", 90, NivelPoder.MEDIO, Arrays.asList("Energy Rings"));
        assertEquals(1, service.listarTodos().size());
        boolean eliminado = service.eliminar(p.getId());
        assertTrue(eliminado);
        assertEquals(0, service.listarTodos().size());
    }

    @Test
    void testBuscarPorNivelMinimo() {
        service.agregar("Scorpion", 100, NivelPoder.ALTO, Arrays.asList("Teleport"));
        service.agregar("Sub-Zero", 90, NivelPoder.MEDIO, Arrays.asList("Ice Blast"));
        service.agregar("Kano", 80, NivelPoder.BAJO, Arrays.asList("Eye Laser"));
        List<Personaje> result = service.buscarPorNivelMinimo(NivelPoder.MEDIO);
        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(p -> p.getNombre().equals("Scorpion")));
    }

    @Test
    void testBuscarPorNombre() {
        service.agregar("Scorpion", 100, NivelPoder.ALTO, Arrays.asList("Teleport"));
        service.agregar("Sub-Zero", 90, NivelPoder.MEDIO, Arrays.asList("Ice Blast"));
        List<Personaje> result = service.buscarPorNombre("sub");
        assertEquals(1, result.size());
        assertEquals("Sub-Zero", result.get(0).getNombre());
    }

    @Test
    void testListarPorSaludDesc() {
        service.agregar("Scorpion", 100, NivelPoder.ALTO, Arrays.asList("Teleport"));
        service.agregar("Sub-Zero", 90, NivelPoder.MEDIO, Arrays.asList("Ice Blast"));
        List<Personaje> result = service.listarPorSaludDesc();
        assertEquals("Scorpion", result.get(0).getNombre());
        assertEquals("Sub-Zero", result.get(1).getNombre());
    }

}
