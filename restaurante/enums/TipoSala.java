public enum TipoSala {

    NORMAL {
        @Override
        public int getFilas() {
            return 5;
        }

        @Override
        public int getColumnas() {
            return 6;
        }

        @Override
        public int getPrecioMesaEuros() {
            return 0;
        }
    },
    PREMIUM {
        @Override
        public int getFilas() {
            return 4;
        }
        
        @Override
        public int getColumnas() {
            return 3;
        }

        @Override
        public int getPrecioMesaEuros() {
            return 10;
        }
    };

    public abstract int getColumnas();
    public abstract int getFilas();
    public abstract int getPrecioMesaEuros();
}
