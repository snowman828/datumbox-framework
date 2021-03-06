/**
 * Copyright (C) 2013-2017 Vasilis Vryniotis <bbriniotis@datumbox.com>
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
package com.datumbox.framework.lib;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.Manifest;

/**
 * Main class of the Framework.
 * 
 * @author Vasilis Vryniotis <bbriniotis@datumbox.com>
 */
public class Datumbox {

    /**
     * It prints on stdout the Version and Build of the Framework.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String[] versionInfo = getVersionBuild();
        System.out.println("Datumbox Machine Learning Framework "+versionInfo[0]+" "+versionInfo[1]);
    }
    
    /**
     * Gets the Version and Build of the Framework from the manifest file.
     * 
     * @return 
     */
    private static String[] getVersionBuild() {
        String version = null;
        String build = null;
        
        URLClassLoader cl = (URLClassLoader) Datumbox.class.getClassLoader();
        URL url = cl.findResource("META-INF/MANIFEST.MF");
        if (url!=null) {
            try (InputStream in = url.openStream()) {
                Manifest manifest = new Manifest(in);
                version = manifest.getMainAttributes().getValue("Implementation-Version");
                build = manifest.getMainAttributes().getValue("Implementation-Build");
            } 
            catch (IOException ex) {
                throw new UncheckedIOException(ex);
            }
        }
        return new String[]{version, build};
    }
}
