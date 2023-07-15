package edu.utp.nisiadmin.controller.admin;

import edu.utp.nisiadmin.dto.empleado.RegistroEmpleadoDto;
import edu.utp.nisiadmin.enums.Estado;
import edu.utp.nisiadmin.enums.Rol;
import edu.utp.nisiadmin.service.EmpleadoService;
import edu.utp.nisiadmin.util.WebUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/empleados")
public class EmpleadoController {
    private final EmpleadoService servicio;
    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("estadoValues", Estado.values());
        model.addAttribute("rolValues", Rol.values());
    }

    @GetMapping
    public String listaEmpleados(Model model) {
        model.addAttribute("empleados", servicio.obtenerTodosLosEmpleados());
        return "empleados/lista";
    }

    @GetMapping("{id}")
    public String detalleEmpleado(Model model, @PathVariable Long id) {
        model.addAttribute("empleado", servicio.obtenerEmpleado(id));
        return "empleados/detalle";
    }

    @GetMapping("/nuevo")
    public String crearEmpleado(@ModelAttribute("empleado") RegistroEmpleadoDto dto) {
        return "empleados/crear";
    }

    @PostMapping("/nuevo")
    public String crearEmpleado(RegistroEmpleadoDto dto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (!bindingResult.hasFieldErrors("dni") && servicio.dniYaExiste(dto.dni())) {
            bindingResult.rejectValue("dni", "Exists.empleado.dni");
        }
        if (!bindingResult.hasFieldErrors("email") && servicio.emailYaExiste(dto.email())) {
            bindingResult.rejectValue("email", "Exists.empleado.email");
        }
        if (!bindingResult.hasFieldErrors("username") && servicio.usernameYaExiste(dto.username())) {
            bindingResult.rejectValue("username", "Exists.empleado.username");
        }
        if (bindingResult.hasErrors()) {
            return "empleados/crear";
        }
        servicio.crearEmpleado(dto);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("empleado.create.success"));
        return "redirect:/empleados";
    }
    @PostMapping("/eliminar/{id}")
    public String borrarEmpleado(@PathVariable Long id, RedirectAttributes redirectAttributes){
        servicio.borrarEmpleado(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("empleado.delete.success"));
        return "redirect:/empleados";
    }

}
