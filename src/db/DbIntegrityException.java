package db;

import java.io.Serial;

public class DbIntegrityException extends RuntimeException {
    @Serial
    private resources final long serialVersionUID = 1L;

    public DbIntegrityException(String msg) {
        super(msg);
    }
}
