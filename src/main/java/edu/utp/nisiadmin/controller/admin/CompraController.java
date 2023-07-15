package edu.utp.nisiadmin.controller.admin;

import edu.utp.nisiadmin.dto.compra.RegistroCompraDto;
import edu.utp.nisiadmin.enums.Estado;
import edu.utp.nisiadmin.service.CompraService;
import edu.utp.nisiadmin.util.WebUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/compras")
public class CompraController {
    private final CompraService servicio;

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("estadoValues", Estado.values());
    }

    @GetMapping
    public String listaCompras(Model model) {
        model.addAttribute("compras", servicio.obtenerTodasLasCompras());
        return "compras/lista";
    }

    @GetMapping("{id}")
    public String detalleCompra(Model model, @PathVariable Long id) {
        model.addAttribute("compra", servicio.obtenerCompra(id));
        return "compras/detalle";
    }

    @PostMapping
    public String crearCompra(RegistroCompraDto dto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        servicio.crearCompra(dto);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("compra.create.success"));
        return "redirect:/compras";
    }

    @PostMapping("/eliminar/{id}")
    public String borrarCompra(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        servicio.borrarCompra(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("compra.delete.success"));
        return "redirect:/compras";
    }
}
