public enum TipoMenu {

    VEGANO {

        @Override
        public int getPrecioEuros() {
            return 15;
        }

        @Override
        public String toString() {
            return "vegano";
        }

    },
    NORMAL {

        @Override
        public int getPrecioEuros() {
            return 10;
        }

        @Override
        public String toString() {
            return "normal";
        }

    };

    public abstract int getPrecioEuros();
}
