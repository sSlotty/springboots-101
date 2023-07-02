package com.thanathip.training.backend.exception;

public class FileException extends BaseException {

    public FileException(String message) {
        super("file exception: " + message);
    }

    public static FileException fileNull() {
        return new FileException("file.null");
    }

    public static FileException fileSizeTooLarge() {
        return new FileException("file.size.toolarge");
    }

    public static FileException fileExtensionNotSupport() {
        return new FileException("file.extension.notsupport");
    }
}
