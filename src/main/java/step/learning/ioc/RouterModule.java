package step.learning.ioc;

import com.google.inject.servlet.ServletModule;
import step.learning.filters.AuthFilter;
import step.learning.filters.DbFilter;
import step.learning.servlets.*;

public class RouterModule extends ServletModule {
    @Override
    protected void configureServlets() {
        // filter("/*").through( DbFilter.class );
        /*
        Фільтри спрацьовують для всіх запитів, у т.ч. до ресурсних
        (до CSS, JS тощо). Перевірка підключення БД для них непотрібна
        і навіть шкідлива (при збоях підключення ресурси також недоступні)
         */
        filterRegex("^/(?!css/.+|js/.+|img/.+|upload/.+).*$").through( DbFilter.class );
        filterRegex("^/(?!css/.+|js/.+|img/.+|upload/.+).*$").through( AuthFilter.class );

        serve("/"      ).with( HomeServlet.class    ) ;
        serve("/auth"  ).with( AuthServlet.class    ) ;
        serve("/ioc"   ).with( IocServlet.class     ) ;
        serve("/news").with( NewsServlet.class    ) ;
        serve("/signup").with( SignupServlet.class  ) ;
        serve("/privacy").with( PrivacyServlet.class  ) ;
    }
}
