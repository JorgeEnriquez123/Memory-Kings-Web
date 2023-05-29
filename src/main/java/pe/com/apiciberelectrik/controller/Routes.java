package pe.com.apiciberelectrik.controller;

import java.util.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pe.com.apiciberelectrik.entity.gestion.*;
import pe.com.apiciberelectrik.repository.gestion.*;
import pe.com.apiciberelectrik.seguridad.SeguridadConfig;
import pe.com.apiciberelectrik.service.gestion.*;
import pe.com.apiciberelectrik.service.impl.gestion.ProductoProveedorServiceImpl;

@Controller
public class Routes {

    @Autowired
    private SeguridadConfig seguridadConfig;

    @Autowired
    private ProveedorService servicioproveedor;
    @Autowired
    private OrdenCompraService servicioordencompra;
    @Autowired
    private DetalleOrdenCompraService serviciodetalleordencompra;

    @Autowired
    private GuiaEntradaService servicioguiaentrada;
    @Autowired
    private DetalleGuiaEntradaService serviciodetalleguiaentrada;
    @Autowired
    private CategoriaService serviciocategoria;

    @Autowired
    private ProductoService servicioproducto;

    @Autowired
    private RolService serviciorol;

    @Autowired
    private ClienteService serviciocliente;
    @Autowired
    private VentaService servicioventa;
    @Autowired
    private DetalleVentaService serviciodetalleventa;

    @Autowired
    private UsuarioService serviciousuario;
    @Autowired
    private UsuarioRepository repositoriousuario;

    @Autowired
    private ClienteRepository repositoriocliente;

    @Autowired
    private DetalleOrdenCompraRepository repositorioDetalleordencompra;
    @Autowired
    private DetalleGuiaEntradaRepository repositorioDetalleguiaentrada;
    @Autowired
    private DetalleVentaRepository repositorioDetalleventa;
    @Autowired
    private ReclamoService servicioreclamo;
    @Autowired ProductoProveedorService servicioproductoproveedor;
    @Autowired
    ProductoProveedorServiceImpl servicioimplProductoproveedor;
    @Autowired
    ProductoProveedorRepository repositorioProductoProveedor;

    @GetMapping("/login")
    public String MostrarLogin() {
        return "login";
    }

    @GetMapping("/noaccess")
    public String noAccess() {
        return "error403";
    }

    // * MOSTRAR ENTIDADES --------------- 🡳🡳🡳🡳🡳🡳🡳🡳
    @GetMapping("/categoria/mostrarcategoria")
    public String MostrarCategoria(Model modelo) {
        modelo.addAttribute("categoria", serviciocategoria.findAllCustom());
        return "/categoria/mostrarcategoria";
    }

    @GetMapping("/rol/mostrarrol")
    public String MostrarRol(Model modelo) {
        modelo.addAttribute("rol", serviciorol.findAllCustom());
        return "/rol/mostrarrol";
    }

    @GetMapping("/cliente/mostrarcliente")
    public String MostrarCliente(Model modelo) {
        modelo.addAttribute("cliente", serviciocliente.findAllCustom());
        return "/cliente/mostrarcliente";
    }
    @GetMapping("/usuario/mostrarusuario")
    public String MostrarUsuario(Model modelo) {
        modelo.addAttribute("usuario", serviciousuario.findAllCustom());
        return "/usuario/mostrarusuario";
    }
    @GetMapping("/reclamo/mostrarreclamo")
    public String MostrarReclamo(Model modelo) {
        modelo.addAttribute("reclamo", servicioreclamo.findAllCustom());
        return "/reclamo/mostrarreclamo";
    }

    @GetMapping("/producto/mostrarproducto")
    public String MostrarProducto(Model modelo) {
        modelo.addAttribute("producto", servicioproducto.findAllCustom());
        return "/producto/mostrarproducto";
    }
    @GetMapping("/productoproveedor/mostrarproductoproveedor")
    public String MostrarProductoProveedor(Model modelo) {
        modelo.addAttribute("productoproveedor", servicioproductoproveedor.findAllCustom());
        return "/productoproveedor/mostrarproductoproveedor";
    }

    @GetMapping("/proveedor/mostrarproveedor")
    public String MostrarProveedor(Model modelo) {
        modelo.addAttribute("proveedor", servicioproveedor.findAllCustom());
        return "/proveedor/mostrarproveedor";
    }
    // ---------------
    @GetMapping("/guiaentrada/mostrarguiaentrada")
    public String MostrarGuiaEntrada(Model modelo) {
        modelo.addAttribute("guiaentrada", servicioguiaentrada.findAllCustom());
        return "/guiaentrada/mostrarguiaentrada";
    }
    @GetMapping("/ordencompra/mostrarordencompra")
    public String MostrarOrdenCompra(Model modelo) {
        modelo.addAttribute("ordencompra", servicioordencompra.findAllCustom());
        return "/ordencompra/mostrarordencompra";
    }
    @GetMapping("/venta/mostrarventa")
    public String MostrarVenta(Model modelo) {
        modelo.addAttribute("venta", servicioventa.findAllCustom());
        return "/venta/mostrarventa";
    }
    @GetMapping("/guiaentrada/mostrarguiaentradaborrar")
    public String MostrarGuiaEntradaBorrar(Model modelo) {
        modelo.addAttribute("guiaentrada", servicioguiaentrada.findAllCustom());
        listaProductosAIngresar.clear();
        return "/guiaentrada/mostrarguiaentrada";
    }
    @GetMapping("/ordencompra/mostrardetalleordencompra/{id}")
    public String MostrarDetalleOrdenCompra(@PathVariable Long id, Model modelo) {
        modelo.addAttribute("detalleordencompra", repositorioDetalleordencompra.findByOrdencompraCodigo(id));
        return "/ordencompra/mostrardetalleordencompra";
    }
    @GetMapping("/guiaentrada/mostrardetalleguiaentrada/{id}")
    public String MostrarDetalleGuiaEntrada(@PathVariable Long id, Model modelo) {
        List<DetalleGuiaEntradaEntity> listaDetalle = repositorioDetalleguiaentrada.findByGuiaentradaCodigo(id);
        modelo.addAttribute("detalleguiaentrada", listaDetalle);

        Double total = 0.0;

        for(int i = 0; i < listaDetalle.size(); i++){
            int cantidadObtenida = listaDetalle.get(i).getStock();
            double precioObtenido = listaDetalle.get(i).getPrecio();
            total = total + (cantidadObtenida * precioObtenido);
        }
        modelo.addAttribute("total", total);
        return "/guiaentrada/mostrardetalleguiaentrada";
    }

    @GetMapping("/venta/mostrardetalleventa/{id}")
    public String MostrarDetalleVenta(@PathVariable Long id, Model modelo) {
        List<DetalleVentaEntity> listaDetalle = repositorioDetalleventa.findByVentaCodigo(id);
        modelo.addAttribute("detalleventa", listaDetalle);

        Double total = 0.0;

        for(int i = 0; i < listaDetalle.size(); i++){
            int cantidadObtenida = listaDetalle.get(i).getCantidad();
            double precioObtenido = listaDetalle.get(i).getPreciouni();
            total = total + (cantidadObtenida * precioObtenido);
        }
        modelo.addAttribute("total", total);
        return "/venta/mostrardetalleventa";
    }

    // * MOSTRAR ENTIDADES --------------------- 🡱🡱🡱🡱🡱🡱🡱🡱
    @GetMapping("/principal")
    public String MostrarPrincipal(Model model, Authentication authentication) {
        String username = (String) authentication.getName();
        UsuarioEntity usuario = repositoriousuario.findByUsuario(username);
        String nombreusuario = usuario.getNombre();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String role = authorities.iterator().next().getAuthority();

        model.addAttribute("name", nombreusuario);
        model.addAttribute("role", role);
        return "principal";
    }


    // * MOSTRAR ACTUALIZAR --------------- 🡳🡳🡳🡳🡳🡳🡳🡳
    @GetMapping("/categoria/mostraractualizarcategoria/{id}")
    public String MostrarActualizarCategoria(@PathVariable Long id, Model modelo) {
        modelo.addAttribute("categoria", serviciocategoria.findById(id).get());
        return "/categoria/actualizarcategoria";
    }
    @GetMapping("/usuario/mostraractualizarusuario/{id}")
    public String MostrarActualizarUsuario(@PathVariable Long id, Model modelo) {
        modelo.addAttribute("rol", serviciorol.findAllCustom());
        modelo.addAttribute("usuario", serviciousuario.findById(id).get());
        return "/usuario/actualizarusuario";
    }
    @GetMapping("/mostraractualizarreclamo/{id}")
    public String MostrarActualizarReclamo(@PathVariable Long id, Model modelo) {
        modelo.addAttribute("cliente", serviciocliente.findAllCustom());
        modelo.addAttribute("reclamo", servicioreclamo.findById(id).get());
        return "/reclamo/actualizarreclamo";
    }

    @GetMapping("/cliente/mostraractualizarcliente/{id}")
    public String MostrarActualizarCliente(@PathVariable Long id, Model modelo) {
        modelo.addAttribute("cliente", serviciocliente.findById(id).get());
        return "/cliente/actualizarcliente";
    }

    @GetMapping("/rol/mostraractualizarrol/{id}")
    public String MostrarActualizarRol(@PathVariable Long id, Model modelo) {
        modelo.addAttribute("rol", serviciorol.findById(id).get());
        return "/rol/actualizarrol";
    }

    @GetMapping("/producto/control/mostraractualizarproducto/{id}")
    public String MostrarActualizarProducto(@PathVariable Long id, Model modelo) {
        modelo.addAttribute("categoria", serviciocategoria.findAllCustom());
        modelo.addAttribute("producto", servicioproducto.findById(id).get());
        return "/producto/actualizarproducto";
    }

    @GetMapping("/proveedor/mostraractualizarproveedor/{id}")
    public String MostrarActualizarProveedor(@PathVariable Long id, Model modelo) {
        modelo.addAttribute("proveedor", servicioproveedor.findById(id).get());
        return "/proveedor/actualizarproveedor";
    }
    @GetMapping("/productoproveedor/mostraractualizarproductoproveedor/{idproducto}/{idproveedor}")
    public String MostrarActualizarCategoria(@PathVariable Long idproducto, @PathVariable Long idproveedor, Model modelo) {
        modelo.addAttribute("productoproveedor", servicioimplProductoproveedor.findById2(idproducto, idproveedor));
        Optional<ProductoProveedorEntity> obj = servicioimplProductoproveedor.findById2(idproducto, idproveedor);
        if(obj.isEmpty()){
            return "redirect:/productoproveedor/mostrarproductoproveedor";
        }
        Optional<ProductoEntity> objprod = servicioproducto.findById(idproducto);
        Optional<ProveedorEntity> objproveedor = servicioproveedor.findById(idproveedor);

        modelo.addAttribute("producto", objprod.get());
        modelo.addAttribute("proveedor", objproveedor.get());

        return "/productoproveedor/actualizarproductoproveedor";
    }
    // * MOSTRAR ACTUALIZAR --------------------- 🡱🡱🡱🡱🡱🡱🡱🡱

    // * MOSTRAR HABILITAR --------------------- 🡳🡳🡳🡳🡳🡳🡳🡳
    @GetMapping("/categoria/mostrarhabilitarcategoria")
    public String MostrarHabilitarCategoria(Model modelo) {
        modelo.addAttribute("categoria", serviciocategoria.findAll());
        return "/categoria/habilitarcategoria";
    }
    @GetMapping("/usuario/mostrarhabilitarusuario")
    public String MostrarHabilitarUsuario(Model modelo) {
        modelo.addAttribute("usuario", serviciousuario.findAll());
        return "/usuario/habilitarusuario";
    }
    @GetMapping("/reclamo/mostrarhabilitarreclamo")
    public String MostrarHabilitarReclamo(Model modelo) {
        modelo.addAttribute("reclamo", servicioreclamo.findAll());
        return "/reclamo/habilitarreclamo";
    }

    @GetMapping("/rol/mostrarhabilitarrol")
    public String MostrarHabilitarRol(Model modelo) {
        modelo.addAttribute("rol", serviciorol.findAll());
        return "/rol/habilitarrol";
    }

    @GetMapping("/producto/control/mostrarhabilitarproducto")
    public String MostrarHabilitarProducto(Model modelo) {
        modelo.addAttribute("producto", servicioproducto.findAll());
        return "/producto/habilitarproducto";
    }
    @GetMapping("/productoproveedor/mostrarhabilitarproductoproveedor")
    public String MostrarHabilitarProductoProveedor(Model modelo) {
        modelo.addAttribute("productoproveedor", servicioproductoproveedor.findAll());
        return "/productoproveedor/habilitarproductoproveedor";
    }
    @GetMapping("/ordencompra/mostrarhabilitarordencompra")
    public String MostrarHabilitarOrdenCompra(Model modelo) {
        modelo.addAttribute("ordencompra", servicioordencompra.findAll());
        return "/ordencompra/habilitarordencompra";
    }
    @GetMapping("/guiaentrada/mostrarhabilitarguiaentrada")
    public String MostrarHabilitarGuiaEntrada(Model modelo) {
        modelo.addAttribute("guiaentrada", servicioguiaentrada.findAll());
        return "/guiaentrada/habilitarguiaentrada";
    }
    @GetMapping("/venta/control/mostrarhabilitarventa")
    public String MostrarHabilitarVenta(Model modelo) {
        modelo.addAttribute("venta", servicioventa.findAll());
        return "/venta/habilitarventa";
    }

    @GetMapping("/proveedor/mostrarhabilitarproveedor")
    public String MostrarHabilitarProveedor(Model modelo) {
        modelo.addAttribute("proveedor", servicioproveedor.findAll());
        return "/proveedor/habilitarproveedor";
    }
    @GetMapping("/cliente/mostrarhabilitarcliente")
    public String MostrarHabilitarCliente(Model modelo) {
        modelo.addAttribute("cliente", serviciocliente.findAll());
        return "/cliente/habilitarcliente";
    }
    // * MOSTRAR HABILITAR --------------------- 🡱🡱🡱🡱🡱🡱🡱🡱

    // * MOSTRAR REGISTRAR --------------------- 🡳🡳🡳🡳🡳🡳🡳🡳
    @GetMapping("/categoria/mostrarresgistrarcategoria")
    public String MostrarRegistrarCategoria() {
        return "/categoria/registrarcategoria";
    }
    @GetMapping("/usuario/mostrarresgistrarusuario")
    public String MostrarRegistrarUsuario(Model modelo) {
        modelo.addAttribute("rol",serviciorol.findAllCustom());
        return "/usuario/registrarusuario";
    }
    long codigoVentadeReclamo = 0;
    @GetMapping("/reclamo/mostrarresgistrarreclamo/{id}")
    public String MostrarRegistrarReclamoPorVenta(@PathVariable Long id, Model model) {
            Optional<VentaEntity> ventaAasignarleReclamo = servicioventa.findById(id);
            if(ventaAasignarleReclamo.isEmpty()){
                return "redirect:/venta/mostrarventa";
            }
            else{
                codigoVentadeReclamo = ventaAasignarleReclamo.get().getCodigo();
                model.addAttribute("codigoventa", codigoVentadeReclamo);
                return "/reclamo/registrarreclamo";
            }
    }

    @GetMapping("/proveedor/mostrarregistrarproveedor")
    public String MostrarRegistrarProveedor() {
        return "/proveedor/registrarproveedor";
    }
    @GetMapping("/rol/mostrarresgistrarrol")
    public String MostrarRegistrarRol() {
        return "/rol/registrarrol";
    }

    @GetMapping("/cliente/mostrarresgistrarcliente")
    public String MostrarRegistrarCliente() {
        return "/cliente/registrarcliente";
    }

    @GetMapping("/guiaentrada/mostrarresgistrarguiaentrada")
    public String MostrarRegistrarGuiaEntrada(Model modelo) {
        modelo.addAttribute("ordenescompradisponibles", servicioordencompra.findAllCustom());
        return "/guiaentrada/registrarguiaentrada";
    }
    @GetMapping("/ordencompra/mostrarresgistrarordencompra")
    public String MostrarRegistrarOrdenCompra(Model modelo) {
        modelo.addAttribute("productosdisponiblesdeproveedor", listaproductosdeproveedor);
        modelo.addAttribute("proveedoresdisponibles", servicioproveedor.findAllCustom());
        int index = 0;
        if (index < 0 || index >= listaproductosdeproveedor.size()){
            modelo.addAttribute("textoprov", "");
        }
        else{
            modelo.addAttribute("textoprov", listaproductosdeproveedor.get(0).getProveedor().getNombre());
        }
        return "/ordencompra/registrarordencompra";
    }
    @GetMapping("/venta/control/mostrarresgistrarventa")
    public String MostrarRegistrarVenta(Model modelo) {
        modelo.addAttribute("productosdisponibles", servicioproducto.findAllCustom());
        return "/venta/registrarventa";
    }
    @GetMapping("/producto/control/mostrarresgistrarproducto")
    public String MostrarRegistrarProducto(Model modelo) {
        modelo.addAttribute("categoria", serviciocategoria.findAllCustom());
        return "/producto/registrarproducto";
    }
    @GetMapping("/productoproveedor/mostrarresgistrarproductoproveedor")
    public String MostrarRegistrarProductoProveedor(Model modelo) {
        modelo.addAttribute("producto", servicioproducto.findAllCustom());
        modelo.addAttribute("proveedor", servicioproveedor.findAllCustom());
        return "/productoproveedor/registrarproductoproveedor";
    }
    // * MOSTRAR REGISTRAR --------------------- 🡱🡱🡱🡱🡱🡱🡱🡱

    // * MODELATTRIBUTE ENTIDADES --------------------- 🡳🡳🡳🡳🡳🡳🡳🡳
    @ModelAttribute("categoria")
    public CategoriaEntity ModeloCategoria() {
        return new CategoriaEntity();
    }
    @ModelAttribute("reclamo")
    public ReclamoEntity ModeloReclamo() {
        return new ReclamoEntity();
    }

    @ModelAttribute("cliente")
    public ClienteEntity ModeloCliente() {
        return new ClienteEntity();
    }

    @ModelAttribute("producto")
    public ProductoEntity ModeloProducto() {
        return new ProductoEntity();
    }

    @ModelAttribute("rol")
    public RolEntity ModeloRol() {
        return new RolEntity();
    }
    @ModelAttribute("usuario")
    public UsuarioEntity ModeloUsurio() {
        return new UsuarioEntity();
    }
    @ModelAttribute("guiaentrada")
    public GuiaEntradaEntity ModeloGuiaEntrada(){
        return new GuiaEntradaEntity();
    }
    @ModelAttribute("detalleguiaentrada")
    public DetalleGuiaEntradaEntity ModeloDetalleGuiaEntrada(){
        return new DetalleGuiaEntradaEntity();
    }
    @ModelAttribute("proveedor")
    public ProveedorEntity ModeloProveedor(){
        return new ProveedorEntity();
    }
    @ModelAttribute("ordencompra")
    public OrdenCompraEntity ModeloOrdenCompra(){
        return new OrdenCompraEntity();
    }
    @ModelAttribute("venta")
    public VentaEntity ModeloVenta() {
        return new VentaEntity();
    }
    @ModelAttribute("detalleventa")
    public DetalleVentaEntity ModeloDetalleVenta() {
        return new DetalleVentaEntity();
    }
    @ModelAttribute("productoproveedor")
    public ProductoProveedorEntity ModeloProductoProveedor() {
        return new ProductoProveedorEntity();
    }


    // * MODELATTRIBUTE ENTIDADES --------------------- 🡱🡱🡱🡱🡱🡱🡱🡱

    // * REGISTRAR ACCION --------------- 🡳🡳🡳🡳🡳🡳🡳🡳
    @PostMapping("/registrarcategoria")
    public String RegistroCategoria(@Valid @ModelAttribute("categoria") CategoriaEntity c, BindingResult result) {
        try {
            if(result.hasErrors()){
                return "categoria/registrarcategoria";
            }
            serviciocategoria.add(c);
            return "redirect:/categoria/mostrarcategoria?correcto";
        } catch (Exception e) {
            return "redirect:/categoria/mostrarcategoria?incorrecto";
        }
    }
    @PostMapping("/cliente/registrarcliente")
    public String RegistroCliente(@Valid @ModelAttribute("cliente") ClienteEntity c, BindingResult result) {
        try {
            if(result.hasErrors()){
                return "cliente/registrarcliente";
            }
            serviciocliente.add(c);
            return "redirect:/cliente/mostrarcliente?correcto";
        } catch (Exception e) {
            return "redirect:/cliente/mostrarcliente?incorrecto";
        }
    }

    // * REGISTRAR PRODOCTO PROVEEDOR ----

    @PostMapping("/productoproveedor/registrarproductoproveedor")
    public String RegistroProductoProveedor(@Valid @ModelAttribute("productoproveedor") ProductoProveedorEntity pv, BindingResult result, Model modelo) {
        try {
            modelo.addAttribute("producto", servicioproducto.findAllCustom());
            modelo.addAttribute("proveedor", servicioproveedor.findAllCustom());
            if(result.hasErrors()){
                return "productoproveedor/registrarproductoproveedor";
            }
            servicioproductoproveedor.add(pv);
            return "redirect:/productoproveedor/mostrarproductoproveedor?correcto";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/productoproveedor/mostrarproductoproveedor?incorrecto";
        }
    }

    long idproveedordelaordendecompra = 0;

    // * ORDEN COMPRA  🡳🡳🡳🡳🡳🡳 -------
    List<ProductoProveedorEntity> listaproductosdeproveedor = new ArrayList<>();
    @PostMapping("/ordencompra/seleccionarproveedorordencompra")
    public String BuscarProductosPorProveedor(@Valid @ModelAttribute("proveedor") ProveedorEntity p, BindingResult result, Model model) {
        try {
            if(p.getCodigo() == 0){
                System.out.println("0");
                return "redirect:/ordencompra/mostrarresgistrarordencompra?noproveedor=true";
            }
            listaProductosAComprar.clear();
            idproveedordelaordendecompra = p.getCodigo();
            listaproductosdeproveedor = repositorioProductoProveedor.findByProveedorCodigo(p.getCodigo());

            return "redirect:/ordencompra/mostrarresgistrarordencompra";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/ordencompra/mostrarresgistrarordencompra";

        }
    }
    @PostMapping("/ordencompra/añadirproductoordencompra")
    public String AñadirProductoAComprar(@Valid @ModelAttribute("producto") ProductoEntity p, BindingResult result, Model model) {
        try{
            model.addAttribute("productosdisponiblesdeproveedor", listaproductosdeproveedor);
            model.addAttribute("proveedoresdisponibles", servicioproveedor.findAllCustom());

            if(p.getCodigo() == 0){
                return "redirect:/ordencompra/mostrarresgistrarordencompra?productonoseleccionado=true";
            } else if (p.getStock() <= 0) {
                return "redirect:/ordencompra/mostrarresgistrarordencompra?cantidadinvalida=true";

            }
            // * Va a buscar los productos, si hay uno que ya se ingresó anteriormente
            for(ProductoEntity product : listaProductosAComprar){
                if(product.getCodigo() == p.getCodigo()){
                    product.agregarStock(p.getStock());
                    return "redirect:/ordencompra/mostrarresgistrarordencompra";
                }
            }
            Optional<ProductoProveedorEntity> productodeproveedor = servicioimplProductoproveedor.findById2(p.getCodigo(), idproveedordelaordendecompra);
            double preciodeProveedor = productodeproveedor.get().getPreciocompra();
            p.setPrecio(preciodeProveedor);
            p.setDescripcion(productodeproveedor.get().getProducto().getDescripcion());

            listaProductosAComprar.add(p);
            return "redirect:/ordencompra/mostrarresgistrarordencompra";
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return "redirect:/ordencompra/mostrarresgistrarordencompra";
        }
    }

    @PostMapping("/ordencompra/registrarordencompra")
    public String RegistroOrdenCompra(Model model) {
        try {
            model.addAttribute("productosdisponiblesdeproveedor", listaproductosdeproveedor);
            model.addAttribute("proveedoresdisponibles", servicioproveedor.findAllCustom());

            ProveedorEntity prov = new ProveedorEntity();
            prov.setCodigo(idproveedordelaordendecompra);

            OrdenCompraEntity oc = new OrdenCompraEntity();
            oc.setProveedor(prov);

            if(oc.getProveedor().getCodigo() == 0){
                return "redirect:/ordencompra/mostrarresgistrarordencompra?noproveedor=true";
            }
            else if(listaProductosAComprar.isEmpty()){
                return "redirect:/ordencompra/mostrarresgistrarordencompra?noproductoselect=true";
            }

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) auth.getPrincipal();

            UsuarioEntity usuariosesion = repositoriousuario.findByUsuario(userDetails.getUsername());
            Long userId = usuariosesion.getCodigo();

            UsuarioEntity usuarioid = new UsuarioEntity();
            usuarioid.setCodigo(userId);

            oc.setUsuario(usuarioid);

            servicioordencompra.add(oc);
            OrdenCompraEntity ordenCompraCreada = servicioordencompra.add(oc);

            for(int i = 0; i < listaProductosAComprar.size(); i++) {
                DetalleOrdenCompraEntity detalleOrdenCompra = new DetalleOrdenCompraEntity();
                detalleOrdenCompra.setPreciouni(listaProductosAComprar.get(i).getPrecio());
                detalleOrdenCompra.setCantidad(listaProductosAComprar.get(i).getStock());
                detalleOrdenCompra.setOrdencompra(ordenCompraCreada);

                ProductoEntity productoAgregado = new ProductoEntity();
                productoAgregado.setCodigo(listaProductosAComprar.get(i).getCodigo());
                detalleOrdenCompra.setProducto(productoAgregado);

                serviciodetalleordencompra.add(detalleOrdenCompra);
            }
            listaProductosAComprar.clear();
            idproveedordelaordendecompra = 0;
            return "redirect:/ordencompra/mostrarordencompra?correcto";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/ordencompra/mostrarordencompra?incorrecto";
        }
    }

    // -----------

    // * VENTA 🡳🡳🡳🡳🡳🡳 ----

    @PostMapping("/venta/añadirproductolistaventa")
    public String AñadirProductoListaVenta(@ModelAttribute("producto") ProductoEntity p, Model modelo){
        try{
            modelo.addAttribute("productosdisponibles", servicioproducto.findAllCustom());

            if(p.getCodigo() == 0){
                return "redirect:/venta/control/mostrarresgistrarventa?productonoseleccionado=true";
            } else if (p.getStock() <= 0) {
                return "redirect:/venta/control/mostrarresgistrarventa?cantidadinvalida=true";
            }

            Optional<ProductoEntity> productoConPrecioActual = servicioproducto.findById(p.getCodigo());

            if(p.getStock() > productoConPrecioActual.get().getStock()){
                return "redirect:/venta/control/mostrarresgistrarventa?nohaystock=true";
            }

            // * DARLE EL PRECIO DEL PRODUCTO ACTUAL
            p.setPrecio(productoConPrecioActual.get().getPrecio());

            // * Va a buscar los productos, si hay uno que ya se ingresó anteriormente
            for(ProductoEntity product : listaProductosAVender){
                int sumastock = 0;
                if(product.getCodigo() == p.getCodigo()){
                    sumastock = sumastock + product.getStock() + p.getStock();
                    if(sumastock > productoConPrecioActual.get().getStock()){
                        return "redirect:/venta/control/mostrarresgistrarventa?nohaystock=true";
                    }
                    product.agregarStock(p.getStock());
                    return "redirect:/venta/control/mostrarresgistrarventa";
                }
            }
            listaProductosAVender.add(p);
            return "redirect:/venta/control/mostrarresgistrarventa";
        }

        catch (Exception e){
            System.out.println(e.getMessage());
            return "redirect:/venta/control/mostrarresgistrarventa?error";
        }
    }

    @PostMapping("/venta/control/registrarventa")
    public String RegistroVenta(@Valid @ModelAttribute("cliente") ClienteEntity c, BindingResult result, Model model) {
        try {
            model.addAttribute("productosdisponibles", servicioproducto.findAllCustom());

            if(listaProductosAVender.isEmpty()){
                return "redirect:/venta/control/mostrarresgistrarventa?noproductoselect=true";
            }

            // * CREAR VENTA
            VentaEntity ventacreada = new VentaEntity();

            // ? SETEANDO CLIENTE
            Optional<ClienteEntity> clienteencontrado = repositoriocliente.findByDni(c.getDni());
            if(clienteencontrado.isEmpty()){
                return "redirect:/venta/control/mostrarresgistrarventa?nocliente=true";
            }
            else{
                ventacreada.setCliente(clienteencontrado.get());
            }
            // ----

            // ? SETEANDO USUARIO
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) auth.getPrincipal();

            UsuarioEntity usuariosesion = repositoriousuario.findByUsuario(userDetails.getUsername());
            Long userId = usuariosesion.getCodigo();

            UsuarioEntity usuarioid = new UsuarioEntity();
            usuarioid.setCodigo(userId);

            ventacreada.setUsuario(usuarioid);
            // -----

            servicioventa.add(ventacreada);
            VentaEntity ventaRecienCreada = servicioventa.add(ventacreada);

            for(int i = 0; i < listaProductosAVender.size(); i++) {
                DetalleVentaEntity detalleVenta = new DetalleVentaEntity();
                detalleVenta.setPreciouni(listaProductosAVender.get(i).getPrecio());
                detalleVenta.setCantidad(listaProductosAVender.get(i).getStock());
                detalleVenta.setVenta(ventaRecienCreada);

                ProductoEntity productoAgregado = new ProductoEntity();
                productoAgregado.setCodigo(listaProductosAVender.get(i).getCodigo());
                detalleVenta.setProducto(productoAgregado);

                // * RESTAR STOCK
                Optional<ProductoEntity> productorestarstock = servicioproducto.findById(listaProductosAVender.get(i).getCodigo());
                ProductoEntity productoresta = productorestarstock.get(); //Obtener producto que se va a actualizar stock (restar)

                productoresta.restarStock(listaProductosAVender.get(i).getStock()); // Restando stock del producto
                servicioproducto.add(productoresta);
                serviciodetalleventa.add(detalleVenta);

            }
            listaProductosAVender.clear();
            return "redirect:/venta/mostrarventa?correcto";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/venta/mostrarventa?incorrecto";
        }
    }

    // ---------
    // * GUIA ENTRADA 🡳🡳🡳🡳🡳🡳🡳🡳

    @PostMapping("/guiaentrada/registrarguiaentrada")
    public String RegistroGuiaEntrada(HttpServletRequest request, Model model) {
        try {
            // * Sin son null los parametros, regresara con un error
            String[] cantidadrecibida = request.getParameterValues("cantidad[]");
            String[] preciorecibidos = request.getParameterValues("precio[]");

            double cant = Double.parseDouble(cantidadrecibida[0]);
            double prec = Double.parseDouble(preciorecibidos[0]);
            if(cant == 0.0 || prec == 0.0){
                return "redirect:/guiaentrada/mostrarresgistrarguiaentrada?error";
            }

            if(cantidadrecibida == null || preciorecibidos == null){
                return "redirect:/guiaentrada/mostrarresgistrarguiaentrada?errornoseleccion=true";
            }

            // * ------------ VERIFICAR QUE NO HAYA INPUTS INVALIDOS -----------
            // No necesita validación ya que el form no va a hacer submit si se ingresan valores invalidos

            // * -------------- RECIBIR INPUTS Y ACTUALIZAR LISTA --------------
            List<String> cantidades = Arrays.asList(request.getParameterValues("cantidad[]"));
            List<String> precios = Arrays.asList(request.getParameterValues("precio[]"));
            for(int i = 0; i < cantidades.size(); i++){
                listaProductosAIngresar.get(i).setStock(Integer.parseInt(cantidades.get(i)));
                listaProductosAIngresar.get(i).setPrecio(Double.parseDouble(precios.get(i)));
            }

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) auth.getPrincipal();

            UsuarioEntity usuariosesion = repositoriousuario.findByUsuario(userDetails.getUsername());
            Long userId = usuariosesion.getCodigo();

            UsuarioEntity usuarioid = new UsuarioEntity();
            usuarioid.setCodigo(userId);

            // ? --------
            // ? Set Usuario
            GuiaEntradaEntity ge = new GuiaEntradaEntity();
            ge.setUsuario(usuarioid);

            // ? Set Orden de Compra
            //long proveedorID = servicioordencompra.findById(ordenCompraseleccionadaID).get().getProveedor().getCodigo();
            OrdenCompraEntity ordencompraAsignado = new OrdenCompraEntity();
            ordencompraAsignado.setCodigo(ordenCompraseleccionadaID);
            ge.setOrdencompra(ordencompraAsignado);
            // ? --------

            servicioguiaentrada.add(ge);
            GuiaEntradaEntity guiaEntradaCreado = servicioguiaentrada.add(ge);



            // * -------------- CREAR DETALLE --------------
            // Lista actualizada para ingresarproductos
            for(int i = 0; i < listaProductosAIngresar.size(); i++) {
                DetalleGuiaEntradaEntity detalleguiaentrada = new DetalleGuiaEntradaEntity();
                detalleguiaentrada.setPrecio(listaProductosAIngresar.get(i).getPrecio());
                detalleguiaentrada.setStock(listaProductosAIngresar.get(i).getStock());
                detalleguiaentrada.setGuiaentrada(guiaEntradaCreado);

                ProductoEntity productoAgregado = new ProductoEntity();
                productoAgregado.setCodigo(listaProductosAIngresar.get(i).getCodigo());
                detalleguiaentrada.setProducto(productoAgregado);

                Optional<ProductoEntity> productoagregarstock = servicioproducto.findById(listaProductosAIngresar.get(i).getCodigo());
                ProductoEntity nuevoproducto = productoagregarstock.get(); //Obtener producto que se va a actualizar

                nuevoproducto.agregarStock(listaProductosAIngresar.get(i).getStock()); // Agregando stock del producto
                servicioproducto.add(nuevoproducto);

                serviciodetalleguiaentrada.add(detalleguiaentrada); // Agregando detalle
            }

            listaProductosAIngresar.clear();
            ordenCompraseleccionadaID = 0;

            return "redirect:/guiaentrada/mostrarguiaentrada?correcto";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/guiaentrada/mostrarresgistrarguiaentrada?error";
        }
    }
    long ordenCompraseleccionadaID = 0; //Para usarlo despues
    @PostMapping("/guiaentrada/buscarordencompra")
    public String BuscarOrdenCompra(@Valid @ModelAttribute("ordencompra") OrdenCompraEntity oc, BindingResult result, Model model) {
        try {
            if(oc.getCodigo() == 0){
                return "redirect:/guiaentrada/mostrarresgistrarguiaentrada?errornoseleccion=true";
            }
            listaProductosAIngresar.clear();
            model.addAttribute("ordenescompradisponibles", servicioordencompra.findAllCustom());

            long codigoordencompra = oc.getCodigo();
            ordenCompraseleccionadaID = servicioordencompra.findById(codigoordencompra).get().getCodigo();

            List<DetalleOrdenCompraEntity> detalleObtenido = repositorioDetalleordencompra.findByOrdencompraCodigo(codigoordencompra);
            for(int i=0; i < detalleObtenido.size(); i++){
                listaProductosAIngresar.add(detalleObtenido.get(i).getProducto());
                listaProductosAIngresar.get(i).setPrecio(detalleObtenido.get(i).getPreciouni());
                listaProductosAIngresar.get(i).setStock(detalleObtenido.get(i).getCantidad());
            }

            return "redirect:/guiaentrada/mostrarresgistrarguiaentrada";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/guiaentrada/mostrarresgistrarguiaentrada";

        }
    }
    List<ProductoEntity> listaProductosAComprar = new ArrayList<>();
    List<ProductoEntity> listaProductosAIngresar = new ArrayList<>();
    List<ProductoEntity> listaProductosAVender = new ArrayList<>();

    // ? Esto hará que en la interfaz se listen los productos que se piensan ingresar
    // ? Se asigna un attribute para que esté disponible en Thymeleaf
    @ModelAttribute("listaproductosagregadosparaingreso")
    public List<ProductoEntity> ModeloListaProductosAgregados() {
        return listaProductosAIngresar;
    }
    @ModelAttribute("listaproductosagregadosparacompra")
    public List<ProductoEntity> ModeloListaProductosAgregadosParaCompra() {
        return listaProductosAComprar;
    }
    @ModelAttribute("listaproductosagregadosparaventa")
    public List<ProductoEntity> ModeloListaProductosAgregadosParaVenta() {
        return listaProductosAVender;
    }

    @GetMapping("/guiaentrada/eliminardelistaentrada/{id}")
    public String EliminarProductoDeListaEntrada(@PathVariable Long id){
        try{
            for(int i = 0; i < listaProductosAIngresar.size(); i++){
                if(listaProductosAIngresar.get(i).getCodigo() == id){
                    listaProductosAIngresar.remove(i);
                }
            }
            return "redirect:/guiaentrada/mostrarresgistrarguiaentrada?eliminado";
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return "redirect:/guiaentrada/mostrarresgistrarguiaentrada?noeliminado";
        }
    }
    @GetMapping("/ordencompra/eliminardelistacompra/{id}")
    public String EliminarProductoDeListaCompra(@PathVariable Long id, Model model){
        try{
            for(int i = 0; i < listaProductosAComprar.size(); i++){
                if(listaProductosAComprar.get(i).getCodigo() == id){
                    listaProductosAComprar.remove(i);
                }
            }
            return "redirect:/ordencompra/mostrarresgistrarordencompra?eliminado";
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return "redirect:/ordencompra/mostrarresgistrarordencompra?noeliminado";
        }
    }
    @GetMapping("/eliminardelistaventa/{id}")
    public String EliminarProductoDeListaVenta(@PathVariable Long id){
        try{
            for(int i = 0; i < listaProductosAVender.size(); i++){
                if(listaProductosAVender.get(i).getCodigo() == id){
                    listaProductosAVender.remove(i);
                }
            }
            return "redirect:/venta/control/mostrarresgistrarventa?eliminado";
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return "redirect:/venta/control/mostrarresgistrarventa?noeliminado";
        }
    }
    // * GUIA ENTRADA -- 🡱🡱🡱🡱🡱🡱🡱🡱🡱

    @PostMapping("/producto/control/registrarproducto")
    public String RegistroProducto(@Valid @ModelAttribute("producto") ProductoEntity p, BindingResult result, Model model) {
        try {
            model.addAttribute("categoria", serviciocategoria.findAllCustom());
            if(result.hasErrors()){
                return "producto/registrarproducto";
            }
            servicioproducto.add(p);
            return "redirect:/producto/mostrarproducto?correcto";
        } catch (Exception e) {
            return "redirect:/producto/mostrarproducto?incorrecto";

        }
    }
    @PostMapping("/proveedor/registrarproveedor")
    public String RegistroProveedor(@Valid @ModelAttribute("proveedor") ProveedorEntity pv, BindingResult result) {
        try {
            if(result.hasErrors()){
                return "proveedor/registrarproveedor";
            }
            servicioproveedor.add(pv);
            return "redirect:/proveedor/mostrarproveedor?correcto";
        } catch (Exception e) {
            return "redirect:/proveedor/mostrarproveedor?incorrecto";
        }
    }
    @PostMapping("/reclamo/registrarreclamo")
    public String RegistroReclamo(@ModelAttribute("reclamo") ReclamoEntity r, Model model) {
        try {
            Optional<VentaEntity> ventaparareclamo = servicioventa.findById(codigoVentadeReclamo);
            r.setCliente(ventaparareclamo.get().getCliente());
            r.setVenta(ventaparareclamo.get());

            servicioreclamo.add(r);
            return "redirect:/reclamo/mostrarreclamo?correcto";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/reclamo/mostrarreclamo?incorrecto";
        }
    }
    @PostMapping("/rol/registrarrol")
    public String RegistroRol(@Valid @ModelAttribute("rol") RolEntity r, BindingResult result) {
        try {
            if(result.hasErrors()){
                return "rol/registrarrol";
            }
            serviciorol.add(r);
            return "redirect:/rol/mostrarrol?correcto";
        } catch (Exception e) {
            return "redirect:/rol/mostrarrol?incorrecto";
        }
    }
    @PostMapping("/usuario/registrarusuario")
    public String RegistroUsuario(@Valid @ModelAttribute("usuario") UsuarioEntity u, BindingResult result, Model model) {
        try {
            model.addAttribute("rol", serviciorol.findAllCustom());
            if(result.hasErrors()){
                return "usuario/registrarusuario";
            }
            String passwordcodificado = seguridadConfig.passwordEncoder().encode(u.getPassword());
            u.setPassword(passwordcodificado);
            serviciousuario.add(u);
            return "redirect:/usuario/mostrarusuario?correcto";
        } catch (Exception e) {
            return "redirect:/usuario/mostrarusuario?incorrecto";
        }
    }




    // * REGISTRAR ACCION --------------------- 🡱🡱🡱🡱🡱🡱🡱🡱

    // * ACTUALIZAR ACCION --------------- 🡳🡳🡳🡳🡳🡳🡳🡳
    @PostMapping("/categoria/actualizarcategoria/{id}")
    public String ActualizarCategoria(@PathVariable Long id,@Valid @ModelAttribute("categoria") CategoriaEntity c, BindingResult result) {
        try {
            if(result.hasErrors()){
                return "categoria/actualizarcategoria";
            }
            serviciocategoria.update(c);
            return "redirect:/categoria/mostrarcategoria?actualizado";
        } catch (Exception e) {
            return "redirect:/categoria/mostrarcategoria?noactualizado";

        }
    }
    @PostMapping("/cliente/actualizarcliente/{id}")
    public String ActualizarCliente(@PathVariable Long id, @Valid @ModelAttribute("cliente") ClienteEntity c, BindingResult result) {
        try {
            if(result.hasErrors()){
                return "cliente/actualizarcliente";
            }
            serviciocliente.update(c);
            return "redirect:/cliente/mostrarcliente?actualizado";
        } catch (Exception e) {
            return "redirect:/cliente/mostrarcliente?noactualizado";
        }
    }
    @PostMapping("/producto/control/actualizarproducto/{id}")
    public String ActualizarProducto(@PathVariable Long id,@Valid @ModelAttribute("producto") ProductoEntity p, BindingResult result, Model model) {
        try {
            model.addAttribute("categoria", serviciocategoria.findAllCustom());
            if(result.hasErrors()){
                return "producto/actualizarproducto";
            }
            servicioproducto.update(p);
            return "redirect:/producto/mostrarproducto?actualizado";
        } catch (Exception e) {
            return "redirect:/producto/mostrarproducto?noactualizado";

        }
    }
    @PostMapping("/proveedor/actualizarproveedor/{id}")
    public String ActualizarProveedor(@PathVariable Long id,@Valid @ModelAttribute("proveedor") ProveedorEntity pv, BindingResult result) {
        try {
            if(result.hasErrors()){
                return "proveedor/actualizarproveedor";
            }
            servicioproveedor.update(pv);
            return "redirect:/proveedor/mostrarproveedor?actualizado";
        } catch (Exception e) {
            return "redirect:/proveedor/mostrarproveedor?noactualizado";

        }
    }
    /*
    @PostMapping("/actualizarreclamo/{id}")
    public String ActualizarReclamo(@PathVariable Long id,@Valid @ModelAttribute("reclamo") ReclamoEntity r, BindingResult result, Model model) {
        try {
            model.addAttribute("cliente", serviciocliente.findAllCustom());
            if(result.hasErrors()){
                return "reclamo/actualizarreclamo";
            }
            servicioreclamo.update(r);
            return "redirect:/reclamo/mostrarreclamo?actualizado";
        } catch (Exception e) {
            return "redirect:/reclamo/mostrarreclamo?noactualizado";

        }
    }*/

    @PostMapping("/rol/actualizarrol/{id}")
    public String ActualizarRol(@PathVariable Long id,@Valid @ModelAttribute("rol") RolEntity r, BindingResult result) {
        try {
            if(result.hasErrors()){
                return "rol/actualizarrol";
            }
            serviciorol.update(r);
            return "redirect:/rol/mostrarrol?actualizado";
        } catch (Exception e) {
            return "redirect:/rol/mostrarrol?noactualizado";
        }
    }
    @PostMapping("/usuario/actualizarusuario/{id}")
    public String ActualizarUsuario(@PathVariable Long id,@Valid @ModelAttribute("usuario") UsuarioEntity u, BindingResult result, Model model){
        try {
            model.addAttribute("rol", serviciorol.findAllCustom());
            if(result.hasErrors()){
                return "usuario/actualizarusuario";
            }
            String passwordcodificado = seguridadConfig.passwordEncoder().encode(u.getPassword());
            u.setPassword(passwordcodificado);
            serviciousuario.update(u);
            return "redirect:/usuario/mostrarusuario?actualizado";
        } catch (Exception e) {
            return "redirect:/usuario/mostrarusuario?noactualizado";
        }
    }
    @PostMapping("/productoproveedor/actualizarproductoproveedor/{idproducto}/{idproveedor}")
    public String ActualizarProductoProveedor(@PathVariable Long idproducto, @PathVariable Long idproveedor,
                                              @Valid @ModelAttribute("productoproveedor") ProductoProveedorEntity pv, BindingResult result, Model model){
        try {
            if(result.hasErrors()){
                return "productoproveedor/actualizarproductoproveedor";
            }
            System.out.println(pv);
            servicioimplProductoproveedor.update2(idproducto, idproveedor, pv);
            return "redirect:/productoproveedor/mostrarproductoproveedor?actualizado";
        } catch (Exception e) {
            return "redirect:/productoproveedor/mostrarproductoproveedor?noactualizado";
        }
    }

    // * ACTUALIZAR ACCION--------------------- 🡱🡱🡱🡱🡱🡱🡱🡱

    // * ELIMINAR (DESDE MOSTRAR) ACCION --------------- 🡳🡳🡳🡳🡳🡳🡳🡳
    @GetMapping("/categoria/eliminarcategoria/{id}")
    public String EliminarCategoria(@PathVariable Long id, Model modelo) {
        try {
            CategoriaEntity objCategoria = serviciocategoria.findById(id).get();
            serviciocategoria.delete(objCategoria);
            return "redirect:/categoria/mostrarcategoria?eliminado";
        } catch (Exception e) {
            return "redirect:/categoria/mostrarcategoria?noeliminado";
        }
    }
    @GetMapping("/usuario/eliminarusuario/{id}")
    public String EliminarUsuario(@PathVariable Long id, Model modelo) {
        try {
            UsuarioEntity objUsuario = serviciousuario.findById(id).get();
            serviciousuario.delete(objUsuario);
            return "redirect:/usuario/mostrarusuario?eliminado";
        } catch (Exception e) {
            return "redirect:/usuario/mostrarusuario?noeliminado";
        }
    }
    @GetMapping("/reclamo/eliminarreclamo/{id}")
    public String EliminarReclamo(@PathVariable Long id, Model modelo) {
        try {
            ReclamoEntity objReclamo = servicioreclamo.findById(id).get();
            servicioreclamo.delete(objReclamo);
            return "redirect:/reclamo/mostrarreclamo?eliminado";
        } catch (Exception e) {
            return "redirect:/reclamo/mostrarreclamo?noeliminado";
        }
    }
    @GetMapping("/cliente/eliminarcliente/{id}")
    public String EliminarCliente(@PathVariable Long id, Model modelo) {
        try {
            ClienteEntity objCliente = serviciocliente.findById(id).get();
            serviciocliente.delete(objCliente);
            return "redirect:/cliente/mostrarcliente?eliminado";
        } catch (Exception e) {
            return "redirect:/cliente/mostrarcliente?noeliminado";
        }
    }
    @GetMapping("/ordencompra/eliminarordencompra/{id}")
    public String EliminarOrdenCompra(@PathVariable Long id, Model modelo) {
        try {
            OrdenCompraEntity objOrdencompra = servicioordencompra.findById(id).get();
            servicioordencompra.delete(objOrdencompra);
            return "redirect:/ordencompra/mostrarordencompra?eliminado";
        } catch (Exception e) {
            return "redirect:/ordencompra/mostrarordencompra?noeliminado";
        }
    }
    @GetMapping("/guiaentrada/eliminarguiaentrada/{id}")
    public String EliminarGuiaEntrada(@PathVariable Long id, Model modelo) {
        try {
            GuiaEntradaEntity objGuiaEntrada = servicioguiaentrada.findById(id).get();
            servicioguiaentrada.delete(objGuiaEntrada);
            return "redirect:/guiaentrada/mostrarguiaentrada?eliminado";
        } catch (Exception e) {
            return "redirect:/guiaentrada/mostrarguiaentrada?noeliminado";
        }
    }
    @GetMapping("/venta/control/eliminarventa/{id}")
    public String EliminarVenta(@PathVariable Long id, Model modelo) {
        try {
            VentaEntity objVenta = servicioventa.findById(id).get();
            servicioventa.delete(objVenta);
            return "redirect:/venta/mostrarventa?eliminado";
        } catch (Exception e) {
            return "redirect:/venta/mostrarventa?noeliminado";
        }
    }
    @GetMapping("/rol/eliminarrol/{id}")
    public String EliminarRol(@PathVariable Long id, Model modelo) {
        try {
            RolEntity objRol = serviciorol.findById(id).get();
            serviciorol.delete(objRol);
            return "redirect:/rol/mostrarrol?eliminado";
        } catch (Exception e) {
            return "redirect:/rol/mostrarrol?noeliminado";

        }
    }
    @GetMapping("/producto/control/eliminarproducto/{id}")
    public String EliminarProdcuto(@PathVariable Long id, Model modelo) {
        try {
            ProductoEntity objProducto = servicioproducto.findById(id).get();
            servicioproducto.delete(objProducto);
            return "redirect:/producto/mostrarproducto?eliminado";
        } catch (Exception e) {
            return "redirect:/producto/mostrarproducto?noeliminado";

        }
    }
    @GetMapping("/productoproveedor/eliminarproductoproveedor/{idproducto}/{idproveedor}")
    public String EliminarProdcuto(@PathVariable Long idproducto, @PathVariable Long idproveedor, Model modelo) {
        try {
            Optional<ProductoProveedorEntity> objpvbuscar = servicioimplProductoproveedor.findById2(idproducto, idproveedor);
            if (objpvbuscar.isEmpty()){
                return "redirect:/productoproveedor/mostrarproductoproveedor";
            }
            ProductoProveedorEntity objpv = objpvbuscar.get();
            servicioproductoproveedor.delete(objpv);
            return "redirect:/productoproveedor/mostrarproductoproveedor?eliminado";
        } catch (Exception e) {
            return "redirect:/productoproveedor/mostrarproductoproveedor?noeliminado";

        }
    }
    @GetMapping("/proveedor/eliminarproveedor/{id}")
    public String EliminarProveedor(@PathVariable Long id, Model modelo) {
        try {
            ProveedorEntity objProveedor = servicioproveedor.findById(id).get();
            servicioproveedor.delete(objProveedor);
            return "redirect:/proveedor/mostrarproveedor?eliminado";
        } catch (Exception e) {
            return "redirect:/proveedor/mostrarproveedor?noeliminado";
        }
    }
    // * ELIMINAR (DESDE MOSTRAR) ACCION--------------------- 🡱🡱🡱🡱🡱🡱🡱🡱

    // * HABILITAR ACCION --------------- 🡳🡳🡳🡳🡳🡳🡳🡳
    @GetMapping("/categoria/habilitarcategoria/{id}")
    public String HabilitarCategoria(@PathVariable Long id, Model modelo) {
        try {
            CategoriaEntity objCategoria = serviciocategoria.findById(id).get();
            serviciocategoria.enabled(objCategoria);
            return "redirect:/categoria/mostrarcategoria?habilitado";
        } catch (Exception e) {
            return "redirect:/categoria/mostrarcategoria?nohabilitado";
        }
    }
    @GetMapping("/rol/habilitarrol/{id}")
    public String HabilitarRol(@PathVariable Long id, Model modelo) {
        try {
            RolEntity objRol = serviciorol.findById(id).get();
            serviciorol.enabled(objRol);
            return "redirect:/rol/mostrarrol?habilitado";
        } catch (Exception e) {
            return "redirect:/rol/mostrarrol?nohabilitado";
        }
    }

    @GetMapping("/usuario/habilitarusuario/{id}")
    public String HabilitarUsuario(@PathVariable Long id, Model modelo) {
        try {
            UsuarioEntity objUsuario = serviciousuario.findById(id).get();
            serviciousuario.enabled(objUsuario);
            return "redirect:/usuario/mostrarusuario?habilitado";
        } catch (Exception e) {
            return "redirect:/usuario/mostrarusuario?nohabilitado";
        }
    }
    @GetMapping("/producto/control/habilitarproducto/{id}")
    public String HabilitarProducto(@PathVariable Long id, Model modelo) {
        try {
            ProductoEntity objCategoria = servicioproducto.findById(id).get();
            servicioproducto.enabled(objCategoria);
            return "redirect:/producto/mostrarproducto?habilitado";
        } catch (Exception e) {
            return "redirect:/producto/mostrarproducto?nohabilitado";

        }
    }
    @GetMapping("/productoproveedor/habilitarproductoproveedor/{idproducto}/{idproveedor}")
    public String HabilitarProducto(@PathVariable Long idproducto, @PathVariable Long idproveedor, Model modelo) {
        try {
            Optional<ProductoProveedorEntity> objpvbuscar = servicioimplProductoproveedor.findById2(idproducto, idproveedor);
            if (objpvbuscar.isEmpty()){
                return "redirect:/productoproveedor/mostrarproductoproveedor";
            }
            ProductoProveedorEntity objpv = objpvbuscar.get();
            servicioproductoproveedor.enabled(objpv);
            return "redirect:/productoproveedor/mostrarproductoproveedor?habilitado";
        } catch (Exception e) {
            return "redirect:/productoproveedor/mostrarproductoproveedor?nohabilitado";

        }
    }
    @GetMapping("/ordencompra/habilitarordencompra/{id}")
    public String HabilitarOrdenCompra(@PathVariable Long id, Model modelo) {
        try {
            OrdenCompraEntity objOrdencompra = servicioordencompra.findById(id).get();
            servicioordencompra.enabled(objOrdencompra);
            return "redirect:/ordencompra/mostrarordencompra?habilitado";
        } catch (Exception e) {
            return "redirect:/ordencompra/mostrarordencompra?nohabilitado";
        }
    }
    @GetMapping("/guiaentrada/habilitarguiaentrada/{id}")
    public String HabilitarGuiaEntrada(@PathVariable Long id, Model modelo) {
        try {
            GuiaEntradaEntity objGuiaEntrada = servicioguiaentrada.findById(id).get();
            servicioguiaentrada.enabled(objGuiaEntrada);
            return "redirect:/guiaentrada/mostrarguiaentrada?habilitado";
        } catch (Exception e) {
            return "redirect:/guiaentrada/mostrarguiaentrada?nohabilitado";
        }
    }
    @GetMapping("/venta/control/habilitarventa/{id}")
    public String HabilitarVenta(@PathVariable Long id, Model modelo) {
        try {
            VentaEntity objVenta = servicioventa.findById(id).get();
            servicioventa.enabled(objVenta);
            return "redirect:/venta/mostrarventa?habilitado";
        } catch (Exception e) {
            return "redirect:/venta/mostrarventa?nohabilitado";
        }
    }
    @GetMapping("/reclamo/habilitarreclamo/{id}")
    public String HabilitarReclamo(@PathVariable Long id, Model modelo) {
        try {
            ReclamoEntity objReclamo = servicioreclamo.findById(id).get();
            servicioreclamo.enabled(objReclamo);
            return "redirect:/reclamo/mostrarreclamo?habilitado";
        } catch (Exception e) {
            return "redirect:/reclamo/mostrarreclamo?nohabilitado";
        }
    }
    @GetMapping("/cliente/habilitarcliente/{id}")
    public String HabilitarCliente(@PathVariable Long id, Model modelo) {
        try {
            ClienteEntity objCliente = serviciocliente.findById(id).get();
            serviciocliente.enabled(objCliente);
            return "redirect:/cliente/mostrarcliente?habilitado";
        } catch (Exception e) {
            return "redirect:/cliente/mostrarcliente?nohabilitado";

        }
    }
    @GetMapping("/proveedor/habilitarproveedor/{id}")
    public String HabilitarProveedor(@PathVariable Long id, Model modelo) {
        try {
            ProveedorEntity objProveedor = servicioproveedor.findById(id).get();
            servicioproveedor.enabled(objProveedor);
            return "redirect:/proveedor/mostrarproveedor?habilitado";
        } catch (Exception e) {
            return "redirect:/proveedor/mostrarproveedor?nohabilitado";
        }
    }
    // * HABILITAR ACCION --------------------- 🡱🡱🡱🡱🡱🡱🡱🡱

    // * DESHABILITAR ACCION --------------- 🡳🡳🡳🡳🡳🡳🡳🡳
    @GetMapping("/categoria/deshabilitarcategoria/{id}")
    public String DesabilitarCategoria(@PathVariable Long id, Model modelo) {
        try {
            CategoriaEntity objCategoria = serviciocategoria.findById(id).get();
            serviciocategoria.delete(objCategoria);
            return "redirect:/categoria/mostrarcategoria?deshabilitado";
        } catch (Exception e) {
            return "redirect:/categoria/mostrarcategoria?nodeshabilitado";
        }
    }
    @GetMapping("/reclamo/deshabilitarreclamo/{id}")
    public String DesabilitarReclamo(@PathVariable Long id, Model modelo) {
        try {
            ReclamoEntity objReclamo = servicioreclamo.findById(id).get();
            servicioreclamo.delete(objReclamo);
            return "redirect:/reclamo/mostrarreclamo?deshabilitado";
        } catch (Exception e) {
            return "redirect:/reclamo/mostrarreclamo?nodeshabilitado";
        }
    }
    @GetMapping("/cliente/deshabilitarcliente/{id}")
    public String DeshabilitarCliente(@PathVariable Long id, Model modelo) {
        try {
            ClienteEntity objCliente = serviciocliente.findById(id).get();
            serviciocliente.delete(objCliente);
            return "redirect:/cliente/mostrarcliente?deshabilitado";
        } catch (Exception e) {
            return "redirect:/cliente/mostrarcliente?nodeshabilitado";
        }
    }
    @GetMapping("/usuario/deshabilitarusuario/{id}")
    public String DesabilitarUsuario(@PathVariable Long id, Model modelo) {
        try {
            UsuarioEntity objUsuario = serviciousuario.findById(id).get();
            serviciousuario.delete(objUsuario);
            return "redirect:/usuario/mostrarusuario?deshabilitado";
        } catch (Exception e) {
            return "redirect:/usuario/mostrarusuario?nodeshabilitado";
        }
    }

    @GetMapping("/rol/deshabilitarrol/{id}")
    public String DesabilitarRol(@PathVariable Long id, Model modelo) {
        try {
            RolEntity objRol = serviciorol.findById(id).get();
            serviciorol.delete(objRol);
            return "redirect:/rol/mostrarrol?deshabilitado";
        } catch (Exception e) {
            return "redirect:/rol/mostrarrol?nodeshabilitado";

        }
    }
    @GetMapping("/producto/control/deshabilitarproducto/{id}")
    public String DeshabilitarProducto(@PathVariable Long id, Model modelo) {
        try {
            ProductoEntity objProducto = servicioproducto.findById(id).get();
            servicioproducto.delete(objProducto);
            return "redirect:/producto/mostrarproducto?deshabilitado";
        } catch (Exception e) {
            return "redirect:/producto/mostrarproducto?nodeshabilitado";

        }
    }
    @GetMapping("/productoproveedor/deshabilitarproductoproveedor/{idproducto}/{idproveedor}")
    public String DeshabilitarProductoProveedor(@PathVariable Long idproducto, @PathVariable Long idproveedor, Model modelo) {
        try {
            Optional<ProductoProveedorEntity> objpvbuscar = servicioimplProductoproveedor.findById2(idproducto, idproveedor);
            if (objpvbuscar.isEmpty()){
                return "redirect:/productoproveedor/mostrarproductoproveedor";
            }
            ProductoProveedorEntity objpv = objpvbuscar.get();
            servicioproductoproveedor.delete(objpv);
            return "redirect:/productoproveedor/mostrarproductoproveedor?deshabilitado";
        } catch (Exception e) {
            return "redirect:/productoproveedor/mostrarproductoproveedor?nodeshabilitado";

        }
    }
    @GetMapping("/ordencompra/deshabilitarordencompra/{id}")
    public String DeshabilitarOrdenCompra(@PathVariable Long id, Model modelo) {
        try {
            OrdenCompraEntity objOrdencompra = servicioordencompra.findById(id).get();
            servicioordencompra.delete(objOrdencompra);
            return "redirect:/ordencompra/mostrarordencompra?deshabilitado";
        } catch (Exception e) {
            return "redirect:/ordencompra/mostrarordencompra?nodeshabilitado";
        }
    }
    @GetMapping("/guiaentrada/deshabilitarguiaentrada/{id}")
    public String DeshabilitarGuiaEntrada(@PathVariable Long id, Model modelo) {
        try {
            GuiaEntradaEntity objGuiaEntrada = servicioguiaentrada.findById(id).get();
            servicioguiaentrada.delete(objGuiaEntrada);
            return "redirect:/guiaentrada/mostrarguiaentrada?deshabilitado";
        } catch (Exception e) {
            return "redirect:/guiaentrada/mostrarguiaentrada?nodeshabilitado";
        }
    }
    @GetMapping("/venta/control/deshabilitarventa/{id}")
    public String DeshabilitarVenta(@PathVariable Long id, Model modelo) {
        try {
            VentaEntity objVenta = servicioventa.findById(id).get();
            servicioventa.delete(objVenta);
            return "redirect:/venta/mostrarventa?deshabilitado";
        } catch (Exception e) {
            return "redirect:/venta/mostrarventa?nodeshabilitado";
        }
    }
    @GetMapping("/proveedor/deshabilitarproveedor/{id}")
    public String DesabilitarProveedor(@PathVariable Long id, Model modelo) {
        try {
            ProveedorEntity objProveedor = servicioproveedor.findById(id).get();
            servicioproveedor.delete(objProveedor);
            return "redirect:/proveedor/mostrarproveedor?deshabilitado";
        } catch (Exception e) {
            return "redirect:/proveedor/mostrarproveedor?nodeshabilitado";
        }
    }
    // * DESHABILITAR ACCION --------------------- 🡱🡱🡱🡱🡱🡱🡱🡱

    ////////

}
