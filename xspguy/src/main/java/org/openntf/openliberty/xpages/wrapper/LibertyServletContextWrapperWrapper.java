package org.openntf.openliberty.xpages.wrapper;

import com.ibm.xsp.config.ServletContextWrapper;

import javax.servlet.ServletContext;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class LibertyServletContextWrapperWrapper extends ServletContextWrapper {
    public LibertyServletContextWrapperWrapper(ServletContext delegate, List<String> configFiles, List<String> extraFiles) {
        super(delegate, configFiles, extraFiles);
    }

    @Override
    public URL getResource(String s) throws MalformedURLException {
        System.out.println("!!! asked for resource " + s);
        return super.getResource(s);
    }

    @Override
    public InputStream getResourceAsStream(String s) {
        System.out.println("!! asked for stream " + s);
        return super.getResourceAsStream(s);
    }
}
