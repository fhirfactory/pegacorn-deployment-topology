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

import net.fhirfactory.pegacorn.deployment.topology.map.achetypes.FHIRPitExternalisedService;
import net.fhirfactory.pegacorn.petasos.model.resilience.mode.ConcurrencyModeEnum;
import net.fhirfactory.pegacorn.petasos.model.resilience.mode.ResilienceModeEnum;
import net.fhirfactory.pegacorn.petasos.model.topology.NodeElementTypeEnum;
import net.fhirfactory.pegacorn.deployment.topology.map.model.DeploymentMapNodeElement;

/**
 *
 * @author Mark A. Hunter
 */
public class BuildFHIRPitMap extends FHIRPitExternalisedService {

    String nodeFHIRPitText = "FHIRPit";
    String nodeFHIRPitGen0Text = "gen0-fhirbreak";
    String NodeFHIRPitGen1Text = "gen1-fhirbreak";

    @Override
    public void buildExternalisedServiceNode(DeploymentMapNodeElement fhirpitNode) {
        DeploymentMapNodeElement fhirpitGen0ServiceNode = new DeploymentMapNodeElement();
        fhirpitGen0ServiceNode.setConcurrencyMode(ConcurrencyModeEnum.CONCURRENCY_MODE_STANDALONE);
        fhirpitGen0ServiceNode.setElementVersion("0.0.1");
        fhirpitGen0ServiceNode.setInstanceName(nodeFHIRPitGen0Text);
        fhirpitGen0ServiceNode.setFunctionName(nodeFHIRPitGen0Text);
        fhirpitGen0ServiceNode.setResilienceMode(ResilienceModeEnum.RESILIENCE_MODE_STANDALONE);
        fhirpitGen0ServiceNode.setTopologyElementType(NodeElementTypeEnum.EXTERNALISED_SERVICE);
        fhirpitGen0ServiceNode.setContainedElements(new ArrayList<DeploymentMapNodeElement>());
        fhirpitNode.getContainedElements().add(fhirpitGen0ServiceNode);

        DeploymentMapNodeElement fhirpitGen1ServiceNode = new DeploymentMapNodeElement();
        fhirpitGen1ServiceNode.setConcurrencyMode(ConcurrencyModeEnum.CONCURRENCY_MODE_STANDALONE);
        fhirpitGen1ServiceNode.setElementVersion("0.0.1");
        fhirpitGen1ServiceNode.setInstanceName(NodeFHIRPitGen1Text);
        fhirpitGen1ServiceNode.setFunctionName(NodeFHIRPitGen1Text);
        fhirpitGen1ServiceNode.setResilienceMode(ResilienceModeEnum.RESILIENCE_MODE_STANDALONE);
        fhirpitGen1ServiceNode.setTopologyElementType(NodeElementTypeEnum.EXTERNALISED_SERVICE);
        fhirpitGen1ServiceNode.setContainedElements(new ArrayList<DeploymentMapNodeElement>());
        fhirpitNode.getContainedElements().add(fhirpitGen1ServiceNode);
    }

    @Override
    public Set<DeploymentMapNodeElement> buildConnectedSystemSet() {
        return(new HashSet<DeploymentMapNodeElement>());
    }

}
