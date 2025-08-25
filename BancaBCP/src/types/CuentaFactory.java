package types;

public class CuentaFactory {
    public static CuentaBancaria crearCuenta(TipoCuenta tipo, double saldoInicial){
        switch (tipo) {
            case AHORRO:
                return new CuentaAhorro(saldoInicial, TipoCuenta.AHORRO);
            case CORRIENTE:
                return new CuentaCorriente(saldoInicial, TipoCuenta.CORRIENTE);
            default:
                throw new IllegalArgumentException("Tipo de cuenta no reconocido: " + tipo);
        }
    }
}
