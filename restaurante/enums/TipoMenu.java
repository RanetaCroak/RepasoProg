public enum TipoMenu {

    VEGANO {

        @Override
        public int getPrecioEuros() {
            return 15;
        }

    },
    NORMAL {

        @Override
        public int getPrecioEuros() {
            return 10;
        }

    };

    public abstract int getPrecioEuros();
}
