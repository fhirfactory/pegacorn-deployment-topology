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
package net.fhirfactory.pegacorn.deployment.topology.map.defaultmap;

import java.util.ArrayList;
import net.fhirfactory.pegacorn.petasos.model.resilience.mode.ConcurrencyModeEnum;
import net.fhirfactory.pegacorn.petasos.model.resilience.mode.ResilienceModeEnum;
import net.fhirfactory.pegacorn.petasos.model.topology.NodeElementTypeEnum;
import net.fhirfactory.pegacorn.deployment.topology.map.model.DeploymentMapNodeElement;

/**
 *
 * @author Mark A. Hunter
 */
public class BuildHestiaMap {

    String nodeHestia = "Hestia";
    String nodeHestiaAudit = "audit-hestia";
    String nodeHestiaDAM = "dam-hestia";

    public void createHestiaNode(DeploymentMapNodeElement solutionNode) {

        DeploymentMapNodeElement hestiaInstance = new DeploymentMapNodeElement();
        hestiaInstance.setConcurrencyMode(ConcurrencyModeEnum.CONCURRENCY_MODE_STANDALONE);
        hestiaInstance.setElementVersion("0.0.1");
        hestiaInstance.setInstanceName(nodeHestia);
        hestiaInstance.setFunctionName(nodeHestia);
        hestiaInstance.setResilienceMode(ResilienceModeEnum.RESILIENCE_MODE_STANDALONE);
        hestiaInstance.setTopologyElementType(NodeElementTypeEnum.SUBSYSTEM);
        hestiaInstance.setContainedElements(new ArrayList<DeploymentMapNodeElement>());
        solutionNode.getContainedElements().add(hestiaInstance);
        createHestiaServices(hestiaInstance);
    }

    public void createHestiaServices(DeploymentMapNodeElement hestiaNode) {
        DeploymentMapNodeElement hestiaDAMInstance = new DeploymentMapNodeElement();
        hestiaDAMInstance.setConcurrencyMode(ConcurrencyModeEnum.CONCURRENCY_MODE_STANDALONE);
        hestiaDAMInstance.setElementVersion("0.0.1");
        hestiaDAMInstance.setInstanceName(nodeHestiaDAM);
        hestiaDAMInstance.setFunctionName(nodeHestiaDAM);
        hestiaDAMInstance.setResilienceMode(ResilienceModeEnum.RESILIENCE_MODE_STANDALONE);
        hestiaDAMInstance.setTopologyElementType(NodeElementTypeEnum.SUBSYSTEM);
        hestiaDAMInstance.setContainedElements(new ArrayList<DeploymentMapNodeElement>());
        hestiaNode.getContainedElements().add(hestiaDAMInstance);

        DeploymentMapNodeElement hestiaAduditInstance = new DeploymentMapNodeElement();
        hestiaAduditInstance.setConcurrencyMode(ConcurrencyModeEnum.CONCURRENCY_MODE_STANDALONE);
        hestiaAduditInstance.setElementVersion("0.0.1");
        hestiaAduditInstance.setInstanceName(nodeHestiaAudit);
        hestiaAduditInstance.setFunctionName(nodeHestiaAudit);
        hestiaAduditInstance.setResilienceMode(ResilienceModeEnum.RESILIENCE_MODE_STANDALONE);
        hestiaAduditInstance.setTopologyElementType(NodeElementTypeEnum.SUBSYSTEM);
        hestiaAduditInstance.setContainedElements(new ArrayList<DeploymentMapNodeElement>());
        hestiaNode.getContainedElements().add(hestiaAduditInstance);
    }
}
