package edu.utp.nisiadmin.controller.website;

import edu.utp.nisiadmin.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class WebSiteController {
    private final ProductoService productoService;

    @GetMapping
    public String index(Model model) {
        var listaProductos = productoService.obtenerTodosLosProductos();
        model.addAttribute("productos", listaProductos);
        return "index";
    }
}
