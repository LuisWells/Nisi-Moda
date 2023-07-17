window.onload = function cargarCarritoOnLoad() {
    //leemos primero si ya hay items en el carrito
    const cookieExistente = Cookies.get("carritoNiSi");
    let listaExistente;
    let itemsEnCarrito;

    if (cookieExistente !== undefined) {
        //Sí existen items en el carrito, por tanto, actualizamos el indicador
        listaExistente = JSON.parse(cookieExistente);
        // console.log("Se encontraron items en un carrito anterior... actualizando la UI")
        // console.log(listaExistente);
        itemsEnCarrito = listaExistente.length;
        const labelCarrito = document.getElementById("indicador-carrito");
        labelCarrito.innerText = itemsEnCarrito;
        let precioTotal = 0;

        for (const item of listaExistente) {
            //TODO: actualizar precio del carrito al cambiar cantidades, sin actualizar la página
            precioTotal = precioTotal + (item.precio * item.cantidad);
            // actualizamos la lista para no agregar nuevamente lo ya agregado
            const productoEnCarrito = document.getElementById("agregar-carrito-producto-" + item.productoId);
            if (productoEnCarrito !== null) {
                productoEnCarrito.classList.remove("btn-outline-primary");
                productoEnCarrito.classList.add("btn-outline-success");
                productoEnCarrito.classList.add("disabled");
                productoEnCarrito.innerHTML = "<i class='bi bi-check-lg'></i> En Carrito</a>";
                // productoEnCarrito
                //     .getElementsByTagName("i")[0]
                //     .classList.replace("bi-cart-plus", "bi-check");
            }

        }
        // console.log("Precio total:")
        // console.log(precioTotal)
        const labelCostoTotal = document.getElementById("monto-costo-total");
        if (labelCostoTotal !== null) {
            labelCostoTotal.innerText = "Total: S/. " + String(precioTotal.toFixed(2));
        }
    }
    //Inicializar los tooltips:
    const tooltipTriggerList = document.querySelectorAll('[data-bs-toggle="tooltip"]')
    const tooltipList = [...tooltipTriggerList].map(tooltipTriggerEl => new bootstrap.Tooltip(tooltipTriggerEl))
}

function agregarListaItemACarrito(productoId, productoPrecio) {
    //leemos primero si ya hay items en el carrito
    const cookieExistente = Cookies.get("carritoNiSi");
    let itemsEnCarrito;
    let nuevaListaItems = [];
    if (cookieExistente !== undefined) {
        //Sí existen items en el carrito
        nuevaListaItems = JSON.parse(cookieExistente);
        // console.log(nuevaListaItems);
    }
    const id = Number(productoId);
    const precio = Number(productoPrecio);
    const itemCompra = {
        "productoId": id,
        "cantidad": 1,
        "precio": precio
    }
    nuevaListaItems.push(itemCompra);
    itemsEnCarrito = nuevaListaItems.length;
    //TODO: agregar propiedad domain a la cookie
    Cookies.set("carritoNiSi", JSON.stringify(nuevaListaItems));
    const labelCarrito = document.getElementById("indicador-carrito");
    if (labelCarrito !== null) {
        labelCarrito.innerText = itemsEnCarrito;
    }
}

function agregarDetalleItemACarrito(productoId, productoPrecio) {
    //leemos primero si ya hay items en el carrito
    const cookieExistente = Cookies.get("carritoNiSi");
    let itemsEnCarrito;
    let nuevaListaItems = [];
    if (cookieExistente !== undefined) {
        //Sí existen items en el carrito
        nuevaListaItems = JSON.parse(cookieExistente);
        // console.log(nuevaListaItems);
    }
    const inputCantidad = document.getElementById("cantidad-input");
    const id = Number(productoId);
    const cantidad = inputCantidad.value;
    const precio = Number(productoPrecio);
    const itemCompra = {
        "productoId": id,
        "cantidad": cantidad,
        "precio": precio
    }
    nuevaListaItems.push(itemCompra);
    itemsEnCarrito = nuevaListaItems.length;
    //TODO: agregar propiedad domain a la cookie
    Cookies.set("carritoNiSi", JSON.stringify(nuevaListaItems));
    const labelCarrito = document.getElementById("indicador-carrito");
    labelCarrito.innerText = itemsEnCarrito;
}

function cargarCarrito() {
    let carrito = Cookies.get("carritoNiSi");
    if (carrito !== undefined) {
        // const itemsListContainer = document.getElementById("contenedor-lista-items");
        // itemsListContainer.innerHTML='';
        const carritoArray = JSON.parse(carrito)
        carritoArray.forEach(item => {
            // console.log("new item!");
            // console.log(item);
        })
    }
}

function generarCompra() {

}

function eliminarCarrito() {
    Cookies.remove("carritoNiSi");
    console.log("Carrito borrado!");
    window.location.reload();
}

function eliminarItemCarrito(idProducto) {
    const id = Number(idProducto);
    let carrito = Cookies.get("carritoNiSi");
    if (carrito !== undefined) {
        const itemsListContainer = document.getElementById("contenedor-lista-items");
        // itemsListContainer.innerHTML='';
        const carritoArray = JSON.parse(carrito)
        let nuevoCarrito = carritoArray.filter((item) => item.productoId !== id);
        Cookies.set("carritoNiSi", JSON.stringify(nuevoCarrito));
        window.location.reload();
    }
}

//TODO: aumentar o reducir cuando el producto ya existe y se vuelve a agregar al carro, o mejor,
// deshabilitar el botón de agregar y mostrar qu esta agregado ya
function aumentarCantidadItem(idProducto) {
    const id = Number(idProducto);
    const labelCantidad = document.getElementById("cantidad-producto-" + idProducto);
    if (labelCantidad !== null) {
        console.log("aumentando cantidad del producto con ID " + idProducto)
        let actual = Number(labelCantidad.innerText);
        if (actual < 100) { //máximo 100 items del mismo producto por venta
            labelCantidad.innerText = String(actual + 1);
            let carrito = Cookies.get("carritoNiSi");
            if (carrito !== undefined) {
                const carritoArray = JSON.parse(carrito)
                carritoArray.forEach((item) => {
                    if (item.productoId === id) {
                        item.cantidad = actual + 1;
                    }
                });
                Cookies.set("carritoNiSi", JSON.stringify(carritoArray));
            }
        }

    }
}

function reducirCantidadItem(idProducto) {
    const id = Number(idProducto);
    const labelCantidad = document.getElementById("cantidad-producto-" + idProducto);
    if (labelCantidad !== null) {
        console.log("aumentando cantidad del producto con ID " + idProducto)
        let actual = Number(labelCantidad.innerText);
        if (actual >= 2) {
            labelCantidad.innerText = String(actual - 1);
            let carrito = Cookies.get("carritoNiSi");
            if (carrito !== undefined) {
                const carritoArray = JSON.parse(carrito)
                carritoArray.forEach((item) => {
                    if (item.productoId === id) {
                        item.cantidad = actual - 1;
                    }
                });
                Cookies.set("carritoNiSi", JSON.stringify(carritoArray));
            }
        }
    }
}