package org.swrlapi.drools.sqwrl;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.dataflow.qual.Deterministic;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.swrlapi.builtins.arguments.SWRLBuiltInArgument;
import org.swrlapi.drools.extractors.DroolsSWRLBuiltInArgumentExtractor;
import org.swrlapi.drools.swrl.BA;
import org.swrlapi.exceptions.TargetSWRLRuleEngineException;

/**
 * This class represents a SQWRL collection in Drools. These are created by SQWRL collection construction operators and
 * passed to the second phase of rule execution via the {@link org.swrlapi.drools.sqwrl.DroolsSQWRLCollectionHandler}.
 *
 * @see org.swrlapi.drools.sqwrl.DroolsSQWRLCollectionHandler
 */
public class SQWRLC implements BA
{
  @NonNull private final String variableName, queryName, collectionName, collectionID;

  public SQWRLC(@NonNull String variableName, @NonNull String queryName, @NonNull String collectionName,
    @NonNull String collectionID)
  {
    this.variableName = variableName;
    this.queryName = queryName;
    this.collectionName = collectionName;
    this.collectionID = collectionID;
  }

  @NonNull public String getVariableName()
  {
    return this.variableName;
  }

  @NonNull public String getQueryName()
  {
    return this.queryName;
  }

  @NonNull public String getCollectionName()
  {
    return this.collectionName;
  }

  @NonNull public String getCollectionID()
  {
    return this.collectionID;
  }

  @NonNull @Override public SWRLBuiltInArgument extract(@NonNull DroolsSWRLBuiltInArgumentExtractor extractor)
    throws TargetSWRLRuleEngineException
  {
    return extractor.extract(this);
  }

  @SideEffectFree @NonNull @Override public String toString()
  {
    return "SQWRLC(" + getQueryName() + ", " + getCollectionName() + ", " + getCollectionID() + ")";
  }

  @SideEffectFree @Deterministic @Override public boolean equals(@Nullable Object o)
  {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    SQWRLC sqwrlc = (SQWRLC)o;

    if (variableName != null ? !variableName.equals(sqwrlc.variableName) : sqwrlc.variableName != null)
      return false;
    if (queryName != null ? !queryName.equals(sqwrlc.queryName) : sqwrlc.queryName != null)
      return false;
    if (collectionName != null ? !collectionName.equals(sqwrlc.collectionName) : sqwrlc.collectionName != null)
      return false;
    return !(collectionID != null ? !collectionID.equals(sqwrlc.collectionID) : sqwrlc.collectionID != null);

  }

  @SideEffectFree @Deterministic @Override public int hashCode()
  {
    int result = variableName != null ? variableName.hashCode() : 0;
    result = 31 * result + (queryName != null ? queryName.hashCode() : 0);
    result = 31 * result + (collectionName != null ? collectionName.hashCode() : 0);
    result = 31 * result + (collectionID != null ? collectionID.hashCode() : 0);
    return result;
  }
}
