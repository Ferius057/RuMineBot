package kz.ferius_057.ruminebot.database;

import java.sql.SQLException;

/**
 * @author whilein
 */
public interface ThrowableFunction<F, T, E extends Throwable> {

    T apply(F value) throws E;

}
