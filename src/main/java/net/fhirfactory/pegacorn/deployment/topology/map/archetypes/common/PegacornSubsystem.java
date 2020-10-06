/*
 * The MIT License
 *
 * Copyright 2020 Mark A. Hunter.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package net.fhirfactory.pegacorn.deployment.topology.map.archetypes.common;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import net.fhirfactory.pegacorn.deployment.topology.map.model.DeploymentMapNodeElement;
import net.fhirfactory.pegacorn.util.PegacornProperties;

/**
 * 
 * @author Mark A Hunter
 *
 */
public abstract class PegacornSubsystem {

    abstract public void buildSubsystemNode(DeploymentMapNodeElement subsystem);
    abstract public Set<DeploymentMapNodeElement> buildConnectedSystemSet();
    
    private static final Integer DEFAULT_EXTERNAL_PORT = Integer.valueOf(443);
   
    public String getProperty(String propertyName, String defaultValue) {
        return PegacornProperties.getProperty(propertyName, defaultValue);        
    }
    
    public Integer getProperty(String propertyName, Integer defaultValue) {
        return PegacornProperties.getIntegerProperty(propertyName, defaultValue);        
    }

    public String getMandatoryProperty(String propertyName) {
        return PegacornProperties.getMandatoryProperty(propertyName);        
    }
        
    /**
     * @param defaultValue
     * @return the external to ACT Health DNS entry
     */
    public String getExternalDNSEntry(String defaultValue) {
        return getProperty("EXTERNAL_DNS_ENTRY", getDNSEntry(defaultValue));
    }

    /**
     * @return the external to ACT Health port
     */
    public Integer getExternalPort() {
        return getExternalPort(DEFAULT_EXTERNAL_PORT);
    }
    public Integer getExternalPort(Integer defaultValue) {
        return getProperty("EXTERNAL_PORT", defaultValue);
    }

    public String getDNSEntry(String defaultValue) {
        return getProperty("DNS_ENTRY", getDNSEntryInsideKubernetes(defaultValue));
    }

    public String getDNSEntryInsideKubernetes(String defaultValue) {
        String value = getProperty("KUBERNETES_SERVICE_NAME", StringUtils.EMPTY);
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }                
        return value + "." + getMandatoryProperty("KUBERNETES_NAMESPACE");
    }

    /**
     * @param defaultValue
     * @return the exposed kubernetes service port
     */
    public Integer getBasePort(Integer defaultValue) {
        return getProperty("BASE_PORT", defaultValue);
    }
    
    /**
     * @param defaultValue
     * @return the internal port used on the Pod/Container
     */
    public Integer getBasePortInsidePod(Integer defaultValue) {
        return getProperty("BASE_PORT_INSIDE_POD", getEdgeReceiverBasePort(defaultValue));
    }
        
    /**
     * @param defaultValue
     * @return the exposed kubernetes service port
     */
    public Integer getEdgeReceiverBasePort(Integer defaultValue) {
        return getProperty("EDGE_RECEIVER_BASE_PORT", defaultValue);
    }
    
    /**
     * @param defaultValue
     * @return the internal port used on the Pod/Container
     */
    public Integer getEdgeReceiverBasePortInsidePod(Integer defaultValue) {
        return getProperty("EDGE_RECEIVER_BASE_PORT_INSIDE_POD", getEdgeReceiverBasePort(defaultValue));
    }
    
    /**
     * @param defaultValue
     * @return the exposed kubernetes service port
     */
    public Integer getPetasosBasePort(Integer defaultValue) {
        return getProperty("PETASOS_BASE_PORT", defaultValue);
    }
    
    /**
     * @param defaultValue
     * @return the internal port used on the Pod/Container
     */
    public Integer getPetasosBasePortInsidePod(Integer defaultValue) {
        return getProperty("PETASOS_BASE_PORT_INSIDE_POD", getPetasosBasePort(defaultValue));
    }    
}
