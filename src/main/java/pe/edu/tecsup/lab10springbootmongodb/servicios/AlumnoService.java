package pe.edu.tecsup.lab10springbootmongodb.servicios;

import pe.edu.tecsup.lab10springbootmongodb.modelos.documents.Alumno;
import java.util.List;

public interface AlumnoService {
    public List<Alumno> listar();

    public void grabar(Alumno alumno);

    public Alumno buscar(String id);

    public void eliminar(String id);
}
