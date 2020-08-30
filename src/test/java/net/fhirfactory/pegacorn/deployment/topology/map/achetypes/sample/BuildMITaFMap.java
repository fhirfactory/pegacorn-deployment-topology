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
package net.fhirfactory.pegacorn.deployment.topology.map.achetypes.sample;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import net.fhirfactory.pegacorn.deployment.topology.map.achetypes.MITaFExternalisedService;
import net.fhirfactory.pegacorn.petasos.model.resilience.mode.ConcurrencyModeEnum;
import net.fhirfactory.pegacorn.petasos.model.resilience.mode.ResilienceModeEnum;
import net.fhirfactory.pegacorn.petasos.model.topology.NodeElementTypeEnum;
import net.fhirfactory.pegacorn.deployment.topology.map.model.DeploymentMapNodeElement;

/**
 *
 * @author Mark A. Hunter
 */
public class BuildMITaFMap extends MITaFExternalisedService {

    String nodeMITaFText = "MITaF";
    String nodeMITaFGen0Text = "gen0-mitaf";
    String nodeMITaFGen1Text = "gen1-mitaf";

    @Override
    public void buildExternalisedServiceNode(DeploymentMapNodeElement mitafNode) {
        DeploymentMapNodeElement mitafGen0ServiceNode = new DeploymentMapNodeElement();
        mitafGen0ServiceNode.setConcurrencyMode(ConcurrencyModeEnum.CONCURRENCY_MODE_STANDALONE);
        mitafGen0ServiceNode.setElementVersion("0.0.1");
        mitafGen0ServiceNode.setInstanceName(nodeMITaFGen0Text);
        mitafGen0ServiceNode.setFunctionName(nodeMITaFGen0Text);
        mitafGen0ServiceNode.setResilienceMode(ResilienceModeEnum.RESILIENCE_MODE_STANDALONE);
        mitafGen0ServiceNode.setTopologyElementType(NodeElementTypeEnum.EXTERNALISED_SERVICE);
        mitafGen0ServiceNode.setContainedElements(new ArrayList<DeploymentMapNodeElement>());
        mitafNode.getContainedElements().add(mitafGen0ServiceNode);

        DeploymentMapNodeElement mitafGen1ServiceNode = new DeploymentMapNodeElement();
        mitafGen1ServiceNode.setConcurrencyMode(ConcurrencyModeEnum.CONCURRENCY_MODE_STANDALONE);
        mitafGen1ServiceNode.setElementVersion("0.0.1");
        mitafGen1ServiceNode.setInstanceName(nodeMITaFGen1Text);
        mitafGen1ServiceNode.setFunctionName(nodeMITaFGen1Text);
        mitafGen1ServiceNode.setResilienceMode(ResilienceModeEnum.RESILIENCE_MODE_STANDALONE);
        mitafGen1ServiceNode.setTopologyElementType(NodeElementTypeEnum.EXTERNALISED_SERVICE);
        mitafGen1ServiceNode.setContainedElements(new ArrayList<DeploymentMapNodeElement>());
        mitafNode.getContainedElements().add(mitafGen1ServiceNode);
    }

    @Override
    public Set<DeploymentMapNodeElement> buildConnectedSystemSet() {
        return(new HashSet<DeploymentMapNodeElement>());
    }

}
