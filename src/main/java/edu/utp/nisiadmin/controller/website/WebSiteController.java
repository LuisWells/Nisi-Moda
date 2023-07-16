package edu.utp.nisiadmin.controller.website;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import edu.utp.nisiadmin.dto.itemcompra.RegistroItemCompraDto;
import edu.utp.nisiadmin.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class WebSiteController {
    private final ProductoService productoService;

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        if (currentPage < 1) {
            currentPage = 1;
        }
        //Acá las páginas vienen en lenguaje natural... inician en 1, por eso hay que restarle uno, ya que el servicio usa arrays que inician en cero.
        var listaProductos = productoService.obtenerTodosLosProductos(currentPage - 1, pageSize);
        model.addAttribute("productos", listaProductos);

        //agregamos al modelo una lista con los números de página, para el paginado
        int totalPages = listaProductos.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed().toList();
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "index";
    }

    @GetMapping("/productos/{id}")
    public String detalleProducto(Model model, @PathVariable Long id) {
        model.addAttribute("producto", productoService.obtenerProducto(id));
        model.addAttribute("relacionados", productoService.obtenerProductosRelacionados(id));
        return "productos/detalle";
    }


    @GetMapping("/carrito")
    public String detalleCarrito(Model model, @CookieValue(name = "carritoNiSi", defaultValue = "noCookie") String carritoCookie) throws JsonProcessingException {
        List<String> listaNombresDeProductos = new ArrayList<>();
        List<String> listaFotosDeProducto = new ArrayList<>();
        model.addAttribute("sinCarritoFlag", carritoCookie.equals("noCookie"));

        if (!carritoCookie.equals("noCookie")) {
            ObjectMapper mapper = new ObjectMapper();
            TypeFactory typeFactory = mapper.getTypeFactory();
            List<RegistroItemCompraDto> listaRegistrosEnCookie = mapper.readValue(carritoCookie,
                    typeFactory.constructCollectionLikeType(List.class, RegistroItemCompraDto.class));
            model.addAttribute("carrito", listaRegistrosEnCookie);
            for (RegistroItemCompraDto registro : listaRegistrosEnCookie) {
                var producto = productoService.obtenerProducto(registro.productoId());
                listaNombresDeProductos.add(producto.nombre());
                listaFotosDeProducto.add(producto.fotoUrl());
            }
            model.addAttribute("listaNombresDeProducto", listaNombresDeProductos);
            model.addAttribute("listaFotoDeProducto", listaFotosDeProducto);
        }
        return "carrito";
    }
}
