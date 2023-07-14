package edu.utp.nisiadmin.controller;

import edu.utp.nisiadmin.dto.producto.RegistroProductoDto;
import edu.utp.nisiadmin.enums.Estado;
import edu.utp.nisiadmin.service.ProductoService;
import edu.utp.nisiadmin.service.ProductoService;
import edu.utp.nisiadmin.util.WebUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
@RequiredArgsConstructor
@RequestMapping("/productos")
public class ProductoController {
    private final ProductoService servicio;

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("estadoValues", Estado.values());
    }

    @GetMapping
    public String listaProductos(Model model) {
        model.addAttribute("productos", servicio.obtenerTodosLosProductos());
        return "productos/lista";
    }

    @GetMapping("{id}")
    public String detalleProducto(Model model, @PathVariable Long id) {
        model.addAttribute("producto", servicio.obtenerProducto(id));
        return "productos/detalle";
    }

    @GetMapping("/nuevo")
    public String crearProducto(@ModelAttribute("producto") RegistroProductoDto dto) {
        return "productos/crear";
    }

    @PostMapping
    public String crearProducto(RegistroProductoDto dto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "productos/crear";
        }
        servicio.crearProducto(dto);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("producto.create.success"));
        return "redirect:/productos";
    }

    @PostMapping("/eliminar/{id}")
    public String borrarProducto(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        servicio.borrarProducto(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("producto.delete.success"));
        return "redirect:/productos";
    }
}
