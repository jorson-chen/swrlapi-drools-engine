package org.swrlapi.drools.owl.dataranges;

/**
 * Class representing an OWL data complement of data range
 *
 * @see org.semanticweb.owlapi.model.OWLDataComplementOf
 */
public class DCO implements DR
{
	private final String id;
	private final DR complement;

	public DCO(String id, DR complement)
	{
		this.id = id;
		this.complement = complement;
	}

	public String getid()
	{
		return this.id;
	}

	public DR getcomplement() { return this.complement; }
}