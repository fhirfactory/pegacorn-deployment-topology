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
package net.fhirfactory.pegacorn.deployment.topology.map.archetypes.sample;

import java.util.HashSet;
import java.util.Set;

import net.fhirfactory.pegacorn.deployment.topology.map.archetypes.CommunicatePegacornSubsystem;
import net.fhirfactory.pegacorn.petasos.model.resilience.mode.ConcurrencyModeEnum;
import net.fhirfactory.pegacorn.petasos.model.resilience.mode.ResilienceModeEnum;
import net.fhirfactory.pegacorn.petasos.model.topology.EndpointElementTypeEnum;
import net.fhirfactory.pegacorn.petasos.model.topology.NodeElementTypeEnum;
import net.fhirfactory.pegacorn.deployment.topology.map.model.DeploymentMapEndpointElement;
import net.fhirfactory.pegacorn.deployment.topology.map.model.DeploymentMapNodeElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Mark A. Hunter
 */
public class BuildCommunicateMap extends CommunicatePegacornSubsystem {
    private static final Logger LOG = LoggerFactory.getLogger(BuildCommunicateMap.class);

    String nodeCommunicate = "Comunicate";
    String nodeGrpServer = "Communicate-GrpChatSvr";
    String nodeAVConferenceServer = "Communicate-AVConfSvr";
    String nodeAVBridge = "Communicate-AVBridge";
    String nodeEcho = "Communicate-Echo";
    String nodeIris = "Communicate-Iris";


    @Override
    public void buildSubsystemNode(DeploymentMapNodeElement solutionNode) {
        LOG.debug(".buildSubsystemNode(): Entry");
        DeploymentMapNodeElement communicateNode = new DeploymentMapNodeElement();
        communicateNode.setConcurrencyMode(ConcurrencyModeEnum.CONCURRENCY_MODE_STANDALONE);
        communicateNode.setElementVersion("0.0.1");
        communicateNode.setInstanceName("Communicate");
        communicateNode.setFunctionName("Communicate");
        communicateNode.setResilienceMode(ResilienceModeEnum.RESILIENCE_MODE_STANDALONE);
        communicateNode.setTopologyElementType(NodeElementTypeEnum.EXTERNALISED_SERVICE);
        LOG.trace(".buildSubsystemNode(): Adding communicateNode (DeploymentMapNodeElement) --> {}", communicateNode);
        communicateNode.getContainedElements().add(communicateNode);
        buildExternalisedServiceNode(communicateNode);
    }

    public void buildExternalisedServiceNode(DeploymentMapNodeElement communicateNode) {
        LOG.debug(".buildExternalisedServiceNode(): Entry");
        DeploymentMapNodeElement echoServer = new DeploymentMapNodeElement();
        echoServer.setConcurrencyMode(ConcurrencyModeEnum.CONCURRENCY_MODE_STANDALONE);
        echoServer.setElementVersion("0.0.1");
        echoServer.setInstanceName(nodeEcho);
        echoServer.setFunctionName(nodeEcho);
        echoServer.setResilienceMode(ResilienceModeEnum.RESILIENCE_MODE_STANDALONE);
        echoServer.setTopologyElementType(NodeElementTypeEnum.EXTERNALISED_SERVICE);
        LOG.trace(".buildExternalisedServiceNode(): Adding echoServer (DeploymentMapNodeElement) --> {}", echoServer);
        communicateNode.getContainedElements().add(echoServer);

        DeploymentMapNodeElement avBridgeServer = new DeploymentMapNodeElement();
        avBridgeServer.setConcurrencyMode(ConcurrencyModeEnum.CONCURRENCY_MODE_STANDALONE);
        avBridgeServer.setElementVersion("0.0.1");
        avBridgeServer.setInstanceName(nodeAVBridge);
        avBridgeServer.setFunctionName(nodeAVBridge);
        avBridgeServer.setResilienceMode(ResilienceModeEnum.RESILIENCE_MODE_STANDALONE);
        avBridgeServer.setTopologyElementType(NodeElementTypeEnum.EXTERNALISED_SERVICE);
        LOG.trace(".buildExternalisedServiceNode(): Adding nodeAVBridge (DeploymentMapNodeElement) --> {}", nodeAVBridge);
        communicateNode.getContainedElements().add(avBridgeServer);

        DeploymentMapNodeElement avConfServerInstance = new DeploymentMapNodeElement();
        avConfServerInstance.setConcurrencyMode(ConcurrencyModeEnum.CONCURRENCY_MODE_STANDALONE);
        avConfServerInstance.setElementVersion("0.0.1");
        avConfServerInstance.setInstanceName(nodeAVConferenceServer);
        avConfServerInstance.setFunctionName(nodeAVConferenceServer);
        avConfServerInstance.setResilienceMode(ResilienceModeEnum.RESILIENCE_MODE_STANDALONE);
        avConfServerInstance.setTopologyElementType(NodeElementTypeEnum.EXTERNALISED_SERVICE);
        LOG.trace(".buildExternalisedServiceNode(): Adding nodeAVConferenceServer (DeploymentMapNodeElement) --> {}", nodeAVConferenceServer);
        communicateNode.getContainedElements().add(avConfServerInstance);

        DeploymentMapNodeElement grpServerInstane = new DeploymentMapNodeElement();
        grpServerInstane.setConcurrencyMode(ConcurrencyModeEnum.CONCURRENCY_MODE_STANDALONE);
        grpServerInstane.setElementVersion("0.0.1");
        grpServerInstane.setInstanceName(nodeGrpServer);
        grpServerInstane.setFunctionName(nodeGrpServer);
        grpServerInstane.setResilienceMode(ResilienceModeEnum.RESILIENCE_MODE_STANDALONE);
        grpServerInstane.setTopologyElementType(NodeElementTypeEnum.EXTERNALISED_SERVICE);
        LOG.trace(".buildExternalisedServiceNode(): Adding nodeGrpServer (DeploymentMapNodeElement) --> {}", nodeGrpServer);
        communicateNode.getContainedElements().add(grpServerInstane);

        DeploymentMapNodeElement irisInstance = new DeploymentMapNodeElement();
        irisInstance.setConcurrencyMode(ConcurrencyModeEnum.CONCURRENCY_MODE_STANDALONE);
        irisInstance.setElementVersion("0.0.1");
        irisInstance.setInstanceName(nodeIris);
        irisInstance.setFunctionName(nodeIris);
        irisInstance.setResilienceMode(ResilienceModeEnum.RESILIENCE_MODE_STANDALONE);
        irisInstance.setTopologyElementType(NodeElementTypeEnum.EXTERNALISED_SERVICE);
        LOG.trace(".buildExternalisedServiceNode(): Adding nodeIris (DeploymentMapNodeElement) --> {}", nodeIris);
        communicateNode.getContainedElements().add(irisInstance);
        createIrisSites(irisInstance);
    }

    public void createIrisSites(DeploymentMapNodeElement irisServiceNode) {
        DeploymentMapNodeElement nodeSiteA = new DeploymentMapNodeElement();
        nodeSiteA.setConcurrencyMode(ConcurrencyModeEnum.CONCURRENCY_MODE_STANDALONE);
        nodeSiteA.setElementVersion("0.0.1");
        nodeSiteA.setInstanceName("___");
        nodeSiteA.setResilienceMode(ResilienceModeEnum.RESILIENCE_MODE_STANDALONE);
        nodeSiteA.setTopologyElementType(NodeElementTypeEnum.SITE);
        irisServiceNode.getContainedElements().add(nodeSiteA);
        createIrisServices(nodeSiteA);
        /*        
        ConfigMapNodeElement nodeSiteB = new ConfigMapNodeElement();
        nodeSiteB.setConcurrencyMode(ConcurrencyModeEnum.CONCURRENCY_MODE_STANDALONE);
        nodeSiteB.setElementVersion("0.0.1");
        nodeSiteB.setInstanceName("SiteB");
        nodeSiteB.setResilienceMode(ResilienceModeEnum.RESILIENCE_MODE_STANDALONE);
        nodeSiteB.setTopologyElementType(NodeElementTypeEnum.SITE);
        irisServiceNode.getContainedElements().add(nodeSiteB);
        createIrisPlatforms(nodeSiteB); */
    }

    public void createIrisServices(DeploymentMapNodeElement irisSites) {
        DeploymentMapNodeElement nodeIrisServiceSiteA = new DeploymentMapNodeElement();
        nodeIrisServiceSiteA.setConcurrencyMode(ConcurrencyModeEnum.CONCURRENCY_MODE_STANDALONE);
        nodeIrisServiceSiteA.setElementVersion("0.0.1");
        nodeIrisServiceSiteA.setInstanceName(nodeIris);
        nodeIrisServiceSiteA.setFunctionName(nodeIris);
        nodeIrisServiceSiteA.setResilienceMode(ResilienceModeEnum.RESILIENCE_MODE_STANDALONE);
        nodeIrisServiceSiteA.setTopologyElementType(NodeElementTypeEnum.SERVICE);
        irisSites.getContainedElements().add(nodeIrisServiceSiteA);
        createIrisPlatforms(nodeIrisServiceSiteA);
        createIrisServiceEndpoints(nodeIrisServiceSiteA);
    }

    public void createIrisServiceEndpoints(DeploymentMapNodeElement irisServiceNode) {
        DeploymentMapEndpointElement endpointMatrixNotificationReceiver = new DeploymentMapEndpointElement();
        endpointMatrixNotificationReceiver.setEndpointInstanceID("MatrixNotificationsReceiver");
        endpointMatrixNotificationReceiver.setEndpointFunctionID("MatrixNotificationsReceiver");
        endpointMatrixNotificationReceiver.setEndpointType(EndpointElementTypeEnum.INGRES_MESSAGING);
        endpointMatrixNotificationReceiver.setExternalDNSEntry("gen0-iris");
        endpointMatrixNotificationReceiver.setExternalPortNumber("10700");
        endpointMatrixNotificationReceiver.setInternalPortNumber("10700");
        endpointMatrixNotificationReceiver.setIsServer(true);
        endpointMatrixNotificationReceiver.setRequiresEncryption(false);
        irisServiceNode.getEndpoints().add(endpointMatrixNotificationReceiver);

        DeploymentMapEndpointElement endpointIrisPetasosTopologySync = new DeploymentMapEndpointElement();
        endpointIrisPetasosTopologySync.setEndpointInstanceID("PetasosTopologySyncSvr");
        endpointIrisPetasosTopologySync.setEndpointFunctionID("PetasosTopologySyncSvr");
        endpointIrisPetasosTopologySync.setEndpointType(EndpointElementTypeEnum.API);
        endpointIrisPetasosTopologySync.setExternalDNSEntry("petasos-gen0-iris");
        endpointIrisPetasosTopologySync.setExternalPortNumber("10870");
        endpointIrisPetasosTopologySync.setInternalPortNumber("10870");
        endpointIrisPetasosTopologySync.setIsServer(true);
        endpointIrisPetasosTopologySync.setRequiresEncryption(false);
        irisServiceNode.getEndpoints().add(endpointIrisPetasosTopologySync);

        DeploymentMapEndpointElement endpointIrisPetasosParcelSync = new DeploymentMapEndpointElement();
        endpointIrisPetasosParcelSync.setEndpointInstanceID("PetasosResilienceParcelSyncSvr");
        endpointIrisPetasosParcelSync.setEndpointFunctionID("PetasosResilienceParcelSyncSvr");
        endpointIrisPetasosParcelSync.setEndpointType(EndpointElementTypeEnum.API);
        endpointIrisPetasosParcelSync.setExternalDNSEntry("petasos-gen0-iris");
        endpointIrisPetasosParcelSync.setExternalPortNumber("10872");
        endpointIrisPetasosParcelSync.setInternalPortNumber("10872");
        endpointIrisPetasosParcelSync.setIsServer(true);
        endpointIrisPetasosParcelSync.setRequiresEncryption(false);
        irisServiceNode.getEndpoints().add(endpointIrisPetasosParcelSync);

        DeploymentMapEndpointElement endpointIrisPetasosHeartbeatSync = new DeploymentMapEndpointElement();
        endpointIrisPetasosHeartbeatSync.setEndpointInstanceID("PetasosHeartbeatSvr");
        endpointIrisPetasosHeartbeatSync.setEndpointFunctionID("PetasosHeartbeatSvr");
        endpointIrisPetasosHeartbeatSync.setEndpointType(EndpointElementTypeEnum.API);
        endpointIrisPetasosHeartbeatSync.setExternalDNSEntry("petasos-gen0-iris");
        endpointIrisPetasosHeartbeatSync.setExternalPortNumber("10871");
        endpointIrisPetasosHeartbeatSync.setInternalPortNumber("10871");
        endpointIrisPetasosHeartbeatSync.setIsServer(true);
        endpointIrisPetasosHeartbeatSync.setRequiresEncryption(true);
        irisServiceNode.getEndpoints().add(endpointIrisPetasosHeartbeatSync);

    }

    public void createIrisPlatforms(DeploymentMapNodeElement irisSiteNode) {
        DeploymentMapNodeElement nodePlatformA = new DeploymentMapNodeElement();
        nodePlatformA.setConcurrencyMode(ConcurrencyModeEnum.CONCURRENCY_MODE_STANDALONE);
        nodePlatformA.setElementVersion("0.0.1");
        nodePlatformA.setInstanceName("___");
        nodePlatformA.setResilienceMode(ResilienceModeEnum.RESILIENCE_MODE_STANDALONE);
        nodePlatformA.setTopologyElementType(NodeElementTypeEnum.PLATFORM);
        irisSiteNode.getContainedElements().add(nodePlatformA);
        createIrisProcessingPlant(nodePlatformA);

        /*        ConfigMapNodeElement nodePlatformB = new ConfigMapNodeElement();
        nodePlatformB.setConcurrencyMode(ConcurrencyModeEnum.CONCURRENCY_MODE_STANDALONE);
        nodePlatformB.setElementVersion("0.0.1");
        nodePlatformB.setInstanceName("Derived");
        nodePlatformB.setResilienceMode(ResilienceModeEnum.RESILIENCE_MODE_STANDALONE);
        nodePlatformB.setTopologyElementType(NodeElementTypeEnum.PLATFORM);
        irisSiteNode.getContainedElements().add(nodePlatformB);  
        createIrisServices(nodePlatformB); */
    }

    public void createIrisProcessingPlant(DeploymentMapNodeElement nodeNode) {
        DeploymentMapNodeElement nodeMatrixNotificationsReceiver = new DeploymentMapNodeElement();
        nodeMatrixNotificationsReceiver.setConcurrencyMode(ConcurrencyModeEnum.CONCURRENCY_MODE_STANDALONE);
        nodeMatrixNotificationsReceiver.setElementVersion("0.0.1");
        nodeMatrixNotificationsReceiver.setInstanceName("Matrix2FHIRServices");
        nodeMatrixNotificationsReceiver.setFunctionName("Matrix2FHIRServices");
        nodeMatrixNotificationsReceiver.setResilienceMode(ResilienceModeEnum.RESILIENCE_MODE_STANDALONE);
        nodeMatrixNotificationsReceiver.setTopologyElementType(NodeElementTypeEnum.PROCESSING_PLANT);
        nodeNode.getContainedElements().add(nodeMatrixNotificationsReceiver);
        createM2FInteractProcessingPlatform(nodeMatrixNotificationsReceiver);
        createM2FTransformProcessingPlatform(nodeMatrixNotificationsReceiver);
        createM2FEdgeProcessingPlatform(nodeMatrixNotificationsReceiver);

    }

    public void createM2FInteractProcessingPlatform(DeploymentMapNodeElement serviceModuleNode) {
        DeploymentMapNodeElement nodeInteractMatrixNotificationsReceiver = new DeploymentMapNodeElement();
        nodeInteractMatrixNotificationsReceiver.setConcurrencyMode(ConcurrencyModeEnum.CONCURRENCY_MODE_STANDALONE);
        nodeInteractMatrixNotificationsReceiver.setElementVersion("0.0.1");
        nodeInteractMatrixNotificationsReceiver.setInstanceName("Interact");
        nodeInteractMatrixNotificationsReceiver.setFunctionName("Interact");
        nodeInteractMatrixNotificationsReceiver.setResilienceMode(ResilienceModeEnum.RESILIENCE_MODE_STANDALONE);
        nodeInteractMatrixNotificationsReceiver.setTopologyElementType(NodeElementTypeEnum.WORKSHOP);
        serviceModuleNode.getContainedElements().add(nodeInteractMatrixNotificationsReceiver);
        createMatrix2FHIRServicesWUPs(nodeInteractMatrixNotificationsReceiver);
    }

    public void createMatrix2FHIRServicesWUPs(DeploymentMapNodeElement nodeInteractMatrixNotificationsReceiver) {
        DeploymentMapNodeElement nodeMatrixIngresInteractWUP = new DeploymentMapNodeElement();
        nodeMatrixIngresInteractWUP.setConcurrencyMode(ConcurrencyModeEnum.CONCURRENCY_MODE_STANDALONE);
        nodeMatrixIngresInteractWUP.setElementVersion("0.0.1");
        nodeMatrixIngresInteractWUP.setInstanceName("MatrixApplicationServicesEventsReceiverWUP");
        nodeMatrixIngresInteractWUP.setFunctionName("MatrixApplicationServicesEventsReceiverWUP");
        nodeMatrixIngresInteractWUP.setResilienceMode(ResilienceModeEnum.RESILIENCE_MODE_STANDALONE);
        nodeMatrixIngresInteractWUP.setTopologyElementType(NodeElementTypeEnum.WUP);
        nodeInteractMatrixNotificationsReceiver.getContainedElements().add(nodeMatrixIngresInteractWUP);

        DeploymentMapEndpointElement endpointMatrixNotificationReceiver = new DeploymentMapEndpointElement();
        endpointMatrixNotificationReceiver.setEndpointInstanceID("MatrixNotificationReceiver");
        endpointMatrixNotificationReceiver.setEndpointFunctionID("MatrixNotificationReceiver");
        endpointMatrixNotificationReceiver.setEndpointType(EndpointElementTypeEnum.INGRES_MESSAGING);
        endpointMatrixNotificationReceiver.setInternalDNSEntry("___");
        endpointMatrixNotificationReceiver.setExternalDNSEntry("___");
        endpointMatrixNotificationReceiver.setExternalPortNumber("10700");
        endpointMatrixNotificationReceiver.setInternalPortNumber("10700");
        endpointMatrixNotificationReceiver.setIsServer(true);
        endpointMatrixNotificationReceiver.setRequiresEncryption(true);
        nodeMatrixIngresInteractWUP.getEndpoints().add(endpointMatrixNotificationReceiver);
        
        DeploymentMapNodeElement matrixMessageSplitter = new DeploymentMapNodeElement();
        matrixMessageSplitter.setConcurrencyMode(ConcurrencyModeEnum.CONCURRENCY_MODE_STANDALONE);
        matrixMessageSplitter.setElementVersion("0.0.1");
        matrixMessageSplitter.setInstanceName("MatrixApplicationServicesEventsSeperatorWUP");
        matrixMessageSplitter.setFunctionName("MatrixApplicationServicesEventsSeperatorWUP");
        matrixMessageSplitter.setResilienceMode(ResilienceModeEnum.RESILIENCE_MODE_STANDALONE);
        matrixMessageSplitter.setTopologyElementType(NodeElementTypeEnum.WUP);
        nodeInteractMatrixNotificationsReceiver.getContainedElements().add(matrixMessageSplitter);
    }

    public void createM2FTransformProcessingPlatform(DeploymentMapNodeElement serviceModuleNode) {
        DeploymentMapNodeElement nodeTransformProcessingPlant = new DeploymentMapNodeElement();
        nodeTransformProcessingPlant.setConcurrencyMode(ConcurrencyModeEnum.CONCURRENCY_MODE_STANDALONE);
        nodeTransformProcessingPlant.setElementVersion("0.0.1");
        nodeTransformProcessingPlant.setInstanceName("Transform");
        nodeTransformProcessingPlant.setFunctionName("Transform");
        nodeTransformProcessingPlant.setResilienceMode(ResilienceModeEnum.RESILIENCE_MODE_STANDALONE);
        nodeTransformProcessingPlant.setTopologyElementType(NodeElementTypeEnum.WORKSHOP);
        serviceModuleNode.getContainedElements().add(nodeTransformProcessingPlant);

        DeploymentMapNodeElement nodeMatrixTransformer1 = new DeploymentMapNodeElement();
        nodeMatrixTransformer1.setConcurrencyMode(ConcurrencyModeEnum.CONCURRENCY_MODE_STANDALONE);
        nodeMatrixTransformer1.setElementVersion("0.0.1");
        nodeMatrixTransformer1.setInstanceName("MatrixRoomCreate2FHIRGroupWUP");
        nodeMatrixTransformer1.setFunctionName("MatrixRoomCreate2FHIRGroupWUP");
        nodeMatrixTransformer1.setResilienceMode(ResilienceModeEnum.RESILIENCE_MODE_STANDALONE);
        nodeMatrixTransformer1.setTopologyElementType(NodeElementTypeEnum.WUP);
        nodeTransformProcessingPlant.getContainedElements().add(nodeMatrixTransformer1);

        DeploymentMapNodeElement nodeMatrixTransformer2 = new DeploymentMapNodeElement();
        nodeMatrixTransformer2.setConcurrencyMode(ConcurrencyModeEnum.CONCURRENCY_MODE_STANDALONE);
        nodeMatrixTransformer2.setElementVersion("0.0.1");
        nodeMatrixTransformer2.setInstanceName("MatrixRoomIM2FHIRCommunicationWUP");
        nodeMatrixTransformer2.setFunctionName("MatrixRoomIM2FHIRCommunicationWUP");
        nodeMatrixTransformer2.setResilienceMode(ResilienceModeEnum.RESILIENCE_MODE_STANDALONE);
        nodeMatrixTransformer2.setTopologyElementType(NodeElementTypeEnum.WUP);
        nodeTransformProcessingPlant.getContainedElements().add(nodeMatrixTransformer2);

        DeploymentMapNodeElement nodeMatrixTransformer3 = new DeploymentMapNodeElement();
        nodeMatrixTransformer3.setConcurrencyMode(ConcurrencyModeEnum.CONCURRENCY_MODE_STANDALONE);
        nodeMatrixTransformer3.setElementVersion("0.0.1");
        nodeMatrixTransformer3.setInstanceName("MatrixRoomName2FHIRGroup");
        nodeMatrixTransformer3.setFunctionName("MatrixRoomName2FHIRGroup");
        nodeMatrixTransformer3.setResilienceMode(ResilienceModeEnum.RESILIENCE_MODE_STANDALONE);
        nodeMatrixTransformer3.setTopologyElementType(NodeElementTypeEnum.WUP);
        nodeTransformProcessingPlant.getContainedElements().add(nodeMatrixTransformer3);
    }

    public void createM2FEdgeProcessingPlatform(DeploymentMapNodeElement serviceModuleNode) {
        DeploymentMapNodeElement nodeM2FEdge = new DeploymentMapNodeElement();
        nodeM2FEdge.setConcurrencyMode(ConcurrencyModeEnum.CONCURRENCY_MODE_STANDALONE);
        nodeM2FEdge.setElementVersion("0.0.1");
        nodeM2FEdge.setInstanceName("Edge");
        nodeM2FEdge.setFunctionName("Edge");
        nodeM2FEdge.setResilienceMode(ResilienceModeEnum.RESILIENCE_MODE_STANDALONE);
        nodeM2FEdge.setTopologyElementType(NodeElementTypeEnum.WORKSHOP);
        serviceModuleNode.getContainedElements().add(nodeM2FEdge);
        createM2FEdgeForwardWUP(nodeM2FEdge);

    }

    public void createM2FEdgeForwardWUP(DeploymentMapNodeElement nodeM2FEdge) {
        DeploymentMapNodeElement nodeIrisEdgeForward = new DeploymentMapNodeElement();
        nodeIrisEdgeForward.setConcurrencyMode(ConcurrencyModeEnum.CONCURRENCY_MODE_STANDALONE);
        nodeIrisEdgeForward.setElementVersion("0.0.1");
        nodeIrisEdgeForward.setInstanceName("Forward");
        nodeIrisEdgeForward.setFunctionName("Forward");
        nodeIrisEdgeForward.setResilienceMode(ResilienceModeEnum.RESILIENCE_MODE_STANDALONE);
        nodeIrisEdgeForward.setTopologyElementType(NodeElementTypeEnum.WUP);
        nodeM2FEdge.getContainedElements().add(nodeIrisEdgeForward);

        DeploymentMapNodeElement nodeIrisEdgeReceive = new DeploymentMapNodeElement();
        nodeIrisEdgeReceive.setConcurrencyMode(ConcurrencyModeEnum.CONCURRENCY_MODE_STANDALONE);
        nodeIrisEdgeReceive.setElementVersion("0.0.1");
        nodeIrisEdgeReceive.setInstanceName("Receive");
        nodeIrisEdgeReceive.setFunctionName("Receive");
        nodeIrisEdgeReceive.setResilienceMode(ResilienceModeEnum.RESILIENCE_MODE_STANDALONE);
        nodeIrisEdgeReceive.setTopologyElementType(NodeElementTypeEnum.WUP);
        nodeM2FEdge.getContainedElements().add(nodeIrisEdgeReceive);

        DeploymentMapEndpointElement endpointIrisEdgeReceive = new DeploymentMapEndpointElement();
        endpointIrisEdgeReceive.setEndpointInstanceID("CommunicationEX");
        endpointIrisEdgeReceive.setEndpointFunctionID("CommunicationEX");
        endpointIrisEdgeReceive.setEndpointType(EndpointElementTypeEnum.INGRES_MESSAGING);
        endpointIrisEdgeReceive.setExternalDNSEntry("Inherited");
        endpointIrisEdgeReceive.setExternalPortNumber("10705");
        endpointIrisEdgeReceive.setIsServer(true);
        endpointIrisEdgeReceive.setRequiresEncryption(false);
        nodeIrisEdgeReceive.getEndpoints().add(endpointIrisEdgeReceive);
    }

    @Override
    public Set<DeploymentMapNodeElement> buildConnectedSystemSet() {
        return(new HashSet<DeploymentMapNodeElement>());
    }

}
