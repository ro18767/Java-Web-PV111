package step.learning.ioc;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import java.sql.*;

import step.learning.services.db.DbService;
import step.learning.services.db.MariaDbService;
import step.learning.services.form_parse.*;
import step.learning.services.hash.*;
import step.learning.services.kdf.*;
import step.learning.services.rnd.*;

public class ServiceModule extends AbstractModule  {
    @Override
    protected void configure() {
        bind(HashService.class).to(Md5HashService.class);
        bind(CodeGenerator.class).to(DigitCodeGenerator.class);
        bind(FormParseService.class).to(CommonsFormParseService.class);
        bind(KdfService.class).to(HashKdfService.class);
        bind(DbService.class).to(MariaDbService.class);
    }

}
