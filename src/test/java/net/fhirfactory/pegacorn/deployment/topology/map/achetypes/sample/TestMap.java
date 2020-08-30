/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.fhirfactory.pegacorn.deployment.topology.map.achetypes.sample;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import net.fhirfactory.pegacorn.common.model.FDN;
import net.fhirfactory.pegacorn.common.model.RDN;
import net.fhirfactory.pegacorn.deployment.topology.map.achetypes.PegacornSolution;
import net.fhirfactory.pegacorn.petasos.model.resilience.mode.ConcurrencyModeEnum;
import net.fhirfactory.pegacorn.petasos.model.resilience.mode.ResilienceModeEnum;
import net.fhirfactory.pegacorn.petasos.model.topology.NodeElementTypeEnum;
import net.fhirfactory.pegacorn.deployment.topology.map.model.DeploymentMapNodeElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author mhunter
 */

@ApplicationScoped
public class TestMap extends PegacornSolution {
    private static final Logger LOG = LoggerFactory.getLogger(TestMap.class);

    @Override
    protected String specifySolutionName() {
        return("PegacornTestSystem");
    }

    @Override
    protected String specifySolutionVersion() {
        return ("0.0.1");
    }

    @Override
    protected ResilienceModeEnum specifyResilienceMode() {
        return (ResilienceModeEnum.RESILIENCE_MODE_STANDALONE);
    }

    @Override
    protected void specifyCommunicateExternalisedServices(DeploymentMapNodeElement subsystem) {
        BuildCommunicateMap communicateBuilder = new BuildCommunicateMap();
        communicateBuilder.buildExternalisedServiceNode(subsystem);
    }

    @Override
    protected void specifyMITaFExternalisedServices(DeploymentMapNodeElement subsystem) {
        BuildMITaFMap mitafBuilder = new BuildMITaFMap();
        mitafBuilder.buildExternalisedServiceNode(subsystem);

    }

    @Override
    protected void specifyFHIRPlaceExternalisedServices(DeploymentMapNodeElement subsystem) {
        BuildFHIRPlaceMap fhirPlaceBuilder = new BuildFHIRPlaceMap();
        fhirPlaceBuilder.buildExternalisedServiceNode(subsystem);
    }

    @Override
    protected void specifyFHIRPitExternalisedServices(DeploymentMapNodeElement subsystem) {
        BuildFHIRPitMap fhirpitBuilder = new BuildFHIRPitMap();
        fhirpitBuilder.buildExternalisedServiceNode(subsystem);
    }

    @Override
    protected void specifyFHIRViewExternalisedServices(DeploymentMapNodeElement subsystem) {
        BuildFHIRViewMap fhirviewBuilder = new BuildFHIRViewMap();
        fhirviewBuilder.buildExternalisedServiceNode(subsystem);
    }

    @Override
    protected void specifyLadonExternalisedServices(DeploymentMapNodeElement subsystem) {
        BuildLadonMap ladonBuilder =  new BuildLadonMap();
        ladonBuilder.buildExternalisedServiceNode(subsystem);
    }

    @Override
    protected void specifyHestiaExternalisedServices(DeploymentMapNodeElement subsystem) {
        BuildHestiaMap hestiaBuilder = new BuildHestiaMap();
        hestiaBuilder.buildExternalisedServiceNode(subsystem);
    }

    @Override
    protected void specifyFHIRBreakExternalisedServices(DeploymentMapNodeElement subsystem) {
        BuildFHIRBreakMap fhirBreakBuilder = new BuildFHIRBreakMap();
        fhirBreakBuilder.buildExternalisedServiceNode(subsystem);
    }

    @Override
    protected Set<DeploymentMapNodeElement> specifyConnectedSystems() {
        return(new HashSet<DeploymentMapNodeElement>());
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
