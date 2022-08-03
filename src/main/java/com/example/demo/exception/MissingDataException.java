package com.example.demo.exception;

public class MissingDataException extends Exception {

		public MissingDataException() {
		}

		public MissingDataException(String message) {
			super(message);
		}

		public MissingDataException(String message, Throwable cause) {
			super(message, cause);
		}

		public MissingDataException(Throwable cause) {
			super(cause);
		}

		public MissingDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
			super(message, cause, enableSuppression, writableStackTrace);
		}

}
