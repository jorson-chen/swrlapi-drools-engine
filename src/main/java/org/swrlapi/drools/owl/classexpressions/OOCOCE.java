package org.swrlapi.drools.owl.classexpressions;

import org.swrlapi.drools.owl.core.DroolsBinaryObject;

/**
 * This class represents an OWL object complement of class expression in Drools. Drools is supplied with an exhaustive pairwise
 * set of classes from the list in an OWL one of class expression.
 *
 * @see org.semanticweb.owlapi.model.OWLObjectComplementOf
 */
public class OOCOCE extends DroolsBinaryObject<String, String> implements CE
{
	public OOCOCE(String id, String c)
	{
		super(id, c);
	}

	@Override
	public String getceid()
	{
		return getT1();
	}

	public String getC()
	{
		return getT2();
	}

	@Override
	public String toString()
	{
		return "OOCOCE" + super.toString();
	}
}