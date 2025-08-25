import repository.ClienteRepository;
import repository.ClienteRepositoryImpl;
import service.CuentaService;
import service.CuentaServiceImpl;
import types.Cliente;
import types.CuentaBancaria;
import types.TipoCuenta;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

    private static final ClienteRepository clienteRepository = new ClienteRepositoryImpl();
    private static final CuentaService cuentaService = new CuentaServiceImpl();

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            boolean salir = false;

            while (!salir) {
                int opcion = pedirOpcionValida(sc);

                switch (opcion) {
                    case 1:
                        abrirCuenta(sc);
                        break;
                    case 2:
                        depositar(sc);
                        break;
                    case 3:
                        retirar(sc);
                        break;
                    case 4:
                        consultarSaldo(sc);
                        break;
                    case 5:
                        System.out.println("Gracias por usar el sistema. ¡Adiós!");
                        salir = true;
                        break;
                    default:
                        System.out.println("Ocurrió un error inesperado.");
                }
            }
        }
    }

    private static void abrirCuenta(Scanner sc) {
        System.out.print("Ingrese su número de DNI: ");
        String dni = sc.nextLine().trim();

        Optional<Cliente> optCliente = clienteRepository.findByDni(dni);

        Cliente cliente = optCliente.orElseGet(() -> {
            System.out.println("¡Cliente no existe! Registrate");

            System.out.print("Ingrese nombre: ");
            String nombre = sc.nextLine().trim();
            System.out.print("Ingrese apellido: ");
            String apellido = sc.nextLine().trim();
            System.out.print("Ingrese email: ");
            String email = sc.nextLine().trim();

            Cliente nuevo = new Cliente(nombre, apellido, dni, email);
            clienteRepository.save(nuevo);
            return nuevo;
        });

        CuentaBancaria cuenta = crearCuentaInteractiva(sc);
        cliente.agregarCuenta(cuenta);

        System.out.printf("Cuenta creada con éxito para %s %s%n",
                cliente.getNombre(), cliente.getApellido());
    }

    private static void depositar(Scanner sc) {
        System.out.print("Ingrese número de cuenta a depositar: ");
        String numeroCuenta = sc.nextLine().trim();
        System.out.print("Ingrese monto a depositar: ");
        double montoDep = Double.parseDouble(sc.nextLine().trim());
        System.out.printf("Depositando %.2f en cuenta %s%n", montoDep, numeroCuenta);
    }

    private static void retirar(Scanner sc) {
        System.out.print("Ingrese número de cuenta a retirar: ");
        String numRet = sc.nextLine().trim();
        System.out.print("Ingrese monto a retirar: ");
        double montoRet = Double.parseDouble(sc.nextLine().trim());
        System.out.printf("Retirando %.2f de cuenta %s%n", montoRet, numRet);
    }

    private static void consultarSaldo(Scanner sc) {
        System.out.print("Ingrese su número de DNI: ");
        String dni = sc.nextLine().trim();

        Optional<Cliente> optCliente = clienteRepository.findByDni(dni);

        if (!optCliente.isPresent()) {
            System.out.println("No existe cliente con DNI: " + dni);
            return;
        }

        Cliente cliente = optCliente.get();
        List<CuentaBancaria> cuentas = cliente.getCuentas();

        if (cuentas.isEmpty()) {
            System.out.println("El cliente no tiene cuentas registradas.");
            return;
        }

        System.out.println("Cuentas asociadas al cliente " + cliente.getNombre() + " " + cliente.getApellido() + ":");

        IntStream.range(0, cuentas.size())
                .forEach(i -> {
                    CuentaBancaria cuenta = cuentas.get(i);
                    System.out.printf("%d. %s (Saldo: %.2f)%n",
                            i + 1,
                            cuenta.getNumeroCuenta(),
                            cuenta.getSaldo());
                });
    }

    /** Método que encapsula la lógica de creación de cuentas */
    private static CuentaBancaria crearCuentaInteractiva(Scanner sc) {
        while (true) {
            System.out.println("Seleccione tipo de cuenta:");
            System.out.println("1. Cuenta AHORRO");
            System.out.println("2. Cuenta CORRIENTE");

            try {
                int opc = Integer.parseInt(sc.nextLine().trim());
                if (opc == 1 || opc == 2) {
                    return cuentaService.crearCuenta(TipoCuenta.fromInt(opc), 0);
                } else {
                    System.out.println("Opción inválida. Intente otra vez.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Debe ingresar un número.");
            }
        }
    }

    public static int pedirOpcionValida(Scanner sc) {
        while (true) {
            System.out.println("Opciones:");
            System.out.println("1. Abrir cuenta bancaria");
            System.out.println("2. Depositar Dinero");
            System.out.println("3. Retirar Dinero");
            System.out.println("4. Consultar Saldo");
            System.out.println("5. Salir");
            System.out.print("Ingresa la opción: ");

            String linea = sc.nextLine().trim();
            try {
                int opcion = Integer.parseInt(linea);
                if (opcion >= 1 && opcion <= 5) return opcion;
                System.out.println("¡Opción NO válida! Intente de nuevo.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Debes ingresar un número.");
            }
        }
    }
}
