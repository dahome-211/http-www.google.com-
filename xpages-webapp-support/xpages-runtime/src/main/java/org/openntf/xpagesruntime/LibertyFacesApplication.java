/**
 * Copyright © 2019 Jesse Gallagher
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openntf.xpagesruntime;

import com.ibm.xsp.application.ApplicationExImpl;
import com.ibm.xsp.application.DesignerApplicationEx;
import com.ibm.xsp.application.ViewHandlerEx;
import com.ibm.xsp.application.ViewHandlerExImpl;
import com.ibm.xsp.factory.FactoryLookup;
import com.ibm.xsp.library.FacesClassLoader;
import com.ibm.xsp.page.FacesPageDriver;

import javax.faces.application.Application;
import javax.faces.application.ViewHandler;

import org.openntf.xpagesruntime.xsp.LibertyFacesClassLoader;
import org.openntf.xpagesruntime.xsp.LibertyFacesPageDriver;
import org.openntf.xpagesruntime.xsp.LibertyFactoryLookup;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class LibertyFacesApplication extends DesignerApplicationEx {
    private FactoryLookup factoryLookup;

    protected LibertyFacesApplication(Application application) {
        super(application);

        try {
            Field factoryLookupField = ApplicationExImpl.class.getDeclaredField("_factoryLookup");
            factoryLookupField.setAccessible(true);
            factoryLookupField.set(this, getFactoryLookup());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ViewHandler getViewHandler() {
        ViewHandler sup = super.getViewHandler();
        if(!(sup instanceof ViewHandlerEx)) {
            FacesClassLoader cl = new LibertyFacesClassLoader(getDesignerApplication());
            FacesPageDriver driver = new LibertyFacesPageDriver(cl);
            ViewHandlerExImpl result = new ViewHandlerExImpl(sup);
            result.setPageDriver(driver);
            return result;
        } else {
            return sup;
        }
    }

    @Override
    public FactoryLookup getFactoryLookup() {
        if(this.factoryLookup == null) {
            this.factoryLookup = new LibertyFactoryLookup(super.getFactoryLookup());
        }
        return this.factoryLookup;
    }

    @Override
    public String getApplicationProperty(String s, String s1) {
        String prop = super.getApplicationProperty(s, s1);
        return prop;
    }

    @Override
    protected ResourceBundle loadResourceBundle(String s, Locale locale) throws IOException {
        return super.loadResourceBundle(s, locale);
    }

    @Override
    public List<?> findServices(String s) {
        List<?> svc = super.findServices(s);
        return svc;
    }



}