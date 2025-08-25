package types;

public enum TipoCuenta {
    AHORRO,
    CORRIENTE,;

    public static TipoCuenta fromInt(int opcion) {
        switch (opcion) {
            case 1: return AHORRO;
            case 2: return CORRIENTE;
            default: return null;
        }
    }
}
