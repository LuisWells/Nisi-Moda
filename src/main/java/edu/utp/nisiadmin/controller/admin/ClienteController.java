package edu.utp.nisiadmin.controller.admin;

import edu.utp.nisiadmin.dto.cliente.RegistroClienteDto;
import edu.utp.nisiadmin.enums.Estado;
import edu.utp.nisiadmin.service.ClienteService;
import edu.utp.nisiadmin.util.WebUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
@RequiredArgsConstructor
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService servicio;
    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("estadoValues", Estado.values());
    }

    @GetMapping
    public String listaClientes(Model model) {
        model.addAttribute("clientes", servicio.obtenerTodosLosClientes());
        return "clientes/lista";
    }

    @GetMapping("{id}")
    public String detalleCliente(Model model, @PathVariable Long id) {
        model.addAttribute("cliente", servicio.obtenerCliente(id));
        return "clientes/detalle";
    }

    @GetMapping("/nuevo")
    public String crearCliente(@ModelAttribute("cliente") RegistroClienteDto dto) {
        return "clientes/crear";
    }

    @PostMapping
    public String crearCliente(RegistroClienteDto dto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (!bindingResult.hasFieldErrors("email") && servicio.emailYaExiste(dto.email())) {
            bindingResult.rejectValue("email", "Exists.cliente.email");
        }
        if (!bindingResult.hasFieldErrors("username") && servicio.usernameYaExiste(dto.username())) {
            bindingResult.rejectValue("username", "Exists.cliente.username");
        }
        if (bindingResult.hasErrors()) {
            return "clientes/crear";
        }
        servicio.crearCliente(dto);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("cliente.create.success"));
        return "redirect:/clientes";
    }
    @PostMapping("/eliminar/{id}")
    public String borrarCliente(@PathVariable Long id, RedirectAttributes redirectAttributes){
        servicio.borrarCliente(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("cliente.delete.success"));
        return "redirect:/clientes";
    }
}
