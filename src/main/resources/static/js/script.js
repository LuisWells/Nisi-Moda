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
    labelCarrito.innerText = itemsEnCarrito;
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