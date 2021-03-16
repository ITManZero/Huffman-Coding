package ite.man.huffmanCoding.util;

public enum Units {
    B {
        @Override
        public long getRatio() {
            return 1;
        }
    },
    KB {
        @Override
        public long getRatio() {
            return 1024;
        }
    }, MB {
        @Override
        public long getRatio() {
            return 1024 * 1024;
        }
    }, GB {
        @Override
        public long getRatio() {
            return 1024 * 1024 * 1024;
        }
    };

    public abstract long getRatio();
}
