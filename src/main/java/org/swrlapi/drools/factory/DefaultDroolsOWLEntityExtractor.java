package org.swrlapi.drools.factory;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.swrlapi.bridge.SWRLRuleEngineBridge;
import org.swrlapi.bridge.extractors.TargetRuleEngineExtractorBase;
import org.swrlapi.drools.extractors.DroolsOWLEntityExtractor;
import org.swrlapi.drools.owl.classes.C;
import org.swrlapi.drools.owl.dataranges.D;
import org.swrlapi.drools.owl.individuals.I;
import org.swrlapi.drools.owl.core.OE;
import org.swrlapi.drools.owl.properties.AP;
import org.swrlapi.drools.owl.properties.DP;
import org.swrlapi.drools.owl.properties.OP;
import org.swrlapi.exceptions.TargetSWRLRuleEngineException;

/**
 * This class converts the Drools representation of OWL entities to their OWLAPI representation.
 *
 * @see org.semanticweb.owlapi.model.OWLEntity
 * @see OE
 */
class DefaultDroolsOWLEntityExtractor extends TargetRuleEngineExtractorBase implements DroolsOWLEntityExtractor
{
  public DefaultDroolsOWLEntityExtractor(@NonNull SWRLRuleEngineBridge bridge)
  {
    super(bridge);
  }

  @NonNull @Override public OWLClass extract(@NonNull C cls) throws TargetSWRLRuleEngineException
  {
    IRI classIRI = prefixedName2IRI(cls.getid());
    return getOWLDataFactory().getOWLClass(classIRI);
  }

  @NonNull @Override public OWLNamedIndividual extract(@NonNull I individual) throws TargetSWRLRuleEngineException
  {
    IRI individualIRI = prefixedName2IRI(individual.getid());
    return getOWLDataFactory().getOWLNamedIndividual(individualIRI);
  }

  @NonNull @Override public OWLDataProperty extract(@NonNull DP property) throws TargetSWRLRuleEngineException
  {
    IRI propertyIRI = prefixedName2IRI(property.getid());
    return getOWLDataFactory().getOWLDataProperty(propertyIRI);
  }

  @NonNull @Override public OWLAnnotationProperty extract(@NonNull AP property) throws TargetSWRLRuleEngineException
  {
    IRI propertyIRI = prefixedName2IRI(property.getid());
    return getOWLDataFactory().getOWLAnnotationProperty(propertyIRI);
  }

  @NonNull @Override public OWLObjectProperty extract(@NonNull OP property) throws TargetSWRLRuleEngineException
  {
    IRI propertyIRI = prefixedName2IRI(property.getid());
    return getOWLDataFactory().getOWLObjectProperty(propertyIRI);
  }

  @NonNull @Override public OWLDatatype extract(@NonNull D datatype) throws TargetSWRLRuleEngineException
  {
    IRI datatypeIRI = prefixedName2IRI(datatype.getid());
    return getOWLDataFactory().getOWLDatatype(datatypeIRI);
  }
}
