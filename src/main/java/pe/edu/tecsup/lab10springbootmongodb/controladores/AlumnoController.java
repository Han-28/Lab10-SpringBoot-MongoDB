package pe.edu.tecsup.lab10springbootmongodb.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import pe.edu.tecsup.lab10springbootmongodb.modelos.documents.Alumno;
import pe.edu.tecsup.lab10springbootmongodb.servicios.AlumnoService;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes("alumno")
public class AlumnoController {

    @Autowired
    private AlumnoService servicio;

    // LISTAR ALUMNOS
    @RequestMapping(value = "/alumno/listar", method = RequestMethod.GET)
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de Alumnos");
        model.addAttribute("alumnos", servicio.listar());
        return "listaAlumno";
    }

    // CREAR NUEVO ALUMNO
    @RequestMapping(value = "/alumno/form")
    public String crear(Map<String, Object> model) {
        Alumno alumno = new Alumno();
        model.put("alumno", alumno);
        model.put("titulo", "Formulario de Alumno");
        model.put("sexos", List.of("M", "F")); // solo M o F
        return "formAlumno";
    }


    // EDITAR ALUMNO
    @RequestMapping(value = "/alumno/form/{id}")
    public String editar(@PathVariable("id") String id, Map<String, Object> model) {
        Alumno alumno = servicio.buscar(id);
        if (alumno == null) {
            return "redirect:/alumno/listar";
        }

        model.put("alumno", alumno);
        model.put("titulo", "Editar Alumno");
        model.put("sexos", List.of("M", "F"));
        return "formAlumno";
    }

    // GUARDAR ALUMNO
    @RequestMapping(value = "/alumno/form", method = RequestMethod.POST)
    public String guardar(@Validated Alumno alumno, BindingResult result, Model model, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Alumno");
            model.addAttribute("sexos", List.of("M", "F"));
            return "formAlumno";
        }

        servicio.grabar(alumno);
        status.setComplete();
        return "redirect:/alumno/listar";
    }

    // ELIMINAR ALUMNO
    @RequestMapping(value = "/alumno/eliminar/{id}")
    public String eliminar(@PathVariable("id") String id) {
        servicio.eliminar(id);
        return "redirect:/alumno/listar";
    }
}

