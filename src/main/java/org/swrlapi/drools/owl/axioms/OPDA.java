package org.swrlapi.drools.owl.axioms;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;
import org.swrlapi.drools.extractors.DroolsOWLAxiomExtractor;
import org.swrlapi.exceptions.TargetSWRLRuleEngineException;

/**
 * This class represents an OWL object property declaration.
 *
 * @see org.semanticweb.owlapi.model.OWLDeclarationAxiom
 */
public class OPDA extends DroolsUnaryPropertyAxiom
{
  private static final long serialVersionUID = 1L;

  public OPDA(@NonNull String propertyID)
  {
    super(propertyID);
  }

  @NonNull @SideEffectFree @Override public String toString()
  {
    return "OPDA(" + super.toString() + ")";
  }

  @NonNull @Override public OWLDeclarationAxiom extract(@NonNull DroolsOWLAxiomExtractor extractor)
    throws TargetSWRLRuleEngineException
  {
    return extractor.extract(this);
  }

  @Override public void visit(@NonNull AVisitor visitor)
  {
    visitor.visit(this);
  }
}
