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

package net.fhirfactory.pegacorn.deployment.topology.map.achetypes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import net.fhirfactory.pegacorn.deployment.topology.initialiser.TopologyMapElementTransformationServices;
import net.fhirfactory.pegacorn.deployment.topology.map.model.DeploymentMapNodeElement;
import net.fhirfactory.pegacorn.petasos.model.resilience.mode.ConcurrencyModeEnum;
import net.fhirfactory.pegacorn.petasos.model.resilience.mode.ResilienceModeEnum;
import net.fhirfactory.pegacorn.petasos.model.topology.NodeElementTypeEnum;
import net.fhirfactory.pegacorn.petasos.topology.manager.TopologyIM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Mark A Hunter
 *
 */

public abstract class PegacornSolution {
	private static final Logger LOG = LoggerFactory.getLogger(PegacornSolution.class);

    protected DeploymentMapNodeElement solutionNode;
    private String solutionName;
    private String solutionVersion;
    private ResilienceModeEnum solutionMode;
    protected Set<DeploymentMapNodeElement> connectedSubsystems;
    private boolean isInitialised;

	@Inject
	private TopologyMapElementTransformationServices 	topologyMapService;

	@Inject
	private TopologyIM topologyServer;
    
    public PegacornSolution(){
    	LOG.debug(".PegacornSolution(): Entry, Constructor called!");
        this.solutionName = specifySolutionName();
        this.solutionVersion = specifySolutionVersion();
        this.solutionMode = specifyResilienceMode();
        this.connectedSubsystems = new HashSet<DeploymentMapNodeElement>();
        isInitialised = false;
		LOG.debug(".PegacornSolution(): Exit, this.solutionName --> {}", this.solutionName);
    }
    
    @PostConstruct
    public void initialise(){
		LOG.debug(".initialise(): Entry");
		if(!isInitialised) {
			LOG.trace(".initialise(): Doing initialisation!");
			this.solutionNode = buildSolution();
			LOG.trace(".initialise(): Solution built, adding FHIRBreak Nodes");
			buildFHIRBreak();
			LOG.trace(".initialise(): FHIRBreak Nodes added, adding FHIRPit Nodes");
			buildFHIRPit();
			LOG.trace(".initialise(): FHIRPit Nodes added, adding FHIRView Nodes");
			buildFHIRView();
			LOG.trace(".initialise(): FHIRView Nodes added, adding FHIRPlace Nodes");
			buildFHIRPlace();
			LOG.trace(".initialise(): FHIRPlace Nodes added, Adding Ladon Nodes");
			buildLadon();
			LOG.trace(".initialise(): Ladon Nodes added, Adding Hestia Nodes");
			buildHestia();
			LOG.trace(".initialise(): Hestia Nodes added, Adding MITaF Nodes");
			buildMITaF();
			LOG.trace(".initialise(): MITaF Nodes added, Adding Communicate Nodes");
			buildCommunicate();
			LOG.trace(".initialise(): Communicate Nodes added, Adding Connected System Nodes");
			this.connectedSubsystems.addAll(specifyConnectedSystems());
			LOG.trace(".initialise(): Connected System Nodes added, Installing Pegacorn Solution into Topology Server");
			topologyMapService.registerDeploymentNodeAsTopologyNodeElement(this.getSolution(), null, null);
			LOG.trace(".initialise(): Installed Pegacorn Solution into Topology Server, Adding Connected Systems to Topology Server");
			for (DeploymentMapNodeElement node : connectedSubsystems) {
				topologyMapService.registerDeploymentNodeAsTopologyNodeElement(node, null, null);
			}
			LOG.trace(".initialise(): Installed Connected Systems to Topology Server, done...");
			this.isInitialised = true;
		}
		LOG.debug(".initialise(): Exit");
    }

    abstract protected String specifySolutionName();
    abstract protected String specifySolutionVersion();
    abstract protected ResilienceModeEnum specifyResilienceMode();
    
    abstract protected void specifyCommunicateExternalisedServices(DeploymentMapNodeElement subsystem);
    abstract protected void specifyMITaFExternalisedServices(DeploymentMapNodeElement subsystem);
    abstract protected void specifyFHIRPlaceExternalisedServices(DeploymentMapNodeElement subsystem);
    abstract protected void specifyFHIRPitExternalisedServices(DeploymentMapNodeElement subsystem);
    abstract protected void specifyFHIRViewExternalisedServices(DeploymentMapNodeElement subsystem);
    abstract protected void specifyLadonExternalisedServices(DeploymentMapNodeElement subsystem);
    abstract protected void specifyHestiaExternalisedServices(DeploymentMapNodeElement subsystem);
    abstract protected void specifyFHIRBreakExternalisedServices(DeploymentMapNodeElement subsystem);
    abstract protected Set<DeploymentMapNodeElement> specifyConnectedSystems();
    
    public DeploymentMapNodeElement getSolution() {
		return solutionNode;
	}

	public void setSolution(DeploymentMapNodeElement solution) {
		this.solutionNode = solution;
	}

	public String getSolutionName() {
		return solutionName;
	}

	public void setSolutionName(String solutionName) {
		this.solutionName = solutionName;
	}

	public String getSolutionVersion() {
		return solutionVersion;
	}

	public void setSolutionVersion(String solutionVersion) {
		this.solutionVersion = solutionVersion;
	}

	public Set<DeploymentMapNodeElement> getConnectedSubsystems() {
		return connectedSubsystems;
	}

	public void setConnectedSubsystems(Set<DeploymentMapNodeElement> containedSubsystems) {
		this.connectedSubsystems = containedSubsystems;
	}
	

	public ResilienceModeEnum getSolutionMode() {
		return solutionMode;
	}

	public void setSolutionMode(ResilienceModeEnum solutionMode) {
		this.solutionMode = solutionMode;
	}

	private DeploymentMapNodeElement buildSolution() {
    	DeploymentMapNodeElement solution = new DeploymentMapNodeElement();
    	solution.setConcurrencyMode(ConcurrencyModeEnum.CONCURRENCY_MODE_STANDALONE);
    	solution.setElementVersion(this.getSolutionVersion());
    	solution.setInstanceName(this.getSolutionName());
        solution.setFunctionName(this.getSolutionName());
        solution.setResilienceMode(this.getSolutionMode());
        solution.setTopologyElementType(NodeElementTypeEnum.SOLUTION);
        solution.setContainedElements(new ArrayList<DeploymentMapNodeElement>());
    	return(solution);
    }
	
	private void buildFHIRBreak() {
    	DeploymentMapNodeElement subsystem = new DeploymentMapNodeElement();
    	subsystem.setConcurrencyMode(ConcurrencyModeEnum.CONCURRENCY_MODE_STANDALONE);
    	subsystem.setElementVersion(this.getSolutionVersion());
    	subsystem.setInstanceName("FHIRBreak");
    	subsystem.setFunctionName("FHIRBreak");
    	subsystem.setResilienceMode(this.getSolutionMode());
    	subsystem.setTopologyElementType(NodeElementTypeEnum.SUBSYSTEM);
    	subsystem.setContainedElements(new ArrayList<DeploymentMapNodeElement>());
    	specifyFHIRBreakExternalisedServices(subsystem);
    	this.getSolution().getContainedElements().add(subsystem);		
	}
	
	private void buildCommunicate() {
    	DeploymentMapNodeElement subsystem = new DeploymentMapNodeElement();
    	subsystem.setConcurrencyMode(ConcurrencyModeEnum.CONCURRENCY_MODE_STANDALONE);
    	subsystem.setElementVersion(this.getSolutionVersion());
    	subsystem.setInstanceName("Communicate");
    	subsystem.setFunctionName("Communicate");
    	subsystem.setResilienceMode(this.getSolutionMode());
    	subsystem.setTopologyElementType(NodeElementTypeEnum.SUBSYSTEM);
    	subsystem.setContainedElements(new ArrayList<DeploymentMapNodeElement>());
    	specifyCommunicateExternalisedServices(subsystem);
    	this.getSolution().getContainedElements().add(subsystem);		
	}
	
	private void buildMITaF() {
    	DeploymentMapNodeElement subsystem = new DeploymentMapNodeElement();
    	subsystem.setConcurrencyMode(ConcurrencyModeEnum.CONCURRENCY_MODE_STANDALONE);
    	subsystem.setElementVersion(this.getSolutionVersion());
    	subsystem.setInstanceName("MITaF");
    	subsystem.setFunctionName("MITaF");
    	subsystem.setResilienceMode(this.getSolutionMode());
    	subsystem.setTopologyElementType(NodeElementTypeEnum.SUBSYSTEM);
    	subsystem.setContainedElements(new ArrayList<DeploymentMapNodeElement>());
    	specifyMITaFExternalisedServices(subsystem);
    	this.getSolution().getContainedElements().add(subsystem);		
	}
	
	private void buildLadon() {
    	DeploymentMapNodeElement subsystem = new DeploymentMapNodeElement();
    	subsystem.setConcurrencyMode(ConcurrencyModeEnum.CONCURRENCY_MODE_STANDALONE);
    	subsystem.setElementVersion(this.getSolutionVersion());
    	subsystem.setInstanceName("Ladon");
    	subsystem.setFunctionName("Ladon");
    	subsystem.setResilienceMode(this.getSolutionMode());
    	subsystem.setTopologyElementType(NodeElementTypeEnum.SUBSYSTEM);
    	subsystem.setContainedElements(new ArrayList<DeploymentMapNodeElement>());
    	specifyLadonExternalisedServices(subsystem);
    	this.getSolution().getContainedElements().add(subsystem);		
	}
	
	private void buildHestia() {
    	DeploymentMapNodeElement subsystem = new DeploymentMapNodeElement();
    	subsystem.setConcurrencyMode(ConcurrencyModeEnum.CONCURRENCY_MODE_STANDALONE);
    	subsystem.setElementVersion(this.getSolutionVersion());
    	subsystem.setInstanceName("Hestia");
    	subsystem.setFunctionName("Hestia");
    	subsystem.setResilienceMode(this.getSolutionMode());
    	subsystem.setTopologyElementType(NodeElementTypeEnum.SUBSYSTEM);
    	subsystem.setContainedElements(new ArrayList<DeploymentMapNodeElement>());
    	specifyHestiaExternalisedServices(subsystem);
    	this.getSolution().getContainedElements().add(subsystem);		
	}
	
	private void buildFHIRPlace() {
    	DeploymentMapNodeElement subsystem = new DeploymentMapNodeElement();
    	subsystem.setConcurrencyMode(ConcurrencyModeEnum.CONCURRENCY_MODE_STANDALONE);
    	subsystem.setElementVersion(this.getSolutionVersion());
    	subsystem.setInstanceName("FHIRPlace");
    	subsystem.setFunctionName("FHIRPlace");
    	subsystem.setResilienceMode(this.getSolutionMode());
    	subsystem.setTopologyElementType(NodeElementTypeEnum.SUBSYSTEM);
    	subsystem.setContainedElements(new ArrayList<DeploymentMapNodeElement>());
    	specifyFHIRPlaceExternalisedServices(subsystem);
    	this.getSolution().getContainedElements().add(subsystem);		
	}
	
	private void buildFHIRPit() {
    	DeploymentMapNodeElement subsystem = new DeploymentMapNodeElement();
    	subsystem.setConcurrencyMode(ConcurrencyModeEnum.CONCURRENCY_MODE_STANDALONE);
    	subsystem.setElementVersion(this.getSolutionVersion());
    	subsystem.setInstanceName("FHIRPit");
    	subsystem.setFunctionName("FHIRPit");
    	subsystem.setResilienceMode(this.getSolutionMode());
    	subsystem.setTopologyElementType(NodeElementTypeEnum.SUBSYSTEM);
    	subsystem.setContainedElements(new ArrayList<DeploymentMapNodeElement>());
    	specifyFHIRPitExternalisedServices(subsystem);
    	this.getSolution().getContainedElements().add(subsystem);		
	}
	
	private void buildFHIRView() {
    	DeploymentMapNodeElement subsystem = new DeploymentMapNodeElement();
    	subsystem.setConcurrencyMode(ConcurrencyModeEnum.CONCURRENCY_MODE_STANDALONE);
    	subsystem.setElementVersion(this.getSolutionVersion());
    	subsystem.setInstanceName("FHIRView");
    	subsystem.setFunctionName("FHIRView");
    	subsystem.setResilienceMode(this.getSolutionMode());
    	subsystem.setTopologyElementType(NodeElementTypeEnum.SUBSYSTEM);
    	subsystem.setContainedElements(new ArrayList<DeploymentMapNodeElement>());
    	specifyFHIRViewExternalisedServices(subsystem);
    	this.getSolution().getContainedElements().add(subsystem);		
	}
    
}
