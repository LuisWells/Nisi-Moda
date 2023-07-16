window.onload = function cargarCarritoOnLoad() {
    //leemos primero si ya hay items en el carrito
    const cookieExistente = Cookies.get("carritoNiSi");
    let listaExistente;
    let itemsEnCarrito;

    if (cookieExistente !== undefined) {
        //Sí existen items en el carrito, por tanto, actualizamos el indicador
        listaExistente = JSON.parse(cookieExistente);
        console.log("Se encontraron items en un carrito anterior... actualizando la UI")
        console.log(listaExistente);
        itemsEnCarrito = listaExistente.length;
        const labelCarrito = document.getElementById("indicador-carrito");
        labelCarrito.innerText = itemsEnCarrito;
        let precioTotal = 0;

        for (const item of listaExistente) {
            precioTotal = precioTotal + item['precio'];
            console.log(item)
        }
        console.log("Precio total:")
        console.log(precioTotal)
        const labelCostoTotal = document.getElementById("monto-costo-total");
        if (labelCostoTotal !== null) {
            labelCostoTotal.innerText = "Total: S/. " + String(precioTotal.toFixed(2));
        }
    }
}

function agregarListaItemACarrito(productoId, productoPrecio) {
    //leemos primero si ya hay items en el carrito
    const cookieExistente = Cookies.get("carritoNiSi");
    let itemsEnCarrito;
    let nuevaListaItems = [];
    if (cookieExistente !== undefined) {
        //Sí existen items en el carrito
        nuevaListaItems = JSON.parse(cookieExistente);
        console.log(nuevaListaItems);
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
        console.log(nuevaListaItems);
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
            console.log("new item!");
            console.log(item);
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