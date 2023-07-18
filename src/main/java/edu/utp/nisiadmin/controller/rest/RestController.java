package edu.utp.nisiadmin.controller.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import edu.utp.nisiadmin.dto.cliente.RegistroClienteDto;
import edu.utp.nisiadmin.dto.compra.RegistroCompraDto;
import edu.utp.nisiadmin.dto.itemcompra.RegistroItemCompraDto;
import edu.utp.nisiadmin.service.ClienteService;
import edu.utp.nisiadmin.service.CompraService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;

@Controller
@ResponseBody
@RequestMapping("/funciones")
@RequiredArgsConstructor
@Log
public class RestController {
    private final CompraService compraService;
    private final ClienteService clienteService;

    @PostMapping("/comprar")
    public boolean generarCompra(@CookieValue(value = "carritoNiSi", defaultValue = "noCookie") String cookie, @Valid @RequestBody RegistroClienteDto clienteDto) throws JsonProcessingException {
        try {
            if (!cookie.equals("noCookie")) {
                ObjectMapper mapper = new ObjectMapper();
                TypeFactory typeFactory = mapper.getTypeFactory();
                List<RegistroItemCompraDto> listaRegistrosEnCookie = mapper.readValue(cookie,
                        typeFactory.constructCollectionLikeType(List.class, RegistroItemCompraDto.class));

                // Consideramos el caso cuando el cliente ya existe
                boolean clienteExistente = clienteService.clienteYaExiste(clienteDto);
                Long clienteId;
                if (clienteExistente) {
                    clienteId = clienteService.obtenerClientePorEmailYDni(clienteDto.email(), clienteDto.dni()).id();
                } else {
                    clienteId = clienteService.crearCliente(clienteDto);
                }
                RegistroCompraDto compraDto = new RegistroCompraDto(clienteId, listaRegistrosEnCookie);
                compraService.crearCompra(compraDto);
            }
            return true;
        } catch (Exception e) {
            log.log(Level.INFO, e.getMessage());
            return false;
        }
    }
}
