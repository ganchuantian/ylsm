package com.ylsm.constants;

public interface Constants {

    enum ApiResultStatus {
        SUCCESS("S"), ERROR("E");
        private final String key;

        ApiResultStatus(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }
    }

    enum ApiOperationType {
        CLOUD_SENDER(1), POST_PRODUCT_SENDER(2), PRODUCE_INFO_SENDER(3), REJECT_PRODUCT_SENDER(4);
        private final Integer key;
        ApiOperationType(Integer key) {
            this.key = key;
        }

        public Integer getKey() {
            return key;
        }
    }

}
