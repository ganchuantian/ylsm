package com.ylsm.constants;

public interface Constants {

    public enum ApiResultStatus {
        SUCCESS("S"), ERROR("E");
        private final String key;

        ApiResultStatus(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }
    }

}
