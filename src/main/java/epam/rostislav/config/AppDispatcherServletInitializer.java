package epam.rostislav.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import javax.servlet.*;

public class AppDispatcherServletInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {

        AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();
        webApplicationContext.register(AppSpringConfig.class);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(webApplicationContext);
        ServletRegistration.Dynamic appDispatcherServlet =
                servletContext.addServlet("AppDispatcherServlet", dispatcherServlet);

        appDispatcherServlet.setLoadOnStartup(1);
        appDispatcherServlet.addMapping("/*");
    }
}
