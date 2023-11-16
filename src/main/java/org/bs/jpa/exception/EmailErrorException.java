package org.bs.jpa.exception;

public class EmailErrorException {

        // 이메일  에러
        public static class emailError extends RuntimeException {

            public emailError(String msg) {
                super(msg);
            }
        }
    
}
