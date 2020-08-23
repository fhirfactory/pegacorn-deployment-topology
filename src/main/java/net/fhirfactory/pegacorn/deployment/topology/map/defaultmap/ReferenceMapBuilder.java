/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.fhirfactory.pegacorn.deployment.topology.map.defaultmap;

import java.util.ArrayList;
import net.fhirfactory.pegacorn.common.model.FDN;
import net.fhirfactory.pegacorn.common.model.RDN;
import net.fhirfactory.pegacorn.petasos.model.resilience.mode.ConcurrencyModeEnum;
import net.fhirfactory.pegacorn.petasos.model.resilience.mode.ResilienceModeEnum;
import net.fhirfactory.pegacorn.petasos.model.topology.NodeElementTypeEnum;
import net.fhirfactory.pegacorn.deployment.topology.map.model.DeploymentMapNodeElement;

/**
 *
 * @author mhunter
 */
public class ReferenceMapBuilder {

    DeploymentMapNodeElement solutionNode;
    
    public ReferenceMapBuilder(){
        solutionNode = new DeploymentMapNodeElement();
    }

    public DeploymentMapNodeElement getSolutionNode() {
        return solutionNode;
    }

    public void setSolutionNode(DeploymentMapNodeElement solutionNode) {
        this.solutionNode = solutionNode;
    }
    
        public void createSolutionNode(String solutionName) {
        this.solutionNode = new DeploymentMapNodeElement();
        this.solutionNode.setTopologyElementType(NodeElementTypeEnum.SOLUTION);
        FDN solutionFDN = new FDN();
        solutionFDN.appendRDN(new RDN(NodeElementTypeEnum.SOLUTION.getNodeElementType(), solutionName));
        this.solutionNode.setInstanceName(solutionName);
        this.solutionNode.setFunctionName(solutionName);
        this.solutionNode.setConcurrencyMode(ConcurrencyModeEnum.CONCURRENCY_MODE_STANDALONE);
        this.solutionNode.setResilienceMode(ResilienceModeEnum.RESILIENCE_MODE_STANDALONE);
        this.solutionNode.setElementVersion("0.0.1");
        this.solutionNode.setContainedElements(new ArrayList<DeploymentMapNodeElement>());
    }

    public void createSubSystemNodes() {

        BuildCommunicateMap communicateBuilder = new BuildCommunicateMap();
        communicateBuilder.createCommunicateNode(solutionNode);

        BuildHestiaMap hestiaBuilder = new BuildHestiaMap();
        hestiaBuilder.createHestiaNode(solutionNode);

        BuildFHIRBreakMap fhirBreakBuilder = new BuildFHIRBreakMap();
        fhirBreakBuilder.createFHIRBreakNode(solutionNode);

        BuildFHIRViewMap fhirviewBuilder = new BuildFHIRViewMap();
        fhirviewBuilder.createFHIRViewNode(solutionNode);

        BuildFHIRPlaceMap fhirPlaceBuilder = new BuildFHIRPlaceMap();
        fhirPlaceBuilder.createFHIRPlaceNode(solutionNode);

        BuildFHIRPitMap fhirpitBuilder = new BuildFHIRPitMap();
        fhirpitBuilder.createFHIRPitNode(solutionNode);
        
        BuildLadonMap ladonBuilder =  new BuildLadonMap();
        ladonBuilder.createLadonNode(solutionNode);
        
        BuildMITaFMap mitafBuilder = new BuildMITaFMap();
        mitafBuilder.createMITaFNode(solutionNode);

        DeploymentMapNodeElement itopsInstance = new DeploymentMapNodeElement();
        itopsInstance.setConcurrencyMode(ConcurrencyModeEnum.CONCURRENCY_MODE_STANDALONE);
        itopsInstance.setElementVersion("0.0.1");
        itopsInstance.setInstanceName("ITOps");
        itopsInstance.setFunctionName("ITOps");
        itopsInstance.setResilienceMode(ResilienceModeEnum.RESILIENCE_MODE_STANDALONE);
        itopsInstance.setTopologyElementType(NodeElementTypeEnum.SUBSYSTEM);
        itopsInstance.setContainedElements(new ArrayList<DeploymentMapNodeElement>());
        this.solutionNode.getContainedElements().add(itopsInstance);
    }

}
