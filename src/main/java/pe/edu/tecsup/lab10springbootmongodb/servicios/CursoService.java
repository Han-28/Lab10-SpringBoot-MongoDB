package pe.edu.tecsup.lab10springbootmongodb.servicios;

import pe.edu.tecsup.lab10springbootmongodb.modelos.documents.Curso;

import java.util.List;

public interface CursoService {

    public List<Curso> listar();

    public void grabar(Curso curso);

    public Curso buscar(String id);

    public void eliminar(String id);

}
