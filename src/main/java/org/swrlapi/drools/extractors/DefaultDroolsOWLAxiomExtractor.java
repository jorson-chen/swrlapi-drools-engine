package org.swrlapi.drools.extractors;

import java.util.HashSet;
import java.util.Set;

import org.semanticweb.owlapi.model.*;
import org.swrlapi.bridge.SWRLRuleEngineBridge;
import org.swrlapi.drools.owl.axioms.*;
import org.swrlapi.exceptions.TargetRuleEngineException;

/**
 * This class converts a Drools OWL axioms represented by the class {@link A} to OWLAPI axioms.
 */
public class DefaultDroolsOWLAxiomExtractor extends DroolsExtractorBase implements DroolsOWLAxiomExtractor
{
	public DefaultDroolsOWLAxiomExtractor(SWRLRuleEngineBridge bridge)
	{
		super(bridge);
	}

	@Override
	public OWLDeclarationAxiom extract(CDA da) throws TargetRuleEngineException
	{
		OWLClass cls = getOWLDataFactory().getOWLClass(prefixedName2IRI(da.getE().getName()));

		return getSWRLAPIOWLDataFactory().getOWLClassDeclarationAxiom(cls);
	}

	@Override
	public OWLDeclarationAxiom extract(IDA da) throws TargetRuleEngineException
	{
		OWLNamedIndividual individual = getOWLDataFactory().getOWLNamedIndividual(prefixedName2IRI(da.getE().getName()));

		return getSWRLAPIOWLDataFactory().getOWLIndividualDeclarationAxiom(individual);
	}

	@Override
	public OWLDeclarationAxiom extract(OPDA da) throws TargetRuleEngineException
	{
		OWLObjectProperty property = getOWLDataFactory().getOWLObjectProperty(prefixedName2IRI(da.getE().getName()));

		return getSWRLAPIOWLDataFactory().getOWLObjectPropertyDeclarationAxiom(property);
	}

	@Override
	public OWLDeclarationAxiom extract(DPDA da) throws TargetRuleEngineException
	{
		OWLDataProperty property = getOWLDataFactory().getOWLDataProperty(prefixedName2IRI(da.getE().getName()));

		return getSWRLAPIOWLDataFactory().getOWLDataPropertyDeclarationAxiom(property);
	}

	@Override
	public OWLDeclarationAxiom extract(APDA da) throws TargetRuleEngineException
	{
		OWLAnnotationProperty property = getOWLDataFactory().getOWLAnnotationProperty(prefixedName2IRI(da.getE().getName()));

		return getSWRLAPIOWLDataFactory().getOWLAnnotationPropertyDeclarationAxiom(property);
	}

	@Override
	public OWLClassAssertionAxiom extract(CAA caa) throws TargetRuleEngineException
	{
		OWLClassExpression cls = getOWLClassExpressionResolver().resolveOWLClassExpression(caa.getC());
		OWLIndividual individual = caa.getI().extract(getDroolsOWLIndividualExtractor());

		return getOWLDataFactory().getOWLClassAssertionAxiom(cls, individual);
	}

	@Override
	public OWLObjectPropertyAssertionAxiom extract(OPAA opaa) throws TargetRuleEngineException
	{
		OWLIndividual subject = opaa.getT1().extract(getDroolsOWLIndividualExtractor());
		OWLObjectPropertyExpression property = opaa.getT2().extract(getDroolsOWLEntityExtractor());
		OWLIndividual object = opaa.getT3().extract(getDroolsOWLIndividualExtractor());

		return getOWLDataFactory().getOWLObjectPropertyAssertionAxiom(property, subject, object);
	}

	@Override
	public OWLNegativeObjectPropertyAssertionAxiom extract(NOPAA nopaa) throws TargetRuleEngineException
	{
		OWLIndividual subject = nopaa.getT1().extract(getDroolsOWLIndividualExtractor());
		OWLObjectPropertyExpression property = nopaa.getT2().extract(getDroolsOWLEntityExtractor());
		OWLIndividual object = nopaa.getT3().extract(getDroolsOWLIndividualExtractor());

		return getOWLDataFactory().getOWLNegativeObjectPropertyAssertionAxiom(property, subject, object);
	}

	@Override
	public OWLDataPropertyAssertionAxiom extract(DPAA dpaa) throws TargetRuleEngineException
	{
		OWLIndividual subject = dpaa.getT1().extract(getDroolsOWLIndividualExtractor());
		OWLDataProperty property = dpaa.getT2().extract(getDroolsOWLEntityExtractor());
		OWLLiteral literal = getDroolsOWLLiteralExtractor().extract(dpaa.getT3());

		return getOWLDataFactory().getOWLDataPropertyAssertionAxiom(property, subject, literal);
	}

	@Override
	public OWLNegativeDataPropertyAssertionAxiom extract(NDPAA ndpaa) throws TargetRuleEngineException
	{
		OWLIndividual subject = ndpaa.getT1().extract(getDroolsOWLIndividualExtractor());
		OWLDataProperty property = ndpaa.getT2().extract(getDroolsOWLEntityExtractor());
		OWLLiteral literal = getDroolsOWLLiteralExtractor().extract(ndpaa.getT3());

		return getOWLDataFactory().getOWLNegativeDataPropertyAssertionAxiom(property, subject, literal);
	}

	@Override
	public OWLSameIndividualAxiom extract(SIA sia) throws TargetRuleEngineException
	{
		OWLIndividual individual1 = sia.getI1().extract(getDroolsOWLIndividualExtractor());
		OWLIndividual individual2 = sia.getI2().extract(getDroolsOWLIndividualExtractor());
		Set<OWLIndividual> individuals = new HashSet<OWLIndividual>();

		individuals.add(individual1);
		individuals.add(individual2);

		return getOWLDataFactory().getOWLSameIndividualAxiom(individuals);
	}

	@Override
	public OWLDifferentIndividualsAxiom extract(DIA dia) throws TargetRuleEngineException
	{
		OWLIndividual individual1 = dia.getI1().extract(getDroolsOWLIndividualExtractor());
		OWLIndividual individual2 = dia.getI2().extract(getDroolsOWLIndividualExtractor());
		Set<OWLIndividual> individuals = new HashSet<OWLIndividual>();

		individuals.add(individual1);
		individuals.add(individual2);

		return getOWLDataFactory().getOWLDifferentIndividualsAxiom(individuals);
	}

	@Override
	public OWLSubClassOfAxiom extract(SCA sca) throws TargetRuleEngineException
	{
		OWLClassExpression superClass = getOWLClassExpressionResolver().resolveOWLClassExpression(sca.getSup());
		OWLClassExpression subClass = getOWLClassExpressionResolver().resolveOWLClassExpression(sca.getSub());

		return getOWLDataFactory().getOWLSubClassOfAxiom(subClass, superClass);
	}

	@Override
	public OWLDisjointClassesAxiom extract(DCA dca) throws TargetRuleEngineException
	{
		OWLClassExpression class1 = getOWLClassExpressionResolver().resolveOWLClassExpression(dca.getC1());
		OWLClassExpression class2 = getOWLClassExpressionResolver().resolveOWLClassExpression(dca.getC2());
		Set<OWLClassExpression> classes = new HashSet<OWLClassExpression>();
		classes.add(class1);
		classes.add(class2);

		return getOWLDataFactory().getOWLDisjointClassesAxiom(classes);
	}

	@Override
	public OWLEquivalentClassesAxiom extract(ECA eca) throws TargetRuleEngineException
	{
		OWLClassExpression class1 = getOWLClassExpressionResolver().resolveOWLClassExpression(eca.getC1());
		OWLClassExpression class2 = getOWLClassExpressionResolver().resolveOWLClassExpression(eca.getC2());
		Set<OWLClassExpression> classes = new HashSet<OWLClassExpression>();
		classes.add(class1);
		classes.add(class2);

		return getOWLDataFactory().getOWLEquivalentClassesAxiom(classes);
	}

	@Override
	public OWLObjectPropertyDomainAxiom extract(DOPA dopa) throws TargetRuleEngineException
	{
		OWLObjectProperty property = dopa.getP().extract(getDroolsOWLEntityExtractor());
		OWLClassExpression domain = getOWLClassExpressionResolver().resolveOWLClassExpression(dopa.getD());

		return getOWLDataFactory().getOWLObjectPropertyDomainAxiom(property, domain);
	}

	@Override
	public OWLDataPropertyDomainAxiom extract(DDPA ddpa) throws TargetRuleEngineException
	{
		OWLDataProperty property = ddpa.getP().extract(getDroolsOWLEntityExtractor());
		OWLClassExpression domain = getOWLClassExpressionResolver().resolveOWLClassExpression(ddpa.getD());

		return getOWLDataFactory().getOWLDataPropertyDomainAxiom(property, domain);
	}

	@Override
	public OWLObjectPropertyRangeAxiom extract(OPRA OPRA) throws TargetRuleEngineException
	{
		OWLObjectProperty property = OPRA.getP().extract(getDroolsOWLEntityExtractor());
		OWLClassExpression range = getOWLClassExpressionResolver().resolveOWLClassExpression(OPRA.getR());

		return getOWLDataFactory().getOWLObjectPropertyRangeAxiom(property, range);
	}

	@Override
	public OWLDataPropertyRangeAxiom extract(DPRA DPRA) throws TargetRuleEngineException
	{
		OWLDataProperty property = DPRA.getP().extract(getDroolsOWLEntityExtractor());
		OWLDataRange range = getOWLDataRangeResolver().resolveOWLDataRange(DPRA.getR());

		return getOWLDataFactory().getOWLDataPropertyRangeAxiom(property, range);
	}

	@Override
	public OWLSubObjectPropertyOfAxiom extract(SOPA sopa) throws TargetRuleEngineException
	{
		OWLObjectProperty superProperty = sopa.getSup().extract(getDroolsOWLEntityExtractor());
		OWLObjectProperty subProperty = sopa.getSub().extract(getDroolsOWLEntityExtractor());

		return getOWLDataFactory().getOWLSubObjectPropertyOfAxiom(subProperty, superProperty);
	}

	@Override
	public OWLInverseObjectPropertiesAxiom extract(IOPA iopa) throws TargetRuleEngineException
	{
		OWLObjectProperty property2 = iopa.getP1().extract(getDroolsOWLEntityExtractor());
		OWLObjectProperty property1 = iopa.getP2().extract(getDroolsOWLEntityExtractor());

		return getOWLDataFactory().getOWLInverseObjectPropertiesAxiom(property1, property2);
	}

	@Override
	public OWLSubDataPropertyOfAxiom extract(SDPA sdpa) throws TargetRuleEngineException
	{
		OWLDataPropertyExpression superProperty = sdpa.getSup().extract(getDroolsOWLEntityExtractor());
		OWLDataPropertyExpression subProperty = sdpa.getSub().extract(getDroolsOWLEntityExtractor());

		return getOWLDataFactory().getOWLSubDataPropertyOfAxiom(subProperty, superProperty);
	}

	@Override
	public OWLEquivalentObjectPropertiesAxiom extract(EOPA eopa) throws TargetRuleEngineException
	{
		OWLObjectPropertyExpression property1 = eopa.getP1().extract(getDroolsOWLEntityExtractor());
		OWLObjectPropertyExpression property2 = eopa.getP2().extract(getDroolsOWLEntityExtractor());
		Set<OWLObjectPropertyExpression> properties = new HashSet<OWLObjectPropertyExpression>();
		properties.add(property1);
		properties.add(property2);

		return getOWLDataFactory().getOWLEquivalentObjectPropertiesAxiom(properties);
	}

	@Override
	public OWLEquivalentDataPropertiesAxiom extract(EDPA edpa) throws TargetRuleEngineException
	{
		OWLDataPropertyExpression property1 = edpa.getP1().extract(getDroolsOWLEntityExtractor());
		OWLDataPropertyExpression property2 = edpa.getP2().extract(getDroolsOWLEntityExtractor());
		Set<OWLDataPropertyExpression> properties = new HashSet<OWLDataPropertyExpression>();
		properties.add(property1);
		properties.add(property2);

		return getOWLDataFactory().getOWLEquivalentDataPropertiesAxiom(properties);
	}

	@Override
	public OWLDisjointObjectPropertiesAxiom extract(DJOPA djopa) throws TargetRuleEngineException
	{
		OWLObjectPropertyExpression property1 = djopa.getP1().extract(getDroolsOWLEntityExtractor());
		OWLObjectPropertyExpression property2 = djopa.getP2().extract(getDroolsOWLEntityExtractor());
		Set<OWLObjectPropertyExpression> properties = new HashSet<OWLObjectPropertyExpression>();
		properties.add(property1);
		properties.add(property2);

		return getOWLDataFactory().getOWLDisjointObjectPropertiesAxiom(properties);
	}

	@Override
	public OWLDisjointDataPropertiesAxiom extract(DJDPA djdpa) throws TargetRuleEngineException
	{
		OWLDataPropertyExpression property1 = djdpa.getP1().extract(getDroolsOWLEntityExtractor());
		OWLDataPropertyExpression property2 = djdpa.getP2().extract(getDroolsOWLEntityExtractor());
		Set<OWLDataPropertyExpression> properties = new HashSet<OWLDataPropertyExpression>();
		properties.add(property1);
		properties.add(property2);

		return getOWLDataFactory().getOWLDisjointDataPropertiesAxiom(properties);
	}

	@Override
	public OWLFunctionalObjectPropertyAxiom extract(FOPA fopa) throws TargetRuleEngineException
	{
		OWLObjectProperty property = fopa.getP().extract(getDroolsOWLEntityExtractor());

		return getOWLDataFactory().getOWLFunctionalObjectPropertyAxiom(property);
	}

	@Override
	public OWLFunctionalDataPropertyAxiom extract(FDPA fdpa) throws TargetRuleEngineException
	{
		OWLDataProperty property = fdpa.getP().extract(getDroolsOWLEntityExtractor());

		return getOWLDataFactory().getOWLFunctionalDataPropertyAxiom(property);
	}

	@Override
	public OWLInverseFunctionalObjectPropertyAxiom extract(IPA ipa) throws TargetRuleEngineException
	{
		OWLObjectProperty property = ipa.getP().extract(getDroolsOWLEntityExtractor());

		return getOWLDataFactory().getOWLInverseFunctionalObjectPropertyAxiom(property);
	}

	@Override
	public OWLIrreflexiveObjectPropertyAxiom extract(IRPA irpa) throws TargetRuleEngineException
	{
		OWLObjectProperty property = irpa.getP().extract(getDroolsOWLEntityExtractor());

		return getOWLDataFactory().getOWLIrreflexiveObjectPropertyAxiom(property);
	}

	@Override
	public OWLAsymmetricObjectPropertyAxiom extract(APA apa) throws TargetRuleEngineException
	{
		OWLObjectProperty property = apa.getP().extract(getDroolsOWLEntityExtractor());

		return getOWLDataFactory().getOWLAsymmetricObjectPropertyAxiom(property);
	}

	@Override
	public OWLSymmetricObjectPropertyAxiom extract(SPA spa) throws TargetRuleEngineException
	{
		OWLObjectProperty property = spa.getP().extract(getDroolsOWLEntityExtractor());

		return getOWLDataFactory().getOWLSymmetricObjectPropertyAxiom(property);
	}

	@Override
	public OWLTransitiveObjectPropertyAxiom extract(TPA tpa) throws TargetRuleEngineException
	{
		OWLObjectProperty property = tpa.getP().extract(getDroolsOWLEntityExtractor());

		return getOWLDataFactory().getOWLTransitiveObjectPropertyAxiom(property);
	}
}
