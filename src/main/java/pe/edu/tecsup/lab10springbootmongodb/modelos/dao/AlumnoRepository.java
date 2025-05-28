package pe.edu.tecsup.lab10springbootmongodb.modelos.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import pe.edu.tecsup.lab10springbootmongodb.modelos.documents.Alumno;

public interface AlumnoRepository extends MongoRepository <Alumno, String>{
}
